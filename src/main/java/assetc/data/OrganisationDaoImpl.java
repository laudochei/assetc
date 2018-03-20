/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import assetc.model.Organisation;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

@Repository
public class OrganisationDaoImpl implements OrganisationDao {

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
	public Organisation findByNo(Integer orgno) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orgno", orgno);
                String sql = "select * from organisations WHERE orgno=:orgno";
		Organisation result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, params, new OrganisationMapper());
		} catch (EmptyResultDataAccessException e) {
			// do nothing, return null
		}
		return result;
	}
        
        
        @Override
	public List<Organisation> findAll() {
		String sql = "SELECT * FROM organisations";
		List<Organisation> result = namedParameterJdbcTemplate.query(sql, new OrganisationDaoImpl.OrganisationMapper());
		return result;
	}
        
        @Override
        public int OrganIDExists(String orgid) {
            
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("orgid", orgid);
                   
            String sql = "SELECT count(*) FROM organisations WHERE orgid = :orgid";
            int count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
            return count;
        }
        
        @Override
        public int OrganNameExists(String orgname) {
            
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("orgname", orgname);
            String sql = "select count(*) from organisations where orgname LIKE '" + orgname + "%'";
            int count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
            return count;
        }
        
        @Override
        public int checkForeignKey(String orgid) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("orgid", orgid);
            String sql = "SELECT count(*) FROM company WHERE orgid = :orgid";
            int count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
            return count;
        }
        
        
        @Override
	public void saveOrganisation(Organisation organisation) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "INSERT INTO ORGANISATIONS(ORGID, ORGNAME, ORGDESC) "
				+ "VALUES ( :orgid, :orgname, :orgdesc)";
		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(organisation), keyHolder);
                organisation.setOrgno(keyHolder.getKey().intValue());
	}
        
        
        @Override
	public void update(Organisation organisation) {
            String sql = "UPDATE ORGANISATIONS SET ORGID=:orgid, ORGNAME=:orgname, ORGDESC=:orgdesc WHERE orgno=:orgno";
            namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(organisation));    
	}
        
        
        @Override
	public void delete(Integer orgno) {
		String sql = "DELETE FROM ORGANISATIONS WHERE orgno= :orgno";
		namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("orgno", orgno));
	}
        
               
        private SqlParameterSource getSqlParameterByModel(Organisation organisation) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
                paramSource.addValue("orgno", organisation.getOrgno());
                paramSource.addValue("orgid", organisation.getOrgid());
		paramSource.addValue("orgname", organisation.getOrgname());
                paramSource.addValue("orgdesc", organisation.getOrgdesc());
		return paramSource;
	}

        
        
	private static final class OrganisationMapper implements RowMapper<Organisation> {

		public Organisation mapRow(ResultSet rs, int rowNum) throws SQLException {
			Organisation organisation = new Organisation();
                        organisation.setOrgno(rs.getInt("orgno"));
                        organisation.setOrgid(rs.getString("orgid"));
                        organisation.setOrgname(rs.getString("orgname"));
                        organisation.setOrgdesc(rs.getString("orgdesc"));
                        return organisation;
		}
	}   
}
