package com.xiaomi.cake.entity;

import java.util.HashMap;
import java.util.Map;

import com.xiaomi.cake.entity.Product;

public class Cart {
	public int total;
	
	public Map<Integer, CartItem> container=new HashMap<Integer, CartItem>();
	public Map<Integer, CartItem> getContainer() {
		return container;
	}
	public void setContainer(Map<Integer, CartItem> container) {
		this.container = container;
	}
    //添加商品到购物车
	public void addCart(Product pro){
		if(container.containsKey(pro.getId())){
			CartItem ci=container.get(pro.getId());
			ci.setCount(ci.getCount()+1);
		}else{
			CartItem ci=new CartItem();
			ci.setProduct(pro);
			ci.setCount(1);
			container.put(pro.getId(), ci);
		}
	}
	
	

	


	//删除购物车商品
	public void deleteCart(Product pro) {

		CartItem ci = container.get(pro.getId());
		/*
		 * if(count>1){ CartItem ci=container.get(pro.getProductid());
		 * ci.setCount(ci.getCount()-1); }else if(count == 1){
		 * container.remove(container.containsKey(pro.getProductid())); }
		 */
		int count = container.get(ci.getProduct().getId()).getCount();
		System.err.println(container.containsKey(ci.getProduct().getId()));
		Integer mykey = 0;
		for (Integer key : container.keySet()) {
			System.out.println("key" + key);
			if (ci == container.get(key)) {
				System.err.println("id" + ci.getProduct().getId());
				System.out.println("here");
				mykey = key;
			}
		}
		container.remove(mykey);
		
	}



	
}