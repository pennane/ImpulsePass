package model;

public class KideAppEventDetails {
	private String dateActualFrom, dateActualUntil, dateSalesFrom, dateSalesUntil;
	private Boolean salesStarted, salesEnded;
	private int availability;

	public KideAppEventDetails() {

	}

	public String getDateActualFrom() {
		return dateActualFrom;
	}

	public String getDateActualUntil() {
		return dateActualUntil;
	}

	public String getDateSalesFrom() {
		return dateSalesFrom;
	}

	public String getDateSalesUntil() {
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

	public void setDateActualFrom(String date) {
		dateActualFrom = date;
	}

	public void setDateActualUntil(String date) {
		dateActualUntil = date;
	}

	public void setDateSalesFrom(String date) {
		dateSalesFrom = date;
	}

	public void setDateSalesUntil(String date) {
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
