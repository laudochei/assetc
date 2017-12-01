package assetc.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SuppliersRepository {
 
	private Connection _conn = null;
	//java.sql.Statement com = null;
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/contactdb";
	static final String USER = "root";
	static final String PASS = "welcome123";
	
	
	public SuppliersRepository() { }
	
	public SuppliersRepository(String path) {

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
	
    
	public List<assetc.model.Supplier> listSuppliers() throws SQLException {
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<assetc.model.Supplier> suppliers = new ArrayList<assetc.model.Supplier>();
		
		try {
			
			String sql = "SELECT SupplierID, SupplierName FROM Suppliers";
			
			stmt = _conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				assetc.model.Supplier supplier = new assetc.model.Supplier();
				
				supplier.setSupplierID(rs.getInt("SupplierID"));
				supplier.setSupplierName(rs.getString("SupplierName"));
				
				suppliers.add(supplier);
			}
	 	}
		finally {
			stmt.close();
			rs.close();
		}
		
		return suppliers;
		
	}
}
