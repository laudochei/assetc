/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.service;


import assetc.data.LocationDao;
import assetc.model.Location;
import assetc.model.Task;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;






@Service("locationService")
public class LocationServiceImpl implements LocationService {
    
        LocationDao locationDao;

	@Autowired
	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}

	

	@Override
	public List<Location> findAllLocation() {
		return locationDao.findAll();
	}
        
       @Override
        public Location findByLocationno(Integer locationno) {    
            return locationDao.findByNo(locationno);
        }
        
        @Override
        public Location findByLocationid(String locationid) {    
            return locationDao.findById(locationid);
        }
        
        @Override
        public int checkAction(Integer actionno) {
            Task task = new Task(); 
                 
            int op;
            if (actionno == 1) {
               task.setCreate(1); // create 
                op = 1;
            } else if (actionno == 2) {
               task.setUpdate(2); // read
               op = 2;
            } else if (actionno == 3) {
               task.setUpdate(3); // update
               op = 3;
            } else {
               task.setUpdate(4); //delete
               op = 4;
            }
            return op;
        }
        
        
        @Override
        public void deleteLocation(Integer locationno) {    
            locationDao.deleteLocation(locationno);
        }
        
       @Override
	public void saveLocation(Location location) {
            locationDao.saveLocation(location);
	}
        
        @Override
	public void updateLocation(Location location) {
            locationDao.updateLocation(location);
	}
        
        
        @Override
        public int LocationExists(String locationid) {
            return locationDao.LocationExists(locationid);
        }
        
        /*
        @Override
        public Location validateLocation(Location location) {
            return locationDao.validateLocation(location);
        }
        */
        
        
        
        @Override
        public int validateLocation(Location location) {
            return locationDao.validateLocation(location);
        }
        
        
        @Override
        public int operationType(int opno) {
            int op;
            if (opno == 1) {
                op = 1; // create 
            } else if (opno == 2) {
                op = 2; // read
            } else if (opno == 3) {
                op = 3; // update
            } else {
                op = 4; //delete
            }
            return op;
        }
        
        
        @Override
	public void dragdrop(Location location, String locationid, String parentname){
            locationDao.dragdrop(location, locationid, parentname);
            
        }
        
}
