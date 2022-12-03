package com.github.fa2bio.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CityAbstractModel {
	
	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Niteroi")
	private String name;
	
	@ApiModelProperty(example = "Rio De Janeiro")
	private String state;
}
