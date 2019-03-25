package com.ankuran.wages.mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import com.ankuran.wages.enums.ItemHistoryEnum;
import com.ankuran.wages.enums.ItemHistoryEnum.HistoryReason;
import com.ankuran.wages.enums.ItemHistoryEnum.HistoryType;
import com.ankuran.wages.model.ItemFactDao;
import com.ankuran.wages.model.ItemLabelsDao;
import com.ankuran.wages.model.ItemsDao;
import com.ankuran.wages.model.response.EmployeeResponseDTO;
import com.ankuran.wages.model.response.ItemHistoryDTO;
import com.ankuran.wages.model.response.ItemResponseDTO;

@Component
public class ItemMapper {
	
	public ItemsDao mapItemsDtoToDao(ItemResponseDTO itemResponseDTO) {
		ItemsDao itemsDao = new ItemsDao();
		itemsDao.setName(itemResponseDTO.getName());
		itemsDao.setPicture(itemResponseDTO.getPicture());
		itemsDao.setCategory(itemResponseDTO.getCategory());
		itemsDao.setStatus(Long.valueOf(1));
		itemsDao.setTimeCreated(itemResponseDTO.getTimeCreated());
		if (itemResponseDTO.getAddedBy() != null) {
			itemsDao.setActorEmployeeId(itemResponseDTO.getAddedBy().getId());
		}
		if (itemResponseDTO.getAvailableUnits() != null) {
			itemsDao.setAvailableUnits(itemResponseDTO.getAvailableUnits());
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
		if (CollectionUtils.isNotEmpty(itemLabels)) {
			item.setLabels(itemLabels.stream().filter(Objects::nonNull).map(x-> x.getValue()).collect(Collectors.toList()));
		}
		item.setName(itemsDao.getName());
		item.setPicture(itemsDao.getPicture());
		item.setTimeCreated(itemsDao.getTimeCreated());
		item.setCategory(itemsDao.getCategory());
		item.setAvailableUnits(itemsDao.getAvailableUnits());
		return item;
	}

	public ItemFactDao mapItemFactDtoToDao(ItemHistoryDTO itemHistory) {
		ItemFactDao itemFact = new ItemFactDao();
		itemFact.setActivityId(itemHistory.getActivityId());
		itemFact.setActorFullName(itemHistory.getActorFullName());
		itemFact.setCentreId(itemHistory.getCentreId());
		itemFact.setItemId(itemHistory.getItemId());
		itemFact.setNotes(itemHistory.getNotes());
		itemFact.setReason(Long.valueOf(ItemHistoryEnum.getHistoryReasonIdFrom(itemHistory.getReason())));
		itemFact.setTimeCreated(itemHistory.getTimeCreated());
		itemFact.setType(Long.valueOf(ItemHistoryEnum.getHistoryTypeIdFrom(itemHistory.getType())));
		itemFact.setUnits(itemHistory.getUnits());
		itemFact.setDeleted(0);
		
		/*
		 * ADD {STOCK_NEW,STOCK_TRANSFER_REVERSAL}
		 * REMOVE {STOCK_TRANSFER, SALE}
		 * Type: Add , Reason : STOCK_NEW,STOCK_TRANSFER_REVERSAL: amount=null & unitprice=null
		 * Type : Remove, Reason : STOCK_TRANSFER : amount=null & unitprice=null
		 */
		if (HistoryType.REMOVE.equals(itemHistory.getType()) && HistoryReason.SALE.equals(itemHistory.getReason())) {
			itemFact.setTotalAmount(itemHistory.getTotalAmount());
			itemFact.setActualUnitSalePrice(itemHistory.getActualUnitSalePrice());
		}
		
		return itemFact;
	}
	
	public ItemHistoryDTO mapItemFactDaoToDto(ItemFactDao itemFact) {
		ItemHistoryDTO itemHistory = new ItemHistoryDTO();
		itemHistory.setId(itemFact.getId());
		itemHistory.setActivityId(itemFact.getActivityId());
		itemHistory.setActorFullName(itemFact.getActorFullName());
		itemHistory.setCentreId(itemFact.getCentreId());
		itemHistory.setItemId(itemFact.getItemId());
		itemHistory.setNotes(itemFact.getNotes());
		itemHistory.setReason(ItemHistoryEnum.getHistoryReasonFrom(itemFact.getReason()));
		itemHistory.setTimeCreated(itemFact.getTimeCreated());
		itemHistory.setType(ItemHistoryEnum.getHistoryTypeFrom(itemFact.getType()));
		itemHistory.setUnits(itemFact.getUnits());
		
		if(HistoryType.REMOVE.equals(itemHistory.getType()) && HistoryReason.SALE.equals(itemHistory.getReason())) {
			itemHistory.setTotalAmount(itemFact.getTotalAmount());
			itemHistory.setActualUnitSalePrice(itemFact.getActualUnitSalePrice());
		}
		if (itemFact.getDeleted() > 0) {
			itemHistory.setDeleted(Boolean.TRUE);
		} else {
			itemHistory.setDeleted(Boolean.FALSE);
		}
		
		return itemHistory;
	}
}
