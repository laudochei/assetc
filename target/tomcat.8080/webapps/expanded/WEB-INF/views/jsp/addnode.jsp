<%@ page import="java.sql.*" %>
<%@ page import="assetc.model.Employee"%>

<%
        int a = Integer.parseInt(request.getParameter("p")); //11;
        int b = Integer.parseInt(request.getParameter("q")); // 14;
        String r = request.getParameter("r"); // 14;
        String s = request.getParameter("s"); // 14;
        
        int EmployeeID = 0;
        int ManagerID = 0;
        String Firstname = r;
        String Lastname = s;
        String Fullname = s + " " + r;
        int Haschildren = 0;
        
        //create prepared statement and result set objects
        PreparedStatement stmt = null;
        

   try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/contactdb", "root", "welcome123");
        // build the sql string
        String sql = "INSERT INTO employees (ManagerID, LastName," +
               "FirstName, FullName, HasChildren) " +
               "VALUES (?, ?, ?, ?, ?)";
        
        // prepare the statement for safe execution, specifying that we
        // want the auto-generated id from the database returned.
        stmt = con.prepareStatement(sql);

        stmt.setInt(1, b);
        stmt.setString(2, Lastname);
        stmt.setString(3, Firstname);
        stmt.setString(4, Fullname);
        stmt.setInt(5,Haschildren);
       
        // execute the create statement
        int rows = stmt.executeUpdate();
        // if no rows were returned, it failed
        if (rows == 0) {
          throw new SQLException("Unable to create node");
        }


      }
      finally {
        // close all connection related instances
        stmt.close();
        //rs.close();
      }

  
%>
Name:<%out.print(Fullname);%>




