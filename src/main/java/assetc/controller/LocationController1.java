package assetc.controller;


import assetc.model.Location;
import assetc.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import assetc.service.LocationService;
import assetc.validator.LocationFormValidator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/locations")
public class LocationController1 {

	private final Logger logger = LoggerFactory.getLogger(LocationController1.class);

	@Autowired
	LocationFormValidator locationFormValidator;
	
        //Set a form validator
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(locationFormValidator);
	}

	private LocationService locationService;
        private int operationtype;
       

	@Autowired
	public void setLocationService(LocationService locationService) {
		this.locationService = locationService;
	}

	
     // list page
    @RequestMapping(value = "/locationlist", method=GET)
    public String showAllLocation(Model model) {
        model.addAttribute("locations", locationService.findAllLocation());
        return "locationlist";
    }
  
    
     // show location
	@RequestMapping(value = "/{locationno}", method = RequestMethod.GET)
	public String showLocation(@PathVariable("locationno") Integer locationno, Model model) {

		logger.debug("showLocation() locationno: {}", locationno);

		Location location = locationService.findByLocationno(locationno);
		if (location == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "Location not found");
		}
		model.addAttribute("location", location);

		return "locationprofile";
	}
        
        
        
        // show add location form
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String showAddLocationForm(Model model) {
                Task task = new Task(); 
                int actionNo = 1; // set add action to 1
                task.setCreate(actionNo);
            
		logger.debug("showAddLocationForm()");
		Location location = new Location();
                                
                String date ="2016-05-01";
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date parsed = null;
                try {
                    parsed = format.parse(date);
                } catch (ParseException ex) {
                    java.util.logging.Logger.getLogger(LocationController1.class.getName()).log(Level.SEVERE, null, ex);
                }
                java.sql.Date sql = new java.sql.Date(parsed.getTime());


		// set default value
                  location.setLocationid("ID80-003-BP-OLS-PEI-X-00");
                  location.setParentname("ID80-ID80-TLSE");
                  location.setDescription("PPNG, HZDOUS SOLID WASTE TREATMENT");
                  location.setLongdescription("PPNG, HZDOUS SOLID WASTE TREATMENT");
                  location.setParentcraft("IMP");
                  location.setCraft("INSP");
                  location.setEquipmenttype("type1");
                  location.setFailurecode("FA_PEG");
                  location.setSystemstatus("CRTE");
                  location.setUserstatus("00");
                  location.setCriticality("C");
                  location.setCo(sql);
                  location.setSud(sql);
                  location.setPlanningplant("00000");
                  location.setMaintenanceplant("ID80");
                  location.setPhysicallocation("Aberdeen");
                  location.setManufacturer("PHILIPS");
                  location.setPartnum("00000");
                  location.setModelnum("eLLK92036-36");
                  location.setSerialnum("00000");
                  location.setCustomfield("00000");
                  
		model.addAttribute("locationForm", location);	    
                return "locationForm"; 
	}
     
        
        
        
    // add location
        @RequestMapping(value="/add", method = RequestMethod.POST)
        public String addLocation(@ModelAttribute("locationForm") @Validated Location location, BindingResult result, Model model,final RedirectAttributes redirectAttributes) {
            
                Task task = new Task(); 
                int actionNo = 1; // set add action to 1
                task.setCreate(actionNo);
                locationService.checkAction(actionNo);
                
            logger.debug("addLocation() : {}", location);
            //check for errors in the form
              if (result.hasErrors()) 
              {
                return "locationForm";
              } else { // save new location
                  redirectAttributes.addFlashAttribute("css", "success");
                  redirectAttributes.addFlashAttribute("msg", "Location added successfully!");
                  locationService.saveLocation(location);
                  return "redirect:/locations/" + location.getLocationno();
                
              }
        }
    
        
        // show update form
	@RequestMapping(value = "/{locationno}/update", method = RequestMethod.GET)
	public String showUpdateLocationForm(@PathVariable("locationno") int locationno, Model model) {
		logger.debug("showUpdateLocationForm() : {}", locationno);
                Location location = locationService.findByLocationno(locationno);
		model.addAttribute("locationForm", location);	    
                return "locationForm";
	}
        
        
        
        
    // update location
    @RequestMapping(value = "/{locationno}/update", method = RequestMethod.POST)
    public String updateLocation(@ModelAttribute("locationForm") @Validated Location location, BindingResult result, Model model,final RedirectAttributes redirectAttributes) {
	operationtype = 1;
        
         Task task = new Task(); 
         int actionNo = 3; // set update action to 3
         task.setUpdate(actionNo);
         locationService.checkAction(actionNo);
         
        
        logger.debug("updateLocation() : {}", location);
        if (result.hasErrors()) {
            return "locationForm";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
            redirectAttributes.addFlashAttribute("msg", "Location updated successfully!");
            locationService.updateLocation(location);
            return "redirect:/locations/" + location.getLocationno();
        }  
    }
    
    
    
    
    // delete location
    @RequestMapping(value = "/{locationno}/deletelocation", method = RequestMethod.GET)
    public String deleteLocation(@PathVariable("locationno") Integer locationno, final RedirectAttributes redirectAttributes) {
	logger.debug("deleteLocation() : {}", locationno);
	locationService.deleteLocation(locationno);
	redirectAttributes.addFlashAttribute("css", "success");
	redirectAttributes.addFlashAttribute("msg", "Location is deleted!");
        return "redirect:/locations/locationlist";
    }   
   

}