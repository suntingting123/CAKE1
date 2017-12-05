package com.xiaomi.cake.user.controller;

import java.util.List;

import javax.annotation.Resource;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.xiaomi.cake.entity.User;
import com.xiaomi.cake.user.service.UserServiceImpl;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserServiceImpl userServiceImpl;

	//后台 用户删除
	@RequestMapping("/delete")
	public String deletelist(HttpSession session,@RequestParam("id") int id){
		User user=this.userServiceImpl.findUser(id);
		this.userServiceImpl.deleteUser(user);
		//this.userServiceImpl.find();
		//session.setAttribute("list", list);
		System.out.println("con"); 
		return "forward:/user/get";
	}
	
	
//	@RequestMapping(value="/add", method=RequestMethod.GET)
//	public String toAdd(Model model){
//		List<ProductType> list=this.productTypeServiceImpl.listTypes();
//		model.addAttribute("list", list);
//		return "form";
//	}
//	
//	@RequestMapping(value="/add", method=RequestMethod.POST)
//	public String add(Product p){
//		this.productServiceImpl.addProduct(p);
//		return "redirect:/product/add";
//	}
	
	
	
	//后台用户列表 查
	@RequestMapping("/get")
	public String list(HttpSession session){
		List<User> list=this.userServiceImpl.find();
		session.setAttribute("list", list);
		return "/admin/userback";
	}

	// 登录
	@RequestMapping("/login")
	public String login(@RequestParam("name") String name, @RequestParam("password") String password, Model model,
			HttpSession session) {
		User user = this.userServiceImpl.login(name, password);
		if (user != null) {
			session.setAttribute("user", user);
			return "forward:/product/list";
		} else {
			model.addAttribute("errorinfo", "您的账号密码不正确！");
			return "index";
		}
		// this.productController.list(model);
	}

	// 注册
	@RequestMapping("/regist")
	public String registUser(@RequestParam("name") String name, 
			@RequestParam("email") String email,@RequestParam("password") String password,
			Model model,HttpSession session) {
		User u = this.userServiceImpl.registUser(email,name,password);
		if(email.isEmpty()||name.isEmpty()){
			model.addAttribute("errorinfo", "用户名不能为空！");
			return "register";
		}else{
			if (u != null) {
				session.setAttribute("errorinfo", "恭喜"+u.getName()+"注册成功");
				session.setAttribute("user", u);
				return "index";

			} else {
				model.addAttribute("errorinfo", "该用户名已存在已存在！");
				return "register";
			}
		}
	}
	
	
	
	//AJAX进行异步校验用户名的执行方法 --啥啊，看不懂
	
	@RequestMapping(value="/findajax", method=RequestMethod.GET)
	public String findByName(HttpSession session,@RequestParam("name") String name){
	 //调用service进行查询
     User user = (User) userServiceImpl.find();
     //获得response对象向页面输出:
     
     //HttpServletResponse writer = ServletActionContext.getResponse();//
     
     //判断
     if(user != null){
    	 //查询到该用户：用户名已存在
    	 session.setAttribute("errorname", "用户名已存在");
    	 //out.getWriter().println("<font color='red'>用户名已存在</font>");
     }else{
    	 //没查询到该用户，用户名可以使用
    	 session.setAttribute("errorname", "用户名可以使用");
    	 //response.getWriter().println("<font color='green'>用户名可以使用</font>");
     }
     return null;
	}

	

}
