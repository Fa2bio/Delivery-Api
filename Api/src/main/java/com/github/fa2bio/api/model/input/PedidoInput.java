package com.github.fa2bio.api.model.input;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoInput {
	
	@Valid
	@NotNull
	private RestauranteIdInput restauranteIdInput;
	
	@Valid
	@NotNull
	private FormaPagamentoIdInput formaPagamentoIdInput;
	
	@Valid
	@NotNull
	private EnderecoInput enderecoInput;
	
	@Valid
	@NotNull
	@Size(min=1)
	private List<ItemPedidoInput> itens;  
}
