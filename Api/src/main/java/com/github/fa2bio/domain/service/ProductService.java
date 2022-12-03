package com.github.fa2bio.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.fa2bio.domain.exception.EntityInUseException;
import com.github.fa2bio.domain.exception.ProductNotFoundException;
import com.github.fa2bio.domain.model.Product;
import com.github.fa2bio.domain.repository.ProductRepository;

@Service
public class ProductService {
	private static final String MSG_PRODUCT_IN_USE 
	= "The product with code %d cannot be removed because it is in use";

	@Autowired
	private ProductRepository productRepository;
	
	@Transactional
	public Product save (Product product) {
		return productRepository.save(product);
	}
	
	@Transactional
	public void delete (Long id) {
		try {
			productRepository.deleteById(id);
			productRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new ProductNotFoundException(id);
		} catch(DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format(MSG_PRODUCT_IN_USE, id));
		}
	}
	
	public Product fetchOrFail(Long productId) {
		return productRepository.findById(productId)
			.orElseThrow(() -> new ProductNotFoundException(productId));
	}
	
	public Product fetchOrFail(Long restaurantId, Long productId) {
		return productRepository.findById(restaurantId, productId)
			.orElseThrow(() -> new ProductNotFoundException(restaurantId, productId));
	}
}
