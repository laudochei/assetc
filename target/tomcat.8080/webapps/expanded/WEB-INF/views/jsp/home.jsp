<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
    
<jsp:include page="../fragments/header.jsp" />

  <head>
    <title>Add Energy</title>
    <link rel="stylesheet" 
          type="text/css" 
          href="<c:url value="/resources/style.css" />" >
  </head>
  <body>
    <h1>Add Energy</h1>

    
    <a href="<c:url value="/account/login" />">Login</a> | 
    <a href="<c:url value="/users/add" />">Register</a> | 
    <a href="<c:url value="/users/list" />">User</a> |
    <a href="<c:url value="/admin/list" />">Admin</a>|
    <a href="<c:url value="/location/list" />">Location</a>|
    <a href="<c:url value="/location/listlocation" />">Location</a>
    
    <jsp:include page="../fragments/footer.jsp" />
  </body>
</html>
