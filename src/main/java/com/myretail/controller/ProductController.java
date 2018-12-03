package com.myretail.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myretail.domain.Product;
import com.myretail.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/products")
public class ProductController {
	
	private ProductService productService;
	
	@Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
	
	@GetMapping()
	public ResponseEntity<List<Product>> showProducts() {
		return productService.listAll();
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> showProduct(@PathVariable String id) {
		return productService.getById(id);	
	}

	@PostMapping()
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		return productService.save(product);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable String id) {
		return productService.update(product, id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable String id) {
		return productService.delete(id);
	}
}
