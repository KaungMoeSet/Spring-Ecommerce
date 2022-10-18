package com.ecommerce.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "products")
public class Products extends BaseDocument {

	@NotEmpty
	@Size(max=50)
	private String name;
	
	@NotNull
	private Float price;
	
	@NotNull
	private Integer year;
	
	@NotNull
	private Category category;
	
	private Binary img;
	
	@NotNull
	private Status status;
	
	@NotNull
	private Integer quantity;
	
	@NotEmpty
	private String description;
	
}