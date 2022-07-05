<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
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
	<h1>All Post </h1>
		<p class="text-secondary">Search Keyword : ${ empty param.keyword ? 'Search All' : param.keyword }</p>
		<c:forEach items="${list}" var="post">
			<div class="card mb-4">
				<div class="card-body">
					${post.title()}
				</div>
				<div class="card-footer d-flex justify-content-between align-items-center">
					<span>${post.creation()}</span>
			
					<c:url value="/show-detail" var="detailUrl">
						<c:param name="postId" value="${post.id()}"></c:param>
					</c:url>
					<a href="${detailUrl}" class="btn">Show Detail</a>
				</div>
			</div>
		</c:forEach>
	</div>
	
</body>
</html>