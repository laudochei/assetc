/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import assetc.model.Asset;
import assetc.service.AssetService;
import assetc.validator.AssetException;
import java.net.URI;
import java.sql.SQLException;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/assetapi")
public class AssetController {

	
	private AssetService assetService;

	@Autowired
	public void setAssetService(AssetService assetService) {
		this.assetService = assetService;
	}

	    
        // list page
        @RequestMapping(value = "/assetlist", method=GET)
        public List<Asset> showAllLocation(Model model) {
            return assetService.findAllAsset();
        }
        
        
        // show asset
	@RequestMapping(value = "/{assetno}", method = RequestMethod.GET)
	public ResponseEntity<String> showAsset(@PathVariable("assetno") Integer assetno) {
		Asset asset = assetService.findByNo(assetno);               
		if (asset == null) {
                    return new ResponseEntity("No asset found for ID " + assetno, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(asset, HttpStatus.OK);
	}
        
        
        //update asset
        @RequestMapping(value = "/update/{assetno}", method = RequestMethod.PUT, headers = "Accept=application/json")
        public ResponseEntity<Void> updateAssset(@PathVariable("assetno") Integer assetno, @RequestBody Asset asset) throws AssetException {
            Asset assetstatus = assetService.findByNo(assetno);
            if (assetstatus == null) {
                throw new AssetException("No record found for assetno: " + assetno);
            }    
            assetService.updateAsset(asset);
            HttpHeaders headers = new HttpHeaders();
            return new ResponseEntity<Void>(headers, HttpStatus.OK);
        }
        
        
        //add asset
        @RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
        public ResponseEntity<Void> addAsset(@RequestBody Asset asset, UriComponentsBuilder ucb) throws AssetException {         
            int assetstatus = assetService.AssetExists(asset.getAssetid());     
            if (assetstatus > 0) { 
                throw new AssetException(" Asset record " + asset.getAssetid() + " already exist");
            }
            assetService.saveAsset(asset);
            HttpHeaders headers = new HttpHeaders();
            URI assetUri = ucb.path("/assetapi/").path(String.valueOf(asset.getAssetno())).build().toUri();
            headers.setLocation(assetUri);
            headers.add("Assetno", String.valueOf(asset.getAssetno()));
            ResponseEntity<Void> responseEntity = new ResponseEntity<Void> (headers, HttpStatus.CREATED);
            return responseEntity;
        }
        
        
        //delete asset
        @RequestMapping(value = "/delete/{assetno}", method = RequestMethod.GET)
        public ResponseEntity<Asset>  deleteAsset(@PathVariable("assetno") Integer assetno) throws AssetException, SQLException {
            Asset asset = assetService.findByNo(assetno); 
            if (asset == null) {
		throw new AssetException("No record found for assetno: " + assetno);
            }   
            /*
            int assetstatus = assetService.deleteException(location.getLocationid());     
            if (locationidstatus > 0) { 
                throw new LocationException("Record cannot be deleted: " + locationno);
                //return new ResponseEntity("Record: " + locationno + " cannot be deleted", HttpStatus.NOT_FOUND);
            }
    
            int rootnodestatus = locationService.checkrootnode(locationno);     
            if (rootnodestatus > 0) { 
                throw new LocationException("Root node cannot be delete");
            }  
            */
            assetService.delete(assetno);
            return new ResponseEntity<Asset>(HttpStatus.NO_CONTENT);   
        } 
            
            
            
            
     @ExceptionHandler({AssetException.class, java.sql.SQLException.class})
	public ResponseEntity<assetc.model.Error> exceptionHandler(Exception ex) {
		assetc.model.Error error = new assetc.model.Error();
		error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<assetc.model.Error>(error, HttpStatus.OK);
	}
        
    
}
