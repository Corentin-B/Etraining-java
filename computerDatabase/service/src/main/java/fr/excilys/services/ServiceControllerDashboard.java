package fr.excilys.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import fr.excilys.mapper.PaginationDashboard;
import fr.excilys.model.Computer;
import fr.excilys.model.DoGetParameter;
import fr.excilys.model.Pagination;

@Service
public class ServiceControllerDashboard {
	
	private ServicesComputer serviceComputer;
	
	public ServiceControllerDashboard (ServicesComputer serviceComputer) {
		this.serviceComputer = serviceComputer;
	}
	public ModelAndView getRequest(DoGetParameter parameterObject) {
		
		int pageNumber = requestParameter(parameterObject.getPage(), 1);
		int rangeNumber = requestParameter(parameterObject.getRange(), 10);
		
		if ("ascchange".equals(parameterObject.getSort()))
			parameterObject.setSort("desc");
		else if ("descchange".equals(parameterObject.getSort()))
			parameterObject.setSort("asc");
		else if ("change".equals(parameterObject.getSort()))
			parameterObject.setSort("asc");

		return getListRequest(pageNumber, rangeNumber, parameterObject.getSearch(), parameterObject.getOrder(), parameterObject.getSort());
	}
	
	public ModelAndView deleteRequest(String selection) {

		if (selection != null && !selection.isBlank()) {
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
		List<Computer> computerList = new ArrayList<Computer>();
		int setSqlPage = (page - 1);

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
		modelandview.addObject("search", search);
		modelandview.addObject("computerList", computerList);

		return modelandview;
	}
	

	private int requestParameter(String parameter, int defaultvalue) {
	
		if (parameter != null && !parameter.isBlank()) {
			return Integer.parseInt(parameter);
		} else {
			return defaultvalue;
		}
	}
}
