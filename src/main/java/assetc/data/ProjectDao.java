/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.data;

import assetc.model.Project;
import java.util.List;

/**
 *
 * @author Laud.Ochei
 */
public interface ProjectDao {
    Project findByNo(Integer projectno);
    List<Project> findAll();
    void saveProject(Project project);
    void update(Project project);
    void delete(Integer projectno);
    int ProjectIDExists(String projectid);
    int ProjectNameExists(String projectname);
    int checkForeignKey(String projectid);
    
}
