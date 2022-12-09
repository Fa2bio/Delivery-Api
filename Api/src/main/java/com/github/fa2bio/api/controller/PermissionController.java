package com.github.fa2bio.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.fa2bio.api.assembler.PermissionModelAssembler;
import com.github.fa2bio.api.model.PermissionModel;
import com.github.fa2bio.api.swaggeropenapi.controller.PermissionControllerSwagger;
import com.github.fa2bio.domain.repository.PermissionRepository;

@RestController
@RequestMapping(value = "/permissions")
public class PermissionController implements PermissionControllerSwagger{
	
	@Autowired
	private PermissionRepository permissionRepository;
	
	@Autowired
	private PermissionModelAssembler permissionModelAssembler;

	@Override
	@GetMapping
	public CollectionModel<PermissionModel> list() {
		return permissionModelAssembler.toCollectionModel(permissionRepository.findAll());
	}

}
