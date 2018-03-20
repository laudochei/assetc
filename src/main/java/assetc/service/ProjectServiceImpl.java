/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.service;

import assetc.data.ProjectDao;
import assetc.model.Project;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Laud.Ochei
 */
 
@Service("ProjectService")
public class ProjectServiceImpl implements ProjectService{
        ProjectDao projectDao;

	@Autowired
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	@Override
	public Project findByNo(Integer projectno) {
		return projectDao.findByNo(projectno);
	}
        
        @Override
	public List<Project> findAllProject() {
		return projectDao.findAll();
	}
    
        
        @Override
	public void saveProject(Project project) {
            projectDao.saveProject(project);
	}
       
        @Override
        public int ProjectIDExists(String projectid) {
            return projectDao.ProjectIDExists(projectid);
        }
        
        
        @Override
        public int ProjectNameExists(String projectname) {
            return projectDao.ProjectNameExists(projectname);
        }
        
        @Override
	public void updateProject(Project project) {
            projectDao.update(project);
	}
        
        
        @Override
        public void delete(Integer projectno) {    
            projectDao.delete(projectno);
        }
        
         @Override
        public int checkForeignKey(String projectid) {
            return projectDao.checkForeignKey(projectid);
        }
        
        
        
        
}
