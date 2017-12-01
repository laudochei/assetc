<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<head>
<title>Location</title>

<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
</head>
                    
<body>
<spring:url value="/locations/locationlist" var="urlHomeLocation" />
<spring:url value="/locations/add" var="urlAddLocation" />

<nav class="navbar navbar-inverse ">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="${urlHomeLocation}">Location List</a>
		</div>
		<div id="navbar">
			<ul class="nav navbar-nav navbar-right">
				<li class="active"><a href="${urlAddLocation}">Add Location</a></li>
			</ul>
		</div>
	</div>
</nav>
<div class="container">

	<c:if test="${not empty msg}">
		<div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong>${msg}</strong>
		</div>
	</c:if>

	<h1>Location Detail</h1>
	<br />

	<div class="row">
		<label class="col-sm-2">ID</label>
		<div class="col-sm-10">${location.locationno}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Location</label>
		<div class="col-sm-10">${location.locationid}</div>
	</div>
        <div class="row">
		<label class="col-sm-2">Parent</label>
		<div class="col-sm-10">${location.parentname}</div>
	</div>
	
	<div class="row">
		<label class="col-sm-2">Description</label>
		<div class="col-sm-10">${location.description}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Parent Craft</label>
		<div class="col-sm-10">${location.parentcraft}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Manufacturer</label>
		<div class="col-sm-10">${location.manufacturer}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Model No</label>
		<div class="col-sm-10">${location.modelnum}</div>
	</div>
	
	
</div>

<div class="container">
	<hr>
	<footer>
		<p>&copy; AddEnergy.no 2017</p>
	</footer>
</div>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

<spring:url value="/resources/core/js/hello.js" var="coreJs" />
<spring:url value="/resources/core/js/bootstrap.min.js"
	var="bootstrapJs" />

<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>

</body>
</html>