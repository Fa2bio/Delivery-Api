package com.github.fa2bio.infrastructure.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.fa2bio.domain.model.PhotoProduct;
import com.github.fa2bio.domain.repository.ProductRepositoryQueries;

@Repository
public class ProductRepositoryImpl implements ProductRepositoryQueries{

	@PersistenceContext
	private EntityManager manager;

	@Override
	@Transactional
	public PhotoProduct save(PhotoProduct photo) {
		return manager.merge(photo);
	}

	@Override
	public void delete(PhotoProduct photo) {
		manager.remove(photo);
	}

}
