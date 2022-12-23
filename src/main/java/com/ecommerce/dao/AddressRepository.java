package com.ecommerce.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.Address;

@Repository
public interface AddressRepository extends ReactiveMongoRepository<Address, String> {

}
