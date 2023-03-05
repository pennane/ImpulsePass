package kide;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.kide.KideAppApi;
import model.kide.KideAppEvent;

class KideAppApiTest {
	KideAppApi api;

	@BeforeEach
	void setup() {
		api = new KideAppApi();
	}

	@Test
	@DisplayName("It should fetch events from kide.app api")
	void productsTest() {
		Optional<List<KideAppEvent>> optionalEvents = api.fetchEvents();
		assertTrue(optionalEvents.isPresent());
		assertFalse(optionalEvents.get().isEmpty());
	}

}
