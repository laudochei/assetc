/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.data;

import assetc.model.ProjectUser;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
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

/**
 *
 * @author Laud.Ochei
 */




@Repository
public class ProjectUserDaoImpl implements ProjectUserDao {

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
	public ProjectUser findByNo(Integer projectuserno) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("projectuserno", projectuserno);
                String sql = "select * from projectuser WHERE projectuserno=:projectuserno";
		ProjectUser result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, params, new ProjectUserDaoImpl.ProjectUserMapper());
		} catch (EmptyResultDataAccessException e) {
			// do nothing, return null
		}
		return result;
	}
        
        
        @Override
	public List<ProjectUser> findAll() {
		String sql = "SELECT * FROM projectuser";
		List<ProjectUser> result = namedParameterJdbcTemplate.query(sql, new ProjectUserDaoImpl.ProjectUserMapper());
		return result;
	}
        
        
        
        @Override
        public int ProjectUserIDExists(String projectuserid) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("projectuserid", projectuserid); 
            String sql = "SELECT count(*) FROM projectuser WHERE projectuserid = :projectuserid";
            int count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
            return count;
        }
        
               
        
        
        @Override
	public void saveProjectUser(ProjectUser projectuser) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "INSERT INTO PROJECTUSER(PROJECTUSERID, PROJECTID, PROJECTUSERDESC, USERID) "
				+ "VALUES ( :projectuserid, :projectid, :projectuserdesc, :userid)";
		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(projectuser), keyHolder);
                projectuser.setProjectuserno(keyHolder.getKey().intValue());
	}
        
        
        @Override
	public void update(ProjectUser projectuser) {
            String sql = "UPDATE PROJECTUSER SET PROJECTUSERID=:projectuserid, PROJECTID=:projectid, PROJECTUSERDESC=:projectuserdesc, USERID=:userid WHERE projectuserno=:projectuserno";
            namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(projectuser));    
	}
        
        
        @Override
	public void delete(Integer projectuserno) {
		String sql = "DELETE FROM PROJECTUSER WHERE projectuserno= :projectuserno";
		namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("projectuserno", projectuserno));
	}
        
        
        


        private SqlParameterSource getSqlParameterByModel(ProjectUser projectuser) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
                paramSource.addValue("projectuserno", projectuser.getProjectuserno());
                paramSource.addValue("projectuserid", projectuser.getProjectuserid());
                paramSource.addValue("projectid", projectuser.getProjectid());
		paramSource.addValue("projectuserdesc", projectuser.getProjectuserdesc());
                paramSource.addValue("userid", projectuser.getUserid());
                
		return paramSource;
	}

        
        
	private static final class ProjectUserMapper implements RowMapper<ProjectUser> {
		public ProjectUser mapRow(ResultSet rs, int rowNum) throws SQLException {
			ProjectUser projectuser = new ProjectUser();
                        projectuser.setProjectuserno(rs.getInt("projectuserno"));
                        projectuser.setProjectuserid(rs.getString("projectuserid"));
                        projectuser.setProjectid(rs.getString("projectid"));
                        projectuser.setProjectuserdesc(rs.getString("projectuserdesc"));
                        projectuser.setUserid(rs.getInt("userid"));
                        return projectuser;
		}
	}        


}