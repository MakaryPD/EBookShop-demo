 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
  <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE>
<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
		<title>Transaction preview</title>
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
				<b>TRNASACTION INFO</b>
				<p>
				<b>Transaction ID:</b> ${transaction.id}
				<p>
				<b>Name:</b> ${personalData.firstName }  ${personalData.lastName }
				<p>
				<b>Delivery Adess:</b> ${transaction.adress }
				<p>
				<b>Credit Card:</b> ${transaction.creditCard.cardType } | ${transaction.creditCard.number }
				<p>
				<b>Total Price:</b> ${transaction.totalCost } $
				<p>
				<b>Books:</b>
				<p>
				<table>
					<tr>
	 					<th>Title</th>
	 					<th>Author</th>
	 					<th>Publisher</th>
	 					<th>Year</th>
	 					<th>Type</th>
	 					<th>Price</th>
	 				</tr>
	 				<c:forEach var="tempBook" items="${transaction.books}">
	 					<tr>
	 						<td>${tempBook.title}</td>
	 						<td>${tempBook.author}</td>
	 						<td>${tempBook.publisher}</td>
	 						<td>${tempBook.year}</td>
	 						<td>${tempBook.type}</td>
	 						<td>${tempBook.price}</td>
	 					</tr>
					</c:forEach>
				</table>
				<p>
				<security:authorize access="!hasRole('ADMIN')">
					<a class="side" href="${pageContext.request.contextPath}/User/userTransactionHistory">Back</a>
				</security:authorize>
				<security:authorize access="hasRole('ADMIN')">
					<a class="side" href="${pageContext.request.contextPath}/Admin/transactionList">Back</a>
				</security:authorize>
			</div>
			<div id="footer">
				Author: Piotr Detko, 2018. All rights reserved. 
			</div>
		</div>
	</body>
</html>