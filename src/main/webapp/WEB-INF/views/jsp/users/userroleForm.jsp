<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <jsp:include page="../fragments/header.jsp" />
    <head>
       
        <title>UserRole</title>
    </head>
    <body>
        <h1>User Role</h1>
  
  <div class="container">  
      
      <c:choose>
		<c:when test="${addUser['new']}">
			<h1>Add User_Role</h1>
		</c:when>
		<c:otherwise>
			<h1>Update User_Role</h1>
		</c:otherwise>
	</c:choose>
	<br />
        
        
        
        <form:form class="form-horizontal" method="post" modelAttribute="addUser" >
	<form:hidden path="userid" />
              
        <table border="0"  width="0%">
        
        <tr><!-- Row 4 -->
           <td>Username:</td><!-- Col 1 -->
           <td><spring:bind path="username">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<div class="col-sm-10">
					<form:input path="username" type="text" class="form-control " id="username" placeholder="username" />
					<form:errors path="username" class="control-label" />
				</div>
			</div>
		</spring:bind></td><!-- Col 2 -->
        </tr>

        <tr><!-- Row 3 -->
           <td>Role:</td><!-- Col 1 -->
           <td><spring:bind path="role">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<div class="col-sm-10">
					<form:input path="role" class="form-control" id="email" placeholder="role" />
					<form:errors path="role" class="control-label" />
				</div>
			</div>
		</spring:bind></td><!-- Col 2 -->
        </tr>
        
        
        
        
       <tr><!-- Row 4 -->
           <td></td><!-- Col 1 -->
           <td>
               <div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<c:choose>
					<c:when test="${addUser['new']}">
						<button type="submit" class="btn-lg btn-primary pull-right">Add</button>
					</c:when>
					<c:otherwise>
						<button type="submit" class="btn-lg btn-primary pull-right">Update</button>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
           </td><!-- Col 2 -->
        </tr>
        </table>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
   </form:form>
    
    </div>  
    
    
    <jsp:include page="../fragments/footer.jsp" />
    </body>
</html>