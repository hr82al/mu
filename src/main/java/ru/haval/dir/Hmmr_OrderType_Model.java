package ru.haval.dir;

import javafx.beans.property.SimpleStringProperty;

public class Hmmr_OrderType_Model {
	
	public SimpleStringProperty Id = new SimpleStringProperty();
	public SimpleStringProperty Name = new SimpleStringProperty();
	public SimpleStringProperty Desc = new SimpleStringProperty();
	
	public Hmmr_OrderType_Model()
	{
		
	}
	public Hmmr_OrderType_Model(String id, String name, String desc)
	{
		this.Id.set(id);
		this.Name.set(name);
		this.Desc.set(desc);
	}
	
	public SimpleStringProperty getId() {
		return Id;
	}
	public void setId(SimpleStringProperty id) {
		Id = id;
	}
	public SimpleStringProperty getName() {
		return Name;
	}
	public void setName(SimpleStringProperty name) {
		Name = name;
	}
	public SimpleStringProperty getDesc() {
		return Desc;
	}
	public void setDesc(SimpleStringProperty desc) {
		Desc = desc;
	}
	
	public String getIdStr()
	{
		return Id.get();
	}
	
	public String getNameStr()
	{
		return Name.get();
	}
	
	public String getDescStr()
	{
		return Desc.get();
	}

}
