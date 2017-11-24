package com.apiapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.apiapp.model.Product;
import com.apiapp.service.ProductService;

@Path("/products")
public class ProductController {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List getProducts() {
		List<Product> listOfProducts = ProductService.getAllProducts();
		
		return listOfProducts;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Product getProductById(@PathParam("id") int id) {
		return ProductService.getProduct(id);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Product addProduct(Product product) {
		return ProductService.saveOrUpdateProduct(product);
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Product updateProduct(Product product) {
		return ProductService.saveOrUpdateProduct(product);
	}
	
	@PUT
	@Path("/{id}/parent/{parent_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Product setParent(@PathParam("id") int id, @PathParam("parent_id") int parentId) {
		return ProductService.setParent(id, parentId);
	}
	
	@PUT
	@Path("/{id}/child/{parent_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Product setChild(@PathParam("id") int id, @PathParam("parent_id") int parentId) {
		return ProductService.setChild(id, parentId);
	}


	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteProduct(@PathParam("id") int id) {
		ProductService.deleteProduct(id);
	}

}
