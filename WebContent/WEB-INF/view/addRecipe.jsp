<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<title>Tasteology Administrator Page</title>
		<link rel="stylesheet" href="<c:url value="resources/assets/css/style1.css"/>" />
		<link rel="stylesheet" href="<c:url value="font-awesome-4.4.0/css/font-awesome.min.css"/>" />
		<script src="<c: url value="script.js"/>"></script>
		<script src="<c: url value="addEditContact.js"/>"></script>

		<link rel="shortcut icon" href="<c:url value="/resources/assets/img/tasteologyicon.png" />" />
		<script src="<c:url value="resources/assets/js/jquery.min.js" />"></script>
		<script src="<c:url value="resources/assets/js/jquery-migrate-1.2.1.min.js"/>"></script>
	</head>
	<body>
		<div id="mainContent">
			<h1><b>Tasteology Administrator Page</b></h1>
			<h2>Add New Recipe</h2>
			<form id="edit-contact-form" onsubmit="return checkSource('contact');">
				New Recipe: <input type="text" value="" required /><br/>
				Category:  <input type="text" required /><br/>
				Add Other Info: <br/>
				<a id="add-contact-info" onclick="showContactInfo();"><i class="fa fa-plus"></i> Add Other Info</a>
				<div id="add-contact-info-form">
					<select id="contact-type">
						<option val="Ingredient">Ingredient</option>
						<option val="Tag">Tag</option>
					</select>
					<input id="contact" type="text" placeholder="Other Info" />
					<a id="submit-contact-info" onclick="addContactInfo();">Add Other Info</a>
				</div>
				<ul id="contact-info">
				</ul>
				<input type="submit" value="Add Recipe" />

				<c:if var="result" test="${!status}">
					Error!
				</c:if>

				<c:if test="${result}">

			</form> 
		</div>
		<div id="contact-error" >
			<div id="contact-modal">
				Please enter valid information in all fields in the recipe info form.<br/>
				<button onclick="$('#contact-error').fadeOut();">OK</button>
			</div>
		</div>
	</body>
</html>