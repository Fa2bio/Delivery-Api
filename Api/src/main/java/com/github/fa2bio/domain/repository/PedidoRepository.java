package com.github.fa2bio.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.github.fa2bio.domain.model.Pedido;

public interface PedidoRepository extends CustomJpaRepository<Pedido, Long>,
		JpaSpecificationExecutor<Pedido>{
	
	@Query("from Pedido p join fetch p.cliente join fetch p.restaurante r join fetch r.cozinha")
	List<Pedido> findAll();
	
	@Query("from Pedido where codigo = :codigo")	
	Optional<Pedido> findByCodigo(String codigo);
}
