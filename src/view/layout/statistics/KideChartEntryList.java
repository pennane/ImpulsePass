package view.layout.statistics;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import database.EventsDataPoint;
import javafx.scene.chart.XYChart;
import kide.KideAppEvent;
import view.layout.statistics.datastrategy.IDateClamper;
import view.layout.statistics.datastrategy.IDateGetter;
import view.layout.statistics.datastrategy.IEntryCreator;

public class KideChartEntryList implements IEntryCreator {
	private IEntryCreator strategy;
	private List<Entry<ZonedDateTime, Integer>> entries;
	private XYChart.Series<String, Integer> series;
	private String name;

	public KideChartEntryList(String name, IEntryCreator strategy) {
		this.strategy = strategy;
		this.name = name;
	}

	/**
	 * Make sure entries are created only once
	 */
	@Override
	public List<Entry<ZonedDateTime, Integer>> getEntries() {
		if (entries == null) {
			entries = strategy.getEntries();
		}
		return entries;
	}

	public XYChart.Series<String, Integer> getDataSeries() {
		if (series == null) {
			series = new XYChart.Series<>();
			series.setName(getName());
			for (Entry<ZonedDateTime, Integer> entry : getEntries()) {
				series.getData().add(entryToChartData(entry));
			}
		}
		return series;
	}

	public void refresh() {
		entries = strategy.getEntries();
		series.getData().clear();
		for (Entry<ZonedDateTime, Integer> entry : getEntries()) {
			series.getData().add(entryToChartData(entry));
		}
	}

	public String getName() {
		return name;
	}

	/**
	 * Group events from multiple data points between some time intervals
	 */
	public static Map<ZonedDateTime, Map<String, KideAppEvent>> groupDataPointsEvents(List<EventsDataPoint> points,
			IDateGetter dateGetter, IDateClamper dateClamper) {
		Map<ZonedDateTime, Map<String, KideAppEvent>> result = new HashMap<>();
		for (EventsDataPoint point : points) {
			for (KideAppEvent event : point.getEvents()) {
				var date = dateGetter.get(event);
				var group = dateClamper.clamp(date);

				result.putIfAbsent(group, new HashMap<>());

				var innerMap = result.get(group);

				innerMap.putIfAbsent(event.getId(), event);
			}
		}
		return result;
	}

	public static XYChart.Data<String, Integer> entryToChartData(Entry<ZonedDateTime, Integer> entry) {
		return new XYChart.Data<>(entry.getKey().toString(), entry.getValue());
	}

}
