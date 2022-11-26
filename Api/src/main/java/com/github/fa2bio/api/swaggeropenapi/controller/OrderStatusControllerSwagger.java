package com.github.fa2bio.api.swaggeropenapi.controller;

import com.github.fa2bio.api.exceptionhandler.Problem;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Orders - Status")
public interface OrderStatusControllerSwagger {

	@ApiOperation("Order confirm")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Order confirmed successfully"),
		@ApiResponse(code = 404, message = "Order not found", response = Problem.class)
	})
	void confirm(
			@ApiParam(value = "Order Code", example = "5a76ddc6-fc45-468d-8b8d-bc8bef3462b4", 
			required = true)
			String orderCode);
	
	@ApiOperation("Order canceled")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Order canceled successfully"),
		@ApiResponse(code = 404, message = "Order not found", response = Problem.class)
	})
	void cancellation(
			@ApiParam(value = "Order Code", example = "5a76ddc6-fc45-468d-8b8d-bc8bef3462b4", 
			required = true)
			String orderCode);
	
	@ApiOperation("Order delivered")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Order delivered successfully"),
		@ApiResponse(code = 404, message = "Order not found", response = Problem.class)
	})
	void delivered(
			@ApiParam(value = "Order Code", example = "5a76ddc6-fc45-468d-8b8d-bc8bef3462b4", 
			required = true)
			String orderCode);
}
