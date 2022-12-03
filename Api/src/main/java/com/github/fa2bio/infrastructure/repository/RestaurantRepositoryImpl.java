package com.github.fa2bio.infrastructure.repository;

import static com.github.fa2bio.infrastructure.repository.spec.RestaurantSpecs.comFreeShipping;
import static com.github.fa2bio.infrastructure.repository.spec.RestaurantSpecs.withSimilarName;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.github.fa2bio.domain.model.Restaurant;
import com.github.fa2bio.domain.repository.RestaurantRepository;
import com.github.fa2bio.domain.repository.RestaurantRepositoryQueries;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired @Lazy
	private RestaurantRepository restaurantRepository;
	
	@Override
	public List<Restaurant> find(String name, 
			BigDecimal creationDateStart, BigDecimal creationDateFinal) {
		var builder = manager.getCriteriaBuilder();
		
		var criteria = builder.createQuery(Restaurant.class);
		var root = criteria.from(Restaurant.class);

		var predicates = new ArrayList<Predicate>();
		
		if (StringUtils.hasText(name)) {
			predicates.add(builder.like(root.get("name"), "%" + name + "%"));
		}
		
		if (creationDateStart != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("rateShipping"), creationDateStart));
		}
		
		if (creationDateFinal != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("rateShipping"), creationDateFinal));
		}
		
		criteria.where(predicates.toArray(new Predicate[0]));
		
		var query = manager.createQuery(criteria);
		return query.getResultList();
	}

	@Override
	public List<Restaurant> findWithFreeShipping(String name) {
		return restaurantRepository.findAll(comFreeShipping()
				.and(withSimilarName(name)));
	}
	
}
