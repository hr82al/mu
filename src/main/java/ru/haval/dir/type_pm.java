package ru.haval.dir;

import javafx.beans.property.SimpleStringProperty;

public class type_pm
{
	public SimpleStringProperty Id = new SimpleStringProperty();
	public SimpleStringProperty Type = new SimpleStringProperty();
	public SimpleStringProperty Name = new SimpleStringProperty();
	public SimpleStringProperty Desc = new SimpleStringProperty();
	public SimpleStringProperty Icon = new SimpleStringProperty();
		
	public type_pm()
	{
		
	}
	
	public type_pm(String Id, String Type, String Name, String Desc, String Icon)
	{
		this.Id.set(Id);
		this.Type.set(Type);
		this.Name.set(Name);
		this.Desc.set(Desc);
		this.Icon.set(Icon);
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
    
    public String getName() {
        return Name.get();
    }

    public SimpleStringProperty NameProperty()
    {
    	return Name;
    }
    
    public void setName(String Name) {
        this.Name.set(Name);
    }
    
    public String getDesc() {
        return Desc.get();
    }

    public SimpleStringProperty DescProperty()
    {
    	return Desc;
    }
    
    public void setDesc(String Desc) {
        this.Desc.set(Desc);
    }
    
    public String getIcon() {
        return Icon.get();
    }

    public SimpleStringProperty IconProperty()
    {
    	return Icon;
    }
    
    public void setIcon(String Icon) {
        this.Icon.set(Icon);
    }
    
    @Override
    public String toString() {
        return "type_pm{" +
               "Id=" + Id.get() +
               ", Type='" + Type.get() + '\'' +
               ", Desc='" + Desc.get() + '\'' +
               '}';
    }
}