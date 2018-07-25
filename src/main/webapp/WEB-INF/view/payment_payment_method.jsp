 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
  <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE>
<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
		<title>Payment method</title>
		<meta name="Description" content="Welcome to the page with biggest collection of books on the internet"/>
		
		<link href="${pageContext.request.contextPath}/resources/css/HomeStyle.css" rel="stylesheet">
		<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
		<link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
	</head>
	<body>
		<div id="Container">
			<div id="header">
				<h2>EBookShop</h2>
				<p>
			</div>
			<div id="menu">
				<div class="option" style="margin-left:350px; border-left:2px dotted #444444;"><a class="menua" href="${pageContext.request.contextPath}/Home/"> Homepage</a></div>
				<div class="option"><a class="menua" href="${pageContext.request.contextPath}/User/user_profile"> Profile</a></div>
				<div class="option"><a class="menua" href="${pageContext.request.contextPath}/User/shopping_cart"> Shopping Cart </a></div>
				<div class="option"><a class="menua" href="${pageContext.request.contextPath}/Home/book_list"> Book List </a></div>
				<div class="option"> Contact </div>
				<div class="option"> About Us</div>
				<div class="login"> 
					<security:authorize access="hasRole('USER')">
						<form:form action="${pageContext.request.contextPath}/logout" method="POST">
						Welcome, <security:authentication property="principal.username"/> <style style="border-right: 2px dotted #666666;"></style> 
						<input type="submit" value="Logout"/>
						</form:form>
					</security:authorize>
					<security:authorize access="isAnonymous()">
						Login on your Account <style style="border-right: 2px dotted #666666;"></style>
						 <input type="button" value="Log in" onclick="window.location.href='${pageContext.request.contextPath}/loginPage'; return false; "/>
					</security:authorize>
				</div>
				<div style="clear:both;"></div>
			</div>
			<div id="topbar">
				<div id="topbarL">
					<img width="120" height="120" src="${pageContext.request.contextPath}/resources/icons/topbarIcon.png "/>
				</div>
				<div id="topbarR">
					"Books are the quietest and most constant of friends; they are the most accessible and wisest of counselors, and the most patient of teachers."<br> - Charles William Eliot
					
				</div>
				<div style="clear:both;"></div>
			</div>
			<div id="sidebar">
			
				<div class="sidebarOption"><a class="side" href="${pageContext.request.contextPath}/User/user_profile">Profile</a></div>
				<div class="sidebarOption"><a class="side" href="${pageContext.request.contextPath}/User/shopping_cart"> Cart</a></div>
				<div class="sidebarOption"><a class="side" href="${pageContext.request.contextPath}/Home/book_list">Book List</a></div>
				<div class="sidebarOption"><a class="side" href="${pageContext.request.contextPath}/Admin/">Admin Page</a></div>
			</div>
			<div id="content">
					<b>Select payment method and Adress</b>
					<p>
				<c:if test="${passIncorrect != null}">
					${passIncorrect}
					<p>
				</c:if>
					<c:if test="${dataNotFound != null}">
					${dataNotFound}
					<p>
				</c:if>
				<form:form action="confirmPaymentMethod" method="POST" modelAttribute="personalData">
					<form:select path="cardId">
						<form:option value="-1" label="--Select credit card--"/>
						<form:options items="${tempMap}"/>
					</form:select>
					<p>
					Confirm your password:
					<p>
					<input type="text" name="userPass" maxlength=3 />
					<p>
					<form:radiobutton path="adress" value="${personalData.registeredAdress}"/> registered Adress
					<p>
					<form:radiobutton path="adress" value="${personalData.contactAdress}"/> contact Adress
					<p>
					<input type="submit" value="Proceed to summary."/>
				</form:form>
			</div>
			<div id="footer">
				Author: Piotr Detko, 2018. All rights reserved. 
			</div>
		</div>
	</body>
</html>