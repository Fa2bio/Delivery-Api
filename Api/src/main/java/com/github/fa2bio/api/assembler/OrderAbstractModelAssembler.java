package com.github.fa2bio.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.model.OderAbstractModel;
import com.github.fa2bio.domain.model.Pedido;

@Component
public class OrderAbstractModelAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public OderAbstractModel toModel(Pedido pedido) {
		return modelMapper.map(pedido, OderAbstractModel.class);
	}
	
	public List<OderAbstractModel> toCollectionModel(Collection<Pedido> pedidos){
		return pedidos.stream()
				.map(pedido -> toModel(pedido))
				.collect(Collectors.toList());
	}
}
