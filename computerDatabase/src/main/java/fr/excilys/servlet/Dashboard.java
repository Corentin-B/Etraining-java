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

	int range = 10;

	public static final String DASHBOARD = "/WEB-INF/views/dashboard.jsp";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int page = 1;

		if (request.getParameter("range") != null) {
			range = Integer.parseInt(request.getParameter("range"));
		}

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		int numberComputer = ServicesComputer.computerGetNumber();
		int lastPage = (int) Math.ceil(numberComputer * 1.0 / range);

		int prevPage = page - 1;
		if (prevPage < 1)
			prevPage = 1;

		int nextPage = page + 1;
		if (nextPage > lastPage)
			nextPage = lastPage;

		int sqlPage = (page - 1) * range;
		int incrementPage = 1;
		int incrementLastPage = 5;

		if (page - 2 < 2)
			incrementPage = 1;
		else if (page + 2 > lastPage)
			incrementPage = lastPage - 4;
		else
			incrementPage = page - 2;

		incrementLastPage = incrementPage + 4;

		List<Computer> computerList = ServicesComputer.computerList(sqlPage, range);

		request.setAttribute("page", page);
		request.setAttribute("prevPage", prevPage);
		request.setAttribute("nextPage", nextPage);
		request.setAttribute("incrementPage", incrementPage);
		request.setAttribute("incrementLastPage", incrementLastPage);
		request.setAttribute("numberComputer", numberComputer);
		request.setAttribute("computerList", computerList);
		this.getServletContext().getRequestDispatcher(DASHBOARD).forward(request, response);
	}
}