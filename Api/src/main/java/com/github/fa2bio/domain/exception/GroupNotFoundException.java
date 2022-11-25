package com.github.fa2bio.domain.exception;

public class GroupNotFoundException extends EntityNotFoundException{

	private static final long serialVersionUID = 1L;
	
	public GroupNotFoundException(String mensagem) {
		super(mensagem);
	}

	public GroupNotFoundException(Long grupoId) {
		super(String.format("Não existe um cadastro de grupo com código %d", grupoId));
	}
}
