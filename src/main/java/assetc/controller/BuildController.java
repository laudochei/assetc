/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.controller;

import assetc.service.BuildService;
import assetc.validator.BuildFormValidator;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/builds")
public class BuildController {
    
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
        @RequestMapping(value = "/asset", method=GET)
        public String showAllBuild(Model model) {
           // model.addAttribute("assets", buildService.findAllAsset());
            return "assetbuild";
        }
        
        
        @RequestMapping(value = "/asset/tableview", method=GET)
        public String showTableView(Model model) {
           // model.addAttribute("assets", buildService.findAllAsset());
            return "tableview";
        }
        
        @RequestMapping(value = "/asset/treeview", method=GET)
        public String showTreeView(Model model) {
           // model.addAttribute("assets", buildService.findAllAsset());
            return "treeview";
        }
        
        @RequestMapping(value = "/asset/deletenode", method=GET)
        public String showDeleteNode(Model model) {
           // model.addAttribute("assets", buildService.findAllAsset());
            return "deletenode";
        }
        
        @RequestMapping(value = "/asset/addnode", method=GET)
        public String showAddNode(Model model) {
           // model.addAttribute("assets", buildService.findAllAsset());
            return "addnode";
        }
        
        @RequestMapping(value = "/asset/updatenode", method=GET)
        public String showUpdateNode(Model model) {
           // model.addAttribute("assets", buildService.findAllAsset());
            return "updatenode";
        }
        
        @RequestMapping(value = "/asset/dragdrop", method=GET)
        public String showDragDrop(Model model) {
           // model.addAttribute("assets", buildService.findAllAsset());
            return "dragdrop";
        }
        
        
        
        @RequestMapping(value = "/asset/treeview_live", method=GET)
        public String showTreeViewLive(Model model) {
           
            return "treeview_live";
        }
        
        
        
       
    
}












