package com.xiaomi.cake.product.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.xiaomi.cake.entity.Order;
import com.xiaomi.cake.entity.Product;
import com.xiaomi.cake.entity.User;

@Repository
public class OrderDaoImpl {

	@Resource
	private SessionFactory sessionFactory;
	//后-删-订单删除
	public Order findByIdBack(int id){
		Order order = (Order)this.sessionFactory.getCurrentSession().
				createQuery("from Order where id = ?").
				setParameter(0, id).uniqueResult();
		return order;	
	}
	public void deleteByIdBack(Order order, int id){
		Session session=sessionFactory.openSession();
		Transaction tran = session.beginTransaction() ;     
	        String hql = "Delete FROM Order Where id=?" ;     
	        Query q = session.createQuery(hql) ;     
	        q.setInteger(0, id) ;     
	        q.executeUpdate() ;     
	        tran.commit() ; 
	}
	
	
	//分页查询数据	
	public List<Order> findByPage(int pageNum, int pageSize){
		try{
			Query query=this.sessionFactory.getCurrentSession().createQuery("from "+Order.class.getSimpleName());
			//查询product表里的所有数据--list
			query.setFirstResult((pageNum-1)*pageSize);
			query.setMaxResults(pageSize);
			System.out.print("ooodao");
			return query.list();
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	//统计数据个数 
	public int findCountByPage(){
		try{
			Query query=this.sessionFactory.getCurrentSession().createQuery("select count("+"*"+") from "+Order.class.getSimpleName());
			return new Long((long)query.uniqueResult()).intValue();
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}

}
