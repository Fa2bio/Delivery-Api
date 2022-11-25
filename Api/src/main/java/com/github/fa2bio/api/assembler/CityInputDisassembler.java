package com.github.fa2bio.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.model.input.CityInput;
import com.github.fa2bio.domain.model.Cidade;
import com.github.fa2bio.domain.model.Estado;

@Component
public class CityInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Cidade toDomainObject(CityInput cidadeInput) {
		return modelMapper.map(cidadeInput, Cidade.class);
	}
	
	public void copyToDomainObject(CityInput cidadeInput, Cidade cidade) {
		// Para evitar org.hibernate.HibernateException: identifier of an instance of 
		// com.github.fa2bio.domain.model.Estado was altered from 1 to 2
		
		cidade.setEstado(new Estado());
		modelMapper.map(cidadeInput, cidade);
	}
}
