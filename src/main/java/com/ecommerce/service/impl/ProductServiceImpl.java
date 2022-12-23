package com.ecommerce.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecommerce.dao.ProductDetailRepository;
import com.ecommerce.dao.ProductRepository;
import com.ecommerce.dao.UserRepository;
import com.ecommerce.model.ProductDetail;
import com.ecommerce.model.Products;
import com.ecommerce.model.User;
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
	ProductDetailRepository productDetailRepository;
	
	@Autowired
	ModelMapper mapper;

	@Override
	public Mono<Products> saveProduct(Products product) {
		if(product.getDetails()!=null) {
			return productDetailRepository.save(product.getDetails())
					.flatMap( productDetail -> {
						log.info(productDetail.toString());
						return productRepository.save(product);
					});
		}else {
			return productRepository.save(product);
		}
	}
	
	@Override
	public Mono<Products> updateProuduct(String id, Products product) {
		
		if(product.getDetails()!=null) {
			return productRepository.findById(id).flatMap( existingProduct -> {
				if(existingProduct.getDetails() != null) {
					return productDetailRepository.findById( existingProduct.getDetails().getId() )
						.flatMap( existingDetail -> {
							existingDetail.setDate(product.getDetails().getDate());
							existingDetail.setImage(product.getDetails().getImage());
							existingDetail.setColors(product.getDetails().getColors());
							existingDetail.setDescription(product.getDetails().getDescription());
							log.info("ProductDetail After Updated: "+existingDetail.toString());
							
							return productDetailRepository.save(existingDetail);
						}).flatMap( updatedDetail-> {
							return updateProductInDB(existingProduct, product);
						});
				} else {
					return productDetailRepository.save( product.getDetails() )
							.flatMap( productDetail -> {
								existingProduct.setName(product.getName());
								existingProduct.setCategory(product.getCategory());
								existingProduct.setStatus(product.getStatus());
								existingProduct.setQuantity(product.getQuantity());
								existingProduct.setPrice(product.getPrice());
								existingProduct.setDetails(product.getDetails());

							return productRepository.save(existingProduct);
					});
				}
			});
			
		} else {
			return productRepository.findById(id).flatMap(existingProduct -> updateProductInDB(existingProduct,product));
		}
	}

	private Mono<Products> updateProductInDB(Products existingProduct, Products product) {
			existingProduct.setName(product.getName());
			existingProduct.setCategory(product.getCategory());
			existingProduct.setStatus(product.getStatus());
			existingProduct.setQuantity(product.getQuantity());
			existingProduct.setPrice(product.getPrice());

		return productRepository.save(existingProduct);
	}

	@Override
	public Mono<Void> deleteProduct(Products product) {
		return productRepository.delete(product)
				.then(productDetailRepository.delete(product.getDetails()));
	}

	@Override
	public Flux<Products> getAllProducts() {
		return productRepository.findAll();
	}
	
	@Override
	public Flux<Products> getAllProductsByPriceAsc() {
		return productRepository.findAllByOrderByPriceAsc();
	}
	
	@Override
	public Flux<Products> getAllProductsByPriceDsc() {
		return productRepository.findAllByOrderByPriceDesc();
	}
	
	@Override
	public Mono<Products> getProductById(String id) {
		return productRepository.findById(id);
	}
	
	@Override
	public Flux<Products> getProductsByPage(int pageNo, int size) {
		Pageable findByPage = (Pageable) PageRequest.of(pageNo, size);
		
		return productRepository.retrieveAllProductsPaged(findByPage);
	}

	@Override
	public Flux<Products> searchByName(String name) {
		return productRepository.findByName(name);
	}

	@Override
	public Flux<Products> searchByCategory(String category) {
		return productRepository.findByCategory(category);
	}
	
	@Override
	public Flux<Products> searchByStatus(String status) {
		return productRepository.findByStatus(status);
	}

	@Override
	public Flux<Products> searchByCategoryAndStatus(String category, String status) {
		return productRepository.findByCategoryAndStatus(category, status);
	}
	
	@Override
	public Flux<Products> searchProductOrderByYear() {
		return null;
	}

	@Override
	public Flux<Products> searchProductOrderByPrice() {
		return null;
	}
}
