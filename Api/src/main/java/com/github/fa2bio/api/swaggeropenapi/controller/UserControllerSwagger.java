package com.github.fa2bio.api.swaggeropenapi.controller;

import java.util.List;

import com.github.fa2bio.api.exceptionhandler.Problem;
import com.github.fa2bio.api.model.UserModel;
import com.github.fa2bio.api.model.input.PasswordInput;
import com.github.fa2bio.api.model.input.UserInput;
import com.github.fa2bio.api.model.input.UserInputWithPassword;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Users")
public interface UserControllerSwagger {

	@ApiOperation("List of users")
	List<UserModel> list();
	
	@ApiOperation("Search a user by Id")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Invalid user Id", response = Problem.class),
		@ApiResponse(code = 404, message = "User not found", response = Problem.class)
	})
	UserModel find(
			@ApiParam(value = "User Id", example = "1", required = true)
			Long userId);
	
	@ApiOperation("Register a user")
	@ApiResponses({
		@ApiResponse(code = 201, message = "User registered"),
	})
	UserModel register(
			@ApiParam(name = "Body", value = "Representation of a new user with password", required = true)
			UserInputWithPassword usuarioInput);
	
	@ApiOperation("Update a user by Id")
	@ApiResponses({
		@ApiResponse(code = 200, message = "User updated"),
		@ApiResponse(code = 404, message = "User not found", response = Problem.class)
	})
	UserModel update(
			@ApiParam(value = "User Id", example = "1", required = true) 
			Long userId, 
			@ApiParam(name = "Body", value = "Representation of a new user with the new data", required = true)
			UserInput userInput);
	
	
	void updatePassword(
			@ApiParam(value = "User Id", example = "1", required = true) 
			Long userId, 
			@ApiParam(name = "Body", value = "Representation of a new password with the new data", required = true)
			PasswordInput passwordInput);
	
	@ApiOperation("Delete a user by Id")
	@ApiResponses({
		@ApiResponse(code = 204, message = "User deleted"),
		@ApiResponse(code = 404, message = "User not found", response = Problem.class)
	})
	void delete(
			@ApiParam(value = "User Id", example = "1", required = true)
			Long userId);
}
