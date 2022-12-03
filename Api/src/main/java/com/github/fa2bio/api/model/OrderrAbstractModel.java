  package com.github.fa2bio.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderrAbstractModel {
	
	private String uuiCode;
	private BigDecimal subtotal;
	private BigDecimal rateShipping;
	private BigDecimal totalAmount;
	private String Status;
	private OffsetDateTime creationDate; 
	private RestaurantAbstractModel restaurant; 
	private UserModel Client;
}
