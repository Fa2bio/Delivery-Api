package com.github.fa2bio.domain.exception;

public class UsuarioNaoEncontradoException extends EntityNotFoundException{

	private static final long serialVersionUID = 1L;

	public UsuarioNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public UsuarioNaoEncontradoException(Long usuarioId) {
		this(String.format("Não existe um usuário com código %d", usuarioId));
	}

}
