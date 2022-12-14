package com.github.fa2bio.core.hypermedia;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.controller.CityController;
import com.github.fa2bio.api.controller.ClusterController;
import com.github.fa2bio.api.controller.ClusterPermissionsController;
import com.github.fa2bio.api.controller.KitchenController;
import com.github.fa2bio.api.controller.OrderController;
import com.github.fa2bio.api.controller.OrderStatusController;
import com.github.fa2bio.api.controller.PaymentMethodsController;
import com.github.fa2bio.api.controller.PermissionController;
import com.github.fa2bio.api.controller.RestaurantController;
import com.github.fa2bio.api.controller.RestaurantPaymentMethodController;
import com.github.fa2bio.api.controller.RestaurantPhotoProductController;
import com.github.fa2bio.api.controller.RestaurantProductsController;
import com.github.fa2bio.api.controller.RestaurantResponsibleUserController;
import com.github.fa2bio.api.controller.StateController;
import com.github.fa2bio.api.controller.StatisticsController;
import com.github.fa2bio.api.controller.UserClusterController;
import com.github.fa2bio.api.controller.UserController;

@Component
public class DeliveryLinks {

	public static final TemplateVariables PAGE_VARIABLES = new TemplateVariables(
			new TemplateVariable("page", VariableType.REQUEST_PARAM),
			new TemplateVariable("size", VariableType.REQUEST_PARAM),
			new TemplateVariable("sort", VariableType.REQUEST_PARAM)
	);
	
	public static final TemplateVariables PROJECTION_VARIABLES = new TemplateVariables(
			new TemplateVariable("projection", VariableType.REQUEST_PARAM));
		
	public Link linkToOrders(String rel) {
		
		TemplateVariables filterVariables = new TemplateVariables(
				new TemplateVariable("clientId", VariableType.REQUEST_PARAM),
				new TemplateVariable("restaurantId", VariableType.REQUEST_PARAM),
				new TemplateVariable("creationDateStart", VariableType.REQUEST_PARAM),
				new TemplateVariable("creationDateFinal", VariableType.REQUEST_PARAM));
		
		String ordersUrl = linkTo(OrderController.class).toUri().toString();
		
		return new Link(UriTemplate.of(ordersUrl,
				PAGE_VARIABLES.concat(filterVariables)), rel);
	}
	
	public Link linkToStatisticsDailySales(String rel) {
		
		TemplateVariables filterVariables = new TemplateVariables(
				new TemplateVariable("restaurantId", VariableType.REQUEST_PARAM),
				new TemplateVariable("creationDateStart", VariableType.REQUEST_PARAM),
				new TemplateVariable("creationDateFinal", VariableType.REQUEST_PARAM),
				new TemplateVariable("timeOffset", VariableType.REQUEST_PARAM));
		
		String ordersUrl = linkTo(methodOn(StatisticsController.class)
				.queryDailySalesJson(null, null))
				.toUri().toString();
		
		return new Link(UriTemplate.of(ordersUrl, filterVariables), rel);
	}
	
	public Link linkToOrder(String rel) {
		return linkTo(OrderController.class).withRel(rel);
	}
	
	public Link linkToCities() {
		return linkToCities(IanaLinkRelations.SELF.value());
	}
	
	public Link linkToCities(String rel) {
		return linkTo(methodOn(CityController.class)
				.list()).withRel(rel);
	}
	
	public Link linkToCities(Long cityId) {
		return linkTo(methodOn(CityController.class)
				.find(cityId)).withSelfRel();
	}
	
	public Link linkToStates() {
		return linkToStates(IanaLinkRelations.SELF.value());
	}
	
	public Link linkToStates(Long stateId) {
		return linkTo(methodOn(StateController.class)
				.find(stateId)).withSelfRel();
	}
	
	public Link linkToStates(String rel) {
		return linkTo(StateController.class).withRel(rel);
	}

