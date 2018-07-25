 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
  <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE>
<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
		<title>Card update form</title>
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
				<div class="sidebarOption"><a class="side" href="${pageContext.request.contextPath}/User/createCard">Add new Credit card</a></div>
				<div class="sidebarOption"><a class="side" href="${pageContext.request.contextPath}/User/userTransactionHistory">Transaction history</a></div>
			</div>
			<div id="content">
				<form:form action="updateCardForm" method="POST" modelAttribute="creditCard">
					<p>
					<form:hidden path="id"/>

						<label><b> CREDIT CARD DATA </b></label>
					<p>
					<table>
						<tbody>
							<tr>
								<td><Label>Card type:</Label></td>
								<td><form:select path="cardType"><
									<form:option value="MasterCard" label="MasterCard"/>
									<form:option value="Visa" label="Visa"/>
								</form:select></td>									
							</tr>
							<tr>
								<td><label>Owner:</label></td>
								<td>
									<form:input path="firstName" placeholder="first name" class="form-field"/>  <form:errors path="firstName"/>
									<form:input path="lastName"  placeholder="last name" class="form-field"/>  <form:errors path="lastName"/>
								</td>
							</tr>
							<tr>
								<td><label>Card Number:</label></td>
								<td><form:input path="number" maxlength="16" class="form-field"/> <form:errors path="number"/></td>
							</tr>
							<tr>
								<td><Label>Expire Date</Label></td>
								<td><form:input path="expireDate" placeholder="YYYY-MM-DD" class="form-field"/>  <form:errors path="expireDate"/></td>
							</tr>
							<tr>
								<td><label>Password: </label></td>
								<td><form:input path="password" maxlength="3" class="form-field"/>  <form:errors path="password"/></td>
							</tr>
							<tr>
								<td><div style="margin-top:20px"><input type="submit" Value="Update user's CreditCard"/></div> </td>
							</tr>
						</tbody>
					</table>
				</form:form>
			</div>
			<div id="footer">
				Author: Piotr Detko, 2018. All rights reserved. 
			</div>
		</div>
	</body>
</html>