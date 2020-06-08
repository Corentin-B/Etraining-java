package fr.excilys.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.excilys.mapper.FormatServletRequest;
import fr.excilys.mapper.MapperComputer;
import fr.excilys.model.Company;
import fr.excilys.model.Computer;

@Service
public class ServiceControllerAddComputer {
	
	
	public ServicesComputer serviceComputer;
	
	public ServiceControllerAddComputer (ServicesComputer serviceComputer) {
		this.serviceComputer = serviceComputer;
	}

	public ModelAndView getRequest() {

		List<Company> companyList = ServicesCompany.companyList();

		ModelAndView modelandview = new ModelAndView();
		modelandview.addObject("companyList", companyList);

		return modelandview;
	}

	public ModelAndView postRequest(@RequestParam(value = "computerId", required = false) String computerId,
			@RequestParam(value = "computerName", required = false) String computerName,
			@RequestParam(value = "introduced", required = false) String introduced,
			@RequestParam(value = "discontinued", required = false) String discontinued,
			@RequestParam(value = "companyId", required = false) String companyId) {

		Boolean success = false;

		Computer newcomputer = MapperComputer.getComputerFromResponse(computerId, computerName, introduced,
				discontinued, companyId);

		if (!newcomputer.getName().isBlank()) {
			if (FormatServletRequest.checkCompany(newcomputer.getCompany().getId())) {
				if (serviceComputer.computerAdd(newcomputer) != 0)
					success = true;
			} else {
				newcomputer.setName("Unknown");
			}
		} else {
			newcomputer.setName("Unknown");
		}

		ModelAndView modelandview = new ModelAndView();
		modelandview.addObject("newComputerName", newcomputer.getName());
		modelandview.addObject("success", success);

		return modelandview;
	}
}
