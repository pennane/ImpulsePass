package model.schedule;

import java.time.ZonedDateTime;

public class Task {
	private String description;
	private Runnable task;
	private ZonedDateTime date;

	public Task(String description, Runnable task, ZonedDateTime date) {
		this.description = description;
		this.task = task;
		this.date = date;
	}

	public void run() {
		task.run();
	}

	public String getDescription() {
		return description;
	}

	public Runnable getTask() {
		return task;
	}

	public ZonedDateTime getDate() {
		return date;
	}

}
