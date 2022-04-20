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
	<h1>Add a Name!</h1>

	<div>
		<form:form action="/names/new" method="post" modelAttribute="babyName">
			<form:hidden path="user" value="${user.id }" />
			<p>
				<form:label path="name">New Name:</form:label>
				<form:input path="name" />
				<form:errors path="name" />
			</p>
			<p>
				<form:label path="gender">Gender</form:label>
				<form:select path="gender">
					<form:option value="Neutral">Neutral</form:option>
					<form:option value="Female">Female</form:option>
					<form:option value="Male">Male</form:option>
				</form:select>

			</p>
			<p>
				<form:label path="origin">Origin:</form:label>
				<form:input path="origin" />
				<form:errors path="origin" />
			</p>
			<p>
				<form:label path="meaning">Meaning</form:label>
				<form:textarea path="meaning" rows="3" cols="20" />
				<form:errors path="meaning" />
			</p>
			<button>Submit</button>
		</form:form>
	</div>

</body>
</html>