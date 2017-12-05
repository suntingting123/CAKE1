package com.xiaomi.cake.product.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xiaomi.cake.entity.Cart;
import com.xiaomi.cake.entity.Product;
import com.xiaomi.cake.entity.ProductType;
import com.xiaomi.cake.entity.User;
import com.xiaomi.cake.product.service.ProductServiceImpl;
import com.xiaomi.cake.producttype.service.ProductTypeServiceImpl;


@Controller
@RequestMapping("/product")
public class ProductController {
//	@Resource//表示注入
//	private ProductTypeServiceImpl productTypeServiceImpl;
	@Resource
	private ProductServiceImpl productServiceImpl;
	//修改商品
	@RequestMapping("/edit")
	public String edit(@RequestParam("id") int id,Model model){
		Product product=this.productServiceImpl.findProduct(id);
		model.addAttribute("product", product);
		return "/admin/editproduct";
	}
	
	@RequestMapping("updateback")
	public String updateBack(Product product,@RequestParam("name") int name,@RequestParam("price") int price) {

		this.productServiceImpl.addProductBack(product);
		System.out.print("con");
		return "redirect:/product/get";
	}
	//后台商品删除
	@RequestMapping("delete")
	public String deletelist(HttpSession session,@RequestParam("id") int id){
		Product product=this.productServiceImpl.findProduct(id);
		this.productServiceImpl.deleteProduct(product);
		return "forward:/product/get";
	}
	
	//后台 添加商品
	@RequestMapping("addback")//没进来，但是跳转了
	public String addBack(Product product) {
		this.productServiceImpl.addProductBack(product);
		System.out.print("con");
		return "redirect:/product/get";
	}
//	@RequestMapping(value="/add", method=RequestMethod.GET)
//	public String toAdd(Model model){
//		List<ProductType> list=this.productTypeServiceImpl.listTypes();
//		model.addAttribute("list", list);
//		return "/admin/form";
//	}	
//	@RequestMapping(value="/add", method=RequestMethod.POST)
//	public String add(Product p){
//		this.productServiceImpl.addProduct(p);
//		return "redirect:/product/add";
//	}
	
	// 注册
		@RequestMapping("add")
		public String add(@RequestParam("name") String name,Model model,HttpSession session,@RequestParam("price") int price) {
			Product p = this.productServiceImpl.addProduct(name,price);
			if(name.isEmpty()){
				model.addAttribute("errorinfo", "用户名不能为空！");
				return "/admin/goodsback";
			}else{
				if (p != null) {
					//session.setAttribute("errorinfo", "恭喜"+u.getName()+"注册成功");
					//session.setAttribute("user", u);
					return "/admin/goodsback";

				} else {
					model.addAttribute("errorinfo", "商品已存在！");
					return "register";
				}
			}
			

		}
	//查 后台商品列表
	@RequestMapping("/get")
	public String listgoods(HttpSession session){
		List<Product> list=this.productServiceImpl.find();
		session.setAttribute("list", list);
		
		return "/admin/goodsback";
	}
//	//删除购物车商品
//	@RequestMapping("/deleteCart")
//	public String delete(@RequestParam("id") int id,HttpSession session,Model model){
//		System.out.print(id);
//		Cart cart = this.productServiceImpl.deleteCart(id, session);
//		return "checkout";
//	}

	//往购物车添加商品
	@RequestMapping("/addCart")
	public String showCart(@RequestParam("id") int id,HttpSession session,Model model){
		System.out.print(id);
		Cart cart = this.productServiceImpl.addCart(id, session);
		return "products";
	}


	
	//查
	@RequestMapping("/list")
	public String list(HttpSession session){
		List<Product> list=this.productServiceImpl.listProducts();
		session.setAttribute("list", list);
		return "products";
	}
		
	

}
