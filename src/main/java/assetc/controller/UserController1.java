package assetc.controller;


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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import assetc.model.User;
import assetc.service.UserService;
import assetc.validator.UserFormValidator;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequestMapping(value = "/users")
public class UserController1 {

	private final Logger logger = LoggerFactory.getLogger(UserController1.class);

	@Autowired
	UserFormValidator userFormValidator;
	
        //Set a form validator
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(userFormValidator);
	}

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	
    
	// list page
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String showAllUsers(Model model) {

		logger.debug("showAllUsers()");
		model.addAttribute("users", userService.findAll());
		return "list";

	}
        
       
        // show user
	@RequestMapping(value = "/{userid}", method = RequestMethod.GET)
	public String showUser(@PathVariable("userid") Integer userid, Model model) {

		logger.debug("showUser() userid: {}", userid);

		User user = userService.findById(userid);
		if (user == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "User not found");
		}
		model.addAttribute("user", user);

		return "profile";
	}
        
        
        
        // delete user
        //@RequestMapping(value = "/{userid}/delete", method = RequestMethod.GET)
        @RequestMapping(value = "/{userid}/delete", method = RequestMethod.GET)
	public String deleteUser(@PathVariable("userid") Integer userid, final RedirectAttributes redirectAttributes) {
		logger.debug("deleteUser() : {}", userid);
		userService.delete(userid);
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "User is deleted!");
                return "redirect:/users/list";
                
                //return "redirect:/users/" + userid;
	}
        
        
    
    
        
        
        
        // show add user form
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String showAddUserForm(Model model) {

		logger.debug("showAddUserForm()");

		User user = new User();

		// set default value
		user.setFirstname("peter");
                user.setLastname("adam");
                user.setUsername("peteradam");
                user.setEmail("test@addenergy.no");
                user.setPassword("123");
                user.setConfirmPassword("123");
                user.setAddress("Aberdeen");
                user.setPhone(1234567890L);
                user.setEnabled(true);

		model.addAttribute("addUser", user);	    
                return "adduser";
                
	}
        
        
        
        // add user
        @RequestMapping(value="/add", method = RequestMethod.POST)
        public String addUser(@ModelAttribute("addUser") @Validated User user,BindingResult result, Model model,final RedirectAttributes redirectAttributes) {
            logger.debug("addUser() : {}", user);
            //check for errors in the form
              if (result.hasErrors()) 
              {
                return "adduser";
              } else { // save new user
                  redirectAttributes.addFlashAttribute("css", "success");
                  redirectAttributes.addFlashAttribute("msg", "User updated successfully!");
                  userService.save(user);
                  return "redirect:/users/" + user.getUserid();
              }
        }
    
    
        
        // show update form
	@RequestMapping(value = "/{userid}/update", method = RequestMethod.GET)
	public String showUpdateUserForm(@PathVariable("userid") int userid, Model model) {
		logger.debug("showUpdateUserForm() : {}", userid);
                User user = userService.findById(userid);
		model.addAttribute("addUser", user);	    
                return "adduser";
	}
        
        
        
    // update user
    @RequestMapping(value = "/{userid}/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("addUser") @Validated User user,BindingResult result, Model model,final RedirectAttributes redirectAttributes) {
	logger.debug("addUser() : {}", user);
        if (result.hasErrors()) {
            return "adduser";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
            redirectAttributes.addFlashAttribute("msg", "User updated successfully!");
            
            userService.update(user);
            return "redirect:/users/" + (int)user.getUserid();
        }
    }
        

}