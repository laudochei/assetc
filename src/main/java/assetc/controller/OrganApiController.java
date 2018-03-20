/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.controller;

import assetc.exception.MessageException;
import assetc.model.Organisation;
import assetc.service.OrganisationService;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Laud.Ochei
 */

@RestController
@RequestMapping(value = "/organapi")
public class OrganApiController {
    
    private OrganisationService organisationService;
	@Autowired
	public void setOrganisationService(OrganisationService organisationService) {
		this.organisationService = organisationService;
	}

	    
        // list page
        @RequestMapping(value = "/organlist", method=GET)
        public List<Organisation> displayAllOrg(Model model) {
            return organisationService.findAllOrganisation();
        }
        
        //display a single record
        @RequestMapping(value = "/{orgno}", method = RequestMethod.GET)
	public ResponseEntity<String> getOrg(@PathVariable("orgno") Integer orgno) {
           
		Organisation organisation = organisationService.findByNo(orgno);
		if (organisation == null) {
			return new ResponseEntity("No record found for organisation ID " + orgno, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(organisation, HttpStatus.OK);
        }
        
        
        
        //add a single record
        @RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
        public ResponseEntity<Void> adddOrg(@RequestBody Organisation organisation, UriComponentsBuilder ucb) throws MessageException {   
            int orgidstatus = organisationService.OrganIDExists(organisation.getOrgid());     
            if (orgidstatus > 0) { 
                throw new MessageException("Record already exist for Organisation ID: " + organisation.getOrgid());
            }
            organisationService.saveOrganisation(organisation);
            HttpHeaders headers = new HttpHeaders();
            URI companyUri = ucb.path("/organapi/").path(String.valueOf(organisation.getOrgno())).build().toUri();
            headers.setLocation(companyUri);
            headers.add("OrganisationNo", String.valueOf(organisation.getOrgno()));
            ResponseEntity<Void> responseEntity = new ResponseEntity<Void> (headers, HttpStatus.CREATED);
            return responseEntity;
        }
        
        
        //update a single record
        @RequestMapping(value = "/update/{orgno}", method = RequestMethod.PUT, headers = "Accept=application/json")
        public ResponseEntity<Void> updateOrg(@PathVariable("orgno") Integer orgno, @RequestBody Organisation organisation) throws MessageException {
            Organisation orgstatus = organisationService.findByNo(orgno);
            if (orgstatus == null) {
                throw new MessageException("No record found for organisation ID: " + organisation.getOrgid());
            } 
            organisationService.updateOrganisation(organisation);
            String Msg ="Record updated for Orginisation ID: " + organisation.getOrgid();
            HttpHeaders headers = new HttpHeaders();
            headers.add("SuccessMsg", Msg);
            ResponseEntity<Void> responseEntity = new ResponseEntity<Void> (headers, HttpStatus.CREATED);
            //return responseEntity;
            throw new MessageException("Record updated for organisation ID: " + organisation.getOrgid());
        }
        
        
        //delete a single record
        @RequestMapping(value = "/delete/{orgno}", method = RequestMethod.GET)
        public ResponseEntity<Organisation>  deleteLocation(@PathVariable("orgno") Integer orgno) throws MessageException {
            Organisation organisation = organisationService.findByNo(orgno);
            if (organisation == null) {
                throw new MessageException("No record found for orgno: " + orgno);
            }    
            int orgidstatus = organisationService.checkForeignKey(organisation.getOrgid());     
            if (orgidstatus > 0) { 
                throw new MessageException("Record cannot be deleted: " + orgno);
            }
            
            organisationService.delete(orgno);
            //return new ResponseEntity<Company>(HttpStatus.NO_CONTENT);   
            throw new MessageException("Record deleted for company ID: " + organisation.getOrgid());
        } 
        
        @ExceptionHandler(MessageException.class)
	public ResponseEntity<assetc.model.Message> exceptionMsgHandler(Exception ex) {
		assetc.model.Message msg = new assetc.model.Message();
		msg.setMessage(ex.getMessage());
		return new ResponseEntity<assetc.model.Message>(msg, HttpStatus.OK);
	}
        
        
    
}
