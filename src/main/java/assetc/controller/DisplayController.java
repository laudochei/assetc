/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.controller;

import assetc.service.BuildService;
import assetc.validator.BuildFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(value = "/display")
public class DisplayController {
    
    private final Logger logger = LoggerFactory.getLogger(BuildController.class);

	@Autowired
	BuildFormValidator buildFormValidator;
	
        //Set a form validator
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(buildFormValidator);
	}

	private BuildService buildService;

	@Autowired
	public void setBuildService(BuildService buildService) {
		this.buildService = buildService;
	}

	    
        // list page
        @RequestMapping(value = "/viewtree", method=GET)
        public String showAllBuild(Model model) {
           
            return "reports/viewtree";
        }
        
        
        // list page
        @RequestMapping(value = "/viewtable", method=GET)
        public String showViewTable(Model model) {
           
            return "reports/viewtable";
        }
}
