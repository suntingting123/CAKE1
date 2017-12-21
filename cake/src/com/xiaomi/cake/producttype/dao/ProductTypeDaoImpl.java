package com.xiaomi.cake.producttype.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.xiaomi.cake.entity.ProductType;


@Repository
public class ProductTypeDaoImpl {
	@Resource
	private SessionFactory sessionFactory;
	//前-查-把数据库里的商品类型放在products.jsp里-查询全部，是一个list
	//后-添加商品或者修改商品时加载商品类型
	@SuppressWarnings("unchecked")
	public List<ProductType> findAllType(){		 
		Session session=this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from ProductType");  
		// addEntity 将查询结果封装到指 lass);  	          
		List<ProductType> listtype = query.list();            
		System.out.println(listtype);  
		System.out.println("typedao");  
		//提交事务  
		
		return listtype;
	}	

}
