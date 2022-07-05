<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Detail Page</title>
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
		
			<c:url var="editPostUrl" value="/post/edit">
				<c:param name="postId" value="${post.id()}" ></c:param>
			</c:url>
			<form action="${editPostUrl}" method="post">
				<h3>${post.title()}</h3>
				
				<div class="d-flex justify-content-between mb-2 mt-2">
					<span class="text-secondary">${post.owner().name()}</span>
					<span class="text-secondary">${post.creation()}</span>
				</div>
				
				<div class="col-md-12 mt-2 mb-4">
					${post.content()}
				</div>
				
				<div class="col-md-12">
					<c:url value="/post/delete" var="deleteUrl">
						<c:param name="postId" value="${post.id()}" ></c:param>
					</c:url>
					<c:url  value="/post/edit" var="editPostUrl">
						<c:param name="postId" value="${post.id()}" ></c:param>
					</c:url>
					
					<c:if test="${ not empty loginUser }">
						<c:if test="${loginUser.login() eq post.owner().login() or loginUser.role() eq 'Admin' }">
							<a href="${deleteUrl}" class="btn btn-danger" >Delete</a>
						</c:if>
						<c:if test="${loginUser.login() eq post.owner().login()}">
							<a href="${editPostUrl}" class="btn btn-primary" >Edit</a>
						</c:if>
					</c:if>
				</div>
			</form>
		</div>
	</div>
	
</body>
</html>