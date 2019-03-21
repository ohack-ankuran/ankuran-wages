package com.ankuran.wages.model.response;

import java.util.ArrayList;
import java.util.List;

public class ItemStoreResponseDTO {

	private Long id;
	private List<LinksDTO> links;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<LinksDTO> getLinks() {
		return links;
	}

	public void setLinks(List<LinksDTO> links) {
		this.links = links;
	}

	public ItemStoreResponseDTO(Long itemId) {
		super();
		this.id = itemId;
		this.links = getLinks(itemId);
	}
	
	private List<LinksDTO> getLinks(Long itemId) {
		List<LinksDTO> links = new ArrayList<LinksDTO>();
		links.add(new LinksDTO(String.valueOf(itemId), "GET"));
		links.add(new LinksDTO(String.valueOf(itemId), "PUT"));
		links.add(new LinksDTO(String.valueOf(itemId), "PATCH"));
		links.add(new LinksDTO(String.valueOf(itemId), "DELETE"));
		return links; 
	}

}
