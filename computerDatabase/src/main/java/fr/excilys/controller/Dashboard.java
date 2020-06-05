package fr.excilys.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.excilys.services.ServicesComputer;
import fr.excilys.mapper.PaginationDashboard;
import fr.excilys.model.Computer;
import fr.excilys.model.Pagination;

@Controller
@RequestMapping(value = "/dashboard")
public class Dashboard {
	
	public ServicesComputer serviceComputer;
	
	public Dashboard (ServicesComputer serviceComputer) {
		this.serviceComputer = serviceComputer;
	}

	@GetMapping
	public ModelAndView doGet(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
							  @RequestParam(value = "range", required = false, defaultValue = "10") int range,
							  @RequestParam(value = "search", required = false) String search,
							  @RequestParam(value = "order", required = false) String order,
							  @RequestParam(value = "sort", required = false) String sort) {

		if ("ascchange".equals(sort))
			sort = "desc";
		else if ("descchange".equals(sort))
			sort = "asc";
		else if ("change".equals(sort))
			sort = "asc";

		return getListRequest(page, range, search, order, sort);
	}

	@PostMapping
	public ModelAndView doPost(@RequestParam(value = "selection", required = false) String selection) {

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
