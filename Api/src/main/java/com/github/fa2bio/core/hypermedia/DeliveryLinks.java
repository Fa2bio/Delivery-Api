package com.github.fa2bio.core.hypermedia;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.controller.CityController;
import com.github.fa2bio.api.controller.KitchenController;
import com.github.fa2bio.api.controller.OrderController;
import com.github.fa2bio.api.controller.PaymentMethodsController;
import com.github.fa2bio.api.controller.RestaurantController;
import com.github.fa2bio.api.controller.RestaurantProductsController;
import com.github.fa2bio.api.controller.RestaurantResponsibleUserController;
import com.github.fa2bio.api.controller.StateController;
import com.github.fa2bio.api.controller.UserClusterController;
import com.github.fa2bio.api.controller.UserController;

@Component
public class DeliveryLinks {

	public static final TemplateVariables PAGE_VARIABLES = new TemplateVariables(
			new TemplateVariable("page", VariableType.REQUEST_PARAM),
			new TemplateVariable("size", VariableType.REQUEST_PARAM),
			new TemplateVariable("sort", VariableType.REQUEST_PARAM)
	);
	
	public Link linkToOrders() {
		TemplateVariables filterVariables = new TemplateVariables(
				new TemplateVariable("clientId", VariableType.REQUEST_PARAM),
				new TemplateVariable("restaurantId", VariableType.REQUEST_PARAM),
				new TemplateVariable("creationDateStart", VariableType.REQUEST_PARAM),
				new TemplateVariable("creationDateFinal", VariableType.REQUEST_PARAM));
		
		String ordersUrl = linkTo(OrderController.class).toUri().toString();
		
		return new Link(UriTemplate.of(ordersUrl,
				PAGE_VARIABLES.concat(filterVariables)), "orders");
	}
	
	public Link linkToOrder(String rel) {
		return linkTo(OrderController.class).withRel(rel);
	}
	
	public Link linkToCities(String rel) {
		return linkTo(methodOn(CityController.class)
				.list()).withRel(rel);
	}
	
	public Link linkToCities(Long cityId) {
		return linkTo(methodOn(CityController.class)
				.find(cityId)).withSelfRel();
	}
	
	public Link linkToStates(Long stateId) {
		return linkTo(methodOn(StateController.class)
				.find(stateId)).withSelfRel();
	}
	
	public Link linkToStates(String rel) {
		return linkTo(StateController.class).withRel(rel);
	}
	
	public Link linkToStates() {
		return linkTo(StateController.class).withSelfRel();
	}
	
	public Link linkToKitchens(String rel) {
		return linkTo(KitchenController.class).withRel(rel);
	}
	
	public Link linkToRestaurants(Long restaurantId) {
		return linkTo(methodOn(RestaurantController.class)
				.find(restaurantId)).withSelfRel();
	}
	
	public Link linkToRestaurantsResponsible(Long restaurantId) {
		return linkTo(methodOn(RestaurantResponsibleUserController.class)
				.list(restaurantId)).withSelfRel();
	}
	
	public Link linkToClients(Long clientId) {
		return linkTo(methodOn(UserController.class)
				.find(clientId)).withSelfRel();
	}
	
	public Link linkToUsers(String rel) {
		return linkTo(UserController.class).withRel(rel);
	}
	
	public Link linkToUsers() {
		return linkTo(UserController.class).withSelfRel();
	}
	
	public Link linkToClustersUsers(Long userId, String rel) {
		return linkTo(methodOn(UserClusterController.class)
				.list(userId)).withRel(rel);
	}
	
	public Link linkToPaymentMethods(Long paymentId) {
		return linkTo(methodOn(PaymentMethodsController.class)
				.find(paymentId)).withSelfRel();
	}
	
	public Link linkToItems(Long restaurantId, Long productId, String rel) {
		return linkTo(methodOn(RestaurantProductsController.class)
				.find(restaurantId, productId))
				.withRel(rel);
	}
	
	
}
