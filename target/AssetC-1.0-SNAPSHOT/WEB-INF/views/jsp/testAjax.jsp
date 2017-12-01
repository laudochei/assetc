<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
    <title></title>
    
    <c:url var="home" value="/builds" scope="request" />
    
    
    
    <spring:url value="/resources/kendo/styles/kendo.common.min.css" var="kendoCommonCss" />
    <spring:url value="/resources/kendo/styles/kendo.default.min.css" var="kendoDefaultCss" />
    <spring:url value="/resources/kendo/styles/kendo.default.mobile.min.css" var="kendoMobileCss" />
    
    
    
    <link href="${kendoCommonCss}" rel="stylesheet" />
    <link href="${kendoDefaultCss}" rel="stylesheet" />
    <link href="${kendoMobileCss}" rel="stylesheet" />
    
    
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <spring:url value="/resources/kendo/js/jquery.min.js" var="jqueryJs" />
    <spring:url value="/resources/kendo/js/kendo.all.min.js" var="kendoJs" />
    <script src="${jqueryJs}"></script>
    <script src="${kendoJs}"></script>
    
   



</head>
<body >
    
    
 <script>
    
  
   
        
        
        $(document).ready(function() {
    $('##translate').submit(
        function(event) {
            var firstname = $('#firstname').val();
            var lastname = $('#lastname').val();                
            var data = 'firstname='
                    + encodeURIComponent(firstname)
                    + '&amp;lastname='
                    + encodeURIComponent(lastname);
            $.ajax({
                url : "${home}/asset/deletenode",
                data : data,
                type : "GET",
 
                success : function(response) {
                    alert( response );
                },
                error : function(xhr, status, error) {
                    alert(xhr.responseText);
                }
            });
            return false;
        });
    });
         
</script>








<input type="text" name="t5" id="t5" class="l-textbox" value="test-value" /><br/>
<button id="translate">Translate</button><br/><br/>
<div id="container"></div>
</body>
</html>



    