/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.service;

import assetc.data.ProjectUserDao;
import assetc.model.ProjectUser;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Laud.Ochei
 */




@Service("ProjectUserService")
public class ProjectUserServiceImpl implements ProjectUserService{
        ProjectUserDao projectuserDao;

	@Autowired
	public void setProjecUserDao(ProjectUserDao projectuserDao) {
		this.projectuserDao = projectuserDao;
	}

	@Override
	public ProjectUser findByNo(Integer projectuserno) {
		return projectuserDao.findByNo(projectuserno);
	}
        
        @Override
	public List<ProjectUser> findAllProject() {
		return projectuserDao.findAll();
	}
        
        
        @Override
	public void saveProjectUser(ProjectUser projectuser) {
            projectuserDao.saveProjectUser(projectuser);
	}
       
        @Override
        public int ProjectUserIDExists(String projectuserid) {
            return projectuserDao.ProjectUserIDExists(projectuserid);
        }
        
       
        @Override
	public void updateProjectUser(ProjectUser projectuser) {
            projectuserDao.update(projectuser);
	}
        
        
        @Override
        public void delete(Integer projectuserno) {    
            projectuserDao.delete(projectuserno);
        }
        
         
}