package com.github.fa2bio.api.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StateInput {

	@NotBlank
	@ApiModelProperty(example = "Rio de Janeiro", required = true)
	private String name;
	
}
