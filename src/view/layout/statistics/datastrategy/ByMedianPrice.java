package view.layout.statistics.datastrategy;

import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.bson.conversions.Bson;

import model.database.mongo.EventsDataPoint;
import model.database.mongo.Mongo;
import model.kide.KideAppEvent;
import view.layout.statistics.KideChartEntryList;

public class ByMedianPrice implements IEntryCreator {
	IDateClamper dateClamper;

	public ByMedianPrice(IDateClamper dateClamper) {
		this.dateClamper = dateClamper;
	}

	@Override
	public List<Entry<ZonedDateTime, Integer>> getEntries() {

		Bson projection = fields(include("id"), include("events._id"), include("events.dateActualFrom"),
				include("events.maxPrice.eur"), include("events.minPrice.eur"));

		List<EventsDataPoint> eventDataPoints = Mongo.INSTANCE.fetchAllProjectedDataPoints(projection);

		var groups = KideChartEntryList.groupDataPointsEvents(eventDataPoints, KideAppEvent::getDateActualFrom,
				dateClamper);

		return groups.entrySet().stream().sorted((a, b) -> a.getKey().compareTo(b.getKey())).map(entry -> {
			var map = entry.getValue();
			List<Integer> averages = new ArrayList<>();

			for (KideAppEvent event : map.values()) {
				var minPrice = event.getMinPrice();
				var maxPrice = event.getMaxPrice();

				if (minPrice == null || maxPrice == null) {
					continue;
				}

				var minPriceEur = minPrice.getEur();
				var maxPriceEur = maxPrice.getEur();

				if (minPriceEur == null || maxPriceEur == null) {
					continue;
				}
				var avg = (Integer.parseInt(minPriceEur) + Integer.parseInt(maxPriceEur)) / 200;

				averages.add(avg);
			}

			Collections.sort(averages);

			int median = averages.size() == 0 ? 0 : averages.get((int) (Math.floor(averages.size() / 2)));
			return Map.entry(entry.getKey(), median);
		}).collect(Collectors.toList());
	}

}
