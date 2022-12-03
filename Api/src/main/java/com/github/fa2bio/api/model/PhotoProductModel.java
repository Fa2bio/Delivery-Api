package com.github.fa2bio.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhotoProductModel {
	private String fileName;
	private String description;
	private String contentType;
	private Long size;
}