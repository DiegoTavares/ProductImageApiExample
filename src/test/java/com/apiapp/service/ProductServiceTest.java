package com.apiapp.service;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.apiapp.model.Image;
import com.apiapp.model.Product;
import com.apiapp.model.ProductWithReferencesDecorator;

public class ProductServiceTest {

	public void SetUp() {
		Product p1 = ProductService.saveOrUpdateProduct(new Product(1, "n1", "d1"));
		Product p2 = ProductService.saveOrUpdateProduct(new Product(2, "n2", "d2"));
		p2.setParent(p1);
		ProductService.saveOrUpdateProduct(p2);
		ImageService.saveOrUpdateImage(new Image(1, "t1", p1));
		ImageService.saveOrUpdateImage(new Image(2, "t2", p1));
	}

	@Before
	public void clearDatabase() {
		SessionBuilder.truncate("Image");
		SessionBuilder.truncate("Product");
	}

	@Test
	public void getAllProductsCompleteTest() {
		List<ProductWithReferencesDecorator> products = ProductService.getAllProductsComplete();
		assertEquals((int) 0, products.size());
		SetUp();
		products = ProductService.getAllProductsComplete();

		assertEquals((int) 2, products.size());
		assertEquals((int) 2, products.get(0).getImagesComplete().size());
		assertEquals((int) 1, products.get(0).getChildrenComplete().size());
	}

	@Test
	public void getAllProductsTest() {
		List<Product> products = ProductService.getAllProducts();
		assertEquals((int) 0, products.size());
		SetUp();
		products = ProductService.getAllProducts();

		assertEquals((int) 2, products.size());
	}

	@Test
	public void getProductCompleteTest() {
		SetUp();
		ProductWithReferencesDecorator product = ProductService.getProductComplete(1);
		assertEquals(Integer.valueOf(1), product.getId());
		assertEquals("n1", product.getName());
		assertEquals("d1", product.getDescription());
		
		assertEquals((int) 1, product.getChildrenComplete().size());
		assertEquals((int) 2, product.getImagesComplete().size());
	}

	@Test
	public void getProductTest() {
		SetUp();
		Product product = ProductService.getProduct(1);
		assertEquals(Integer.valueOf(1), product.getId());
		assertEquals("n1", product.getName());
		assertEquals("d1", product.getDescription());
	}

	@Test
	public void getProductImagesTest() {
		SetUp();
		Set<Image> images = ProductService.getProductImages(1);
		assertEquals((int)2, images.size());
	}

	@Test
	public void getProductChildrenTest() {
		SetUp();
		Set<Product> products = ProductService.getProductChildren(1);
		assertEquals((int)1, products.size());
	}

	@Test
	public void saveOrUpdateProductTest() {
		List<Product> products = ProductService.getAllProducts();
		assertEquals((int) 0, products.size());
		
		ProductService.saveOrUpdateProduct(new Product(1, "n1", "d1"));
		Product product = ProductService.getProduct(1);
		assertEquals("n1", product.getName());
		
		product.setName("New Name");
		ProductService.saveOrUpdateProduct(product);
		product = ProductService.getProduct(1);
		assertEquals("New Name", product.getName());
		
	}

	@Test
	public void deleteProductTest() {
		SetUp();
		List<Product> products = ProductService.getAllProducts();
		assertEquals((int) 2, products.size());
		ProductService.deleteProduct(2);
		products = ProductService.getAllProducts();
		assertEquals((int) 1, products.size());
	}

	@Test
	public void setParentTest() {
		SetUp();
		ProductService.saveOrUpdateProduct(new Product(3, "n3", "d3"));
		
		ProductService.setParent(3, 1);
		Product p3 = ProductService.getProduct(3);
		
		assertEquals(Integer.valueOf(1), p3.getParent().getId());
	}

	@Test
	public void setChildTest() {
		SetUp();
		ProductService.saveOrUpdateProduct(new Product(3, "n3", "d3"));
		
		ProductService.setChild(1, 3);
		Product p3 = ProductService.getProduct(3);
		
		assertEquals(Integer.valueOf(1), p3.getParent().getId());
	}
}
