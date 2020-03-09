package fr.excilys.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.excilys.services.ServicesComputer;
import fr.excilys.mapper.PaginationDashboard;
import fr.excilys.model.Computer;
import fr.excilys.model.Pagination;

public class Dashboard extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String DASHBOARD = "/WEB-INF/views/dashboard.jsp";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int page = requestParameter(request, "page", 1);
		int range = requestParameter(request, "range", 10);
		String search = requestParameter(request, "search", null);
		String order = requestParameter(request, "order", null);
		String sort = requestParameter(request, "sort", null);

		if ("asc".equals(sort))
			sort = "desc";
		else
			sort = "asc";

		getListRequest(request, response, page, range, search, order, sort);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameter("selection") != null) {
			String[] computerDelete = request.getParameter("selection").split(",");

			for (String selectComputer : computerDelete) {
				ServicesComputer.computerRemove(Integer.parseInt(selectComputer));
			}
			this.doGet(request, response);
		}
	}

	private void getListRequest(HttpServletRequest request, HttpServletResponse response, int page, int range, String search, String order, String sort) throws ServletException, IOException {

		int numberComputer;
		List<Computer> computerList = new ArrayList<>();
		int setSqlPage = (page - 1) * range;

		if (search != null && !search.isEmpty()) {
			computerList = ServicesComputer.computerSearchList(search, setSqlPage, range, order, sort);
			numberComputer = ServicesComputer.computerGetNumberSearch(search);
		} else {
			computerList = ServicesComputer.computerList(setSqlPage, range);
			numberComputer = ServicesComputer.computerGetNumber();
		}
		Pagination pagination = PaginationDashboard.pagingValues(page, range, numberComputer);
		
		request.setAttribute("prevPage", pagination.getPrevPage());
		request.setAttribute("nextPage", pagination.getNextPage());
		request.setAttribute("incrementPage", pagination.getIncrementPage());
		request.setAttribute("incrementLastPage", pagination.getIncrementLastPage());
		request.setAttribute("numberComputer", pagination.getNumberComputer());
		request.setAttribute("order", order);
		request.setAttribute("sort", sort);
		request.setAttribute("computerList", computerList);
				this.getServletContext().getRequestDispatcher(DASHBOARD).forward(request, response);
	}

	private String requestParameter(HttpServletRequest request, String parameter, String defaultvalue) {

		if (request.getParameter(parameter) != null && !request.getParameter(parameter).isBlank()) {
			String value = request.getParameter(parameter);
			request.setAttribute(parameter, value);
			return value;
		} else {
			request.setAttribute(parameter, defaultvalue);
			return defaultvalue;
		}
	}

	private int requestParameter(HttpServletRequest request, String parameter, int defaultvalue) {

		if (request.getParameter(parameter) != null && !request.getParameter(parameter).isBlank()) {
			int value = Integer.parseInt(request.getParameter(parameter));
			request.setAttribute(parameter, value);
			return value;
		} else {
			request.setAttribute(parameter, defaultvalue);
			return defaultvalue;
		}
	}
}
