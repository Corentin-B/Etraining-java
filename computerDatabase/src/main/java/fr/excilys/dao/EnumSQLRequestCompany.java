package fr.excilys.dao;

enum EnumSQLRequestCompany {

	DELETE_COMPANY 	  		  ("DELETE "
							  +"FROM company "
							  +"WHERE company.id = :idCompany;"),

	DELETE_COMPANYCOMPUTER 	  ("DELETE "
					  		  +"FROM computer "
					  		  +"WHERE computer.company_id = :idCompany;"),

	
	SELECT_ALLCOMPANY 		  ("SELECT company.id, company.name "
			   		  		  +"FROM company;"),

	SELECT_ONECOMPANY 		  ("SELECT company.id, company.name "
			   		  		  +"FROM company "
			   		  		  +"WHERE company.id = :idCompany;");
	
	private String message;
	
	protected String getMessage() {
		return message;
	}

	private EnumSQLRequestCompany(String message) {
		this.message = message;
	}
}
