package view.layout.statistics;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map.Entry;

import view.layout.statistics.datastrategy.IChartEntriesStrategy;

public class KideChart implements IChartEntriesStrategy {
	String name;
	String title;
	IChartEntriesStrategy dataStrategy;
	List<Entry<ZonedDateTime, Integer>> entries;

	public KideChart(String name, String title, IChartEntriesStrategy dataStrategy) {
		this.name = name;
		this.title = title;
		this.dataStrategy = dataStrategy;
	}

	@Override
	public String toString() {
		return name;
	}

	/**
	 * Make sure entries are created only once
	 */
	@Override
	public List<Entry<ZonedDateTime, Integer>> getEntries() {
		if (entries == null) {
			entries = dataStrategy.getEntries();
		}
		return entries;
	}

	public static ZonedDateTime clampToStartOfMonth(ZonedDateTime date) {
		return date.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
	}

	public static ZonedDateTime clampToStartOfDay(ZonedDateTime date) {
		return date.withHour(0).withMinute(0).withSecond(0).withNano(0);
	}

	public String getName() {
		return name;
	}

	public String getTitle() {
		return title;
	}

}
