package assetc.api;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import assetc.model.Location;
import assetc.repository.LocationRepository;

/**
 * Servlet implementation class Employees
 */
//@WebServlet(description = "A servlet to return data about employees from the database", urlPatterns = { "/builds/api/employees" })

@WebServlet(description = "A servlet to return data about employees from the database", urlPatterns = { "/builds/asset/api/locations" })
public class Locations extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// employee repository class
	private LocationRepository _repository = null;
	private Gson _gson = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Locations() {
        super();
        
        // initialize the Gson library
        _gson = new Gson();
    }
    
    public void init() throws ServletException {
    	super.init();
    
    	// create a new instance of the repository class. pass in the path to the data/sample.db
    	// file which we can get by getting the servlet context, then calling 'getRealPath'
    	_repository = new LocationRepository(this.getServletContext().getRealPath("data/sample.db"));
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			System.out.println(request.getParameter(""));
			
			// get the managerId off of the request
			//String managerId = request.getParameter("EmployeeID") == null ? 0 : request.getParameter("EmployeeID");
                        String managerId = request.getParameter("locationid") == null ? "0" : request.getParameter("locationid");
                        
                        
//                        String managerId = "0";
//                        if (request.getParameter("EmployeeID")== null || request.getParameter("EmployeeID").trim().equals("")) {
//                            managerId = request.getParameter("EmployeeID");
//                        }
                       
			
                        //int managerId = request.getParameter("EmployeeID") == null ? 0 : Integer.parseInt(request.getParameter("EmployeeID"));
			
                        
			System.out.println(managerId);
			
			// get the employees from the database
			//List<assetc.model.Employee> employees = _repository.listEmployees(managerId);
                        
                        List<assetc.model.Employee> employees = _repository.listEmployees(managerId);
			
			// set the content type we are sending back as JSON
			response.setContentType("application/json"); 
			
			// convert the list to json and write it to the response
			response.getWriter().print(_gson.toJson(employees));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
