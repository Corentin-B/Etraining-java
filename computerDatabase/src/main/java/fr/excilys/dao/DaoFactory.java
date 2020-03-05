package fr.excilys.dao;

<<<<<<< HEAD
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
=======
import java	.sql.Connection;
>>>>>>> dev
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public final class DaoFactory {
<<<<<<< HEAD
	private String url;
	private String username;
	private String password;
	private static Properties connectionProperties;
	private static volatile DaoFactory instance = null;
	private static final String CONFIGURATION_LOCATION = "resouces/databaseconfig.properties";
=======

	private static HikariConfig config;
	private static HikariDataSource datasource;
	
	private static volatile DaoFactory instance = null;
	private static final String CONFIGURATION_LOCATION = "/db.properties";
>>>>>>> dev

	private static Logger logger = Logger.getLogger(DaoFactory.class);
	
	private DaoFactory() {
		super();
	}

	public static DaoFactory getInstance() {
		
		if (DaoFactory.instance == null) {

			synchronized (DaoFactory.class) {
				if (DaoFactory.instance == null) {

<<<<<<< HEAD
					try {
						Class.forName("com.mysql.jdbc.Driver");
					} catch (ClassNotFoundException e) {
						logger.debug(e);
					}

					List<String> conProperties = getConnectionProperties();

					DaoFactory.instance = new DaoFactory(conProperties.get(0), 
														 conProperties.get(1),
														 conProperties.get(2));
=======
					DaoFactory.instance = new DaoFactory();
>>>>>>> dev
				}
			}
		}
		return DaoFactory.instance;
	}
<<<<<<< HEAD

	private static List<String> getConnectionProperties() {

		List<String> conProperties = new ArrayList<>();
		connectionProperties = new Properties();

		try {
			connectionProperties.load(Thread.currentThread()
											.getContextClassLoader()
											.getResourceAsStream("databaseconfig.properties"));

			conProperties.add(connectionProperties.getProperty("url"));
			conProperties.add(connectionProperties.getProperty("username"));
			conProperties.add(connectionProperties.getProperty("password"));

			System.out.println(conProperties);
		} catch (IOException e) {
			logger.debug(e);
		} catch (NullPointerException e) {
			logger.debug(e);
		}

		return conProperties;
=======
	
	public static Connection getConn() {

		config = new HikariConfig(CONFIGURATION_LOCATION);
		datasource = new HikariDataSource(config);
		
		try {
			return datasource.getConnection();
		} catch (SQLException e) {
			logger.debug(e);
		}
		return null;
>>>>>>> dev
	}

	public Connection getConnection() throws SQLException {
		return getConn();
	}

	public CompanyDao getCompanyDao() {
		return new CompanyDao(this);
	}

	public ComputerDao getComputerDao() {
		return new ComputerDao(this);
	}
}
