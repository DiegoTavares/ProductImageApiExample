package com.apiapp.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import com.apiapp.service.SessionBuilder;

public class ImageTest {
	
	@Before
	public void clearDatabase() {
		SessionBuilder.truncate("Image");
		SessionBuilder.truncate("Product");
	}
	
	@Test
	/**
	 * Images shall be compared by their id
	 */
	public void equalsTest() {
		Product p1 = new Product(1, "Test 1", "Desc 1");
		Image i1 = new Image(1, "t1", p1);
		Image i2 = new Image(1, "t2", p1);
		Image i3 = new Image(1, "t3", p1);
		Image i4 = new Image(1, "t4", p1);
		Image i5 = new Image(2, "t5", p1);
		
		assertEquals(i1, i2);
		assertEquals(i1, i3);
		assertEquals(i1, i4);
		assertFalse(i1.equals(i5));
		
	}
}
