<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isErrorPage="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

<title>Joy Bundler!</title>
</head>
<body class="container">
	<h1>${babyName.name}</h1>
	<h1 class="text-decoration-none">
		(added by
		<c:out value="${babyName.user.name }" />
		)
	</h1>
	<h3>
		Gender:
		<c:out value="${babyName.gender }" />
	</h3>
	<h3>
		Origin:
		<c:out value="${babyName.origin}" />
	</h3>
	<p>
		Meaning:
		<c:out value="${babyName.meaning }" />
	</p>
	<c:choose>
		<c:when test="${ babyName.likedUsers.contains(user)}">
			<p>You voted for this name</p>
		</c:when>
		<c:otherwise>
			<form action="/names/${babyName.id }/like" method="post">
				<input type="hidden" name="_method" value="put" />
				<button class="btn">Like!</button>
			</form>

		</c:otherwise>
	</c:choose>
	<c:if test="${user_id == babyName.user.id }">
		<a href="/names/${babyName.id }/edit" class="button">Edit</a>
	</c:if>
</body>
</html>