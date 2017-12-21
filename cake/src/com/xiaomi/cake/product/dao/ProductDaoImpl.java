package com.xiaomi.cake.product.dao;

import java.util.List;
import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.xiaomi.cake.entity.Order;
import com.xiaomi.cake.entity.Product;
import com.xiaomi.cake.entity.ProductType;

@Repository
public class ProductDaoImpl {

	@Resource
	private SessionFactory sessionFactory;
	
	//分页查询数据	
	public List<Product> findByPage(int pageNum, int pageSize){
		try{
			Query query=this.sessionFactory.getCurrentSession().createQuery("from "+Product.class.getSimpleName());
			//查询product表里的所有数据--list
			query.setFirstResult((pageNum-1)*pageSize);
			query.setMaxResults(pageSize);
			return query.list();
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	//统计数据个数 
	public int findCountByPage(){
		try{
			Query query=this.sessionFactory.getCurrentSession().createQuery("select count("+"*"+") from "+Product.class.getSimpleName());
			return new Long((long)query.uniqueResult()).intValue();
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	
	//前-查-把数据库里的商品放在products.jsp里-商品列表
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
		//提交事务  
		ts.commit();        
		//关闭资源  
		session.close(); 
		return list;
	}

	//后-删-商品删除、修改商品 ----前--从购物车里删除商品
	public Product findByIdBack(int id){	
		Product product = (Product)this.sessionFactory.getCurrentSession().
				createQuery("from Product where id = ?").
				setParameter(0, id).uniqueResult();
		return product;
	}
	//删除商品
	public void deleteByIdBack(Product product,int id){
		Session session=sessionFactory.openSession();
		System.out.print("dao快删啊");
		Transaction tran = session.beginTransaction() ;     
	        String hql = "Delete FROM Product Where id=?" ;     
	        Query q = session.createQuery(hql) ;     
	        q.setInteger(0, id) ;     
	        q.executeUpdate() ;     
	        tran.commit() ; 			
		
	}
	//后-改-submit那那个提交 ---调用修改
	public Product updateBack(Product product){
//		Session session=sessionFactory.getCurrentSession();
//		System.out.print("aaaaa");
//		session.update(product);	
//		return product;
		
		Session session=sessionFactory.openSession();  
	    Transaction tx=null;  
	    try{  
	        tx=session.beginTransaction(); 
	        System.out.print("aaaaa");
	        session.update(product);  
	        tx.commit();  
	    } catch (Exception e) {  
	        tx.rollback();  
	     throw new RuntimeException(e);  
	     } finally{  
	         session.close();  
	     }
		return product;
		
	}
	//后-查-订单列表
	public List<Order> findOrderById(){
		//获得session  
	    Session session = sessionFactory.openSession();  
	    //打开事务  
	    Transaction ts = session.beginTransaction();
	    //原生的Sql查询  
	    SQLQuery query = session.createSQLQuery("select * from order ");  
	    // addEntity 将查询结果封装到指定对象中  
	    query.addEntity(Order.class);  	          
	    List<Order> list = query.list();            
	    System.out.println(list);            
	    //提交事务  
	    ts.commit();        
	    //关闭资源  
	    session.close(); 
	    return list;
	}
	
	//后-增-添加商品 (一个添加，一个查询)
	public Product saveProductBack(Product product){
		Session session=sessionFactory.openSession();
		Transaction transtion=session.beginTransaction();
		session.save(product);			
		transtion.commit();
		session.close();
		//this.sessionFactory.getCurrentSession().save(product);
		return product;
	} 
	//前、后-查----商品列表 
	public List<Product> findById(){
		Session session=this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Product");  
		// addEntity 将查询结果封装到指 lass);  	          
		List<Product> list = query.list();
		//获得session  
//	    Session session = this.sessionFactory.getCurrentSession();  
//	    
//	    Query query = session.createQuery("from Product");  
//	    // addEntity 将查询结果封装到指定对象中  
//	    ((SQLQuery) query).addEntity(Product.class);  	          
//	    List<Product> list = query.list();            
//	    System.out.println(list);            
	    /*List<Object[]> list = query.list();    
	    for(Object[] objs : list){ 
	    	System.out.println(Arrays.toString(objs)); 
	    }*/
	    //提交事务  
//	    ts.commit();        
//	    //关闭资源  
//	    session.close(); 
	    return list;
	}
	
	
	
	//前、后-增-添加商品
	public Product saveProduct(Product product){
//		Session session=sessionFactory.openSession();
//		Transaction transtion=session.beginTransaction();
//		session.save(product);			
//		transtion.commit();
//		session.close();
		System.out.print("dao999");
		this.sessionFactory.getCurrentSession().save(product);
		System.out.print("dao"+product.getName());
		return product;
		
	}
	public Product findByName(String name){
		//return super.get(name);
		Product product = (Product)this.sessionFactory.openSession().
				createQuery("from User where name = ?").
				setParameter(0, name).uniqueResult();
		return product;

	}

	//删除商品
	public Product deleteById(int id) {	
		Product product = (Product)this.sessionFactory.openSession().createQuery("delete from Product where id = ?").setParameter(0, id).uniqueResult();
		System.out.print("dao");		
		return product;
	}
		

	//添加商品到购物车--思路错
	public Product findById(int id) {	
		Product product = (Product)this.sessionFactory.openSession().createQuery("from Product where id = ?").setParameter(0, id).uniqueResult();
		System.out.print("dao");
		return product;
	}
	
	//前-生成订单-把购物车里商品保存在order里
	public Order saveOrder(Order order){
		System.out.print("111dao");
		Session session=sessionFactory.openSession();
		Transaction transtion=session.beginTransaction();
		session.merge(order);	
		System.out.print("ooodao");
		transtion.commit();
		session.close();	
		return order;


	}

}
