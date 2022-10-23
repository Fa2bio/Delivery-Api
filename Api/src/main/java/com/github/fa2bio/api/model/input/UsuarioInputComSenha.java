package com.github.fa2bio.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInputComSenha extends UsuarioInput{
	
	@NotBlank
	private String senha;
}
