package com.github.fa2bio.api.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentMethodInput {
	
	@NotBlank
	@ApiModelProperty(example = "Cartão de crédito", required = true)
	private String description;
}
