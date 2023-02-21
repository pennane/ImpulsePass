package database;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Filters.lte;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import config.Config;
import kide.KideAppEvent;

public enum Mongo {
	INSTANCE;

	MongoDatabase database;
	MongoCollection<EventsDataPoint> eventsCollection;

	Mongo() {
		/// AAAAAAAAAAAAAA :D
		ConnectionString connectionString = new ConnectionString(Config.get("CLUSTER_URL"));
		CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
				fromProviders(PojoCodecProvider.builder().automatic(true).build()));
		MongoClientSettings clientSettings = MongoClientSettings.builder().applyConnectionString(connectionString)
				.codecRegistry(codecRegistry).build();
		MongoClient mongoClient = MongoClients.create(clientSettings);

		database = mongoClient.getDatabase(Config.get("DB_NAME"));
		eventsCollection = database.getCollection("events", EventsDataPoint.class);
	}

	public void insertEvents(List<KideAppEvent> events) {
		eventsCollection.insertOne(new EventsDataPoint(events));
	}

	public List<EventsDataPoint> fetchDataPoints(Date startDate, Date endDate) {
		List<EventsDataPoint> results = new ArrayList<>();

		var filter = and(gte("date", startDate), lte("date", endDate));

		eventsCollection.find(filter).into(results);

		return results;

	}
}
