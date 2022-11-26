package com.github.fa2bio.api.swaggeropenapi.controller;

import java.util.List;

import com.github.fa2bio.api.exceptionhandler.Problem;
import com.github.fa2bio.api.model.GroupModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Users - Groups")
public interface UserGroupControllerSwagger {

	@ApiOperation("List of users associated with a group")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Invalid user Id", response = Problem.class),
		@ApiResponse(code = 404, message = "User not found", response = Problem.class)
	})
	List<GroupModel> list(
			@ApiParam(value = "User Id", example = "1", required = true)
			Long userId);
	
	@ApiOperation("User association with group")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Association performed successfully"),
		@ApiResponse(code = 404, message = "User or group not found", response = Problem.class)
	})
	void associate(
			@ApiParam(value = "User Id", example = "1", required = true)
			Long userId, 
			@ApiParam(value = "Group Id", example = "1", required = true) 
			Long groupId);
	
	@ApiOperation("User association with group")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Disassociation performed successfully"),
		@ApiResponse(code = 404, message = "User or group not found", response = Problem.class)
	})
	void disassociate(
			@ApiParam(value = "User Id", example = "1", required = true)
			Long userId, 
			@ApiParam(value = "Group Id", example = "1", required = true) 
			Long groupId);
}
