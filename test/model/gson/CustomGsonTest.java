package model.gson;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.ZonedDateTime;

import org.junit.jupiter.api.Test;

import model.CustomGson;

public class CustomGsonTest {
	private class Obu {
		ZonedDateTime time;

		public Obu(ZonedDateTime time) {
			this.time = time;
		}

		@SuppressWarnings("unused")
		public void setTime(ZonedDateTime time) {
			this.time = time;
		}

		public ZonedDateTime getTime() {
			return time;
		}
	}

	@Test
	void gsonParsesZonedDateTime() {
		var now = ZonedDateTime.now();
		var obu = new Obu(now);

		var json = CustomGson.INSTANCE.getGson().toJson(obu);
		var after = CustomGson.INSTANCE.getGson().fromJson(json, Obu.class);
		assertTrue(now.equals(after.getTime()));
	}
}
