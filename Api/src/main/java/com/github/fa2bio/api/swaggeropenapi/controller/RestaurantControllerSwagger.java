package com.github.fa2bio.api.swaggeropenapi.controller;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.github.fa2bio.api.exceptionhandler.Problem;
import com.github.fa2bio.api.model.RestaurantBasicModel;
import com.github.fa2bio.api.model.RestaurantModel;
import com.github.fa2bio.api.model.RestaurantNameOnlyModel;
import com.github.fa2bio.api.model.input.RestaurantInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Restaurants")
public interface RestaurantControllerSwagger {

	@ApiOperation("List of restaurants")
	CollectionModel<RestaurantBasicModel> list();
	
	@ApiOperation("List of restaurant names")
	CollectionModel<RestaurantNameOnlyModel> listOnlyName();
	
	@ApiOperation("Search a restaurant by Id")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Invalid restaurant Id", response = Problem.class),
		@ApiResponse(code = 404, message = "Restaurant not found", response = Problem.class)
	})
	RestaurantModel find(
			@ApiParam(value = "Restaurant Id", example = "1", required = true)
			Long restaurantId);
	
	@ApiOperation("Register a restaurant")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Restaurant registered"),
	})
	RestaurantModel register(
			@ApiParam(name = "Body", value = "Representation of a new restaurant", required = true)
			RestaurantInput restaurantInput);
	
	@ApiOperation("Update a restaurant by Id")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Restaurant updated"),
		@ApiResponse(code = 404, message = "Restaurant not found", response = Problem.class)
	})
	RestaurantModel update(
			@ApiParam(value = "Restaurant Id", example = "1", required = true) 
			Long restaurantId,
			@ApiParam(name = "Body", value = "Representation of a new restaurant with the new data", required = true)
			RestaurantInput restaurantInput);
	
	@ApiOperation("Activate restaurant by Id")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Restaurant activated"),
		@ApiResponse(code = 404, message = "Restaurant not found", response = Problem.class)
	})
	ResponseEntity<Void> activate(
			@ApiParam(value = "Restaurant Id", example = "1", required = true) 
			Long restaurantId);
	
	@ApiOperation("Activate multiples restaurant by Id")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Restaurants activated"),
		@ApiResponse(code = 404, message = "Restaurant not found", response = Problem.class)
	})
	ResponseEntity<Void> activateMultiples(
			@ApiParam(name = "Body", value = "Restaurant Ids", required = true)
			List<Long> restaurantIds);
		
	@ApiOperation("Inactivate restaurant by Id")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Restaurant inactivate"),
		@ApiResponse(code = 404, message = "Restaurant not found", response = Problem.class)
	})
	ResponseEntity<Void> inactivate(
			@ApiParam(value = "Restaurant Ids", example = "1", required = true) 
			Long restaurantId);
	
	@ApiOperation("Inactivate multiples restaurant by Id")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Restaurants inactivate"),
		@ApiResponse(code = 404, message = "Restaurant not found", response = Problem.class)
	})
	ResponseEntity<Void> inactivateMultiples(
			@ApiParam(name = "Body", value = "Restaurant Ids", required = true)
			List<Long> restaurantIds);
	
	@ApiOperation("Open a restaurant by Id")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Successfully opened restaurant"),
		@ApiResponse(code = 404, message = "Restaurant not found", response = Problem.class)
	})
	ResponseEntity<Void> open(
			@ApiParam(value = "Restaurant Id", example = "1", required = true)
			Long restaurantId);
	
	@ApiOperation("Close a restaurant by ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Successfully closed restaurant"),
		@ApiResponse(code = 404, message = "Restaurant not found", response = Problem.class)
	})
	ResponseEntity<Void> close(
			@ApiParam(value = "Restaurant Id", example = "1", required = true)
			Long restaurantId);
	
	
}
