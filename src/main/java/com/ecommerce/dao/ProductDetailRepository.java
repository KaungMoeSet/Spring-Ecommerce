package com.ecommerce.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.ProductDetail;

@Repository
public interface ProductDetailRepository extends ReactiveMongoRepository<ProductDetail, String> {

}
