package com.github.fa2bio.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.controller.RestaurantController;
import com.github.fa2bio.api.model.RestaurantModel;
import com.github.fa2bio.core.hypermedia.DeliveryLinks;
import com.github.fa2bio.domain.model.Restaurant;

@Component
public class RestaurantModelAssembler 
	extends RepresentationModelAssemblerSupport<Restaurant, RestaurantModel>{
	 
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private DeliveryLinks deliveryLinks;
	
	public RestaurantModelAssembler() {
		super(RestaurantController.class, RestaurantModel.class);
	}
	
	@Override
	public RestaurantModel toModel(Restaurant restaurant) {
		RestaurantModel restaurantModel = createModelWithId(restaurant.getId(), restaurant);
		modelMapper.map(restaurant, restaurantModel);
		
		restaurantModel.add(deliveryLinks.linkToRestaurants("restaurants"));
		
		restaurantModel.getKitchen().add(
				deliveryLinks.linkToKitchens(restaurant.getKitchen().getId()));
		
		restaurantModel.getAddress().getCity().add(
				deliveryLinks.linkToCities(restaurant.getAddress().getCity().getId()));
		
		restaurantModel.add(deliveryLinks.linkToRestaurantPaymentMethods(restaurant.getId(), "payment-methods"));
		
		restaurantModel.add(deliveryLinks.linkToRestaurantsResponsible(restaurant.getId(), "responsible"));
		
		return restaurantModel;
	}
	
	@Override
	public CollectionModel<RestaurantModel> toCollectionModel(Iterable<? extends Restaurant> entities) {
		return super.toCollectionModel(entities)
				.add(deliveryLinks.linkToRestaurants());
	}
}
