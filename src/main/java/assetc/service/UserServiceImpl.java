package assetc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import assetc.data.UserDao;
import assetc.model.User;
import assetc.model.Login;
import java.util.HashMap;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {

	UserDao userDao;

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User findById(Integer userid) {
		return userDao.findById(userid);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public void save(User user) {
            userDao.save(user);
	}
        
        
        @Override
	public void update(User user) {
            userDao.update(user);
	}

               
        @Override
        public void delete(Integer userid) {
            userDao.delete(userid);
        }
         
        
        @Override
	public User validateUser(Login login) {
		return userDao.validateUser(login);     
	}
        
        
        @Override
        public int UserExists(String username) {
            return userDao.UserExists(username);
        }

}