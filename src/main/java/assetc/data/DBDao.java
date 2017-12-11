/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.data;

import assetc.model.Db;
import java.util.List;



public interface DBDao {

	//User findById(Integer userid);
	List<Db> selectrecord();
	void createtb(Db db);
        //void insertrecord(Db db);
	//void update(User user);
	//void delete(Integer userid);
        //User validateUser(Login login);
        //int UserExists(String username);
        
           
}