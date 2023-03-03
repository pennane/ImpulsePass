package view.layout.statistics.datastrategy;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import database.EventsDataPoint;
import database.Mongo;
import view.layout.statistics.KideChart;

public class CurrentEventStartingDays implements IChartEntriesStrategy {

	@Override
	public List<Entry<ZonedDateTime, Integer>> getEntries() {
		Optional<EventsDataPoint> optionalDataPoint = Mongo.INSTANCE.fetchLatest();

		if (optionalDataPoint.isEmpty()) {
			return new ArrayList<>();
		}

		Map<ZonedDateTime, Integer> entryMap = new HashMap<>();

		optionalDataPoint.get().getEvents().stream().forEach(e -> {
			ZonedDateTime day = KideChart.clampToStartOfDay(e.getDateActualFrom());
			entryMap.put(day, entryMap.getOrDefault(day, 0) + 1);
		});

		return entryMap.entrySet().stream().sorted((a, b) -> a.getKey().compareTo(b.getKey()))
				.collect(Collectors.toList());
	}

}
