package com.github.fa2bio.domain.exception;

public class StateNotFoundException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public StateNotFoundException(String mensagem) {
		super(mensagem);
	}
	
	public StateNotFoundException(Long estadoId) {
		this(String.format("Não existe um cadastro de estado com código %d", estadoId));
	}
	
}
