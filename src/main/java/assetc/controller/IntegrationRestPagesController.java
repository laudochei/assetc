/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.controller;




import assetc.service.AssetService;
import assetc.service.EmployeeService;
import assetc.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RequestParam;
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
        
        
        
    
}
