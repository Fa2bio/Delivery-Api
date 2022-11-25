package com.github.fa2bio.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.model.input.GroupInput;
import com.github.fa2bio.domain.model.Grupo;

@Component
public class GroupInputDisassembler {
	@Autowired
	private ModelMapper modelMapper;
	
	public Grupo toDomainObject(GroupInput grupoInput) {
		return modelMapper.map(grupoInput, Grupo.class);
	}
	
	public void copyToDomainObject(GroupInput grupoInput, Grupo grupo) {
		modelMapper.map(grupoInput, grupo);
	}
}
