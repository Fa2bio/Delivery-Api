package com.github.fa2bio.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.fa2bio.api.model.view.RestaurantView;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class KitchenModel {
	
	@JsonView(RestaurantView.Resumo.class)
	private Long id;
	
	@JsonView(RestaurantView.Resumo.class)
	private String nome;
}
