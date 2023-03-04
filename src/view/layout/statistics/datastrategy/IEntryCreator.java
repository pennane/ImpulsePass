package view.layout.statistics.datastrategy;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map.Entry;

public interface IEntryCreator {
	public List<Entry<ZonedDateTime, Integer>> getEntries();
}
