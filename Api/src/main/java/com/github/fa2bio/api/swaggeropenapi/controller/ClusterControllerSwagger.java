package com.github.fa2bio.api.swaggeropenapi.controller;

import java.util.List;

import com.github.fa2bio.api.exceptionhandler.Problem;
import com.github.fa2bio.api.model.ClusterModel;
import com.github.fa2bio.api.model.input.ClusterInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cluster")
public interface ClusterControllerSwagger {

	@ApiOperation("List of clusters")
	List<ClusterModel> list();
	
	@ApiOperation("Search a cluster by Id")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Invalid cluster Id", response = Problem.class),
		@ApiResponse(code = 404, message = "Cluster not found", response = Problem.class)
	})
	ClusterModel find(
			@ApiParam(value = "Cluster Id", example = "1", required = true)
			Long clusterId);
	
	@ApiOperation("Register a cluster")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Cluster registered"),
	})
	ClusterModel register(
			@ApiParam(name = "Body", value = "Representation of a new city", required = true)
			ClusterInput clusterInput);
	
	@ApiOperation("Update a group by Id")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Cluster updated"),
		@ApiResponse(code = 404, message = "Cluster not found", response = Problem.class)
	})
	ClusterModel update(
			@ApiParam(value = "Cluster Id", example = "1", required = true) 
			Long clusterId, 
			@ApiParam(name = "Body", value = "Representation of a new cluster with the new data", required = true)
			ClusterInput clusterInput);
	
	@ApiOperation("Delete a group by Id")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Cluster deleted"),
		@ApiResponse(code = 404, message = "Cluster not found", response = Problem.class)
	})
	void delete(
			@ApiParam(value = "Cluster Id", example = "1", required = true)
			Long clusterId);
}
