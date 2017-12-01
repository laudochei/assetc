package assetc.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductsRepository {
    
        private Connection _conn = null;
	//java.sql.Statement com = null;
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/contactdb";
	static final String USER = "root";
	static final String PASS = "welcome123";

	public ProductsRepository () { }
        
        
        
        public ProductsRepository(String path) {

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
	

            
	
	public int getProductCount() throws SQLException {
		
		int total = 0;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			String sql = "SELECT COUNT(*) AS Total FROM products";
			
			stmt = _conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				total = rs.getInt("Total");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			stmt.close();
			rs.close();
		}
		
		return total;
		
	}
	
        
        //public List<models.Product> listProducts() throws SQLException {

	public List<assetc.model.Product> listProducts(int take, int skip) throws SQLException {
		
		List<assetc.model.Product> products = new ArrayList<assetc.model.Product>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			String sql = "SELECT p.ProductID, p.ProductName, p.SupplierID, s.SupplierName, " +
						 "p.CategoryID, c.CategoryName, p.UnitPrice, p.UnitsInStock, p.Discontinued " +
						 "FROM products p " +
						 "JOIN suppliers s ON p.SupplierID = s.SupplierID " +
						 "JOIN categories c ON p.CategoryID = c.CategoryID " + 
						 "LIMIT ?,?";
			
			stmt = _conn.prepareStatement(sql);
		
			stmt.setInt(1, skip);
			stmt.setInt(2, take);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				assetc.model.Product product = new assetc.model.Product();
				
				product.setProductID(rs.getInt("ProductID"));
				product.setProductName(rs.getString("ProductName"));
				product.setSupplier(new assetc.model.Supplier(rs.getInt("SupplierID"), rs.getString("SupplierName")));
				product.setCategory(new assetc.model.Category(rs.getInt("CategoryID"),rs.getString("CategoryName")));
				product.setUnitPrice(rs.getFloat("UnitPrice"));
				product.setUnitsInStock(rs.getInt("UnitsInStock"));
				product.setDiscontinued(rs.getBoolean("Discontinued"));
				
				products.add(product);				
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			rs.close();
			stmt.close();
		}
		
		return products;
	}

	// update products method
	public assetc.model.Product doUpdateProduct(assetc.model.Product product) throws Exception {

		// create a prepared statement object
		PreparedStatement stmt = null;

		try {

			// build the sql needed for the update
			String sql = "UPDATE Products SET SupplierID = ?, CategoryID = ?, ProductName = ?, " +
						 "UnitPrice = ?, UnitsInStock = ?, Discontinued = ? " +
						 "WHERE ProductID = ?";

			// prepare the statement for safe execution
			stmt = _conn.prepareStatement(sql);

			// map the parameters into the query
			stmt.setInt(1, product.getSupplier().getSupplierID());
			stmt.setInt(2, product.getCategory().getCategoryID());
			stmt.setString(3, product.getProductName());
			stmt.setFloat(4, product.getUnitPrice());
			stmt.setInt(5, product.getUnitsInStock());
			stmt.setBoolean(6, product.getDiscontinued());
			stmt.setInt(7, product.getProductID());
			
			// execute the update
			stmt.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		finally {
			// close all necessary connection related instances
			stmt.close();
		}
		
		return product;
	}
	
	// product create method
	public int doCreateProduct(assetc.model.Product product) throws Exception {
		
		// set a default id value
		int id = 0;
		
		//create prepared statement and result set objects
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			// build the sql string
			String sql = "INSERT INTO Products (SupplierID, CategoryID, " +
						 "ProductName, UnitPrice, UnitsInStock, Discontinued ) " +
						 "VALUES (?, ?, ?, ?, ?, ?)";

			// prepare the statement for safe execution, specifying that we
			// want the auto-generated id from the database returned.
			stmt = _conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			// map the product object to the sql
			stmt.setInt(1, product.getSupplier().getSupplierID());
			stmt.setInt(2, product.getCategory().getCategoryID());
			stmt.setString(3, product.getProductName());
			stmt.setFloat(4, product.getUnitPrice());
			stmt.setInt(5, product.getUnitsInStock());
			stmt.setBoolean(6, product.getDiscontinued());

			// execute the create statement
			int rows = stmt.executeUpdate();
			// if no rows were returned, it failed
			if (rows == 0) {
				throw new SQLException("Unable to create product");
			}

			// get the generated key for this item
			rs = stmt.getGeneratedKeys();
			
			// pull the key off the result set
			if (rs.next()) {
				id = rs.getInt(1);
			}
			else {
				throw new SQLException("Unable to create product. No auto-genereated key obtained");
			}
		}
		finally {
			// close all connection related instances
			stmt.close();
			rs.close();
		}
		
		// return the id that was or wasn't created
		return id;
	}
	
	// the delete method
	public void doDeleteProduct(int productId) throws SQLException {
		
		// create a prepared statement object
		PreparedStatement stmt = null;
		
		try {
			
			// build the simple sql statement
			String sql = "DELETE FROM Products WHERE ProductID = ?";
			
			// prepare the statement for safe execution
			stmt = _conn.prepareStatement(sql);
			
			// set the id of the product to delete
			stmt.setInt(1, productId);
			
			// execute the delete
			stmt.executeUpdate();
		}
		finally {
			// close all connection related instances
			stmt.close();
		}
	}
	
}
