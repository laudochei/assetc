package assetc.config;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class DataConfig {
    
        @Bean
        public DataSource getDataSource1() {
            DriverManagerDataSource db = new DriverManagerDataSource();
            db.setDriverClassName("com.mysql.jdbc.Driver");
            db.setUrl("jdbc:mysql://localhost:3306/assetc");
            db.setUsername("root");
            db.setPassword("welcome123");
            return db;
        }
       
        
        /*
        @Bean
        public JdbcOperations jdbcTemplate(DataSource dataSource) {
          return new JdbcTemplate(dataSource);
        }
        */
        
        @Bean
        public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}
        
}
