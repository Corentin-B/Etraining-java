package fr.excilys.dao;

public enum EnumSQLRequestCompany {

	DELETE_COMPANY ("DELETE "
				   +"FROM company "
			   	   +"WHERE company.id = ?;"),

	SELECT_ALLCOMPANY ("SELECT company.id, company.name "
			   		  +"FROM company;"),

	SELECT_ONECOMPANY ("SELECT company.id, company.name "
			   		  +"FROM company "
			   		  +"WHERE company.id = ?;");
	
	private String message;
	
	public String getMessage() {
		return message;
	}

	private EnumSQLRequestCompany(String message) {
		this.message = message;
	}
}
