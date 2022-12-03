package com.github.fa2bio.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.model.KitchenModel;
import com.github.fa2bio.domain.model.Kitchen;

@Component
public class KitchenModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public KitchenModel toModel(Kitchen kitchen) {
		return modelMapper.map(kitchen, KitchenModel.class);
	}
	
	public List<KitchenModel> toCollectionModel(List<Kitchen> kitchens){
		return kitchens.stream()
				.map(kitchen -> toModel(kitchen))
				.collect(Collectors.toList());
	}
}
