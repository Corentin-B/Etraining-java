package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import Computer.Computer;

public class ComputerDaoImpl implements ComputerDao{

	private DaoFactory daoFactory;

	ComputerDaoImpl(DaoFactory daoFactory){
		this.daoFactory = daoFactory;
	}

	@Override
	public void ajouter(Computer computer) {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("INSERT INTO company(name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?);");
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
	public void supprimer(Computer computer) {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("DELETE FOME computer WHERE id = ? ");
			preparedStatement.setLong(1, computer.getId());

			preparedStatement.executeUpdate();

		} catch (SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public void modifier(Computer computer) {
		// TODO Auto-generated method stub
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("UPDATE company SET name = ?, introduced = ?, discontinued = ?, company_id = ?  WHERE id = ?;");
			preparedStatement.setString(1, computer.getName());

			preparedStatement.executeUpdate();

		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	
	@Override
	public List<Computer> lister() {

		List<Computer> company = new ArrayList<Computer>();

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
				Timestamp introduced = (Timestamp) resultat.getObject("introduced");
				Timestamp discontinued = (Timestamp) resultat.getObject("discontinued");
				long company_id = resultat.getLong("company_id");


				Computer newcompany = new Computer(id, name, introduced, discontinued, company_id);		
				company.add(newcompany);
			}

		}catch (SQLException e) {
			e.printStackTrace();
		}
		return company;
	}


}
