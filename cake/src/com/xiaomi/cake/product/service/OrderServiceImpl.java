package com.xiaomi.cake.product.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaomi.cake.entity.Order;
import com.xiaomi.cake.entity.Product;
import com.xiaomi.cake.entity.User;
import com.xiaomi.cake.product.dao.OrderDaoImpl;

@Service
@Transactional(readOnly=true)
public class OrderServiceImpl {
	
	@Resource
	private OrderDaoImpl orderDaoImpl;
	//后-用户删除
	public Order findOrder(Integer id){
		return this.orderDaoImpl.findByIdBack(id);
	}	
	public void deleteOrder(Order order, int id) {				
		this.orderDaoImpl.deleteByIdBack(order,id);
	}
	
	//分页查询数据
	public List<Order> findByPage(int num, int i) {
		System.out.print("oooser");
		return orderDaoImpl.findByPage(num, i);
	}
	public int findByCount() {
		return orderDaoImpl.findCountByPage();
	}
		
}
