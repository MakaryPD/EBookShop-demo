<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
	<head>
		<link href="${pageContext.request.contextPath}/resources/css/login.css" rel="stylesheet">
		<title>Registration Form</title>
		<style>
		.failed{
			color: red; 
		}
		</style>
	</head>
	<body>
	<div id="container">
		<form:form action="${pageContext.request.contextPath}/register/processRegistrationForm" modelAttribute="user">
			<!-- Check for registration error -->
			<h2>Registration Form</h2>
				<c:if test="${registrationError != null}">
					<i class= failed>${registrationError}</i>
			</c:if>
			<p>
			<b>User:</b>
			<p>
			Username: *
			<form:input path="username" placeholder="username"/> <form:errors path="username"/>
			<p>
			Password: * 
			<form:password path="password" placeholder="password"/>  <form:errors path="password"/>
			<p>
			======================================================
			<p>
			<b>Personal Data: </b>
			<p>
			First name: * 
			<form:input path="firstName" placeholder="First name"/>  <form:errors path="firstName"/>
			<p>
			<p>
			Last name: * 
			<form:input path="lastName" placeholder="Last name"/>  <form:errors path="lastName"/>
			<p>
			<p>
			Email: * 
			<form:input path="email" placeholder="email@"/>  <form:errors path="email"/>
			<p>
			<p>
			======================================================
			<p>
			<b>Registered Adress: </b>
			<p>
			Postal : *   
			<form:input path="reg_postal" placeholder="__-___" maxlength="5"/>  <form:errors path="reg_postal"/>
			 City: * 
			<form:input path="reg_city" placeholder="City"/>  <form:errors path="reg_city"/>
			<p>
			Street: *
			<form:input path="reg_street" placeholder="Street" class="street"/>  <form:errors path="reg_street"/>
			House: *
			<form:input path="reg_houseNr" placeholder="House nr" class="street"/>  <form:errors path="reg_houseNr"/>
			Apartment: *
			<form:input path="reg_apartmentNr" placeholder="Apartment nr" class="street"/>  <form:errors path="reg_apartmentNr"/>
			<p>
			<p>
			======================================================
			
			<p>
			<b>Contact Adress: </b>
			<p>
			<form:checkbox path="checkbox"/> Same as registered adress.
			<p>
			Postal :       
			<form:input path="contact_postal" placeholder="__-___" maxlength="5"/>
			 City: 
			<form:input path="contact_city" placeholder="City"/>
			<p>
			Street: 
			<form:input path="contact_street" placeholder="Street" class="street"/>
			House: 
			<form:input path="contact_houseNr" placeholder="House nr" class="street"/>
			Apartment: 
			<form:input path="contact_apartmentNr" placeholder="Apartment nr" class="street"/>
			<p>
			<button type="submit">Register</button>
		</form:form>
		</div>
	</body>
</html>