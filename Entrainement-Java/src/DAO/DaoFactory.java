package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DaoFactory {
	private String url;
	private String username;
	private String password;
	private static volatile DaoFactory instance = null;

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
						e.printStackTrace();
					}

					DaoFactory.instance = new DaoFactory("jdbc:mysql://localhost:3306/computer-database-db", "admincdb",
							"qwerty1234");
				}
			}
		}
		return DaoFactory.instance;

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
