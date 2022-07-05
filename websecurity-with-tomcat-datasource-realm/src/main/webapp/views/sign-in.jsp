<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign In Page</title>
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
			<h3>Sign In</h3>

			<c:url value="/sign-in" var="singInUrl"></c:url>
			<form action="${singInUrl}" method="post">
				<div class="col-md-4 mb-2">
					<label class="form-label text-secondary" for="loginId">LoginId</label>
					<input name="loginId" type="text" placeholder="Enter LoginId" class="form-control" />
				</div>
				<div class="col-md-4 mb-4">
					<label class="form-label text-secondary" for="password">Password</label>
					<input name="password" type="password" placeholder="Enter Password" class="form-control" />
				</div>

				<input type="submit" value="Sign In" class="btn btn-success" />

				<c:url var="signUpUrl" value="/sign-up"></c:url>
				<a href="${signUpUrl}" class="btn btn-primary">Sign Up</a>
			</form>
		</div>
	</div>

</body>
</html>