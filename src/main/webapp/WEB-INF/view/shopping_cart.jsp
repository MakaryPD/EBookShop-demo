 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
  <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE>
<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
		<title>shopping cart</title>
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
					You are now in your cart, <security:authentication property="principal.username"/>
				<br>
				<c:if test="${cart.size ==0 }">
					<br>
					<i>Your cart is empty.</i>
				</c:if>	
				<br>
				<c:if test="${cart.size >0 }">
					<table>
	 					<tr>
	 						<th>Title</th>
	 						<th>Author</th>
	 						<th>Publisher</th>
	 						<th>Year</th>
	 						<th>Type</th>
	 						<th>Price</th>
	 						<th></th>
	 					</tr>
	 				<c:forEach var="tempBook" items="${cart.books}">
	 			 			
	 			 	<c:url var="deleteLink" value="/User/deleteBookFromCart">
	 					<c:param name="bookId" value="${tempBook.id }"/>
	 				</c:url>
	 				
	 					<tr>
	 						<td>${tempBook.title}</td>
	 						<td>${tempBook.author}</td>
	 						<td>${tempBook.publisher}</td>
	 						<td>${tempBook.year}</td>
	 						<td>${tempBook.type}</td>
	 						<td>${tempBook.price}</td>
	 						<td><a class="side" href="${deleteLink }"
	 						onclick ="if(!(confirm('Are you sure you want to delete this record?'))) return false"><b>DELETE</b></a></td>
	 					</tr>
	 				</c:forEach>
	 			</table>
	 			<br>
	 			In Total: ${cart.totalCost}$
	 			<p>
	 			<c:if test="${not empty creditCards}">
	 				<a class="side" href="${pageContext.request.contextPath}/Payment/paymentMethod"><b>Confirm these item and go to payment method.</b></a>
	 			</c:if>
	 			<c:if test="${empty creditCards }">
	 				<a class="side" href="${pageContext.request.contextPath}/User/createCard"><b>You need to add a Credit Card first.</b></a>
	 			</c:if>
	 			<p>
	 		</c:if>
	 			<p>
			</div>
			<div id="footer">
				Author: Piotr Detko, 2018. All rights reserved. 
			</div>
		</div>
	</body>
</html>