package com.ecommerce.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.ecommerce.model.Products;

public interface ProductRepository extends ReactiveMongoRepository<Products, String>  {

}
