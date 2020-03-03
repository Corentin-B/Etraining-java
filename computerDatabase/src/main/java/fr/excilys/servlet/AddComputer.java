package fr.excilys.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.excilys.model.Company;
import fr.excilys.model.Computer;
import fr.excilys.services.ServicesCompany;
import fr.excilys.services.ServicesComputer;
import fr.excilys.mapper.CheckFormat;
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

		String computerName = request.getParameter("computerName");
		LocalDate introduced = CheckFormat.checkDateFormatValueAndConvert(request.getParameter("introduced"));
		LocalDate discontinued = CheckFormat.checkDateFormatValueAndConvert(request.getParameter("discontinued"));
		int companyId = CheckFormat.checkIntFormatAndConvert(request.getParameter("companyId"));
		introduced = CheckFormat.checkIntroducedDiscontinued(introduced, discontinued);

		Computer newcomputer = MapperComputer.getInstance().getComputerFromPost(computerName, introduced, discontinued, companyId);
		
		Boolean Success = ServicesComputer.computerAdd(newcomputer);
		
		request.setAttribute("newComputerName", newcomputer.getName());
		request.setAttribute("Success", Success);
		this.getServletContext().getRequestDispatcher(ADDCOMPUTER).forward(request, response);
	}
}
