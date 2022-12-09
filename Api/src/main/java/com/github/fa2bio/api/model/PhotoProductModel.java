package com.github.fa2bio.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Relation(collectionRelation = "photos")
public class PhotoProductModel extends RepresentationModel<PhotoProductModel>{
	
	@ApiModelProperty(example = "b8bbd21a-4dd3-4954-835c-3493af2ba6a0_Prime-Rib.jpg")
	private String fileName;
	
	@ApiModelProperty(example = "Prime Rib ao ponto")
	private String description;
	
	@ApiModelProperty(example = "image/jpeg")
	private String contentType;
	
	@ApiModelProperty(example = "202912")
	private Long size;
}
