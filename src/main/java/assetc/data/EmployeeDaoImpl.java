/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.data;


import assetc.model.Asset;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import assetc.model.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public Employee findById(String locationid) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("locationid", locationid);
		String sql = "SELECT * FROM employees WHERE locationid=:locationid";
		Employee result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, params, new EmployeeMapper());
		} catch (EmptyResultDataAccessException e) {
			// do nothing, return null
		}
		return result;
	}

        
        
        @Override
	public List<Employee> findAll() {

		String sql = "SELECT * FROM employees";
		List<Employee> result = namedParameterJdbcTemplate.query(sql, new EmployeeDaoImpl.EmployeeMapper());
		return result;
	}
	
        
        
        
        @Override
        public int LocationExists(String locationid) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("locationid", locationid);
            String sql = "SELECT count(*) FROM employee WHERE locationid = :locationid";
            int count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
            return count;
        }
        
        
        

	private SqlParameterSource getSqlParameterByModel(Employee employee) {
            
		// Unable to handle List<String> or Array
		// BeanPropertySqlParameterSource

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
                
                //paramSource.addValue("locationno", location.getLocationno());
		paramSource.addValue("locationid", employee.getLocationid());
                paramSource.addValue("parentname", employee.getParentname());
                paramSource.addValue("description", employee.getDescription());
                paramSource.addValue("longdescription", employee.getLongdescription());
                paramSource.addValue("parentcraft", employee.getParentcraft());
                paramSource.addValue("craft", employee.getCraft());
                paramSource.addValue("equipmenttype", employee.getEquipmenttype());
                paramSource.addValue("failurecode", employee.getFailurecode());
                paramSource.addValue("systemstatus", employee.getSystemstatus());
                paramSource.addValue("userstatus", employee.getUserstatus());
                paramSource.addValue("criticality", employee.getCriticality());
                paramSource.addValue("co", employee.getCo());
                paramSource.addValue("sud", employee.getSud());
                paramSource.addValue("planningplant", employee.getPlanningplant());
                paramSource.addValue("maintenanceplant", employee.getMaintenanceplant());
                paramSource.addValue("physicallocation", employee.getPhysicallocation());
                paramSource.addValue("manufacturer", employee.getManufacturer());
                paramSource.addValue("partnum", employee.getPartnum());
                paramSource.addValue("modelnum", employee.getModelnum());
                paramSource.addValue("serialnum", employee.getSerialnum());
                paramSource.addValue("customfield", employee.getCustomfield());

		return paramSource;
	}

        
        
        class EmployeeMapper implements RowMapper<Employee> {
                public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
                  Employee employee = new Employee();
                  
                        //employee.setLocationno(rs.getInt("locationno"));
                        employee.setLocationid(rs.getString("locationid"));
                        employee.setParentname(rs.getString("parentname"));
                        employee.setDescription(rs.getString("description"));
                        employee.setLongdescription(rs.getString("longdescription"));
                        employee.setParentcraft(rs.getString("parentcraft"));
                        employee.setCraft(rs.getString("craft"));
                        employee.setEquipmenttype(rs.getString("equipmenttype"));
                        employee.setFailurecode(rs.getString("failurecode"));
                        employee.setSystemstatus(rs.getString("systemstatus"));
                        employee.setUserstatus(rs.getString("userstatus"));
                        employee.setCriticality(rs.getString("criticality"));
                        employee.setCo(rs.getDate("co"));
                        employee.setSud(rs.getDate("sud"));
                        employee.setPlanningplant(rs.getString("planningplant"));
                        employee.setMaintenanceplant(rs.getString("maintenanceplant"));
                        employee.setPhysicallocation(rs.getString("physicallocation"));
                        employee.setManufacturer(rs.getString("manufacturer"));
                        employee.setPartnum(rs.getString("partnum"));
                        employee.setModelnum(rs.getString("modelnum"));
                        employee.setSerialnum(rs.getString("serialnum"));
                        employee.setCustomfield(rs.getString("customfield"));


                  return employee;
                }
      }
 	
}