package fr.excilys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	protected ModelAndView doPost(@RequestParam(value = "computerId", required = false) String computerId,
			@RequestParam(value = "computerName", required = false) String computerName,
			@RequestParam(value = "introduced", required = false) String introduced,
			@RequestParam(value = "discontinued", required = false) String discontinued,
			@RequestParam(value = "companyId", required = false) String companyId) {

		return serviceControllerAddComputer.postRequest(computerId, computerName, introduced, discontinued, companyId);
	}
}
