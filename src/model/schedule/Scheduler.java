package model.schedule;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public enum Scheduler {
	INSTANCE;

	ScheduledExecutorService executorService;

	private Scheduler() {
		executorService = Executors.newSingleThreadScheduledExecutor();
	}

	public void schedule(Task task) {
		var until = ZonedDateTime.now().until(task.getDate(), ChronoUnit.MINUTES);

		executorService.schedule(task.getTask(), until, TimeUnit.MINUTES);

		System.out.println("Scheduled task '" + task.getDescription() + "' " + until + " minutes into future");
	}

}
