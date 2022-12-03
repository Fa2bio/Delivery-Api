package com.github.fa2bio.domain.exception;

public class PhotoProductNotFoundException extends EntityNotFoundException{

	private static final long serialVersionUID = 1L;
	
	public PhotoProductNotFoundException(String message) {
		super(message);
	}

	public PhotoProductNotFoundException(Long restaurantId, Long productId) {
		this(String.format("There is no record of product photo with code %d for the restaurant with code %d"
				,productId ,restaurantId));
	}
}
