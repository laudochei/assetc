/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import assetc.model.UserList;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/userlist")
public class UserListController {
    private List<UserList> userList = new ArrayList<UserList>();

   @RequestMapping(value="/addAjaxList", method=RequestMethod.GET)
    //@RequestMapping(method = GET)
    public String showForm(){
        //return "AddUser_1";
        return "addajax_1";
    }

    @RequestMapping(value="/addAjaxList",method=RequestMethod.POST)
    //public @ResponseBody String addUser(@ModelAttribute(value="user") UserList user, BindingResult result, final RedirectAttributes redirectAttributes ){
    public String addUser(@ModelAttribute(value="user") UserList user, BindingResult result, final RedirectAttributes redirectAttributes ){
        
        String returnText;
        if(!result.hasErrors()){
            userList.add(user);
            returnText = "User has been added to the list. Total number of users are " + userList.size();
        }else{
            returnText = "Sorry, an error has occur. User has not been added to list.";
        }
        //return returnText;
        return "redirect:/userlist/showAjaxList";
    }

    @RequestMapping(value="/showAjaxList")
    public String showUsers(ModelMap model){
        model.addAttribute("Users", userList);
        return "ShowUser_1";
    }
    
   
    
}