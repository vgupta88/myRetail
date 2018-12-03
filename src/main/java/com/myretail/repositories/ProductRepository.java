package com.myretail.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.myretail.domain.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

}
