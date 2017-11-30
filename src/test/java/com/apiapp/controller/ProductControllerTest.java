package com.apiapp.controller;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Before;
import org.junit.Test;

import com.apiapp.SetApplication;
import com.apiapp.model.Product;
import com.apiapp.service.SessionBuilder;

public class ProductControllerTest extends JerseyTest {

	private final int DEFAULT_ID = 1;
	private final String DEFAULT_NAME = "test 1";
	private final String DEFAULT_DESC = "desc 1";

	/**
	 * The handler used in the {@link SetResource}. We are going to use
	 * {@link Mockito} to mock this handler for some tests, to just check the API
	 * call itself.
	 */
	@Override
	protected Application configure() {
		return new SetApplication();
	}

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		SessionBuilder.truncate("Image");
		SessionBuilder.truncate("Product");
		target("/products").request().post(Entity.json(addProductRequest(DEFAULT_ID, DEFAULT_NAME, DEFAULT_DESC)));
	}

	protected Map<String, String> addProductRequest(int id, String name, String description) {
		Map<String, String> data = new HashMap<String, String>();
		data.put("id", String.valueOf(DEFAULT_ID));
		data.put("name", DEFAULT_NAME);
		data.put("description", DEFAULT_DESC);

		return data;
	}

//	@Test
//	public void addProductTest() {
//		Response response = target("/products").request().post(Entity.json(addProductRequest(10, "test 1", "desc 1")));
//
//		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
//
//		Product response2 = target("/products/10").request(MediaType.APPLICATION_JSON_TYPE).get(Product.class);
//		assertEquals(Integer.valueOf(10), response2.getId());
//		assertEquals("test 1", response2.getName());
//		assertEquals("desc 1", response2.getDescription());
//	}

	@Test
	public void getProductTest() {
		Product response = target("/products/" + DEFAULT_ID).request(MediaType.APPLICATION_JSON_TYPE).get()
				.readEntity(Product.class);
		assertEquals(Integer.valueOf(DEFAULT_ID), response.getId());
		assertEquals(DEFAULT_NAME, response.getName());
		assertEquals(DEFAULT_DESC, response.getDescription());
	}

//	@Test
//	public void getProductsTest() {
//		List<Product> products = target("/products").request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<List<Product>>(){});
//		
//		assertEquals(1, products.size());
//		
//		target("/products").request().post(Entity.json(addProductRequest(11, DEFAULT_NAME + 2, DEFAULT_DESC)));
//		target("/products").request().post(Entity.json(addProductRequest(12, DEFAULT_NAME + 3, DEFAULT_DESC)));
//		target("/products").request().post(Entity.json(addProductRequest(13, DEFAULT_NAME + 4, DEFAULT_DESC)));
//		
//		products = target("/products").request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<List<Product>>(){});
//		assertEquals(4, products.size());
//	}
}