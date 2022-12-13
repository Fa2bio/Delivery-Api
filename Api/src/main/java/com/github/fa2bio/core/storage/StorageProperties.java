package com.github.fa2bio.core.storage;

import java.nio.file.Path;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties("delivery.storage")
public class StorageProperties {

	private Location location = new Location();
	private S3 s3 = new S3();
	
	@Getter
	@Setter
	public class Location{
		private Path directoryPhotos;
	}
	
	@Getter
	@Setter
	public class S3 {
		
		private String accessKeyId;
		private String secretAccessKey;
		private String bucket;
		private String region;
		private String directoryPhotos;
		
	}
}
