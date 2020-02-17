package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import computer.Computer;

public class ComputerDaoImpl implements ComputerDao {

	private DaoFactory daoFactory;

	final String INSERT_NEWCOMPUTER = "INSERT INTO computer(name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?);";
	final String DELETE_COMPUTER = "DELETE FROM computer WHERE id = ?;";
	final String UPDATE_COMPUTER = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ?  WHERE id = ?;";
	final String SELECT_ONECOMPUTER = "SELECT id, name, introduced, discontinued, company_id FROM computer WHERE id = ?;";
	final String SELECT_ALLCOMPUTER = "SELECT id, name, introduced, discontinued, company_id FROM computer LIMIT ?, 20;";

	public ComputerDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public void ajouter(Computer computer) {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(INSERT_NEWCOMPUTER);
			preparedStatement.setString(1, computer.getName());
			preparedStatement.setDate(2, computer.getIntroduced());
			preparedStatement.setDate(3, computer.getDiscontinued());
			preparedStatement.setLong(4, computer.getCompany_id());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Echec d'ajout");
		}
	}

	@Override
	public void supprimer(int id) {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(DELETE_COMPUTER);
			preparedStatement.setLong(1, id);

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Echec de la supression");
		}
	}

	@Override
	public void modifier(Computer computer) {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(UPDATE_COMPUTER);
			preparedStatement.setString(1, computer.getName());
			preparedStatement.setDate(2, computer.getIntroduced());
			preparedStatement.setDate(3, computer.getDiscontinued());
			preparedStatement.setLong(4, computer.getCompany_id());
			preparedStatement.setLong(5, computer.getId());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Echec de la mise à jour");
		}
	}

	@Override
	public Optional<Computer> selectionner(int idComputer) {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		Computer selectedComputer = new Computer(idComputer, null, null, null, 0);

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(SELECT_ONECOMPUTER);
			preparedStatement.setInt(1, idComputer);
			
			ResultSet resultat = preparedStatement.executeQuery();

			while (resultat.next()) {

				long id = resultat.getLong("id");
				String name = resultat.getString("name");
				Date introduced = (Date) resultat.getDate("introduced");
				Date discontinued = (Date) resultat.getDate("discontinued");
				long company_id = resultat.getLong("company_id");

				selectedComputer = new Computer(id, name, introduced, discontinued, company_id);
			}

			preparedStatement.close();
			connexion.close();
		} catch (SQLException e) {
			//TODO log & wrapper
		}
		return Optional.ofNullable(selectedComputer);
	}

	@Override
	public Optional<List<Computer>> lister(int range) {

		List<Computer> computer = new ArrayList<>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(SELECT_ALLCOMPUTER);
			preparedStatement.setInt(1, range);
			
			ResultSet resultat = preparedStatement.executeQuery();

			while (resultat.next()) {

				long id = resultat.getLong("id");
				String name = resultat.getString("name");
				Date introduced = (Date) resultat.getDate("introduced");
				Date discontinued = (Date) resultat.getDate("discontinued");
				long company_id = resultat.getLong("company_id");

				Computer newComputer = new Computer(id, name, introduced, discontinued, company_id);
				
				computer.add(newComputer);
			}

			preparedStatement.close();
			connexion.close();
		} catch (SQLException e) {
			//TODO log & wrapper
		}
		return Optional.ofNullable(computer);
	}
}
