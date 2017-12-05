 package com.xiaomi.cake.product.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaomi.cake.entity.Cart;
import com.xiaomi.cake.entity.CartItem;
import com.xiaomi.cake.entity.Product;
import com.xiaomi.cake.entity.User;
import com.xiaomi.cake.product.dao.ProductDaoImpl;

@Service
@Transactional(readOnly=true)
public class ProductServiceImpl {

	
	@Resource
	private ProductDaoImpl productDaoImpl;
	
	//后台   商品添加
	public Product addProductBack(Product product){
		return this.productDaoImpl.saveProductBack(product);
				
	}
	
	
	
	//后台 商品删除
	//修改商品
	public Product findProduct(Integer id){
		return this.productDaoImpl.findByIdBack(id);
	}	
	public void deleteProduct(Product product) {				
		this.productDaoImpl.deleteByIdBack(product);
	}
	
	
	
	//后台 添加商品
	public void addProduct(Product product){
		this.productDaoImpl.saveProductBack(product);
	}
	
	public List<Product> listProductsBack(){
		return new ProductDaoImpl().findById();
	}
	
	
	
	
	
	
	//后台 查
	public List<Product> find(){
			
		return this.productDaoImpl.findById();
	}
	//添加商品
	public Product addProduct(String name, int price){
		Product product=this.productDaoImpl.findByName(name);
		if(product==null){
			//不存在
			Product p=new Product();
			p.setName(name);
			p.setPrice(price);
			return this.productDaoImpl.saveProduct(p);
		}else{
			return null;
		}
				
	}
	
	
	
	//删除商品
//		public Cart deleteCart(int id, HttpSession session) {
//
//			Product product = this.productDaoImpl.findById(id);
//			if(product.getId()==null||product.getAmount()==0){
//				session.setAttribute("msg","商品不存在");
//				return null;
//			}
//			Cart cart = (Cart) session.getAttribute("cart");
//			List list = new ArrayList<>();
//			
//			cart.deleteCart(product);
//			Iterator i = cart.container.values().iterator();
//			while (i.hasNext()) {
//				CartItem ci = (CartItem) i.next();
//				list.add(ci);
//			}
//			session.setAttribute("cart", cart);
//			session.setAttribute("cartlist", list);
//			return cart;
//
//		}
	
	
	//分页
//	public void page(String pageNum,HttpSession session){
//		session.getAttribute(pageNum);
//		if(pageNum==null||pageNum.equals("")){
//			
//		}
//	}
	
	
	
	
	
	//添加到购物车
	public Cart addCart(int id, HttpSession session) {

		Product product = this.productDaoImpl.findById(id);

		Cart cart = (Cart) session.getAttribute("cart");//
		List list = new ArrayList<>();
		if (cart == null) {
			cart = new Cart();
		}
		cart.addCart(product);
		
		Iterator i = cart.container.values().iterator();//遍历Map,把map里的的东西放在list里
		while (i.hasNext()) {
			CartItem ci = (CartItem) i.next();
			list.add(ci);
		}
		
		session.setAttribute("cart", cart);
		session.setAttribute("cartlist", list);//
		return cart;

	}

	
	//查 前后台
	public List<Product> listProducts(){
		return productDaoImpl.findAll();
	}

}
