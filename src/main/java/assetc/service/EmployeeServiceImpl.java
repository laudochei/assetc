/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.service;



import assetc.data.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import assetc.model.Employee;
import java.util.List;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

	EmployeeDao employeeDao;

	@Autowired
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

        
        
	@Override
	public Employee findById(String locationid) {
		return employeeDao.findById(locationid);
	}

        @Override
	public List<Employee> findAllEmployee() {
		return employeeDao.findAll();
	}

	
	@Override
        public int LocationExists(String locationid) {
            return employeeDao.LocationExists(locationid);
        }
}