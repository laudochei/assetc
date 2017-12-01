/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.service;

import assetc.model.Asset;
import java.util.List;

/**
 *
 * @author Laud.Ochei
 */
public interface AssetService {
    
        Asset findByNo(Integer assetno);
	List<Asset> findAllAsset();
	//void saveOrUpdate(User user);
	//void delete(int userid);
        //User validateUser(Login login);
               
        int AssetExists(String assetid);
        
        void saveAsset(Asset asset);
	void updateAsset(Asset asset);
        void delete(Integer assetno);
        int setAction(Integer actionno);
    
}
