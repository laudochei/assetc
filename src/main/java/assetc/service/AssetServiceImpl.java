/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import assetc.data.AssetDao;
import assetc.model.Asset;
import assetc.model.Task;

@Service("assetService")
public class AssetServiceImpl implements AssetService {

	AssetDao assetDao;

	@Autowired
	public void setAssetDao(AssetDao assetDao) {
		this.assetDao = assetDao;
	}

	@Override
	public Asset findByNo(Integer assetno) {
		return assetDao.findByNo(assetno);
	}

	@Override
	public List<Asset> findAllAsset() {
		return assetDao.findAll();
	}

	
        @Override
	public void saveAsset(Asset asset) {
            assetDao.saveAsset(asset);
	}
        
        
        @Override
	public void updateAsset(Asset asset) {
            assetDao.update(asset);
	}
        
        
        @Override
        public int AssetExists(String locationid) {
            return assetDao.AssetExists(locationid);
        }
        
        
        @Override
        public void delete(Integer assetno) {    
            assetDao.delete(assetno);
        }
        
        @Override
        public int setAction(Integer actionno) {
            return assetDao.setAction(actionno);
        }
}