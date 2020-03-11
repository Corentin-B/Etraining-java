package fr.excilys.services;

import java.sql.SQLException;
import java.util.List;
import fr.excilys.dao.CompanyDao;
import fr.excilys.model.Company;

public class ServicesCompany {
	
	private static CompanyDao companyDao;
    
	public static boolean companyRemove(int idComputer) throws SQLException {

		return companyDao.remove(idComputer);
	}
    
	public static List<Company>  companyList() {

		return companyDao.lister();
	}
	
	public static Company companySelectForCheck(long idCompany) {

		return companyDao.selecOneCompany(idCompany).get();
	}
}
