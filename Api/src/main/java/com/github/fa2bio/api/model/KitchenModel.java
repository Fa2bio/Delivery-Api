package com.github.fa2bio.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.fa2bio.api.model.view.RestaurantView;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class KitchenModel {
	
	@JsonView(RestaurantView.Summary.class)
	private Long id;
	
	@JsonView(RestaurantView.Summary.class)
	private String name;
}
