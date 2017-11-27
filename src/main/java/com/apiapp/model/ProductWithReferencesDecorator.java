package com.apiapp.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductWithReferencesDecorator extends ProductDecorator {

	public ProductWithReferencesDecorator(Product product) {
		super(product);		
	}
	
	@JsonProperty("children")
	public Set<Product> getChildrenComplete() {
		return super.getChildren();
	}
	
	@JsonProperty("images")
	public Set<Image> getImagesComplete() {
		return super.getImages();
	}

	public static List<ProductWithReferencesDecorator> fromProductsList(List<Product> productsList) {
		Set<ProductWithReferencesDecorator> out = new HashSet<ProductWithReferencesDecorator>();
		for(Iterator<Product> p = productsList.iterator(); p.hasNext();) {
			Product prod = p.next();
			System.out.println("_____________" + prod.getId());
			out.add(new ProductWithReferencesDecorator(prod));
		}

		return new ArrayList<ProductWithReferencesDecorator>(out);
	}
}
