package com.github.fa2bio.infrastructure.service.storage;

import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.github.fa2bio.core.storage.StorageProperties;
import com.github.fa2bio.domain.service.PhotoStorageService;

@Service
public class LocationPhotoStorageService implements PhotoStorageService{

//	@Value("${delivery.storage.location.directory-photos}")
//	private Path directoryPhotos;
	
	@Autowired
	private StorageProperties storageProperties;
	
	@Override
	public void store(NewPhoto newPhoto) {
		try {
			Path filePath = getArquivoPath(newPhoto.getFileName());
			FileCopyUtils.copy(newPhoto.getInputStream(), Files.newOutputStream(filePath));
		} catch (Exception e) {
			throw new StorageException("Unable to store the file.", e);
		}
	}

	@Override
	public void toRemove(String fileName) {
		try {
			Path arquivoPath = getArquivoPath(fileName);
			
			Files.deleteIfExists(arquivoPath);
		} catch (Exception e) {
			throw new StorageException("Could not delete file.", e);
		}
	}
	
	@Override
	public PhotoRecover toRecover(String fileName) {
		try {
			Path filePath = getArquivoPath(fileName);
			PhotoRecover photoRecovered = PhotoRecover.builder()
					.inputStream(Files.newInputStream(filePath))
					.build();
			return photoRecovered;
		} catch (Exception e) {
			throw new StorageException("Unable to retrieve file.", e);
		}		
	}
	
	private Path getArquivoPath(String fileName) {
//		return directoryPhotos.resolve(Path.of(fileName));
		return storageProperties.getLocation().getDirectoryPhotos()
				.resolve(Path.of(fileName));
	}

}
