<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        
        <title>Add Users using ajax</title>
        
        <c:url var="listUrl" value="/ajax" scope="request" />
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        
        
    <script type="text/javascript">
      
    function doAjaxPost() {
        // get the form values
        var name = $('#name').val();
        var education = $('#education').val();

        $.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${listUrl}/addAjax",
			data: "name=" + name + "&education=" + education,
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				 $("#info").html(data);
			},
			error : function(e) {
				console.log("ERROR: ", e);
				$("#info").html(data);
			},
			done : function(e) {
				console.log("DONE");
				enableSearchButton(true);
			}
		});
                
         // submit a form
         document.getElementById("logoutForm").submit();
                
        }
      
        
    
        </script>
    </head>
    <body>
        <h1>Add Users using Ajax ........</h1>
        
        
        <form method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
    
        
        <table>
            <tr><td>Enter your name : </td><td> <input type="text" id="name"><br/></td></tr>
            <tr><td>Education : </td><td> <input type="text" id="education"><br/></td></tr>
            <tr><td colspan="2">
                   
                <input type="button" value="Add Users" onclick="doAjaxPost()"> <br/>
                    
                </td></tr>
            <tr><td colspan="2"><div id="info" style="color: green;"></div></td></tr>
        </table>
        
    
        <a href="<c:url value="/ajax/showAjax" />">Show All Users</a>
    </body>
</html>




		