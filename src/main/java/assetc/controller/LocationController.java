package assetc.controller;


import assetc.model.Error;
import assetc.model.Location;
import assetc.service.LocationService;
import assetc.validator.LocationException;
import assetc.validator.SQLException;
import java.net.URI;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;


@RestController
@RequestMapping(value = "/locationapi")
public class LocationController {
        private final Logger logger = LoggerFactory.getLogger(LocationController.class);
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
        
        //display the record for the parent node 
//        @RequestMapping(value = "/rootnode", method = RequestMethod.GET)
//	public ResponseEntity<String> getParentNode(@PathVariable("locationid") String locationid) {
//            System.out.println("JSON Tree ");
//                Location location = locationService.findParentNode(locationid);
//		if (location == null) {
//			return new ResponseEntity("No location found for ID " + locationid, HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity(location, HttpStatus.OK);
//        }
        
        
        @RequestMapping(value = "/rootnode", method = RequestMethod.GET)
        public ResponseEntity<String> getRootNode() {
            Location location = locationService.findParentNode();
            if (location == null) {
                return new ResponseEntity("No location found for root node", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity(location, HttpStatus.OK);
        }
        
        
        //display all children of a particular node when given a locationid
        @RequestMapping(value = "/children/{locationid}", method = RequestMethod.GET)
	public List<Location> getAllLocationChild(@PathVariable("locationid") String locationid,Model model) {
	
                return locationService.findAllChild(locationid);
        }
        
        //display a single record
        @RequestMapping(value = "/allnodes", method = RequestMethod.GET)
        public List<Location> getChildrenofNode(@RequestParam(value="locationid", defaultValue="20") String locationid) {
            return locationService.findChildrenofNode(locationid);
        }
        
                
        @RequestMapping(value = "/update/{locationno}", method = RequestMethod.PUT, headers = "Accept=application/json")
        public ResponseEntity<Void> updateLocation(@PathVariable("locationno") Integer locationno, @RequestBody Location location) {
         location.setLocationno(locationno);
         locationService.updateLocation(location);
         HttpHeaders headers = new HttpHeaders();
         return new ResponseEntity<Void>(headers, HttpStatus.OK);
        }
        
        
        
        
        
        
        @RequestMapping(value = "/delete/{locationno}", method = RequestMethod.GET)
        public ResponseEntity<Location>  deleteLocation(@PathVariable("locationno") Integer locationno) throws LocationException, SQLException {
            System.out.println("Fetching & Deleting Location with no " + locationno);
            
            Location location = locationService.findByLocationno(locationno);
            if (location == null) {
                throw new LocationException("No record found for locationno: " + locationno);
                //throw new SQLException("Record cannot be delete or update " + locationno);
		//return new ResponseEntity("No location found for ID " + locationno, HttpStatus.NOT_FOUND);  
            }    
            
            int locationidstatus = locationService.deleteException(location.getLocationid());     
            if (locationidstatus > 0) { 
                throw new LocationException("Record cannot be deleted: " + locationno);
                //return new ResponseEntity("Record: " + locationno + " cannot be deleted", HttpStatus.NOT_FOUND);
            }
            
            
            int rootnodestatus = locationService.checkrootnode(locationno);     
            if (rootnodestatus > 0) { 
                throw new LocationException("Root node cannot be delete");
            }
            
                              
            locationService.deleteLocation(locationno);
            return new ResponseEntity<Location>(HttpStatus.NO_CONTENT);
                
        } 
        
        
        /*
        @RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
        public ResponseEntity<Void> addLocation(@RequestBody Location location) {         
         locationService.saveLocation(location);
         HttpHeaders headers = new HttpHeaders();
         return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
        }
        */
        
        
        @RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
        public ResponseEntity<Void> adddynamicurl(@RequestBody Location location, UriComponentsBuilder ucb) throws LocationException {   
            //System.out.println("Adding a new location record:" +  location.getLocationid());
            logger.info("creating new location: {}", location);
            int locationidstatus = locationService.LocationExists(location.getLocationid());     
            if (locationidstatus > 0) { 
                //logger.info("location record with name " + location.getLocationid() + " already exists");
                //System.out.println(" location record " + location.getLocationid() + " already exist");
                //return new ResponseEntity<Void>(HttpStatus.CONFLICT);
                //return new ResponseEntity<Void>(HttpStatus.CONFLICT);
                //return new ResponseEntity("Record: " + location.getLocationno() + " cannot be deleted", HttpStatus.NOT_FOUND);
                throw new LocationException(" Location record " + location.getLocationid() + " already exist");
            }

            locationService.saveLocation(location);
            HttpHeaders headers = new HttpHeaders();
            URI locationUri = ucb.path("/locationapi/").path(String.valueOf(location.getLocationno())).build().toUri();
            headers.setLocation(locationUri);
            headers.add("Locationno", String.valueOf(location.getLocationno()));
            ResponseEntity<Void> responseEntity = new ResponseEntity<Void> (headers, HttpStatus.CREATED);
            
            
            
            //var response = Request.CreateResponse(HttpStatusCode.OK, createdItemId);
            //response.Headers.Location = new Uri(Url.Link("SomeRoutes", new { id = createdItem }));

//            var corsResult = new CorsResult();
//            corsResult.AllowedExposedHeaders.Add("Location");
//            response.WriteCorsHeaders(corsResult);
//            
            
            
            
            //return new ResponseEntity(location.getLocationno(), HttpStatus.CREATED);
            return responseEntity;
        }
        
        
        
        @RequestMapping(value = "/addlocation}", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<String> addlocation(@RequestBody Location location, UriComponentsBuilder ucb) {
		Location locationstatus = locationService.findByLocationid(location.getLocationid());
		if (locationstatus != null) {
			return new ResponseEntity<String>("Duplicate locationid: " + location.getLocationid(), HttpStatus.NOT_FOUND);
		}
		locationService.saveLocation(location);
                HttpHeaders headers = new HttpHeaders();
                URI locationUri = ucb.path("/locationapi/").path(String.valueOf(location.getLocationno())).build().toUri();
                headers.setLocation(locationUri);
                ResponseEntity<String> responseEntity = new ResponseEntity<String> (headers, HttpStatus.CREATED);
                return responseEntity;
        }
        
        
        @RequestMapping(value = "/locationtree", method = RequestMethod.GET)
        public ResponseEntity<String> showallGenres() {
            String locationid = "0";
            String location = locationService.findJsonTree(locationid);
            return new ResponseEntity(location, HttpStatus.OK);
        }
        
        /*
        @RequestMapping(value="/deletelocation/{locationno}" )
        public Location getCustomerById(@PathVariable Integer locationno) throws CustomerNotFoundException
        {
        return locationService.findByLocationno(locationno);
        }

        @ResponseStatus(value="HttpStatus.NOT_FOUND",reason="This customer is not found in the system")
        public class CustomerNotFoundException extends Exception 
        {
        private static final long serialVersionUID = 100L;
        }
        */
        
        
        @ExceptionHandler({LocationException.class, java.sql.SQLException.class})
	public ResponseEntity<Error> exceptionHandler(Exception ex) {
		Error error = new Error();
		error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<Error>(error, HttpStatus.OK);
	}

}
