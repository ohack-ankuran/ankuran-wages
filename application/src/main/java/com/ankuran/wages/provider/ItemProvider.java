package com.ankuran.wages.provider;

import com.ankuran.wages.model.response.ItemResponseDTO;

public interface ItemProvider {

	Long addProduct(ItemResponseDTO item);
}
