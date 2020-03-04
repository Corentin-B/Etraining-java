package fr.excilys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import fr.excilys.mapper.MapperComputer;
import fr.excilys.mapper.MapperDateTime;
import fr.excilys.model.Computer;

public class ComputerDao {

	private DaoFactory daoFactory;

	private static Logger logger = Logger.getLogger(ComputerDao.class);

	private final String INSERT_NEWCOMPUTER		= "INSERT INTO computer(name, introduced, discontinued, company_id) "
												+ "VALUES (?, ?, ?, ?);";

	private final String DELETE_COMPUTER 		= "DELETE FROM computer " 
										 		+ "WHERE id = ?;";

	private final String UPDATE_COMPUTER 		= "UPDATE computer "
										 		+ "SET name = ?, introduced = ?, discontinued = ?, company_id = ? "
										 		+ "WHERE id = ?;";

	private final String SELECT_ONECOMPUTER 	= "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name "
												+ "FROM computer " 
												+ "LEFT JOIN company ON computer.company_id = company.id " 
												+ "WHERE computer.id = ?;";

	private final String SELECT_NOMBERCOMPUTER 	= "SELECT COUNT(*) "
											   	+ "FROM computer";

	private final String SELECT_ALLCOMPUTER 	= "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name "
												+ "FROM computer " 
												+ "LEFT JOIN company ON company_id = company.id " 
												+ "LIMIT ?, ?;";

	public ComputerDao(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public boolean ajouter(Computer computer) {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(INSERT_NEWCOMPUTER);
			preparedStatement.setString(1, computer.getName());
			preparedStatement.setTimestamp(2, MapperDateTime.getDatetimeToTimestamp(computer.getIntroduced()));
			preparedStatement.setTimestamp(3, MapperDateTime.getDatetimeToTimestamp(computer.getDiscontinued()));
			preparedStatement.setLong(4, computer.getCompany().getId());

			preparedStatement.executeUpdate();
			preparedStatement.close();
			connexion.close();

			return true;

		} catch (SQLException e) {
			logger.debug(e);
			return false;
		}
	}

	public boolean supprimer(int id) {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(DELETE_COMPUTER);
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

	public boolean modifier(Computer computer) {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(UPDATE_COMPUTER);
			preparedStatement.setString(1, computer.getName());
			preparedStatement.setTimestamp(2, MapperDateTime.getDatetimeToTimestamp(computer.getIntroduced()));
			preparedStatement.setTimestamp(3, MapperDateTime.getDatetimeToTimestamp(computer.getDiscontinued()));
			preparedStatement.setLong(4, computer.getCompany().getId());
			preparedStatement.setLong(5, computer.getId());

			preparedStatement.executeUpdate();
			preparedStatement.close();
			connexion.close();

			return true;

		} catch (SQLException e) {
			logger.debug(e);
			return false;
		}
	}

	public Optional<Computer> selectionner(int idComputer) {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		Computer selectedComputer = new Computer.Builder().build();

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(SELECT_ONECOMPUTER);
			preparedStatement.setInt(1, idComputer);

			ResultSet resultat = preparedStatement.executeQuery();

			if (resultat.first()) {

				selectedComputer = MapperComputer.getInstance().getComputerFromResultSet(resultat);
			}

			preparedStatement.close();
			connexion.close();
		} catch (SQLException e) {
			logger.debug(e);
		}
		return Optional.ofNullable(selectedComputer);
	}

	public int numberPage() {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		int pagesNumber = 0;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(SELECT_NOMBERCOMPUTER);

			ResultSet resultat = preparedStatement.executeQuery();

			if (resultat.first()) {
				pagesNumber = resultat.getInt(1);
			}

			preparedStatement.close();
			connexion.close();
		} catch (SQLException e) {
			logger.debug(e);
		}
		return pagesNumber;
	}

	public List<Computer> lister(int numberPage, int range) {

		List<Computer> computer = new ArrayList<>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(SELECT_ALLCOMPUTER);
			preparedStatement.setInt(1, numberPage);
			preparedStatement.setInt(2, range);

			ResultSet resultat = preparedStatement.executeQuery();

			while (resultat.next()) {

				computer.add(MapperComputer.getInstance().getComputerFromResultSet(resultat));
			}

			preparedStatement.close();
			connexion.close();
		} catch (SQLException e) {
			logger.debug(e);
		}
		return computer;
	}
}
