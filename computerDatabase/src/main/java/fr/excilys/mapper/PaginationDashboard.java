package fr.excilys.mapper;

import fr.excilys.model.Pagination;

public class PaginationDashboard {

	public static Pagination pagingValues(int page, int range, int numberComputer) {

		int lastPage = (int) Math.ceil(numberComputer * 1.0 / range);
		int setSqlPage = (page - 1) * range;
		
		Pagination pagination = new Pagination();
		pagination.setSqlPage(setSqlPage);
		pagination.setNumberComputer(numberComputer);
		
		pagination.setPrevPage(findPrevPage(page));
		
		pagination.setNextPage(findNextPage(page, lastPage));

		pagination.setIncrementPage(findIncrementPage (page, lastPage));
		
		pagination.setIncrementLastPage(pagination.getIncrementPage() + 4);
		
		if(lastPage < 5) {
			pagination.setIncrementPage(1);
			pagination.setIncrementLastPage(lastPage);
		}
		return pagination;
	}
	
	private static int findPrevPage (int currentPage) {
		if (currentPage <= 1)
			return 1;
		else
			return currentPage - 1;
	}
	
	private static int findNextPage (int currentPage, int lastPage) {
		if (currentPage >= lastPage)
			return lastPage;
		else
			return currentPage + 1;
	}
	
	private static int findIncrementPage (int currentPage, int lastPage) {
		if (currentPage - 2 < 2)
			return 1;
		else if (currentPage + 2 > lastPage)
			return lastPage - 4;
		else
			return currentPage - 2;
	}
}
