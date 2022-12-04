package com.github.fa2bio.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.controller.CityController;
import com.github.fa2bio.api.controller.StateController;
import com.github.fa2bio.api.model.CityModel;
import com.github.fa2bio.domain.model.City;

@Component
public class CityModelAssembler 
	extends RepresentationModelAssemblerSupport<City, CityModel>{

	@Autowired
	private ModelMapper modelMapper;
	
	public CityModelAssembler() {
		super(CityController.class, CityModel.class);
	}
	
	@Override
	public CityModel toModel(City city) {
		
		CityModel cityModel = createModelWithId(city.getId(), city);
		modelMapper.map(city, cityModel);
		
		cityModel.add(linkTo(methodOn(CityController.class)
				.list()).withRel("cities"));
		
		cityModel.getState().add(linkTo(methodOn(StateController.class)
				.find(cityModel.getState().getId())).withSelfRel());
		
		return cityModel;
	}
	
	@Override
	public CollectionModel<CityModel> toCollectionModel(Iterable<? extends City> entities){
		return super.toCollectionModel(entities).add(linkTo(CityController.class).withSelfRel());
	}
}
