<%@ page import="java.sql.*" %>
<%@ page import="assetc.model.Employee"%>

<%
        int p = Integer.parseInt(request.getParameter("p")); //11;
        
        //create prepared statement and result set objects
        PreparedStatement stmt = null;
        
   try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/contactdb", "root", "welcome123");
        // build the sql string
        String sql = "DELETE FROM employees WHERE EmployeeID = ?";

       // prepare the statement for safe execution
        stmt = con.prepareStatement(sql);
        
        // set the id of the product to delete
        stmt.setInt(1, p);
       
        // execute the delete
        stmt.executeUpdate();
      }
      finally {
        // close all connection related instances
        stmt.close();
      }
%>
Name:<%out.print("Amr");%>




