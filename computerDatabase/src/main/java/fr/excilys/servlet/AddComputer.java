package fr.excilys.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.excilys.model.Company;
import fr.excilys.model.Computer;
import fr.excilys.services.ServicesCompany;
import fr.excilys.services.ServicesComputer;
import fr.excilys.mapper.MapperComputer;

public class AddComputer extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String ADDCOMPUTER = "/WEB-INF/views/addComputer.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Company> companyList = ServicesCompany.companyList();
						
		request.setAttribute("companyList", companyList);
		this.getServletContext().getRequestDispatcher(ADDCOMPUTER).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Boolean Success = false;
		
		Computer newcomputer = MapperComputer.getComputerFromResponse(request);
		
		if(!newcomputer.getName().isBlank()) {
			if(ServicesComputer.computerAdd(newcomputer) != 0)
				Success = true;
		} else {
			newcomputer.setName("Unknown");
		}
		
		request.setAttribute("newComputerName", newcomputer.getName());
		request.setAttribute("Success", Success);
		this.getServletContext().getRequestDispatcher(ADDCOMPUTER).forward(request, response);
	}
}
