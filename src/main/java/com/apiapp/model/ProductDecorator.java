package com.apiapp.model;

import java.util.Set;

abstract class ProductDecorator extends Product {
	protected Product toBeDecorated;
	
	public ProductDecorator(Product product) {
		this.toBeDecorated = product;
	}
	
	@Override
	public Set<Product> getChildren(){
		return this.toBeDecorated.children;
	}
	
	@Override
	public void setChildren(Set<Product> products) {
		this.toBeDecorated.setChildren(products);
	}
	
	@Override
	public Set<Image> getImages() {
		return this.toBeDecorated.images;
	}
	
	@Override
	public void setImages(Set<Image> images) {
		this.toBeDecorated.setImages(images);
	}
	
	@Override
	public String getDescription() {
		return this.toBeDecorated.getDescription();
	}

	@Override
	public void setDescription(String description) {
		this.toBeDecorated.setDescription(description);
	}

	@Override
	public Product getParent() {
		return this.toBeDecorated.getParent();
	}

	@Override
	public void setParent(Product parent) {
		this.toBeDecorated.setParent(parent);
	}
	
	@Override
	public Integer getId() {
		return this.toBeDecorated.getId();
	}

	@Override
	public void setId(Integer id) {
		this.toBeDecorated.setId(id);
	}

	@Override
	public String getName() {
		return this.toBeDecorated.getName();
	}

	@Override
	public void setName(String name) {
		this.toBeDecorated.setName(name);
	}
}
