/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.data;

import assetc.model.ProjectUser;
import java.util.List;

/**
 *
 * @author Laud.Ochei
 */

public interface ProjectUserDao {
    ProjectUser findByNo(Integer projectuserno);
    List<ProjectUser> findAll();
    void saveProjectUser(ProjectUser projectuser);
    void update(ProjectUser projectuser);
    void delete(Integer projectuserno);
    int ProjectUserIDExists(String projectuserid);
    
}
