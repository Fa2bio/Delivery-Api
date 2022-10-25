package com.github.fa2bio.domain.exception;

public class PermissaoNaoEncontradaException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;
	
	public PermissaoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public PermissaoNaoEncontradaException(Long permissaoId) {
		this(String.format("Não existe um cadastro de permissao com código %d", permissaoId));
	}

	public PermissaoNaoEncontradaException(Long grupoId, Long permissaoId) {
		this(String.format("Não existe um cadastro de permissao com código %d para o grupo de código %d", 
				permissaoId, grupoId));
	}
}
