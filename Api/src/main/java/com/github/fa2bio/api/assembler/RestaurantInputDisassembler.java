package com.github.fa2bio.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.model.input.RestaurantInput;
import com.github.fa2bio.domain.model.City;
import com.github.fa2bio.domain.model.Kitchen;
import com.github.fa2bio.domain.model.Restaurant;

@Component
public class RestaurantInputDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Restaurant toDomainObject(RestaurantInput restaurantInput) {
		return modelMapper.map(restaurantInput, Restaurant.class);
	}
	
	public void copyToDomainObject(RestaurantInput restaurantInput, Restaurant restaurant) {
		//To avoid org.hibernate.HibernateException: identifier of an instance
		//of com.github.fa2bio.domain.model.Cozinha was altered from 1 to 2
		restaurant.setKitchen(new Kitchen());
		
		if(restaurant.getAddress() != null) {
			restaurant.getAddress().setCity(new City());
		}
		
		modelMapper.map(restaurantInput, restaurant);
	}
}
