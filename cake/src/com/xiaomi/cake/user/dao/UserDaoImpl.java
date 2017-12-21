package com.xiaomi.cake.user.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.xiaomi.cake.entity.Product;
import com.xiaomi.cake.entity.User;


@Repository
public class UserDaoImpl {

	@Resource
	private SessionFactory sessionFactory;
	//分页查询数据	
		public List<User> findByPage(int pageNum, int pageSize){
			try{
				Query query=this.sessionFactory.getCurrentSession().createQuery("from "+User.class.getSimpleName());
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
				Query query=this.sessionFactory.getCurrentSession().createQuery("select count("+"*"+") from "+User.class.getSimpleName());
				return new Long((long)query.uniqueResult()).intValue();
			}catch(Exception e){
				e.printStackTrace();
				return 0;
			}
		}
	
	
	//登录 查
	public List<User> findById(){	 
        //获得session  
        Session session = sessionFactory.openSession();  
        //打开事务  
        Transaction ts = session.beginTransaction();
        //原生的Sql查询  
        SQLQuery query = session.createSQLQuery("select * from user ");  
        // addEntity 将查询结果封装到指定对象中  
        query.addEntity(User.class);  	          
        List<User> list = query.list();            
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
	
	
	
	
	//注册 查（判断）
	
	public User findByName(String name){
		//return super.get(name);
		
		User user = (User)this.sessionFactory.openSession().
				createQuery("from User where name = ?").setParameter(0, name).uniqueResult();
		return user;

	}
	//ajax
	public User findByEmail(String email){
		//return super.get(name);
		
		User user = (User)this.sessionFactory.openSession().
				createQuery("from User where email = ?").setParameter(0, email).uniqueResult();
		System.out.print("dao");
		return user;
	}
	//注册 增
	public User saveUser(User user){
		Session session=sessionFactory.openSession();
		Transaction transtion=session.beginTransaction();
		session.save(user);			
		transtion.commit();
		session.close();
		//this.sessionFactory.getCurrentSession().save(user);
		return user;
	}
	
	
	
	
	
	//后-用户删除
	public User findById(int id){
		User u=this.sessionFactory.getCurrentSession().get(User.class, id);
		System.out.println(u.getName());	
		return u;		
	}
	public void deleteById(User user, int id){
		Session session=sessionFactory.openSession();
		Transaction tran = session.beginTransaction() ;     
	        String hql = "Delete FROM User Where id=?" ;     
	        Query q = session.createQuery(hql) ;     
	        q.setInteger(0, id) ;     
	        q.executeUpdate() ;     
	        tran.commit() ; 
	}


}
