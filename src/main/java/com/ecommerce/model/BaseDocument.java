package com.ecommerce.model;

import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

@Data
public class BaseDocument {

	@Id
	private String id;
	
	@CreatedDate
	private DateTime created_at;
	
	@LastModifiedDate
	private DateTime updated_at;
}
