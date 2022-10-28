package com.github.fa2bio.domain.exception;

public class FormaPagamentoNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	
	public FormaPagamentoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public FormaPagamentoNaoEncontradaException(Long id) {
		this(String.format("Não existe uma forma de pagamento com código %d", id));
	}
	
	public FormaPagamentoNaoEncontradaException(Long id, Long restauranteID) {
		this(String.format("Não existe uma forma de pagamento com código %d para o restaurante com código %d", id, restauranteID));
	}

}
