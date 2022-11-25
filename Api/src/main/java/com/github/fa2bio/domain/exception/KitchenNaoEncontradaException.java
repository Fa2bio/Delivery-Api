package com.github.fa2bio.domain.exception;

public class KitchenNaoEncontradaException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public KitchenNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public KitchenNaoEncontradaException(Long cozinhaId) {
		this(String.format("Não existe um cadastro de cozinha com código %d", cozinhaId));
	}
	
}
