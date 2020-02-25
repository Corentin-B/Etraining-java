package fr.excilys.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.excilys.services.ServicesComputer;
import fr.excilys.model.Computer;

public class Dashboard extends HttpServlet {

	public static final String DASHBOARD = "/WEB-INF/views/dashboard.jsp";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int range = 10;
		int page = 1;

		try {
			range = Integer.parseInt(request.getParameter("range"));

		} catch (NumberFormatException e) {
			range = 10;
		}

		try {
			page = Integer.parseInt(request.getParameter("page"));

		} catch (NumberFormatException e) {
			page = 1;
		}

		int sqlPage = --page * range;

		int numberComputer = ServicesComputer.computerGetNumber();

		System.out.println(sqlPage + "   " + range + "   " + page);

		List<Computer> computerList = ServicesComputer.computerList(sqlPage, range);

		request.setAttribute("numberComputer", numberComputer);
		request.setAttribute("computerList", computerList);
		this.getServletContext().getRequestDispatcher(DASHBOARD).forward(request, response);
	}
}
