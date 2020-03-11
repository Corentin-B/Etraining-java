package fr.excilys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import fr.excilys.mapper.MapperCompany;
import fr.excilys.model.Company;

@Repository
public class CompanyDao {

	private DaoFactory daoFactory;

	private static Logger logger = Logger.getLogger(CompanyDao.class);

	public CompanyDao(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public boolean remove(int id) throws SQLException {

		Connection connexion = null;
		PreparedStatement preparedStatementDeleteCompany = null;
		PreparedStatement preparedStatementDeleteComputer = null;

		try {
			connexion = daoFactory.getConnection();
			connexion.setAutoCommit(false);

			preparedStatementDeleteComputer = connexion
					.prepareStatement(EnumSQLRequestCompany.DELETE_COMPANYCOMPUTER.getMessage());
			preparedStatementDeleteComputer.setLong(1, id);
			preparedStatementDeleteComputer.executeUpdate();

			preparedStatementDeleteCompany = connexion
					.prepareStatement(EnumSQLRequestCompany.DELETE_COMPANY.getMessage());
			preparedStatementDeleteCompany.setLong(1, id);
			preparedStatementDeleteCompany.executeUpdate();
			connexion.commit();

			return true;

		} catch (SQLException e) {
			connexion.rollback();
			logger.debug(e);
			return false;

		} finally {
			if (preparedStatementDeleteCompany != null)
				preparedStatementDeleteCompany.close();

			if (preparedStatementDeleteComputer != null)
				preparedStatementDeleteComputer.close();

			if (connexion != null)
				connexion.setAutoCommit(true);
		}

	}

	public List<Company> lister() {

		List<Company> company = new ArrayList<>();
		PreparedStatement preparedStatement = null;

		try {
			Connection connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(EnumSQLRequestCompany.SELECT_ALLCOMPANY.getMessage());

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

	public Optional<Company> selecOneCompany(long idComputer) {

		PreparedStatement preparedStatement = null;
		Company selectedCompany = new Company.Builder().build();

		try {
			Connection connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(EnumSQLRequestCompany.SELECT_ONECOMPANY.getMessage());
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
