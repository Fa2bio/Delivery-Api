package com.github.fa2bio.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.model.OrderrModel;
import com.github.fa2bio.domain.model.Orderr;

@Component
public class OrderrModelAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public OrderrModel toModel(Orderr orderr) {
		return modelMapper.map(orderr, OrderrModel.class);
	}
	
	public List<OrderrModel> toCollectionModel(Collection<Orderr> orderrs){
		return orderrs.stream()
				.map(order -> toModel(order))
				.collect(Collectors.toList());
	}
}
