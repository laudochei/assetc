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
        Location findById(String locationid);
        boolean locationexists(Location location);
        Location findParentNode();
	List<Location> findAll();
        List<Location> findAllChild(String locationid);
        List<Location> findChildrenofNode(String locationid);
	void saveLocation(Location location);
	void updateLocation(Location location);
        int LocationExists(String locationid);
        void deleteLocation(Integer locationno);
        int deleteException(String locationid);
        int validateLocation(Location location);
        void dragdrop(Location location, String locationid, String parentname);
        Location updateSingleLocation(Integer locationno, Location location);
        String findJsonTree(String locationid);
        int checkrootnode(Integer locationno);
        void reassign(String locationid_source, String parentname_source, String locationid_dest, String parentname_dest, Location location); //Integer locationno_dest, String parentname_source, String parentname_dest);
        int LocationHasChild(String locationid);
        List<Location> findMultiList(Integer numofrecords, Integer pageno);
        List<Location> findMultiListRange(Integer numofrecords, Integer pageno);
        List<Location> findMultiList(Integer numOfrecords);
        int countRowsInTable();
        
        
    
}
