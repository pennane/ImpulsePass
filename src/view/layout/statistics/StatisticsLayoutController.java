package view.layout.statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.ListView;
import view.Gui;
import view.ILayoutController;
import view.layout.statistics.datastrategy.CurrentEventStartingDays;
import view.layout.statistics.datastrategy.PublishedEventCountsHistory;

public class StatisticsLayoutController implements ILayoutController {
	Gui gui;

	@FXML
	ListView<KideChart> listViewCharts;

	@FXML
	LineChart<String, Integer> lineChart;

	@Override
	public ILayoutController initialize(Gui gui) {
		this.gui = gui;
		System.out.println();
		List<KideChart> charts = new ArrayList<>();

		charts.add(new KideChart("Nykyhetken tapahtumapäivät", "tapahtumien määrä", new CurrentEventStartingDays()));
		charts.add(
				new KideChart("tapahtumat / kuukausi", "tapahtumien enimmäismäärä", new PublishedEventCountsHistory()));

		lineChart.setCreateSymbols(false);
		lineChart.setAnimated(false);

		listViewCharts.getItems().addAll(charts);

		listViewCharts.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				drawChart(newValue);
			} else {
				listViewCharts.getSelectionModel().clearSelection();
			}
		});

		listViewCharts.getSelectionModel().select(0);

		return this;
	}

	public void drawChart(KideChart chart) {
		lineChart.getData().clear();
		lineChart.setTitle(chart.getTitle());

		XYChart.Series<String, Integer> eventSeries = new Series<>();

		eventSeries.setName(chart.getName());
		eventSeries.getData().addAll(chart.getEntries().stream()
				.map(e -> new Data<String, Integer>(e.getKey().toString(), e.getValue())).collect(Collectors.toList()));

		lineChart.getData().add(eventSeries);
	}

}