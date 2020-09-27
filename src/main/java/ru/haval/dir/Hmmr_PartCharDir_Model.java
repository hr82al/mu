package ru.haval.dir;

import javafx.beans.property.SimpleStringProperty;

public class Hmmr_PartCharDir_Model {
	public SimpleStringProperty Id = new SimpleStringProperty();
	public SimpleStringProperty Part_Char = new SimpleStringProperty();
	public SimpleStringProperty Part_Char_Eng = new SimpleStringProperty();
	
	public Hmmr_PartCharDir_Model()
	{
		
	}
	public Hmmr_PartCharDir_Model(String Id, String Part_Char, String Part_Char_Eng)
	{
		this.Id.set(Id);
		this.Part_Char.set(Part_Char);
		this.Part_Char_Eng.set(Part_Char_Eng);
	}
	public SimpleStringProperty getId() {
		return Id;
	}
	public void setId(SimpleStringProperty id) {
		Id = id;
	}
	public SimpleStringProperty getPart_Char() {
		return Part_Char;
	}
	public void setPart_Char(SimpleStringProperty part_Char) {
		Part_Char = part_Char;
	}
	public SimpleStringProperty getPart_Char_Eng() {
		return Part_Char_Eng;
	}
	public void setPart_Char_Eng(SimpleStringProperty part_Char_Eng) {
		Part_Char_Eng = part_Char_Eng;
	}
	
	public String getIdStr()
	{
		return Id.get();
	}
	
	public String getPart_CharStr()
	{
		return Part_Char.get();
	}
	
	public String getPart_Char_EngStr()
	{
		return Part_Char_Eng.get();
	}

}
