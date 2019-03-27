package com.ankuran.wages.resource.resourceImpl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.ankuran.wages.HelperUtil;
import com.ankuran.wages.model.response.ItemHistoryDTO;
import com.ankuran.wages.model.response.ItemResponseDTO;
import com.ankuran.wages.model.response.ItemStoreResponseDTO;
import com.ankuran.wages.model.response.ItemUpdateResponseDTO;
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
	public ResponseEntity<ItemsResponseDTO> getProducts(String category, List<String> labels) {
		List<ItemResponseDTO> items = itemProvider.getProducts(category, labels != null ? labels.stream().filter(x->!StringUtils.isBlank(x)).collect(Collectors.toList()) : labels);
		return new ResponseEntity<ItemsResponseDTO>(new ItemsResponseDTO(items), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ItemUpdateResponseDTO> addItemHistory(Long productId, ItemHistoryDTO itemHistoryDTO) {
		itemHistoryDTO.setItemId(productId);
		ItemHistoryDTO itemHistory = itemProvider.addItemHistory(itemHistoryDTO);
		if (itemHistory != null && itemHistory.getId() != null && itemHistory.getId() > 0) {
			return new ResponseEntity<ItemUpdateResponseDTO>(new ItemUpdateResponseDTO(new ArrayList<>(Arrays.asList(itemHistory))), HttpStatus.CREATED);
		} else if (itemHistory != null && itemHistory.getId() != null && itemHistory.getId() == 0) {
			return new ResponseEntity<ItemUpdateResponseDTO>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<ItemUpdateResponseDTO>(HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<ItemUpdateResponseDTO> getItemHistory(Long productId, String lowerTimeCreated,
			String upperTimeCreated) throws ParseException {
		Pair<Date, Date> timeRange = HelperUtil.getTimeRange(lowerTimeCreated, upperTimeCreated);
		List<ItemHistoryDTO> itemUpdates = itemProvider.getItemHistory(productId, timeRange.getLeft(), timeRange.getRight());
		return new ResponseEntity<ItemUpdateResponseDTO>(new ItemUpdateResponseDTO(itemUpdates), HttpStatus.OK);
	}
	

}
