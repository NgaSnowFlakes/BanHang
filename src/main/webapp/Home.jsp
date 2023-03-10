<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet"
	integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
	crossorigin="anonymous">
<link href="web/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<jsp:include page="Menu.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<div class="col">
				<nav aria-label="breadcrumb">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="Home.jsp">Home</a></li>
						<li class="breadcrumb-item"><a href="#">Category</a></li>
						<li class="breadcrumb-item active" aria-current="#">Sub-category</li>
					</ol>
				</nav>
			</div>
		</div>
	</div>
	
	<div class="container">
		<div class="row">
			<jsp:include page="Left.jsp"></jsp:include>
			<div class="col-sm-9">
				<div  id="content" class="row">
					<c:forEach items="${ListP}" var="o">
						<div class="product col-12 col-md-6 col-lg-4">
							<div class="card">
								<img class="card-img-top" src="${o.image }" alt="Card image cap">
								<div class="card-body">
									<h4 class="card-title show_txt">
										<a href="detail?pid=${o.id}" title="View Product">"${o.getTitle()}"</a>
									</h4>
									<p class="card-text show_txt">${o.getDesc()}</p>
									<div class="row">
										<div class="col">
											<p class="btn btn-danger btn-block">${o.getPrice()}$</p>
										</div>
										<div class="col">
											<a href="#" class="btn btn-success btn-block">Add to cart</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
				<div class="row">
					<button onclick="loadMore()" class="btn btn-primary">Load More</button>
				</div>
			</div>

		</div>
	</div>

	<jsp:include page="Footer.jsp"></jsp:include>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<script type="text/javascript">
		function loadMore(){
			let amount = document.getElementsByClassName("product").length;
			console.log(amount);
			$.ajax({
		        url: "/BAN_HANG/LoadMore",
		        type: 'GET',
		        data:  {
		        	quantity: amount
		        },
		        success: function(data) {
		        	console.log(data);
		        	let content = document.querySelector("#content");
		        	content.innerHTML += data;
		        },
		        error: function (xhr){
		        	
		        }
		    });
		}
		
		function searchByAjax(param){
			let txtSearch = param.value;
			$.ajax({
		        url: "/BAN_HANG/searchByAjax",
		        type: 'GET',
		        data:  {
		        	txt: txtSearch
		        },
		        success: function(data) {
		        	let content = document.querySelector("#content");
		        	content.innerHTML = data;
		        },
		        error: function (xhr){
		        	
		        }
		    });
		}
		
		function loadProduct(cateID){
			$.ajax({
		        url: "/BAN_HANG/category",
		        type: 'GET',
		        data:  {
		        	cid: cateID
		        },
		        success: function(data) {
		        	document.querySelector("#content").innerHTML = data;
		        	
		        },
		        error: function (xhr){
		        	
		        }
		    });
		}
	</script>
</body>
</html>

