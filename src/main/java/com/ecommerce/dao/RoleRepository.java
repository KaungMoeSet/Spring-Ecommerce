package com.ecommerce.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.ERole;
import com.ecommerce.model.Role;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {

	Optional<Role> findByName(ERole name);
}
