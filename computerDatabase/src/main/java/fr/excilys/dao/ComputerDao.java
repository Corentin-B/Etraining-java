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

import fr.excilys.mapper.MapperComputer;
import fr.excilys.mapper.MapperDateTimeMidNight;
import fr.excilys.mapper.PrepareQuerry;
import fr.excilys.model.Computer;

@Repository
public class ComputerDao {

	private DaoFactory daoFactory;

	private static Logger logger = Logger.getLogger(ComputerDao.class);

	public ComputerDao(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public boolean add(Computer computer) {
		PreparedStatement preparedStatement = null;

		try {
			Connection connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(EnumSQLRequestComputer.INSERT_NEWCOMPUTER.getMessage());
			preparedStatement.setString(1, computer.getName());
			preparedStatement.setTimestamp(2, MapperDateTimeMidNight.getDatetimeToTimestamp(computer.getIntroduced()));
			preparedStatement.setTimestamp(3, MapperDateTimeMidNight.getDatetimeToTimestamp(computer.getDiscontinued()));
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

	public boolean remove(int id) {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(EnumSQLRequestComputer.DELETE_COMPUTER.getMessage());
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

	public boolean update(Computer computer) {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(EnumSQLRequestComputer.UPDATE_COMPUTER.getMessage());
			preparedStatement.setString(1, computer.getName());
			preparedStatement.setTimestamp(2, MapperDateTimeMidNight.getDatetimeToTimestamp(computer.getIntroduced()));
			preparedStatement.setTimestamp(3, MapperDateTimeMidNight.getDatetimeToTimestamp(computer.getDiscontinued()));
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

	public int numberPage() {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		int pagesNumber = 0;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(EnumSQLRequestComputer.SELECT_NUMBERCOMPUTER.getMessage());

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

	public int numberSearch(String name) {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		int pagesNumber = 0;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(EnumSQLRequestComputer.SELECT_NUMBERSEARCH.getMessage());
			preparedStatement.setString(1, "%" + name.toUpperCase() + "%");

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

	public Optional<Computer> selectComputerById(int idComputer) {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		Computer selectedComputer = new Computer.Builder().build();

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(EnumSQLRequestComputer.SELECT_ONECOMPUTER.getMessage());
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

	public List<Computer> searchComputerByName(String nameComputer, int numberPage, int range, String order,
			String sort) {

		List<Computer> computer = new ArrayList<>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(PrepareQuerry.checkOrderValueSuffix(order, sort, EnumSQLRequestComputer.SELECT_SEARCHCOMPUTER.getMessage()));
			preparedStatement.setString(1, "%" + nameComputer.toUpperCase() + "%");
			preparedStatement.setInt(2, numberPage);
			preparedStatement.setInt(3, range);

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

	public List<Computer> list(int numberPage, int range) {

		List<Computer> computer = new ArrayList<>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(EnumSQLRequestComputer.SELECT_ALLCOMPUTER.getMessage());
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

	public List<Computer> listOrder(int numberPage, int range, String order, String sort) {

		List<Computer> computer = new ArrayList<>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(PrepareQuerry.checkOrderValueSuffix(order, sort, EnumSQLRequestComputer.SELECT_ALLCOMPUTER_ORDER.getMessage()));
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
