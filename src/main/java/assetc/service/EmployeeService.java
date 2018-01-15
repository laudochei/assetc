/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.service;

import assetc.model.Employee;
import java.util.List;


public interface EmployeeService {
    
        Employee findById(String locationid);
        List<Employee> findAllEmployee();
        int LocationExists(String locationid);
    
}
