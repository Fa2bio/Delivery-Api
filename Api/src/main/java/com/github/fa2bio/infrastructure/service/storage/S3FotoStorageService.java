package com.github.fa2bio.infrastructure.service.storage;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.github.fa2bio.core.storage.StorageProperties;
import com.github.fa2bio.domain.service.PhotoStorageService;

//@Service
public class S3FotoStorageService implements PhotoStorageService{
	
	@Autowired
	private AmazonS3 amazonS3;
	
	@Autowired
	private StorageProperties storageProperties;

	@Override
	public InputStream toRecover(String fileName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void store(NewPhoto newPhoto) {
		
		try {
			String filePath = getFilePath(newPhoto.getFileName());
			
			var objectMetadata = new ObjectMetadata();
			objectMetadata.setContentType(newPhoto.getContentType());
			
			var putObjectRequest = new PutObjectRequest(
					storageProperties.getS3().getBucket(),
					filePath,
					newPhoto.getInputStream(),
					objectMetadata)
				.withCannedAcl(CannedAccessControlList.PublicRead);
			
			amazonS3.putObject(putObjectRequest);
		} catch (Exception e) {
			throw new StorageException("Unable to send file to Amazon S3.", e);
		}
		
	}

	@Override
	public void toRemove(String fileName) {
		try {
			String filePath = getFilePath(fileName);

			var deleteObjectRequest = new DeleteObjectRequest(
					storageProperties.getS3().getBucket(), filePath);

			amazonS3.deleteObject(deleteObjectRequest);
		} catch (Exception e) {
			throw new StorageException("Unable to remove file into Amazon S3.", e);
		}
		
	}
	
	private String getFilePath(String fileName) {
		return String.format("%s/%s", storageProperties.getS3().getDirectoryPhotos(), fileName);
	}

}
