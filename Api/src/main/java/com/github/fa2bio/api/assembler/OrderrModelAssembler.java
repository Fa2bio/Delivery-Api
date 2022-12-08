package com.github.fa2bio.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.controller.OrderController;
import com.github.fa2bio.api.model.OrderrModel;
import com.github.fa2bio.core.hypermedia.DeliveryLinks;
import com.github.fa2bio.domain.model.Orderr;

@Component
public class OrderrModelAssembler 
	extends RepresentationModelAssemblerSupport<Orderr, OrderrModel>{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private DeliveryLinks deliveryLinks;
	
	public OrderrModelAssembler() {
		super(OrderController.class, OrderrModel.class);
	}
	
	public OrderrModel toModel(Orderr orderr) {
		OrderrModel orderModel = createModelWithId(orderr.getUuiCode(), orderr);
		modelMapper.map(orderr, orderModel);
		
		orderModel.add(deliveryLinks.linkToOrders("orders"));
		
		orderModel.getRestaurant().add(deliveryLinks
				.linkToRestaurants(orderr.getRestaurant().getId()));
		
		orderModel.getClient().add(deliveryLinks
				.linkToClients(orderr.getClient().getId()));
		
		orderModel.getPaymentMethod().add(deliveryLinks
				.linkToFindPaymentMethods(orderr.getPaymentMethod().getId()));
		
		orderModel.getDeliveryAddress().getCity().add(deliveryLinks
				.linkToCities(orderr.getDeliveryAddress().getCity().getId()));
				
		orderModel.getItems().forEach(item -> {
			item.add(deliveryLinks
					.linkToItems(orderModel.getRestaurant().getId(),  item.getProductId(), "product"));
		});
		
		if(orderr.canBeConfirmed()) {
			orderModel.add(deliveryLinks
					.linkToConfirmedOrder(orderr.getUuiCode(), "confirm"));
		}

		if(orderr.canBeCancel()) {
			orderModel.add(deliveryLinks
					.linkToCancellationOrder(orderr.getUuiCode(), "cancel"));
		}
		
		if(orderr.canBeDeliver()) {
			orderModel.add(deliveryLinks
					.linkToDeliveredOrder(orderr.getUuiCode(), "deliver"));
		}
	
		return orderModel;
	}
	
}
