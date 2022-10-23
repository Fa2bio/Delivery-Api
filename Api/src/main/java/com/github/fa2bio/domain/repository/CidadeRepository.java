package com.github.fa2bio.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.github.fa2bio.domain.model.Cidade;

@Repository
public interface CidadeRepository extends CustomJpaRepository<Cidade, Long> {

	@Query("from Cidade r join fetch r.estado")
	List<Cidade> findAll();
}
