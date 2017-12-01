package assetc.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Repository {

        public Connection _conn = null;
	//java.sql.Statement com = null;
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/contactdb";
	static final String USER = "root";
	static final String PASS = "welcome123";
	
	public Repository(String path) {
		
	
        
        try {
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
        	_conn = DriverManager.getConnection(DB_URL,USER,PASS);
        	
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
        }
        
	
}
