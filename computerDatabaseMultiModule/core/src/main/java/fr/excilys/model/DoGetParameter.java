package fr.excilys.model;

public class DoGetParameter {
	private String page;
	private String range;
	private String search;
	private String order;
	private String sort;

	public DoGetParameter(String page, String range, String search, String order, String sort) {
		this.page = page;
		this.range = range;
		this.search = search;
		this.order = order;
		this.sort = sort;
	}

	public String getPage() {
		return page;
	}

	public String getRange() {
		return range;
	}

	public String getSearch() {
		return search;
	}

	public String getOrder() {
		return order;
	}

	public String getSort() {
		return sort;
	}
	
	public void setSort(String sort) {
		this.sort = sort;
	}
}