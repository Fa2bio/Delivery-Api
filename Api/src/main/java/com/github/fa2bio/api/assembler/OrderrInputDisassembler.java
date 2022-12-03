package com.github.fa2bio.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.model.input.OrrderInput;
import com.github.fa2bio.domain.model.Orderr;

@Component
public class OrderrInputDisassembler {
	@Autowired
	private ModelMapper modelMapper;
	
	public Orderr toDomainObject(OrrderInput orrderInput) {
		return modelMapper.map(orrderInput, Orderr.class);
	}
	
	public void copyToDomainObject(OrrderInput orrderInput, Orderr orderr) {
		modelMapper.map(orrderInput,orderr);
	}
}
