package com.xiaomi.cake.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product implements java.io.Serializable{
	

	private Integer id;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	private String name;
	private int price;
	private int discountprice;
	private double discount;
	private String image;
	private String description;
	private ProductType producttype;//双向
	private Integer amount;
	//private OrderItem orderitem;

//	@ManyToMany(mappedBy="order")
//	private Set<Order> orderSet = new HashSet<Order>();
//	private int productindex;//这个写在数据库里了，但是不知道用不用在这写，感觉不用
//	private int producttypeid;//
	


	public double getDiscount() {
		return discount;
	}
	
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	public int getDiscountprice() {
		return discountprice;
	}
	
	public void setDiscountprice(int discountprice) {
		this.discountprice = discountprice;
	}	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@ManyToOne
	@JoinColumn(name="PRODUCTTYPEID")//指明外键
	public ProductType getProducttype() {
		return producttype;
	}
	public void setProducttype(ProductType producttype) {
		this.producttype = producttype;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	
//	@ManyToOne
//	@JoinColumn(name="ORDERITEMID")//指明外键
//	public OrderItem getOrderitem() {
//		return orderitem;
//	}
//	public void setOrderitem(OrderItem orderitem) {
//		this.orderitem = orderitem;
//	}
	
	
	
//	public Set<Order> getOrderSet() {
//		return orderSet;
//	}
//	public void setOrderSet(Set<Order> orderSet) {
//		this.orderSet = orderSet;
//	}
	
	

}
