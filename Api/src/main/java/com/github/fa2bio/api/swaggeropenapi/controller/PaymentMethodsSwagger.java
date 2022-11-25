package com.github.fa2bio.api.swaggeropenapi.controller;

import java.util.List;

import com.github.fa2bio.api.exceptionhandler.Problem;
import com.github.fa2bio.api.model.PaymentMethodModel;
import com.github.fa2bio.api.model.input.PaymentMethodInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Payment Methods")
public interface PaymentMethodsSwagger {

	@ApiOperation("List of payment method")
	List<PaymentMethodModel> list();
	
	@ApiOperation("Search a payment method by Id")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Invalid payment method Id", response = Problem.class),
		@ApiResponse(code = 404, message = "Payment method not found", response = Problem.class)
	})
	PaymentMethodModel find(
			@ApiParam(value = "Payment method Id", example = "1", required = true)
			Long paymentMethodId);
	
	@ApiOperation("Register a payment method")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Payment method registered"),
	})
	PaymentMethodModel register(
			@ApiParam(name = "Body", value = "Representation of a new payment method", required = true)
			PaymentMethodInput paymentMethodInput);
	
	@ApiOperation("Update a payment method by Id")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Payment method updated"),
		@ApiResponse(code = 404, message = "Payment method not found", response = Problem.class)
	})
	PaymentMethodModel update(
			@ApiParam(value = "Payment method Id", example = "1", required = true) 
			Long paymentMethodId, 
			@ApiParam(name = "Body", value = "Representation of a new payment method with the new data", required = true)
			PaymentMethodInput paymentMethodInput);
	
	@ApiOperation("Delete a payment method by Id")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Payment method deleted"),
		@ApiResponse(code = 404, message = "Payment method not found", response = Problem.class)
	})
	void delete(
			@ApiParam(value = "Payment method Id", example = "1", required = true)
			Long paymentMethodId);
}
