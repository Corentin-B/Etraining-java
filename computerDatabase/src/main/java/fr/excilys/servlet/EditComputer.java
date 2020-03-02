package fr.excilys.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.excilys.mapper.MapperComputer;
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int computerId = 0;
		
		if (request.getParameter("computerId") != null) {
			computerId = Integer.parseInt(request.getParameter("computerId"));
		}
		System.out.println(request.getParameter("computerId"));
		String computerName = request.getParameter("computerName");
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		String companyId = request.getParameter("companyId");
		
		Computer computer = requestToComputer(computerId, computerName, introduced, discontinued, companyId);

		ServicesComputer.computerUpdate(computer);
		
		request.setAttribute("UpdateComputerName", computer.getName());		
		//this.getServletContext().getRequestDispatcher(EDITCOMPUTER).forward(request, response);
		
		response.sendRedirect(request.getContextPath()+"/ashboard");
	}
	
	private static Computer requestToComputer (int computerId, String computerName, String introduced, String discontinued, String companyId) {
		
		Computer newcomputer = MapperComputer.getInstance().getComputerFromPost(computerId, computerName, introduced, discontinued, companyId);

		Computer computer = ServicesComputer.computerSelectForUpdate(computerId);

		if(newcomputer.getName() != null && newcomputer.getName() != computer.getName()) {
			computer.setName(newcomputer.getName());
		}
		if(newcomputer.getIntroduced() != null && newcomputer.getIntroduced() != computer.getIntroduced()) {
			computer.setIntroduced(newcomputer.getIntroduced());
		}
		if(newcomputer.getDiscontinued() != null && newcomputer.getDiscontinued() != computer.getDiscontinued()) {
			computer.setDiscontinued(newcomputer.getDiscontinued());
		}
		if(newcomputer.getCompany().getId() != 0.0f && newcomputer.getCompany().getId() != computer.getCompany().getId()) {
			computer.setCompany(new Company.Builder()
										   .setId(newcomputer.getCompany()
												  		 	 .getId())
										   .build());
		}
		return computer;
	}
}
