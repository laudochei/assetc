/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.service;

import assetc.model.Location;
import java.util.List;

public interface LocationService {
    
    List<Location> findAllLocation();
    Location findByLocationno(Integer locationno);
    Location findByLocationid(String locationid);
    List<Location> findAllChild(String locationid);
    void deleteLocation(Integer locationno);
    void saveLocation(Location location);
    void updateLocation(Location location);
    int LocationExists(String locationid);
    int operationType(int operationno);
    boolean exists(Location location);
    int validateLocation(Location location);
    int checkAction(Integer actionno);
    void dragdrop(Location location, String locationid, String parentname);
    Location findParentNode();
    List<Location> findChildrenofNode(String locationid);
    String findJsonTree(String locationid);
    int deleteException(String locationid);
    int checkrootnode(Integer locationno);
    void reassign(String locationid_source, String parentname_source, String locationid_dest, String parentname_dest, Location location);
    int LocationHasChild(String locationid);
    List<Location> findMultiList(Integer numofrecords, Integer pageno);
    List<Location> findMultiListRange(Integer numofrecords, Integer pageno);
    List<Location> findMultiList(Integer numOfrecords);
    int countRowsInTable();
    
    
    
}
