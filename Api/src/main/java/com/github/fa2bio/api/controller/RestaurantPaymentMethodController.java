package com.github.fa2bio.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.fa2bio.api.assembler.PaymentMethodModelAssembler;
import com.github.fa2bio.api.model.PaymentMethodModel;
import com.github.fa2bio.api.swaggeropenapi.controller.RestaurantPaymentMethodControllerSwagger;
import com.github.fa2bio.core.hypermedia.DeliveryLinks;
import com.github.fa2bio.domain.model.Restaurant;
import com.github.fa2bio.domain.service.RestaurantService;

@RestController
@RequestMapping(value = "/restaurants/{restaurantId}/payment-methods")
public class RestaurantPaymentMethodController implements RestaurantPaymentMethodControllerSwagger{

	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private PaymentMethodModelAssembler paymentMethodModelAssembler;
	
	@Autowired
	private DeliveryLinks deliveryLinks;

	@Override
	@GetMapping
	public CollectionModel<PaymentMethodModel> list(@PathVariable Long restaurantId) {
		Restaurant restaurant = restaurantService.fetchOrFail(restaurantId);
		
		CollectionModel<PaymentMethodModel> paymentMethodsModel = paymentMethodModelAssembler
				.toCollectionModel(restaurant.getPaymentMethods())
				.removeLinks()
				.add(deliveryLinks.linkToRestaurantPaymentMethods(restaurantId));
		
		paymentMethodsModel.getContent().forEach(paymentMethodModel -> {
			paymentMethodModel.add(deliveryLinks.linkToRestaurantPaymentMethodsAssociate(
					restaurantId, paymentMethodModel.getId(), "associate"));
		});
		
		paymentMethodsModel.getContent().forEach(paymentMethodModel -> {
			paymentMethodModel.add(deliveryLinks.linkToRestaurantPaymentMethodsDisassociate(
					restaurantId, paymentMethodModel.getId(), "disassociate"));
		});
		
		return paymentMethodsModel;
	}
	
	@Override
	@PutMapping("/{paymentMethodId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associate(@PathVariable Long restaurantId, @PathVariable Long paymentMethodId) {
		restaurantService.associatePaymentMethod(restaurantId, paymentMethodId);
		return ResponseEntity.noContent().build();
	}
	
	@Override
	@DeleteMapping("/{paymentMethodId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void>disassociate(@PathVariable Long restaurantId, @PathVariable Long paymentMethodId) {
		restaurantService.disassociatePaymentMethod(restaurantId, paymentMethodId);
		return ResponseEntity.noContent().build();
	}
}
