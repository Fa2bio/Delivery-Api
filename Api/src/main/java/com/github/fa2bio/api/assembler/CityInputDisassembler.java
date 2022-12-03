package com.github.fa2bio.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.model.input.CityInput;
import com.github.fa2bio.domain.model.City;
import com.github.fa2bio.domain.model.State;

@Component
public class CityInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public City toDomainObject(CityInput cityInput) {
		return modelMapper.map(cityInput, City.class);
	}
	
	public void copyToDomainObject(CityInput cityInput, City city) {
		
		// To avoid org.hibernate.HibernateException: identifier of an instance of
		// com.github.fa2bio.domain.model.State was altered from 1 to 2
		city.setState(new State());
		modelMapper.map(cityInput, city);
	}
}
