package com.github.fa2bio.domain.service;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

public interface PhotoStorageService {
	
	java.io.InputStream toRecover(String fileName);

	void store(NewPhoto newPhoto);

	void toRemove(String fileName);
	
	default void toReplace(String oldFileName, NewPhoto newPhoto) {
		this.store(newPhoto);
		if(oldFileName != null) this.toRemove(oldFileName);
	}
	
	default String generateFileName(String originalName) {
		return UUID.randomUUID().toString() + "_" + originalName;
	}
	
	@Builder
	@Getter
	class NewPhoto{
		private String fileName;
		private String contentType;
		private java.io.InputStream inputStream;
	}
}
