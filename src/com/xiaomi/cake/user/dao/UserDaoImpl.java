package com.xiaomi.cake.user.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;



import com.xiaomi.cake.entity.User;


@Repository
public class UserDaoImpl {

	@Resource
	private SessionFactory sessionFactory;

	
	
	//登录 查 ajax
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
	//ajax
	public User findByName(String name){
		//return super.get(name);
		
		User user = (User)this.sessionFactory.openSession().
				createQuery("from User where name = ?").setParameter(0, name).uniqueResult();
		return user;

	}
	public User findByEmail(String email){
		//return super.get(name);
		
		User user = (User)this.sessionFactory.openSession().
				createQuery("from User where email = ?").setParameter(0, email).uniqueResult();
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
	
	
	
	
	
	//后台 用户删除
	public User findById(int id){
		User u=this.sessionFactory.getCurrentSession().get(User.class, id);
		System.out.println(u.getName());
		return u;		
	}	
	public void deleteById(User user){
		Session session=sessionFactory.openSession();
		Transaction transtion=session.beginTransaction();
		session.delete(user);			
		transtion.commit();
		session.close();		
	}


}
