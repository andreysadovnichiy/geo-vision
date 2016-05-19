<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Driver Login</title>

<spring:url value="/static/css/bootstrap.min.css" var="bootstrapCss" />
<spring:url value="/static/css/signin.css" var="signinCss" />

<link rel="stylesheet" type="text/css" href="${bootstrapCss}" />
<link rel="stylesheet" type="text/css" href="${signinCss}" />

<link rel="icon" 
      type="image/png" 
      href="<spring:url value="/static/images/favicon.png" />">

</head>
<body>
	<div class="container">
		<form class="form-signin" action='<spring:url value="/login" />' method="post">
			<h2 class="form-signin-heading">Please sign in</h2>
			<label for="inputLogin" class="sr-only">Login</label> 
			<input type="text" id="inputLogin" name="username" class="form-control" placeholder="Login" required autofocus> 
			<label for="inputPassword" class="sr-only">Password</label> 
			<input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
		</form>
	</div>
</body>
</html>

