package com.ankuran.wages.model.response;

import java.util.List;

import com.ankuran.wages.enums.EmployeeActivityEnum;

public class DueDetails extends ActivityRecordDetails {

	private String id;
	private EmployeeActivityEnum.DueDistributionType distributionType;
	private Long quantity;
	private Double duePerItem;
	private ItemResponseDTO item;
	private List<EmployeeShare> distribution;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public EmployeeActivityEnum.DueDistributionType getDistributionType() {
		return distributionType;
	}
	public void setDistributionType(EmployeeActivityEnum.DueDistributionType distributionType) {
		this.distributionType = distributionType;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public Double getDuePerItem() {
		return duePerItem;
	}
	public void setDuePerItem(Double duePerItem) {
		this.duePerItem = duePerItem;
	}
	public ItemResponseDTO getItem() {
		return item;
	}
	public void setItem(ItemResponseDTO item) {
		this.item = item;
	}
	public List<EmployeeShare> getDistribution() {
		return distribution;
	}
	public void setDistribution(List<EmployeeShare> distribution) {
		this.distribution = distribution;
	}
}
