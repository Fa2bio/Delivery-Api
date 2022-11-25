package com.github.fa2bio.api.swaggeropenapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.github.fa2bio.api.exceptionhandler.Problem;
import com.github.fa2bio.api.model.KitchenModel;
import com.github.fa2bio.api.model.input.KitchenInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Kitchens")
public interface KitchenControllerSwagger {

	@ApiOperation("List of kitchens with pegeable")
	Page<KitchenModel> list(Pageable pegeable);
	
	@ApiOperation("Search a kitchen by Id")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Invalid kitchen Id", response = Problem.class),
		@ApiResponse(code = 404, message = "Kitchen not found", response = Problem.class)
	})
	KitchenModel find(
			@ApiParam(value = "Kitchen Id", example = "1", required = true)
			Long kitchenId);
	
	@ApiOperation("Register a kitchen")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Kitchen registered"),
	})
	KitchenModel register(
			@ApiParam(name = "Body", value = "Representation of a new kitchen", required = true)
			KitchenInput kitchenInput);
	
	
	@ApiOperation("Update a kitchen by Id")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Kitchen updated"),
		@ApiResponse(code = 404, message = "Kitchen not found", response = Problem.class)
	})
	KitchenModel update(
			@ApiParam(value = "Kitchen Id", example = "1", required = true) 
			Long kitchenId, 
			@ApiParam(name = "Body", value = "Representation of a new kitchen with the new data", required = true)
			KitchenInput kitchenInput);
	
	@ApiOperation("Delete a kitchen by Id")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Kitchen deleted"),
		@ApiResponse(code = 404, message = "Kitchen not found", response = Problem.class)
	})
	void delete(
			@ApiParam(value = "kitchen Id", example = "1", required = true)
			Long kitchenId);
}
