package assetc.data;


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

import assetc.model.Location;

@Repository
public class LocationDaoImpl implements LocationDao {

	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public Location findByNo(Integer locationno) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("locationno", locationno);
		String sql = "SELECT * FROM location WHERE locationno=:locationno";
		Location result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, params, new LocationMapper());
		} catch (EmptyResultDataAccessException e) {
			// do nothing, return null
		}
		return result;
	}

	@Override
	public List<Location> findAll() {

		String sql = "select * from location order by locationno";
		List<Location> result = namedParameterJdbcTemplate.query(sql, new LocationMapper());

		return result;

	}

        
        @Override
	public void deleteLocation(Integer locationno) {
		String sql = "DELETE FROM LOCATION WHERE locationno= :locationno";
		namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("locationno", locationno));
	}
        
	
        @Override
        public int LocationExists(String locationid) {
            
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("locationid", locationid);
        
            String sql = "SELECT count(*) FROM location WHERE locationid = :locationid";
            int count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
            return count;
        }
        
        
        
        @Override
	public void saveLocation(Location location) {

		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		String sql = "INSERT INTO LOCATION(locationid, CO, SUD, CRAFT, DESCRIPTION, LONGDESCRIPTION, PARENTNAME, PARENTCRAFT, EQUIPMENTTYPE, FAILURECODE, SYSTEMSTATUS, USERSTATUS, CRITICALITY, PLANNINGPLANT, MAINTENANCEPLANT, PHYSICALLOCATION, MANUFACTURER, PARTNUM, MODELNUM, SERIALNUM, CUSTOMFIELD) "
				+ "VALUES ( :locationid, :co, :sud, :craft, :description, :longdescription, :parentname, :parentcraft, :equipmenttype, :failurecode, :systemstatus, :userstatus, :criticality, :planningplant, :maintenanceplant, :physicallocation, :manufacturer, :partnum, :modelnum, :serialnum, :customfield)";

		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(location), keyHolder);
                location.setLocationno(keyHolder.getKey().intValue());
		
	}

       
               
        @Override
	public void updateLocation(Location location) {
            String sql = "UPDATE LOCATION SET LOCATIONNO=:locationno, PARENTNAME=:parentname, DESCRIPTION=:description, LONGDESCRIPTION=:longdescription, PARENTCRAFT=:parentcraft, CRAFT=:craft, EQUIPMENTTYPE=:equipmenttype, FAILURECODE=:failurecode, SYSTEMSTATUS=:systemstatus, USERSTATUS=:userstatus, CRITICALITY=:criticality, CO=:co, SUD=:sud, PLANNINGPLANT=:planningplant, MAINTENANCEPLANT=:maintenanceplant, PHYSICALLOCATION=:physicallocation, MANUFACTURER=:manufacturer, PARTNUM=:partnum, MODELNUM=:modelnum, SERIALNUM=:serialnum, CUSTOMFIELD=:customfield WHERE locationid=:locationid";
            namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(location));
            
	}
                
        
              
        
        @Override
        public int validateLocation(Location location) {
            
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("locationid", location.getLocationid());
            params.put("locationno", location.getLocationno());
            String sql = "select * from location where locationno='" + location.getLocationno() + "' and locationid='" + location.getLocationid() + "'" ;
            int count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
            return count;
        }
        
        
        /*
        @Override
        public Location validateLocation(Location loc) {
            String sql = "select * from location where locationno='" + loc.getLocationno() + "' and locationid='" + loc.getLocationid() + "'" ;
            List<Location> location = namedParameterJdbcTemplate.query(sql, new LocationMapper());
            return location.size() > 0 ? location.get(0) : null;  
        }
        */
        
        

	private SqlParameterSource getSqlParameterByModel(Location location) {
            
		// Unable to handle List<String> or Array
		// BeanPropertySqlParameterSource

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
                
                paramSource.addValue("locationno", location.getLocationno());
		paramSource.addValue("locationid", location.getLocationid());
                paramSource.addValue("parentname", location.getParentname());
                paramSource.addValue("description", location.getDescription());
                paramSource.addValue("longdescription", location.getLongdescription());
                paramSource.addValue("parentcraft", location.getParentcraft());
                paramSource.addValue("craft", location.getCraft());
                paramSource.addValue("equipmenttype", location.getEquipmenttype());
                paramSource.addValue("failurecode", location.getFailurecode());
                paramSource.addValue("systemstatus", location.getSystemstatus());
                paramSource.addValue("userstatus", location.getUserstatus());
                paramSource.addValue("criticality", location.getCriticality());
                paramSource.addValue("co", location.getCo());
                paramSource.addValue("sud", location.getSud());
                paramSource.addValue("planningplant", location.getPlanningplant());
                paramSource.addValue("maintenanceplant", location.getMaintenanceplant());
                paramSource.addValue("physicallocation", location.getPhysicallocation());
                paramSource.addValue("manufacturer", location.getManufacturer());
                paramSource.addValue("partnum", location.getPartnum());
                paramSource.addValue("modelnum", location.getModelnum());
                paramSource.addValue("serialnum", location.getSerialnum());
                paramSource.addValue("customfield", location.getCustomfield());

		return paramSource;
	}

        
        
        class LocationMapper implements RowMapper<Location> {
                public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
                  Location location = new Location();
                  
                        location.setLocationno(rs.getInt("locationno"));
                        location.setLocationid(rs.getString("locationid"));
                        location.setParentname(rs.getString("parentname"));
                        location.setDescription(rs.getString("description"));
                        location.setLongdescription(rs.getString("longdescription"));
                        location.setParentcraft(rs.getString("parentcraft"));
                        location.setCraft(rs.getString("craft"));
                        location.setEquipmenttype(rs.getString("equipmenttype"));
                        location.setFailurecode(rs.getString("failurecode"));
                        location.setSystemstatus(rs.getString("systemstatus"));
                        location.setUserstatus(rs.getString("userstatus"));
                        location.setCriticality(rs.getString("criticality"));
                        location.setCo(rs.getDate("co"));
                        location.setSud(rs.getDate("sud"));
                        location.setPlanningplant(rs.getString("planningplant"));
                        location.setMaintenanceplant(rs.getString("maintenanceplant"));
                        location.setPhysicallocation(rs.getString("physicallocation"));
                        location.setManufacturer(rs.getString("manufacturer"));
                        location.setPartnum(rs.getString("partnum"));
                        location.setModelnum(rs.getString("modelnum"));
                        location.setSerialnum(rs.getString("serialnum"));
                        location.setCustomfield(rs.getString("customfield"));


                  return location;
                }

      }
        
   
	
}