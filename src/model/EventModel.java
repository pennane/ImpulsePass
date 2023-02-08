package model;
/*
 * 
 * AUTOMATICALLY GENERATED MODEL
 * 
 */
public class EventModel
{
    private String dateSalesFrom;

    private String isActual;

    private String companyName;

    private String availability;

    private String hasInventoryItems;

    private String dateCreated;

    private String mediaFilename;

    private String salesPaused;

    private String id;

    private String place;

    private String productType;

    private String dateActualFrom;


    private String dateActualUntil;

    private String isLong;

    private String isFavorited;

    private String timeUntilActual;

    private String salesStarted;

    private String dateSalesUntil;

    private String salesEnded;

    private String favoritedTimes;

    private EventPrice minPrice;
    
    private EventPrice maxPrice;

    private String hasFreeInventoryItems;

    private String salesOngoing;

    private String companyMediaFilename;
    
    private String pricingInformation;

    private String name;

    private String timeUntilSalesStart;


    private String time;

    private String datePublishFrom;

    public String getDateSalesFrom ()
    {
        return dateSalesFrom;
    }

    public void setDateSalesFrom (String dateSalesFrom)
    {
        this.dateSalesFrom = dateSalesFrom;
    }

    public String getIsActual ()
    {
        return isActual;
    }

    public void setIsActual (String isActual)
    {
        this.isActual = isActual;
    }

    public String getCompanyName ()
    {
        return companyName;
    }

    public void setCompanyName (String companyName)
    {
        this.companyName = companyName;
    }

    public String getAvailability ()
    {
        return availability;
    }

    public void setAvailability (String availability)
    {
        this.availability = availability;
    }
    
    public String getPricingInformation() {
    	return pricingInformation;
    }
    
    public void setPricingInformation(String pricingInformation) {
    	this.pricingInformation = pricingInformation;
    }

    public String getHasInventoryItems ()
    {
        return hasInventoryItems;
    }

    public void setHasInventoryItems (String hasInventoryItems)
    {
        this.hasInventoryItems = hasInventoryItems;
    }

    public String getDateCreated ()
    {
        return dateCreated;
    }

    public void setDateCreated (String dateCreated)
    {
        this.dateCreated = dateCreated;
    }

    public String getMediaFilename ()
    {
        return mediaFilename;
    }

    public void setMediaFilename (String mediaFilename)
    {
        this.mediaFilename = mediaFilename;
    }

    public String getSalesPaused ()
    {
        return salesPaused;
    }

    public void setSalesPaused (String salesPaused)
    {
        this.salesPaused = salesPaused;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getPlace ()
    {
        return place;
    }

    public void setPlace (String place)
    {
        this.place = place;
    }

    public String getProductType ()
    {
        return productType;
    }

    public void setProductType (String productType)
    {
        this.productType = productType;
    }

    public String getDateActualFrom ()
    {
        return dateActualFrom;
    }

    public void setDateActualFrom (String dateActualFrom)
    {
        this.dateActualFrom = dateActualFrom;
    }

    public String getDateActualUntil ()
    {
        return dateActualUntil;
    }

    public void setDateActualUntil (String dateActualUntil)
    {
        this.dateActualUntil = dateActualUntil;
    }

    public String getIsLong ()
    {
        return isLong;
    }

    public void setIsLong (String isLong)
    {
        this.isLong = isLong;
    }

    public String getIsFavorited ()
    {
        return isFavorited;
    }

    public void setIsFavorited (String isFavorited)
    {
        this.isFavorited = isFavorited;
    }

    public String getTimeUntilActual ()
    {
        return timeUntilActual;
    }

    public void setTimeUntilActual (String timeUntilActual)
    {
        this.timeUntilActual = timeUntilActual;
    }

    public String getSalesStarted ()
    {
        return salesStarted;
    }

    public void setSalesStarted (String salesStarted)
    {
        this.salesStarted = salesStarted;
    }

    public String getDateSalesUntil ()
    {
        return dateSalesUntil;
    }

    public void setDateSalesUntil (String dateSalesUntil)
    {
        this.dateSalesUntil = dateSalesUntil;
    }

    public String getSalesEnded ()
    {
        return salesEnded;
    }

    public void setSalesEnded (String salesEnded)
    {
        this.salesEnded = salesEnded;
    }

    public String getFavoritedTimes ()
    {
        return favoritedTimes;
    }

    public void setFavoritedTimes (String favoritedTimes)
    {
        this.favoritedTimes = favoritedTimes;
    }

    public EventPrice getMinPrice ()
    {
        return minPrice;
    }

    public void setMinPrice (EventPrice minPrice)
    {
        this.minPrice = minPrice;
    }

    public String getHasFreeInventoryItems ()
    {
        return hasFreeInventoryItems;
    }

    public void setHasFreeInventoryItems (String hasFreeInventoryItems)
    {
        this.hasFreeInventoryItems = hasFreeInventoryItems;
    }

    public String getSalesOngoing ()
    {
        return salesOngoing;
    }

    public void setSalesOngoing (String salesOngoing)
    {
        this.salesOngoing = salesOngoing;
    }

    public String getCompanyMediaFilename ()
    {
        return companyMediaFilename;
    }

    public void setCompanyMediaFilename (String companyMediaFilename)
    {
        this.companyMediaFilename = companyMediaFilename;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getTimeUntilSalesStart ()
    {
        return timeUntilSalesStart;
    }

    public void setTimeUntilSalesStart (String timeUntilSalesStart)
    {
        this.timeUntilSalesStart = timeUntilSalesStart;
    }
    
    public void setMaxPrice(EventPrice maxPrice) {
    	this.maxPrice = maxPrice;
    }

    public EventPrice getMaxPrice ()
    {
        return maxPrice;
    }

    public String getTime ()
    {
        return time;
    }

    public void setTime (String time)
    {
        this.time = time;
    }

    public String getDatePublishFrom ()
    {
        return datePublishFrom;
    }

    public void setDatePublishFrom (String datePublishFrom)
    {
        this.datePublishFrom = datePublishFrom;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [dateSalesFrom = "+dateSalesFrom+", isActual = "+isActual+", companyName = "+companyName+", availability = "+availability+", hasInventoryItems = "+hasInventoryItems+", dateCreated = "+dateCreated+", mediaFilename = "+mediaFilename+", salesPaused = "+salesPaused+", id = "+id+", place = "+place+", productType = "+productType+", dateActualFrom = "+dateActualFrom+", pricingInformation = "+pricingInformation+", dateActualUntil = "+dateActualUntil+", isLong = "+isLong+", isFavorited = "+isFavorited+", timeUntilActual = "+timeUntilActual+", salesStarted = "+salesStarted+", dateSalesUntil = "+dateSalesUntil+", salesEnded = "+salesEnded+", favoritedTimes = "+favoritedTimes+", minPrice = "+minPrice+", hasFreeInventoryItems = "+hasFreeInventoryItems+", salesOngoing = "+salesOngoing+", companyMediaFilename = "+companyMediaFilename+", name = "+name+", timeUntilSalesStart = "+timeUntilSalesStart+", maxPrice = "+maxPrice+", time = "+time+", datePublishFrom = "+datePublishFrom+"]";
    }
}
			
			