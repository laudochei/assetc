/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.data;


import assetc.model.Project;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

@Repository
public class ProjectDaoImpl implements ProjectDao {

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
	public Project findByNo(Integer projectno) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("projectno", projectno);
                String sql = "select * from project WHERE projectno=:projectno";
		Project result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, params, new ProjectDaoImpl.ProjectMapper());
		} catch (EmptyResultDataAccessException e) {
			// do nothing, return null
		}
		return result;
	}
        
        
        @Override
	public List<Project> findAll() {
		String sql = "SELECT * FROM project";
		List<Project> result = namedParameterJdbcTemplate.query(sql, new ProjectDaoImpl.ProjectMapper());
		return result;
	}
        
        
        
        @Override
        public int ProjectIDExists(String projectid) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("projectid", projectid); 
            String sql = "SELECT count(*) FROM project WHERE projectid = :projectid";
            int count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
            return count;
        }
        
        @Override
        public int ProjectNameExists(String projectname) {
            
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("projectname", projectname);
            String sql = "select count(*) from project where projectname LIKE '" + projectname + "%'";
            int count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
            return count;
        }
        
        
        
        @Override
	public void saveProject(Project project) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
                String sql = "INSERT INTO PROJECT(PROJECTID, COMPANYID, PROJECTNAME, PROJECTDESC, CREATIONDATE, CLOSUREDATE, STARTDATE, ENDDATE, FINANCECODE, PROJECTCOLLECTION, DAV, PAV, HIERARCHY, CRITICALITY, LEVELLING, BOM, IN_OUT) "
				+ "VALUES ( :projectid, :companyid, :projectname, :projectdesc, :creationdate, :closuredate, :startdate, :enddate, :financecode, :projectcollection, :dav, :pav, :hierarchy, :criticality, :levelling, :bom, :in_out)";
                namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(project), keyHolder);
                project.setProjectno(keyHolder.getKey().intValue());
	}
        
        
        @Override
	public void update(Project project) {
            String sql = "update project set projectid=:projectid, companyid=:companyid, projectname=:projectname, projectdesc=:projectdesc, creationdate=:creationdate, closuredate=:closuredate, startdate=:startdate, enddate=:enddate, financecode=:financecode, projectcollection=:projectcollection, dav=:dav, pav=:pav, hierarchy=:hierarchy, criticality=:criticality, levelling=:levelling, bom=:bom, in_out=:in_out where projectno=:projectno";
            namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(project));    
	}
        
        
        @Override
	public void delete(Integer projectno) {
		String sql = "DELETE FROM project WHERE projectno= :projectno";
		namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("projectno", projectno));
	}
        
        @Override
        public int checkForeignKey(String projectid) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("projectid", projectid);
            String sql = "SELECT count(*) FROM projectusers WHERE projectid = :projectid";
            int count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
            return count;
        }
 
        
   private SqlParameterSource getSqlParameterByModel(Project project) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
                paramSource.addValue("projectno", project.getProjectno());
                paramSource.addValue("projectid", project.getProjectid());
                paramSource.addValue("companyid", project.getCompanyid());
		paramSource.addValue("projectname", project.getProjectname());
                paramSource.addValue("projectdesc", project.getProjectdesc());
                paramSource.addValue("creationdate", project.getCreationdate());
                paramSource.addValue("closuredate", project.getClosuredate());
                paramSource.addValue("startdate", project.getStartdate());
                paramSource.addValue("enddate", project.getEnddate());
                paramSource.addValue("financecode", project.getFinancecode());
                paramSource.addValue("projectcollection", project.isProjectcollection());
                paramSource.addValue("dav", project.isDav());
                paramSource.addValue("pav", project.isPav());
                paramSource.addValue("hierarchy", project.isHierarchy());
                paramSource.addValue("criticality", project.isCriticality());
                paramSource.addValue("levelling", project.isLevelling());
                paramSource.addValue("bom", project.isBom());
                paramSource.addValue("in_out", project.isIn_out());
                
		return paramSource;
	}

        
        
	private static final class ProjectMapper implements RowMapper<Project> {
		public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
			Project project = new Project();
                        project.setProjectno(rs.getInt("projectno"));
                        project.setProjectid(rs.getString("projectid"));
                        project.setCompanyid(rs.getString("companyid"));
                        project.setProjectname(rs.getString("projectname"));
                        project.setProjectdesc(rs.getString("projectdesc"));
                        project.setCreationdate(rs.getDate("creationdate"));
                        project.setClosuredate(rs.getDate("closuredate"));
                        project.setStartdate(rs.getDate("startdate"));
                        project.setEnddate(rs.getDate("enddate"));
                        project.setFinancecode(rs.getString("financecode"));
                        project.setProjectcollection(rs.getBoolean("projectcollection"));
                        project.setDav(rs.getBoolean("dav"));
                        project.setPav(rs.getBoolean("pav"));
                        project.setHierarchy(rs.getBoolean("hierarchy"));
                        project.setCriticality(rs.getBoolean("criticality"));
                        project.setLevelling(rs.getBoolean("levelling"));
                        project.setBom(rs.getBoolean("bom"));
                        project.setIn_out(rs.getBoolean("in_out"));
                        return project;
		}
	}        


}