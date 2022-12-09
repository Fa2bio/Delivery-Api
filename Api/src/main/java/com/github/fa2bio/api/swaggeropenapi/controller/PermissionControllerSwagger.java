package com.github.fa2bio.api.swaggeropenapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.github.fa2bio.api.model.PermissionModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Permissions")
public interface PermissionControllerSwagger {

	@ApiOperation("List of permissions")
	CollectionModel<PermissionModel> list();
}
