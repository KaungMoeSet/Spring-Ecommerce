package com.ecommerce.dao;


import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.Products;

import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Products, String> {

	Boolean existsByName(String name);
	
	@Query("{ id: { $exists: true }}")
	Flux<Products> retrieveAllProductsPaged(Pageable page);
	
	Flux<Products> findAllByOrderByPriceDesc();
	Flux<Products> findAllByOrderByPriceAsc();
	Flux<Products> findByName(String name);
	Flux<Products> findByCategory(String category);
	Flux<Products> findByStatus(String status);
	Flux<Products> findByCategoryAndStatus(String category, String status);
}
