package com.github.fa2bio.api.swaggeropenapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.github.fa2bio.api.exceptionhandler.Problem;
import com.github.fa2bio.api.model.OrderrAbstractModel;
import com.github.fa2bio.api.model.OrderrModel;
import com.github.fa2bio.api.model.input.OrrderInput;
import com.github.fa2bio.domain.filter.OrderFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Orders")
public interface OrderControllerSwagger {

	@ApiOperation("Search orders")
	@ApiImplicitParams({
		@ApiImplicitParam(value = "Names of properties to filter on the response, separated by a comma",
				name = "fields", paramType = "query", type = "string")
	})
	PagedModel<OrderrAbstractModel> findWithPageable(Pageable pageable, OrderFilter filter);
	
	@ApiOperation("Search a order by UUID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Invalid order UUID", response = Problem.class),
		@ApiResponse(code = 404, message = "Order not found", response = Problem.class)
	})
	OrderrModel find(
			@ApiParam(value = "Order UUID", example = "5a76ddc6-fc45-468d-8b8d-bc8bef3462b4", required = true)
			String orderCode);
	
	@ApiOperation("Register a order")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Order registered"),
	})
	OrderrModel register(
			@ApiParam(name = "Body", value = "Representation of a new order", required = true)
			OrrderInput orrderInput);
}
