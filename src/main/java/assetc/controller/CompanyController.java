/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.controller;

import assetc.model.Company;
import assetc.service.CompanyService;
import assetc.validator.CompanyFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 *
 * @author Laud.Ochei
 */
@Controller
@RequestMapping(value = "/companys")
public class CompanyController {
    
    private final Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    CompanyFormValidator companyFormValidator;
	
    //Set a form validator
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
	binder.setValidator(companyFormValidator);
    }
    
    private CompanyService companyService;
    
    @Autowired
    public void setCompanyService(CompanyService companyService) {
	this.companyService = companyService;
    }
    
    
    // list organisation
    @RequestMapping(value = "/companylist", method=GET)
    public String displayCompany(Model model) {
        model.addAttribute("companys", companyService.findAllCompany());
        return "companylist";
        
    }
    
    
    @RequestMapping(value = "/{companyno}", method = RequestMethod.GET)
	public String showOrg(@PathVariable("companyno") Integer companyno, Model model) {

		//logger.debug("showOrg() orgno: {}", orgno);
		Company company = companyService.findByNo(companyno);
		if (company == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "Company details not found");
		}
		model.addAttribute("company", company);
		return "companyprofile";
	}
    
    
}
