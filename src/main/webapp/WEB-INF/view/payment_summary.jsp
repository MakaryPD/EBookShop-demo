 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
  <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE>
<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
		<title>Summary</title>
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
				<h3><b>User:</b></h3> 
				<p>
				Username: ${userCrm.username}
				<p>
				First name: ${pd.firstName }
				<p>
				Last name: ${pd.lastName }
				<p>
				email: ${pd.email }
				<p>
				<h4>Adress:</h4> ${personalData.adress} 
				<p>
				<h4>CreditCard:</h4>
				<table>
	 				<tr>
	 					<th>Type</th>
	 					<th>Owner</th>
		 				<th>Number</th>
		 				<th>Expire Date</th>
	 				</tr>
	 				<tr>
	 					<td>${creditCard.cardType }</td>
	 					<td>${creditCard.firstName } ${creditCard.lastName }</td>
	 					<td>${creditCard.number }</td>
	 					<td>${creditCard.expireDate }</td>
	 				</tr>
	 			</table>
	 			<h4>Items: </h4>
	 			<table>
	 				<tr>
	 					<th>Title</th>
	 					<th>Author</th>
	 					<th>Publisher</th>
	 					<th>Year</th>
	 					<th>Type</th>
	 					<th>Price</th>
	 				</tr>
	 				<c:forEach var="tempBook" items="${cart.books }">
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
	 		<h3>In total: ${cart.totalCost }$</h3>
	 		<p>
	 		<c:if test="${cart.size>0 }">
	 		<form:form action="createOrder" modelAttribute="pd" method="POST">
	 			<form:hidden path="cardId" value="${creditCard.id }"/>
	 			<form:hidden path="adress" value="${personalData.adress }"/>
	 			<input type="submit" value="Accept and procced"/>
	 		</form:form>
	 		</c:if>
	 		<form:form action="cancelPayment" method="POST">
	 			<input type="submit" value="Cancel the payment"/>
	 		</form:form>
			</div>
			<div id="footer">
				Author: Piotr Detko, 2018. All rights reserved. 
			</div>
		</div>
	</body>
</html>
			