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

	@Value("${delivery.storage.local.diretorio-fotos}")
	private Path diretorioFotos;
	
	@Override
	public void store(NewPhoto newPhoto) {
		try {
			Path arquivoPath = getArquivoPath(newPhoto.getNomeArquivo());
			FileCopyUtils.copy(newPhoto.getInputStream(), Files.newOutputStream(arquivoPath));
		} catch (Exception e) {
			throw new StorageException("Não foi possível armazenar o arquivo", e);
		}
	}

	@Override
	public void toRemove(String nomeArquivo) {
		try {
			Path arquivoPath = getArquivoPath(nomeArquivo);
			
			Files.deleteIfExists(arquivoPath);
		} catch (Exception e) {
			throw new StorageException("Não foi possível excluir arquivo.", e);
		}
	}
	
	@Override
	public InputStream toRecover(String nomeArquivo) {
		try {
			Path arquivoPath = getArquivoPath(nomeArquivo);
			return Files.newInputStream(arquivoPath);
		} catch (Exception e) {
			throw new StorageException("Não foi possível recuperar o arquivo", e);
		}		
	}
	
	private Path getArquivoPath(String nomeArquivo) {
		return diretorioFotos.resolve(Path.of(nomeArquivo));
	}

}
