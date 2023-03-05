package model.database.mongo;

import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.ZonedDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.kide.KideAppApi;
import model.kide.KideAppEvent;

public class MongoTest {
	KideAppApi api;
	Mongo db;
	ZonedDateTime frozenDate = ZonedDateTime.now();

	public KideAppEvent createMockEvent(String id, String name) {
		KideAppEvent e = new KideAppEvent();
		e.setId(id);
		e.setName(name);
		e.setDateActualFrom(frozenDate);

		return e;

	}

	@BeforeEach
	void resetDb() throws Exception {
		Mongo.INSTANCE.resetAndUseTestDatabase();
		db = Mongo.INSTANCE;
	}

	@Test
	public void insertEventsTest() {
		KideAppEvent event = createMockEvent("123", "test");
		db.insertEvents(List.of(event));
		var latest = db.fetchLatest();
		assertTrue(latest.isPresent(), "failed to save or fetch");
		var saved = latest.get().getEvents().get(0);
		assertTrue(saved.getId().equals(event.getId()), "ids dont match");
		assertTrue(saved.getDateActualFrom().getDayOfMonth() == event.getDateActualFrom().getDayOfMonth(),
				"dates dont match");
	}

	@Test
	public void insertUserSavedEventTest() {
		KideAppEvent event = createMockEvent("123", "test");

		var before = db.savedEventExists(event);
		assertFalse(before);

		db.insertUserSavedEvent(event);

		var after = db.savedEventExists(event);
		assertTrue(after);
	}

	@Test
	public void savedEventExistsTest() {
		KideAppEvent event = createMockEvent("123", "test");

		var before = db.savedEventExists(event);
		assertFalse(before);

		db.insertUserSavedEvent(event);

		var after = db.savedEventExists(event);
		assertTrue(after);
	}

	@Test
	public void updateEventTest() {
		KideAppEvent event = createMockEvent("123", "test");
		db.insertUserSavedEvent(event);
		KideAppEvent detailsToUpdate = createMockEvent("123", null);
		detailsToUpdate.setAvailability(69);
		db.updateEvent(detailsToUpdate);
		List<KideAppEvent> saved = db.fetchUserSavedEvents();
		KideAppEvent found = null;
		for (KideAppEvent e : saved) {
			if (e.getId().equals(event.getId())) {
				found = e;
				break;
			}
		}
		assertNotEquals(null, found);
		assertTrue(found.getName().equals(event.getName()));
		assertTrue(found.getAvailability().equals(detailsToUpdate.getAvailability()));
	}

	@Test
	public void deleteEventTest() {
		KideAppEvent event = createMockEvent("123", "test");
		db.insertUserSavedEvent(event);
		db.deleteEvent(event);
		var result = db.savedEventExists(event);
		assertFalse(result);
	}

	@Test
	public void fetchUserSavedEventsTest() {
		KideAppEvent event1 = createMockEvent("123", "test1");
		KideAppEvent event2 = createMockEvent("321", "test2");
		db.insertUserSavedEvent(event1);
		db.insertUserSavedEvent(event2);
		var result = db.fetchUserSavedEvents();
		assertTrue(result.size() == 2);

		var hasEvent1 = false;
		for (KideAppEvent e : result) {
			if (e.getId().equals(event1.getId())) {
				hasEvent1 = true;
				break;
			}
		}
		assertTrue(hasEvent1);

		var hasEvent2 = false;
		for (KideAppEvent e : result) {
			if (e.getId().equals(event2.getId())) {
				hasEvent2 = true;
				break;
			}
		}

		assertTrue(hasEvent2);
	}

	@Test
	public void fetchAllProjectedDataPointsTest() {
		KideAppEvent event1 = createMockEvent("123", "test1");
		event1.setAvailability(69);
		KideAppEvent event2 = createMockEvent("321", "test2");
		event2.setAvailability(69);

		db.insertEvents(List.of(event1, event2));
		var projection = fields(include("id"), include("events._id"), include("events.availability"));
		var result = db.fetchAllProjectedDataPoints(projection);
		assertTrue(result.size() == 1);
		assertNull(result.get(0).getEvents().get(0).getName());
		assertTrue(result.get(0).getEvents().get(0).getAvailability() == 69);
	}

	@Test
	public void fetchLatestTest() {
		KideAppEvent event = createMockEvent("123", "test1");
		db.insertEvents(List.of(event));
		var result = db.fetchLatest();
		assertTrue(result.isPresent());
		assertTrue(result.get().getEvents().get(0).getId().equals("123"));
	}
}
