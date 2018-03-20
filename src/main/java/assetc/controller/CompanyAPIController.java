/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.controller;

import assetc.exception.CompanyException;
import assetc.exception.MessageException;
import assetc.model.Company;
import assetc.service.CompanyService;
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
@RequestMapping(value = "/companyapi")
public class CompanyAPIController {
    
        private CompanyService companyService;
	@Autowired
	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}

	    
        // list page
        @RequestMapping(value = "/companylist", method=GET)
        public List<Company> displayAllCompany(Model model) {
            return companyService.findAllCompany();
        }
        
         //display a single record
        @RequestMapping(value = "/{companyno}", method = RequestMethod.GET)
	public ResponseEntity<String> getCompany(@PathVariable("companyno") Integer companyno) {
           
		Company company = companyService.findByNo(companyno);
		if (company == null) {
			return new ResponseEntity("No company found for ID " + companyno, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(company, HttpStatus.OK);
        }
        
        
         //add a single record
        @RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
        public ResponseEntity<Void> adddCompany(@RequestBody Company company, UriComponentsBuilder ucb) throws CompanyException {   
            int companyidstatus = companyService.CompanyIDExists(company.getCompanyid());     
            if (companyidstatus > 0) { 
                throw new CompanyException(" Company record " + company.getCompanyid() + " already exist");
            }
            companyService.saveCompany(company);
            HttpHeaders headers = new HttpHeaders();
            URI companyUri = ucb.path("/companyapi/").path(String.valueOf(company.getCompanyno())).build().toUri();
            headers.setLocation(companyUri);
            headers.add("Companyno", String.valueOf(company.getCompanyno()));
            ResponseEntity<Void> responseEntity = new ResponseEntity<Void> (headers, HttpStatus.CREATED);
            return responseEntity;
        }
        
         //update a single record
        @RequestMapping(value = "/update/{companyno}", method = RequestMethod.PUT, headers = "Accept=application/json")
        public ResponseEntity<Void> updateCompany(@PathVariable("companyno") Integer companyno, @RequestBody Company company) throws CompanyException, MessageException {
            Company companystatus = companyService.findByNo(companyno);
            if (companystatus == null) {
                throw new CompanyException(" Company record " + company.getCompanyid() + " cannot be found exist");
            } 
            companyService.updateCompany(company);
            
            String Msg ="Record updated for company ID: " + company.getCompanyid();
            HttpHeaders headers = new HttpHeaders();
            headers.add("SuccessMsg", Msg);
            ResponseEntity<Void> responseEntity = new ResponseEntity<Void> (headers, HttpStatus.CREATED);
            return responseEntity;
            //throw new MessageException("Record updated for company ID: " + company.getCompanyid());
        }
        
        
        //delete a single record
        @RequestMapping(value = "/delete/{companyno}", method = RequestMethod.GET)
        public ResponseEntity<Company>  deleteLocation(@PathVariable("companyno") Integer companyno) throws CompanyException,MessageException {
            Company company = companyService.findByNo(companyno);
            if (company == null) {
                throw new CompanyException("No record found for companyno: " + companyno);
            }    
            
            int companyidstatus = companyService.checkForeignKey(company.getCompanyid());     
            if (companyidstatus > 0) { 
                throw new CompanyException("Record ID: " + companyno + " cannot be deleted");
            }
            companyService.delete(companyno);
            //return new ResponseEntity<Company>(HttpStatus.NO_CONTENT);   
            throw new MessageException("Record deleted for company ID: " + company.getCompanyid());
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
