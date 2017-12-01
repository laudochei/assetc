<%-- 
    Document   : login
    Created on : 24-Aug-2017, 13:03:37
    Author     : Laud.Ochei
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
      <h1>Add Energy - SignIn</h1>
      <form method="POST">
      <table border="0"  width="0%">
        <tr><!-- Row 1 -->
           <td>Username</td><!-- Col 1 -->
           <td><input type="text" name="username" size="22" /></td><!-- Col 2 -->
        </tr>
        <tr><!-- Row 2 -->
           <td>Password</td><!-- Col 1 -->
           <td><input type="password" name="password"  size="25"/></td><!-- Col 2 -->
        </tr>
        <tr><!-- Row 3 -->
           <td></td><!-- Col 1 -->
           <td><input type="submit" value="Login" /></td><!-- Col 2 -->
        </tr>
      </table>
    </form>

</html>
