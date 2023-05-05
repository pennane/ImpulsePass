package model;

import java.io.IOException;
import java.time.ZonedDateTime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/**
 * Kustomoitu GSON parseri Tukee ZonedDateTimen parsimista JSON muodosta Javaan
 * ja takaisin.
 */
public enum CustomGson {
	INSTANCE;

	private final Gson gson = new GsonBuilder()
			.registerTypeAdapter(ZonedDateTime.class, new TypeAdapter<ZonedDateTime>() {
				@Override
				public void write(JsonWriter out, ZonedDateTime value) throws IOException {
					out.value(value.toString());
				}

				@Override
				public ZonedDateTime read(JsonReader in) throws IOException {
					return ZonedDateTime.parse(in.nextString());
				}
			}).enableComplexMapKeySerialization().create();

	public Gson getGson() {
		return gson;
	}
}
