package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Company.Company;

public class CompanyDaoImpl implements CompanyDao {

	private DaoFactory daoFactory;

	public CompanyDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public List<Company> lister() {

		List<Company> company = new ArrayList<Company>();

		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("SELECT id, name FROM company LIMIT ?, ?");
			preparedStatement.setInt(1, 0);
			preparedStatement.setInt(2, 20);
			ResultSet resultat = preparedStatement.executeQuery();

			while (resultat.next()) {

				long id = resultat.getLong("id");
				String name = resultat.getString("name");

				Company newcompany = new Company(id, name);
				company.add(newcompany);
			}

			preparedStatement.close();
			connexion.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return company;
	}
}
