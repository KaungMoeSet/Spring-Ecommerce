package com.ecommerce.controller.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.model.Products;
import com.ecommerce.service.ProductService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@PostMapping("/saveProduct")
	ResponseEntity<Mono<Products>> saveProduct(@RequestBody @Valid Products product) {
		Mono<Products> savedMovie = productService.saveProduct(product);
		log.info("saveProduct Controller "+ savedMovie);
		return ResponseEntity.status(200).body(savedMovie);
	}
	
	@PostMapping("/{id}")
	Mono<ResponseEntity<Products>> updateProduct(@PathVariable(value = "id") String productID, @RequestBody @Valid Products product) {
		
		return productService.findById(productID)
							.flatMap(existingProduct -> {
								existingProduct.setName(product.getName());
								existingProduct.setPrice(product.getPrice());
								existingProduct.setYear(product.getYear());
								existingProduct.setCategory(product.getCategory());
								existingProduct.setImg(product.getImg());
								existingProduct.setStatus(product.getStatus());
								existingProduct.setQuantity(product.getQuantity());
								existingProduct.setDescription(product.getDescription());
								return productService.saveProduct(existingProduct);
							})
							.map(updatedProduct -> new ResponseEntity<>(updatedProduct, HttpStatus.OK))
							.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		}
	}
