package com.github.fa2bio.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.model.input.OrderInput;
import com.github.fa2bio.domain.model.Pedido;

@Component
public class OrderInputDisassembler {
	@Autowired
	private ModelMapper modelMapper;
	
	public Pedido toDomainObject(OrderInput pedidoInput) {
		return modelMapper.map(pedidoInput, Pedido.class);
	}
	
	public void copyToDomainObject(OrderInput pedidoInput, Pedido pedido) {
		modelMapper.map(pedidoInput,pedido);
	}
}
