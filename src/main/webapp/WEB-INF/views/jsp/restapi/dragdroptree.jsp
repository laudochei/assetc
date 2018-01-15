<%@ page import="java.sql.*" %>
<%@ page import="assetc.model.Employee"%>

<%
        //int p = Integer.parseInt(request.getParameter("p")); //11;
        //int q = Integer.parseInt(request.getParameter("q")); // 14;
        
        
        String p = request.getParameter("p");
        String q = request.getParameter("q");
        
        
        //String p = "ID80"; //request.getParameter("p");
        //String q = "0"; //request.getParameter("q");
        
        String locationid = "";
        String parentname = "";
        
        PreparedStatement stmt = null;
        try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assetc", "root", "welcome123");
        
        String sql1 = "select * from location where locationid='" + p + "'";
        stmt = con.prepareStatement(sql1);
        ResultSet r = stmt.executeQuery();
        while (r.next()) {
            locationid = r.getString("locationid");
            parentname = r.getString("parentname");
            System.out.println("locationid: " + locationid);
            System.out.println("parentname: " + parentname);
        }
       
        // build the sql needed for the update
        String sql2 = "UPDATE location SET parentname = ? WHERE locationid = ? ";
        
        // prepare the statement for safe execution
        stmt = con.prepareStatement(sql2);
        
        // map the parameters into the query
        stmt.setString(1, q);
        stmt.setString(2, locationid);
        
        // execute the update
        stmt.executeUpdate(); 
        
         //con.close    
        } catch (Exception e) {
             e.printStackTrace();
        }
        
%>
Name:<%out.print(locationid);%>




