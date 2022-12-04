package com.github.fa2bio.api.swaggeropenapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.github.fa2bio.api.exceptionhandler.Problem;
import com.github.fa2bio.api.model.StateModel;
import com.github.fa2bio.api.model.input.StateInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "States")
public interface StateControllerSwagger {

	@ApiOperation("List of states")
	CollectionModel<StateModel> list();
	
	@ApiOperation("Search a state by Id")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Invalid state Id", response = Problem.class),
		@ApiResponse(code = 404, message = "state not found", response = Problem.class)
	})
	StateModel find(
			@ApiParam(value = "State Id", example = "1", required = true)
			Long stateId);
	
	@ApiOperation("Register a state")
	@ApiResponses({
		@ApiResponse(code = 201, message = "State registered"),
	})
	StateModel register(
			@ApiParam(name = "Body", value = "Representation of a new state", required = true)
			StateInput stateInput);
	
	@ApiOperation("Update a state by Id")
	@ApiResponses({
		@ApiResponse(code = 200, message = "State updated"),
		@ApiResponse(code = 404, message = "State not found", response = Problem.class)
	})
	StateModel update(
			@ApiParam(value = "State Id", example = "1", required = true) 
			Long stateId, 
			@ApiParam(name = "Body", value = "Representation of a new state with the new data", required = true)
			StateInput stateInput);
	
	@ApiOperation("Delete a state by Id")
	@ApiResponses({
		@ApiResponse(code = 204, message = "State deleted"),
		@ApiResponse(code = 404, message = "State not found", response = Problem.class)
	})
	void delete(
			@ApiParam(value = "State Id", example = "1", required = true)
			Long stateId);
	
}
