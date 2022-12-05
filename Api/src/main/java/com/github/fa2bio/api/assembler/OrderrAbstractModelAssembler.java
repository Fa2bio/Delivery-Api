package com.github.fa2bio.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.controller.OrderController;
import com.github.fa2bio.api.model.OrderrAbstractModel;
import com.github.fa2bio.core.hypermedia.DeliveryLinks;
import com.github.fa2bio.domain.model.Orderr;

@Component
public class OrderrAbstractModelAssembler 
	extends RepresentationModelAssemblerSupport<Orderr, OrderrAbstractModel>{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private DeliveryLinks deliveryLinks;
	
	public OrderrAbstractModelAssembler() {
		super(OrderController.class, OrderrAbstractModel.class);
	}
	public OrderrAbstractModel toModel(Orderr orderr) {
		OrderrAbstractModel orderModel = createModelWithId(orderr.getUuiCode(), orderr);
		modelMapper.map(orderr, orderModel);
		
		orderModel.add(deliveryLinks
				.linkToOrder("orders"));
		
		orderModel.getRestaurant().add(deliveryLinks
				.linkToRestaurants(orderr.getRestaurant().getId()));
		
		orderModel.getClient().add(deliveryLinks
				.linkToClients(orderr.getClient().getId()));
				
		return orderModel;
	}

}
