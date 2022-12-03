package com.github.fa2bio.infrastructure.repository.spec;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.github.fa2bio.domain.filter.OrderFilter;
import com.github.fa2bio.domain.model.Orderr;

public class OrderSpecs {

	public static Specification<Orderr> usingFilter(OrderFilter filter){
		return (root, query, builder) -> {
			
			if(Orderr.class.equals(query.getResultType())) {
				root.fetch("restaurant").fetch("kitchen");
				root.fetch("client" );
			}
			
			var predicates = new ArrayList<Predicate>();
			
			if(filter.getClientId() != null) {
				predicates.add(builder.equal(root.get("client"), filter.getClientId()));
			}
			
			if(filter.getRestaurantId() != null) {
				predicates.add(builder.equal(root.get("restaurant"), filter.getRestaurantId()));
			}
			
			if(filter.getCreationDateStart() != null) {
				predicates.add(builder.greaterThanOrEqualTo(root.get("creationDate"), filter.getCreationDateStart()));
			}
			
			if(filter.getCreationDateFinal() != null) {
				predicates.add(builder.lessThanOrEqualTo(root.get("creationDate"), filter.getCreationDateFinal()));
			}
			
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
	
}
