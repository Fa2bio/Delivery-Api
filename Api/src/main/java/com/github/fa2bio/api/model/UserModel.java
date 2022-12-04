package com.github.fa2bio.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Relation(collectionRelation = "users")
public class UserModel extends RepresentationModel<UserModel>{
	
	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "João da Silva")
	private String name;
	
	@ApiModelProperty(example = "joao.ger@test.com")
	private String email;
}