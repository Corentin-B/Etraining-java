package fr.excilys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.excilys.services.ServiceControllerDashboard;

@Controller
@RequestMapping(value = "/dashboard")
public class Dashboard {
	
	public ServiceControllerDashboard serviceControllerDashboard;
	
	public Dashboard (ServiceControllerDashboard serviceControllerDashboard) {
		this.serviceControllerDashboard = serviceControllerDashboard;
	}

	@GetMapping
	public ModelAndView doGet(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
							  @RequestParam(value = "range", required = false, defaultValue = "10") int range,
							  @RequestParam(value = "search", required = false) String search,
							  @RequestParam(value = "order", required = false) String order,
							  @RequestParam(value = "sort", required = false) String sort) {

		return serviceControllerDashboard.getRequest(page, range, search, order, sort);
	}

	@PostMapping
	public ModelAndView doPost(@RequestParam(value = "selection", required = false) String selection) {

		return serviceControllerDashboard.postRequest(selection);
	}
}
