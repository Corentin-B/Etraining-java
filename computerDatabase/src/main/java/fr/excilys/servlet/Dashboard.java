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

		int numberComputer = ServicesComputer.computerGetNumber();
		int lastPage = numberComputer / range;

		System.out.println(page);
		
		int prevPage = page - 1;
		if(prevPage < 1)
			prevPage = 1;
		int nextPage = page + 1;
		if(nextPage > lastPage)
			nextPage = lastPage - 1;
		
		int sqlPage = (page - 1) * range;
		
		System.out.println(page+" "+prevPage+" "+nextPage+" "+sqlPage);

		
		List<Computer> computerList = ServicesComputer.computerList(sqlPage, range);

		request.setAttribute("prevPage", prevPage);
		request.setAttribute("nextPage", nextPage);
		request.setAttribute("numberComputer", numberComputer);
		request.setAttribute("computerList", computerList);
		this.getServletContext().getRequestDispatcher(DASHBOARD).forward(request, response);
	}
}
