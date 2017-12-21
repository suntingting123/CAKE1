package com.xiaomi.cake.product.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.xiaomi.cake.entity.Cart;
import com.xiaomi.cake.entity.Order;
import com.xiaomi.cake.entity.Page;
import com.xiaomi.cake.entity.Product;
import com.xiaomi.cake.entity.ProductType;
import com.xiaomi.cake.entity.User;
import com.xiaomi.cake.product.service.ProductServiceImpl;
import com.xiaomi.cake.producttype.service.ProductTypeServiceImpl;

@Controller
@RequestMapping("/product")
public class ProductController {


	@Resource
	private ProductTypeServiceImpl productTypeServiceImpl;
	@Resource
	private ProductServiceImpl productServiceImpl;
	
	//后-删-商品删除
	@RequestMapping("/delete")
	public String deletelist(HttpSession session,@RequestParam("id") int id){
		Product product=this.productServiceImpl.findProduct(id);
		System.out.print("con快删啊");
		this.productServiceImpl.deleteProduct(product,id);
		return "forward:/product/get";
	}
	
	//后-改-修改商品
	//后-改-先找到要改的商品---修改商品
	@RequestMapping("/edit")
	public String edit(@RequestParam("id") int id,Model model){
		List<ProductType> listtype=this.productTypeServiceImpl.listProductType();
		model.addAttribute("listtype", listtype);
		Product product=this.productServiceImpl.findProduct(id);
		System.out.println("xiao"+product.getDescription());
		model.addAttribute("product", product);
		return "/admin/productedit";
	}	
	//后-改-修改商品
	@RequestMapping(value="updateback", method=RequestMethod.POST)//没进来，但是跳转了什么鬼
	public String updateBack(@RequestParam("id") int id,Product product,
			@RequestParam("name") String name,
			@RequestParam("price") int price,
			@RequestParam("discountprice") int discountprice,
			@RequestParam("discount") double discount,
			@RequestParam("description") String description,
			@RequestParam("producttypeid") String producttypeid,
			@RequestParam("image") String image,@RequestParam("producttype") ProductType producttype) {
		Product p=this.productServiceImpl.findProduct(id);
		p.setName(name);
		p.setPrice(price);
		p.setDiscountprice(discountprice);
		p.setDiscount(discount);
		p.setImage(image);
		p.setProducttype(producttype);
		p.setDescription(description);
		this.productServiceImpl.updateProduct(product);
		System.out.print("aaaaa"+product.getName());
		return "redirect:get";
	}
	
	//后-添加-添加商品
	@RequestMapping(value="/addBack", method=RequestMethod.GET)
	public String toAdd(Model model){
		List<ProductType> listtype=this.productTypeServiceImpl.listProductType();
		model.addAttribute("listtype", listtype);
		System.out.println("contype");  
		return "/admin/productdetail";//一会得看看这里面写的什么
	}
	//后-添加-添加商品---上传图片
	@RequestMapping(value="addBack", method=RequestMethod.POST)//没进来，但是跳转了
	public String addBack(Product product,
			@RequestParam("name") String name,@RequestParam("price") int price,
			@RequestParam("discountprice") int discountprice,
			@RequestParam("discount") double discount,
			@RequestParam("producttypeid") Integer producttypeid,
			@RequestParam("description") String description,
			@RequestParam("img") CommonsMultipartFile img) {
		System.out.print("con999");
		System.out.println(img.getOriginalFilename());
		product.setImage("images/"+img.getOriginalFilename());
		//product.setImage(img.getOriginalFilename());//
		Product p=this.productServiceImpl.addProductBack(product);		
		return "redirect:get";
	}

	//后-查-商品列表
	@RequestMapping("/get")
	public String listgoods(Product product,HttpSession session,HttpServletRequest request){
		String num = request.getParameter("pageNum");//获取用户要看的页码
		int pageNumber = 1;
		if(num!=null){
			pageNumber = Integer.parseInt(num);
		}		
		List<Product> list=this.productServiceImpl.findByPage(pageNumber, 2);
		Page page = new Page(pageNumber,2);
		page.setList(list);
		page.setTotalCount(this.productServiceImpl.findByCount());
		session.setAttribute("list", list);
		session.setAttribute("page", page);
		System.out.println(product.getDescription());
		return "/admin/productlist";
	}
	
	

	//前-添加-往购物车添加商品
	@RequestMapping("/addCart")
	public String showCart(@RequestParam("id") int id,HttpSession session){	
		System.out.print(id);
		Cart cart = this.productServiceImpl.addCart(id, session);	
		return "products";
	}
	
	//前-查-在商品页面显示商品列表
	@RequestMapping("/list")
	public String list(HttpSession session){
		List<Product> list=this.productServiceImpl.listProducts();
		session.setAttribute("list", list);
		return "products";
	}
	
	//前-删-从购物车删除商品--可以批量删除
	@RequestMapping("deleteproducts")
	public String delete(String checkedId,HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		this.productServiceImpl.deleteProducts(checkedId,session);
		return "forward:addCart";
	}
	
	//前-生成订单
	@RequestMapping("ordersubmit")
	public String productCheck(HttpSession session){
		System.err.println("here hello");
		Cart cart = (Cart)session.getAttribute("cart");
		User u = (User)session.getAttribute("user");
		Order or = this.productServiceImpl.save(cart,u);
		session.setAttribute("order", or);
		return "order";
	}


}
