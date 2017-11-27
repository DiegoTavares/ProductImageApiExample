package com.apiapp.controller;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

public class ProductControllerTest extends JerseyTest {
	@Override
    protected Application configure() {
        return new ResourceConfig(ProductController.class);
    }
	
	@Test
	public void getProductTest() {
		String response = target("products/10").request().get(String.class);
		
		Assert.assertEquals(response, "");
	}
}
