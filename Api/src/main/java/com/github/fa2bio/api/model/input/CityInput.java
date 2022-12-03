package com.github.fa2bio.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityInput {
	
	@NotBlank
	@ApiModelProperty(example = "Niteroi", required = true)
	private String name;
	
	@Valid
	@NotNull
	private StateIdInput state;
}
