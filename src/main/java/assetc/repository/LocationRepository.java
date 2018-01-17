package assetc.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import assetc.model.Employee;

public class LocationRepository {
	
	private Connection _conn = null;
	//java.sql.Statement com = null;
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://us-cdbr-iron-east-05.cleardb.net:3306/heroku_7ac45a05bab0085";
	static final String USER = "b4778f9d520c68";
	static final String PASS = "132d6c92";
	
	
	public LocationRepository() { }
	
	public LocationRepository(String path) {

		// initialize the database driver
        try {
        	//Class.forName("org.sqlite.JDBC");
        	try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
			// set the connection instance
			//_conn = DriverManager.getConnection("jdbc:sqlite:" + path);
        	_conn = DriverManager.getConnection(DB_URL,USER,PASS);
        	
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public List<Employee> listEmployees (String managerId){
		// create a new empty list of employees to return as a result
		List<Employee> employees = new ArrayList<Employee>();
		
		try {
			// use prepared statements to prevent sql injection attacks
			PreparedStatement stmt = null;
			
                        // the query to send to the database
                        //String query = "SELECT e.locationid, e.description, e.longdescription, e.parentname As parentname, (SELECT COUNT(*) FROM employees WHERE parentname = e.locationid) AS DirectReports FROM employees e "; 
                        String query = "SELECT e.locationid, e.description, e.longdescription, e.parentname As parentname, (SELECT COUNT(*) FROM location WHERE parentname = e.locationid) AS DirectReports FROM location e "; 
                        
                        
                        
                        if (managerId=="0") {
                            // select where employees reportsto is null
                            query += "WHERE e.parentname = '0'";
                            stmt = _conn.prepareStatement(query);
                        }else{
                            // select where the reportsto is equal to the employeeId parameter
                            query += "WHERE e.parentname = ?" ;
                            stmt = _conn.prepareStatement(query);
                            stmt.setString(1, managerId);
                        }
			// execute the query into a result set
			ResultSet rs = stmt.executeQuery();
                        
			// iterate through the result set
			while(rs.next()) {	
				// create a new employee model object
				Employee employee = new Employee();
				
				// select fields out of the database and set them on the class
				employee.setLocationid(rs.getString("locationid"));
				employee.setDescription(rs.getString("description"));
				employee.setLongdescription(rs.getString("longdescription"));
				employee.setParentname(rs.getString("parentname"));
                                employee.setHasChildren(rs.getInt("DirectReports") > 0); 
				employee.setFullDescription();
				
				// add the class to the list
				employees.add(employee);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		// return the result list
		return employees;
		
	}
        
             
        
	
}
