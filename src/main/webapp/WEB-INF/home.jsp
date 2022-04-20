<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isErrorPage="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

<title>Joy Bundler</title>
</head>
<body class="container">
	<div class="row">
		<div class="col">
			<h1>
				Hello,
				<c:out value="${user.name }" />
				. Here are some name suggestions..
			</h1>
		</div>
		<div class="col-md-auto">
			<p>
				<a href="/logout">Logout</a>
			</p>
			<p>
				<a href="/names/new">Add new name</a>
			</p>
		</div>
	</div>
	<h4>Baby Names</h4>
	<table class="table">
		<thead>
			<tr>
			<th scope="col"></th>
				<th scope="col"></th>
				<th scope="col"></th>
				<th scope="col"></th>
				<th scope="col">Votes</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="name" items="${babyNames }">
				<tr>
					<td><c:choose>
							<c:when test="${ name.likedUsers.contains(user)}">
							</c:when>
							<c:otherwise>
								<form action="/names/${name.id }/like" method="post">
									<input type="hidden" name="_method" value="put" />
									<button class="btn">Like!</button>
								</form>

							</c:otherwise>
						</c:choose></td>
					<td scope="row"><a href="/names/${name.id}"><c:out
								value="${name.name}" /></a></td>
					<th><c:out value="${name.gender}"></c:out></th>
					<th>Origin:<c:out value="${name.origin}"></c:out></th>
					<th>${name.likedUsers.size()}</th>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>