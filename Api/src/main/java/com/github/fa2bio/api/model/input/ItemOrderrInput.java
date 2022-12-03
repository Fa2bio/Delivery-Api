package com.github.fa2bio.api.model.input;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemOrderrInput {

	@NotNull
	@ApiModelProperty(example = "1", required = true)
	private Long productId;
	
	@NotNull
	@PositiveOrZero
	@ApiModelProperty(example = "2", required = true)
	private Integer quantity;
	 
	@ApiModelProperty(example = "Menos picante, por favor")
	private String note;
}
