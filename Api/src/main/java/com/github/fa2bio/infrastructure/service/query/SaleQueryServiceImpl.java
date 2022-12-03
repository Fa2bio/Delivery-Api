package com.github.fa2bio.infrastructure.service.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;

import org.springframework.stereotype.Repository;

import com.github.fa2bio.domain.filter.DailySaleFilter;
import com.github.fa2bio.domain.model.Orderr;
import com.github.fa2bio.domain.model.OrderStatus;
import com.github.fa2bio.domain.model.dto.DailySale;
import com.github.fa2bio.domain.service.SaleQueryService;

@Repository
public class SaleQueryServiceImpl implements SaleQueryService{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<DailySale> consultDailySales(DailySaleFilter filter,
			String timeOffset) {
		var builder = manager.getCriteriaBuilder();
		var query = builder.createQuery(DailySale.class);
		var root = query.from(Orderr.class);
		
		var predicates = new ArrayList<Predicate>();
		
		if(filter.getRestaurantId() != null) {
			predicates.add(builder.equal(root.get("restaurant"), filter.getRestaurantId()));
		}
		
		if(filter.getCreationDateStart() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("creationDate"),
					filter.getCreationDateStart()));
		}
		
		if(filter.getCreationDateFinal() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("creationDate"),
					filter.getCreationDateFinal()));
		}
		
		predicates.add(root.get("status").in(OrderStatus.CONFIRMED, OrderStatus.DELIVERED));
		
		var functionConvertTzCreationDate = builder.function(
				"convert_tz", Date.class, root.get("creationDate"),
				builder.literal("+00:00"), builder.literal(timeOffset));
		
		var functionCreationDate = builder.function(
				"date", Date.class, functionConvertTzCreationDate);
		
		var selection = builder.construct(DailySale.class,
				functionCreationDate,
				builder.count(root.get("id")),
				builder.sum(root.get("totalAmount")));
		
		query.select(selection);
		query.where(builder.and(predicates.toArray(new Predicate[0])));
		query.groupBy(functionCreationDate);
		
		return manager.createQuery(query).getResultList();
	}

	
}
