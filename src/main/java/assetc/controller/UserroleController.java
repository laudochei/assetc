/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.controller;

import assetc.model.Asset;
import assetc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import assetc.service.UserService;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/userroleapi")
public class UserroleController {

	

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	// list page
        @RequestMapping(value = "/userlist", method=GET)
        public List<User> showAllUser(Model model) {
            return userService.findAll();
        }
        
        
        // show asset
	@RequestMapping(value = "/display/{userid}", method = RequestMethod.GET)
	public ResponseEntity<String> showUser(@PathVariable("userid") Integer userid) {
		User user = userService.findById(userid);               
		if (user == null) {
                    return new ResponseEntity("No user found for ID " + userid, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(user, HttpStatus.OK);
	}
        
        
        //update asset
        @RequestMapping(value = "/update/{userid}", method = RequestMethod.PUT, headers = "Accept=application/json")
        public ResponseEntity<Void> updateUser(@PathVariable("userid") Integer userid, @RequestBody User user) {
            //location.setLocationno(locationno);
            userService.update(user);
            HttpHeaders headers = new HttpHeaders();
            return new ResponseEntity<Void>(headers, HttpStatus.OK);
        }
        
        
        //add asset
        @RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
        public ResponseEntity<Void> addUser(@RequestBody User user) {         
         userService.save(user);
         HttpHeaders headers = new HttpHeaders();
         return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
        }
        
        //delete assetadd
        @RequestMapping(value = "/delete/{userid}", method = RequestMethod.GET)
        public ResponseEntity<User>  deleteUser(@PathVariable("userid") Integer userid) {
            System.out.println("Fetching & Deleting User with id " + userid);
            
            User user = userService.findById(userid); 
            if (user == null) {
		return new ResponseEntity("No user found for ID " + userid, HttpStatus.NOT_FOUND);
            }    
            userService.delete(userid);
            return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
                
        }   

}
