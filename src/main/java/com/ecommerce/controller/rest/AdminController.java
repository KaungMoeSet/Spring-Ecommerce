package com.ecommerce.controller.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.model.Products;
import com.ecommerce.service.ProductService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	ProductService productService;

	@GetMapping("/getProducts")
	Flux<Products> getAllProducts(){
		return productService.getAllProducts();
	}
	
	@PostMapping("/saveProduct")
	ResponseEntity<Mono<Products>> saveProduct(@RequestBody @Valid Products product) {
		Mono<Products> savedMovie = productService.saveProduct(product);
		log.info("saveProduct Controller " + savedMovie);
		return ResponseEntity.status(200).body(savedMovie);
	}

	@PostMapping("/{id}")
	Mono<ResponseEntity<Products>> updateProduct(@PathVariable(value = "id") String productID,
			@RequestBody @Valid Products product) {

		return productService.findById(productID).flatMap(existingProduct -> {
			existingProduct.setName(product.getName());
			existingProduct.setPrice(product.getPrice());
			existingProduct.setYear(product.getYear());
			existingProduct.setCategory(product.getCategory());
			existingProduct.setImg(product.getImg());
			existingProduct.setStatus(product.getStatus());
			existingProduct.setQuantity(product.getQuantity());
			existingProduct.setDescription(product.getDescription());
			return productService.saveProduct(existingProduct);
		}).map(updatedProduct -> new ResponseEntity<>(updatedProduct, HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@DeleteMapping("/{id}")
	Mono<ResponseEntity<String>> deleteProdut(@PathVariable(value = "id") String productID) {

		return productService.findById(productID).flatMap(existingProduct -> 
			productService.deleteProduct(existingProduct)
					.then(Mono.just(new ResponseEntity<>("Delete Ok",HttpStatus.OK)))
		).defaultIfEmpty(new ResponseEntity<>("Product Not found",HttpStatus.NOT_FOUND));
	}
}
