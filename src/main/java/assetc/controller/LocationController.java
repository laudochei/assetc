package assetc.controller;


import assetc.model.Location;
import assetc.service.AssetService;
import assetc.service.EmployeeService;
import assetc.service.LocationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;


@RestController
@RequestMapping(value = "/locationapi")
public class LocationController {
            
        private LocationService locationService;
        
       

	@Autowired
	public void setLocationService(LocationService locationService) {
		this.locationService = locationService;
	}

	
        // list page
        @RequestMapping(value = "/locationlist", method=GET)
        public  List<Location> showRestApi3(Model model) {
           return locationService.findAllLocation();
        }
        
        
        
        //display a single record
        @RequestMapping(value = "/{locationno}", method = RequestMethod.GET)
	public ResponseEntity<String> getLocation(@PathVariable("locationno") Integer locationno) {
		Location location = locationService.findByLocationno(locationno);
		if (location == null) {
			return new ResponseEntity("No location found for ID " + locationno, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(location, HttpStatus.OK);
        }
        
        //display a single record
        @RequestMapping(value = "/children/{locationid}", method = RequestMethod.GET)
	public List<Location> getAllLocationChild(@PathVariable("locationid") String locationid,Model model) {
	
                return locationService.findAllChild(locationid);
        }
        
        
        @RequestMapping(value = "/update/{locationno}", method = RequestMethod.PUT, headers = "Accept=application/json")
        public ResponseEntity<Void> updateLocation(@PathVariable("locationno") Integer locationno, @RequestBody Location location) {
         location.setLocationno(locationno);
         locationService.updateLocation(location);
         HttpHeaders headers = new HttpHeaders();
         return new ResponseEntity<Void>(headers, HttpStatus.OK);
        }
        
        
        @RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
        public ResponseEntity<Void> addLocation(@RequestBody Location location) {         
         locationService.saveLocation(location);
         HttpHeaders headers = new HttpHeaders();
         return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
        }
        
        
        
        @RequestMapping(value = "/delete/{locationno}", method = RequestMethod.GET)
        public ResponseEntity<Location>  deleteLocation(@PathVariable("locationno") Integer locationno) {
            System.out.println("Fetching & Deleting Location with no " + locationno);
            
            Location location = locationService.findByLocationno(locationno);
            if (location == null) {
		return new ResponseEntity("No location found for ID " + locationno, HttpStatus.NOT_FOUND);
            }    
            locationService.deleteLocation(locationno);
            return new ResponseEntity<Location>(HttpStatus.NO_CONTENT);
                
        }   
        
 
}
