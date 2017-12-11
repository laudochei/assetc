package assetc.config;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class DataConfig {
    
		/*
        @Bean
        public DataSource getDataSource1() {
            DriverManagerDataSource db = new DriverManagerDataSource();
            db.setDriverClassName("com.mysql.jdbc.Driver");
            db.setUrl("mysql://b4778f9d520c68:132d6c92@us-cdbr-iron-east-05.cleardb.net/heroku_7ac45a05bab0085?reconnect=true");
            db.setUsername("root");
            db.setPassword("welcome123");
            return db;
        }
       */


	@Bean
        public DataSource getDataSource1() {
            DriverManagerDataSource db = new DriverManagerDataSource();
            db.setDriverClassName("com.mysql.jdbc.Driver");
            db.setUrl("jdbc:mysql://us-cdbr-iron-east-05.cleardb.net:3306/heroku_7ac45a05bab0085?reconnect=true");
            db.setUsername("b4778f9d520c68");
            db.setPassword("132d6c92");
            return db;
        }
	




	/*
	@Bean
    	public DataSource dataSource() throws URISyntaxException {
        	URI dbUri = new URI(System.getenv("CLEARDB_DATABASE_URL"));

        	String username = dbUri.getUserInfo().split(":")[0];
        	String password = dbUri.getUserInfo().split(":")[1];
        	String dbUrl = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath();

        	BasicDataSource basicDataSource = new BasicDataSource();
        	basicDataSource.setUrl(dbUrl);
        	basicDataSource.setUsername(username);
        	basicDataSource.setPassword(password);

        	return basicDataSource;
    	}

	*/





        
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
