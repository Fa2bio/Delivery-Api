package com.github.fa2bio.infrastructure.repository.spec;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.github.fa2bio.domain.model.Restaurant;

public class RestaurantSpecs {

	public static Specification<Restaurant> comFreeShipping() {
		return (root, query, builder) -> 
			builder.equal(root.get("rateShipping"), BigDecimal.ZERO);
	}
	
	public static Specification<Restaurant> withSimilarName(String name) {
		return (root, query, builder) ->
			builder.like(root.get("name"), "%" + name + "%");
	}
	
}
