package com.github.fa2bio.api.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.fa2bio.api.model.view.RestaurantView;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestaurantModel {
	
	@JsonView({RestaurantView.Summary.class, RestaurantView.NameOnly.class})
	private Long id;
	
	@JsonView({RestaurantView.Summary.class, RestaurantView.NameOnly.class})
	private String name;
	
	@JsonView(RestaurantView.Summary.class)
	private BigDecimal rateShipping;
	
	@JsonView(RestaurantView.Summary.class)
	private KitchenModel kitchen;
	 
	private Boolean active;
	private Boolean open;
	private AddressModel address;
}
