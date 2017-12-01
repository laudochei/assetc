package assetc.api;

import assetc.repository.SuppliersRepository;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


/**
 * Servlet implementation class Suppliers
 */
@WebServlet("/builds/asset/api/suppliers")
public class Suppliers extends HttpServlet {
    private static final long serialVersionUID = 1L;
    //private SuppliersRepository _repository;   
    private SuppliersRepository _repository = null;  
    private Gson _gson = new Gson();
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Suppliers() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init() throws ServletException {
    	super.init();
    	_repository = new SuppliersRepository(this.getServletContext().getRealPath("data/sample.db"));
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<assetc.model.Supplier> suppliers = new ArrayList<assetc.model.Supplier>();
		
		try {
			
			suppliers = _repository.listSuppliers();
			
			// set the content type we are sending back as JSON
			response.setContentType("application/json"); 
						
			// print the content to the response
			response.getWriter().print(_gson.toJson(suppliers));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendError(500);
		}
		
	}

}
