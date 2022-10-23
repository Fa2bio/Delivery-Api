package com.github.fa2bio.domain.repository;

import org.springframework.stereotype.Repository;

import com.github.fa2bio.domain.model.Grupo;

@Repository
public interface GrupoRepository extends CustomJpaRepository<Grupo, Long>{

}
