package ru.haval.dir;

import javafx.beans.property.SimpleStringProperty;

public class Hmmr_Part_Model {
	public SimpleStringProperty Id = new SimpleStringProperty();
	public SimpleStringProperty Part_Type = new SimpleStringProperty();
	public SimpleStringProperty Part_Type_Eng = new SimpleStringProperty();
	
	public Hmmr_Part_Model()
	{
		
	}
	public Hmmr_Part_Model(String Id, String Part_Type, String Part_Type_Eng)
	{
		this.Id.set(Id);
		this.Part_Type.set(Part_Type);
		this.Part_Type_Eng.set(Part_Type_Eng);
	}
	public SimpleStringProperty getId() {
		return Id;
	}
	public void setId(SimpleStringProperty id) {
		Id = id;
	}
	public SimpleStringProperty getPart_Type() {
		return Part_Type;
	}
	public void setPart_Type(SimpleStringProperty part_Type) {
		Part_Type = part_Type;
	}
	public SimpleStringProperty getPart_Type_Eng() {
		return Part_Type_Eng;
	}
	public void setPart_Type_Eng(SimpleStringProperty part_Type_Eng) {
		Part_Type_Eng = part_Type_Eng;
	}
	
	public String getIdStr()
	{
		return Id.get();
	}
	
	public String getPart_TypeStr()
	{
		return Part_Type.get();
	}
	
	public String getPart_Type_EngStr()
	{
		return Part_Type_Eng.get();
	}

}
