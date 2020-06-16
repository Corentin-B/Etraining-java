package fr.excilys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.excilys.model.DoGetParameter;
import fr.excilys.services.ServiceControllerDashboard;

@Controller
@RequestMapping(value = "/dashboard")
public class Dashboard {
	
	public ServiceControllerDashboard serviceControllerDashboard;
	
	public Dashboard (ServiceControllerDashboard serviceControllerDashboard) {
		this.serviceControllerDashboard = serviceControllerDashboard;
	}
	
	@GetMapping
	public ModelAndView doGet(DoGetParameter parameterObject) {
		
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
