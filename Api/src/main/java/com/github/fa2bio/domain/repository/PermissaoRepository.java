package com.github.fa2bio.domain.repository;

import org.springframework.stereotype.Repository;

import com.github.fa2bio.domain.model.Permissao;

@Repository
public interface PermissaoRepository extends CustomJpaRepository<Permissao, Long> {
	
}
