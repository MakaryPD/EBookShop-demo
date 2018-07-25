 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
  <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE>
<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
		<title>Profile</title>
		<meta name="Description" content="Welcome to the page with biggest collection of books on the internet"/>
		
		<link href="${pageContext.request.contextPath}/resources/css/HomeStyle.css" rel="stylesheet">
		<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
		<link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
	</head>
	<body>
		<div id="Container">
		
			<c:url var="personalLink" value="/User/update_user_profile">
	 				<c:param name="personalId" value="${personalData.id }"/>
	 		</c:url>
		
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
			
				<div class="sidebarOption"><a class="side" href="${personalLink}">Update Information</a></div>
				<div class="sidebarOption"><a class="side" href="${pageContext.request.contextPath}/User/shopping_cart"> Cart</a></div>
				<div class="sidebarOption"><a class="side" href="${pageContext.request.contextPath}/User/createCard">Add new Credit card</a></div>
				<div class="sidebarOption"><a class="side" href="${pageContext.request.contextPath}/User/userTransactionHistory">Transaction history</a></div>
			</div>
			<div id="content">
	
	 			<p>
	 			<b>PERSONAL INFORMATIONS: </b>
				<p>
				<b>First name</b>: ${personalData.firstName }
				<p>
				<b>Last name:</b> ${personalData.lastName }
				<p>
				<b>Email:</b> ${personalData.email }
				<p>
				<b>ADRESS:</b>
				<p>
				<b>Registered Adress:</b>
				<p>
				<b>City:</b> ${personalData.regPostal } ${personalData.regCity}
				<p> 
				<b>Street:</b> ${personalData.regStreet } ${personalData.regHouseNr }/${personalData.regApartmentNr }
				<p>
				<b>Address for correspondence:</b>
				<p>
				<b>City:</b> ${personalData.contactPostal } ${personalData.contactCity}
				<p> 
				<b>Street:</b> ${personalData.contactStreet } ${personalData.contactHouseNr }/${personalData.contactApartmentNr } 
				<p>
				<b>CREDIT CARD INFO: </b>
				<p>
				<table>
	 				<tr>
	 					<th>Type</th>
	 					<th>Owner</th>
		 				<th>Number</th>
		 				<th>Expire Date</th>
	 				</tr>
	 				<c:forEach var="tempCard" items="${creditCards}">
	 				
	 				<c:url var="cardLink" value="/User/showCardUpdateFrom">
	 					<c:param name="cardId" value="${tempCard.id}"/>
	 				</c:url>	 		
	 				<c:url var="deleteLink" value="/User/deleteCard">
	 					<c:param name="cardId" value="${tempCard.id}"/>
	 				</c:url>	
	 					<tr>
	 						<td>${tempCard.cardType }</td>
	 						<td>${tempCard.firstName} ${tempCard.lastName }</td>
	 						<td>${tempCard.number }</td>
	 						<td>${tempCard.expireDate }</td>
	 						<td>
	 							<a class="side" href="${cardLink}"><b>EDIT</b></a> |
	 							<a class="side"href="${deleteLink }" onclick ="if(!(confirm('Are you sure you want to delete this card? (WARNING: Deleting the card will also delete any transaction associated with that card.)'))) return false"><b>DELETE</b></a>
	 						</td>
	 					</tr>
	 				</c:forEach>
	 			</table>
	 			<p>
			</div>
			<div id="footer">
				Author: Piotr Detko, 2018. All rights reserved. 
			</div>
		</div>
	</body>
</html>