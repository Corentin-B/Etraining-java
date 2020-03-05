package fr.excilys.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.excilys.services.ServicesComputer;
import fr.excilys.mapper.PaginationDashboard;
import fr.excilys.model.Computer;

public class Dashboard extends HttpServlet {

	private static final long serialVersionUID = 1L;
<<<<<<< HEAD

	int range = 10;
=======
>>>>>>> dev

	private static final String DASHBOARD = "/WEB-INF/views/dashboard.jsp";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int page = 1;
		int range = 10;
		String search = null;

		System.out.println("range = " + request.getParameter("range"));
		
		if (request.getParameter("range") != null && !request.getParameter("range").isBlank()) {
			range = Integer.parseInt(request.getParameter("range"));
			request.setAttribute("range", range);
		} else {
			request.setAttribute("range", range);
		}

		if (request.getParameter("page") != null && !request.getParameter("page").isBlank()) {
			page = Integer.parseInt(request.getParameter("page"));
			request.setAttribute("page", page);
		} else {
			request.setAttribute("page", page);
		}
		
		if (request.getParameter("search") != null && !request.getParameter("search").isBlank()) {
			search = request.getParameter("search");
			request.setAttribute("search", search);
		} else {
			request.setAttribute("search", search);
		}
		
		if (request.getParameter("search") != null) {
			getSearchRequest(request, response, page, range);

		} else {
			getListRequest(request, response, page, range);
		}
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

	private void getListRequest(HttpServletRequest request, HttpServletResponse response, int page, int range) {

		List<Integer> pagingValues = PaginationDashboard.pagingValues(page, range);

		int sqlPage = (page - 1) * range;
		List<Computer> computerList = ServicesComputer.computerList(sqlPage, range);

		request.setAttribute("prevPage", pagingValues.get(0));
		request.setAttribute("nextPage", pagingValues.get(1));
		request.setAttribute("incrementPage", pagingValues.get(2));
		request.setAttribute("incrementLastPage", pagingValues.get(3));
		request.setAttribute("numberComputer", pagingValues.get(4));
		request.setAttribute("computerList", computerList);
		try {
			this.getServletContext().getRequestDispatcher(DASHBOARD).forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void getSearchRequest(HttpServletRequest request, HttpServletResponse response, int page, int range) {
	
		String searchComputer = request.getParameter("search");
		
		
		List<Computer> computerList = ServicesComputer.computerSearchList(searchComputer, page, range);
		
		request.setAttribute("computerList", computerList);
		try {
			this.getServletContext().getRequestDispatcher(DASHBOARD).forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
