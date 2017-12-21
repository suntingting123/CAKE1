<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Checkout</title>
<!-- Custom Theme files -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<script type="application/x-javascript">
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 
</script>
<!-- //Custom Theme files -->
<link href="${ctx }/css/bootstrap.css" type="text/css" rel="stylesheet"
	media="all">
<link href="${ctx }/css/style.css" type="text/css" rel="stylesheet"
	media="all">
<!-- js -->
<script src="${ctx }/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/js/bootstrap-3.1.1.min.js"></script>
<!-- //js -->
<!-- cart -->
<script src="${ctx }/js/simpleCart.min.js">
	
</script>
<!-- cart -->
<script>
	//删除所选中的商品 
	function datadel() {
		//alert("ffffff");

		// 获取所有选中的checked框  

		var option = $(":checked");
		var checkedId = "";
		var boo = true;
		//拼接除全选框外,所有选中的id,  
		for (var i = 0, len = option.length; i < len; i++) {

			if (boo) {
				if (option[i].id == 'allSelect') {
					boo = true;
				} else {
					boo = false;
					checkedId += option[i].value;
				}
			} else {
				checkedId += "," + option[i].value;
			}
		}
		$.ajax({
			type : "post",
			url : '${ctx}/product/deleteproducts',
			data : {
				"checkedId" : checkedId
			},
			dataType : "text",
			success : function(msg) {
				window.location.href="${ctx}/checkout.jsp"; 
			},
			error : function(msg){
				window.location.href="${ctx}/checkout.jsp"; 
			}
		});
		
		
	}
</script>

