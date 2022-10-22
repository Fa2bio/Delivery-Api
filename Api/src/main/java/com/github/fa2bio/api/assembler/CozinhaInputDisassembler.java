package com.github.fa2bio.api.assembler;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.model.input.CozinhaInput;
import com.github.fa2bio.domain.model.Cozinha;
import com.github.fa2bio.domain.model.Restaurante;

@Component
public class CozinhaInputDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Cozinha toDomainObject(CozinhaInput cozinhaInput) {
		return modelMapper.map(cozinhaInput, Cozinha.class);
	}
	
	public void copyToDomainObject(CozinhaInput cozinhaInput, Cozinha cozinha) {
		//Para evitar org.hibernate.HibernateException: identifier of an instance 
		//of com.github.fa2bio.domain.model.Restaurante was altered from 1 to 2
		cozinha.setRestaurantes(new ArrayList<Restaurante>());
		modelMapper.map(cozinhaInput, cozinha);
	}
}
