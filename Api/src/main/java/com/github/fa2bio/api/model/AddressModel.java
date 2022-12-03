package com.github.fa2bio.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressModel {
	
	@ApiModelProperty(example = "38400-000")
	private String zipCode;
	
	@ApiModelProperty(example = "Rua Floriano Peixoto")
	private String publicPlace;
	
	@ApiModelProperty(example = "\"1500\"")
	private String number;
	
	@ApiModelProperty(example = "Apto 901")
	private String complement;
	
	@ApiModelProperty(example = "Centro")
	private String district;
	
	private CityAbstractModel city;
}