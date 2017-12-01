package assetc.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {
        
    private Connection _conn = null;
	//java.sql.Statement com = null;
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/contactdb";
	static final String USER = "root";
	static final String PASS = "welcome123";
	
	
	public CategoryRepository() { }
	
	public CategoryRepository(String path) {

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
	
    
    	
	public List<assetc.model.Category> listCategories() throws SQLException {
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<assetc.model.Category> categories = new ArrayList<assetc.model.Category>();
		
		try {
			
			String sql = "SELECT CategoryID, CategoryName FROM Categories";
			
			stmt = _conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				assetc.model.Category category = new assetc.model.Category();
				
				category.setCategoryID(rs.getInt("CategoryID"));
				category.setCategoryName(rs.getString("CategoryName"));
				
				categories.add(category);
			}
		}
		finally {
			stmt.close();
			rs.close();
		}
		
		return categories;
		
	}
}
