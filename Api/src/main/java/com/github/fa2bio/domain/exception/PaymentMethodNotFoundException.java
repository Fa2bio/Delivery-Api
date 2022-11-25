package com.github.fa2bio.domain.exception;

public class PaymentMethodNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;
	
	public PaymentMethodNotFoundException(String mensagem) {
		super(mensagem);
	}
	
	public PaymentMethodNotFoundException(Long id) {
		this(String.format("Não existe uma forma de pagamento com código %d", id));
	}
	
	public PaymentMethodNotFoundException(Long id, Long restauranteID) {
		this(String.format("Não existe uma forma de pagamento com código %d para o restaurante com código %d", id, restauranteID));
	}

}
