package com.xiaomi.cake.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="producttype")
public class ProductType implements java.io.Serializable{

	private Integer id;
	private String name;
	//private List orderList=new ArrayList<Product>();
	private Set<Product> productSet=new HashSet<Product>();
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	@OneToMany(mappedBy="producttype",targetEntity=Product.class,cascade=CascadeType.ALL)
	public Set<Product> getProductSet() {
		return productSet;
	}
	/**
	 * @param productSet the productSet to set
	 */
	public void setProductSet(Set<Product> productSet) {
		this.productSet = productSet;
	}

	
}
