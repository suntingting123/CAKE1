package com.xiaomi.cake.user.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xiaomi.cake.entity.Page;
import com.xiaomi.cake.entity.Product;
import com.xiaomi.cake.entity.User;
import com.xiaomi.cake.user.service.UserServiceImpl;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserServiceImpl userServiceImpl;
	//邮箱激活
	
	//后-用户删除
	@RequestMapping("delete")
	public String deletelist(HttpSession session,@RequestParam("id") int id){
		User user=this.userServiceImpl.findUser(id);
		this.userServiceImpl.deleteUser(user,id);
		//this.userSe
			
		//this.userServiceImpl.find();
		//session.setAttribute("list", list);
		System.out.println("con删除用户"); 
		return "forward:/user/get";
	}
	
	
	
	
	//后台用户列表 查
	@RequestMapping("/get")
	public String list(HttpSession session,HttpServletRequest request){
		String num = request.getParameter("pageNum");//获取用户要看的页码
		int pageNumber = 1;
		if(num!=null){
			pageNumber = Integer.parseInt(num);
		}
		
		List<User> list=this.userServiceImpl.findByPage(pageNumber, 2);
		Page page = new Page(pageNumber,2);
		page.setList(list);
		page.setTotalCount(this.userServiceImpl.findByCount());
		session.setAttribute("list", list);
		session.setAttribute("page", page);

		return "/admin/userlist";
	}

	// 登录
	@RequestMapping("/login")
	public String login(@RequestParam("name") String name, @RequestParam("password") String password, Model model,
			HttpSession session) {
		User user = this.userServiceImpl.login(name, password);
		
		if (user != null) {
			session.setAttribute("user", user);
			//return "forward:/product/list";
			return "reallyindex";
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
			session.setAttribute("errorinfo", "邮件不能为空！");
			return "register";
		}else{
			if (u != null) {
				session.setAttribute("errorinfo", "恭喜用户 "+u.getName()+"注册成功");
				session.setAttribute("user", u);
				return "reallyindex";

			} else {
				model.addAttribute("errorinfo", "该邮件已经被注册！");
				return "register";
			}
		}
	}
	
	
	
	//AJAX进行异步校验用户名的执行方法 --啥啊，看不懂
	
	@RequestMapping(value="/findajax", method=RequestMethod.POST)
	public void findByName(HttpServletRequest request,HttpSession session,@RequestParam("email") String email,HttpServletResponse response,Model model) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		User user = this.userServiceImpl.ajaxEmail(email);
		System.out.print("con");
		
		if (user != null) {
			response.getWriter().write("<font color='red' size='6' face='楷体'>该邮箱已经被注册</font>");
		} else {
			response.getWriter().write("<font color='red' size='6' face='楷体'>邮箱合理</font>");
		}
		
	}
}