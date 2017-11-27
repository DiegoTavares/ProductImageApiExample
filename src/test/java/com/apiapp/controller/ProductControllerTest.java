package com.apiapp.controller;

import java.util.Set;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;

import org.glassfish.jersey.test.JerseyTest;

import com.apiapp.SetApplication;
public class ProductControllerTest extends JerseyTest {

	/**
	 * Used to specify the format expected for the return type of the add call.
	 */
	private final GenericType<Boolean> BOOLEAN_RETURN_TYPE=new GenericType<Boolean>(){};
	private final GenericType<Set<String>> STRING_SET_RETURN_TYPE=new GenericType<Set<String>>(){};

	/**
	 * The handler used in the {@link SetResource}. We are going to use {@link Mockito} to mock this handler for some
	 * tests, to just check the API call itself.
	 */
	@Override
	protected Application configure() {
		return new SetApplication();
	}
}