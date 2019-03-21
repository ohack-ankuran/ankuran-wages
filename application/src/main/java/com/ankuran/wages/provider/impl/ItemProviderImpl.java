package com.ankuran.wages.provider.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
			itemRepository.save(itemDao);
			if (CollectionUtils.isNotEmpty(item.getLabels())) {
				List<ItemLabelsDao> itemLabels = itemMapper.getItemLabelsFromItemsDto(item, itemDao.getId(), item.getTimeCreated());
				itemLabelsRepository.saveAll(itemLabels);
			}
			return item.getId();
		}
		return null;
	}
	
}
