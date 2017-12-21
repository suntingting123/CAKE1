package com.xiaomi.cake.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ordert")
public class Order implements Serializable{

	private Integer id;
	private int price;
	private Date ordercreatetime;
	private Date orderpaytime;
	private int orderstate;
	private User user;

	private Set<OrderItem> orderitemSet = new HashSet<OrderItem>();
//	@ManyToMany
//	@JoinTable(name="ORDERITEM",joinColumns=@JoinColumn(name="ORDERID"),inverseJoinColumns=@JoinColumn(name="productid"))
//	private Set<Product> productSet = new HashSet<Product>();


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@ManyToOne
	@JoinColumn(name="USERID")//指明外键
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
//@OneToMany(mappedBy="order",targetEntity=OrderItem.class,cascade=CascadeType.ALL)
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="orderid")
	public Set<OrderItem> getOrderitemSet() {
		return orderitemSet;
	}
	public void setOrderitemSet(Set<OrderItem> orderitemSet) {
		this.orderitemSet = orderitemSet;
	}
	
	public Date getOrdercreatetime() {
		return ordercreatetime;
	}
	public void setOrdercreatetime(Date ordercreatetime) {
		this.ordercreatetime = ordercreatetime;
	}
	public Date getOrderpaytime() {
		return orderpaytime;
	}
	public void setOrderpaytime(Date orderpaytime) {
		this.orderpaytime = orderpaytime;
	}
	public int getOrderstate() {
		return orderstate;
	}
	public void setOrderstate(int orderstate) {
		this.orderstate = orderstate;
	}

	
	

	
}
