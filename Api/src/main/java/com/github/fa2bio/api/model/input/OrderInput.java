package com.github.fa2bio.api.model.input;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderInput {
	
	@Valid
	@NotNull
	private RestaurantIdInput restaurante;
	
	@Valid
	@NotNull
	private PaymentMethodIdInput formaPagamento;
	
	@Valid
	@NotNull
	private EnderecoInput enderecoEntrega;
	
	@Valid
	@NotNull
	@Size(min=1)
	private List<ItemPedidoInput> itens;  
}
