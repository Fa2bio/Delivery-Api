package com.github.fa2bio.api.model.input;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemOrderrInput {

	@NotNull
	private Long productId;
	
	@NotNull
	@PositiveOrZero
	private Integer quantity;
	 
	private String note;
}
