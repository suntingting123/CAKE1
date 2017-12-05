package com.xiaomi.cake.product.dao;

import java.util.List;
import javax.annotation.Resource;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.xiaomi.cake.entity.Product;
import com.xiaomi.cake.entity.User;

@Repository
public class ProductDaoImpl {

	@Resource
	private SessionFactory sessionFactory;
	//后台 商品删除、修改商品
	public Product findByIdBack(int id){	
		Product product = (Product)this.sessionFactory.openSession().
				createQuery("from Product where id = ?").setParameter(0, id).uniqueResult();
		return product;
	}
	
	public void deleteByIdBack(Product product){
 
		Session session=sessionFactory.openSession();
		Transaction transtion=session.beginTransaction();
		session.delete(product);			
		transtion.commit();
		session.close();
	
		
	}
	
	
	
	//后台 添加商品 (一个添加，一个查询)
	public Product saveProductBack(Product product){
		Session session=sessionFactory.openSession();
		Transaction transtion=session.beginTransaction();
		session.save(product);			
		transtion.commit();
		session.close();
		//this.sessionFactory.getCurrentSession().save(user);
		return product;
	}
	
	//查 前台、后台
	public List<Product> findById(){
		
			 
	        //获得session  
	     Session session = sessionFactory.openSession();  
	        //打开事务  
	     Transaction ts = session.beginTransaction();
	        //原生的Sql查询  
	     SQLQuery query = session.createSQLQuery("select * from product ");  
	        // addEntity 将查询结果封装到指定对象中  
	      query.addEntity(Product.class);  	          
	      List<Product> list = query.list();            
	      System.out.println(list);            
	        /*List<Object[]> list = query.list(); 
	         
	        for(Object[] objs : list){ 
	            System.out.println(Arrays.toString(objs)); 
	        }*/
	        //提交事务  
	       ts.commit();        
	        //关闭资源  
	       session.close(); 
	       return list;

	}
	
	
	
	// 前台 添加商品
	public Product saveProduct(Product product){
		Session session=sessionFactory.openSession();
		Transaction transtion=session.beginTransaction();
		session.save(product);			
		transtion.commit();
		session.close();
		//this.sessionFactory.getCurrentSession().save(user);
		return product;
	}
	public Product findByName(String name){
		//return super.get(name);
		
		Product product = (Product)this.sessionFactory.openSession().
				createQuery("from User where name = ?").setParameter(0, name).uniqueResult();
		return product;

	}
	
	
	
	
	
	
	//删除商品
	public Product deleteById(int id) {	
		Product product = (Product)this.sessionFactory.openSession().createQuery("delete from Product where id = ?").setParameter(0, id).uniqueResult();
		System.out.print("dao");		
		return product;
	}
		
	
	//添加商品到购物车 思路错
	public Product findById(int id) {	
		Product product = (Product)this.sessionFactory.openSession().createQuery("from Product where id = ?").setParameter(0, id).uniqueResult();
		System.out.print("dao");
				
		return product;
	}
	
	
	
	
	
	
	
	
	//分页查询数据
	public List<Product> findByPage(int pageNum,int pageSize){
		//获得session  
        Session session = sessionFactory.openSession();  
        //打开事务  
        Transaction ts = session.beginTransaction();
        //原生的Sql查询  
        SQLQuery query = session.createSQLQuery("select * from product limit ?,?");
        query.setInteger(1,(pageNum-1)*pageSize);
        query.setInteger(2,pageSize);
        // addEntity 将查询结果封装到指定对象中  
        query.addEntity(Product.class);  	          
        List<Product> list = query.list();            
        System.out.println(list);            
        /*List<Object[]> list = query.list(); 
         
        for(Object[] objs : list){ 
            System.out.println(Arrays.toString(objs)); 
        }*/
        //提交事务  
        ts.commit();        
        //关闭资源  
        session.close(); 
        return list;
	}
	//数据统计个数
	public int findCountByPage(int id, int count){
		Session session = sessionFactory.openSession();  
        //打开事务  
        Transaction ts = session.beginTransaction();
        //原生的Sql查询  
        SQLQuery query = session.createSQLQuery("select count(id) from product");
        //query.addEntity(count);		
		return count;
	}
	
	
	
	
	
	
	
	
	
	


	
	
	//查 把数据库里的商品放在products.jsp里
	public List<Product> findAll(){
			 
	        //获得session  
	        Session session = sessionFactory.openSession();  
	        //打开事务  
	        Transaction ts = session.beginTransaction();
	        //原生的Sql查询  
	        SQLQuery query = session.createSQLQuery("select * from product");  
	        // addEntity 将查询结果封装到指定对象中  
	        query.addEntity(Product.class);  	          
	        List<Product> list = query.list();            
	        System.out.println(list);            
	        /*List<Object[]> list = query.list(); 
	         
	        for(Object[] objs : list){ 
	            System.out.println(Arrays.toString(objs)); 
	        }*/
	        //提交事务  
	        ts.commit();        
	        //关闭资源  
	        session.close(); 
	        return list;

	}
	
	
	

	

}
