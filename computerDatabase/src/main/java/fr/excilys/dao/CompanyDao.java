package fr.excilys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import fr.excilys.mapper.MapperCompany;
import fr.excilys.model.Company;

public class CompanyDao {

	private DaoFactory daoFactory;

	private static Logger logger = Logger.getLogger(CompanyDao.class);

	private final String DELETE_COMPANY   = "DELETE FROM company "
										   + "WHERE company.id = ?;";

	private final String SELECT_ALLCOMPANY = "SELECT company.id, company.name "
										   + "FROM company;";

	private final String SELECT_ONECOMPANY = "SELECT company.id, company.name "
										   + "FROM company WHERE company.id = ?;";

	public CompanyDao(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public boolean remove(int id) {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(DELETE_COMPANY);
			preparedStatement.setLong(1, id);

			preparedStatement.executeUpdate();
			preparedStatement.close();
			connexion.close();

			return true;

		} catch (SQLException e) {
			logger.debug(e);
			return false;
		}
	}

	public List<Company> lister() {

		List<Company> company = new ArrayList<>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(SELECT_ALLCOMPANY);

			ResultSet resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				company.add(MapperCompany.getInstance().getCompanyFromResultSet(resultat));
			}

			preparedStatement.close();
			connexion.close();

		} catch (SQLException e) {
			logger.debug(e);
		}
		return company;
	}

	public Optional<Company> selectionner(long idComputer) {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		Company selectedCompany = new Company.Builder().build();

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(SELECT_ONECOMPANY);
			preparedStatement.setLong(1, idComputer);

			ResultSet resultat = preparedStatement.executeQuery();

			if (resultat.first()) {

				selectedCompany = MapperCompany.getInstance().getCompanyFromResultSet(resultat);
			}

			preparedStatement.close();
			connexion.close();
		} catch (SQLException e) {
			logger.debug(e);
		}
		return Optional.ofNullable(selectedCompany);
	}
}
