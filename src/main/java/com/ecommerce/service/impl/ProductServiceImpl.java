package com.ecommerce.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.model.Products;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.ProductService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ModelMapper mapper;

	@Override
	public Mono<Products> saveProduct(Products product) {
		return productRepository.save(product);
	}

	@Override
	public Mono<Products> findById(String id) {
		return productRepository.findById(id);
	}
	
	@Override
	public Mono<Void> deleteProduct(Products product) {
		return productRepository.delete(product);
	}

	@Override
	public Flux<Products> getAllProducts() {
		return productRepository.findAll();
	}
	
	@Override
	public Optional<Products> getProductById(String id) {
		return null;
	}

	@Override
	public List<Products> findByName(String name) {
		return null;
	}

	@Override
	public List<Products> findProductByPage(int pageNo, int size) {
		return null;
	}

	@Override
	public List<Products> findByCategory(String category) {
		return null;
	}

	@Override
	public List<Products> findProductOrderByYear() {
		return null;
	}

	@Override
	public List<Products> findProductOrderByPrice() {
		return null;
	}

}
