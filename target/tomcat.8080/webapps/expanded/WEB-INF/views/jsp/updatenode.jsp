<%@ page import="java.sql.*" %>
<%@ page import="assetc.model.Employee"%>

<%
        int p = Integer.parseInt(request.getParameter("p")); //11;
        String q = request.getParameter("q"); // 14;
        String t = request.getParameter("r"); // 14;
        
        
        
        int EmployeeID = 0;
        int ManagerID = 0;
        String Firstname = "";
        String Lastname = "";
        String Fullname = "";
        int Haschildren = 0;
        
        
        PreparedStatement stmt = null;
   
        try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/contactdb", "root", "welcome123");
        // create a prepared statement object
                
        //String sql1 = "select * from employees where EmployeeID='" + q + "'" ;
        String sql1 = "select * from employees where EmployeeID=" + p + "";
        stmt = con.prepareStatement(sql1);
        ResultSet r = stmt.executeQuery();
        while (r.next()) {
            EmployeeID = r.getInt("EmployeeID");
            ManagerID = r.getInt("ManagerID");
            Firstname = r.getString("Firstname");
            Lastname = r.getString("Lastname");
            Fullname = r.getString("Fullname");
            Haschildren = r.getInt("Haschildren");; 
        }
             
             
             
        //UPDATE THE TABLE
        // build the sql needed for the update
        String sql2 = "UPDATE employees SET ManagerID = ?, LastName = ?, " +
                 "FirstName = ?, FullName = ?, HasChildren = ? " +
                 "WHERE EmployeeID = ?";
        
        // prepare the statement for safe execution
        stmt = con.prepareStatement(sql2);
        //models.Employee product;
               
        // map the parameters into the query
        //stmt.setInt(1, ManagerID);
        stmt.setInt(1, ManagerID);
        stmt.setString(2, t);
        stmt.setString(3, q);
        stmt.setString(4, Fullname);
        stmt.setInt(5,Haschildren);
        stmt.setInt(6, EmployeeID);
        
        // execute the update
        stmt.executeUpdate(); 
        
         //con.close    
        } catch (Exception e) {
             e.printStackTrace();
        }
        
%>
Name:<%out.print(q + t);%>




