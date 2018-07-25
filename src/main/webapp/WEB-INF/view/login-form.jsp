<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html lang="pl">
	<head>
		<meta charset="utf-8" />
		<title>login form</title>
		<meta name="description" content="Nauka podstawowego stylizowania elementów formularzy" />
		<meta name="keywords" content="css, odcinek" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<link href="${pageContext.request.contextPath}/resources/css/login.css" rel="stylesheet">
		<style>
		.failed{
			color: red; 
		}
		</style>
	</head>
	<body>
	<div id="container">
		<form:form action="${pageContext.request.contextPath}/authenticateTheUser" method="POST">
			<p>
			Username:<br> <input type="text" name="username"/>
			<p>
			Password:<br> <input type="password" name="password"/>
			<p>
			<c:if test="${param.error!=null }">
			<i class= failed>"Username/Password is incorecct. Please, try again."</i>
			</c:if>
			<br>
			<input type="submit" value="Login"/>
		</form:form>
		<br>
		Don't have an account? Register here: 
		<p>  
	<input type="button" value="Register new user" onclick="window.location.href='${pageContext.request.contextPath}/register/showRegistrationForm'; return false; "/>
	</div>
	</body>
</html>