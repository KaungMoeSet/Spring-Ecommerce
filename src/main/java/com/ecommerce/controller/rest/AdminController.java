package com.ecommerce.controller.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dao.ProductRepository;
import com.ecommerce.model.Products;
import com.ecommerce.model.User;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("admin")
@SecurityRequirement(name = "swaggerOpenAPI")
public class AdminController {

	@Autowired
	ProductService productService;
	
	@Autowired
	UserService userService;

	@GetMapping("/product/getProducts")
	Flux<Products> getAllProducts(){
		return productService.getAllProducts();
	}
	
	@GetMapping("/product/productPaging")
	ResponseEntity<Flux<Products>> getAllProductsByPage(@RequestParam Integer pageNo, @RequestParam Integer size) {
		
		return ResponseEntity.ok(productService.getProductsByPage(pageNo, size));
	}
	
	@GetMapping("/product/{id}")
	Mono<ResponseEntity<Products>> getProductById(@PathVariable(value = "id") String productId) {
		return productService.getProductById(productId)
				.map(savedProduct -> ResponseEntity.ok(savedProduct))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/product/saveProduct")
	ResponseEntity<Mono<Products>> saveProduct(@RequestBody @Valid Products product) {
		Mono<Products> savedProduct = productService.saveProduct(product);
		log.info("saveProduct Controller " + savedProduct);
		return ResponseEntity.status(200).body(savedProduct);
	}

	@PutMapping("/product/{id}")
	Mono<ResponseEntity<Products>> updateProduct(@PathVariable(value = "id") String productID,
			@RequestBody @Valid Products product) {

		return productService.updateProuduct(productID, product)
				.map(updatedProduct -> new ResponseEntity<>(updatedProduct, HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@DeleteMapping("/product/{id}")
	Mono<ResponseEntity<Void>> deleteProdut(@PathVariable(value = "id") String productID) {

		return productService.getProductById(productID)
				.flatMap(existingProduct -> {
						log.info(""+existingProduct.getDetails());
						return productService.deleteProduct(existingProduct).then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)));
				})
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
				
	}
	
	@GetMapping("/product/searchByName")
	ResponseEntity<Flux<Products>> searchByName(@RequestParam String name) {
		
		return ResponseEntity.ok(productService.searchByName(name));
	}
	
	@GetMapping("/product/searchByCategory")
	ResponseEntity<Flux<Products>> searchByCategory(@RequestParam String category) {
		return ResponseEntity.ok(productService.searchByCategory(category));
	}
	
	@GetMapping("/product/searchByStatus")
	ResponseEntity<Flux<Products>> searchByStatus(@RequestParam String status) {
		return ResponseEntity.ok(productService.searchByStatus(status));
	}
	
	@GetMapping("/product/searchByCategoryAndStatus")
	ResponseEntity<Flux<Products>> searchByCategoryAndStatus(@RequestParam String category,
															@RequestParam String status) {
		return ResponseEntity.ok(productService.searchByCategoryAndStatus(category, status));
	}
	
/*-------------------------------- Product Session End --------------------------------*/	
	
	
/*-------------------------------- User Session Start --------------------------------*/	
	@GetMapping("/user/userPaging")
	ResponseEntity<List<User>> getAllUserByPage(@RequestParam Integer pageNo, @RequestParam Integer size) {
		return ResponseEntity.ok(userService.getUsersByPage(pageNo, size));
	}
	
	@GetMapping("/user/AdminAccount")
	ResponseEntity<List<User>> getAdminAccount() {
		return ResponseEntity.ok(userService.getAdminAccount());
	}
}
