package com.github.fa2bio.infrastructure.service.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;

import org.springframework.stereotype.Repository;

import com.github.fa2bio.domain.filter.DailySaleFilter;
import com.github.fa2bio.domain.model.Pedido;
import com.github.fa2bio.domain.model.StatusPedido;
import com.github.fa2bio.domain.model.dto.DailySale;
import com.github.fa2bio.domain.service.SaleQueryService;

@Repository
public class VendaQueryServiceImpl implements SaleQueryService{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<DailySale> consultDailySales(DailySaleFilter filtro,
			String timeOffset) {
		var builder = manager.getCriteriaBuilder();
		var query = builder.createQuery(DailySale.class);
		var root = query.from(Pedido.class);
		
		var predicates = new ArrayList<Predicate>();
		
		if(filtro.getRestauranteId() != null) {
			predicates.add(builder.equal(root.get("restaurante"), filtro.getRestauranteId()));
		}
		
		if(filtro.getDataCriacaoInicio() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"),
					filtro.getDataCriacaoInicio()));
		}
		
		if(filtro.getDataCriacaoFim() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"),
					filtro.getDataCriacaoFim()));
		}
		
		predicates.add(root.get("status").in(StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE));
		
		var functionConvertTzDataCriacao = builder.function(
				"convert_tz", Date.class, root.get("dataCriacao"),
				builder.literal("+00:00"), builder.literal(timeOffset  ));
		
		var functionDataCriacao = builder.function(
				"date", Date.class, functionConvertTzDataCriacao);
		
		var selection = builder.construct(DailySale.class,
				functionDataCriacao,
				builder.count(root.get("id")),
				builder.sum(root.get("valorTotal")));
		
		query.select(selection);
		query.where(builder.and(predicates.toArray(new Predicate[0])));
		query.groupBy(functionDataCriacao);
		
		return manager.createQuery(query).getResultList();
	}

	
}
