package fr.excilys.mapper;

import java.util.Arrays;
import java.util.List;

import fr.excilys.services.ServicesComputer;

public class Pagination {

	public static List<Integer> pagingValues(int page, int range) {

		int numberComputer = ServicesComputer.computerGetNumber();
		int lastPage = (int) Math.ceil(numberComputer * 1.0 / range);
		int prevPage;
		
		if (page - 1 < 1) {
			prevPage = 1;
		} else {
			prevPage = page - 1;
		}

		int nextPage;
		if (page + 1 > lastPage) {
			nextPage = lastPage;
		} else {
			nextPage = page + 1;
		}

		int incrementPage = 1;
		int incrementLastPage = 5;

		if (page - 2 < 2) {
			incrementPage = 1;
		} else if (page + 2 > lastPage) {
			incrementPage = lastPage - 4;
		} else {
			incrementPage = page - 2;
		}
		
		incrementLastPage = incrementPage + 4;
		return Arrays.asList(prevPage, nextPage, incrementPage, incrementLastPage, numberComputer);
	}
}
