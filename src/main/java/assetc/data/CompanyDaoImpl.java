/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.data;

import assetc.model.Company;
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
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

@Repository
public class CompanyDaoImpl implements CompanyDao {

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
	public Company findByNo(Integer companyno) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("companyno", companyno);
                String sql = "select * from company WHERE companyno=:companyno";
		Company result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, params, new CompanyMapper());
		} catch (EmptyResultDataAccessException e) {
			// do nothing, return null
		}
		return result;
	}
        
        
        @Override
	public List<Company> findAll() {
		String sql = "SELECT * FROM company";
		List<Company> result = namedParameterJdbcTemplate.query(sql, new CompanyDaoImpl.CompanyMapper());
		return result;
	}
        
        
        @Override
        public int CompanyIDExists(String companyid) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("companyid", companyid); 
            String sql = "SELECT count(*) FROM company WHERE companyid = :companyid";
            int count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
            return count;
        }
        
        @Override
        public int CompanyNameExists(String companyname) {
            
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("companyname", companyname);
            String sql = "select count(*) from company where companyname LIKE '" + companyname + "%'";
            int count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
            return count;
        }
        
        
        
        @Override
	public void saveCompany(Company company) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "INSERT INTO COMPANY(COMPANYID, ORGID, COMPANYNAME, COMPANYDESC, CREATEDBY, CREATEDON) "
				+ "VALUES ( :companyid, :orgid, :companyname, :companydesc, :createdby, :createdon)";
		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(company), keyHolder);
                company.setCompanyno(keyHolder.getKey().intValue());
	}
        
        
        @Override
	public void update(Company company) {
            String sql = "UPDATE COMPANY SET COMPANYID=:companyid, ORGID=:orgid, COMPANYNAME=:companyname, COMPANYDESC=:companydesc, CREATEDBY=:createdby, CREATEDON=:createdon WHERE companyno=:companyno";
            namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(company));    
	}
        
        
        @Override
	public void delete(Integer companyno) {
		String sql = "DELETE FROM COMPANY WHERE companyno= :companyno";
		namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("companyno", companyno));
	}
        
        @Override
        public int checkForeignKey(String companyid) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("companyid", companyid);
            String sql = "SELECT count(*) FROM project WHERE companyid = :companyid";
            int count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
            return count;
        }
        
       
               
        private SqlParameterSource getSqlParameterByModel(Company company) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
                paramSource.addValue("companyno", company.getCompanyno());
                paramSource.addValue("companyid", company.getCompanyid());
                paramSource.addValue("orgid", company.getOrgid());
		paramSource.addValue("companyname", company.getCompanyname());
                paramSource.addValue("companydesc", company.getCompanydesc());
                paramSource.addValue("createdon", company.getCreatedon());
                paramSource.addValue("createdby", company.getCreatedby());
                
		return paramSource;
	}

        
        
	private static final class CompanyMapper implements RowMapper<Company> {
		public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
			Company company = new Company();
                        company.setCompanyno(rs.getInt("companyno"));
                        company.setCompanyid(rs.getString("companyid"));
                        company.setOrgid(rs.getString("orgid"));
                        company.setCompanyname(rs.getString("companyname"));
                        company.setCompanydesc(rs.getString("companydesc"));
                        company.setCreatedon(rs.getDate("createdon"));
                        company.setCreatedby(rs.getString("createdby"));
                        return company;
		}
	}   
}
