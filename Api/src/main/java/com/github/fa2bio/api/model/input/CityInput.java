package com.github.fa2bio.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityInput {
	
	@NotBlank
	private String nome;
	
	@Valid
	@NotNull
	private StateIdInput estado;
}
