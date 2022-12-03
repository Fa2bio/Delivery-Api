package com.github.fa2bio.api.model.input;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrrderInput {
	
	@Valid
	@NotNull
	private RestaurantIdInput restaurant;
	
	@Valid
	@NotNull
	private PaymentMethodIdInput paymentMethod;
	
	@Valid
	@NotNull
	private AddressInput deliveryAddress;
	
	@Valid
	@NotNull
	@Size(min=1)
	private List<ItemOrderrInput> items;  
}
