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

import com.github.fa2bio.api.assembler.UserModelAssembler;
import com.github.fa2bio.api.model.UserModel;
import com.github.fa2bio.api.swaggeropenapi.controller.RestaurantResponsibleUserControllerSwagger;
import com.github.fa2bio.core.hypermedia.DeliveryLinks;
import com.github.fa2bio.domain.model.Restaurant;
import com.github.fa2bio.domain.service.RestaurantService;

@RestController
@RequestMapping("/restaurants/{restaurantId}")
public class RestaurantResponsibleUserController implements RestaurantResponsibleUserControllerSwagger{

	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private UserModelAssembler userModelAssembler;
	
	@Autowired
	private DeliveryLinks deliveryLinks;
	
	@Override
	@GetMapping("/responsible")
	public CollectionModel<UserModel> list(@PathVariable Long restaurantId){
		Restaurant restaurant = restaurantService.fetchOrFail(restaurantId);
		
		CollectionModel<UserModel> usersModel = userModelAssembler.toCollectionModel(restaurant.getResponsibles())
				.removeLinks()
				.add(deliveryLinks.linkToRestaurantsResponsible(restaurantId));
		
		usersModel.getContent().stream().forEach(userModel -> {
			userModel.add(deliveryLinks.linkToRestaurantsResponsibleAssociate(restaurantId, userModel.getId(), "associate"));
			userModel.add(deliveryLinks.linkToRestaurantsResponsibleDisassociate(restaurantId, userModel.getId(), "disassociate"));
		});
		
		return usersModel;
	}
	
	@Override
	@PutMapping("/responsible/{userId}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Void> associate(@PathVariable Long restaurantId, @PathVariable Long userId) {
		restaurantService.associateUser(restaurantId, userId);
		return ResponseEntity.noContent().build();
	}
	
	@Override
	@DeleteMapping("/responsible/{userId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> disassociate(@PathVariable Long restaurantId, @PathVariable Long userId) {
		restaurantService.disassociateUser(restaurantId, userId);
		return ResponseEntity.noContent().build();
	}
}
