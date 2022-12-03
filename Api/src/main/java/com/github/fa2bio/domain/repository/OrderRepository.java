package com.github.fa2bio.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.github.fa2bio.domain.model.Orderr;

public interface OrderRepository extends CustomJpaRepository<Orderr, Long>,
		JpaSpecificationExecutor<Orderr>{
	
	@Query("from Orderr p join fetch p.client join fetch p.restaurant r join fetch r.kitchen")
	List<Orderr> findAll();
	
	@Query("from Orderr where uui_code = :uui_code")	
	Optional<Orderr> findByCodigo(String uui_code);
}
