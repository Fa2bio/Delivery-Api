package com.github.fa2bio.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.model.RestaurantModel;
import com.github.fa2bio.domain.model.Restaurante;

@Component
public class RestaurantModelAssembler {
	 
	@Autowired
	private ModelMapper modelMapper;
	
	public RestaurantModel toModel(Restaurante restaurante) {
		return modelMapper.map(restaurante, RestaurantModel.class);
	}
	
	public List<RestaurantModel> toCollectionModel(List<Restaurante> restaurantes){
		return restaurantes.stream()
				.map(restaurante -> toModel(restaurante))
				.collect(Collectors.toList());
	}
}
