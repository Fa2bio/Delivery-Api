package com.github.fa2bio.api.swaggeropenapi.model;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel("Links")
public class LinksModelSwagger {
	
	private LinkModel rel;
	
	@Setter
	@Getter
	@ApiModel("Link")
	private class LinkModel {
		
		private String href;
		private boolean templated;
		
	}
}
