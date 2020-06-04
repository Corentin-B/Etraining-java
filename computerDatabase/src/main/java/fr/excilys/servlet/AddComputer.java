package fr.excilys.servlet;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.excilys.model.Company;
import fr.excilys.model.Computer;
import fr.excilys.services.ServicesCompany;
import fr.excilys.services.ServicesComputer;
import fr.excilys.mapper.MapperComputer;

@Controller
@RequestMapping(value = "/addcomputer")
public class AddComputer {

	public ServicesComputer serviceComputer;

	public AddComputer (ServicesComputer serviceComputer) {
		this.serviceComputer = serviceComputer;
	}
	
	@GetMapping
	protected ModelAndView doGet() {

		List<Company> companyList = ServicesCompany.companyList();
								
		ModelAndView modelandview = new ModelAndView();
		
		modelandview.addObject("companyList", companyList);
		
		return modelandview;
	}

	@PostMapping
	protected ModelAndView doPost(@RequestParam(value = "computerId", required = false) String computerId,
			  					  @RequestParam(value = "computerName", required = false) String computerName,
			  					  @RequestParam(value = "introduced", required = false) String introduced,
			  					  @RequestParam(value = "discontinued", required = false) String discontinued,
			  					  @RequestParam(value = "companyId", required = false) String companyId) {

		Boolean Success = false;
		
		Computer newcomputer = MapperComputer.getComputerFromResponse(computerId, computerName, introduced, discontinued, companyId);
		
		if(!newcomputer.getName().isBlank()) {
			if(serviceComputer.computerAdd(newcomputer) != 0)
				Success = true;
		} else {
			newcomputer.setName("Unknown");
		}
		
		ModelAndView modelandview = new ModelAndView();
		
		modelandview.addObject("newComputerName", newcomputer.getName());
		modelandview.addObject("Success", Success);
		
		return modelandview;
	}
}
