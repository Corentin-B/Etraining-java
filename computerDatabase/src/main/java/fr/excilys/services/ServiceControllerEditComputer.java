package fr.excilys.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import fr.excilys.mapper.FormatServletRequest;
import fr.excilys.mapper.MapperComputer;
import fr.excilys.model.Company;
import fr.excilys.model.Computer;

@Service
public class ServiceControllerEditComputer {

	public ServicesComputer serviceComputer;

	public ServiceControllerEditComputer(ServicesComputer serviceComputer) {
		this.serviceComputer = serviceComputer;
	}

	public ModelAndView getRequest(int computerId) {

		Computer computer = serviceComputer.computerSelectForUpdate(computerId);

		List<Company> companyList = ServicesCompany.companyList();

		ModelAndView modelandview = new ModelAndView();
		modelandview.addObject("computer", computer);
		modelandview.addObject("companyList", companyList);

		return modelandview;
	}

	public ModelAndView postRequest(String computerId, String computerName, String introduced, String discontinued,
			String companyId) {

		Boolean success = false;

		Computer newcomputer = MapperComputer.getComputerFromResponse(computerId, computerName, introduced,
				discontinued, companyId);

		if (FormatServletRequest.checkCompany(newcomputer.getCompany().getId())) {
			if (serviceComputer.computerUpdate(newcomputer) != 0)
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
