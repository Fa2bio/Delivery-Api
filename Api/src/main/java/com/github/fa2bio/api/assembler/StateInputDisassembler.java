package com.github.fa2bio.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.model.input.StateInput;
import com.github.fa2bio.domain.model.Estado;

@Component
public class StateInputDisassembler {
	@Autowired
	private ModelMapper modelMapper;
	
	public Estado toDomainObject(StateInput estadoInput) {
		return modelMapper.map(estadoInput, Estado.class);
	}
	
	public void copyToDomainObject(StateInput estadoInput, Estado estado) {
		modelMapper.map(estadoInput, estado);
	}
}
