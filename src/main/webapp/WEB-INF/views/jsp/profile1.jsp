<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
  <head>
    <title>Spitter</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" >
  </head>
  <body>
    <h1>Your Profile</h1>
    <c:out value="${user.username}" /><br/>
    <c:out value="${user.firstname}" /> <c:out value="${user.lastname}" /><br/>
    <c:out value="${user.email}" /><br/>
    <c:out value="${user.userid}" />
     
  
  <a href="<c:url value="/" />">Home</a> 
  </body>
</html>
