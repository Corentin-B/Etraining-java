package fr.excilys.mapper;

import fr.excilys.model.Pagination;

public class PaginationDashboard {

	public static Pagination pagingValues(int page, int range, int numberComputer) {

		int lastPage = (int) Math.ceil(numberComputer * 1.0 / range);
		int setSqlPage = (page - 1) * range;
		
		Pagination pagination = new Pagination(numberComputer, 0, 0, 1, 5, setSqlPage);
		
		if (page - 1 < 1)
			pagination.setPrevPage(1);
		else
			pagination.setPrevPage(page - 1);
		
		if (page + 1 > lastPage)
			pagination.setNextPage(lastPage);
		else
			pagination.setNextPage(page + 1);

		if (page - 2 < 2)
			pagination.setIncrementPage(1);
		else if (page + 2 > lastPage)
			pagination.setIncrementPage(lastPage - 4);
		else
			pagination.setIncrementPage(page - 2);
		
		pagination.setIncrementLastPage(pagination.getIncrementPage() + 4);
		
		if(lastPage < 5) {
			pagination.setIncrementPage(1);
			pagination.setIncrementLastPage(lastPage);
		}
		return pagination;
	}
}
