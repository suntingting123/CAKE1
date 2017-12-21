package com.xiaomi.cake.producttype.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaomi.cake.entity.ProductType;
import com.xiaomi.cake.producttype.dao.ProductTypeDaoImpl;

@Service
public class ProductTypeServiceImpl {
	
	@Resource
	private ProductTypeDaoImpl productTypeDaoImpl;
	
	//前-查-把数据库里的商品类型放在products.jsp里-查询全部，是一个list
	//后-添加商品或者修改商品时加载商品类型-在productController里调用
	public List<ProductType> listProductType(){
		System.out.println("ser");
		return productTypeDaoImpl.findAllType();
		  
	}
}
