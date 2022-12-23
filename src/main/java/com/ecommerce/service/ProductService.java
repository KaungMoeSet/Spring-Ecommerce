package com.ecommerce.service;

import java.util.Optional;

import com.ecommerce.model.Products;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

	Mono<Products> saveProduct(Products product);
	Mono<Products> updateProuduct(String id, Products product);
	Mono<Void> deleteProduct(Products product);
	
	Mono<Products> getProductById(String id);
	Flux<Products> getAllProducts();
	Flux<Products> getAllProductsByPriceAsc();
	Flux<Products> getAllProductsByPriceDsc();
	Flux<Products> getProductsByPage(int pageNo, int size);
	
	Flux<Products> searchByName(String name);
	Flux<Products> searchByCategory(String category);	
	Flux<Products> searchByStatus(String status);
	Flux<Products> searchByCategoryAndStatus(String category, String status);
	Flux<Products> searchProductOrderByYear();
	Flux<Products> searchProductOrderByPrice();
}
