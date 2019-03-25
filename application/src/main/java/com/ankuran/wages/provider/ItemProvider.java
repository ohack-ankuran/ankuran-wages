package com.ankuran.wages.provider;

import java.util.Date;
import java.util.List;

import com.ankuran.wages.model.response.ItemHistoryDTO;
import com.ankuran.wages.model.response.ItemResponseDTO;

public interface ItemProvider {

	Long addProduct(ItemResponseDTO item);
	List<ItemResponseDTO> getAllProducts();
	List<ItemResponseDTO> getProducts(String category, List<String> labels);
	ItemResponseDTO getProductById(Long id);
	ItemHistoryDTO addItemHistory(ItemHistoryDTO itemHistory);
	List<ItemHistoryDTO> getItemHistory(Long itemId, Date lowerTimeCreated, Date upperTimeCreated);
}
