package com.github.fa2bio.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.github.fa2bio.domain.model.City;

@Repository
public interface CityRepository extends CustomJpaRepository<City, Long> {

	@Query("from City r join fetch r.state")
	List<City> findAll();
}
