package com.github.fa2bio.api.model.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInput {

	@NotBlank
	private String nome;
	
	@Email
	@NotBlank
	private String email;
}
