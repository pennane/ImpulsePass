package model;

import java.time.ZonedDateTime;

public class KideAppEventDetails {
	private ZonedDateTime dateActualFrom;
	private ZonedDateTime dateActualUntil;
	private ZonedDateTime dateSalesFrom;
	private ZonedDateTime dateSalesUntil;
	private Boolean salesStarted;
	private Boolean salesEnded;
	private int availability;

	public KideAppEventDetails() {

	}

	public ZonedDateTime getDateActualFrom() {
		return dateActualFrom;
	}

	public ZonedDateTime getDateActualUntil() {
		return dateActualUntil;
	}

	public ZonedDateTime getDateSalesFrom() {
		return dateSalesFrom;
	}

	public ZonedDateTime getDateSalesUntil() {
		return dateSalesUntil;
	}

	public Boolean getSalesStarted() {
		return salesStarted;
	}

	public Boolean getSalesEnded() {
		return salesEnded;
	}

	public int getAvailability() {
		return availability;
	}

	public void setDateActualFrom(ZonedDateTime date) {
		dateActualFrom = date;
	}

	public void setDateActualUntil(ZonedDateTime date) {
		dateActualUntil = date;
	}

	public void setDateSalesFrom(ZonedDateTime date) {
		dateSalesFrom = date;
	}

	public void setDateSalesUntil(ZonedDateTime date) {
		dateSalesUntil = date;
	}

	public void setSalesStarted(Boolean started) {
		salesStarted = started;
	}

	public void setSalesEnded(Boolean ended) {
		salesEnded = ended;
	}

	public void setAvailability(int av) {
		availability = av;
	}

}
