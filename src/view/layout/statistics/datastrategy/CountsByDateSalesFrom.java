package view.layout.statistics.datastrategy;

import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.bson.conversions.Bson;

import model.database.mongo.EventsDataPoint;
import model.database.mongo.Mongo;
import model.kide.KideAppEvent;
import view.layout.statistics.KideChartEntryList;

public class CountsByDateSalesFrom implements IEntryCreator {
	IDateClamper dateClamper;

	public CountsByDateSalesFrom(IDateClamper dateClamper) {
		this.dateClamper = dateClamper;
	}

	@Override
	public List<Entry<ZonedDateTime, Integer>> getEntries() {

		Bson projection = fields(include("id"), include("events._id"), include("events.dateSalesFrom"));

		List<EventsDataPoint> eventDataPoints = Mongo.INSTANCE.fetchAllProjectedDataPoints(projection);

		var groups = KideChartEntryList.groupDataPointsEvents(eventDataPoints, KideAppEvent::getDateSalesFrom,
				dateClamper);

		return groups.entrySet().stream().sorted((a, b) -> a.getKey().compareTo(b.getKey()))
				.map(e -> Map.entry(e.getKey(), e.getValue().size())).collect(Collectors.toList());
	}

}
