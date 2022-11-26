package com.github.fa2bio.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.model.input.UserInput;
import com.github.fa2bio.domain.model.Usuario;

@Component
public class UserInputDisassembler {
	@Autowired
	private ModelMapper modelMapper;
	
	public Usuario toDomainObject(UserInput UsuarioInput) {
		return modelMapper.map(UsuarioInput, Usuario.class);
	}
	
	public void copyToDomainObject(UserInput usuarioInput, Usuario usuario) {
		modelMapper.map(usuarioInput, usuario);
	}
}
