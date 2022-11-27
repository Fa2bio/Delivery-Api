package com.github.fa2bio.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantIdInput {

	@NotNull
	private Long id;
}
