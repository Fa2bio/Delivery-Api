package com.github.fa2bio.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.model.PermissionModel;
import com.github.fa2bio.domain.model.Permissao;

@Component
public class PermissionModelAssembler {
	@Autowired
	private ModelMapper modelMapper;
	
	public PermissionModel toModel(Permissao permissao) {
		return modelMapper.map(permissao, PermissionModel.class);
	}
	
	public List<PermissionModel> toCollectionModel(Collection<Permissao> permissoes){
		return permissoes.stream()
				.map(permissao -> toModel(permissao))
				.collect(Collectors.toList());
	}
}
