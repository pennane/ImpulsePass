package model.schedule;

import model.kide.KideAppEvent;

public class TaskConvertter {
	public static Task toSalesStartingTask(KideAppEvent e) {
		return new Task("sales start: " + e.getName(), () -> System.out.println(e.getName() + " myynti alkaa pian :D"),
				e.getDateSalesFrom().minusMinutes(30));
	}

	public static Task toEventStartingTask(KideAppEvent e) {
		return new Task("event start: " + e.getName(),
				() -> System.out.println(e.getName() + " tapahtuma alkaa huomenna :D"),
				e.getDateActualFrom().minusDays(1));
	}
}
