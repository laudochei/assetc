<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<div class="container">

	<c:if test="${not empty msg}">
		<div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong>${msg}</strong>
		</div>
	</c:if>

	<h1>User Detail</h1>
	<br />

	<div class="row">
		<label class="col-sm-2">ID</label>
		<div class="col-sm-10">${user.userid}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Username</label>
		<div class="col-sm-10">${user.username}</div>
	</div>
	
	<div class="row">
		<label class="col-sm-2">First name</label>
		<div class="col-sm-10">${user.firstname}</div>
	</div>
	
	<div class="row">
		<label class="col-sm-2">Last name</label>
		<div class="col-sm-10">${user.lastname}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Email</label>
		<div class="col-sm-10">${user.email}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Address</label>
		<div class="col-sm-10">${user.address}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Phone</label>
		<div class="col-sm-10">${user.phone}</div>
	</div>
        
        <c:url value="/users/${user.userid}" var="profileUrl" />

	<!-- csrt support -->
        <c:url value="/users/list" var="listUrl" />

	<!-- csrt support -->
	<form action="${profileUrl}" method="post" id="profileForm">
		<input type="hidden"
			name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>

	<script>
		function formSubmit() {
			document.getElementById("profileForm").submit();
		}
	</script>
</div>
<jsp:include page="../fragments/footer.jsp" />

</body>
</html>

