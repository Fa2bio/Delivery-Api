  package com.github.fa2bio.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonFilter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonFilter("pedidoFilter")
public class PedidoResumoModel {
	
	private String codigo;
	private BigDecimal subtotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;
	private String Status;
	private OffsetDateTime dataCriacao;
	private RestauranteResumoModel restauranteResumoModel;
	private UsuarioModel usuarioModel;
}
