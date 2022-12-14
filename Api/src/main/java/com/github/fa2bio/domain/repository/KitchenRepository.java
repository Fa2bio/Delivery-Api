package com.github.fa2bio.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.github.fa2bio.domain.model.Kitchen;

@Repository
public interface KitchenRepository extends CustomJpaRepository<Kitchen, Long> {

	List<Kitchen> findAllByNameContaining(String name);
	
	Optional<Kitchen> findByName(String name);
	
	boolean existsByName(String name);
	
}
