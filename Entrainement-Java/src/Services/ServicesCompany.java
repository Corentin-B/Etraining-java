package Services;

import java.sql.SQLException;

import Client.UserInterface;
import DAO.CompanyDaoImpl;
import DAO.DaoFactory;

public class ServicesCompany {

	public static void companyList() {
		DaoFactory dao = DaoFactory.getInstance();
		try {
			dao.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		CompanyDaoImpl companyDao = new CompanyDaoImpl(dao);

		UserInterface.displayCompanyList(companyDao.lister());
	}
}
