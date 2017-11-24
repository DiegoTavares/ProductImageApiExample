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

import com.apiapp.model.Image;
import com.apiapp.model.Product;
import com.apiapp.service.ImageService;
import com.apiapp.service.ProductService;

@Path("/images")
public class ImageController {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List getImages() {
		List<Image> listOfImages = ImageService.getAllImages();
		
		return listOfImages;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Image getImageById(@PathParam("id") int id) {
		return ImageService.getImage(id);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Image addImage(Image image) {
		return ImageService.saveOrUpdateImage(image);
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Image updateImage(Image image) {
		return ImageService.saveOrUpdateImage(image);
	}
	
	@PUT
	@Path("/{image_id}/product/{product_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Image setProduct(@PathParam("image_id") int imageId, @PathParam("product_id") int productId) {
		return ImageService.setProduct(imageId, productId);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteImage(@PathParam("id") int id) {
		ImageService.deleteImage(id);
	}

}
