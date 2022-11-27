package com.github.fa2bio.api.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductModel {
	
	private Long id;
	private String nome;
	private String descricao;
	private BigDecimal preco;
	private Boolean ativo;

}
