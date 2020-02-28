package fr.excilys.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.excilys.model.Company;
import fr.excilys.services.ServicesCompany;

public class EditComputer extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private static final String EDITCOMPUTER = "/WEB-INF/views/editComputer.jsp";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Company> companyList = ServicesCompany.companyList();
		
		request.setAttribute("companyList", companyList);

		this.getServletContext().getRequestDispatcher(EDITCOMPUTER).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher(EDITCOMPUTER).forward(request, response);
	}

}
