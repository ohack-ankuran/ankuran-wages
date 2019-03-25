package com.ankuran.wages.provider.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.ankuran.wages.enums.ItemHistoryEnum.HistoryType;
import com.ankuran.wages.mapper.ItemMapper;
import com.ankuran.wages.model.ItemFactDao;
import com.ankuran.wages.model.ItemLabelsDao;
import com.ankuran.wages.model.ItemsDao;
import com.ankuran.wages.model.response.ItemHistoryDTO;
import com.ankuran.wages.model.response.ItemResponseDTO;
import com.ankuran.wages.provider.ItemProvider;
import com.ankuran.wages.repository.ItemFactRepository;
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
	
	@Autowired
	ItemFactRepository itemFactRepository;

	@Override
	public Long addProduct(ItemResponseDTO item) {
		if (item != null && !StringUtils.isEmpty(item.getName()) && !StringUtils.isEmpty(item.getCategory())
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


	@Override
	public List<ItemResponseDTO> getProducts(String category, List<String> labels) {
		List<ItemsDao> itemDaos = new ArrayList<>();
		if (!StringUtils.isEmpty(category)) {
			itemDaos = itemRepository.findAllByCategory(category);
		} else {
			itemDaos = itemRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
		}
		List<ItemResponseDTO> items = new ArrayList<>(); 
		for (ItemsDao itemDao : itemDaos) {
			List<ItemLabelsDao> itemLabels = itemLabelsRepository.findAllByItemId(itemDao.getId());
			ItemResponseDTO item = itemMapper.mapItemsDaoTODto(itemDao, itemLabels);
			//Add only those which contains available labels
			if (CollectionUtils.isNotEmpty(labels)) {
				if (CollectionUtils.isNotEmpty(item.getLabels()) && item.getLabels().containsAll(labels)) {
					items.add(item);
				}
			} else {
				items.add(item);				
			}
		}
		return CollectionUtils.isNotEmpty(items) ? items : null;
	}


	@Override
	public ItemHistoryDTO addItemHistory(ItemHistoryDTO itemHistory) {
		Long storedId = Long.valueOf(0);
		if (baseCheckItemHistory(itemHistory)) {
			ItemFactDao itemFact = itemMapper.mapItemFactDtoToDao(itemHistory);
			if (!hasExistingItem(itemFact)) {
				itemFactRepository.save(itemFact);
				storedId = itemFact.getId();
				
				Optional<ItemsDao> itemDaoOpt = itemRepository.findById(itemHistory.getItemId());
				if (itemDaoOpt.isPresent()) {
					ItemsDao itemDao = itemDaoOpt.get();
					Long currentUnits = itemDao.getAvailableUnits();
					if (HistoryType.ADD.equals(itemHistory.getType())) {
						Long availableUnits = currentUnits + itemHistory.getUnits();
						itemDao.setAvailableUnits(availableUnits);
					} else if (HistoryType.REMOVE.equals(itemHistory.getType())) {
						Long availableUnits = currentUnits - itemHistory.getUnits();
						itemDao.setAvailableUnits(availableUnits);
					}
					itemRepository.save(itemDao);
				}
			} else {
				itemFact.setId(storedId);
			}
			return itemMapper.mapItemFactDaoToDto(itemFact);
		}
		return null;
	}


	private boolean hasExistingItem(ItemFactDao item) {
		Date lowerTimeCreated = new Date(item.getTimeCreated().getTime() - 1000);
		Date upperTimeCreated = new Date(item.getTimeCreated().getTime() + 1000);
		List<ItemFactDao> existingItemDaos = itemFactRepository.findAllByCentreIdAndItemIdAndTimeCreatedBetweenAndTypeAndReason(item.getCentreId(), item.getItemId(), lowerTimeCreated, upperTimeCreated, item.getType(), item.getReason());
		for (ItemFactDao existingItemDao : existingItemDaos) {
			if (existingItemDao.getUnits() != null && item.getUnits() != null && existingItemDao.getUnits() == item.getUnits()) {
				return true;
			}
		}
		return false;
	}


	private boolean baseCheckItemHistory(ItemHistoryDTO itemHistory) {
		return itemHistory != null && itemHistory.getCentreId() != null && itemHistory.getCentreId() > 0 
				&& itemHistory.getItemId() != null && itemHistory.getItemId() > 0 
				&& itemHistory.getTimeCreated() != null
				&& itemHistory.getType() != null && itemHistory.getReason() != null;
	}


	@Override
	public List<ItemHistoryDTO> getItemHistory(Long itemId, Date lowerTimeCreated, Date upperTimeCreated) {
		List<ItemHistoryDTO> history = new ArrayList<>();
		List<ItemFactDao> itemFacts = itemFactRepository.findAllByItemIdAndTimeCreatedBetweenOrderByTimeCreatedDesc(itemId, lowerTimeCreated, upperTimeCreated);
		if (CollectionUtils.isNotEmpty(itemFacts)) {
			history = itemFacts.stream().map(itemFact -> itemMapper.mapItemFactDaoToDto(itemFact)).collect(Collectors.toList());
		}
		return history;
	}
	
}
