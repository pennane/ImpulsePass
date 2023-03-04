package view.layout.statistics.datastrategy;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class DateUtil {
	public static ZonedDateTime dateToZonedDateTime(Date date) {
		return ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}

	public static ZonedDateTime toStartOfMonth(ZonedDateTime date) {
		return ZonedDateTime.of(date.getYear(), date.getMonth().getValue(), 1, 0, 0, 0, 0, ZoneId.systemDefault());
	}

	public static ZonedDateTime toStartOfWeek(ZonedDateTime date) {
		var day = (int) (Math.min(Math.floor(date.getDayOfMonth() / 7) * 7 + 1, 22));
		return ZonedDateTime.of(date.getYear(), date.getMonth().getValue(), day, 0, 0, 0, 0, ZoneId.systemDefault());
	}

	public static ZonedDateTime toStartOfDay(ZonedDateTime date) {
		return ZonedDateTime.of(date.getYear(), date.getMonth().getValue(), date.getDayOfMonth(), 0, 0, 0, 0,
				ZoneId.systemDefault());
	}
}
