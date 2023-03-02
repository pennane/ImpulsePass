package kide;

import model.EventDetailed;
import model.EventPrice;

/*
 * 
 * AUTOMATICALLY GENERATED MODEL
 * 
 */
public class KideAppEvent {
	private String dateSalesFrom;

	private Boolean isActual;

	private String companyName;

	private Integer availability;

	private Boolean hasInventoryItems;

	private String dateCreated;

	private String mediaFilename;

	private Boolean salesPaused;

	private String id;

	private String place;

	private Integer productType;

	private String dateActualFrom;

	private String dateActualUntil;

	private Boolean isLong;

	private Boolean isFavorited;

	private String timeUntilActual;

	private Boolean salesStarted;

	private String dateSalesUntil;

	private Boolean salesEnded;

	private Integer favoritedTimes;

	private EventPrice minPrice;

	private EventPrice maxPrice;

	private Boolean hasFreeInventoryItems;

	private Boolean salesOngoing;

	private String companyMediaFilename;

	private String pricingInformation;

	private String name;

	private String timeUntilSalesStart;

	private String time;

	private String datePublishFrom;

	public String getDateSalesFrom() {
		return dateSalesFrom;
	}

	public void updateData(EventDetailed e) {
		this.availability = e.getAvailability();
		this.dateActualFrom = e.getDateActualFrom();
		this.dateActualUntil = e.getDateActualUntil();
		this.dateSalesFrom = e.getDateSalesFrom();
		this.dateSalesUntil = e.getDateSalesUntil();
		this.salesEnded = e.getSalesEnded();
		this.salesStarted = e.getSalesStarted();
	}

	public void setDateSalesFrom(String dateSalesFrom) {
		this.dateSalesFrom = dateSalesFrom;
	}

	public Boolean getIsActual() {
		return isActual;
	}

	public void setIsActual(Boolean isActual) {
		this.isActual = isActual;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getAvailability() {
		return availability;
	}

	public void setAvailability(Integer availability) {
		this.availability = availability;
	}

	public Boolean getHasInventoryItems() {
		return hasInventoryItems;
	}

	public void setHasInventoryItems(Boolean hasInventoryItems) {
		this.hasInventoryItems = hasInventoryItems;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getMediaFilename() {
		return mediaFilename;
	}

	public void setMediaFilename(String mediaFilename) {
		this.mediaFilename = mediaFilename;
	}

	public Boolean getSalesPaused() {
		return salesPaused;
	}

	public void setSalesPaused(Boolean salesPaused) {
		this.salesPaused = salesPaused;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	public String getDateActualFrom() {
		return dateActualFrom;
	}

	public void setDateActualFrom(String dateActualFrom) {
		this.dateActualFrom = dateActualFrom;
	}

	public String getDateActualUntil() {
		return dateActualUntil;
	}

	public void setDateActualUntil(String dateActualUntil) {
		this.dateActualUntil = dateActualUntil;
	}

	public Boolean getIsLong() {
		return isLong;
	}

	public void setIsLong(Boolean isLong) {
		this.isLong = isLong;
	}

	public Boolean getIsFavorited() {
		return isFavorited;
	}

	public void setIsFavorited(Boolean isFavorited) {
		this.isFavorited = isFavorited;
	}

	public String getTimeUntilActual() {
		return timeUntilActual;
	}

	public void setTimeUntilActual(String timeUntilActual) {
		this.timeUntilActual = timeUntilActual;
	}

	public Boolean getSalesStarted() {
		return salesStarted;
	}

	public void setSalesStarted(Boolean salesStarted) {
		this.salesStarted = salesStarted;
	}

	public String getDateSalesUntil() {
		return dateSalesUntil;
	}

	public void setDateSalesUntil(String dateSalesUntil) {
		this.dateSalesUntil = dateSalesUntil;
	}

	public Boolean getSalesEnded() {
		return salesEnded;
	}

	public void setSalesEnded(Boolean salesEnded) {
		this.salesEnded = salesEnded;
	}

	public Integer getFavoritedTimes() {
		return favoritedTimes;
	}

	public void setFavoritedTimes(Integer favoritedTimes) {
		this.favoritedTimes = favoritedTimes;
	}

	public EventPrice getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(EventPrice minPrice) {
		this.minPrice = minPrice;
	}

	public EventPrice getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(EventPrice maxPrice) {
		this.maxPrice = maxPrice;
	}

	public Boolean getHasFreeInventoryItems() {
		return hasFreeInventoryItems;
	}

	public void setHasFreeInventoryItems(Boolean hasFreeInventoryItems) {
		this.hasFreeInventoryItems = hasFreeInventoryItems;
	}

	public Boolean getSalesOngoing() {
		return salesOngoing;
	}

	public void setSalesOngoing(Boolean salesOngoing) {
		this.salesOngoing = salesOngoing;
	}

	public String getCompanyMediaFilename() {
		return companyMediaFilename;
	}

	public void setCompanyMediaFilename(String companyMediaFilename) {
		this.companyMediaFilename = companyMediaFilename;
	}

	public String getPricingInformation() {
		return pricingInformation;
	}

	public void setPricingInformation(String pricingInformation) {
		this.pricingInformation = pricingInformation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTimeUntilSalesStart() {
		return timeUntilSalesStart;
	}

	public void setTimeUntilSalesStart(String timeUntilSalesStart) {
		this.timeUntilSalesStart = timeUntilSalesStart;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDatePublishFrom() {
		return datePublishFrom;
	}

	public void setDatePublishFrom(String datePublishFrom) {
		this.datePublishFrom = datePublishFrom;
	}

	@Override
	public String toString() {
		return name;
	}
}
