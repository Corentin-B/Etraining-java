package fr.excilys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.excilys.model.DoPostParameter;
import fr.excilys.services.ServiceControllerAddComputer;

@Controller
@RequestMapping(value = "/addcomputer")
public class AddComputer {

	public ServiceControllerAddComputer serviceControllerAddComputer;

	public AddComputer(ServiceControllerAddComputer serviceControllerAddComputer) {
		this.serviceControllerAddComputer = serviceControllerAddComputer;
	}

	@GetMapping
	protected ModelAndView doGet() {

		return serviceControllerAddComputer.getRequest();
	}

	@PostMapping
	protected ModelAndView doPost(DoPostParameter parameterObject) {

		return serviceControllerAddComputer.postRequest(parameterObject);
	}
}
