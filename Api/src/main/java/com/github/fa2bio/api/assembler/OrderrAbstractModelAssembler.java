package com.github.fa2bio.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.controller.OrderController;
import com.github.fa2bio.api.controller.RestaurantController;
import com.github.fa2bio.api.model.OrderrAbstractModel;
import com.github.fa2bio.domain.model.Orderr;

@Component
public class OrderrAbstractModelAssembler 
	extends RepresentationModelAssemblerSupport<Orderr, OrderrAbstractModel>{
	
	@Autowired
	private ModelMapper modelMapper;
	
	public OrderrAbstractModelAssembler() {
		super(OrderController.class, OrderrAbstractModel.class);
	}
	public OrderrAbstractModel toModel(Orderr orderr) {
		OrderrAbstractModel orderModel = createModelWithId(orderr.getUuiCode(), orderr);
		modelMapper.map(orderr, orderModel);
		
		orderModel.add(linkTo(OrderController.class).withRel("orders"));
		
		orderModel.getRestaurant().add(linkTo(methodOn(RestaurantController.class)
				.find(orderr.getRestaurant().getId())).withSelfRel());
		
		orderModel.getClient().add(linkTo(methodOn(RestaurantController.class)
				.find(orderr.getClient().getId())).withSelfRel());
		
		return orderModel;
	}

}
