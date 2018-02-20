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
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(value = "/display/{assetno}", method = RequestMethod.GET)
	public ResponseEntity<String> showAsset(@PathVariable("assetno") Integer assetno) {
		Asset asset = assetService.findByNo(assetno);               
		if (asset == null) {
                    return new ResponseEntity("No asset found for ID " + assetno, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(asset, HttpStatus.OK);
	}
        
        
        //update asset
        @RequestMapping(value = "/update/{assetno}", method = RequestMethod.PUT, headers = "Accept=application/json")
        public ResponseEntity<Void> updateAssset(@PathVariable("assetno") Integer assetno, @RequestBody Asset asset) {
            //location.setLocationno(locationno);
            assetService.updateAsset(asset);
            HttpHeaders headers = new HttpHeaders();
            return new ResponseEntity<Void>(headers, HttpStatus.OK);
        }
        
        
        //add asset
        @RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
        public ResponseEntity<Void> addAsset(@RequestBody Asset asset) {         
         assetService.saveAsset(asset);
         HttpHeaders headers = new HttpHeaders();
         return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
        }
        
        //delete assetadd
        @RequestMapping(value = "/delete/{assetno}", method = RequestMethod.GET)
        public ResponseEntity<Asset>  deleteLocation(@PathVariable("assetno") Integer assetno) {
            System.out.println("Fetching & Deleting Asset with no " + assetno);
            
            Asset asset = assetService.findByNo(assetno); 
            if (asset == null) {
		return new ResponseEntity("No asset found for ID " + assetno, HttpStatus.NOT_FOUND);
            }    
            assetService.delete(assetno);
            return new ResponseEntity<Asset>(HttpStatus.NO_CONTENT);
                
        }   
        
       
        
}
