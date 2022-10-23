package com.github.fa2bio.domain.repository;

import org.springframework.stereotype.Repository;

import com.github.fa2bio.domain.model.Estado;

@Repository
public interface EstadoRepository extends CustomJpaRepository<Estado, Long> {

}
