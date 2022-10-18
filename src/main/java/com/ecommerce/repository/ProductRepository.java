package com.ecommerce.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.Products;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Products, String>  {

}
