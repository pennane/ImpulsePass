package view.layout.statistics.datastrategy;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import database.EventsDataPoint;
import database.Mongo;
import view.layout.statistics.KideChart;

public class PublishedEventCountsHistory implements IChartEntriesStrategy {

	@Override
	public List<Entry<ZonedDateTime, Integer>> getEntries() {
		ZonedDateTime endDate = ZonedDateTime.now();
		ZonedDateTime startDate = ZonedDateTime.now().minusDays(30);

		List<EventsDataPoint> eventDataPoints = Mongo.INSTANCE.fetchDataPoints(startDate, endDate);

		Map<ZonedDateTime, Integer> obungaMap = new HashMap<>();

		for (EventsDataPoint dataPoint : eventDataPoints) {
			ZonedDateTime dataPointDate = ZonedDateTime.ofInstant(dataPoint.getDate().toInstant(),
					ZoneId.systemDefault());
			ZonedDateTime month = KideChart.clampToStartOfMonth(dataPointDate);
			Integer eventCount = dataPoint.getEvents().size();
			obungaMap.merge(month, eventCount, (highest, current) -> current > highest ? current : highest);
		}

		return obungaMap.entrySet().stream().sorted((a, b) -> a.getKey().compareTo(b.getKey()))
				.collect(Collectors.toList());
	}

}
