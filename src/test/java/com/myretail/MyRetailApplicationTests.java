package com.myretail;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.myretail.domain.Product;
import com.myretail.repositories.ProductRepository;
import com.myretail.service.ProductServiceImpl;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = MyRetailApplication.class)
public class MyRetailApplicationTests {
	
	@Mock
	private ProductRepository productRepository;
	
	@InjectMocks
	private ProductServiceImpl productService;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetProductList() throws Exception {
		List<Product> prodList = new ArrayList<Product>();
		Product p = createSampleProduct();
		prodList.add(p);
		Mockito.when(productRepository.findAll()).thenReturn(prodList);
		
		ResponseEntity<List<Product>> prodListResult = productService.listAll();
		Assert.assertEquals(1, prodListResult.getBody().size());
	}
	
	@Test
	public void testAddProduct() throws Exception {
		Product p = createSampleProduct();
		Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(p);
		
		ResponseEntity<Product> prodAddResult = productService.save(p);
		Assert.assertEquals(p.getId(), prodAddResult.getBody().getId());
	}
	
	@Test
	public void testGetProductById() throws Exception {
		Product p = createSampleProduct();
		Mockito.when(productRepository.findById(p.getId())).thenReturn(Optional.of(p));
		
		ResponseEntity<Product> prodListResult = productService.getById(p.getId());
		Assert.assertEquals(p.getId(), prodListResult.getBody().getId());
		Assert.assertEquals(p.getDescription(), prodListResult.getBody().getDescription());

	}
	
	@Test
	public void testUpdateProduct() throws Exception {
		Product p = createUpdatedProduct();
		Mockito.when(productRepository.save(p)).thenReturn(p);
		
		ResponseEntity<Product> prodUpdateResult = productService.update(p, p.getId());
		Assert.assertEquals(p.getCurrentPrice(), prodUpdateResult.getBody().getCurrentPrice());

	}
	
	private Product createSampleProduct() {
		Product p = new Product();
		p.setId("12345");
		p.setDescription("Test");
		p.setCurrentPrice("10.00");
		p.setCurrencyCode("USD");
		return p;
	}
	
	private Product createUpdatedProduct() {
		Product p = new Product();
		p.setId("12345");
		p.setDescription("Test");
		p.setCurrentPrice("11.00");
		p.setCurrencyCode("USD");
		return p;
	}

}
