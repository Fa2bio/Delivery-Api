package com.github.fa2bio.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.fa2bio.domain.exception.EntityInUseException;
import com.github.fa2bio.domain.exception.ProdutoNaoEncontradoException;
import com.github.fa2bio.domain.model.Produto;
import com.github.fa2bio.domain.repository.ProductRepository;

@Service
public class ProductService {
	private static final String MSG_PRODUTO_EM_USO 
	= "Produto de código %d não pode ser removido, pois está em uso";

	@Autowired
	private ProductRepository productRepository;
	
	@Transactional
	public Produto salvar (Produto produto) {
		return productRepository.save(produto);
	}
	
	@Transactional
	public void excluir (Long id) {
		try {
			productRepository.deleteById(id);
			productRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new ProdutoNaoEncontradoException(id);
		} catch(DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format(MSG_PRODUTO_EM_USO, id));
		}
	}
	
	public Produto fetchOrFail(Long produtoId) {
		return productRepository.findById(produtoId)
			.orElseThrow(() -> new ProdutoNaoEncontradoException(produtoId));
	}
	
	public Produto fetchOrFail(Long restauranteId, Long produtoId) {
		return productRepository.findById(restauranteId, produtoId)
			.orElseThrow(() -> new ProdutoNaoEncontradoException(restauranteId, produtoId));
	}
}
