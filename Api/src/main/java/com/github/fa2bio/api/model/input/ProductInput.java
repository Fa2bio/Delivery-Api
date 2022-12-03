package com.github.fa2bio.api.model.input;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductInput {

	@NotBlank
	@ApiModelProperty(example = "Espetinho de Cupim", required = true)
	private String name;
	
	@NotBlank
	@ApiModelProperty(example = "Acompanha farinha, mandioca e vinagrete", required = true)
	private String description;
	
	@NotNull
	@PositiveOrZero
	@ApiModelProperty(example = "12.50", required = true)
	private BigDecimal price;
	
	@NotNull
	@ApiModelProperty(example = "true", required = true)
	private Boolean active;
	
}
