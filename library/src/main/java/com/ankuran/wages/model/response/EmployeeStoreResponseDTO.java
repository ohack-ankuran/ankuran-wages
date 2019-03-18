package com.ankuran.wages.model.response;

import java.util.ArrayList;
import java.util.List;

public class EmployeeStoreResponseDTO {
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
	public EmployeeStoreResponseDTO(Long employeeId, Long centreId) {
		super();
		this.id = employeeId;
		this.links = getLinks(employeeId, centreId);
	}
	private List<LinksDTO> getLinks(Long employeeId, Long centreId) {
		List<LinksDTO> links = new ArrayList<LinksDTO>();
		links.add(new LinksDTO(String.valueOf(centreId), String.valueOf(employeeId), "GET"));
		links.add(new LinksDTO(String.valueOf(centreId), String.valueOf(employeeId), "PUT"));
		links.add(new LinksDTO(String.valueOf(centreId), String.valueOf(employeeId), "PATCH"));
		links.add(new LinksDTO(String.valueOf(centreId), String.valueOf(employeeId), "DELETE"));
		return links; 
	}
	
}
