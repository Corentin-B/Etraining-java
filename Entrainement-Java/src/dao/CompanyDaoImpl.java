package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import company.Company;

public class CompanyDaoImpl implements CompanyDao {

	private DaoFactory daoFactory;

	final String SELECT_ALLCOMPANY = "SELECT id, name FROM company LIMIT ?, 20;";

	public CompanyDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public Optional<List<Company>> lister(int range) {
	
		List<Company>company = new ArrayList<>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(SELECT_ALLCOMPANY);
			preparedStatement.setInt(1, range);
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
			//TODO Log & wrapper
		}
		return Optional.ofNullable(company);
	}
}
