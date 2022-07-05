<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Post Page</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
</head>
<body>
	<c:import url="/views/includes/menu.jsp"></c:import>

	<div class="container mt-4">
		<div class="row">
		<h1>${ not empty post.id() ? 'Edit Post' : 'Add New Post' }</h1>
		
		<c:url var="savePostUrl" value="/post/edit">
			<c:param name="postId" value="${post.id()}" ></c:param>
		</c:url>
			<form action="${savePostUrl}" method="post">
				<div class="col-md-8 mb-2 mt-2">
					<input name="title" type="text" placeholder="Enter Post Title" class="form-control" value="${post.title()}"/>
				</div>
				<div class="col-md-12 mb-4">
					<textarea name="content" id="" cols="100" rows="6" class="form-control">${post.content()}</textarea>
				</div>
				<div class="col-md-12">
					<input type="reset" value="Reset" class="btn btn-danger" />
					<input type="submit" value="Post" class="btn btn-success" />
				</div>
			</form>	
		</div>
	</div>
	
</body>
</html>