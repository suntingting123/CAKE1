package com.xiaomi.cake.product.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xiaomi.cake.entity.Order;
import com.xiaomi.cake.entity.Page;
import com.xiaomi.cake.entity.Product;
import com.xiaomi.cake.entity.User;
import com.xiaomi.cake.product.service.OrderServiceImpl;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Resource
	private OrderServiceImpl orderServiceImpl;
	//后-订单删除
	@RequestMapping("delete")
	public String deletelist(HttpSession session,@RequestParam("id") int id){
		Order order=this.orderServiceImpl.findOrder(id);
		this.orderServiceImpl.deleteOrder(order,id);
		return "forward:/order/get";
	}
	//后-查-订单列表
	@RequestMapping("/get")
	public String listgoods(Order order,HttpSession session,HttpServletRequest request){
		String num = request.getParameter("pageNum");//获取用户要看的页码
		int pageNumber = 1;
		if(num!=null){
			pageNumber = Integer.parseInt(num);
		}	
		System.out.print("ooocon");
		List<Order> list=this.orderServiceImpl.findByPage(pageNumber, 2);
		Page page = new Page(pageNumber,2);
		page.setList(list);
		page.setTotalCount(this.orderServiceImpl.findByCount());
		session.setAttribute("list", list);
		session.setAttribute("page", page);
		System.out.print(list);
		return "/admin/orderlist";
	}

}
