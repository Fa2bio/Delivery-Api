package com.github.fa2bio.api.swaggeropenapi.controller;

import java.io.IOException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

import com.github.fa2bio.api.exceptionhandler.Problem;
import com.github.fa2bio.api.model.PhotoProductModel;
import com.github.fa2bio.api.model.input.PhotoProductInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Restaurants - Photo Product")
public interface RestaurantPhotoProductControllerSwagger {

	@ApiOperation(value = "Search for a product photo of a restaurant",
			produces = "application/json, image/jpeg, image/png")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Invalid restaurant or product ID", response = Problem.class),
		@ApiResponse(code = 404, message = "Product photo not found", response = Problem.class)
	})
	PhotoProductModel find(
			@ApiParam(value = "Restaurant Id", example = "1", required = true)
			Long restaurantId, 
			@ApiParam(value = "Product Id", example = "1", required = true)
			Long productId);
	
	@ApiOperation(value = "Search for a product photo of a restaurant", hidden = true)
	ResponseEntity<InputStreamResource> recoverPhoto(Long restaurantId, Long productId, String acceptHeader) throws HttpMediaTypeNotAcceptableException;
	
	@ApiOperation("Register and update a restaurant's product photo")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Updated product photo"),
		@ApiResponse(code = 404, message = "Restaurant product not found", response = Problem.class)
	})
	PhotoProductModel registerPhoto(
			@ApiParam(value = "Restaurant Id", example = "1", required = true)
			Long restaurantId, 
			@ApiParam(value = "Product Id", example = "1", required = true)
			Long productId, 
			@ApiParam(value = "Product photo file (maximum 500KB, JPG and PNG only)",
			required = true)
			PhotoProductInput photoProductInput) throws IOException;
	
	@ApiOperation("Deletes a product photo from a restaurant")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Product photo deleted"),
		@ApiResponse(code = 400, message = "Invalid restaurant or product ID", response = Problem.class),
		@ApiResponse(code = 404, message = "Product photo not found", response = Problem.class)
	})
	void delete(
			@ApiParam(value = "Restaurant Id", example = "1", required = true)
			Long restaurantId, 
			@ApiParam(value = "Product Id", example = "1", required = true)
			Long productId);
}
