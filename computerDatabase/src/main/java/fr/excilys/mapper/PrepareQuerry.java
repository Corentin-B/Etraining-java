package fr.excilys.mapper;

public class PrepareQuerry {

	private final static String SQL_COMPUTER_NAME = "computer.name ";
	private final static String SQL_COMPUTER_INTRODUCED = "computer.introduced ";
	private final static String SQL_COMPUTER_DISCONTINUED = "computer.discontinued ";
	private final static String SQL_COMPUTER_COMPANYID = "computer.company_id ";

	private final static String SQL_ASC = "ASC ";
	private final static String SQL_DESC = "DESC ";
	
	public static String checkOrderValueSuffix(String order, String sort, String querry) {

		StringBuilder prepredQuerry = new StringBuilder(querry);
				
		prepredQuerry = querryOrderBy(order, prepredQuerry);
		prepredQuerry = querrySort(sort, prepredQuerry);
		
		System.out.println(prepredQuerry);
		return prepredQuerry.toString();
	}
	
	private static StringBuilder querryOrderBy(String order, StringBuilder querry) {
		
		System.out.println(order);
		
		if ("introduced".equals(order))
			querry.insert(querry.length() - 11, SQL_COMPUTER_INTRODUCED);
		else if ("discontinued".equals(order))
			querry.insert(querry.length() - 11, SQL_COMPUTER_DISCONTINUED);
		else if ("company_id".equals(order))
			querry.insert(querry.length() - 11, SQL_COMPUTER_COMPANYID);
		else
			querry.insert(querry.length() - 11, SQL_COMPUTER_NAME);
			
		return querry;
	}
	
	private static StringBuilder querrySort(String sort, StringBuilder querry) {
		
		if ("desc".equals(sort))
			querry.insert(querry.length() - 11, SQL_DESC);
		else
			querry.insert(querry.length() - 11, SQL_ASC);
			
		return querry;
	}
}
