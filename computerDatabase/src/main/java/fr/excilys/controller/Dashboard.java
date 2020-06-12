package fr.excilys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.excilys.model.Company;
import fr.excilys.model.DoGetParameter;
import fr.excilys.repository.CompanyRepository;
import fr.excilys.services.ServiceControllerDashboard;

@Controller
@RequestMapping(value = "/dashboard")
public class Dashboard {
	
	public ServiceControllerDashboard serviceControllerDashboard;
	
	public Dashboard (ServiceControllerDashboard serviceControllerDashboard) {
		this.serviceControllerDashboard = serviceControllerDashboard;
	}
    
	@Autowired
    private CompanyRepository companyRepository;
	
	@GetMapping
	public ModelAndView doGet(DoGetParameter parameterObject) {
	        		
	      System.out.println(" -- finding all company --");
	      List <Company> all = companyRepository.findAll();
	      System.out.println(all);
		
		return serviceControllerDashboard.getRequest(parameterObject);
	}
	
	@PostMapping
	public ModelAndView doPost(@RequestParam(value = "selection", required = false) String selection) {

		return serviceControllerDashboard.deleteRequest(selection);
	}
	
	@DeleteMapping
	public ModelAndView doDelete(@RequestParam(value = "selection", required = false) String selection) {

		return serviceControllerDashboard.deleteRequest(selection);
	}
}
