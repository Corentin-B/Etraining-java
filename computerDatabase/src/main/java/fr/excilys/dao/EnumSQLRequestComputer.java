package fr.excilys.dao;

enum EnumSQLRequestComputer {

	INSERT_NEWCOMPUTER		 ("INSERT INTO computer(name, introduced, discontinued, company_id) "
					  		 +"VALUES (?, ?, ?, ?);"),
	
	DELETE_COMPUTER 		 ("DELETE FROM computer " 
		 					 +"WHERE id = ?;"),

	UPDATE_COMPUTER 		 ("UPDATE computer "
							 +"SET name = ?, introduced = ?, discontinued = ?, company_id = ? "
							 +"WHERE id = ?;"),

	SELECT_NUMBERCOMPUTER 	 ("SELECT COUNT(*) "
						  	 +"FROM computer"),

	SELECT_NUMBERSEARCH		 ("SELECT COUNT(*) "
							 +"FROM computer "
							 +"WHERE UPPER(computer.name) LIKE ?"),

	SELECT_ONECOMPUTER 		 ("SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name "
							 +"FROM computer " 
							 +"LEFT JOIN company ON computer.company_id = company.id " 
							 +"WHERE computer.id = ?;"),

	SELECT_SEARCHCOMPUTER 	 ("SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name "
						  	 +"FROM computer " 
						  	 +"LEFT JOIN company ON computer.company_id = company.id " 
						  	 +"WHERE UPPER(computer.name) LIKE ? "
							 +"ORDER BY "
							 +"LIMIT ?, ?;"),

	SELECT_ALLCOMPUTER 		 ("SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name "
							 +"FROM computer " 
							 +"LEFT JOIN company ON company_id = company.id " 
							 +"LIMIT ?, ?;"),

	SELECT_ALLCOMPUTER_ORDER ("SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name "
							 +"FROM computer " 
							 +"LEFT JOIN company ON company_id = company.id "
							 +"ORDER BY "
							 +"LIMIT ?, ?;");
	
	private String message;
	
	public String getMessage() {
		return message;
	}

	private EnumSQLRequestComputer(String message) {
		this.message = message;
	}
}
