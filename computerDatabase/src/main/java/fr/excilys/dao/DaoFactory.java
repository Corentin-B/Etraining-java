package fr.excilys.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

public final class DaoFactory {
	private String url;
	private String username;
	private String password;
	private static Properties connectionProperties;
	private static volatile DaoFactory instance = null;
	private static final String CONFIGURATION_LOCATION = "databaseconfig.properties";

	private static Logger logger = Logger.getLogger(DaoFactory.class);

	private DaoFactory(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}

	public static DaoFactory getInstance() {
		
		if (DaoFactory.instance == null) {

			synchronized (DaoFactory.class) {
				if (DaoFactory.instance == null) {

					try {
						Class.forName("com.mysql.jdbc.Driver");
					} catch (ClassNotFoundException e) {
						logger.debug(e);
					}

					List<String> conProperties = getConnectionProperties();

					DaoFactory.instance = new DaoFactory(conProperties.get(0), 
														 conProperties.get(1),
														 conProperties.get(2));
				}
			}
		}
		return DaoFactory.instance;
	}

	private static List<String> getConnectionProperties() {

		List<String> conProperties = new ArrayList<>();
		connectionProperties = new Properties();

		try {
			connectionProperties.load(Thread.currentThread()
											.getContextClassLoader()
											.getResourceAsStream(CONFIGURATION_LOCATION));

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
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}

	public CompanyDao getCompanyDao() {
		return new CompanyDaoImpl(this);
	}

	public ComputerDao getComputerDao() {
		return new ComputerDaoImpl(this);
	}
}
