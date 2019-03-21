package com.ankuran.wages.mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ankuran.wages.model.ItemLabelsDao;
import com.ankuran.wages.model.ItemsDao;
import com.ankuran.wages.model.response.EmployeeResponseDTO;
import com.ankuran.wages.model.response.ItemResponseDTO;

@Component
public class ItemMapper {
	
	public ItemsDao mapItemsDtoToDao(ItemResponseDTO itemResponseDTO) {
		ItemsDao itemsDao = new ItemsDao();
		itemsDao.setName(itemResponseDTO.getName());
		itemsDao.setPicture(itemResponseDTO.getPicture());
		itemsDao.setCategory(itemResponseDTO.getType());
		itemsDao.setStatus(Long.valueOf(1));
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

	public ItemResponseDTO mapItemsDaoTODto(ItemsDao itemsDao, List<ItemLabelsDao> itemLabels) {
		ItemResponseDTO item = new ItemResponseDTO();
		if (itemsDao.getStatus() != null && itemsDao.getStatus() > 0) {
			item.setActive(true);
		} else {
			item.setActive(false);
		}
		
		if (itemsDao.getActorEmployeeId() != null && itemsDao.getActorEmployeeId() > 0) {
			EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO();
			employeeResponseDTO.setId(itemsDao.getActorEmployeeId());
			item.setAddedBy(employeeResponseDTO);
		}
		item.setId(itemsDao.getId());
		item.setLabels(itemLabels.stream().filter(Objects::nonNull).map(x-> x.getValue()).collect(Collectors.toList()));
		item.setName(itemsDao.getName());
		item.setPicture(itemsDao.getPicture());
		item.setTimeCreated(itemsDao.getTimeCreated());
		item.setType(itemsDao.getCategory());
		return item;
	}

}
