package fr.excilys.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import fr.excilys.mapper.PaginationDashboard;
import fr.excilys.model.Computer;
import fr.excilys.model.Pagination;

@Service
public class ServiceControllerDashboard {
	
	public ServicesComputer serviceComputer;
	
	public ServiceControllerDashboard (ServicesComputer serviceComputer) {
		this.serviceComputer = serviceComputer;
	}

	public ModelAndView getRequest(int page, int range, String search, String order, String sort) {
		
		if ("ascchange".equals(sort))
			sort = "desc";
		else if ("descchange".equals(sort))
			sort = "asc";
		else if ("change".equals(sort))
			sort = "asc";

		return getListRequest(page, range, search, order, sort);
	}
	
	public ModelAndView postRequest(String selection) {

		if (selection != null) {
			String[] computerDelete = selection.split(",");

			for (String selectComputer : computerDelete) {
				serviceComputer.computerRemove(Integer.parseInt(selectComputer));
			}
		}
		ModelAndView modelandview = new ModelAndView("redirect:/dashboard");
		return modelandview;
	}

	private ModelAndView getListRequest(int page, int range, String search, String order, String sort) {

		int numberComputer;
		List<Computer> computerList = new ArrayList<>();
		int setSqlPage = (page - 1) * range;

		if (search != null && !search.isEmpty()) {
			computerList = serviceComputer.computerSearchList(search, setSqlPage, range, order, sort);
			numberComputer = serviceComputer.computerGetNumberSearch(search);
		} else {
			computerList = serviceComputer.computerList(setSqlPage, range, order, sort);
			numberComputer = serviceComputer.computerGetNumber();
		}
		Pagination pagination = PaginationDashboard.pagingValues(page, range, numberComputer);
				
		ModelAndView modelandview = new ModelAndView();
		modelandview.addObject("prevPage", pagination.getPrevPage());
		modelandview.addObject("nextPage", pagination.getNextPage());
		modelandview.addObject("incrementPage", pagination.getIncrementPage());
		modelandview.addObject("incrementLastPage", pagination.getIncrementLastPage());
		modelandview.addObject("numberComputer", pagination.getNumberComputer());
		modelandview.addObject("order", order);
		modelandview.addObject("sort", sort);
		modelandview.addObject("range", range);
		modelandview.addObject("computerList", computerList);

		return modelandview;
	}
}
