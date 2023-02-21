package database;

import java.util.List;

import org.bson.Document;

import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import config.Config;
import kide.KideAppEvent;

public enum Mongo {
	INSTANCE;

	MongoClient mongoClient;
	MongoDatabase database;
	Gson gson;
	MongoCollection<Document> eventsCollection;

	Mongo() {
		mongoClient = MongoClients.create(Config.get("CLUSTER_URL"));
		database = mongoClient.getDatabase(Config.get("DB_NAME"));
		gson = new Gson();
		eventsCollection = database.getCollection("events");
	}

	public void insertEvents(List<KideAppEvent> events) {
		Document document = Document.parse(gson.toJson(new EventsDataPoint(events)));
		eventsCollection.insertOne(document);
	}
}
