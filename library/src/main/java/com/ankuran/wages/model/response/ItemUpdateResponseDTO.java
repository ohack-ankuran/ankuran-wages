package com.ankuran.wages.model.response;

import java.util.List;

public class ItemUpdateResponseDTO {

	private List<ItemHistoryDTO> itemUpdateHistory;

	public List<ItemHistoryDTO> getItemUpdateHistory() {
		return itemUpdateHistory;
	}

	public void setItemUpdateHistory(List<ItemHistoryDTO> itemUpdateHistory) {
		this.itemUpdateHistory = itemUpdateHistory;
	}

	public ItemUpdateResponseDTO(List<ItemHistoryDTO> itemUpdateHistory) {
		super();
		this.itemUpdateHistory = itemUpdateHistory;
	}
	
}
