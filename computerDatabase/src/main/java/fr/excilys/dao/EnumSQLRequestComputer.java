package fr.excilys.dao;

enum EnumSQLRequestComputer {

	SELECT_ONECOMPUTER 		 ("SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name "
							 +"FROM computer " 
							 +"LEFT JOIN company ON computer.company_id = company.id " 
							 +"WHERE computer.id = :idComputer;"),

	SELECT_SEARCHCOMPUTER 	 ("SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name "
						  	 +"FROM computer " 
						  	 +"LEFT JOIN company ON computer.company_id = company.id " 
						  	 +"WHERE UPPER(computer.name) LIKE :nameComputer "
							 +"ORDER BY "
							 +"LIMIT :numberPage, :range;"),

	SELECT_ALLCOMPUTER 		 ("SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name "
							 +"FROM computer " 
							 +"LEFT JOIN company ON company_id = company.id " 
							 +"LIMIT :numberPage, :range;"),

	SELECT_ALLCOMPUTER_ORDER ("SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name "
							 +"FROM computer " 
							 +"LEFT JOIN company ON company_id = company.id "
							 +"ORDER BY "
							 +"LIMIT :numberPage, :range;"),
	
	SELECT_NUMBERCOMPUTER 	 ("SELECT COUNT(*) "
							 +"FROM computer"),

	SELECT_NUMBERSEARCH		 ("SELECT COUNT(*) "
							 +"FROM computer "
							 +"WHERE UPPER(computer.name) LIKE :nameComputer"),
	
	INSERT_NEWCOMPUTER		 ("INSERT INTO computer(name, introduced, discontinued, company_id) "
							 +"VALUES (:nameComputer, :introducedComputer, :discontinuedComputer, :idCompanyComputer);"),

	DELETE_COMPUTER 		 ("DELETE FROM computer " 
				 			 +"WHERE id = :idComputer;"),
	
	UPDATE_COMPUTER 		 ("UPDATE computer "
				 			 +"SET name = :nameComputer, introduced = :introducedComputer, "
				 			 +"discontinued = :discontinuedComputer, company_id = :idCompanyComputer "
				 			 +"WHERE id = :idComputer;");

	
	private String message;
	
	protected String getMessage() {
		return message;
	}

	private EnumSQLRequestComputer(String message) {
		this.message = message;
	}
}
