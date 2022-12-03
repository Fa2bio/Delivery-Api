package com.github.fa2bio.infrastructure.service.storage;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.github.fa2bio.domain.service.PhotoStorageService;

@Service
public class LocalFotoStorageService implements PhotoStorageService{

	@Value("${delivery.storage.local.diretorio-photos}")
	private Path directoryPhotos;
	
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
	public InputStream toRecover(String fileName) {
		try {
			Path filePath = getArquivoPath(fileName);
			return Files.newInputStream(filePath);
		} catch (Exception e) {
			throw new StorageException("Unable to retrieve file.", e);
		}		
	}
	
	private Path getArquivoPath(String fileName) {
		return directoryPhotos.resolve(Path.of(fileName));
	}

}
