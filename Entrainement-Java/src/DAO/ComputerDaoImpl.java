package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import Computer.Computer;

public class ComputerDaoImpl implements ComputerDao{

	private DaoFactory daoFactory;

	public ComputerDaoImpl(DaoFactory daoFactory){
		this.daoFactory = daoFactory;
	}

	
	@Override
	public void ajouter(Computer computer) {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("INSERT INTO computer(name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?);");
			preparedStatement.setString(1, computer.getName());
			preparedStatement.setObject(2, computer.getIntroduced());
			preparedStatement.setObject(3, computer.getDiscontinued());
			preparedStatement.setLong(4, computer.getCompany_id());

			preparedStatement.executeUpdate();

		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void supprimer(int Id) {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("DELETE FROM computer WHERE id = ? ");
			preparedStatement.setLong(1, Id);

			preparedStatement.executeUpdate();

		} catch (SQLException e){
			e.printStackTrace();
		}
	}

	
	@Override
	public void modifier(Computer computer) {
	
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ?  WHERE id = ?;");
			preparedStatement.setString(1, computer.getName());
			preparedStatement.setDate(2, computer.getIntroduced());
			preparedStatement.setDate(3, computer.getDiscontinued());
			preparedStatement.setLong(4, computer.getCompany_id());
			
			preparedStatement.setLong(5, computer.getId());

			preparedStatement.executeUpdate();

		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	
	@Override
	public Computer selectionner(int Id) {

		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		Computer selectedComputer = new Computer(Id, null, null, null, 0);

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			String querry = "SELECT id, name, introduced, discontinued, company_id FROM computer WHERE id = "+ Id +";";
			resultat = statement.executeQuery(querry);

			while(resultat.next()) {
			
			long id = resultat.getLong("id");
			String name = resultat.getString("name");
			Date introduced = (Date) resultat.getDate("introduced");
			Date discontinued = (Date) resultat.getDate("discontinued");
			long company_id = resultat.getLong("company_id");

			selectedComputer = new Computer(id, name, introduced, discontinued, company_id);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return selectedComputer;
		
	}
	
	
	@Override
	public List<Computer> lister() {

		List<Computer> computer = new ArrayList<Computer>();

		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT id, name, introduced, discontinued, company_id FROM computer");


			while(resultat.next()) {

				long id = resultat.getLong("id");
				String name = resultat.getString("name");
				Date introduced = (Date) resultat.getDate("introduced");
				Date discontinued = (Date) resultat.getDate("discontinued");
				long company_id = resultat.getLong("company_id");


				Computer newComputer = new Computer(id, name, introduced, discontinued, company_id);		
				computer.add(newComputer);
			}

		}catch (SQLException e) {
			e.printStackTrace();
		}
		return computer;
	}
}
