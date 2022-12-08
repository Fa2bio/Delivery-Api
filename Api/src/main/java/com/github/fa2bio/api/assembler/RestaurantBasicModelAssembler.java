package com.github.fa2bio.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.controller.RestaurantController;
import com.github.fa2bio.api.model.RestaurantBasicModel;
import com.github.fa2bio.core.hypermedia.DeliveryLinks;
import com.github.fa2bio.domain.model.Restaurant;

@Component
public class RestaurantBasicModelAssembler 
	extends RepresentationModelAssemblerSupport<Restaurant, RestaurantBasicModel>{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private DeliveryLinks deliveryLinks;
	
	public RestaurantBasicModelAssembler() {
		super(RestaurantController.class, RestaurantBasicModel.class);
	}

	@Override
	public RestaurantBasicModel toModel(Restaurant restaurant) {
		RestaurantBasicModel restaurantModel = createModelWithId(restaurant.getId(), restaurant);
		
		modelMapper.map(restaurant, restaurantModel);
		
		restaurantModel.add(deliveryLinks.linkToRestaurants("restaurants"));
		
		restaurantModel.getKitchen().add(
			deliveryLinks.linkToKitchens(restaurant.getKitchen().getId()));
		
		return restaurantModel;
	}
	
	@Override
	public CollectionModel<RestaurantBasicModel> toCollectionModel(Iterable<? extends Restaurant> entities) {
		return super.toCollectionModel(entities)
				.add(deliveryLinks.linkToRestaurants());
	}
}
