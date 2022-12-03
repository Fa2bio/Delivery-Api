package com.github.fa2bio.api.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordInput {

	@NotBlank
	@ApiModelProperty(example = "123", required = true)
	private String currentPassword;
	
	@NotBlank
	@ApiModelProperty(example = "123", required = true)
	private String newPassword;
}
