package services;

import java.sql.SQLException;
import java.util.List;

import company.Company;
import dao.CompanyDaoImpl;
import dao.DaoFactory;

public class ServicesCompany {

	public static List<Company>  companyList(int range) {

		CompanyDaoImpl companyDao = new CompanyDaoImpl(getDaoFacotry());
		return companyDao.lister(range).get();
	}
	
	private static DaoFactory getDaoFacotry() {
		DaoFactory dao = DaoFactory.getInstance();
		try {
			dao.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dao;
	}
}
