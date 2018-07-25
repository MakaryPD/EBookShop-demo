 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
  <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE>
<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
		<title>Book list</title>
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
			
				<div class="sidebarOption"><a class="side" href="${pageContext.request.contextPath}/Admin/userList">Display Users</a></div>
				<div class="sidebarOption"><a class="side" href="${pageContext.request.contextPath}/Admin/bookList">Display Books</a></div>
				<div class="sidebarOption"><a class="side" href="${pageContext.request.contextPath}/Admin/transactionList">Display Transaction history</a></div>
				<div class="sidebarOption"><a class="side" href="${pageContext.request.contextPath}/Admin/">Admin Page</a></div>
			</div>
			<div id="content">
			<p>
			<form:form action="searchByBook" method="POST">
				Search by: 
				<select name="searchCategory">
	 					<option label="-- select option --"/>
	 					<option label="Title" value="title"/>
	 					<option label="Author" value="author"/>
	 					<option label="Publisher" value="publisher"/>
	 					<option label="Type" value="type"/>
	 				</select>
	 				<input type="text" name="searchWord"/> 
	 				<input type="submit" value="search"/>
			</form:form>
			<p>
			<table>
	 				<tr>
	 					<th>Title</th>
	 					<th>Author</th>
	 					<th>Publisher</th>
	 					<th>Year</th>
	 					<th>Type</th>
	 					<th>Price</th>
	 					<th> Vat </th>
	 					<th> Quantity </th>
	 					<th>Enabled<th> 
	 				</tr>
	 			<c:forEach var="tempBook" items="${books}">
	 			
	 			<c:url var="bookLink" value="/Admin/updateBookInfo">
	 				<c:param name="bookId" value="${tempBook.id }"/>
	 			</c:url>
	 			<c:url var="deleteLink" value="/Admin/deleteBook">
	 				<c:param name="bookId" value="${tempBook.id }"/>
	 			</c:url>
	 			<c:url var="disableLink" value="/Admin/disableBook">
	 				<c:param name="bookId" value="${tempBook.id }"/>
	 			</c:url>
	 				<tr>
	 					<td>${tempBook.title}</td>
	 					<td>${tempBook.author}</td>
	 					<td>${tempBook.publisher}</td>
	 					<td>${tempBook.year}</td>
	 					<td>${tempBook.type}</td>
	 					<td>${tempBook.price}</td>
	 					<td>${tempBook.vat}%</td>
	 					<td>${tempBook.number}</td>
	 					<td>${tempBook.enabled }</td>
	 					<td><a class="side" href="${bookLink}"><b>UPDATE</b></a>|<a class="side" href="${deleteLink}" onclick ="if(!(confirm('Are you sure you want to delete this card? (WARING: Deleting a book pernamently will affect Transactions history records.)'))) return false"><b>DELETE</b></a>|
	 					<a class="side" href="${disableLink}"><b>DISABLE/ENABLE</b> </a>
	 					</td>
	 				</tr>
	 			</c:forEach>
	 		</table>
	 		<p>
	 		<form:form action="sortByBook" post="POST">
	 			Sort by: <select name="sortCategory">
	 			<option label="-- select option --"/>
	 			<option label="Title" value="title"/>
	 			<option label="Author" value="author"/>	
	 			<option label="Publisher" value="publisher"/>
 				<option label="Type" value="type"/>
 				<option label="Price" value="price"/>
	 			</select>
	 			<input type="submit" value="sort"/>
	 		</form:form>
	 		<p>
	 		<a class="side" href="${pageContext.request.contextPath}/Admin/addBook"><b>Add a new book</b></a>
	 		<p>
			</div>
			<div id="footer">
				Author: Piotr Detko, 2018. All rights reserved. 
			</div>
		</div>
	</body>
</html>