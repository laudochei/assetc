/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.data;


import assetc.model.Employee;
import java.util.List;

public interface EmployeeDao {
    Employee findById(String locationid);
    List<Employee> findAll();
    int LocationExists(String locationid);

    
//        Asset findByNo(Integer assetno);
    
//        void saveAsset(Asset asset);
//        void update(Asset asset);
//	//void save(Asset asset);
//	//void update(Asset asset);
//	void delete(Integer assetno);
//        int AssetExists(String assetid);
//        int setAction(Integer actionno);
        
    
}
