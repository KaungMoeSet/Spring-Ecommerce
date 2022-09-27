package com.ecommerce.model;

import org.bson.types.Binary;

import lombok.Data;

@Data
public class Products extends BaseDocument {

	private String name;
	private Float price;
	private String category;
	private Binary img;
	private Status status;
}