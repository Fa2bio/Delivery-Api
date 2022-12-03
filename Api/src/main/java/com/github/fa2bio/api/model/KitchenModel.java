package com.github.fa2bio.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.fa2bio.api.model.view.RestaurantView;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class KitchenModel {
	
	@ApiModelProperty(example = "1")
	@JsonView(RestaurantView.Summary.class)
	private Long id;
	
	@ApiModelProperty(example = "Carioca")
	@JsonView(RestaurantView.Summary.class)
	private String name;
}
