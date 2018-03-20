/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.service;

import assetc.model.Project;
import java.util.List;

/**
 *
 * @author Laud.Ochei
 */
public interface ProjectService {
    Project findByNo(Integer projectno);
    List<Project> findAllProject();
    void saveProject(Project project);
    void updateProject(Project project);
    void delete(Integer projectno);
    int ProjectIDExists(String projectid);
    int ProjectNameExists(String projectname);
    int checkForeignKey(String projectid);
    
}
