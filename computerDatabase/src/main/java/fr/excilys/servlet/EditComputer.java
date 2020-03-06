package fr.excilys.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.excilys.mapper.MapperComputer;
import fr.excilys.mapper.FormatServletRequest;
import fr.excilys.model.Company;
import fr.excilys.model.Computer;
import fr.excilys.services.ServicesCompany;
import fr.excilys.services.ServicesComputer;

public class EditComputer extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String EDITCOMPUTER = "/WEB-INF/views/editComputer.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int computerId = 0;

		if (request.getParameter("computerid") != null)
			computerId = Integer.parseInt(request.getParameter("computerid"));

		Computer computer = ServicesComputer.computerSelectForUpdate(computerId);

		List<Company> companyList = ServicesCompany.companyList();

		request.setAttribute("computer", computer);
		request.setAttribute("companyList", companyList);
		this.getServletContext().getRequestDispatcher(EDITCOMPUTER).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {

		Boolean Success = false;
		
		Computer newcomputer = MapperComputer.getInstance().getComputerFromResponse(request);
		
		if(FormatServletRequest.checkCompany(newcomputer.getCompany().getId()))
			Success = ServicesComputer.computerUpdate(newcomputer);
		else 
			newcomputer.setName("Unknown");
		
		request.setAttribute("UpdateComputerName", newcomputer.getName());
		request.setAttribute("Success", Success);
		this.getServletContext().getRequestDispatcher(EDITCOMPUTER).forward(request, response);
	}
}
