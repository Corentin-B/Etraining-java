package fr.excilys.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.excilys.mapper.MapperComputer;
import fr.excilys.mapper.CheckFormat;
import fr.excilys.model.Company;
import fr.excilys.model.Computer;
import fr.excilys.services.ServicesCompany;
import fr.excilys.services.ServicesComputer;

public class EditComputer extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String EDITCOMPUTER = "/WEB-INF/views/editComputer.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int computerId = 0;

		if (request.getParameter("computerid") != null) {
			computerId = Integer.parseInt(request.getParameter("computerid"));
		}

		Computer computer = ServicesComputer.computerSelectForUpdate(computerId);

		List<Company> companyList = ServicesCompany.companyList();

		request.setAttribute("computer", computer);
		request.setAttribute("companyList", companyList);
		this.getServletContext().getRequestDispatcher(EDITCOMPUTER).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {

		int computerId = CheckFormat.checkIntFormatAndConvert(request.getParameter("computerId"));
		String computerName = request.getParameter("computerName");
		LocalDate introduced = CheckFormat.checkDateFormatValueAndConvert(request.getParameter("introduced"));
		LocalDate discontinued = CheckFormat.checkDateFormatValueAndConvert(request.getParameter("discontinued"));
		int companyId = CheckFormat.checkIntFormatAndConvert(request.getParameter("companyId"));
		introduced = CheckFormat.checkIntroducedDiscontinued(introduced, discontinued);
		
		Computer newcomputer = MapperComputer.getInstance().getComputerFromPost(computerId, computerName, introduced, discontinued, companyId);
		
		Computer computer = requestToComputer(newcomputer);

		Boolean Success = ServicesComputer.computerUpdate(computer);

		request.setAttribute("UpdateComputerName", computer.getName());
		request.setAttribute("Success", Success);
		this.getServletContext().getRequestDispatcher(EDITCOMPUTER).forward(request, response);
	}

	private static Computer requestToComputer(Computer newcomputer) {


		Computer computer = ServicesComputer.computerSelectForUpdate((int) newcomputer.getId());

		if (newcomputer.getName() != null && newcomputer.getName() != computer.getName()) {
			computer.setName(newcomputer.getName());
		}
		computer.setIntroduced(newcomputer.getIntroduced());
		computer.setDiscontinued(newcomputer.getDiscontinued());
		computer.setCompany(new Company.Builder()
									   .setId(newcomputer.getCompany()
											   			 .getId())
									   .build());
		return computer;
	}
}
