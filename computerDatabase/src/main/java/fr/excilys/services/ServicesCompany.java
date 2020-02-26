package fr.excilys.services;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import fr.excilys.dao.CompanyDaoImpl;
import fr.excilys.dao.DaoFactory;
import fr.excilys.model.Company;

public class ServicesCompany {

    private static Logger logger = Logger.getLogger(ServicesCompany.class);
	
	public static List<Company>  companyList(int range) {

		CompanyDaoImpl companyDao = new CompanyDaoImpl(getDaoFacotry());
		return companyDao.lister(range);
	}
	
	private static DaoFactory getDaoFacotry() {
		DaoFactory dao = DaoFactory.getInstance();
		try {
			dao.getConnection();
		} catch (SQLException e) {
	        logger.debug(e);
		}
		return dao;
	}
}
