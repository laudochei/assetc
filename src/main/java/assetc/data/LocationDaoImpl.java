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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

@Repository
public class LocationDaoImpl implements LocationDao {

	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
        
        
        DataSource dataSource;
        @Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
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
	public Location findById(String locationid) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("locationid", locationid);
		String sql = "SELECT * FROM location WHERE locationid=:locationid";
		Location result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, params, new LocationMapper());
		} catch (EmptyResultDataAccessException e) {
			// do nothing, return null
		}
                return result;
           
	}
        
        
        // find the parent node
        @Override
	public Location findParentNode() {

		Map<String, Object> params = new HashMap<String, Object>();
                String parentname = "0";
		params.put("parentname", parentname);
                String sql = "select * from location where parentname='" + parentname + "'" ;
                //String sql = "select * from location";
		//String sql = "SELECT * FROM location WHERE parentname=:parentname";  
                //String managerId = locationid == null ? "0" : locationid;
                //String sql = "SELECT e.locationid, e.description, e.longdescription, e.parentname As parentname, (SELECT COUNT(*) FROM location WHERE parentname = e.locationid) AS DirectReports FROM location e WHERE e.parentname = '0'"; 
            
		Location result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, params, new LocationMapper());
		} catch (EmptyResultDataAccessException e) {
			// do nothing, return null
		}
                return result;
           
	}
        
        
        // this function returns all the children of a particular node
        @Override
	public List<Location> findAllChild(String locationid) {
            String sql = "select * from location where parentname='" + locationid + "'" ;
            List<Location> result = namedParameterJdbcTemplate.query(sql, new LocationMapper());
            return result;
        }
        
        
        
        // this function returns hierarchical data
        @Override
	public List<Location> findChildrenofNode(String locationid) {
            Map<String, Object> params = new HashMap<String, Object>();
            System.out.println("Check this: " + locationid);
            String managerId = "";
            if (locationid.equals("20")){
                managerId = "0";
            }
            else{
                managerId = locationid;
            }
                
            //String managerId = locationid == "20" ? "0" : locationid;
            System.out.println("managerid: " + managerId);
            String sql = ""; //"SELECT e.locationid, e.description, e.longdescription, e.parentname As parentname, (SELECT COUNT(*) FROM location WHERE parentname = e.locationid) AS DirectReports FROM location e "; 
            
//            if (managerId=="0") {
//                // select where employees reportsto is null
//                sql += "WHERE e.parentname = '0'";
//                //stmt = _conn.prepareStatement(query);
//            }else{
//                // select where the reportsto is equal to the employeeId parameter
//                sql += "WHERE e.parentname = ?" ;
//                //stmt = _conn.prepareStatement(query);
//                //stmt.setString(1, managerId);
//                //params.put("locationid", managerId);
//            }


            if (managerId=="0") {
                //sql += "WHERE e.parentname = '0'";
                sql = "SELECT * from location where parentname = '0'"; //e.locationid, e.description, e.longdescription, e.parentname As parentname, (SELECT COUNT(*) FROM assetc.location WHERE parentname = e.locationid) AS DirectReports FROM assetc.location e WHERE e.parentname = '0'";
            }else{
                sql = "SELECT * from location where parentname = '" + managerId + "'" ;
            }
            
            
                        
           
            //String sql = "select * from location where parentname='" + locationid + "'" ;
            List<Location> result = namedParameterJdbcTemplate.query(sql, new LocationMapper());
            return result;
        }
        
        
        
	@Override
	public List<Location> findAll() {
                //String sql = "SELECT CONCAT( REPEAT(' ', COUNT(parent.locationid) - 1), node.locationid) AS name FROM location AS node, location AS parent WHERE node.lft BETWEEN parent.lft AND parent.rgt GROUP BY node.locationid ORDER BY node.lft";
                //String sql = "select * FROM location order by locationno";
		String sql = "select e.*, (SELECT COUNT(*) FROM location WHERE parentname = e.locationid) AS DirectReports from location As e";
		List<Location> result = namedParameterJdbcTemplate.query(sql, new LocationMapper());
                
		return result;
                
                
                
                                
                /*
                String query = "SELECT e.locationno, e.locationid, e.parentname, e.description, e.longdescription, e.parentcraft, e.craft, e.equipmenttype, e.failurecode, e.systemstatus, e.userstatus, e.criticality, e.co, e.sud, e.planningplant, e.maintenanceplant, e.physicallocation, e.manufacturer, e.partnum, e.modelnum, e.serialnum, e.customfield, (SELECT COUNT(*) FROM location WHERE parentname = e.locationid) AS DirectReports FROM location e";                      
                                 
                
                //String jsonstring = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
                List<Location> locations = new ArrayList<Location>();
		try{
                        //con = DriverManager.getConnection(jdbcUrl, username, password);
                        con = dataSource.getConnection();
			ps = con.prepareStatement(query);
                        rs = ps.executeQuery();
			while(rs.next()){
                                //System.out.println(rs.getString(1));
                                //jsonstring = rs.getString(1);
                                Location location = new Location();
				
				// select fields out of the database and set them on the class
                                location.setLocationno(rs.getInt("locationno"));
				location.setLocationid(rs.getString("locationid"));
				location.setDescription(rs.getString("description"));
				location.setLongdescription(rs.getString("longdescription"));
				location.setParentname(rs.getString("parentname"));
                                location.setParentcraft(rs.getString("parentcraft"));
                                location.setCraft(rs.getString("craft"));
                                location.setEquipmenttype(rs.getString("equipmenttype"));
                                location.setFailurecode(rs.getString("failurecode"));
                                location.setSystemstatus(rs.getString("systemstatus"));
                                location.setUserstatus(rs.getString("userstatus"));
                                location.setCriticality(rs.getString("criticality"));
                                location.setPlanningplant(rs.getString("planningplant"));
                                location.setMaintenanceplant(rs.getString("maintenanceplant"));
                                location.setPhysicallocation(rs.getString("physicallocation"));
                                location.setManufacturer(rs.getString("manufacturer"));
                                location.setPartnum(rs.getString("partnum"));
                                location.setModelnum(rs.getString("modelnum"));
                                location.setSerialnum(rs.getString("serialnum"));
                                location.setCustomfield(rs.getString("customfield"));
                                location.setHasChildren(rs.getInt("DirectReports") > 0); 
				//location.setFullDescription();
				
				// add the class to the list
				locations.add(location);
			}	
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return locations; //genres;
                */

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
	public Location updateSingleLocation(Integer locationno, Location location) {
            
            String sql = "UPDATE LOCATION SET LOCATIONNO=:locationno, PARENTNAME=:parentname, DESCRIPTION=:description, LONGDESCRIPTION=:longdescription, PARENTCRAFT=:parentcraft, CRAFT=:craft, EQUIPMENTTYPE=:equipmenttype, FAILURECODE=:failurecode, SYSTEMSTATUS=:systemstatus, USERSTATUS=:userstatus, CRITICALITY=:criticality, CO=:co, SUD=:sud, PLANNINGPLANT=:planningplant, MAINTENANCEPLANT=:maintenanceplant, PHYSICALLOCATION=:physicallocation, MANUFACTURER=:manufacturer, PARTNUM=:partnum, MODELNUM=:modelnum, SERIALNUM=:serialnum, CUSTOMFIELD=:customfield WHERE locationid=:locationid";
            namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(location));
            
            return null;
	}
        
        
        @Override
	public void dragdrop(Location location, String locationid, String parentname) {
             String sql = "UPDATE LOCATION SET LOCATIONNO=:locationno, PARENTNAME= parentname WHERE locationid=locationid";
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
                
//                paramSource.addValue("lft", location.getLft());
//                paramSource.addValue("rgt", location.getRgt());
//                paramSource.addValue("depth", location.getDepth());
                   
                
                
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
//                        location.setLft(rs.getInt("lft"));
//                        location.setRgt(rs.getInt("lft"));
//                        location.setDepth(rs.getInt("depth"));
//                        
                  
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
                        location.setHasChildren(rs.getInt("DirectReports") > 0); 


                  return location;
                }

      }
        
   
	
}