<%@page import="org.springframework.dao.DataAccessException"%>
<%@ page import="java.sql.*" %>
<%@ page import="assetc.model.Employee"%>
<%@ page import="assetc.config.DataConfig"%>
<%@ page import="org.springframework.jdbc.core.namedparam.MapSqlParameterSource"%>
<%@ page import="org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate"%>
<%@ page import="org.springframework.jdbc.core.namedparam.SqlParameterSource"%>
<%@ page import="org.springframework.beans.factory.annotation.Autowired"%>

<%
        
        //NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
        
        
//int p = Integer.parseInt(request.getParameter("p")); //11;
        //int q = Integer.parseInt(request.getParameter("q")); // 14;
        
        
        String p = request.getParameter("p");
        String q = request.getParameter("q");
        
        
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
        stmt.setString(1, "q");
        stmt.setString(2, Lastname);
        stmt.setString(3, Firstname);
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
Name:<%out.print(Fullname);%>




