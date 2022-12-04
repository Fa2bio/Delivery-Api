package com.github.fa2bio.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.controller.CityController;
import com.github.fa2bio.api.controller.OrderController;
import com.github.fa2bio.api.controller.PaymentMethodsController;
import com.github.fa2bio.api.controller.RestaurantProductsController;
import com.github.fa2bio.api.controller.UserController;
import com.github.fa2bio.api.model.OrderrModel;
import com.github.fa2bio.domain.model.Orderr;

@Component
public class OrderrModelAssembler 
	extends RepresentationModelAssemblerSupport<Orderr, OrderrModel>{
	
	@Autowired
	private ModelMapper modelMapper;
	
	public OrderrModelAssembler() {
		super(OrderController.class, OrderrModel.class);
	}
	
	public OrderrModel toModel(Orderr orderr) {
		OrderrModel orderModel = createModelWithId(orderr.getUuiCode(), orderr);
		modelMapper.map(orderr, orderModel);
		
		TemplateVariables pageVariables = new TemplateVariables(
				new TemplateVariable("page", VariableType.REQUEST_PARAM),
				new TemplateVariable("size", VariableType.REQUEST_PARAM),
				new TemplateVariable("sort", VariableType.REQUEST_PARAM));
		
		TemplateVariables filterVariables = new TemplateVariables(
				new TemplateVariable("clientId", VariableType.REQUEST_PARAM),
				new TemplateVariable("restaurantId", VariableType.REQUEST_PARAM),
				new TemplateVariable("creationDateStart", VariableType.REQUEST_PARAM),
				new TemplateVariable("creationDateFinal", VariableType.REQUEST_PARAM));
		
		String ordersUrl = linkTo(OrderController.class).toUri().toString();
		
		orderModel.add(new Link(UriTemplate.of(ordersUrl, 
				pageVariables.concat(filterVariables)), "orders"));
		
		orderModel.getClient().add(linkTo(methodOn(UserController.class)
				.find(orderr.getClient().getId())).withSelfRel());
		
		orderModel.getPaymentMethod().add(linkTo(methodOn(PaymentMethodsController.class)
				.find(orderr.getPaymentMethod().getId())).withSelfRel());
		
		orderModel.getDeliveryAddress().getCity().add(linkTo(methodOn(CityController.class)
				.find(orderr.getDeliveryAddress().getCity().getId())).withSelfRel());
		
		orderModel.getItems().forEach(item -> {
			item.add(linkTo(methodOn(RestaurantProductsController.class)
					.find(orderModel.getRestaurant().getId(), item.getProductId()))
					.withRel("product"));
		});
		
		return orderModel;
	}
	
}
