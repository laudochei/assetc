package assetc.service;

import assetc.model.Login;
import java.util.List;

//import com.mkyong.form.model.User;

import assetc.model.User;

public interface UserService {

	User findById(Integer userid);
	List<User> findAll();
        User validateUser(Login login);
        int UserExists(String username);
        void save(User user);
	void update(User user);
        void delete(Integer userid);
        
	
}