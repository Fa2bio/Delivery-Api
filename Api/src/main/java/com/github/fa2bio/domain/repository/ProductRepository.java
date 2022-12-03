package com.github.fa2bio.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.github.fa2bio.domain.model.PhotoProduct;
import com.github.fa2bio.domain.model.Product;
import com.github.fa2bio.domain.model.Restaurant;

@Repository
public interface ProductRepository extends CustomJpaRepository<Product, Long>, ProductRepositoryQueries{

	@Query("from Product where restaurant.id = :restaurant and id = :product")
	Optional<Product> findById(@Param("restaurant") Long restaurantId, 
			@Param("product") Long productId);
	
	List<Product> findAllByRestaurant(Restaurant restaurant);
	
	@Query("from Product p where p.active = true and p.restaurant = :restaurant")
	List<Product> findActivesByRestaurant(Restaurant restaurant);
	
	@Query("select f from PhotoProduct f join f.product p where p.restaurant.id = :restaurantId and f.product.id = :productId")
	Optional<PhotoProduct> findPhotoById(Long restaurantId, Long productId);
}
