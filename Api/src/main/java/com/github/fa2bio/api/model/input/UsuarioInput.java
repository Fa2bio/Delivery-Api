package com.github.fa2bio.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInput {

	@NotBlank
	private String nome;
	
	@Email
	@Valid
	@NotBlank
	private String email;
}
