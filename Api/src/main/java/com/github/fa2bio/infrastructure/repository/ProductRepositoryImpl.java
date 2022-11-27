package com.github.fa2bio.infrastructure.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.fa2bio.domain.model.FotoProduto;
import com.github.fa2bio.domain.repository.ProductRepositoryQueries;

@Repository
public class ProductRepositoryImpl implements ProductRepositoryQueries{

	@PersistenceContext
	private EntityManager manager;

	@Override
	@Transactional
	public FotoProduto save(FotoProduto foto) {
		return manager.merge(foto);
	}

	@Override
	public void delete(FotoProduto foto) {
		manager.remove(foto);
	}

}
