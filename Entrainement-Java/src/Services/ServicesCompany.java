package Services;

import java.sql.SQLException;
import java.util.List;

import Company.Company;
import DAO.CompanyDaoImpl;
import DAO.DaoFactory;

public class ServicesCompany {

	public static List<Company>  companyList() {
		DaoFactory dao = DaoFactory.getInstance();
		try {
			dao.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		CompanyDaoImpl companyDao = new CompanyDaoImpl(dao);

		return companyDao.lister();
	}
}
