package com.github.fa2bio.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.controller.RestaurantController;
import com.github.fa2bio.api.model.RestaurantNameOnlyModel;
import com.github.fa2bio.core.hypermedia.DeliveryLinks;
import com.github.fa2bio.domain.model.Restaurant;

@Component
public class RestaurantNameOnlyAssembler 
	extends RepresentationModelAssemblerSupport<Restaurant, RestaurantNameOnlyModel>{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private DeliveryLinks deliveryLinks;
	
	public RestaurantNameOnlyAssembler() {
		super(RestaurantController.class, RestaurantNameOnlyModel.class);
	}

	@Override
	public RestaurantNameOnlyModel toModel(Restaurant restaurant) {
		RestaurantNameOnlyModel restaurantModel = createModelWithId(restaurant.getId(), restaurant);
		modelMapper.map(restaurant, restaurantModel);
		restaurantModel.add(deliveryLinks.linkToRestaurants("restaurants"));
		return restaurantModel;
	}
	
	@Override
	public CollectionModel<RestaurantNameOnlyModel> toCollectionModel(Iterable<? extends Restaurant> entities){
		return super.toCollectionModel(entities)
				.add(deliveryLinks.linkToRestaurants());
	}
}
