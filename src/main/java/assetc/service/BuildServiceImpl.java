/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.service;

import assetc.data.BuildDao;
import assetc.model.Build;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Laud.Ochei
 */

    
@Service("buildService")
public class BuildServiceImpl implements BuildService {
    
    
    
    BuildDao buildDao;

	@Autowired
	public void setBuildDao(BuildDao buildDao) {
		this.buildDao = buildDao;
	}

	@Override
	public Build findByNo(Integer buildno) {
		return buildDao.findByNo(buildno);
	}
    
}
