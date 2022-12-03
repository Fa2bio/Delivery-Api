package com.github.fa2bio.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.model.CityModel;
import com.github.fa2bio.domain.model.City;

@Component
public class CityModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public CityModel toModel(City city) {
		return modelMapper.map(city, CityModel.class);
	}
	
	public List<CityModel> toCollectionModel(List<City> cities){
		return cities.stream()
				.map(city -> toModel(city))
				.collect(Collectors.toList());
	}
}
