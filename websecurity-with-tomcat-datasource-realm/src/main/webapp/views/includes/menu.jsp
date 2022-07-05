<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="bg-success">
	<div class="container d-flex justify-content-between align-items-center pt-2 pb-2">
		<h2 class="text-white">Post Demo</h2>
		
		<c:url value="/search-post" var="searchUrl"></c:url>
		<form action="${searchUrl}" class="d-flex d-flex-reverse">
			<div >
				<input name="keyword" value="${param.keyword}" type="text" placeholder="Enter Search Keyword" class="form-control" />
			</div>
			<button type="submit" class="btn text-white">Search</button>
			
			<c:choose>
				<c:when test="${empty loginUser }">
					<c:url value="/sign-up" var="signUpUrl"></c:url>
					<a href="${signUpUrl}" class="btn text-white">Sign Up</a>
					
					<c:url value="/sign-in" var="signInUrl"></c:url>
					<a href="${signInUrl}" class="btn text-white"> Sign In</a>
				</c:when>
				<c:otherwise>
					
					<c:url value="/post/edit" var="addUrl"></c:url>
					<a href="${addUrl}" class="btn text-white"> Add New</a>
					
					<c:url value="/sign-out" var="signOutUrl"></c:url>
					<a href="${signOutUrl}" class="btn text-white"> Sign Out</a>
				
				</c:otherwise>
			</c:choose>	
					
			<span class="btn">${ loginUser.name() }</span>				
		</form>
	</div>
</nav>