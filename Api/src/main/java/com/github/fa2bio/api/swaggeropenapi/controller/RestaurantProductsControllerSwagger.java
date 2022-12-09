package com.github.fa2bio.api.swaggeropenapi.controller;

import java.util.List;

import com.github.fa2bio.api.exceptionhandler.Problem;
import com.github.fa2bio.api.model.ProductModel;
import com.github.fa2bio.api.model.input.ProductInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Restaurants - Products")
public interface RestaurantProductsControllerSwagger {

	@ApiOperation("List of products associated with a restaurant")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Invalid restaurant Id", response = Problem.class),
		@ApiResponse(code = 404, message = "Restaurant not found", response = Problem.class)
	})
	List<ProductModel> list(
			@ApiParam(value = "Restaurant Id", example = "1", required = true)
			Long restaurantId, 
			@ApiParam(value = "Indica se deve ou não incluir produtos inativos no resultado da listagem", 
			example = "false", defaultValue = "false")
			boolean includeInactive);
	
	@ApiOperation("Search for a product from a restaurant")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Invalid restaurant or product Id", response = Problem.class),
		@ApiResponse(code = 404, message = "Product of restaurant not found", response = Problem.class)
	})
	ProductModel find(
			@ApiParam(value = "Restaurant Id", example = "1", required = true)
			Long restaurantId, 
			@ApiParam(value = "Product Id", example = "1", required = true)
			Long productId);
	
	@ApiOperation("Register a product associated with a restaurant")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Restaurant registered"),
		@ApiResponse(code = 404, message = "Product of restaurant not found", response = Problem.class)
	})
	ProductModel register(
			@ApiParam(value = "Restaurant Id", example = "1", required = true)
			Long restaurantId, 
			@ApiParam(name = "Body", value = "Representation of a new product", required = true)
			ProductInput productInput);
	
	@ApiOperation("Update a product associated with a restaurant")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Product updated"),
		@ApiResponse(code = 404, message = "Product of restaurant not found", response = Problem.class)
	})
	ProductModel update(
			@ApiParam(value = "Restaurant Id", example = "1", required = true)
			Long restaurantId, 
			@ApiParam(value = "Product Id", example = "1", required = true)
			Long productId, 
			@ApiParam(name = "Body", value = "Representation of a new product", required = true)
			ProductInput productInput);
}