	public Link linkToKitchens(String rel) {
		return linkTo(KitchenController.class).withRel(rel);
	}
	
	public Link linkToKitchens(Long kitchenId, String rel) {
		return linkTo(methodOn(KitchenController.class)
				.find(kitchenId)).withRel(rel);
	}
	
	public Link linkToKitchens(Long kitchenId) {
		return linkToKitchens(kitchenId, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToRestaurants() {
		return linkToRestaurants(IanaLinkRelations.SELF.value());
	}
	
	public Link linkToRestaurants(Long restaurantId) {
		return linkTo(methodOn(RestaurantController.class)
				.find(restaurantId)).withSelfRel();
	}
	
	public Link linkToRestaurants(String rel){
		return linkTo(RestaurantController.class).withRel(rel);
	}
	
	public Link linkToRestaurantsOpen(Long restaurantId, String rel) {
		return linkTo(methodOn(RestaurantController.class)
				.open(restaurantId)).withRel(rel);
	}
	
	public Link linkToRestaurantsClose(Long restaurantId, String rel) {
		return linkTo(methodOn(RestaurantController.class)
				.close(restaurantId)).withRel(rel);
	}
	
	public Link linkToRestaurantsActivate(Long restaurantId, String rel) {
		return linkTo(methodOn(RestaurantController.class)
				.activate(restaurantId)).withRel(rel);
	}
	
	public Link linkToRestaurantsInactivate(Long restaurantId, String rel) {
		return linkTo(methodOn(RestaurantController.class)
				.inactivate(restaurantId)).withRel(rel);
	}
	
	public Link linkToRestaurantPaymentMethods(Long restaurantId) {
		return linkToRestaurantPaymentMethods(restaurantId, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToRestaurantPaymentMethods(Long restaurantId, String rel) {
		return linkTo(methodOn(RestaurantPaymentMethodController.class)
				.list(restaurantId)).withRel(rel);
	}	
	
	public Link linkToRestaurantPaymentMethodsAssociate(Long restaurantId, Long paymentMethodId, String rel) {
		return linkTo(methodOn(RestaurantPaymentMethodController.class)
				.associate(restaurantId, paymentMethodId)).withRel(rel);
	}
	
	public Link linkToRestaurantPaymentMethodsDisassociate(Long restaurantId, Long paymentMethodId, String rel) {
		return linkTo(methodOn(RestaurantPaymentMethodController.class)
				.disassociate(restaurantId, paymentMethodId)).withRel(rel);
	}
	
	public Link linkToRestaurantsResponsible(Long restaurantId) {
		return linkToRestaurantsResponsible(restaurantId, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToRestaurantsResponsible(Long restaurantId, String rel) {
		return linkTo(methodOn(RestaurantResponsibleUserController.class)
				.list(restaurantId)).withRel(rel);
	}
	
	public Link linkToRestaurantsResponsibleAssociate(Long restaurantId, Long userId, String rel) {
		return linkTo(methodOn(RestaurantResponsibleUserController.class)
				.associate(restaurantId, userId)).withRel(rel);
	}
	
	public Link linkToRestaurantsResponsibleDisassociate(Long restaurantId, Long userId, String rel) {
		return linkTo(methodOn(RestaurantResponsibleUserController.class)
				.disassociate(restaurantId, userId)).withRel(rel);
	}
	
	public Link linkToClients(Long clientId) {
		return linkTo(methodOn(UserController.class)
				.find(clientId)).withSelfRel();
	}
	
	public Link linkToUsers() {
		return linkToUsers(IanaLinkRelations.SELF.value());
	}
	
	public Link linkToUsers(String rel) {
		return linkTo(UserController.class).withRel(rel);
	}

	public Link linkToUsers(Long userId, String rel) {
		return linkTo(methodOn(UserController.class)
				.find(userId)).withRel(rel);
	}
	
	public Link linkToUsers(Long userId) {
		return linkToUsers(userId, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToClustersUsers(Long userId, String rel) {
		return linkTo(methodOn(UserClusterController.class)
				.list(userId)).withRel(rel);
	}
	
	public Link linkToClustersUsersAssociate(Long userId, String rel) {
		return linkTo(methodOn(UserClusterController.class)
				.associate(userId, null)).withRel(rel);
	}
	
	public Link linkToClustersUsersDisassociate(Long userId, Long clusterId, String rel) {
		return linkTo(methodOn(UserClusterController.class)
				.disassociate(userId, clusterId)).withRel(rel);
	}
	
	public Link linkToPaymentMethods() {
		return linkToPaymentMethods(IanaLinkRelations.SELF.value());
	}
	
	public Link linkToFindPaymentMethods(Long paymentId) {
		return linkTo(methodOn(PaymentMethodsController.class)
				.find(paymentId)).withSelfRel();
	}
	
	public Link linkToDeletePaymentMethods(Long paymentId, String rel) {
		return linkTo(methodOn(PaymentMethodsController.class)
				.delete(paymentId)).withRel(rel);
	}
	
	public Link linkToPaymentMethods(String rel) {
		return linkTo(PaymentMethodsController.class).withRel(rel);
	}
	
	public Link linkToItems(Long restaurantId, Long productId, String rel) {
		return linkTo(methodOn(RestaurantProductsController.class)
				.find(restaurantId, productId))
				.withRel(rel);
	}
	
	public Link linkToProducts(Long restaurantId, String rel) {
		return linkTo(methodOn(RestaurantProductsController.class)
				.list(restaurantId, null)).withRel(rel);
	}
	
	public Link linkToProducts(Long restaurantId) {
		return linkToProducts(restaurantId, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToPhotoProduct(Long restaurantId, Long productId, String rel) {
		return linkTo(methodOn(RestaurantPhotoProductController.class)
				.find(restaurantId, productId)).withRel(rel);
	}
	
	public Link linkToPhotoProduct(Long restaurantId, Long productId) {
		return linkToPhotoProduct(restaurantId, productId, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToConfirmedOrder(String orderCode, String rel) {
		return linkTo(methodOn(OrderStatusController.class)
				.confirm(orderCode))
				.withRel(rel);
	}
	
	public Link linkToCancellationOrder(String orderCode, String rel) {
		return linkTo(methodOn(OrderStatusController.class)
				.cancellation(orderCode))
				.withRel(rel);
	}
	
	public Link linkToDeliveredOrder(String orderCode, String rel) {
		return linkTo(methodOn(OrderStatusController.class)
				.delivered(orderCode))
				.withRel(rel);
	}
	
	public Link linkToClusters() {
		return linkToClusters(IanaLinkRelations.SELF.value());
	}
	
	public Link linkToClusters(String rel) {
		return linkTo(ClusterController.class).withRel(rel);
	}
	
	public Link linkToClustersPermissions(Long clusterId, String rel) {
		return linkTo(methodOn(ClusterPermissionsController.class)
				.list(clusterId)).withRel(rel);
	}
	
	public Link linkToClustersPermissions(Long clusterId) {
		return linkToClustersPermissions(clusterId, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToClustersPermissionsAssociate(Long clusterId, String rel) {
		return linkTo(methodOn(ClusterPermissionsController.class)
				.associate(clusterId, null))
				.withRel(rel);
	}
	
	public Link linkToClustersPermissionsDisassociate(Long clusterId, Long permissionId, String rel) {
		return linkTo(methodOn(ClusterPermissionsController.class)
				.disassociate(clusterId, permissionId))
				.withRel(rel);
	}
	
	public Link linkToPermissions(String rel) {
		return linkTo(PermissionController.class).withRel(rel);
	}
	
	public Link linkToPermissions() {
		return linkToPermissions(IanaLinkRelations.SELF.value());
	}
	
	public Link linkToStatistics(String rel) {
		return linkTo(StatisticsController.class).withRel(rel);
	}
}
