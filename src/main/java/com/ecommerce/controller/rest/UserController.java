package com.ecommerce.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.model.Products;
import com.ecommerce.service.ProductService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {

	@Autowired
	ProductService productService;
	
	@GetMapping("/product/serchByProductName")
	ResponseEntity<Flux<Products>> searchByName(@RequestParam String name) {
		
		return ResponseEntity.ok(productService.searchByName(name));
	}
	
	@GetMapping("/product/searchByPriceAsc")
	ResponseEntity<Flux<Products>> searchByPriceAsc() {
		return ResponseEntity.ok(productService.getAllProductsByPriceAsc());
	}
	
	@GetMapping("/product/searchByPriceDesc")
	ResponseEntity<Flux<Products>> searchByPriceDesc() {
		return ResponseEntity.ok(productService.getAllProductsByPriceDsc());
	}
}
