package fr.excilys.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.excilys.dao.CompanyDao;
import fr.excilys.model.Company;

@Service
public class ServicesCompany {
	
	private static CompanyDao companyDao;
    
	public ServicesCompany(CompanyDao companyDao) {
		ServicesCompany.companyDao = companyDao;
	}
	
	public static int[] companyRemove(int idComputer) throws SQLException {

		return companyDao.remove(idComputer);
	}
    
	public static List<Company>  companyList() {

		return companyDao.lister();
	}
	
	public static Company companySelectForCheck(long idCompany) {

		return companyDao.selecOneCompany(idCompany); 
	}
}
