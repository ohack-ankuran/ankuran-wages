package com.ankuran.wages.model.response;

import java.util.List;

public class ItemsResponseDTO {
	
	private List<ItemResponseDTO> items;

	public ItemsResponseDTO(List<ItemResponseDTO> items) {
		super();
		this.items = items;
	}

	public List<ItemResponseDTO> getItems() {
		return items;
	}

	public void setItems(List<ItemResponseDTO> items) {
		this.items = items;
	}
	
}
