package com.github.fa2bio.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Relation(collectionRelation = "permissions")
public class PermissionModel extends RepresentationModel<PermissionModel>{
	
	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "CONSULTAR_COZINHAS")
	private String name;
	
	@ApiModelProperty(example = "Permite consultar cozinhas")
	private String description;
}
