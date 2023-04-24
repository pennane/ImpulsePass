package view.layout.statistics;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import view.Gui;
import view.ILayoutController;
import view.Intl;
import view.layout.statistics.datastrategy.ByMedianPrice;
import view.layout.statistics.datastrategy.CountsByDateActualFrom;
import view.layout.statistics.datastrategy.CountsByDateCreated;
import view.layout.statistics.datastrategy.CountsByDateSalesFrom;
import view.layout.statistics.datastrategy.DateUtil;

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
		List<KideChart> charts = createCharts();

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

	private List<KideChart> createCharts() {
		List<KideChart> charts = new ArrayList<>();
		KideChartEntryList dateCreatedByWeek = new KideChartEntryList(
				Intl.INSTANCE.get("statistics.chart.entryList.eventsCreated"),
				new CountsByDateCreated(DateUtil::toStartOfWeek));
		KideChartEntryList dateActualFromByWeek = new KideChartEntryList(
				Intl.INSTANCE.get("statistics.chart.entryList.eventsHappening"),
				new CountsByDateActualFrom(DateUtil::toStartOfWeek));
		KideChartEntryList dateSalesFromByWeek = new KideChartEntryList(
				Intl.INSTANCE.get("statistics.chart.entryList.eventSales"),
				new CountsByDateSalesFrom(DateUtil::toStartOfWeek));

		KideChart datesByWeek = new KideChart(Intl.INSTANCE.get("statistics.chart.title.datesByWeek"))
				.addEntryList(dateCreatedByWeek).addEntryList(dateActualFromByWeek).addEntryList(dateSalesFromByWeek);

		KideChartEntryList dateCreatedByMonth = new KideChartEntryList(
				Intl.INSTANCE.get("statistics.chart.entryList.eventsCreated"),
				new CountsByDateCreated(DateUtil::toStartOfMonth));
		KideChartEntryList dateActualFromByMonth = new KideChartEntryList(
				Intl.INSTANCE.get("statistics.chart.entryList.eventsHappening"),
				new CountsByDateActualFrom(DateUtil::toStartOfMonth));
		KideChartEntryList dateSalesFromByMonth = new KideChartEntryList(
				Intl.INSTANCE.get("statistics.chart.entryList.eventSales"),
				new CountsByDateSalesFrom(DateUtil::toStartOfMonth));

		KideChart datesByMonth = new KideChart(Intl.INSTANCE.get("statistics.chart.title.datesByMonth"))
				.addEntryList(dateCreatedByMonth).addEntryList(dateActualFromByMonth)
				.addEntryList(dateSalesFromByMonth);

		KideChartEntryList dateCreatedByDay = new KideChartEntryList(
				Intl.INSTANCE.get("statistics.chart.entryList.eventsCreated"),
				new CountsByDateCreated(DateUtil::toStartOfDay));
		KideChartEntryList dateActualFromByDay = new KideChartEntryList(
				Intl.INSTANCE.get("statistics.chart.entryList.eventsHappening"),
				new CountsByDateActualFrom(DateUtil::toStartOfDay));
		KideChartEntryList dateSalesFromByDay = new KideChartEntryList(
				Intl.INSTANCE.get("statistics.chart.entryList.eventSales"),
				new CountsByDateSalesFrom(DateUtil::toStartOfDay));

		KideChart datesByDay = new KideChart(Intl.INSTANCE.get("statistics.chart.title.datesByDay"))
				.addEntryList(dateCreatedByDay).addEntryList(dateActualFromByDay).addEntryList(dateSalesFromByDay);

		KideChartEntryList medianPriceByWeek = new KideChartEntryList(
				Intl.INSTANCE.get("statistics.chart.entryList.euros"), new ByMedianPrice(DateUtil::toStartOfWeek));

		KideChart medianEventPrice = new KideChart(Intl.INSTANCE.get("statistics.chart.title.medianEventPrice"))
				.addEntryList(medianPriceByWeek);

		charts.add(datesByWeek);
		charts.add(datesByMonth);
		charts.add(datesByDay);
		charts.add(medianEventPrice);

		return charts;
	}

}