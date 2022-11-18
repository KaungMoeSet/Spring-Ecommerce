package com.ecommerce.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	private Date date;
	
	@NotNull
	private Category category;
	
	private byte[] image;
	
	private List<Color> colors;
	
	@NotNull
	private EStatus status;
	
	@NotNull
	private Integer quantity;
	
	@NotEmpty
	private String description;
	
}