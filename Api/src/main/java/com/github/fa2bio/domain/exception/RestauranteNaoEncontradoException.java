package com.github.fa2bio.domain.exception;

public class RestauranteNaoEncontradoException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public RestauranteNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public RestauranteNaoEncontradoException(Long restauranteId) {
		this(String.format("Não existe um cadastro de restaurante com código %d", restauranteId));
	}
	
}
