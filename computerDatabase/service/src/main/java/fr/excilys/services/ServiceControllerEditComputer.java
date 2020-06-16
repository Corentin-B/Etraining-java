package fr.excilys.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import fr.excilys.mapper.MapperComputer;
import fr.excilys.model.Company;
import fr.excilys.model.Computer;
import fr.excilys.model.DoPostParameter;

@Service
public class ServiceControllerEditComputer {

	private ServicesComputer serviceComputer;
	private ServicesCompany servicesCompany;

	public ServiceControllerEditComputer(ServicesComputer serviceComputer, ServicesCompany servicesCompany) {
		this.serviceComputer = serviceComputer;
		this.servicesCompany = servicesCompany;
	}

	public ModelAndView getRequest(int computerId) {

		Computer computer = serviceComputer.computerSelectForUpdate(computerId);

		List<Company> companyList = servicesCompany.companyList();

		ModelAndView modelandview = new ModelAndView();
		modelandview.addObject("computer", computer);
		modelandview.addObject("companyList", companyList);

		return modelandview;
	}

	public ModelAndView postRequest(DoPostParameter doPostParameter) {

		Boolean success = false;

		Computer newcomputer = MapperComputer.getComputerFromResponse(doPostParameter);

		if (checkCompany(newcomputer.getCompany().getId(), servicesCompany)) {
			Computer updatedComputer = serviceComputer.computerUpdate(newcomputer);
			if (updatedComputer.getId() != 0)
				success = true;
		} else {
			newcomputer.setName("Unknown");
		}

		ModelAndView modelandview = new ModelAndView();
		modelandview.addObject("updateComputerName", newcomputer.getName());
		modelandview.addObject("success", success);

		return modelandview;
	}
	
	private static boolean checkCompany(long companyId, ServicesCompany servicesCompany) {
		
		Company company = servicesCompany.companySelectForCheck(companyId);

		return company.getId() == companyId;
	}
}
