package com.myretail.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.myretail.domain.Product;
import com.myretail.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Override
	public ResponseEntity<List<Product>> listAll() {
		List<Product> prodList = productRepository.findAll();
		ResponseEntity<List<Product>> respEntity = new ResponseEntity<List<Product>>(prodList, HttpStatus.OK);
		return respEntity;
	}

	@Override
	public ResponseEntity<Product> getById(String id) {
		Optional<Product> savedProduct = productRepository.findById(id);
		ResponseEntity<Product> respEntity = null;
		if(savedProduct.isPresent()) 
			respEntity = new ResponseEntity<Product>(savedProduct.get(), HttpStatus.OK);
		else
			respEntity = new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);
		return respEntity;
	}

	@Override
	public ResponseEntity<Product> save(Product product) {
		Product savedProduct = productRepository.save(product);
		ResponseEntity<Product> respEntity = new ResponseEntity<Product>(savedProduct, HttpStatus.OK);
		return respEntity;
	}

	@Override
	public ResponseEntity<Product> update(Product product, String id) {
		Product updatedProduct = productRepository.save(product);
		ResponseEntity<Product> respEntity = new ResponseEntity<Product>(updatedProduct, HttpStatus.OK);
		return respEntity;
	}

	@Override
	public ResponseEntity<Product> delete(String id) {
		productRepository.deleteById(id);
		ResponseEntity<Product> respEntity = new ResponseEntity<Product>(HttpStatus.OK);
		return respEntity;
	}


}
