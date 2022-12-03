package com.github.fa2bio.domain.repository;

import org.springframework.stereotype.Repository;

import com.github.fa2bio.domain.model.State;

@Repository
public interface StateRepository extends CustomJpaRepository<State, Long> {

}
