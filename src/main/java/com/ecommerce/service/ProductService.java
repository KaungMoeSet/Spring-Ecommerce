package com.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.ecommerce.model.Products;

import reactor.core.publisher.Mono;

public interface ProductService {

	Mono<Products> saveProduct(Products productDto);
	void deleteProductById(String productId);
	Optional<Products> getProductById(String id);
	
	Mono<Products> findById(String id);
	List<Products> findByName(String name);
	List<Products> findProductByPage(int pageNo, int size);
	List<Products> findByCategory(String category);	
	List<Products> findProductOrderByYear();
	List<Products> findProductOrderByPrice();
}