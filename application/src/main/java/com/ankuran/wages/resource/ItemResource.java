package com.ankuran.wages.resource;

import java.text.ParseException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ankuran.wages.model.response.ItemHistoryDTO;
import com.ankuran.wages.model.response.ItemResponseDTO;
import com.ankuran.wages.model.response.ItemStoreResponseDTO;
import com.ankuran.wages.model.response.ItemUpdateResponseDTO;
import com.ankuran.wages.model.response.ItemsResponseDTO;

@Controller
@RequestMapping(path="/catalogue")
public interface ItemResource {

	@PostMapping(path="/products")
	public ResponseEntity<ItemStoreResponseDTO> addProduct(@RequestBody ItemResponseDTO itemResponseDTO);
	
	@GetMapping(path="/products/{productId}")
	public ResponseEntity<ItemResponseDTO> getProduct(@PathVariable("productId") Long productId);
	
	@GetMapping(path="/products")
	public ResponseEntity<ItemsResponseDTO> getProducts(@RequestParam(value="category", required=false) String category,
			@RequestParam(value="labels", required=false) List<String> labels);
	
	@PostMapping(path="/products/{productId}/history")
	public ResponseEntity<ItemUpdateResponseDTO> addItemHistory(@PathVariable("productId") Long productId, @RequestBody ItemHistoryDTO itemHistoryDTO);
	
	@GetMapping(path="/products/{productId}/history")
	public ResponseEntity<ItemUpdateResponseDTO> getItemHistory(@PathVariable("productId") Long productId,
			@RequestParam(value="lowerTimeCreated", required=false) String lowerTimeCreated,
			@RequestParam(value="upperTimeCreated", required=false) String upperTimeCreated) throws ParseException;
}
