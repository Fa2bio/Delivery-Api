package com.github.fa2bio.api.swaggeropenapi.controller;

import java.util.List;

import com.github.fa2bio.api.exceptionhandler.Problem;
import com.github.fa2bio.api.model.GroupModel;
import com.github.fa2bio.api.model.input.GroupInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Groups")
public interface GroupControllerSwagger {

	@ApiOperation("List of groups")
	List<GroupModel> list();
	
	@ApiOperation("Search a group by Id")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Invalid group Id", response = Problem.class),
		@ApiResponse(code = 404, message = "Group not found", response = Problem.class)
	})
	GroupModel find(
			@ApiParam(value = "Group Id", example = "1", required = true)
			Long groupId);
	
	@ApiOperation("Register a group")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Group registered"),
	})
	GroupModel register(
			@ApiParam(name = "Body", value = "Representation of a new city", required = true)
			GroupInput groupInput);
	
	@ApiOperation("Update a group by Id")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Group updated"),
		@ApiResponse(code = 404, message = "Group not found", response = Problem.class)
	})
	GroupModel update(
			@ApiParam(value = "Group Id", example = "1", required = true) 
			Long groupId, 
			@ApiParam(name = "Body", value = "Representation of a new group with the new data", required = true)
			GroupInput groupInput);
	
	@ApiOperation("Delete a group by Id")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Group deleted"),
		@ApiResponse(code = 404, message = "Group not found", response = Problem.class)
	})
	void delete(
			@ApiParam(value = "Group Id", example = "1", required = true)
			Long groupId);
}
