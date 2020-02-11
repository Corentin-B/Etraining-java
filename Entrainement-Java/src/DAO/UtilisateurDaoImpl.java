package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Company.Company;

public class UtilisateurDaoImpl implements UtilisateurDao{

	private DaoFactory daoFactory;

	UtilisateurDaoImpl(DaoFactory daoFactory){
		this.daoFactory = daoFactory;
	}

	@Override
	public void ajouter(Company utilisateur) {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("INSERT INTO company(name) VALUES (?);");
			preparedStatement.setString(1, utilisateur.getName());

			preparedStatement.executeUpdate();

		} catch (SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public List<Company> lister() {

		List<Company> company = new ArrayList<Company>();

		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT id, name FROM company");


			while(resultat.next()) {
				
				long id = resultat.getLong("id");
				String name = resultat.getString("name");

				Company newcompany = new Company(id, name);		
				company.add(newcompany);
			}

		}catch (SQLException e) {
			e.printStackTrace();
		}
		return company;
	}
}
