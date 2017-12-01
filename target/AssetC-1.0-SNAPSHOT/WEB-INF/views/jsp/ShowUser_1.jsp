<%@page session="false"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<html>
    <head>
        <title>Users Added using Ajax</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        
    </head>
    <body style="color: green;">
    The following are the users added in the list :<br>
        <ul>
            <c:forEach items="${Users}" var="user">
                <li>Name : <c:out value="${user.name}" />; Education : <c:out value="${user.education}"/>
            </c:forEach>
        </ul>
    
    <a href="<c:url value="/userlist/addAjaxList" />">Ajax Form</a>
    </body>
    
</html>
