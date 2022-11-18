package com.ecommerce.service;

import java.util.Optional;

import com.ecommerce.model.Products;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

	Mono<Products> saveProduct(Products productDto);
	Mono<Void> deleteProduct(Products product);
	Optional<Products> getProductById(String id);
	
	Flux<Products> getAllProducts();
	Flux<Products> getAllProductsByPriceAsc();
	Flux<Products> getAllProductsByPriceDsc();
	
	Mono<Products> findById(String id);
	Flux<Products> findByName(String name);
	Flux<Products> findProductByPage(int pageNo, int size);
	Flux<Products> findByCategory(String category);	
	Flux<Products> findProductOrderByYear();
	Flux<Products> findProductOrderByPrice();
}
