/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.controller;

/**
 *
 * @author Laud.Ochei
 */


import assetc.model.Login;
import assetc.model.User;
import assetc.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/account")
public class LoginController {

  private UserService userService;
  

  @Autowired
  public LoginController(UserService userService) {
    this.userService = userService;
  }
  
  
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView("login");
    mav.addObject("login", new Login());
    return mav;
  }
  
  
  @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
  public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("login") Login login) {
    ModelAndView mav = null;
    User user = userService.validateUser(login);
    if (null != user) {
        mav = new ModelAndView("welcome");
        //mav.addObject("firstname", user.getFirstname());
        mav.addObject("firstname", user.getUsername());
    } else {
        mav = new ModelAndView("login");
        mav.addObject("message", "Username or Password is wrong!!");
    }
    return mav;
  }

 
  
}