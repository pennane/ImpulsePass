package model.schedule;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.ZonedDateTime;

import org.junit.jupiter.api.Test;

import model.kide.KideAppEvent;

public class ScheduleTest {
	@Test
	void toSaleTaskTest() {
		KideAppEvent mockEvent = new KideAppEvent();
		mockEvent.setName("test");
		mockEvent.setDateSalesFrom(ZonedDateTime.now());
		Task task = TaskConvertter.toSalesStartingTask(mockEvent);
		assertTrue(task instanceof Task);
	}

	@Test
	void toEventTaskTest() {
		KideAppEvent mockEvent = new KideAppEvent();
		mockEvent.setName("test");
		mockEvent.setDateActualFrom(ZonedDateTime.now());
		var task = TaskConvertter.toEventStartingTask(mockEvent);
		assertTrue(task instanceof Task);
	}
}
