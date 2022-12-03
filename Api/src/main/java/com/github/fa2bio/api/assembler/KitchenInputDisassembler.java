package com.github.fa2bio.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.model.input.KitchenInput;
import com.github.fa2bio.domain.model.Kitchen;

@Component
public class KitchenInputDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Kitchen toDomainObject(KitchenInput kitchenInput) {
		return modelMapper.map(kitchenInput, Kitchen.class);
	}
	
	public void copyToDomainObject(KitchenInput kitchenInput, Kitchen kitchen) {
		modelMapper.map(kitchenInput, kitchen);
	}
}
