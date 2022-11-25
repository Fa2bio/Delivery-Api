package com.github.fa2bio.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.fa2bio.api.model.view.RestauranteView;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class KitchenModel {
	
	@JsonView(RestauranteView.Resumo.class)
	private Long id;
	
	@JsonView(RestauranteView.Resumo.class)
	private String nome;
}