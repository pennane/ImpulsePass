package view.layout.statistics;

import java.util.ArrayList;
import java.util.List;

import view.layout.statistics.datastrategy.ByMedianPrice;
import view.layout.statistics.datastrategy.CountsByDateActualFrom;
import view.layout.statistics.datastrategy.CountsByDateCreated;
import view.layout.statistics.datastrategy.CountsByDateSalesFrom;
import view.layout.statistics.datastrategy.DateUtil;

public class KideChart {
	String title;
	List<KideChartEntryList> entryLists;

	public KideChart(String title) {
		this.entryLists = new ArrayList<>();
		this.title = title;
	}

	public KideChart addEntryList(KideChartEntryList entryList) {
		entryLists.add(entryList);
		return this;
	}

	public List<KideChartEntryList> getEntryLists() {
		return entryLists;
	}

	public void refresh() {
		entryLists.forEach(KideChartEntryList::refresh);
	}

	public String getTitle() {
		return title;
	}

	@Override
	public String toString() {
		return title;
	}

	public static List<KideChart> createCharts() {
		List<KideChart> charts = new ArrayList<>();
		KideChartEntryList dateCreatedByWeek = new KideChartEntryList("Events created",
				new CountsByDateCreated(DateUtil::toStartOfWeek));
		KideChartEntryList dateActualFromByWeek = new KideChartEntryList("Events happening",
				new CountsByDateActualFrom(DateUtil::toStartOfWeek));
		KideChartEntryList dateSalesFromByWeek = new KideChartEntryList("Event sales",
				new CountsByDateSalesFrom(DateUtil::toStartOfWeek));

		KideChart datesByWeek = new KideChart("Dates by week").addEntryList(dateCreatedByWeek)
				.addEntryList(dateActualFromByWeek).addEntryList(dateSalesFromByWeek);

		KideChartEntryList dateCreatedByMonth = new KideChartEntryList("Events created",
				new CountsByDateCreated(DateUtil::toStartOfMonth));
		KideChartEntryList dateActualFromByMonth = new KideChartEntryList("Events happening",
				new CountsByDateActualFrom(DateUtil::toStartOfMonth));
		KideChartEntryList dateSalesFromByMonth = new KideChartEntryList("Event sales",
				new CountsByDateSalesFrom(DateUtil::toStartOfMonth));

		KideChart datesByMonth = new KideChart("Dates by month").addEntryList(dateCreatedByMonth)
				.addEntryList(dateActualFromByMonth).addEntryList(dateSalesFromByMonth);

		KideChartEntryList dateCreatedByDay = new KideChartEntryList("Events created",
				new CountsByDateCreated(DateUtil::toStartOfDay));
		KideChartEntryList dateActualFromByDay = new KideChartEntryList("Events happening",
				new CountsByDateActualFrom(DateUtil::toStartOfDay));
		KideChartEntryList dateSalesFromByDay = new KideChartEntryList("Event sales",
				new CountsByDateSalesFrom(DateUtil::toStartOfDay));

		KideChart datesByDay = new KideChart("Dates by day").addEntryList(dateCreatedByDay)
				.addEntryList(dateActualFromByDay).addEntryList(dateSalesFromByDay);

		KideChartEntryList medianPriceByWeek = new KideChartEntryList("Euros",
				new ByMedianPrice(DateUtil::toStartOfWeek));

		KideChart medianEventPrice = new KideChart("Median event price by week").addEntryList(medianPriceByWeek);

		charts.add(datesByWeek);
		charts.add(datesByMonth);
		charts.add(datesByDay);
		charts.add(medianEventPrice);

		return charts;
	}

}
