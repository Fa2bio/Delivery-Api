  package com.github.fa2bio.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderrModel {
	
	private String uuiCode;
	private BigDecimal subtotal;
	private BigDecimal rateShipping;
	private BigDecimal totalAmount;
	private String status;
	private OffsetDateTime creationDate; 
	private OffsetDateTime confirmationDate; 
	private OffsetDateTime cancellationDate; 
	private OffsetDateTime deliveryDate; 	
	private RestaurantAbstractModel restaurant; 
	private UserModel client; 
	private PaymentMethodModel paymentMethod; 
	private AddressModel deliveryAddress; 
	private List<ItemOrderrModel> items; 
}
