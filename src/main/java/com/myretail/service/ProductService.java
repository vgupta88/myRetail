package com.myretail.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.myretail.domain.Product;

public interface ProductService {

	ResponseEntity<List<Product>> listAll();

	ResponseEntity<Product> getById(String id);

	ResponseEntity<Product> save(Product product);
	
	ResponseEntity<Product> update(Product product, String id);

	ResponseEntity<Product> delete(String id);
	
}
