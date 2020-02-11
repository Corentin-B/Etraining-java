import java.sql.SQLException;

import Company.Company;
import DAO.DaoFactory;
import DAO.CompanyDaoImpl;


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

		CompanyDaoImpl companyDao = new CompanyDaoImpl(dao);
		
		
		for (Company details : companyDao.lister()) {
			System.out.println(details.getId() + " " + details.getName());
		}

	}
}
