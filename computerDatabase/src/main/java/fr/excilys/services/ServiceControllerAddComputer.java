package fr.excilys.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import fr.excilys.mapper.FormatServletRequest;
import fr.excilys.mapper.MapperComputer;
import fr.excilys.model.Company;
import fr.excilys.model.Computer;
import fr.excilys.model.DoPostParameter;

@Service
public class ServiceControllerAddComputer {

	private ServicesComputer serviceComputer;
	private ServicesCompany servicesCompany;

	public ServiceControllerAddComputer(ServicesComputer serviceComputer, ServicesCompany servicesCompany) {
		this.serviceComputer = serviceComputer;
		this.servicesCompany = servicesCompany;
	}

	public ModelAndView getRequest() {

		List<Company> companyList = servicesCompany.companyList();

		ModelAndView modelandview = new ModelAndView();
		modelandview.addObject("companyList", companyList);

		return modelandview;
	}

	public ModelAndView postRequest(DoPostParameter parameterObject) {

		Boolean success = false;

		Computer newcomputer = MapperComputer.getComputerFromResponse(parameterObject);

		if (!newcomputer.getName().isBlank()) {
			if (FormatServletRequest.checkCompany(newcomputer.getCompany().getId(), servicesCompany)) {
				Computer addedcomputer = serviceComputer.computerAdd(newcomputer);
				if (addedcomputer.getId() != 0)
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
