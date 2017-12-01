<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

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

			<c:forEach var="location" items="${locations}">
				<tr>
					<td>${location.locationid}</td>
					<td>${location.desc}</td>
					<td>${location.equipmenttype}</td>
					<td>${location.physicallocation}</td>
					<td>
                                        <spring:url value="/location/${locationid.locationid}" var="locationUrl" />
					<spring:url value="/location/${locationid.locationid}/delete" var="deleteUrl" /> 
					<spring:url value="/location/${location.locationid}/update" var="updateUrl" />
					<button class="btn btn-info" onclick="location.href='${locationUrl}'">Query</button>
					<button class="btn btn-primary" onclick="location.href='${updateUrl}'">Update</button>
					<button class="btn btn-danger" onclick=  "location.href='${deleteUrl}'">Delete</button>
                                        
                                        
                                        </td>
				</tr>
			</c:forEach>
		</table>

               
           
	</div>
	<jsp:include page="../fragments/footer.jsp" />
</body>
</html>