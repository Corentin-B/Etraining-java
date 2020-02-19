package fr.excilys.services;

import java.sql.SQLException;
import java.util.List;

import fr.excilys.DefaultLogger.Logger;
import fr.excilys.dao.CompanyDaoImpl;
import fr.excilys.dao.DaoFactory;
import fr.excilys.model.Company;

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
			Logger.writeLog("ERROR", "can't get DAO Connection");
		}
		return dao;
	}
}
