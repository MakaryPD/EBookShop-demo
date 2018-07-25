 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
  <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE>
<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
		<title>Home</title>
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
				<br>
					Welcome to eBookShop Homepage. 
					<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Tempor commodo ullamcorper a lacus vestibulum. Facilisi nullam vehicula ipsum a arcu cursus vitae congue mauris. Nunc sed velit dignissim sodales ut eu. Turpis egestas pretium aenean pharetra magna ac placerat vestibulum lectus. Vestibulum lectus mauris ultrices eros in cursus turpis massa tincidunt. Egestas erat imperdiet sed euismod nisi porta. Enim nec dui nunc mattis. Ultrices eros in cursus turpis massa. Duis tristique sollicitudin nibh sit amet commodo nulla facilisi nullam. Lectus quam id leo in vitae turpis massa. Egestas fringilla phasellus faucibus scelerisque eleifend. Netus et malesuada fames ac turpis egestas. Integer eget aliquet nibh praesent tristique magna sit amet. Mattis nunc sed blandit libero volutpat sed cras. Habitant morbi tristique senectus et. Quam lacus suspendisse faucibus interdum posuere lorem ipsum dolor sit. Et molestie ac feugiat sed lectus vestibulum.
					<p>Ut lectus arcu bibendum at varius vel pharetra vel. Volutpat diam ut venenatis tellus in metus vulputate eu. Nulla facilisi morbi tempus iaculis urna id volutpat. Et malesuada fames ac turpis egestas maecenas pharetra convallis. Accumsan in nisl nisi scelerisque eu ultrices vitae auctor. Sed cras ornare arcu dui vivamus arcu felis bibendum. Id interdum velit laoreet id donec ultrices tincidunt arcu. Nulla facilisi morbi tempus iaculis urna id volutpat lacus laoreet. Diam maecenas sed enim ut sem. Urna neque viverra justo nec ultrices. Pretium fusce id velit ut. Tempus imperdiet nulla malesuada pellentesque elit eget. Vel quam elementum pulvinar etiam. Volutpat blandit aliquam etiam erat velit scelerisque in. Est ullamcorper eget nulla facilisi etiam dignissim diam quis.
					<p>Et leo duis ut diam quam nulla porttitor massa id. Blandit turpis cursus in hac habitasse platea dictumst quisque. Odio ut enim blandit volutpat maecenas volutpat. Et leo duis ut diam quam nulla. Tellus at urna condimentum mattis pellentesque. Pellentesque id nibh tortor id aliquet. Penatibus et magnis dis parturient montes nascetur ridiculus mus mauris. Cras tincidunt lobortis feugiat vivamus at augue eget arcu. Imperdiet proin fermentum leo vel orci porta non pulvinar. Urna neque viverra justo nec ultrices dui sapien eget mi. Ultrices eros in cursus turpis massa tincidunt. Etiam sit amet nisl purus in. Ac ut consequat semper viverra. Elementum integer enim neque volutpat ac tincidunt. Viverra justo nec ultrices dui sapien eget mi. Sit amet nulla facilisi morbi tempus iaculis urna id. Nulla at volutpat diam ut venenatis.
					<p>At auctor urna nunc id. Maecenas accumsan lacus vel facilisis volutpat est velit egestas dui. Amet facilisis magna etiam tempor orci eu. Sed odio morbi quis commodo. Tortor posuere ac ut consequat semper viverra nam libero justo. Aliquam ultrices sagittis orci a scelerisque purus semper eget duis. Cursus in hac habitasse platea dictumst quisque sagittis. Nisl rhoncus mattis rhoncus urna. In nibh mauris cursus mattis. Vel pretium lectus quam id leo. Morbi quis commodo odio aenean sed adipiscing. Laoreet suspendisse interdum consectetur libero id faucibus. Sollicitudin ac orci phasellus egestas. Tellus pellentesque eu tincidunt tortor aliquam nulla facilisi. Duis convallis convallis tellus id interdum velit laoreet id donec. Auctor urna nunc id cursus metus aliquam eleifend.
			</div>
			<div id="footer">
				Author: Piotr Detko, 2018. All rights reserved. 
			</div>
		</div>
	</body>
</html>