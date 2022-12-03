package com.github.fa2bio.api.swaggeropenapi.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("Pageable")
@Setter
@Getter
public class PageableModelSwagger {

	@ApiModelProperty(example = "0", value = "Page number (startes with 0)")
	private int page;
	
	@ApiModelProperty(example = "10", value = "Number of elements per page")
	private int size;
	
	@ApiModelProperty(example = "nome,asc", value = "Property name for sorting")
	private List<String> sort;
}
