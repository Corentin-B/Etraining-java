package fr.excilys.mapper;

public class FromatPrepareQuerry {

	private final static String SQL_COMPUTER_NAME = "computer.name";
	private final static String SQL_COMPUTER_INTRODUCED = "computer.introduced";
	private final static String SQL_COMPUTER_DISCONTINUED = "computer.discontinued";
	private final static String SQL_COMPUTER_COMPANYID = "computer.company_id";

	private final static String SQL_ASC = "ASC";
	private final static String SQL_DESC = "DESC";

	public static String checkOrderByValue(String value) {

		if (value.equals("name"))
			return SQL_COMPUTER_NAME;
		else if (value.equals("introduced"))
			return SQL_COMPUTER_INTRODUCED;
		else if (value.equals("discontinued"))
			return SQL_COMPUTER_DISCONTINUED;
		else if (value.equals("company_id"))
			return SQL_COMPUTER_COMPANYID;
		else
			return null;
	}

	public static String checkOrderSuffix(String value) {

		if (value.equals("asc"))
			return SQL_ASC;
		else if (value.equals("desc")) {
			return SQL_DESC;
		} else
			return null;
	}
}
