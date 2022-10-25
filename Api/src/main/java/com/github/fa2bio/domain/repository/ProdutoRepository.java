package com.github.fa2bio.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.github.fa2bio.domain.model.Produto;
import com.github.fa2bio.domain.model.Restaurante;

@Repository
public interface ProdutoRepository extends CustomJpaRepository<Produto, Long>{

	@Query("from Produto where restaurante.id = :restaurante and id = :produto")
	Optional<Produto> findById(@Param("restaurante") Long restauranteId, 
			@Param("produto") Long produtoId);
	
	List<Produto> findByRestaurante(Restaurante restaurante);
	
}
