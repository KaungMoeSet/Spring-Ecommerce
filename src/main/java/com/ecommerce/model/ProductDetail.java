package com.ecommerce.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("product_detail")
public class ProductDetail extends BaseDocument {
	
	@NotNull
	private Date date;

	private byte[] image;

	private List<Color> colors;
	
	@NotBlank
	@Size(max = 140)
	private String description;
}
