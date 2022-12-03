package com.github.fa2bio.domain.exception;

public class ProductNotFoundException extends EntityNotFoundException{

	private static final long serialVersionUID = 1L;

	public ProductNotFoundException(String message) {
		super(message);
	}
	
	public ProductNotFoundException(Long productId) {
		this(String.format("There is no product record with code %d",
				productId));
	}
	
	public ProductNotFoundException(Long restaurantId, Long productId) {
		this(String.format("There is no product record with code %d for restaurant code %d", 
				productId, restaurantId));
	}

}
