package fr.excilys.mapper;

import java.time.LocalDate;

public class FormatServletRequest {

	public static int checkIntFormatAndConvert(String value) {

		if (value != null && !value.isBlank()) {
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

	public static LocalDate checkDateFormatValueAndConvert(String value) {

		if (!value.isBlank()) {
			LocalDate valueDate = LocalDate.parse(value);

			if (valueDate.isBefore(LocalDate.parse("1970-01-02"))) {
				return null;
			} else if (valueDate.isAfter(LocalDate.parse("2038-01-19"))) {
				return null;
			} else {
				return valueDate;
			}
		} else {
			return null;
		}
	}

	public static LocalDate checkIntroducedDiscontinued(LocalDate introduced, LocalDate discontinued) {

		if (discontinued != null) {
			if (introduced.isBefore(discontinued)) {
				return introduced;
			} else {
				return null;
			}
		} else {
			return introduced;
		}
	}
}
