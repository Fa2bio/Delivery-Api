package com.github.fa2bio.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.model.input.UsuarioInput;
import com.github.fa2bio.domain.model.Usuario;

@Component
public class UsuarioInputDisassembler {
	@Autowired
	private ModelMapper modelMapper;
	
	public Usuario toDomainObject(UsuarioInput UsuarioInput) {
		return modelMapper.map(UsuarioInput, Usuario.class);
	}
	
	public void copyToDomainObject(UsuarioInput usuarioInput, Usuario usuario) {
		modelMapper.map(usuarioInput, usuario);
	}
}
