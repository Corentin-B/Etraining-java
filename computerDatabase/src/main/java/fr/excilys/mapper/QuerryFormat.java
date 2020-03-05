package fr.excilys.mapper;

public class QuerryFormat {

	private final static String SQL_COMPUTER_NAME = "computer.name";
	private final static String SQL_COMPUTER_INTRODUCED = "computer.introduced";
	private final static String SQL_COMPUTER_DISCONTINUED = "computer.discontinued";
	private final static String SQL_COMPUTER_COMPANYID = "computer.company_id";

	private final static String SQL_ASC = "ASC";
	private final static String SQL_DESC = "DESC";

	public static String checkOrderByValue(String value) {

		if (value == "name")
			return SQL_COMPUTER_NAME;
		else if (value == "introduced")
			return SQL_COMPUTER_INTRODUCED;
		else if (value == "discontinued")
			return SQL_COMPUTER_DISCONTINUED;
		else if (value == "company_id")
			return SQL_COMPUTER_COMPANYID;
		else
			return null;
	}

	public static String checkOrderSuffix(String value) {

		if (value == "asc")
			return SQL_ASC;
		else if (value == "desc") {
			return SQL_DESC;
		} else
			return null;
	}
}
