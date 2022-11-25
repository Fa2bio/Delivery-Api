package com.github.fa2bio.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.model.KitchenModel;
import com.github.fa2bio.domain.model.Cozinha;

@Component
public class KitchenModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public KitchenModel toModel(Cozinha cozinha) {
		return modelMapper.map(cozinha, KitchenModel.class);
	}
	
	public List<KitchenModel> toCollectionModel(List<Cozinha> cozinhas){
		return cozinhas.stream()
				.map(cozinha -> toModel(cozinha))
				.collect(Collectors.toList());
	}
}
