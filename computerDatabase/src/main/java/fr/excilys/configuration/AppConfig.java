package fr.excilys.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@ComponentScan(basePackages = "fr.excilys.dao, fr.excilys.services, fr.excilys.mapper, fr.excilys.servlet, fr.excilys.configuration")
@PropertySource("classpath:database.properties")

public class AppConfig implements WebApplicationInitializer{

	
	@Autowired
	Environment environment;

	private final String URL = "database.url";
	private final String USER = "database.dbuser";
	private final String DRIVER = "database.driver";
	private final String PASSWORD = "database.dbpassword";

	@Bean
	DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setUrl(environment.getProperty(URL));
		driverManagerDataSource.setUsername(environment.getProperty(USER));
		driverManagerDataSource.setPassword(environment.getProperty(PASSWORD));
		driverManagerDataSource.setDriverClassName(environment.getProperty(DRIVER));
		return driverManagerDataSource;
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		System.out.println("Starting . . .");
		
		AnnotationConfigWebApplicationContext webCtx = new AnnotationConfigWebApplicationContext();
		webCtx.register(AppConfig.class);
		webCtx.setServletContext(servletContext);
		
        ServletRegistration.Dynamic servlet = servletContext.addServlet("dashboard", new DispatcherServlet(webCtx));
	    servlet.setLoadOnStartup(1);
	    servlet.addMapping("/");
	}
}
