package assetc.data;

import assetc.model.Login;
import java.util.List;

import assetc.model.User;

public interface UserDao {

	User findById(Integer userid);
	List<User> findAll();
	void save(User user);
	void update(User user);
	void delete(Integer userid);
        User validateUser(Login login);
        int UserExists(String username);
        
   
}