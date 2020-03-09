package fr.excilys.model;

public class Pagination {

	private int numberComputer;
	private int prevPage;
	private int nextPage;
	private int incrementPage;
	private int incrementLastPage;
	private int sqlPage;
	
	public Pagination() {
		this.incrementPage = 1;
		this.incrementLastPage = 5;
	}
	
	public int getNumberComputer() {
		return numberComputer;
	}
	public void setNumberComputer(int numberComputer) {
		this.numberComputer = numberComputer;
	}
	public int getPrevPage() {
		return prevPage;
	}
	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getIncrementPage() {
		return incrementPage;
	}
	public void setIncrementPage(int incrementPage) {
		this.incrementPage = incrementPage;
	}
	public int getIncrementLastPage() {
		return incrementLastPage;
	}
	public void setIncrementLastPage(int incrementLastPage) {
		this.incrementLastPage = incrementLastPage;
	}
	public int getSqlPage() {
		return sqlPage;
	}
	public void setSqlPage(int sqlPage) {
		this.sqlPage = sqlPage;
	}
}
