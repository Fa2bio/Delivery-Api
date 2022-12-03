package com.github.fa2bio.api.swaggeropenapi.model;

import io.swagger.annotations.ApiModelProperty;

public class PagedModelSwagger<T> {
	
	@ApiModelProperty(example = "10", value = "Number of records per page")
	private Long size;
	
	@ApiModelProperty(example = "50", value = "Total record")
	private Long totalElements;
	
	@ApiModelProperty(example = "5", value = "Total of pages")
	private Long totalPages;
	
	@ApiModelProperty(example = "0", value = "Page number (startes with 0)")
	private Long number;
}
