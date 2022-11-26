package com.github.fa2bio.domain.exception;

public class UserNotFoundException extends EntityNotFoundException{

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String mensagem) {
		super(mensagem);
	}
	
	public UserNotFoundException(Long usuarioId) {
		this(String.format("Não existe um usuário com código %d", usuarioId));
	}

}
