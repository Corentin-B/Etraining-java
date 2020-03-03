package fr.excilys.mapper;

import java.time.LocalDate;

public class CheckFormat {

	private final static String REGEX_DATEFORMAT = "([0-9]{4}-[0-9]{2}-[0-9]{2})";
	private final static String REGEX_ANYNUMBER = "([0-9]{1,})";

	public static int checkIntFormatAndConvert(String value) {

		if (value.matches(REGEX_ANYNUMBER)) {
			try {
				return Integer.parseInt(value);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return -1;
			}
		} else {
			return -1;
		}
	}
	
	public static boolean checkStringFormat(String value) {

		return !value.isBlank();
	}

	public static LocalDate checkDateFormatValueAndConvert(String value) {
		
		if (value.matches(REGEX_DATEFORMAT)) {
			LocalDate valueDate =  LocalDate.parse(value);
			
			if(valueDate.isBefore(LocalDate.parse("1970-01-01"))) {
				return null;
			} else if (valueDate.isAfter(LocalDate.parse("2038-01-19"))){
				return null;
			} else {
				return valueDate;
			}
		} else {
			return null;
		}
	}
	
	public static LocalDate checkIntroducedDiscontinued(LocalDate introduced, LocalDate discontinued) {
		
		if (introduced.isBefore(discontinued)) {
			return introduced;
		} else {
			return null;
		}
	}
}
