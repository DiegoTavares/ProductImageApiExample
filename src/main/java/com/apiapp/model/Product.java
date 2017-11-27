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

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    protected Set<Product> children = new HashSet<Product>();
    
    @OneToMany(mappedBy="product")
    protected Set<Image> images = new HashSet<Image>();
    
	public Product() {
		super();
	}

	public Product(int id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	/*
	 * Products will be compared using their id
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		return (o instanceof Product) && ((Product) o).getId().equals(this.getId());
	}
	
	/*
	 * Products will be hashed using theis id
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return getId();
	}
	
	// Getters & Setters
	
	@JsonIgnore
	public Set<Product> getChildren(){
		return this.children;
	}
	
	public void setChildren(Set<Product> products) {
		this.children = products;
	}
	
	@JsonIgnore
	public Set<Image> getImages() {
		return this.images;
	}
	
	public void setImages(Set<Image> images) {
		this.images = images;
	}
	
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
