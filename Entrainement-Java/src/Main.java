import java.sql.SQLException;

import Company.Company;
import DAO.DaoFactory;
import DAO.UtilisateurDaoImpl;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DaoFactory dao = DaoFactory.getInstance();
		try {
			dao.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		UtilisateurDaoImpl utilisateurDao = new UtilisateurDaoImpl(dao);

		Company agfa = new Company(0, "MySherpa");
		
		utilisateurDao.ajouter(agfa);
		
		for (Company details : utilisateurDao.lister()) {
			System.out.println(details.getId() + " " + details.getName());
		}

	}
}
