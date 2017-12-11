/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.data;

import assetc.model.Db;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


@Repository
public class DBDaoImpl implements DBDao {

	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	

	@Override
	public List<Db> selectrecord() {

		//String sql = "SELECT * FROM users";
                String sql = "SELECT * FROM ticks";
		List<Db> result = namedParameterJdbcTemplate.query(sql, new DbMapper());

		return result;

	}

	
        @Override
	public void createtb(Db db) {

		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		String sql = "INSERT INTO TICKS(USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ADDRESS, PHONE, ENABLED) "
				+ "VALUES ( :username, :password, :firstname, :lastname, :email, :address, :phone, :enabled)";

		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(db), keyHolder);
                db.setActionid(keyHolder.getKey().intValue());
		
	}
        
      

	private SqlParameterSource getSqlParameterByModel(Db db) {
            
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("actionid", db.getActionid());
		paramSource.addValue("display", db.getDisplay());
                paramSource.addValue("add", db.getAdd());
                paramSource.addValue("update", db.getUpdate());
                paramSource.addValue("delete", db.getDelete());
                

		return paramSource;
	}
        


	private static final class DbMapper implements RowMapper<Db> {

		public Db mapRow(ResultSet rs, int rowNum) throws SQLException {
			Db db = new Db();
                        db.setActionid(rs.getInt("actionid"));
                        db.setDisplay(rs.getString("display"));
                        db.setAdd(rs.getString("add"));
                        db.setUpdate(rs.getString("update"));
                        db.setDelete(rs.getString("delete"));
                       
                        return db;
        
		}
	}

	
}