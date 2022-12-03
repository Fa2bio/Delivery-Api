package com.github.fa2bio.domain.exception;

public class OrderNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public OrderNotFoundException(String orderCode) {
		super(String.format("There is no order with code %s", orderCode));
	}
	
}