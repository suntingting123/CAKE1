package com.xiaomi.cake.producttype.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xiaomi.cake.entity.ProductType;
import com.xiaomi.cake.producttype.service.ProductTypeServiceImpl;

@Controller
@RequestMapping("/producttype")
public class ProductTypeController {
	
	@Resource
	private ProductTypeServiceImpl productTypeServiceImpl;
	
	//查 商品种类
	@RequestMapping(value="/listtype", method=RequestMethod.GET)
	public void getType(HttpServletRequest request,HttpSession session,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		System.out.print("con");
		List<ProductType> listtype=this.productTypeServiceImpl.listProductType();
		session.setAttribute("listtype", listtype);
		if (listtype != null) {
			response.getWriter().write("<font color='red'>该邮箱已被注册</font>");
		} else {
			response.getWriter().write("<font color='red'>邮箱合理</font>");

		}
		
	}


}
