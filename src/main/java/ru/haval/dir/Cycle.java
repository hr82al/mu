package ru.haval.dir;

import javafx.beans.property.SimpleStringProperty;

public class Cycle
{
	public SimpleStringProperty Id = new SimpleStringProperty();
	public SimpleStringProperty Type = new SimpleStringProperty();
	public SimpleStringProperty Pereodic = new SimpleStringProperty();
	public SimpleStringProperty Hours = new SimpleStringProperty();
	public SimpleStringProperty BeginDate = new SimpleStringProperty();
	public SimpleStringProperty EndDate = new SimpleStringProperty();
	
	public Cycle()
	{
		
	}
	
	public Cycle(String Id, String Type, String Pereodic, String Hours, String BeginDate, String EndDate)
	{
		this.Id.set(Id);
		this.Type.set(Type);
		this.Pereodic.set(Pereodic);
		this.Hours.set(Hours);
		this.BeginDate.set(BeginDate);
		this.EndDate.set(EndDate);
	}
	
	public String getId() {
        return Id.get();
    }

    public SimpleStringProperty IdProperty()
    {
    	return Id;
    }
    
    public void setId(String Id) {
        this.Id.set(Id);
    }
	
	public String getType() {
        return Type.get();
    }

    public SimpleStringProperty TypeProperty()
    {
    	return Type;
    }
    
    public void setType(String Type) {
        this.Type.set(Type);
    }
    
    public String getPereodic() {
        return Pereodic.get();
    }

    public SimpleStringProperty PereodicProperty()
    {
    	return Pereodic;
    }
    
    public void setPereodic(String Pereodic) {
        this.Pereodic.set(Pereodic);
    }
    
    public String getHours() {
        return Hours.get();
    }

    public SimpleStringProperty HoursProperty()
    {
    	return Hours;
    }
    
    public void setHours(String Hours) {
        this.Hours.set(Hours);
    }
    
    public String getBeginDate() {
        return BeginDate.get();
    }

    public SimpleStringProperty BeginDateProperty()
    {
    	return BeginDate;
    }
    
    public void setBeginDate(String BeginDate) {
        this.BeginDate.set(BeginDate);
    }
    
    public String getEndDate() {
        return EndDate.get();
    }

    public SimpleStringProperty EndDateProperty()
    {
    	return EndDate;
    }
    
    public void setEndDate(String EndDate) {
        this.EndDate.set(EndDate);
    }
    
    @Override
    public String toString() {
        return "Cycle{" +
               "Id=" + Id.get() +
               ", Type='" + Type.get() + '\'' +
               ", Pereodic='" + Pereodic.get() + '\'' +
               ", Hours='" + Hours.get() + '\'' +
               ", BeginDate='" + BeginDate.get() + '\'' +
               ", EndDate='" + EndDate.get() + '\'' +
               '}';
    }
}