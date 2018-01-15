<%@ page import="java.sql.*" %>
<%@ page import="assetc.model.Employee"%>

<%
       
   try {
        Class.forName("com.mysql.jdbc.Driver");
        //Connection con = DriverManager.getConnection("jdbc:mysql://us-cdbr-iron-east-05.cleardb.net:3306/heroku_7ac45a05bab0085", "b4778f9d520c68", "132d6c92");
       Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assetc", "root", "welcome123");
        
        Statement stmt = con.createStatement();
        
        //stmt.executeUpdate("DROP TABLE IF EXISTS users, user_roles, location, asset");
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS users (userid int(11) NOT NULL AUTO_INCREMENT, username varchar(45) NOT NULL, password varchar(45) NOT NULL, firstname varchar(45) DEFAULT NULL, lastname varchar(45) DEFAULT NULL, email varchar(45) DEFAULT NULL, address varchar(45) DEFAULT NULL, phone int(10) DEFAULT NULL, enabled tinyint(4) NOT NULL DEFAULT '1', PRIMARY KEY (username), UNIQUE KEY userid_UNIQUE (userid))ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8");
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS user_roles (user_role_id int(11) NOT NULL AUTO_INCREMENT, username varchar(45) NOT NULL, role varchar(45) NOT NULL, PRIMARY KEY (user_role_id), UNIQUE KEY uni_username_role (role,username),KEY fk_username_idx (username), CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username)) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8");
        
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS location3 (locationno int(11) NOT NULL AUTO_INCREMENT,locationid varchar(255) NOT NULL,parentname varchar(255) DEFAULT NULL,description varchar(255) DEFAULT NULL,longdescription varchar(255) DEFAULT NULL,parentcraft varchar(255) DEFAULT NULL,craft varchar(255) DEFAULT NULL,equipmenttype varchar(255) DEFAULT NULL,failurecode varchar(255) DEFAULT NULL,systemstatus varchar(255) DEFAULT NULL,userstatus varchar(255) DEFAULT NULL,criticality varchar(255) DEFAULT NULL,co date DEFAULT NULL,sud date DEFAULT NULL,planningplant varchar(255) DEFAULT NULL,maintenanceplant varchar(255) DEFAULT NULL,physicallocation varchar(255) DEFAULT NULL,manufacturer varchar(255) DEFAULT NULL,partnum varchar(255) DEFAULT NULL,modelnum varchar(255) DEFAULT NULL,serialnum varchar(255) DEFAULT NULL,customfield varchar(255) DEFAULT NULL,PRIMARY KEY (locationid),UNIQUE KEY locationno_UNIQUE (locationno)) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8");
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS asset3 (assetno int(11) NOT NULL AUTO_INCREMENT,assetid varchar(255) NOT NULL,locationid varchar(255) NOT NULL,description varchar(255) DEFAULT NULL,longdescription varchar(255) DEFAULT NULL,  equipmenttype varchar(255) DEFAULT NULL,failurecode varchar(255) DEFAULT NULL,manufacturer varchar(255) DEFAULT NULL,partnum varchar(255) DEFAULT NULL,modelnum varchar(255) DEFAULT NULL,serialnum varchar(255) DEFAULT NULL,physicallocation varchar(255) DEFAULT NULL,customfield varchar(255) DEFAULT NULL,PRIMARY KEY (assetid),UNIQUE KEY asset_UNIQUE (assetno),KEY FK_locationid_idx (locationid),CONSTRAINT FK_locationid1 FOREIGN KEY (locationid) REFERENCES location3 (locationid) ON DELETE NO ACTION ON UPDATE NO ACTION) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8");



        
        //stmt.executeUpdate("INSERT INTO users (username, password, firstname, lastname, email, address, phone, enabled) VALUES ('mkyong3', '123456', 'mkyong', 'stormzy', 'stormzy@yaddenergy.no', 'aberdeen', 1234, 1)");        
        //stmt.executeUpdate("INSERT INTO user_roles (user_role_id, username, role) VALUES (2, 'mkyong', 'ROLE_ADMIN')");
        stmt.executeUpdate("INSERT INTO location3 (locationno, locationid, parentname, description, longdescription, parentcraft, craft, equipmenttype, failurecode, systemstatus, userstatus, criticality, co, sud, planningplant, maintenanceplant, physicallocation, manufacturer, partnum, modelnum, serialnum, customfield) VALUES (108, 'ID80-006-BP-OLS-PEI-XII-10', 'ID80-ID80-TLSE', 'PPNG, HZDOUS SOLID WASTE TREATMENT', 'PPNG, HZDOUS SOLID WASTE TREATMENT', 'IMP', 'INSP', 'type1', 'FA_PEG', 'CRTE', '00', 'C', '2016-05-01', '2016-05-01', '00000', 'ID80', 'Aberdeen', 'FACEBOOK', '00000', 'eLLK92036-36', '00000', '00000')");
        stmt.executeUpdate("INSERT INTO asset3 (assetno, assetid, locationid, description, longdescription, equipmenttype, failurecode, manufacturer, partnum, modelnum, serialnum, physicallocation, customfield) VALUES (40, '100000000020', 'ID80-006-BP-OLS-PEI-XII-10', 'PPNG, HZDOUS SOLID WASTE TREATMENT', 'PPNG, HZDOUS SOLID WASTE TREATMENT', 'type1', 'FA_PEG', 'PHILIPSON', '00000', 'eLLK92036-36', '00000', 'London', '00000')");

        
        
        //ResultSet rs = stmt.executeQuery("SELECT * FROM users");
        ResultSet rs = stmt.executeQuery("SELECT * FROM location3");

        
        while (rs.next()) {
         
           System.out.println("Location: " + rs.getString("locationid"));
                   }

        
      } catch (Exception e) {
        
             e.printStackTrace();
        
      }
     

  
%>
Name:<%out.print("Laud is OK");%>
