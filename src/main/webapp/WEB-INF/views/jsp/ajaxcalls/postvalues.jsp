<%@page session="false"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        
        <title>Add Users using ajax</title>
        
        <c:url var="home" value="/ajaxcalls" scope="request" />
        
        <spring:url value="/resources/core/css/hello.css" var="coreCss" />
        <spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
        <link href="${bootstrapCss}" rel="stylesheet" />
        <link href="${coreCss}" rel="stylesheet" />

        <spring:url value="/resources/core/js/jquery.1.10.2.min.js" var="jqueryJs" />
        <script src="${jqueryJs}"></script>
        
         <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        
        <script type="text/javascript">
        var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken = $("meta[name='_csrf']").attr("content");
    
        
        function doAjaxPost() {
        
        // get the form values
        var name = $('#name').val();
        var education = $('#education').val();
        var data = {};
	data[csrfParameter] = csrfToken;
	data["name"] = "John";

        $.ajax({
        type: "GET",
        url : "${home}/display/getrequest",
        data: data,
        //data: "name=" + name + "&education=" + education,
        success: function(response){
        // we have the response
        //$('#info').html(response);
        //$('#name').val('');
        //$('#education').val('');
        },
        error: function(e){
        alert('Error: ' + e);
        }
        });
        }
        </script>
       
			
    </head>
    <body>
        <h1>Add Users using Ajax ........</h1>
        
        
           <form name="treeForm">
            <table>
            <tr><td>Enter your name : </td><td> <input type="text" id="name"><br/></td></tr>
            <tr><td>Education : </td><td> <input type="text" id="education"><br/></td></tr>
            <tr><td colspan="2"><input type="Submit" value="Add Users" onclick="doAjaxPost()"><br/></td></tr>
            <tr><td colspan="2"><div id="info" style="color: green;"></div></td></tr>
            </table>
	    
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>
                                    
        
        <a href="<c:url value="/ShowUser" />">Show All Users</a>
    </body>
</html>