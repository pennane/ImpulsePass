package view.layout.statistics.datastrategy;

import java.time.ZonedDateTime;

public interface IDateClamper {
	public ZonedDateTime clamp(ZonedDateTime date);
}
