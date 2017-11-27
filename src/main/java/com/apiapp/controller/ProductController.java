package com.apiapp.controller;

import java.util.List;
import java.util.Set;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.apiapp.model.Image;
import com.apiapp.model.Product;
import com.apiapp.model.ProductWithReferencesDecorator;
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
	@Path("/full")
	@Produces(MediaType.APPLICATION_JSON)
	public List getProductsComplete() {
		List<Product> listOfProducts = ProductService.getAllProducts();
		
		return listOfProducts;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Product getProductById(@PathParam("id") int id) {
		return ProductService.getProduct(id);
	}

	@GET
	@Path("/{id}/full")
	@Produces(MediaType.APPLICATION_JSON)
	public ProductWithReferencesDecorator getProductCompleteById(@PathParam("id") int id) {
		return ProductService.getProductComplete(id);
	}
	
	@GET
	@Path("/{product_id}/images")
	@Produces(MediaType.APPLICATION_JSON)
	public Set<Image> getProductImages(@PathParam("product_id") int productId) {
		return ProductService.getProductImages(productId);
	}

	@GET
	@Path("/{product_id}/children")
	@Produces(MediaType.APPLICATION_JSON)
	public Set<Product> getProductChildren(@PathParam("product_id") int productId) {
		return ProductService.getProductChildren(productId);
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
