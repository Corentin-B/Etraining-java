package fr.excilys.mapper;

import java.time.LocalDate;

import fr.excilys.model.Company;
import fr.excilys.services.ServicesCompany;

public class CheckFormat {

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

	public static boolean checkString(String value) {

		return !value.isBlank();
	}

	public static LocalDate checkDateFormatValueAndConvert(String value) {

		System.out.println(value);

		if (!value.isBlank()) {
			LocalDate valueDate = LocalDate.parse(value);

			if (valueDate.isBefore(LocalDate.parse("1970-01-01"))) {
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

		System.out.println(introduced);
		System.out.println(discontinued);
		
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

	public static boolean checkCompany(long companyId) {

		Company company = ServicesCompany.computerSelectForCheck((int) companyId);

		return company.getId() == companyId;
	}
}
