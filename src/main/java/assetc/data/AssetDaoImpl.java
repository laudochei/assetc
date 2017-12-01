/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import org.springframework.stereotype.Repository;

import assetc.model.Asset;
import assetc.model.Task;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

@Repository
public class AssetDaoImpl implements AssetDao {

	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public Asset findByNo(Integer assetno) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("assetno", assetno);

		String sql = "SELECT * FROM asset WHERE assetno=:assetno";

		Asset result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, params, new AssetMapper());
		} catch (EmptyResultDataAccessException e) {
			// do nothing, return null
		}
                //Asset asset = new Asset();
                //System.out.println("Showed record! = " + assetno + " AssetID = " + asset.getAssetid() + " LocationID = " + asset.getLocationid());
		return result;

	}

	@Override
	public List<Asset> findAll() {

		String sql = "SELECT * FROM asset";
		List<Asset> result = namedParameterJdbcTemplate.query(sql, new AssetMapper());
		return result;
	}

	@Override
	public void saveAsset(Asset asset) {

		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		String sql = "INSERT INTO ASSET(ASSETID, LOCATIONID, DESCRIPTION, LONGDESCRIPTION, EQUIPMENTTYPE, FAILURECODE, PHYSICALLOCATION, MANUFACTURER, PARTNUM, MODELNUM, SERIALNUM, CUSTOMFIELD) "
				+ "VALUES ( :assetid, :locationid, :description, :longdescription, :equipmenttype, :failurecode, :physicallocation, :manufacturer, :partnum, :modelnum, :serialnum, :customfield)";

		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(asset), keyHolder);
                asset.setAssetno(keyHolder.getKey().intValue());
	}
        
             
        
        @Override
	public void update(Asset asset) {
            String sql = "UPDATE ASSET SET ASSETID=:assetid, LOCATIONID=:locationid, DESCRIPTION=:description, LONGDESCRIPTION=:longdescription, EQUIPMENTTYPE=:equipmenttype, FAILURECODE=:failurecode, PHYSICALLOCATION=:physicallocation, MANUFACTURER=:manufacturer, PARTNUM=:partnum, MODELNUM=:modelnum, SERIALNUM=:serialnum, CUSTOMFIELD=:customfield WHERE assetno=:assetno";
            namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(asset));
            
	}
        
        
        
        @Override
        public int AssetExists(String assetid) {
            
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("assetid", assetid);
        
            String sql = "SELECT count(*) FROM asset WHERE assetid = :assetid";
            int count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
            return count;
        }
        
        
        @Override
	public void delete(Integer assetno) {
		String sql = "DELETE FROM ASSET WHERE assetno= :assetno";
		namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("assetno", assetno));
	}
        
        
        @Override
        public int setAction(Integer actionno) {
            Task task = new Task();                
            int op;
            if (actionno == 1) {
               task.setCreate(1); // create 
                op = 1;
            } else if (actionno == 2) {
               task.setUpdate(2); // read
               op = 2;
            } else if (actionno == 3) {
               task.setUpdate(3); // update
               op = 3;
            } else {
               task.setUpdate(4); //delete
               op = 4;
            }
            return op;
        }
        
        
        
        
	private SqlParameterSource getSqlParameterByModel(Asset asset) {
            
		// Unable to handle List<String> or Array
		// BeanPropertySqlParameterSource

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
                
                paramSource.addValue("assetno", asset.getAssetno());
                paramSource.addValue("assetid", asset.getAssetid());
		paramSource.addValue("locationid", asset.getLocationid());
                paramSource.addValue("description", asset.getDescription());
                paramSource.addValue("longdescription", asset.getLongdescription());
		paramSource.addValue("equipmenttype", asset.getEquipmenttype());
                paramSource.addValue("failurecode", asset.getFailurecode());
                paramSource.addValue("manufacturer", asset.getManufacturer());
                paramSource.addValue("partnum", asset.getPartnum());
                paramSource.addValue("modelnum", asset.getModelnum());
                paramSource.addValue("serialnum", asset.getSerialnum());
                paramSource.addValue("physicallocation", asset.getPhysicallocation());
                paramSource.addValue("customfield", asset.getCustomfield());
                
		return paramSource;
	}

	private static final class AssetMapper implements RowMapper<Asset> {

		public Asset mapRow(ResultSet rs, int rowNum) throws SQLException {
			Asset asset = new Asset();
                        
                        asset.setAssetno(rs.getInt("assetno"));
                        asset.setAssetid(rs.getString("assetid"));
                        asset.setLocationid(rs.getString("locationid"));
                        asset.setDescription(rs.getString("description"));
                        asset.setLongdescription(rs.getString("longdescription"));
                        asset.setEquipmenttype(rs.getString("equipmenttype"));
                        asset.setFailurecode(rs.getString("failurecode"));
                        asset.setManufacturer(rs.getString("manufacturer"));
                        asset.setPartnum(rs.getString("partnum"));
                        asset.setModelnum(rs.getString("modelnum"));
                        asset.setSerialnum(rs.getString("serialnum"));
                        asset.setPhysicallocation(rs.getString("physicallocation"));
                        asset.setCustomfield(rs.getString("customfield"));
                        
                        return asset;
        
		}
	}

	
}