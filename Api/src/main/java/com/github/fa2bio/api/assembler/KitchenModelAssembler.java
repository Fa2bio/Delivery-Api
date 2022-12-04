package com.github.fa2bio.api.assembler;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.controller.KitchenController;
import com.github.fa2bio.api.model.KitchenModel;
import com.github.fa2bio.domain.model.Kitchen;

@Component
public class KitchenModelAssembler 
	extends RepresentationModelAssemblerSupport<Kitchen, KitchenModel>{

	@Autowired
	private ModelMapper modelMapper;
	
	public KitchenModelAssembler() {
		super(KitchenController.class, KitchenModel.class);
	}
	
	public KitchenModel toModel(Kitchen kitchen) {
		KitchenModel kitchenModel = createModelWithId(kitchen.getId(), kitchen);
		modelMapper.map(kitchen, kitchenModel);
		
		kitchenModel.add(linkTo(KitchenController.class).withRel("kitchens"));
		
		return kitchenModel;
	}
}
