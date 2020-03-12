package Configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan("com.baeldung.jdbc")
@PropertySource("classpath:db.properties")
public class SpringJdbcConfig {
	
	@Value("${database.url}")
	private String mysqlUrl;

	@Value("${database.driver}")
	private String driver;
	
	@Value("${database.username}")
	private String username;

	@Value("${database.password}")
	private String password;
	
    @Bean
    public DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(mysqlUrl);
        dataSource.setUrl(driver);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        
        return dataSource;
    }
}
