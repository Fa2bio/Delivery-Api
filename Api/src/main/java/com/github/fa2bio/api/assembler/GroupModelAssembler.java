package com.github.fa2bio.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.model.GroupModel;
import com.github.fa2bio.domain.model.Grupo;

@Component
public class GroupModelAssembler {
	@Autowired
	private ModelMapper modelMapper;
	
	public GroupModel toModel(Grupo grupo) {
		return modelMapper.map(grupo, GroupModel.class);
	}
	
	public List<GroupModel> toCollectionModel(Collection<Grupo> grupos){
		return grupos.stream()
				.map(grupo -> toModel(grupo))
				.collect(Collectors.toList());
	}
}