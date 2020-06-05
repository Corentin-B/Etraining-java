package fr.excilys.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.excilys.mapper.MapperComputer;
import fr.excilys.mapper.FormatServletRequest;
import fr.excilys.model.Company;
import fr.excilys.model.Computer;
import fr.excilys.services.ServicesCompany;
import fr.excilys.services.ServicesComputer;

@Controller
@RequestMapping(value = "/editcomputer")
public class EditComputer {

	public ServicesComputer serviceComputer;

	public EditComputer (ServicesComputer serviceComputer) {
		this.serviceComputer = serviceComputer;
	}
	
	@GetMapping
	protected ModelAndView doGet(@RequestParam(value = "computerid", required = false, defaultValue = "0") int computerId) {

		Computer computer = serviceComputer.computerSelectForUpdate(computerId);

		List<Company> companyList = ServicesCompany.companyList();
		
		ModelAndView modelandview = new ModelAndView();
		modelandview.addObject("computer", computer);
		modelandview.addObject("companyList", companyList);

		return modelandview;
	}

	@PostMapping
	protected ModelAndView doPost(@RequestParam(value = "computerId", required = false) String computerId,
			  					  @RequestParam(value = "computerName", required = false) String computerName,
			  					  @RequestParam(value = "introduced", required = false) String introduced,
			  					  @RequestParam(value = "discontinued", required = false) String discontinued,
			  					  @RequestParam(value = "companyId", required = false) String companyId) {
		
		Boolean success = false;
		
		Computer newcomputer = MapperComputer.getComputerFromResponse(computerId, computerName, introduced, discontinued, companyId);
		
		if(FormatServletRequest.checkCompany(newcomputer.getCompany().getId())) {
			if(serviceComputer.computerUpdate(newcomputer) != 0)
				success = true;
		} else {
			newcomputer.setName("Unknown");
		}
		
		ModelAndView modelandview = new ModelAndView();
		modelandview.addObject("updateComputerName", newcomputer.getName());
		modelandview.addObject("success", success);
		
		return modelandview;
	}
}
