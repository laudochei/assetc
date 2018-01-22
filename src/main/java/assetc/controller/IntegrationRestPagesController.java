/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.controller;




import assetc.service.AssetService;
import assetc.service.EmployeeService;
import assetc.service.LocationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author laud.ochei
 */

@Controller
@RequestMapping ("/integrate")
public class IntegrationRestPagesController {
    
        private AssetService assetService;
        private EmployeeService employeeService;
        private LocationService locationService;
        
       

	@Autowired
	public void setLocationService(LocationService locationService) {
		this.locationService = locationService;
	}

	@Autowired
	public void setAssetService(AssetService assetService) {
		this.assetService = assetService;
	}
	 
        // list page
        @Autowired
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
              
	
        // list page
        @RequestMapping(value = "/display/restapi", method=GET)
        public  String DisplayApi(Model model) {
           return "restapi/displayapi";
        }
        
        
        @RequestMapping(value = "/getrequest", method=GET)
        public String getRestApi(Model model) {
            // model.addAttribute("assets", buildService.findAllAsset());
                return "restapi/getrequest";
        }
        
        /*
        @RequestMapping(value = "/postrequest", method=POST)
        public String postRestApi(Model model) {
            // model.addAttribute("assets", buildService.findAllAsset());
                return "restapi/postrequest";
        }
        */
        
        @RequestMapping(value = "/postrequest", method=GET)
        public String postRestApi(@RequestParam("locationid") String name,@RequestParam("parentname") String location) {
           
            return "checkvalues";
        }
       
        /*
        @RequestMapping(value = "/dragdrop", method=POST)
        //@RequestMapping(value = "/restapi/dragdrop1", method=GET)
        //public String dragdropRestApi(Model model, @RequestParam String locationid, @RequestParam String parentname) {
        public String dragdrop(Model model) {
            //Location location = locationService.findByLocationid(locationid);
            //locationService.dragdrop(location, locationid, parentname);
            //return "redirect:/builds/asset/treeview_live";
            
            
            //return "redirect:restapi/dragdrop";
            return "restapi/dragdrop";
        }
        */
        
        
//         @RequestMapping(value = "/dragdrop", method=GET)
//        public ModelAndView dragdrop(@RequestParam("p") String locationid) {
//            
//            ModelAndView mav = new ModelAndView();
//            mav.addObject("p", locationid);
//            System.out.println(locationid);
//            mav.setViewName("restapi/dragdrop");
//            return mav;
//            
//        }
        
        @RequestMapping(value = "/addnode", method=POST)
        public String showAddNode(Model model) {
           // model.addAttribute("assets", buildService.findAllAsset());
           
            return "restapi/addtreenode";
        }
        
        @RequestMapping(value = "/dragdrop", method=GET)
        public String showDragDrop(Model model) {
           
            return "restapi/dragdroptree";
        }
        
        
        /*
        @RequestMapping(value = "/treeview", method = RequestMethod.GET)
        public ModelAndView treeView() throws IOException {
              String test = "http://localhost:25175/AssetC/userapi/userlist";
              ObjectMapper mapper = new ObjectMapper();
              Object json = mapper.readValue(test, Object.class);
              ModelAndView modelandView = new ModelAndView("viewname");
              modelandView.addObject("restapi/jsontree", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json));
              return modelandView;
        }
        */
        
        
        @RequestMapping(value = "/treeview", method = RequestMethod.GET)
        public ModelAndView treeView() throws IOException {
              String test = "http://localhost:25175/AssetC/userapi/userlist";
              ObjectMapper mapper = new ObjectMapper();
              Object json = mapper.readValue(test, Object.class);
              ModelAndView modelandView = new ModelAndView();
              modelandView.addObject("output", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json));
              modelandView.setViewName("restapi/displayapi");
              return modelandView;
              
              
//            ModelAndView model = new ModelAndView();
//            model.addObject("title", "Spring Security Login Form - Database Authentication");
//            model.addObject("message", "This is default page!");
//            model.setViewName("hello");
//            return model;
            
        }
        
        @RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
            
                //final String uri = "http://localhost:25175/AssetC/userapi/userlist";
                //final String uri = "http://localhost:25175/AssetC/integration/restapi3";
                //final String uri = "http://localhost:25175/AssetC/builds/asset/api/locations";
                //final String uri = "http://localhost:25175/AssetC/integration/restapi/children/ID80";
                final String uri = "http://localhost:25175/AssetC/integration/restapi/children/TANGGUH";
                RestTemplate restTemplate = new RestTemplate();
                String result = restTemplate.getForObject(uri, String.class);
                
                ObjectMapper mapper = new ObjectMapper();
                Object json = "";
                Object node = "";
            try {
                //JsonNode node = mapper.readTree(genreJson);
                node = mapper.readTree(result);
                //json = mapper.readValue(result, Object.class);
            } catch (IOException ex) {
                Logger.getLogger(IntegrationRestPagesController.class.getName()).log(Level.SEVERE, null, ex);
            }
		ModelAndView model = new ModelAndView();
            try {
                model.addObject("output", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node));
            } catch (JsonProcessingException ex) {
                Logger.getLogger(IntegrationRestPagesController.class.getName()).log(Level.SEVERE, null, ex);
            }
		
		model.setViewName("restapi/jsontree");
                getEmployees();
		return model;

	}
        
        private static void getEmployees()
        {
            try {
                final String uri = "http://localhost:25175/AssetC/userapi/userlist";
                RestTemplate restTemplate = new RestTemplate();
                String result = restTemplate.getForObject(uri, String.class);
                
                // create an ObjectMapper instance.
                ObjectMapper mapper = new ObjectMapper();
                // use the ObjectMapper to read the json string and create a tree
                JsonNode node = mapper.readTree(result);
                
                
                String resultOriginal = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
                System.out.println("JSON Tree " + resultOriginal);
                //System.out.println(result);
            } catch (IOException ex) {
                Logger.getLogger(IntegrationRestPagesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
        }
    
}
