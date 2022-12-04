package com.github.fa2bio.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Relation(collectionRelation = "cities")
public class CityAbstractModel extends RepresentationModel<CityAbstractModel>{
	
	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Niteroi")
	private String name;
	
	@ApiModelProperty(example = "Rio De Janeiro")
	private String state;
}
