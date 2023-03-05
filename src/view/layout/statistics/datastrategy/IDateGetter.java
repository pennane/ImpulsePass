package view.layout.statistics.datastrategy;

import java.time.ZonedDateTime;

import model.kide.KideAppEvent;

public interface IDateGetter {
	public ZonedDateTime get(KideAppEvent e);
}
