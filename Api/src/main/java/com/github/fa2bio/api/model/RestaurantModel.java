package com.github.fa2bio.api.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.fa2bio.api.model.view.RestaurantView;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestaurantModel {
	
	@ApiModelProperty(example = "1")
	@JsonView({RestaurantView.Summary.class, RestaurantView.NameOnly.class})
	private Long id;
	
	@ApiModelProperty(example = "Thai Gourmet")
	@JsonView({RestaurantView.Summary.class, RestaurantView.NameOnly.class})
	private String name;
	
	@ApiModelProperty(example = "12.00")
	@JsonView(RestaurantView.Summary.class)
	private BigDecimal rateShipping;
	
	@JsonView(RestaurantView.Summary.class)
	private KitchenModel kitchen;
	 
	private Boolean active;
	private Boolean open;
	private AddressModel address;
}
