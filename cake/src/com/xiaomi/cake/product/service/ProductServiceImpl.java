 package com.xiaomi.cake.product.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaomi.cake.entity.Cart;
import com.xiaomi.cake.entity.CartItem;
import com.xiaomi.cake.entity.Order;
import com.xiaomi.cake.entity.OrderItem;
import com.xiaomi.cake.entity.Product;
import com.xiaomi.cake.entity.User;
import com.xiaomi.cake.product.dao.ProductDaoImpl;

@Service
@Transactional(readOnly=true)
public class ProductServiceImpl {

	
	@Resource
	private ProductDaoImpl productDaoImpl;
	
	//前-生成订单
	public Order save(Cart c, User u) {

		Order o = new Order();
		Set<OrderItem> orderset = new HashSet<OrderItem>();
		orderset = o.getOrderitemSet();
		int sum = 0;
		//ordritem
		for (Map.Entry<Integer, CartItem> entry : c.container.entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue().getCount());
			OrderItem oi = new OrderItem();
			oi.setAmount(entry.getValue().getCount());
			oi.setProduct(entry.getValue().getProduct());
			oi.setPrice(entry.getValue().getProduct().getPrice());
			//oi.getOrder(entry.getValue());
//			oi.setId(entry.getValue().getProduct().getId());
			
//			oi.setOrder(o);
			//oi.setOrderid(o.getId());
			orderset.add(oi);
			sum += entry.getValue().getCount() * entry.getValue().getProduct().getPrice();
		}

		Date date = new Date();
		long times = date.getTime();// 时间戳
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(date);
		System.out.println(sum);

		System.err.println("sum"+sum);
		//order
		o.setOrderstate(0);
		o.setOrdercreatetime(date);
		o.setOrderpaytime(date);
		o.setPrice(sum);
		o.setOrderitemSet(orderset);
		//o.setId(2);
		this.productDaoImpl.saveOrder(o);
		return o;
	}

	
	//从购物车删除商品-批量删除
	public Cart deleteProducts(String checkedId,HttpSession session) {
		String[] result = checkedId.split(",");
		List list = new ArrayList<>();
		Cart cart = (Cart) session.getAttribute("cart");
		
		for (int i = 0; i < result.length; i++) {
			int id = Integer.parseInt(result[i]);	
			Product product = this.productDaoImpl.findById(id);
			cart.deleteCart(product);
		}
	
		Cart cart1 = (Cart) session.getAttribute("cart");
		
		Iterator i = cart1.container.values().iterator();
		while (i.hasNext()) {
			CartItem ci = (CartItem) i.next();
			list.add(ci);
		}
		
		session.setAttribute("cart", cart1);
		session.setAttribute("cartlist", list);
		return cart;

		// return productDaoImpl.falseDelete(checkedId.split(","));
	}
	
	
	
	//后台   商品添加
	public Product addProductBack(Product product){
		return this.productDaoImpl.saveProduct(product);
				
	}
	
	
	//分页查询数据
	public List<Product> findByPage(int num, int i) {
		return productDaoImpl.findByPage(num, i);
	}
	public int findByCount() {
		return productDaoImpl.findCountByPage();
	}
	
	
	//后-商品删除
	//修改商品
	public Product findProduct(Integer id){
		return this.productDaoImpl.findByIdBack(id);
	}	
	public void deleteProduct(Product product, int id) {
		System.out.print("ser快删啊");
		this.productDaoImpl.deleteByIdBack(product,id);
	}
	public Product updateProduct(Product product) {				
		return this.productDaoImpl.updateBack(product);
		
	}
	
	
	
	//后-添加商品
	public void addProduct(Product product){
		this.productDaoImpl.saveProductBack(product);
	}
	
	public List<Product> listProductsBack(){
		return new ProductDaoImpl().findById();
	}

	//前、后-查-商品列表
	public List<Product> find(){			
		return this.productDaoImpl.findById();
	}
	
	//后-查-订单列表
	public List<Order> findOrder(){			
		return this.productDaoImpl.findOrderById();
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


	//前-添加到购物车
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

	
	//查 前、后-商品列表
	public List<Product> listProducts(){
		return productDaoImpl.findAll();
	}
	
	

}
