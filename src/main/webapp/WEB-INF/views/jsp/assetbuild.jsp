<%@taglib prefix="sec" 	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
   
    <head>
    <title>Add Energy</title>
    <link rel="stylesheet" 
          type="text/css" 
          href="<c:url value="/resources/style.css" />" >
  </head>
    <body>
        <h1>Welcome to Asset Register</h1>
        
        <a href="<c:url value="/builds/asset/tableview" />">Table View</a>|
        <a href="<c:url value="/builds/asset/treeview" />">Tree View</a>|
        <a href="<c:url value="/builds/asset/deletenode" />">Delete</a>|
        <a href="<c:url value="/builds/asset/testAjax" />">Ajax Call</a>|
        <a href="<c:url value="/builds/asset/treeview_live" />">Tree View-Live</a>|
        <a href="<c:url value="/builds/asset/tableview_live" />">Table View-Live</a>|
        <a href="<c:url value="/display/viewtree" />">View Tree</a>|
        <a href="<c:url value="/display/viewtable" />">View Table</a>
    
    </body>
</html>
