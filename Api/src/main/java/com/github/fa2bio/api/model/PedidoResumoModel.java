  package com.github.fa2bio.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoResumoModel {
	
	private Long id;
	private BigDecimal subtotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;
	private OffsetDateTime dataCriacao;
	private RestauranteResumoModel restauranteResumoModel;
	private UsuarioModel usuarioModel;
}
