/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.data;

import assetc.model.Asset;
import java.util.List;

/**
 *
 * @author Laud.Ochei
 */
public interface AssetDao {
        Asset findByNo(Integer assetno);
	List<Asset> findAll();
        void saveAsset(Asset asset);
        void update(Asset asset);
	//void save(Asset asset);
	//void update(Asset asset);
	void delete(Integer assetno);
        int AssetExists(String assetid);
        int setAction(Integer actionno);
        
    
}
