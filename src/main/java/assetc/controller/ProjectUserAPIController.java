/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.controller;

import assetc.exception.MessageException;

import assetc.model.ProjectUser;
import assetc.service.ProjectUserService;
import java.net.URI;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping(value = "/projectuserapi")
public class ProjectUserAPIController {
        private final Logger logger = LoggerFactory.getLogger(ProjectController.class);
        private ProjectUserService projectuserService;
        
	@Autowired
	public void setProjectUserService(ProjectUserService projectuserService) {
		this.projectuserService = projectuserService;
	}

	
        // list page
        @RequestMapping(value = "/projectuserlist", method=GET)
        public  List<ProjectUser> showProjectList(Model model) {
           return projectuserService.findAllProject();
        }
       
        
        //display a single record
        @RequestMapping(value = "/{projectuserno}", method = RequestMethod.GET)
	public ResponseEntity<String> getProject(@PathVariable("projectuserno") Integer projectuserno) {
           
		ProjectUser projectuser = projectuserService.findByNo(projectuserno);
		if (projectuser == null) {
			return new ResponseEntity("No project found for No: " + projectuserno, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(projectuser, HttpStatus.OK);
        }
        
        
         //add a single record
        @RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
        public ResponseEntity<Void> adddProjectUser(@RequestBody ProjectUser projectuser, UriComponentsBuilder ucb) throws MessageException {   
            int projectuserstatus = projectuserService.ProjectUserIDExists(projectuser.getProjectuserid());     
            if (projectuserstatus > 0) { 
                throw new MessageException("Record already exist for Projectuser with ID: " + projectuser.getProjectuserno());
            }
            projectuserService.saveProjectUser(projectuser);
            HttpHeaders headers = new HttpHeaders();
            URI companyUri = ucb.path("/projectuserapi/").path(String.valueOf(projectuser.getProjectuserno())).build().toUri();
            headers.setLocation(companyUri);
            headers.add("Projectuserno", String.valueOf(projectuser.getProjectuserno()));
            ResponseEntity<Void> responseEntity = new ResponseEntity<Void> (headers, HttpStatus.CREATED);
            
            //return responseEntity;
            throw new MessageException("Record added for ProjectUser with ID: " + projectuser.getProjectuserno());
        }
        
         //update a single record
        @RequestMapping(value = "/update/{projectuserno}", method = RequestMethod.PUT, headers = "Accept=application/json")
        public ResponseEntity<Void> updateProjectUser(@PathVariable("projectuserno") Integer projectuserno, @RequestBody ProjectUser projectuser) throws MessageException {
            ProjectUser projectuserstatus = projectuserService.findByNo(projectuserno);
            if (projectuserstatus == null) {
                throw new MessageException("Record cannot be found for ProjectUser ID: " + projectuserno);
            } 
            projectuserService.updateProjectUser(projectuser);
            
            String Msg ="Record updated for projectuser ID: " + projectuser.getProjectuserid();
            HttpHeaders headers = new HttpHeaders();
            headers.add("SuccessMsg", Msg);
            ResponseEntity<Void> responseEntity = new ResponseEntity<Void> (headers, HttpStatus.CREATED);
            //return responseEntity;
            throw new MessageException("Record updated for projectuser ID: " + projectuser.getProjectuserid());
        }
        
        
        //delete a single record
        @RequestMapping(value = "/delete/{projectuserno}", method = RequestMethod.GET)
        public ResponseEntity<ProjectUser>  deleteLocation(@PathVariable("projectuserno") Integer projectuserno) throws MessageException {
            ProjectUser projectuser = projectuserService.findByNo(projectuserno);
            if (projectuser == null) {
                throw new MessageException("No record found for projectuserno: " + projectuserno);
            }    
            projectuserService.delete(projectuserno);
            //return new ResponseEntity<Company>(HttpStatus.NO_CONTENT);   
            throw new MessageException("Record deleted for projectuser ID: " + projectuser.getProjectuserid());
        }        
        
        
        @ExceptionHandler(MessageException.class)
	public ResponseEntity<assetc.model.Message> exceptionMsgHandler(Exception ex) {
            assetc.model.Message msg = new assetc.model.Message();
            msg.setMessage(ex.getMessage());
            return new ResponseEntity<assetc.model.Message>(msg, HttpStatus.OK);
	}
        
        
        
        /*
        @ExceptionHandler(MessageException.class)
	public ResponseEntity<assetc.model.MessageHeader> exceptionMsgHandler(Exception ex) {
                assetc.model.MessageHeader msg = new assetc.model.MessageHeader();
                ProjectUser projectuser = new ProjectUser(); 
                HttpHeaders headers = new HttpHeaders();
                headers.add("SuccessMsg", "Record added successfully!");
                headers.add("ProjectUserNo", String.valueOf(projectuser.getProjectuserno()));
                msg.setHeaders(headers);
                msg.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
		msg.setMessage(ex.getMessage());
		return new ResponseEntity<assetc.model.MessageHeader>(msg, HttpStatus.OK);   
	}
        */
        
        
 
}