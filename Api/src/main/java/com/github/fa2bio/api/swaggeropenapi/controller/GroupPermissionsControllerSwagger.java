package com.github.fa2bio.api.swaggeropenapi.controller;

import java.util.List;

import com.github.fa2bio.api.exceptionhandler.Problem;
import com.github.fa2bio.api.model.PermissionModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Groups - Permissions")
public interface GroupPermissionsControllerSwagger {

	@ApiOperation("List of permissions associated with a group")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Invalid group Id", response = Problem.class),
		@ApiResponse(code = 404, message = "Group not found", response = Problem.class)
	})
	List<PermissionModel> list(
			@ApiParam(value = "Group Id", example = "1", required = true)
			Long groupId);
	
	@ApiOperation("Permission association with group")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Association performed successfully"),
		@ApiResponse(code = 404, message = "Group or permission not found", response = Problem.class)
	})
	void associate(
			@ApiParam(value = "Group Id", example = "1", required = true) 
			Long groupId, 
			@ApiParam(value = "Permission Id", example = "1", required = true) 
			Long permissionId);
	
	@ApiOperation("Disassociate a permission from a group")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Disassociation performed successfully"),
		@ApiResponse(code = 404, message = "\"Group or permission not found", response = Problem.class)
	})
	void disassociate(
			@ApiParam(value = "Group Id", example = "1", required = true) 
			Long groupId, 
			@ApiParam(value = "Permission Id", example = "1", required = true) 
			Long permissionId);
	
}
