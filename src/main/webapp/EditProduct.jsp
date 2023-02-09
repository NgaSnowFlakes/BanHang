<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
</head>
<body>
	<div class="container">

		<form action="edit" method = "Post">

			<h4 class="modal-title">Edit Employee</h4>
			<div class="form-group">
				<label>Product ID</label> 
				<input value="${detail.id}" name="id" type="text"
					class="form-control" readonly>
			</div>
			<div class="form-group">
				<label>Name</label> 
				<input value="${detail.name}" name="name" type="text"
					class="form-control" required>
			</div>
			<div class="form-group">
				<label>Image</label> <input name="image" type="text"
					class="form-control" required value="${detail.image}">
			</div>
			<div class="form-group">
				<label>Price</label> <input name="price" type="text"
					class="form-control" required value="${detail.price}">
			</div>
			<div class="form-group">
				<label>Title</label>
				<textarea name="title" class="form-control" required>${detail.title}</textarea>
			</div>
			<div class="form-group">
				<label>Description</label>
				<textarea name="description" class="form-control" required>${detail.desc}</textarea>
			</div>
			<div class="form-group">
				<label>Category</label> <select name="category" class="form-select"
					aria-label="Default select example">
					<c:forEach items="${listC}" var="o">
						<option value="${o.cid}">${o.cname}</option>
					</c:forEach>
				</select>
			</div>
			<div class="modal-footer">
				<input type="button" class="btn btn-default" data-dismiss="modal"
					value="Cancel"> <input type="submit" class="btn btn-info"
					value="Save">
			</div>
		</form>
	</div>
</body>
</html>