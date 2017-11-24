package com.apiapp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.apiapp.service.ProductService;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;
	
	@ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="parent_id")
    private Product parent;

    @OneToMany(mappedBy="parent")
    private Set<Product> childProducts = new HashSet<Product>();
    
    @OneToMany(mappedBy="product")
    private Set<Image> images;

//    private int parent_id;
    
	public Product() {
		super();
	}

	public Product(int id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
//	public Set<Product> getChildProducts(){
//		return this.childProducts;
//	}
//	
//	public void setChildProducts(Set<Product> products) {
//		this.childProducts = products;
//	}
	
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Product getParent() {
		return parent;
	}

	public void setParent(Product parent) {
		this.parent = parent;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
