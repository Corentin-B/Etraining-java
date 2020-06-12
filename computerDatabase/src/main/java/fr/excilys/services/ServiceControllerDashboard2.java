package fr.excilys.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.excilys.model.Company;
import fr.excilys.repository.CompanyRepository;

@Service
public class ServiceControllerDashboard2 {

	public CompanyRepository companyRepository;

	@Transactional
	public List<Company> getCompany() {
		return companyRepository.findAll();
	}
	
    @Transactional
    public void saveCompany(Company theCompany) {
        companyRepository.save(theCompany);
    }
    
    @Transactional
    public Company getCompany(Long id) {
    	return companyRepository.findById(id).orElse(new Company.Builder().build());
    }
}
