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
        
         
        @RequestMapping(value = "/rootnode", method = RequestMethod.GET)
        public ResponseEntity<String> getRootNode() {
            Location location = locationService.findParentNode();
            if (location == null) {
                return new ResponseEntity("No location found for root node", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity(location, HttpStatus.OK);
        }
        
        
        
        //display all children of a particular node when given a locationid
        @RequestMapping(value = "/siblings/{locationno}", method = RequestMethod.GET)
	public List<Location> getSiblings(@PathVariable("locationno") Integer locationno,Model model) {
                Location location = locationService.findByLocationno(locationno);
                return locationService.findAllChild(location.getParentname());
        }
        
        
        //display all children of a particular node when given a locationid
        @RequestMapping(value = "/children/{locationno}", method = RequestMethod.GET)
	public List<Location> getAllLocationChild(@PathVariable("locationno") Integer locationno,Model model) {
                Location location = locationService.findByLocationno(locationno);
                return locationService.findAllChild(location.getLocationid());
        }
        
        //display a single record
        @RequestMapping(value = "/allnodes", method = RequestMethod.GET)
        public List<Location> getChildrenofNode(@RequestParam(value="locationno", defaultValue="20") Integer locationno) {
             Location location = locationService.findByLocationno(locationno);
            return locationService.findChildrenofNode(location.getLocationid());
        }
        
                
        @RequestMapping(value = "/update/{locationno}", method = RequestMethod.PUT, headers = "Accept=application/json")
        public ResponseEntity<Void> updateLocation(@PathVariable("locationno") Integer locationno, @RequestBody Location location) {
         location.setLocationno(locationno);
         locationService.updateLocation(location);
         HttpHeaders headers = new HttpHeaders();
         return new ResponseEntity<Void>(headers, HttpStatus.OK);
        }
        
        
        @RequestMapping(value = "/assign", method = RequestMethod.PUT, headers = "Accept=application/json")
        public ResponseEntity<Void>  reassign(@RequestParam("locationno_source") Integer locationno_source, @RequestParam(value="locationno_dest") Integer locationno_dest, @RequestBody Location location) throws LocationException {
            Location location_source = locationService.findByLocationno(locationno_source);
            Location location_dest = locationService.findByLocationno(locationno_dest);
            if (location_source == null) {
                return new ResponseEntity("No location found for ID " + location_source, HttpStatus.NOT_FOUND);  
            } 
            
            if (location_dest == null) {
                return new ResponseEntity("No location found for ID " + locationno_dest, HttpStatus.NOT_FOUND);  
            } 
                       
            int rootnodestatus = locationService.checkrootnode(locationno_source);     
            if (rootnodestatus > 0) { 
                throw new LocationException("Root node cannot be moved");
            }
            
            location_source.setParentname(location_dest.getLocationid());
            locationService.updateLocation(location_source);
            HttpHeaders headers = new HttpHeaders();
            return new ResponseEntity<Void>(headers, HttpStatus.OK);
        }
        
        //delete location records
        @RequestMapping(value = "/delete/{locationno}", method = RequestMethod.GET)
        public ResponseEntity<Location>  deleteLocation(@PathVariable("locationno") Integer locationno) throws LocationException, SQLException {
            
            
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
            
            
            int childstatus = locationService.LocationHasChild(location.getLocationid());     
            if (childstatus > 0) { 
                throw new LocationException("Node cannot be delete because it has a child!");
            } 
            
            locationService.deleteLocation(locationno);
            return new ResponseEntity<Location>(HttpStatus.NO_CONTENT);   
        } 
        
              
        
        //show siblings after deleting location records
        @RequestMapping(value = "/SiblingsAfterDelete/{locationno}", method = RequestMethod.GET)
        public List<Location>  siblingsAfterDelete(@PathVariable("locationno") Integer locationno) throws LocationException {  
            Location location = locationService.findByLocationno(locationno);
            if (location == null) {
                throw new LocationException("No record found for locationno: " + locationno);
            }    
            
            int locationidstatus = locationService.deleteException(location.getLocationid());     
            if (locationidstatus > 0) { 
                throw new LocationException("Record cannot be deleted: " + locationno);
            }
    
            int rootnodestatus = locationService.checkrootnode(locationno);     
            if (rootnodestatus > 0) { 
                throw new LocationException("Root node cannot be deleted");
            } 
           
            int childstatus = locationService.LocationHasChild(location.getLocationid());     
            if (childstatus > 0) { 
                throw new LocationException("Node cannot be deleted because it has a child!");
            } 
            
            locationService.deleteLocation(locationno);
            return locationService.findAllChild(location.getParentname());
        } 
        
        
        @RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
        public ResponseEntity<Void> adddynamicurl(@RequestBody Location location, UriComponentsBuilder ucb) throws LocationException {   
            logger.info("creating new location: {}", location);
            int locationidstatus = locationService.LocationExists(location.getLocationid());     
            if (locationidstatus > 0) { 
                throw new LocationException(" Location record " + location.getLocationid() + " already exist");
            }
            locationService.saveLocation(location);
            HttpHeaders headers = new HttpHeaders();
            URI locationUri = ucb.path("/locationapi/").path(String.valueOf(location.getLocationno())).build().toUri();
            headers.setLocation(locationUri);
            headers.add("Locationno", String.valueOf(location.getLocationno()));
            ResponseEntity<Void> responseEntity = new ResponseEntity<Void> (headers, HttpStatus.CREATED);
            return responseEntity;
        }
        
        
        //show details of the added record
        @RequestMapping(value = "/NodesAfterAdd", method = RequestMethod.POST, headers = "Accept=application/json")
        public ResponseEntity<Location>  nodesAfterAdd(@RequestBody Location location, UriComponentsBuilder ucb) throws LocationException {  
            logger.info("creating new location: {}", location);
            int locationidstatus = locationService.LocationExists(location.getLocationid());     
            if (locationidstatus > 0) { 
                throw new LocationException(" Location record " + location.getLocationid() + " already exist");
            }
            locationService.saveLocation(location);
            HttpHeaders headers = new HttpHeaders();
            URI locationUri = ucb.path("/locationapi/").path(String.valueOf(location.getLocationno())).build().toUri();
            headers.setLocation(locationUri);
            headers.add("Locationno", String.valueOf(location.getLocationno()));
            ResponseEntity<Location> responseEntity = new ResponseEntity<Location> (location, headers, HttpStatus.CREATED);
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
        
       
        
        
        @ExceptionHandler({LocationException.class, java.sql.SQLException.class})
	public ResponseEntity<Error> exceptionHandler(Exception ex) {
		Error error = new Error();
		error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<Error>(error, HttpStatus.OK);
	}

}
