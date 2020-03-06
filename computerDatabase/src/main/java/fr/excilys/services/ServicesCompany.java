package fr.excilys.services;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import fr.excilys.dao.CompanyDao;
import fr.excilys.dao.DaoFactory;
import fr.excilys.model.Company;

public class ServicesCompany {

    private static Logger logger = Logger.getLogger(ServicesCompany.class);
	
	public static boolean companyRemove(int idComputer) {

		CompanyDao companyDao = new CompanyDao(getDaoFactory());
		return companyDao.remove(idComputer);
	}
    
	public static List<Company>  companyList() {

		CompanyDao companyDao = new CompanyDao(getDaoFactory());
		return companyDao.lister();
	}
	
	public static Company companySelectForCheck(long idCompany) {

		CompanyDao companyDao = new CompanyDao(getDaoFactory());
		return companyDao.selecOneCompany(idCompany).get();
	}
	
	private static DaoFactory getDaoFactory() {
		DaoFactory dao = DaoFactory.getInstance();
		try {
			dao.getConnection();
		} catch (SQLException e) {
	        logger.debug(e);
		}
		return dao;
	}
}
