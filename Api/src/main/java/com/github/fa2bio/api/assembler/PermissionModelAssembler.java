package com.github.fa2bio.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.model.PermissionModel;
import com.github.fa2bio.core.hypermedia.DeliveryLinks;
import com.github.fa2bio.domain.model.Permission;

@Component
public class PermissionModelAssembler 
	implements RepresentationModelAssembler<Permission, PermissionModel>{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private DeliveryLinks deliveryLinks;
	
	@Override
	public PermissionModel toModel(Permission permission) {
		PermissionModel permissionModel = modelMapper.map(permission, PermissionModel.class);
		return permissionModel;
	}
	
	@Override
	public CollectionModel<PermissionModel> toCollectionModel(Iterable<? extends Permission> entities){
		return RepresentationModelAssembler.super.toCollectionModel(entities)
				.add(deliveryLinks.linkToPermissions());
	}
}
