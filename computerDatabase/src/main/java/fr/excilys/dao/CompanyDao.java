package fr.excilys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import fr.excilys.mapper.MapperCompany;
import fr.excilys.model.Company;


public class CompanyDao {

	private DaoFactory daoFactory;

	private static Logger logger = Logger.getLogger(CompanyDao.class);
	
	private final String SELECT_ALLCOMPANY="SELECT company.id, company.name "
										 + "FROM company;";

	public CompanyDao(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
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
}
