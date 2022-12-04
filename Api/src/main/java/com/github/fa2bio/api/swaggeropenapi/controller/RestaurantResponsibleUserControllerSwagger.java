package com.github.fa2bio.api.swaggeropenapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.github.fa2bio.api.exceptionhandler.Problem;
import com.github.fa2bio.api.model.UserModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Restaurants - Responsible Users")
public interface RestaurantResponsibleUserControllerSwagger {

	@ApiOperation("List of responsible Users associated with a restaurant")
	@ApiResponses({
		@ApiResponse(code = 404, message = "Restaurant not found", response = Problem.class)
	})
	CollectionModel<UserModel> list(
			@ApiParam(value = "Restaurant Id", example = "1", required = true)
			Long restaurantId);
	
	@ApiOperation("Permission association with group")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Association performed successfully"),
		@ApiResponse(code = 404, message = "Restaurant or user not found", response = Problem.class)
	})
	void associate(
			@ApiParam(value = "Restaurant Id", example = "1", required = true)
			Long restaurantId, 
			@ApiParam(value = "User Id", example = "1", required = true)
			Long userId);
	
	@ApiOperation("Disassociate a permission from a group")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Disassociation performed successfully"),
		@ApiResponse(code = 404, message = "Restaurant or user not found", response = Problem.class)
	})
	void disassociate(
			@ApiParam(value = "Restaurant Id", example = "1", required = true)
			Long restaurantId, 
			@ApiParam(value = "User Id", example = "1", required = true)
			Long userId);
}
