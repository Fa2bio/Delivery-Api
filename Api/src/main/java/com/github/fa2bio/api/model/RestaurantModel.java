package com.github.fa2bio.api.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.fa2bio.api.model.view.RestaurantView;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestaurantModel {
	
	@JsonView({RestaurantView.Resumo.class, RestaurantView.ApenasNome.class})
	private Long id;
	
	@JsonView({RestaurantView.Resumo.class, RestaurantView.ApenasNome.class})
	private String nome;
	
	@JsonView(RestaurantView.Resumo.class)
	private BigDecimal taxaFrete;
	
	@JsonView(RestaurantView.Resumo.class)
	private KitchenModel cozinha;
	 
	private Boolean ativo;
	private Boolean aberto;
	private EnderecoModel endereco;
}
