<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">

<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Location</title>
        
        <spring:url value="/resources/core/css/hello.css" var="coreCss" />
        <spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
        <link href="${bootstrapCss}" rel="stylesheet" />
        <link href="${coreCss}" rel="stylesheet" />
</head>

 <spring:url value="/location/list" var="urlHome" />
<spring:url value="/location/add" var="urlAddLocation" />

<nav class="navbar navbar-inverse ">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="${urlHome}">Location Form</a>
		</div>
		<div id="navbar">
			<ul class="nav navbar-nav navbar-right">
				<li class="active"><a href="${urlAddLocation}">Add Location</a></li>
			</ul>
		</div>
	</div>
</nav>

<body>

	<div class="container">
                               
		<c:if test="${not empty msg}">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>${msg}</strong>
			</div>
		</c:if>

		<h1>All Users</h1>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>#LocationID</th>
					<th>Description</th>
					<th>Equip_type</th>
					<th>Location</th>
					<th>Action</th>
				</tr>
			</thead>

			<c:forEach var="user" items="${users}">
				<tr>
					<td>${user.userid}</td>
					<td>${user.firstname}</td>
					<td>${user.lastname}</td>
					<td>${user.email}</td>
					<td>
                                        <spring:url value="/users/${user.userid}" var="userUrl" />
					<spring:url value="/users/${user.userid}/delete" var="deleteUrl" /> 
					<spring:url value="/users/${user.userid}/update" var="updateUrl" />
					<button class="btn btn-info" onclick="location.href='${userUrl}'">Query</button>
					<button class="btn btn-primary" onclick="location.href='${updateUrl}'">Update</button>
					<button class="btn btn-danger" onclick=  "location.href='${deleteUrl}'">Delete</button>
                                        
                                        
                                        </td>
				</tr>
			</c:forEach>
		</table>

               
        
        <hr>
	<footer>
		<p>&copy; AddEnergy.no 2017</p>
	</footer>
	</div>
	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<spring:url value="/resources/core/js/hello.js" var="coreJs" />
<spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs" />
<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>

</body>
</html>