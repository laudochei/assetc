<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
    
<jsp:include page="../fragments/headergeneral.jsp" />

  <head>
    <title>User Support</title>
    <link rel="stylesheet" 
          type="text/css" 
          href="<c:url value="/resources/style.css" />" >
  </head>
  <body>
    <h1>User Support</h1>

    
    <a href="<c:url value="/" />">Contact Us</a> | 
    <a href="<c:url value="/" />">About Us</a> | 
    <a href="<c:url value="/" />">User Manual</a> |
    <a href="<c:url value="/" />">FAQ</a>
    
    <jsp:include page="../fragments/footergeneral.jsp" />
  </body>
</html>
