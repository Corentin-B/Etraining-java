package fr.excilys.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.excilys.services.ServicesComputer;
import fr.excilys.mapper.Pagination;
import fr.excilys.model.Computer;

public class Dashboard extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String DASHBOARD = "/WEB-INF/views/dashboard.jsp";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int page = 1;
		int range = 10;

		if (request.getParameter("range") != null) {
			range = Integer.parseInt(request.getParameter("range"));
		}

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		List<Integer> pagingValues = Pagination.pagingValues(page, range);

		int sqlPage = (page - 1) * range;
		List<Computer> computerList = ServicesComputer.computerList(sqlPage, range);

		request.setAttribute("page", page);
		request.setAttribute("range", range);
		request.setAttribute("prevPage", pagingValues.get(0));
		request.setAttribute("nextPage", pagingValues.get(1));
		request.setAttribute("incrementPage", pagingValues.get(2));
		request.setAttribute("incrementLastPage", pagingValues.get(3));
		request.setAttribute("numberComputer", pagingValues.get(4));
		request.setAttribute("computerList", computerList);
		this.getServletContext().getRequestDispatcher(DASHBOARD).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String[] computerDelete = request.getParameter("selection").split(",");

		for (String selectComputer : computerDelete) {
			ServicesComputer.computerRemove(Integer.parseInt(selectComputer));
		}
		this.doGet(request, response);
	}
}
