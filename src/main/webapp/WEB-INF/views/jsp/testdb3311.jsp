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

	stmt.executeUpdate("DROP TABLE IF EXISTS users, user_roles");        
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS users (userid int(11) NOT NULL AUTO_INCREMENT, username varchar(45) NOT NULL, password varchar(45) NOT NULL, firstname varchar(45) DEFAULT NULL, lastname varchar(45) DEFAULT NULL, email varchar(45) DEFAULT NULL, address varchar(45) DEFAULT NULL, phone int(10) DEFAULT NULL, enabled tinyint(4) NOT NULL DEFAULT '1', PRIMARY KEY (username), UNIQUE KEY userid_UNIQUE (userid))ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8");
        //stmt.executeUpdate("CREATE TABLE IF NOT EXISTS user_roles2 (userid int(11) NOT NULL AUTO_INCREMENT, username varchar(45) NOT NULL, password varchar(45) NOT NULL, firstname varchar(45) DEFAULT NULL, lastname varchar(45) DEFAULT NULL, email varchar(45) DEFAULT NULL, address varchar(45) DEFAULT NULL, phone int(10) DEFAULT NULL, enabled tinyint(4) NOT NULL DEFAULT '1', PRIMARY KEY (username), UNIQUE KEY userid_UNIQUE (userid))ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8");
        
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS user_roles (user_role_id int(11) NOT NULL AUTO_INCREMENT, username varchar(45) NOT NULL, role varchar(45) NOT NULL, PRIMARY KEY (user_role_id), UNIQUE KEY uni_username_role (role,username),KEY fk_username_idx (username), CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username)) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8");



        //stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
        //stmt.executeUpdate("INSERT INTO users2 (userid, username, password, firstname, lastname, email, address, phone, enabled) VALUES (1, 'mkdons1', '123456', 'mkyong', 'stormzy', 'stormzy@yaddenergy.no', 'aberdeen', 1234, 1)");
        stmt.executeUpdate("INSERT INTO users (username, password, firstname, lastname, email, address, phone, enabled) VALUES ('mkyong', '123456', 'mkyong', 'stormzy', 'stormzy@yaddenergy.no', 'aberdeen', 1234, 1)");        
        stmt.executeUpdate("INSERT INTO user_roles (user_role_id, username, role) VALUES (1, 'mkyong', 'ROLE_ADMIN')");
        
        
        //ResultSet rs = stmt.executeQuery("SELECT * FROM users");
        ResultSet rs = stmt.executeQuery("SELECT * FROM user_roles");

        //ArrayList<String> output = new ArrayList<String>();
        while (rs.next()) {
         
           System.out.println("Roles: " + rs.getString("role"));
                   }

        
      } catch (Exception e) {
        
             e.printStackTrace();
        
      }
     

  
%>
Name:<%out.print("Laud is OK");%>
