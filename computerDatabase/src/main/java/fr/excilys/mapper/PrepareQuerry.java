package fr.excilys.mapper;

public class PrepareQuerry {

	private final static String SQL_COMPUTER_NAME = "computer.name";
	private final static String SQL_COMPUTER_INTRODUCED = "computer.introduced";
	private final static String SQL_COMPUTER_DISCONTINUED = "computer.discontinued";
	private final static String SQL_COMPUTER_COMPANYID = "computer.company_id";

	private final static String SQL_ASC = "ASC";
	private final static String SQL_DESC = "DESC";

	public static String checkOrderByValue(String value) {

		if ("name".equals(value))
			return SQL_COMPUTER_NAME;
		else if ("introduced".equals(value))
			return SQL_COMPUTER_INTRODUCED;
		else if ("discontinued".equals(value))
			return SQL_COMPUTER_DISCONTINUED;
		else if ("company_id".equals(value))
			return SQL_COMPUTER_COMPANYID;
		else
			return null;
	}

	public static String checkOrderSuffix(String value) {

		if ("asc".equals(value))
			return SQL_ASC;
		else if ("desc".equals(value)) {
			return SQL_DESC;
		} else
			return null;
	}
}