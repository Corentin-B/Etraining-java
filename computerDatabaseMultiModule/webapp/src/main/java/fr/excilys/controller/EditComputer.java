package fr.excilys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.excilys.model.DoPostParameter;
import fr.excilys.services.ServiceControllerEditComputer;


@Controller
@RequestMapping(value = "/editcomputer")
public class EditComputer {

	public ServiceControllerEditComputer serviceControllerEditComputer;

	public EditComputer (ServiceControllerEditComputer serviceControllerEditComputer) {
		this.serviceControllerEditComputer = serviceControllerEditComputer;
	}
	
	@GetMapping
	protected ModelAndView doGet(@RequestParam(value = "computerid", required = false, defaultValue = "0") int computerId) {

		return serviceControllerEditComputer.getRequest(computerId);
	}

	@PostMapping
	protected ModelAndView doPost(DoPostParameter doPostParameter) {
		
		return serviceControllerEditComputer.postRequest(doPostParameter);
	}
}
