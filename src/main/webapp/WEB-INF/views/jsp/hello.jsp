<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    
    <jsp:include page="../fragments/headergeneral.jsp" />
    <head>
    <title>Add Energy</title>
    <link rel="stylesheet" 
          type="text/css" 
          href="<c:url value="/resources/style.css" />" >
  </head>
<body>
	<h1>Title : ${title}</h1>
	<h1>Message : ${message}</h1>

	<sec:authorize access="hasRole('ROLE_USER')">
		<!-- For login user -->
		<c:url value="/logout" var="logoutUrl" />
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
		<script>
			function formSubmit() {
				document.getElementById("logoutForm").submit();
			}
		</script>

		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h2>
				User : ${pageContext.request.userPrincipal.name} | <a
					href="javascript:formSubmit()"> Logout</a>
			</h2>
		</c:if>


	</sec:authorize>
                
    <a href="<c:url value="/account/login" />">Login</a> | 
    <a href="<c:url value="/users/add" />">Register</a> | 
    <a href="<c:url value="/users/list" />">User</a> |
    <a href="<c:url value="/admin/list" />">Admin</a>|
    <a href="<c:url value="/assets/assetlist" />">Asset</a>|
    <a href="<c:url value="/locations/locationlist" />">Location</a>|
    <a href="<c:url value="/reports/reportlist" />">Reports</a>|
    <a href="<c:url value="/uploads/uploadslist" />">Uploads</a>|
    <a href="<c:url value="/builds/asset" />">Asset Build</a>|
    <a href="<c:url value="/builds/treeview" />">Tree View</a>|
    <a href="<c:url value="/userlist/addAjaxList" />">Ajax</a>|
    <a href="<c:url value="/db/showdb" />">DB-Tasks</a>
    <a href="<c:url value="/db/createdb" />">CreateDB</a>
    
    
    
<jsp:include page="../fragments/footergeneral.jsp" />







</body>
</html>