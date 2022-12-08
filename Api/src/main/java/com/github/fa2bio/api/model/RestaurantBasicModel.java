package com.github.fa2bio.api.model;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Relation(collectionRelation = "restaurants")
public class RestaurantBasicModel extends RepresentationModel<RestaurantBasicModel>{

	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Burguer King")
	private String name;
	
	
	@ApiModelProperty(example = "12.00")
	private BigDecimal rateShipping;
	
	private KitchenModel kitchen;
	
}
