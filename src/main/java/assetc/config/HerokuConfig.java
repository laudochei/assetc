/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.config;



import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class HerokuConfig {
    
        @Bean
        public DataSource getDataSource1() {
            DriverManagerDataSource db = new DriverManagerDataSource();
            db.setDriverClassName("com.mysql.jdbc.Driver");
            db.setUrl("jdbc:mysql://us-cdbr-iron-east-05.cleardb.net:3306/heroku_7ac45a05bab0085");
            db.setUsername("b4778f9d520c68");
            db.setPassword("132d6c92");
            return db;
        }
       
        @Bean
        public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}
        
}
