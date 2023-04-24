package view.layout.statistics.datastrategy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;

import org.junit.jupiter.api.Test;;

public class DateUtilTest {
	@Test
	public void dateToZonedDateTime() {
		Date now = new Date();
		ZonedDateTime zoned = DateUtil.dateToZonedDateTime(now);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		assertEquals(formatter.format(Date.from(zoned.toInstant())), formatter.format(now));
	}

	@Test
	public void toStartOfMonth() {
		ZonedDateTime now = ZonedDateTime.now();
		ZonedDateTime clamped = DateUtil.toStartOfMonth(now);
		assertEquals(clamped.getNano(), 0);
		assertEquals(clamped.getSecond(), 0);
		assertEquals(clamped.getMinute(), 0);
		assertEquals(clamped.getHour(), 0);
		assertEquals(clamped.getDayOfMonth(), 1);
		assertEquals(clamped.getYear(), now.getYear());
	}

	@Test
	public void toStartOfWeek() {
		ZonedDateTime now = ZonedDateTime.now();
		ZonedDateTime clamped = DateUtil.toStartOfWeek(now);
		var day = (int) (Math.min(Math.floor(now.getDayOfMonth() / 7) * 7 + 1, 22));
		assertEquals(clamped.getNano(), 0);
		assertEquals(clamped.getSecond(), 0);
		assertEquals(clamped.getMinute(), 0);
		assertEquals(clamped.getHour(), 0);
		assertEquals(clamped.getDayOfMonth(), day);
		assertEquals(clamped.getYear(), now.getYear());
	}

	@Test
	public void toStartOfDay() {
		ZonedDateTime now = ZonedDateTime.now();
		ZonedDateTime clamped = DateUtil.toStartOfDay(now);
		assertTrue(clamped.getNano() == 0);
		assertTrue(clamped.getSecond() == 0);
		assertTrue(clamped.getMinute() == 0);
		assertTrue(clamped.getHour() == 0);
		assertTrue(clamped.getDayOfYear() == now.getDayOfYear());
		assertTrue(clamped.getYear() == now.getYear());
	}
}
