/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.service;

import assetc.model.ProjectUser;
import java.util.List;

/**
 *
 * @author Laud.Ochei
 */
public interface ProjectUserService {
    ProjectUser findByNo(Integer projectuserno);
    List<ProjectUser> findAllProject();
    void saveProjectUser(ProjectUser projectuser);
    void updateProjectUser(ProjectUser projectuser);
    void delete(Integer projectuserno);
    int ProjectUserIDExists(String projectuserid);
}
