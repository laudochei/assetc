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

public class EmployeeRepository1 {
	
	private Connection _conn = null;
	//java.sql.Statement com = null;
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/contactdb";
	static final String USER = "root";
	static final String PASS = "welcome123";
	
	
	public EmployeeRepository1() { }
	
	public EmployeeRepository1(String path) {

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
	
	public List<Employee> listEmployees (int managerId){
		// create a new empty list of employees to return as a result
		List<Employee> employees = new ArrayList<Employee>();
		
		try {
			// use prepared statements to prevent sql injection attacks
			PreparedStatement stmt = null;
			
                        // the query to send to the database
                        String query = "SELECT e.EmployeeID, e.FirstName, e.LastName, e.ManagerID As ManagerID, (SELECT COUNT(*) FROM employees WHERE ManagerID = e.EmployeeID) AS DirectReports FROM employees e "; 
                        
                        if (managerId == 0) {
                            // select where employees reportsto is null
                            query += "WHERE e.ManagerID = 0";
                            stmt = _conn.prepareStatement(query);
                        }else{
                            // select where the reportsto is equal to the employeeId parameter
                            query += "WHERE e.ManagerID = ?" ;
                            stmt = _conn.prepareStatement(query);
                            stmt.setInt(1, managerId);
                        }
			// execute the query into a result set
			ResultSet rs = stmt.executeQuery();
                        
			// iterate through the result set
			while(rs.next()) {	
				// create a new employee model object
				Employee employee = new Employee();
				
				// select fields out of the database and set them on the class
				//employee.setEmployeeID(rs.getString("EmployeeID"));
				//employee.setFirstName(rs.getString("FirstName"));
				//employee.setLastName(rs.getString("LastName"));
				//employee.setManagerID(rs.getString("ManagerID"));
                                employee.setHasChildren(rs.getInt("DirectReports") > 0); 
				//employee.setFullName();
				
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
        
              public int updatetreeview (int managerId){
		// create a new empty list of employees to return as a result
		//List<Employee> employees = new ArrayList<Employee>();
		int updatestatus = 0;
                
		try {
			// use prepared statements to prevent sql injection attacks
			PreparedStatement stmt = null;
			
                        // the query to send to the database
                        String query = "UPDATE employees SET ManagerID = 0 " + "WHERE EmployeeISD = " + managerId + ""; 
                        
			// execute the query into a result set
			//ResultSet rs = stmt.executeQuery();
                        stmt = _conn.prepareStatement(query);
                        stmt.executeUpdate();
                        
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		// return the result list
		return updatestatus;
	}  
        
	
}
