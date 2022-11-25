package com.github.fa2bio.api.swaggeropenapi.controller;

import java.util.List;

import com.github.fa2bio.api.exceptionhandler.Problem;
import com.github.fa2bio.api.model.CityModel;
import com.github.fa2bio.api.model.input.CityInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cities")
public interface CityControllerSwagger {

	@ApiOperation("List the cities")
	List<CityModel> list();
	
	@ApiOperation("Search a city by Id")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Invalid city Id", response = Problem.class),
		@ApiResponse(code = 404, message = "City not found", response = Problem.class)
	})
	CityModel find(
			@ApiParam(value = "City Id", example = "1", required = true)
			Long cityId);
	
	@ApiOperation("Register a city")
	@ApiResponses({
		@ApiResponse(code = 201, message = "City registered"),
	})
	CityModel register(
			@ApiParam(name = "Body", value = "Representation of a new city", required = true)
			CityInput cityInput);
	
	@ApiOperation("Update a city by Id")
	@ApiResponses({
		@ApiResponse(code = 200, message = "City updated"),
		@ApiResponse(code = 404, message = "City not found", response = Problem.class)
	})
	CityModel update(
			@ApiParam(value = "City Id", example = "1", required = true) 
			Long cityId, 
			@ApiParam(name = "Body", value = "Representation of a new city with the new data", required = true)
			CityInput cityInput);
	
	@ApiOperation("Delete a city by Id")
	@ApiResponses({
		@ApiResponse(code = 204, message = "City deleted"),
		@ApiResponse(code = 404, message = "City not found", response = Problem.class)
	})
	void delete(
			@ApiParam(value = "City Id", example = "1", required = true)
			Long cityId);
}
