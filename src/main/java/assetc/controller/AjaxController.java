/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping(value = "/ajaxcalls")
public class AjaxController {
    
    
    
     // list page
    @RequestMapping(value = "/display", method=GET)
    public String displayAjax(Model model) {
        
        return "ajaxcalls/displaycalls";
        
    }
  
    
    @RequestMapping(value = "/display/postrequest", method=GET)
    public String postvalues(Model model) {
        
        return "ajaxcalls/postvalues";
    }
    
    
    @RequestMapping(value = "/display/getrequest", method=GET)
    public String getvalues(Model model) {
        
        return "ajaxcalls/getvalues";
    }
}
