package com.github.fa2bio.api.swaggeropenapi.controller;

import java.util.List;

import com.github.fa2bio.api.exceptionhandler.Problem;
import com.github.fa2bio.api.model.PaymentMethodModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Restaurants - Payment Methods")
public interface RestaurantPaymentMethodControllerSwagger {

	@ApiOperation("List of payment methods associated with a restaurant")
	@ApiResponses({
		@ApiResponse(code = 404, message = "Restaurant not found", response = Problem.class)
	})
	List<PaymentMethodModel> list(
			@ApiParam(value = "Restaurant Id", example = "1", required = true)
			Long restaurantId);
	
	@ApiOperation("Payment method association with restaurant")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Association performed successfully"),
		@ApiResponse(code = 404, message = "Restaurant or payment method not found", response = Problem.class)
	})
	void associate(
			@ApiParam(value = "Restaurant Id", example = "1", required = true)
			Long restaurantId, 
			@ApiParam(value = "Payment method Id", example = "1", required = true)
			Long paymentMethodId);
	
	@ApiOperation("Payment method disassociate with restaurant")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Disassociate performed successfully"),
		@ApiResponse(code = 404, message = "Restaurant or payment method not found", response = Problem.class)
	})
	void disassociate(
			@ApiParam(value = "Restaurant Id", example = "1", required = true)
			Long restaurantId,
			@ApiParam(value = "Payment method Id", example = "1", required = true)
			Long paymentMethodId);
}
