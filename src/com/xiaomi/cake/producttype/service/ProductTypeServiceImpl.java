package com.xiaomi.cake.producttype.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xiaomi.cake.entity.ProductType;
import com.xiaomi.cake.producttype.dao.ProductTypeDaoImpl;



@Service
public class ProductTypeServiceImpl {

	public List<ProductType> listTypes(){
		ProductTypeDaoImpl pdi=new ProductTypeDaoImpl();
		return pdi.findAll();
	}
}
