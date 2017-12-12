<%@ page import="java.sql.*" %>
<%@ page import="assetc.model.Employee"%>
<%@ page import = "java.util.ArrayList" %>

<%
       
   try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://us-cdbr-iron-east-05.cleardb.net:3306/heroku_7ac45a05bab0085", "b4778f9d520c68", "132d6c92");
        //Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/contactdb", "root", "welcome123");
        
        Statement stmt = con.createStatement();
        //stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
        
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS users (userid int(11) NOT NULL AUTO_INCREMENT, username varchar(45) NOT NULL, password varchar(45) NOT NULL, firstname varchar(45) DEFAULT NULL, lastname varchar(45) DEFAULT NULL, email varchar(45) DEFAULT NULL, address varchar(45) DEFAULT NULL, phone int(10) DEFAULT NULL, enabled tinyint(4) NOT NULL DEFAULT '1', PRIMARY KEY (username), UNIQUE KEY userid_UNIQUE (userid))ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8");
        

        //stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
        //stmt.executeUpdate("INSERT INTO users2 (userid, username, password, firstname, lastname, email, address, phone, enabled) VALUES (15, 'mkdons1', '123456', 'mkyong', 'stormzy', 'stormzy@yaddenergy.no', 'aberdeen', 1234, 1)");
        stmt.executeUpdate("INSERT INTO users (username, password, firstname, lastname, email, address, phone, enabled) VALUES ('mkyong', '123456', 'mkyong', 'stormzy', 'stormzy@yaddenergy.no', 'aberdeen', 1234, 1)");

        
        
        ResultSet rs = stmt.executeQuery("SELECT * FROM users");

        ArrayList<String> output = new ArrayList<String>();
        while (rs.next()) {
         
           System.out.println("Usernames: " + rs.getString("username"));
                   }

        
      } catch (Exception e) {
        
             e.printStackTrace();
        
      }
     

  
%>
Name:<%out.print("Laud is OK");%>
