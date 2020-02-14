package services;

import java.sql.SQLException;
import java.util.List;

import company.Company;
import dao.CompanyDaoImpl;
import dao.DaoFactory;

public class ServicesCompany {

	public static List<Company>  companyList(int range) {
		DaoFactory dao = DaoFactory.getInstance();
		try {
			dao.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		CompanyDaoImpl companyDao = new CompanyDaoImpl(dao);

		return companyDao.lister(range);
	}
}
