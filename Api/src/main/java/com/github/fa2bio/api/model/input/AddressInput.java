package com.github.fa2bio.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressInput {
	
	@NotBlank
	@ApiModelProperty(example = "38400-000", required = true)
	private String zipCode;
	
	@NotBlank
	@ApiModelProperty(example = "Rua Floriano Peixoto", required = true)
	private String publicPlace;
	
	@NotBlank
	@ApiModelProperty(example = "\"1500\"", required = true)
	private String number;
	
	@ApiModelProperty(example = "Apto 901")
	private String complement;
	
	@NotBlank
	@ApiModelProperty(example = "Centro", required = true)
	private String district;
	
	@Valid
	@NotNull
	private CityIdInput city;
}
