package com.github.fa2bio.domain.exception;

public class PaymentMethodNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;
	
	public PaymentMethodNotFoundException(String message) {
		super(message);
	}
	
	public PaymentMethodNotFoundException(Long id) {
		this(String.format("There is no payment method with code %d", id));
	}
	
	public PaymentMethodNotFoundException(Long id, Long restaurantId) {
		this(String.format("There is no payment method with code %d for the restaurant with code %d",
				id, restaurantId));
	}

}
