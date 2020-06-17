package fr.excilys.mapper;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

public class MapperDateTimeMidNight {

	public static Timestamp getDatetimeToTimestamp(LocalDate computerDate) {

		if (computerDate != null)
			return Timestamp.valueOf(computerDate.atTime(LocalTime.MIDNIGHT));
		else
			return null;
	}
}
