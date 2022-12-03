package com.github.fa2bio.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import com.github.fa2bio.domain.model.Restaurant;

public interface RestaurantRepositoryQueries {

	List<Restaurant> find(String name, 
			BigDecimal initialRateShipping, BigDecimal finalRateShipping);
	
	List<Restaurant> findWithFreeShipping(String name);

}