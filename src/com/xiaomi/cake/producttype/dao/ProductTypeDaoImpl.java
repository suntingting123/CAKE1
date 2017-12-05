package com.xiaomi.cake.producttype.dao;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.xiaomi.cake.entity.ProductType;


@Repository
public class ProductTypeDaoImpl {
	@Resource
	private SessionFactory sessionFactory;

	public List<ProductType> findAll(){
		List<ProductType> list=new ArrayList<ProductType>();
		ProductType producttype = (ProductType)this.sessionFactory.getCurrentSession().
				createQuery("from Product").uniqueResult();
		return list;
	}

}
