package com.ankuran.wages.provider;

import java.util.List;

import com.ankuran.wages.model.response.ItemResponseDTO;

public interface ItemProvider {

	Long addProduct(ItemResponseDTO item);
	List<ItemResponseDTO> getAllProducts();
	ItemResponseDTO getProductById(Long id);
}
