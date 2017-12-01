<%@page session="false"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<html>
    <head>
        <title>JSP Page</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        
        <script type="text/javascript">    
        $(document).ready(function() {
                // process the form
                $('loginForm').submit(function(event) {

                    // get the form values
                    var name = $('#name').val();
                    var education = $('#education').val();

                    $.ajax({
                                    type : "POST",
                                    contentType : "application/json",
                                    url : "${UserListUrl}/addAjaxList",
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
                    // stop the form from submitting the normal way and refreshing the page
                    //event.preventDefault();
                    //return false;
            });
            // stop the form from submitting the normal way and refreshing the page
            event.preventDefault();
            return false;
        });
        </script>
        
    </head>
    <body>
        
        <div class="col-sm-6 col-sm-offset-3">
            <h1>Processing an AJAX Form</h1>
            
            <c:url var="UserListUrl" value="/userlist" scope="request" />
            <form name='loginForm' action="<c:url value='/userlist/addAjaxList' />" method='POST'>
                                <table>
                                        <tr>
                                                <td>Name:</td>
                                                <td><input type='text' name='name'></td>
                                        </tr>
                                        <tr>
                                                <td>Education:</td>
                                                <td><input type='text' name='education' /></td>
                                        </tr>
                                        <tr>
                                            <td colspan='2'><input name="submit" type="submit" value="submit" /></td>
                                        </tr>
                                </table>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </form>
        </div>
                
                <a href="<c:url value="/userlist/showAjaxList" />">Show All Users</a>
    </body>
</html>
