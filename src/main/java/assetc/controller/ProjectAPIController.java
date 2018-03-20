/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.controller;

import assetc.exception.CompanyException;
import assetc.exception.MessageException;
import assetc.model.Project;
import assetc.service.ProjectService;
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
@RequestMapping(value = "/projectapi")
public class ProjectAPIController {
        private ProjectService projectService;
        
       

	@Autowired
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	
        // list page
        @RequestMapping(value = "/projectlist", method=GET)
        public  List<Project> showProjectList(Model model) {
           return projectService.findAllProject();
        }
       
        //display a single record
        @RequestMapping(value = "/{projectno}", method = RequestMethod.GET)
	public ResponseEntity<String> getProject(@PathVariable("projectno") Integer projectno) {
           
		Project project = projectService.findByNo(projectno);
		if (project == null) {
			return new ResponseEntity("No project found for No: " + projectno, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(project, HttpStatus.OK);
        }
        
        
         //add a single record
        @RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
        public ResponseEntity<String> adddProject(@RequestBody Project project, UriComponentsBuilder ucb) throws MessageException {   
            int projectidstatus = projectService.ProjectIDExists(project.getProjectid());     
            if (projectidstatus > 0) { 
                throw new MessageException(" Message record " + project.getProjectid() + " already exist");
            }
            projectService.saveProject(project);
            HttpHeaders headers = new HttpHeaders();
            URI projectUri = ucb.path("/projectapi/").path(String.valueOf(project.getProjectno())).build().toUri();
            headers.setLocation(projectUri);
            headers.add("Projectno", String.valueOf(project.getProjectno()));
            //ResponseEntity<Void> responseEntity = new ResponseEntity<Void> (headers, HttpStatus.CREATED);
            ResponseEntity<String> responseEntity = new ResponseEntity<String> (headers, HttpStatus.CREATED);
            //throw new MessageException("Record inserted for project ID: " + project.getProjectid());

            //ResponseEntity<Location> responseEntity = new ResponseEntity<Location> (project, headers, HttpStatus.CREATED);
            //return new ResponseEntity(locationService.findAllChild(location.getParentname()), headers, HttpStatus.OK);
            return responseEntity;
        }
       
        
        
        //update a single record
        @RequestMapping(value = "/update/{projectno}", method = RequestMethod.PUT, headers = "Accept=application/json")
        public ResponseEntity<Void> updateProject(@PathVariable("projectno") Integer projectno, @RequestBody Project project) throws MessageException {
            Project projectstatus = projectService.findByNo(projectno);
            if (projectstatus == null) {
                throw new MessageException("No record found for project ID: " + project.getProjectid());
            } 
            projectService.updateProject(project);
            
            String Msg ="Record updated for project ID: " + project.getProjectid();
            HttpHeaders headers = new HttpHeaders();
            headers.add("SuccessMsg", Msg);
            ResponseEntity<Void> responseEntity = new ResponseEntity<Void> (headers, HttpStatus.CREATED);
            //return responseEntity;
            throw new MessageException("Record updated for project ID: " + project.getProjectid());
            //return new ResponseEntity(projectService.findAllProject(project.getProjectid()), headers, HttpStatus.OK);
        }
        
        
        //delete a single record
        @RequestMapping(value = "/delete/{projectno}", method = RequestMethod.GET)
        public ResponseEntity<Project>  deleteProject(@PathVariable("projectno") Integer projectno) throws MessageException {
            Project project = projectService.findByNo(projectno);
            if (project == null) {
                throw new MessageException("No record found for projectno: " + projectno);
            }    
            
            int projectidstatus = projectService.checkForeignKey(project.getProjectid());     
            if (projectidstatus > 0) { 
                throw new MessageException("Record ID: " + projectno + " cannot be deleted");
            }
            projectService.delete(projectno);
            //return new ResponseEntity<Company>(HttpStatus.NO_CONTENT);   
            throw new MessageException("Record deleted for project ID: " + project.getProjectid());
        } 
        

        @ExceptionHandler({CompanyException.class, java.sql.SQLException.class})
	public ResponseEntity<assetc.model.Error> exceptionHandler(Exception ex) {
		assetc.model.Error error = new assetc.model.Error();
		error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<assetc.model.Error>(error, HttpStatus.OK);
	}
        
        
        
        @ExceptionHandler(MessageException.class)
	public ResponseEntity<assetc.model.Message> exceptionMsgHandler(Exception ex) {
		assetc.model.Message msg = new assetc.model.Message();
		msg.setMessage(ex.getMessage());
		return new ResponseEntity<assetc.model.Message>(msg, HttpStatus.OK);
	}
 
}