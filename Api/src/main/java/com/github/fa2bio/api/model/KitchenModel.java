package com.github.fa2bio.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Relation(collectionRelation = "kitchens")
public class KitchenModel extends RepresentationModel<KitchenModel>{
	
	@ApiModelProperty(example = "1")
	//@JsonView(RestaurantView.Summary.class)
	private Long id;
	
	@ApiModelProperty(example = "Carioca")
	//@JsonView(RestaurantView.Summary.class)
	private String name;
}
