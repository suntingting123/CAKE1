package com.xiaomi.cake.entity;

import com.xiaomi.cake.entity.Product;

public class CartItem {
	private Product product;
	private int count;
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	

}
