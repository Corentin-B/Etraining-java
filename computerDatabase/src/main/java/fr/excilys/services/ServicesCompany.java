package fr.excilys.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.excilys.model.Company;
import fr.excilys.repository.CompanyRepository;

@Service
public class ServicesCompany {
	
	@Autowired
	private CompanyRepository companyRepository;

	public ServicesCompany(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}
	
	public void companyRemove(int idComputer) throws SQLException {

		companyRepository.deleteById((long) idComputer);;
	}
    
	public List<Company>  companyList() {

		System.out.println(companyRepository);
		return companyRepository.findAll();
	}
	
	public Company companySelectForCheck(long idCompany) {

		return companyRepository.findById(idCompany).orElse(new Company());
	}
}
