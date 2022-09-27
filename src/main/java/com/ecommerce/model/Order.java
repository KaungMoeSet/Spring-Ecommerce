package com.ecommerce.model;

import lombok.Data;

@Data
public class Order extends BaseDocument {

	private String customer_name;
	private String customer_email;
	private String product_name;
	private Address customer_address;
	
	private int product_amount;
	private double total;
}
