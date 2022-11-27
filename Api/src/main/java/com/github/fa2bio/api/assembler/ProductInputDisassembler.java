package com.github.fa2bio.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.model.input.ProductInput;
import com.github.fa2bio.domain.model.Produto;

@Component
public class ProductInputDisassembler {
	@Autowired
	private ModelMapper modelMapper;
	
	public Produto toDomainObject(ProductInput productInput) {
		return modelMapper.map(productInput, Produto.class);
	}
	
	public void copyToDomainObject(ProductInput productInput, Produto produto) {
		modelMapper.map(productInput, produto);
	}
}
