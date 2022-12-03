package com.github.fa2bio.domain.model;

import java.util.Arrays;
import java.util.List;

public enum OrderStatus {

	CREATED("Created"),
	CONFIRMED("Confirmed", CREATED),
	DELIVERED("Delivered", CONFIRMED),
	CANCELED("Canceled", CREATED, CONFIRMED);
	
	private String description;
	private List<OrderStatus> statusPrevious;
	
	OrderStatus(String description, OrderStatus... statusPrevious){
		  this.description = description;
		  this.statusPrevious = Arrays.asList(statusPrevious);
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public boolean cannotChangeTo(OrderStatus newStatus) {
		return !newStatus.statusPrevious.contains(this);
	}
}