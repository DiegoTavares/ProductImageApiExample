package com.apiapp.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.apiapp.service.SessionBuilder;

public class ProductTest {

	@Before
	public void clearDatabase() {
		SessionBuilder.truncate("Image");
		SessionBuilder.truncate("Product");
	}
	
	@Test
	/**
	 * Products shall be compared by their id
	 */
	public void equalsTest() {
		Product p1 = new Product(1, "Test 1", "Desc 1");
		Product p2 = new Product(1, "Test 1", "Desc 1");
		Product p3 = new Product(1, "Test 2", "Desc 1");
		Product p4 = new Product(1, "Test 1", "Desc 2");
		Product p5 = new Product(2, "Test 1", "Desc 1");
		
		assertEquals(p1, p2);
		assertEquals(p1, p3);
		assertEquals(p1, p4);
		assertFalse(p1.equals(p5));
		
	}
	
	@Test
	public void hashCodeTest() {
		Product p1 = new Product(1, "Test 1", "Desc 1");
		assertEquals(p1.hashCode(), 1);
	}
}
