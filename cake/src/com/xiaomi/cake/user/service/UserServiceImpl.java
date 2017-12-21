package com.xiaomi.cake.user.service;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaomi.cake.entity.Product;
import com.xiaomi.cake.entity.User;
import com.xiaomi.cake.user.dao.UserDaoImpl;

@Service
@Transactional(readOnly=true)
public class UserServiceImpl {

	@Resource
	private UserDaoImpl userDaoImpl;
	//分页查询数据
	public List<User> findByPage(int num, int i) {
		return userDaoImpl.findByPage(num, i);
	}
	public int findByCount() {
		return userDaoImpl.findCountByPage();
	}
	//删除 用户删除
	public User findUser(Integer id){
		return this.userDaoImpl.findById(id);
	}	
	public void deleteUser(User user, int id) {	
		System.out.println("ser删除用户"); 
		this.userDaoImpl.deleteById(user,id);
	}
	
	
	//查 调用的是id（后台）用户列表 和ajax
	public List<User> find(){	
		return this.userDaoImpl.findById();
	}
	
	
	//前台
	//登录
	public User login(String name, String password){
		
		User user=this.userDaoImpl.findByName(name);
		if(user!=null){
			if(user.getPassword().equals(password)){
				return user;
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
	
	//ajax
	public User ajaxEmail(String email){
		
		User user=this.userDaoImpl.findByEmail(email);
		System.out.print("ser");
		if(user!=null){
			return user;
		}else{
			return null;
		}
		
	}
	
	
	
	//注册
	public User registUser(String email,String name,String password){
		User user=this.userDaoImpl.findByEmail(email);
		if(user==null){
			//不存在
			User u=new User();
			u.setEmail(email);
			u.setName(name);
			u.setPassword(password);
			return this.userDaoImpl.saveUser(u);
		}else{
			return null;
		}
				
	}
	
	
}
