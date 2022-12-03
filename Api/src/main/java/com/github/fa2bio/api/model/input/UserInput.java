package com.github.fa2bio.api.model.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInput {

	@NotBlank
	@ApiModelProperty(example = "João da Silva", required = true)
	private String name;
	
	@Email
	@NotBlank
	@ApiModelProperty(example = "joao.ger@algafood.com.br", required = true)
	private String email;
}
