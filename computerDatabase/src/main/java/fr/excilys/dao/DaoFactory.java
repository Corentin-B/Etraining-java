package fr.excilys.dao;

import java	.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Component
public final class DaoFactory {

	private static HikariConfig config;
	private static HikariDataSource datasource;
	
	private static final String CONFIGURATION_LOCATION = "/db.properties";

	private static Logger logger = Logger.getLogger(DaoFactory.class);
	
	private DaoFactory() {
	}

	public static Connection getConn() {

		config = new HikariConfig(CONFIGURATION_LOCATION);
		datasource = new HikariDataSource(config);
		
		try {
			return datasource.getConnection();
		} catch (SQLException e) {
			logger.debug(e);
		}
		return null;
	}

	public Connection getConnection() {
		return getConn();
	}

	public CompanyDao getCompanyDao() {
		return new CompanyDao(this);
	}

	public ComputerDao getComputerDao() {
		return new ComputerDao(this);
	}
}
