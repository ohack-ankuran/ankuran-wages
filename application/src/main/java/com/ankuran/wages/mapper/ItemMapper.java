package com.ankuran.wages.mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ankuran.wages.model.ItemLabelsDao;
import com.ankuran.wages.model.ItemsDao;
import com.ankuran.wages.model.response.ItemResponseDTO;

@Component
public class ItemMapper {
	
	public ItemsDao mapItemsDtoToDao(ItemResponseDTO itemResponseDTO) {
		ItemsDao itemsDao = new ItemsDao();
		itemsDao.setName(itemResponseDTO.getName());
		itemsDao.setPicture(itemResponseDTO.getPicture());
		itemsDao.setCategory(itemResponseDTO.getType());
		if (itemResponseDTO.getActive() != null && Boolean.TRUE.equals(itemResponseDTO.getActive())) { 
				itemsDao.setStatus(Long.valueOf(1));
		} else {
			itemsDao.setStatus(Long.valueOf(0));
		}
		itemsDao.setTimeCreated(itemResponseDTO.getTimeCreated());
		if (itemResponseDTO.getAddedBy() != null) {
			itemsDao.setActorEmployeeId(itemResponseDTO.getAddedBy().getId());
		}
		return itemsDao;
	}

	public List<ItemLabelsDao> getItemLabelsFromItemsDto(ItemResponseDTO item, Long itemId, Date timeCreated) {
		List<ItemLabelsDao> itemLabels = new ArrayList<>();
		for (String label : item.getLabels()) {
			ItemLabelsDao itemLabel = new ItemLabelsDao();
			itemLabel.setItemId(itemId);
			itemLabel.setStatus(Long.valueOf(1));
			itemLabel.setTimeCreated(timeCreated);
			itemLabel.setValue(label);
			itemLabels.add(itemLabel);
		}
		return itemLabels;
	}

}
