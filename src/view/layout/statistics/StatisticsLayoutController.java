package view.layout.statistics;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import database.EventsDataPoint;
import database.Mongo;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import kide.KideAppEvent;
import view.Gui;
import view.ILayoutController;

public class StatisticsLayoutController implements ILayoutController {
	Gui gui;

	@FXML
	LineChart<String, Integer> lineChart;

	@Override
	public ILayoutController initialize(Gui gui) {
		this.gui = gui;

		drawNewChart(createEntries(), "title", "nimi");

		return this;
	}

	public ZonedDateTime getClampedDate(ZonedDateTime date) {
		return date.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
	}

	public void drawNewChart(List<Entry<ZonedDateTime, Integer>> entries, String title, String name) {

		lineChart.setTitle(title);
		XYChart.Series<String, Integer> eventSeries = new Series<>();
		eventSeries.setName(name);

		for (Entry<ZonedDateTime, Integer> entry : entries) {
			eventSeries.getData().add(new Data<String, Integer>(entry.getKey().toString(), entry.getValue()));
		}

		lineChart.getData().add(eventSeries);
	}

	public List<Entry<ZonedDateTime, Integer>> createEntries() {
		ZonedDateTime endDate = ZonedDateTime.now();
		ZonedDateTime startDate = ZonedDateTime.now().minusDays(30);

		List<EventsDataPoint> eventDataPoints = Mongo.INSTANCE.fetchDataPoints(startDate, endDate);
		List<KideAppEvent> events = eventDataPoints.get(0).getEvents();

		Map<ZonedDateTime, Integer> map = new HashMap<>();

		for (KideAppEvent event : events) {
			ZonedDateTime month = getClampedDate(event.getDateActualFrom());
			map.put(month, map.getOrDefault(month, 0) + 1);
		}

		return map.entrySet().stream().sorted((a, b) -> a.getKey().compareTo(b.getKey())).collect(Collectors.toList());

	}

}