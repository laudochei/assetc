/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.service;

import assetc.model.Login;
import assetc.model.User;
import java.util.List;





import assetc.model.Login;
import java.util.List;

//import com.mkyong.form.model.User;

import assetc.model.User;

public interface DBService {

	User findById(Integer userid);
	List<User> findAll();
        User validateUser(Login login);
        int UserExists(String username);
        void save(User user);
	void update(User user);
        void delete(Integer userid);
        
	
}