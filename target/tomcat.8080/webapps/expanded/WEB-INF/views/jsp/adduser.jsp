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
       
        <title>Add User</title>
    </head>
    <body>
        <h1>Add Energy- SignUp</h1>
  
  <div class="container">  
      
      <c:choose>
		<c:when test="${addUser['new']}">
			<h1>Add User</h1>
		</c:when>
		<c:otherwise>
			<h1>Update User</h1>
		</c:otherwise>
	</c:choose>
	<br />
        
        
        
        <form:form class="form-horizontal" method="post" modelAttribute="addUser" >
	<form:hidden path="userid" />
              
        <table border="0"  width="0%">
        <tr><!-- Row 1 -->
           <td>First name:</td><!-- Col 1 -->
           <td>
               
               <spring:bind path="firstname">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<div class="col-sm-10">
					<form:input path="firstname" type="text" class="form-control " id="firstname" placeholder="firstname" />
					<form:errors path="firstname" class="control-label" />
				</div>
			</div>
		</spring:bind>
           
           </td><!-- Col 2 -->
        </tr>
        <tr><!-- Row 2 -->
           <td>Last name:</td><!-- Col 1 -->
           <td><spring:bind path="lastname">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<div class="col-sm-10">
					<form:input path="lastname" type="text" class="form-control " id="lastname" placeholder="lastname" />
					<form:errors path="lastname" class="control-label" />
				</div>
			</div>
		</spring:bind>
           </td><!-- Col 2 -->
        </tr>
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
           <td>Email:</td><!-- Col 1 -->
           <td><spring:bind path="email">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<div class="col-sm-10">
					<form:input path="email" class="form-control" id="email" placeholder="email" />
					<form:errors path="email" class="control-label" />
				</div>
			</div>
		</spring:bind></td><!-- Col 2 -->
        </tr>
        <tr><!-- Row 4 -->
           <td>Password:</td><!-- Col 1 -->
           <td><spring:bind path="password">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<div class="col-sm-10">
					<form:password path="password" class="form-control" id="password" placeholder="password" />
					<form:errors path="password" class="control-label" />
				</div>
			</div>
		</spring:bind>
           </td><!-- Col 2 -->
        </tr>
        <tr><!-- Row 4 -->
           <td>Confirm Password:</td><!-- Col 1 -->
           <td><spring:bind path="confirmPassword">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<div class="col-sm-10">
					<form:password path="confirmPassword" class="form-control" id="password" placeholder="password" />
					<form:errors path="confirmPassword" class="control-label" />
				</div>
			</div>
		</spring:bind></td><!-- Col 2 -->
        </tr>
        <tr><!-- Row 4 -->
           <td>Address:</td><!-- Col 1 -->
           <td><spring:bind path="address">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<div class="col-sm-10">
					<form:textarea path="address" rows="5" class="form-control" id="address" placeholder="address" />
					<form:errors path="address" class="control-label" />
				</div>
			</div>
		</spring:bind></td><!-- Col 2 -->
        </tr>
        <tr><!-- Row 4 -->
           <td>Phone:</td><!-- Col 1 -->
           <td><spring:bind path="phone">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<div class="col-sm-10">
                                        <form:input path="phone" class="form-control" id="phone" placeholder="phone" />
					<form:errors path="phone" class="control-label" />
				</div>
			</div>
		</spring:bind></td><!-- Col 2 -->
        </tr>
        
        
        <tr><!-- Row 4 -->
           <td>Enabled:</td><!-- Col 1 -->
           <td><spring:bind path="enabled">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<div class="col-sm-10">
					<div class="checkbox">
						<form:checkbox path="enabled" id="enabled"/>
						<form:errors path="enabled" class="control-label" />
					</div>
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