package com.github.fa2bio.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.model.StateModel;
import com.github.fa2bio.domain.model.Estado;

@Component
public class StateModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public StateModel toModel(Estado estado) {
		return modelMapper.map(estado, StateModel.class);
	}
	
	public List<StateModel> toCollectionModel(List<Estado> estados){
		return estados.stream()
				.map(estado -> toModel(estado))
				.collect(Collectors.toList());
	}
}
