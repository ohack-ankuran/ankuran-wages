package com.ankuran.wages.model.response;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ActivityStoreResponseDTO {
	private String id;
	private List<LinksDTO> links;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<LinksDTO> getLinks() {
		return links;
	}
	public void setLinks(List<LinksDTO> links) {
		this.links = links;
	}
	public ActivityStoreResponseDTO(Long centreId, Long employeeId, BigInteger activityId) {
		super();
		this.id = activityId.toString();
		this.links = getLinks(String.valueOf(centreId), String.valueOf(employeeId), activityId.toString());
	}
	private List<LinksDTO> getLinks(String centreId, String employeeId, String activityId) {
		List<LinksDTO> links = new ArrayList<LinksDTO>();
		links.add(new LinksDTO(centreId, employeeId, activityId, "GET"));
		links.add(new LinksDTO(centreId, employeeId, activityId, "PUT"));
		links.add(new LinksDTO(centreId, employeeId, activityId, "PATCH"));
		links.add(new LinksDTO(centreId, employeeId, activityId, "DELETE"));
		return links; 
	}
}
