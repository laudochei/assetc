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
	<h1>Title : ${user.userid}</h1>
	<h1>Message : ${user.username}</h1>

	
		<!-- For login user -->
		<c:url value="/logout" var="logoutUrl" />
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
		<script>
			function formSubmit() {
				document.getElementById("logoutForm").submit();
			}
		</script>

		
                
    <a href="<c:url value="/" />">Home</a> 

<jsp:include page="../fragments/footergeneral.jsp" />
</body>
</html>