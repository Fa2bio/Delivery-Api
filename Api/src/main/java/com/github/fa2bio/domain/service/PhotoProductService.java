package com.github.fa2bio.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.fa2bio.domain.exception.FotoProdutoNaoEncontradaException;
import com.github.fa2bio.domain.model.FotoProduto;
import com.github.fa2bio.domain.repository.ProductRepository;
import com.github.fa2bio.domain.service.PhotoStorageService.NewPhoto;

@Service
public class PhotoProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private PhotoStorageService photoStorageService;
	
	@Transactional
	public FotoProduto salvar(FotoProduto foto, java.io.InputStream dadosArquivo) {
		Long restauranteId = foto.getRestauranteId();
		Long produtoId = foto.getProduto().getId();
		String nomeNovoArquivo = photoStorageService.generateFileName(foto.getNomeArquivo());
		String nomeArquivoExistente = null;
		
		Optional<FotoProduto> fotoExistente = productRepository
				.findFotoById(restauranteId, produtoId);
		
		if (fotoExistente.isPresent()) {
			nomeArquivoExistente = fotoExistente.get().getNomeArquivo();
			productRepository.delete(fotoExistente.get());
		}
		
		foto.setNomeArquivo(nomeNovoArquivo);
		foto = productRepository.save(foto);
		productRepository.flush();
		
		NewPhoto newPhoto = NewPhoto.builder()
				.nomeArquivo(foto.getNomeArquivo())
				.inputStream(dadosArquivo)
				.build();
		
		photoStorageService.toReplace(nomeArquivoExistente, newPhoto);
		
		return foto;
	}
	
	@Transactional
	public void excluir(Long restauranteId, Long produtoId) {
		FotoProduto foto = fetchOrFail(restauranteId, produtoId);
		
		productRepository.delete(foto);
		productRepository.flush();

		photoStorageService.toRemove(foto.getNomeArquivo());
	}
	
	public FotoProduto fetchOrFail(Long restauranteId,Long produtoId) {
		return productRepository.findFotoById(restauranteId, produtoId)
				.orElseThrow(() -> new FotoProdutoNaoEncontradaException(restauranteId, produtoId));
	}
	
}
