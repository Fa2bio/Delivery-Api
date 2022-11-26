package com.github.fa2bio.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.model.OrderModel;
import com.github.fa2bio.domain.model.Pedido;

@Component
public class OrderModelAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public OrderModel toModel(Pedido pedido) {
		return modelMapper.map(pedido, OrderModel.class);
	}
	
	public List<OrderModel> toCollectionModel(Collection<Pedido> pedidos){
		return pedidos.stream()
				.map(pedido -> toModel(pedido))
				.collect(Collectors.toList());
	}
}
