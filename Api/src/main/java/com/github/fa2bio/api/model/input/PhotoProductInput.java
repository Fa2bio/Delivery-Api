package com.github.fa2bio.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import com.github.fa2bio.core.validation.FileContentType;
import com.github.fa2bio.core.validation.FileSize;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhotoProductInput {

	@NotNull
	@FileSize(max = "500KB")
	@FileContentType(allowed = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
	private MultipartFile file;
	
	@NotBlank
	private String description;
}
