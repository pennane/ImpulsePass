package database;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Filters.lte;
import static com.mongodb.client.model.Sorts.descending;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoWriteException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import config.Config;
import kide.KideAppEvent;

public enum Mongo {
	INSTANCE;

	MongoDatabase database;
	MongoCollection<EventsDataPoint> eventsCollection;
	MongoCollection<KideAppEvent> userSavedCollection;

	Mongo() {
		/// AAAAAAAAAAAAAA :D
		ConnectionString connectionString = new ConnectionString(Config.get("CLUSTER_URL"));
		CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
				fromProviders(new ZonedDateTimeCodecProvider(), PojoCodecProvider.builder().automatic(true).build()));
		MongoClientSettings clientSettings = MongoClientSettings.builder().applyConnectionString(connectionString)
				.codecRegistry(codecRegistry).build();
		MongoClient mongoClient = MongoClients.create(clientSettings);

		database = mongoClient.getDatabase(Config.get("DB_NAME"));
		eventsCollection = database.getCollection("events", EventsDataPoint.class);
		userSavedCollection = database.getCollection("userSavedEvents", KideAppEvent.class);
	}

	public void insertEvents(List<KideAppEvent> events) {
		EventsDataPoint dataPoint = new EventsDataPoint(events);
		if (fetchLatest().hashCode() != dataPoint.hashCode()) {
			eventsCollection.insertOne(dataPoint);
		}
	}

	public void insertUserSavedEvent(KideAppEvent event) {
		try {
			userSavedCollection.insertOne(event);
		} catch (MongoWriteException e) {
			e.printStackTrace();
		}
	}

	public Boolean savedEventExists(KideAppEvent e) {
		Bson query = new Document("_id", e.getId());
		FindIterable<KideAppEvent> result = userSavedCollection.find(query);
		return result.first() != null;
	}

	public void updateEvent(KideAppEvent event) {
		try {
			Bson filter = Filters.eq("_id", event.getId());
			userSavedCollection.findOneAndUpdate(filter, new Document("$set", event));
		} catch (MongoWriteException e) {
			e.printStackTrace();
		}
	}

	public void deleteEvent(KideAppEvent event) {
		try {
			Bson filter = Filters.eq("_id", event.getId());
			userSavedCollection.findOneAndDelete(filter);
		} catch (MongoWriteException e) {
			e.printStackTrace();
		}
	}

	public List<KideAppEvent> fetchUserSavedEvents() {
		List<KideAppEvent> results = new ArrayList<>();
		userSavedCollection.find().into(results);
		return results;
	}

	public List<EventsDataPoint> fetchDataPoints(ZonedDateTime startDate, ZonedDateTime endDate) {
		List<EventsDataPoint> results = new ArrayList<>();

		var filter = and(gte("date", startDate.toInstant()), lte("date", endDate.toInstant()));

		eventsCollection.find(filter).into(results);

		return results;

	}

	public Optional<EventsDataPoint> fetchLatest() {
		List<EventsDataPoint> results = new ArrayList<>();
		if (eventsCollection.countDocuments() > 0) {
			var latest = eventsCollection.find().sort(descending("date")).first();
			return Optional.of(latest);

		}
		return Optional.empty();
	}

}
