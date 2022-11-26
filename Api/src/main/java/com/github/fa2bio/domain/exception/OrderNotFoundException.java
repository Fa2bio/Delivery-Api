package com.github.fa2bio.domain.exception;

public class OrderNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public OrderNotFoundException(String codigoPedido) {
		super(String.format("Não existe um pedido com código %s", codigoPedido));
	}
	
}