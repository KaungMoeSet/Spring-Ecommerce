package com.ecommerce.repository;


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
//	Flux<Products>
}
