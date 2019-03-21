package com.ankuran.wages.provider.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.ankuran.wages.mapper.ItemMapper;
import com.ankuran.wages.model.ItemLabelsDao;
import com.ankuran.wages.model.ItemsDao;
import com.ankuran.wages.model.response.ItemResponseDTO;
import com.ankuran.wages.provider.ItemProvider;
import com.ankuran.wages.repository.ItemLabelsRepository;
import com.ankuran.wages.repository.ItemsRepository;

@Component
public class ItemProviderImpl implements ItemProvider{

	@Autowired
	ItemMapper itemMapper;
	
	@Autowired
	ItemsRepository itemRepository;
	
	@Autowired
	ItemLabelsRepository itemLabelsRepository;

	@Override
	public Long addProduct(ItemResponseDTO item) {
		if (item != null && !StringUtils.isEmpty(item.getName()) && !StringUtils.isEmpty(item.getType())
				&& item.getTimeCreated() != null) {
			ItemsDao itemDao = itemMapper.mapItemsDtoToDao(item);
			if (!hasExistingItem(itemDao)) {
				itemRepository.save(itemDao);
			}
			if (itemDao.getId() != null && itemDao.getId() > 0 && CollectionUtils.isNotEmpty(item.getLabels())) {
				List<ItemLabelsDao> itemLabels = itemMapper.getItemLabelsFromItemsDto(item, itemDao.getId(), item.getTimeCreated());
				itemLabelsRepository.saveAll(itemLabels);
			} else {
				return Long.valueOf(0);
			}
			return itemDao.getId();
		}
		return null;
	}

	
	private boolean hasExistingItem(ItemsDao item) {
		Date lowerTimeCreated = new Date(item.getTimeCreated().getTime() - 1000);
		Date upperTimeCreated = new Date(item.getTimeCreated().getTime() + 1000);
		List<ItemsDao> existingItemDaos = itemRepository.findAllByTimeCreatedBetween(lowerTimeCreated, upperTimeCreated);
		for (ItemsDao existingItemDao : existingItemDaos) {
			if (!StringUtils.isEmpty(existingItemDao.getName()) && existingItemDao.getName().equals(item.getName())
					&& !StringUtils.isEmpty(existingItemDao.getCategory()) && existingItemDao.getCategory().equals(item.getCategory())) {
				return true;
			}
		}
		return false;
	}


	@Override
	public List<ItemResponseDTO> getAllProducts() {
		List<ItemsDao> itemsDao = itemRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
		List<ItemResponseDTO> items = new ArrayList<>();
		for (ItemsDao itemDao : itemsDao) {
			List<ItemLabelsDao> itemLabels = itemLabelsRepository.findAllByItemId(itemDao.getId());
			ItemResponseDTO item = itemMapper.mapItemsDaoTODto(itemDao, itemLabels);
			items.add(item);
		}
		return CollectionUtils.isNotEmpty(items) ? items : null;
	}


	@Override
	public ItemResponseDTO getProductById(Long id) {
		Optional<ItemsDao> itemDao = itemRepository.findById(id);
		if (itemDao.isPresent()) {
			List<ItemLabelsDao> itemLabels = itemLabelsRepository.findAllByItemId(itemDao.get().getId());
			ItemResponseDTO item = itemMapper.mapItemsDaoTODto(itemDao.get(), itemLabels);
			return item;
		}
		return null;
	}
	
}
