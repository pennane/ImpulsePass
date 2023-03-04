package view.layout.statistics.datastrategy;

import java.time.ZonedDateTime;

import kide.KideAppEvent;

public interface IDateGetter {
	public ZonedDateTime get(KideAppEvent e);
}
