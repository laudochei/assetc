<%@ page import="java.sql.*" %>
<%@ page import="assetc.model.Employee"%>
<%@ page import = "java.util.ArrayList" %>

<%
       
        

   try {
        Class.forName("com.mysql.jdbc.Driver");
        //Connection con = DriverManager.getConnection("jdbc:mysql://us-cdbr-iron-east-05.cleardb.net:3306/heroku_7ac45a05bab0085", "b4778f9d520c68", "132d6c92");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/contactdb", "root", "welcome123");
         
        Statement stmt = con.createStatement();
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
        stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
        ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

        ArrayList<String> output = new ArrayList<String>();
        while (rs.next()) {
          output.add("Read from DB: " + rs.getTimestamp("tick"));
        }

        //model.put("records", output);
        //return "db";
      } catch (Exception e) {
        //model.put("message", e.getMessage());
        //return "error";
      }
     

  
%>
Name:<%out.print("Laud is OK-1");%>




