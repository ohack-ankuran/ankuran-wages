package com.ankuran.wages.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ankuran.wages.model.response.ItemResponseDTO;
import com.ankuran.wages.model.response.ItemStoreResponseDTO;

@Controller
@RequestMapping(path="/catalogue")
public interface ItemResource {

	@PostMapping(path="/products")
	public ResponseEntity<ItemStoreResponseDTO> addProduct(@RequestBody ItemResponseDTO itemResponseDTO);
	
	@GetMapping(path="/products/{productId}")
	public ResponseEntity<ItemResponseDTO> getProduct(@PathVariable Long productId);
	
}
