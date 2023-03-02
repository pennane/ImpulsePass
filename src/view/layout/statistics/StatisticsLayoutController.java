package view.layout.statistics;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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

	public LocalDateTime getMonthFromDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
		LocalDateTime dateTime = LocalDateTime.parse(date, formatter).withDayOfMonth(1).withHour(0).withMinute(0)
				.withSecond(0).withNano(0);
		return dateTime;
	}

	public void drawNewChart(List<Entry<LocalDateTime, Integer>> entries, String title, String name) {

		lineChart.setTitle(title);
		XYChart.Series<String, Integer> eventSeries = new Series<>();
		eventSeries.setName(name);

		for (Entry<LocalDateTime, Integer> entry : entries) {
			eventSeries.getData().add(new Data<String, Integer>(entry.getKey().toString(), entry.getValue()));
		}

		lineChart.getData().add(eventSeries);
	}

	public List<Entry<LocalDateTime, Integer>> createEntries() {
		LocalDate endDate = LocalDate.now();
		LocalDate startDate = LocalDate.now().minusDays(30);
		Date startDateObj = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date endDateObj = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

		List<EventsDataPoint> eventDataPoints = Mongo.INSTANCE.fetchDataPoints(startDateObj, endDateObj);
		List<KideAppEvent> events = eventDataPoints.get(0).getEvents();

		Map<LocalDateTime, Integer> map = new HashMap<>();

		for (KideAppEvent event : events) {
			LocalDateTime month = getMonthFromDate(event.getDateActualFrom());
			map.put(month, map.getOrDefault(month, 0) + 1);
		}

		return map.entrySet().stream().sorted((a, b) -> a.getKey().compareTo(b.getKey())).collect(Collectors.toList());

	}

}