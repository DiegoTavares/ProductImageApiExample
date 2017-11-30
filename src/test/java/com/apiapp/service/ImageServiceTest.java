package com.apiapp.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.apiapp.model.Image;
import com.apiapp.model.Product;

public class ImageServiceTest {
	
	
	public void setUp() {
		Product p1 = ProductService.saveOrUpdateProduct(new Product(1, "t1", "d1"));
		ImageService.saveOrUpdateImage(new Image(1, "t1", p1));
		ImageService.saveOrUpdateImage(new Image(2, "t2", p1));
		ImageService.saveOrUpdateImage(new Image(3, "t3", p1));
	}
	
	@Before
	public void clearDatabase() {
		SessionBuilder.truncate("Image");
		SessionBuilder.truncate("Product");
	}
	
	@Test
	public void getAllImagesTest() {
		assertEquals(0, ImageService.getAllImages().size());
		setUp();
		
		List<Image> images = ImageService.getAllImages();
		assertEquals(3, images.size());
	}
	
	@Test
	public void getImageTest() {
		setUp();
		
		assertNull(ImageService.getImage(4));
		Image i1 = ImageService.getImage(1);
		assertEquals(Integer.valueOf(1), i1.getId());
		assertEquals("t1", i1.getType());
		assertEquals(Integer.valueOf(1), i1.getProduct().getId());
	}
	
	@Test
	public void saveOrUpdateImageTest() {
		assertEquals(0, ImageService.getAllImages().size());
		Product p1 = ProductService.saveOrUpdateProduct(new Product(1, "t1", "d1"));
		Product p2 = ProductService.saveOrUpdateProduct(new Product(2, "t2", "d2"));
		Image i0 = new Image(1, "t1", p1);
		ImageService.saveOrUpdateImage(i0);
		Image i1 = ImageService.getImage(1);
		
		assertEquals(i0.getId(), i1.getId());
		assertEquals(i0.getType(), i1.getType());
		assertEquals(i0.getProduct().getId(), i1.getProduct().getId());
		i1.setType("t2");
		i1.setProduct(p2);
		
		ImageService.saveOrUpdateImage(i1);
		
		i1 = ImageService.getImage(1);
		assertEquals(i0.getId(), i1.getId());
		assertEquals("t2", i1.getType());
		assertEquals(p2.getId(), i1.getProduct().getId());
	}
	
	@Test
	public void deleteImageTest() {
		assertEquals(0, ImageService.getAllImages().size());
		setUp();
		ImageService.deleteImage(1);
		List<Image> images = ImageService.getAllImages();
	
		assertEquals(2, images.size());
		assertFalse(images.contains(new Image(1, "t1", null)));
	}
	
	@Test
	public void setProductTest() {
		Product p2 = ProductService.saveOrUpdateProduct(new Product(2, "t2", "d2"));
		assertEquals(0, ImageService.getAllImages().size());
		setUp();
		
		ImageService.setProduct(1, p2.getId());
		Image i1 = ImageService.getImage(1);
		assertEquals(p2.getId(), i1.getProduct().getId());
	}
}
