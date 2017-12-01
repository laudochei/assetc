/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.data;


import assetc.model.Location;
import java.util.List;

/**
 *
 * @author Laud.Ochei
 */
public interface LocationDao {
    
        Location findByNo(Integer locationno);
	List<Location> findAll();
	void saveLocation(Location location);
        
	void updateLocation(Location location);
       
        int LocationExists(String locationid);
        void deleteLocation(Integer locationno);
        int validateLocation(Location location);
        
        //Location validateLocation(Location loc);
        
       
        
        
    
}
