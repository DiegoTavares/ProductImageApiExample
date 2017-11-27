package com.apiapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "image")
public class Image {

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "type")
	private String type;

	@ManyToOne
    @JoinColumn(name="product_id", nullable=false)
    private Product product;

	public Image() {
		super();
	}

	public Image(int id, String type) {
		super();
		this.id = id;
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

//	@JsonIgnore
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