</head>
<body>
	<!--header-->
	<div class="header">
		<div class="container">
			<nav class="navbar navbar-default" role="navigation">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<h1 class="navbar-brand">
					<a href="index.html">Yummy</a>
				</h1>
			</div>
			<!--navbar-header-->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="index.jsp" class="active">Home</a></li>
					<li><a href="checkout.jsp">Cart</a></li>
					<li><a href="/cake/product/list">Product</a></li>
					<li><a href="login.jsp">Login</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Birthday<b class="caret"></b></a>
						<ul class="dropdown-menu multi-column columns-4">
							<div class="row">
								<div class="col-sm-3">
									<h4>By Relation</h4>
									<ul class="multi-column-dropdown">
										<li><a class="list" href="products.html">Friend</a></li>
										<li><a class="list" href="products.html">Lover</a></li>
										<li><a class="list" href="products.html">Sister</a></li>
										<li><a class="list" href="products.html">Brother</a></li>
										<li><a class="list" href="products.html">Kids</a></li>
										<li><a class="list" href="products.html">Parents</a></li>
									</ul>
								</div>
								<div class="col-sm-3">
									<h4>By Flavour</h4>
									<ul class="multi-column-dropdown">
										<li><a class="list" href="products.html">Chocolate</a></li>
										<li><a class="list" href="products.html">Mixed Fruit</a></li>
										<li><a class="list" href="products.html">Butterscotch</a></li>
										<li><a class="list" href="products.html">Strawberry</a></li>
										<li><a class="list" href="products.html">Vanilla</a></li>
										<li><a class="list" href="products.html">Eggless
												Cakes</a></li>
									</ul>
								</div>
								<div class="col-sm-3">
									<h4>By Theme</h4>
									<ul class="multi-column-dropdown">
										<li><a class="list" href="products.html">Heart shaped</a></li>
										<li><a class="list" href="products.html">Cartoon
												Cakes</a></li>
										<li><a class="list" href="products.html">2-3 Tier
												Cakes</a></li>
										<li><a class="list" href="products.html">Square shape</a></li>
										<li><a class="list" href="products.html">Round shape</a></li>
										<li><a class="list" href="products.html">Photo cakes</a></li>
									</ul>
								</div>
								<div class="col-sm-3">
									<h4>Weight</h4>
									<ul class="multi-column-dropdown">
										<li><a class="list" href="products.html">1 kG</a></li>
										<li><a class="list" href="products.html">1.5 kG</a></li>
										<li><a class="list" href="products.html">2 kG</a></li>
										<li><a class="list" href="products.html">3 kG</a></li>
										<li><a class="list" href="products.html">4 kG</a></li>
										<li><a class="list" href="products.html">Large</a></li>
									</ul>
								</div>
							</div>
						</ul></li>


					<li class="dropdown grid"><a href="#"
						class="dropdown-toggle list1" data-toggle="dropdown">Store<b
							class="caret"></b></a>
						<ul class="dropdown-menu multi-column columns-3">
							<div class="row">
								<div class="col-sm-4">
									<h4>By Relation</h4>
									<ul class="multi-column-dropdown">
										<li><a class="list" href="products.html">Friend</a></li>
										<li><a class="list" href="products.html">Lover</a></li>
										<li><a class="list" href="products.html">Sister</a></li>
										<li><a class="list" href="products.html">Brother</a></li>
										<li><a class="list" href="products.html">Kids</a></li>
										<li><a class="list" href="products.html">Parents</a></li>
									</ul>
								</div>
								<div class="col-sm-4">
									<h4>By Flavour</h4>
									<ul class="multi-column-dropdown">
										<li><a class="list" href="products.html">Chocolate</a></li>
										<li><a class="list" href="products.html">Mixed Fruit</a></li>
										<li><a class="list" href="products.html">Butterscotch</a></li>
										<li><a class="list" href="products.html">Strawberry</a></li>
										<li><a class="list" href="products.html">Vanilla</a></li>
										<li><a class="list" href="products.html">Eggless
												Cakes</a></li>
									</ul>
								</div>
								<div class="col-sm-4">
									<h4>Specials</h4>
									<ul class="multi-column-dropdown">
										<li><a class="list" href="products.html">Ice cream
												cake</a></li>
										<li><a class="list" href="products.html">Swiss roll</a></li>
										<li><a class="list" href="products.html">Ruske kape</a></li>
										<li><a class="list" href="products.html">Cupcakes</a></li>
										<li><a class="list" href="products.html">Muffin</a></li>
										<li><a class="list" href="products.html">Merveilleux</a></li>
									</ul>
								</div>
							</div>
						</ul></li>
				</ul>
				<!--/.navbar-collapse-->
			</div>
			<!--//navbar-header--> </nav>

			<div class="header-info">
				<div class="header-right search-box">
					<a href="#"><span class="glyphicon glyphicon-search"
						aria-hidden="true"></span></a>
					<div class="search">
						<form class="navbar-form">
							<input type="text" class="form-control">
							<button type="submit" class="btn btn-default"
								aria-label="Left Align">Search</button>
						</form>
					</div>
				</div>
				<div class="header-right login">
					<a href="#"><span class="glyphicon glyphicon-user"
						aria-hidden="true"></span></a>
					<div id="loginBox">
						<form id="loginForm" action="/cake/user/login">
							<fieldset id="body">

								<fieldset>
									<label for="email">Email Address</label> <input type="text"
										name="name" id="email">
								</fieldset>
								<fieldset>
									<label for="password">Password</label> <input type="password"
										name="password" id="password">
								</fieldset>
								<input type="submit" id="login" value="Sign in"> <label
									for="checkbox"><input type="checkbox" id="checkbox">
									<i>Remember me</i></label>
							</fieldset>
							<p>
								New User ? <a class="sign" href="register.jsp">Sign Up</a> <span><a
									href="#">Forgot your password?</a></span>
							</p>
						</form>
					</div>
				</div>

				<div class="header-right cart">
					<a href="#"><span class="glyphicon glyphicon-shopping-cart"
						aria-hidden="true"></span></a>
					<div class="cart-box">
						<h4>
							<a href="checkout.html"> <span class="simpleCart_total">
									$0.00 </span> (<span id="simpleCart_quantity"
								class="simpleCart_quantity"> 0 </span>)
							</a>
						</h4>
						<p>
							<a href="javascript:;" class="simpleCart_empty">Empty cart</a>
						</p>
						<div class="clearfix"></div>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>
		</div>

	</div>
	${errorinfo}
	<!--//header-->
	<!--cart-items-->
	<div class="cart-items">
		<div class="container">
			<h2>My Shopping Cart</h2>




			<c:forEach items="${cartlist }" var="p">
				<script>
					$(document).ready(function(c) {
						$('.close2').on('click', function(c) {
							$('.cart-header2').fadeOut('slow', function(c) {
								$('.cart-header2').remove();
							});
						});
					});
				</script>

				<div class="cart-header2">
					<form action="${ctx }/product/ordersubmit">
					<input type="checkbox" value="${p.product.id }" name="id" id="${p.product.id }">
					<%-- <a href="${ctx }/product/deleteproducts?id=${p.product.id }"
						class="close2" onclick="datadel()"></a> --%>
					<div class="cart-sec simpleCart_shelfItem">
						<div class="cart-item cyc">
							<img src="${p.product.image }" class="img-responsive" alt="">
						</div>
						<div class="cart-item-info">
							<h3>
								<a href="#"> Lorem Ipsum is not simply </a><span>${p.product.name }</span>
							</h3>

							<div class="delivery">
								<p>Service Charges : ${p.product.discountprice }</p>
								<span>Count:${p.count }</span>
								<div class="clearfix"></div>
							</div>
						</div>
						<div class="clearfix"></div>
					</div>
				</div>
			</c:forEach>
			<input type="button" value="批量删除" onclick="datadel()"> 
			<input type="submit" value="生成订单"/></form>
		</div>
	</div>
	<!--//checkout-->
	<!--footer-->
	<div class="footer">
		<div class="container">
			<div class="footer-grids">
				<div class="col-md-2 footer-grid">
					<h4>company</h4>
					<ul>
						<li><a href="products.html">products</a></li>
						<li><a href="#">Work Here</a></li>
						<li><a href="#">Team</a></li>
						<li><a href="#">Happenings</a></li>
						<li><a href="#">Dealer Locator</a></li>
					</ul>
				</div>
				<div class="col-md-2 footer-grid">
					<h4>service</h4>
					<ul>
						<li><a href="#">Support</a></li>
						<li><a href="#">FAQ</a></li>
						<li><a href="#">Warranty</a></li>
						<li><a href="contact.html">Contact Us</a></li>
					</ul>
				</div>
				<div class="col-md-3 footer-grid">
					<h4>order & returns</h4>
					<ul>
						<li><a href="#">Order Status</a></li>
						<li><a href="#">Shipping Policy</a></li>
						<li><a href="#">Return Policy</a></li>
						<li><a href="#">Digital Gift Card</a></li>
					</ul>
				</div>
				<div class="col-md-2 footer-grid">
					<h4>legal</h4>
					<ul>
						<li><a href="#">Privacy</a></li>
						<li><a href="#">Terms and Conditions</a></li>
						<li><a href="#">Social Responsibility</a></li>
					</ul>
				</div>
				<div class="col-md-3 footer-grid icons">
					<h4>Connect with Us</h4>
					<ul>
						<li><a href="#"><img src="images/i1.png" alt="" />Follow
								us on Facebook</a></li>
						<li><a href="#"><img src="images/i2.png" alt="" />Follow
								us on Twitter</a></li>
						<li><a href="#"><img src="images/i3.png" alt="" />Follow
								us on Google-plus</a></li>
						<li><a href="#"><img src="images/i4.png" alt="" />Follow
								us on Pinterest</a></li>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	</div>
	<!--footer-->
	<div class="footer-bottom">
		<div class="container">
			<p>
				Copyright &copy; 2015.Company name All rights reserved.More
				Templates <a href="" target="_blank" title="小米">小米</a> - Collect
				from <a href="" title="小米" target="_blank">小米</a>
			</p>
		</div>
	</div>
</body>
</html>