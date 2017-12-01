/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.data;

import assetc.model.Build;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

    
@Repository
public class BuildDaoImpl implements BuildDao {
    
    
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public Build findByNo(Integer buildno) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("buildno", buildno);

		String sql = "SELECT * FROM build WHERE buildno=:buildno";

		Build result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, params, new BuildDaoImpl.BuildMapper());
		} catch (EmptyResultDataAccessException e) {
			// do nothing, return null
		}
                //Asset asset = new Asset();
                //System.out.println("Showed record! = " + assetno + " AssetID = " + asset.getAssetid() + " LocationID = " + asset.getLocationid());
		return result;

	}
        
        
        
        
        private static final class BuildMapper implements RowMapper<Build> {

		public Build mapRow(ResultSet rs, int rowNum) throws SQLException {
			Build build = new Build();
                        
                        build.setBuildno(rs.getInt("buildno"));
                        build.setBuildtype(rs.getString("buildtype"));
                        
                        return build;
        
		}
	}

    
}
