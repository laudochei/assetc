<%@ page import="java.sql.*" %>
<%@ page import="assetc.model.Employee"%>


<%
       
   try {
        Class.forName("com.mysql.jdbc.Driver");
        //Connection con = DriverManager.getConnection("jdbc:mysql://us-cdbr-iron-east-05.cleardb.net:3306/heroku_7ac45a05bab0085", "b4778f9d520c68", "132d6c92");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/contactdb", "root", "welcome123");
        
        Statement stmt = con.createStatement();
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
        stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
        ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

       
        while (rs.next()) {
         
           System.out.println("Timestamp: " + rs.getTimestamp("tick"));
        }

        
      } catch (Exception e) {
        
      }
     

  
%>
Name:<%out.print("Laud is OK");%>
