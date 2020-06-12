package fr.excilys.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.excilys.model.Company;
import fr.excilys.repository.CompanyRepository;

@Service
public class ServicesCompany {
	
	private static CompanyRepository companyRepository;
	
	public ServicesCompany(CompanyRepository companyRepository) {
		ServicesCompany.companyRepository = companyRepository;
	}
	/*
	@Autowired
    private static CompanyRepository companyRepository;*/
	
	public static void companyRemove(int idComputer) throws SQLException {

		companyRepository.deleteById((long) idComputer);;
	}
    
	public static List<Company>  companyList() {

		return companyRepository.findAll();
	}
	
	public static Company companySelectForCheck(long idCompany) {

		return companyRepository.findById(idCompany).orElse(new Company());
	}
}
