package com.github.fa2bio.api.model.input;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.github.fa2bio.api.model.EnderecoModel;
import com.github.fa2bio.api.model.FormaPagamentoModel;
import com.github.fa2bio.api.model.ItemPedidoModel;
import com.github.fa2bio.api.model.RestauranteResumoModel;
import com.github.fa2bio.api.model.UsuarioModel;
import com.github.fa2bio.domain.model.Endereco;
import com.github.fa2bio.domain.model.StatusPedido;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoInput {
	
	@NotNull
	private Long id;
	
	@NotNull
	@PositiveOrZero
	private BigDecimal subtotal;
	
	@NotNull
	@PositiveOrZero
	private BigDecimal taxaFrete;
	
	@NotNull
	@PositiveOrZero
	private BigDecimal valorTotal;
	
	@NotNull
	@PositiveOrZero
	private Endereco enderecoEntrega;
	
	@NotNull
	private StatusPedido status = StatusPedido.CRIADO;
	
	@NotNull
	private OffsetDateTime dataConfirmacao;
	
	@NotNull
	private OffsetDateTime dataCancelamento;
	
	@NotNull
	private OffsetDateTime dataEntrega;
	
	@Valid
	@NotNull
	private RestauranteResumoModel restauranteResumoModel;
	
	@Valid
	@NotNull
	private UsuarioModel usuarioModel;
	
	@Valid
	@NotNull
	private FormaPagamentoModel formaPagamentoModel;
	
	@Valid
	@NotNull
	private EnderecoModel enderecoModel;
	
	@Valid
	@NotNull
	private List<ItemPedidoModel> itens;  
}
