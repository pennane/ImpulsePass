package view.layout.statistics;

import java.util.ArrayList;
import java.util.List;

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

}
