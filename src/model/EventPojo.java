package model;
public class EventPojo {
	private EventModel[] model;
	private String[] links;

    public EventModel[] getModel()
    {
        return model;
    }

    public void setModel (EventModel[] model)
    {
        this.model = model;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [model = "+model+", links = "+links+"]";
    }
}
