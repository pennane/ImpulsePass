package view.layout.statistics;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import view.Gui;
import view.ILayoutController;

public class StatisticsLayoutController implements ILayoutController {
	Gui gui;

	@FXML
	ListView<KideChart> listViewCharts;

	@FXML
	LineChart<String, Integer> lineChart;

	@FXML
	Button buttonRefreshChart;

	private KideChart currentChart;

	@Override
	public ILayoutController initialize(Gui gui) {
		this.gui = gui;
		System.out.println();
		List<KideChart> charts = KideChart.createCharts();

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
		currentChart = chart;
		lineChart.getData().clear();
		lineChart.setTitle(chart.getTitle());

		for (KideChartEntryList entryList : chart.getEntryLists()) {
			lineChart.getData().add(entryList.getDataSeries());
		}

	}

	public void refreshChart() {
		if (currentChart != null) {
			currentChart.refresh();
		}
	}

}