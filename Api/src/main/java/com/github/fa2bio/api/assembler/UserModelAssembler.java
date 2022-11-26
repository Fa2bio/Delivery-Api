package com.github.fa2bio.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.model.UserModel;
import com.github.fa2bio.domain.model.Usuario;

@Component
public class UserModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public UserModel toModel(Usuario usuario) {
		return modelMapper.map(usuario, UserModel.class);
	}
	
	public List<UserModel> toCollectionModel(Collection<Usuario> usuarios){
		return usuarios.stream()
				.map(usuario -> toModel(usuario))
				.collect(Collectors.toList());
	}
}
