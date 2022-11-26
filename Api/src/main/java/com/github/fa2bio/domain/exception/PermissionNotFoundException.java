package com.github.fa2bio.domain.exception;

public class PermissionNotFoundException extends EntityNotFoundException{

	private static final long serialVersionUID = 1L;
	
	public PermissionNotFoundException(String mensagem) {
		super(mensagem);
	}
	
	public PermissionNotFoundException(Long permissaoId) {
		this(String.format("Não existe um cadastro de permissao com código %d", permissaoId));
	}

	public PermissionNotFoundException(Long grupoId, Long permissaoId) {
		this(String.format("Não existe um cadastro de permissao com código %d para o grupo de código %d", 
				permissaoId, grupoId));
	}
}
