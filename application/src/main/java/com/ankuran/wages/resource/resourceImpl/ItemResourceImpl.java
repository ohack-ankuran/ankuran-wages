package com.ankuran.wages.resource.resourceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.ankuran.wages.model.response.ItemResponseDTO;
import com.ankuran.wages.model.response.ItemStoreResponseDTO;
import com.ankuran.wages.model.response.ItemsResponseDTO;
import com.ankuran.wages.provider.ItemProvider;
import com.ankuran.wages.resource.ItemResource;

@Component
public class ItemResourceImpl implements ItemResource {
	
	@Autowired
	ItemProvider itemProvider;

	@Override
	public ResponseEntity<ItemStoreResponseDTO> addProduct(ItemResponseDTO itemResponseDTO) {
		Long savedItemId = itemProvider.addProduct(itemResponseDTO);
		if (savedItemId != null && savedItemId > 0) {
			return new ResponseEntity<ItemStoreResponseDTO>(new ItemStoreResponseDTO(savedItemId), HttpStatus.CREATED);
		} else if (savedItemId != null && savedItemId == 0) {
			return new ResponseEntity<ItemStoreResponseDTO>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<ItemStoreResponseDTO>(HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<ItemResponseDTO> getProduct(Long productId) {
		ItemResponseDTO item = itemProvider.getProductById(productId);
		return new ResponseEntity<ItemResponseDTO>(item, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ItemsResponseDTO> getAllProducts() {
		List<ItemResponseDTO> items = itemProvider.getAllProducts();
		return new ResponseEntity<ItemsResponseDTO>(new ItemsResponseDTO(items), HttpStatus.OK);
	}
	

}
