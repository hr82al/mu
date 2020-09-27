package ru.haval.action;

import javafx.beans.property.SimpleStringProperty;

public class Hmmr_CS_Model {
	
	public SimpleStringProperty Id = new SimpleStringProperty();
	public SimpleStringProperty ID_HMMR_COMPLEX = new SimpleStringProperty();
	public SimpleStringProperty ID_HMMR_SUB = new SimpleStringProperty();

	public Hmmr_CS_Model()
	{
		
	}
	public Hmmr_CS_Model(String Id, String ID_HMMR_COMPLEX, String ID_HMMR_SUB)
	{
		this.Id.set(Id);
		this.ID_HMMR_COMPLEX.set(ID_HMMR_COMPLEX);
		this.ID_HMMR_SUB.set(ID_HMMR_SUB);
	}
	public SimpleStringProperty getId() {
		return Id;
	}
	public void setId(SimpleStringProperty id) {
		Id = id;
	}
	public SimpleStringProperty getID_HMMR_COMPLEX() {
		return ID_HMMR_COMPLEX;
	}
	public void setID_HMMR_COMPLEX(SimpleStringProperty id_HMMR_COMPLEX) {
		ID_HMMR_COMPLEX = id_HMMR_COMPLEX;
	}
	public SimpleStringProperty getID_HMMR_SUB() {
		return ID_HMMR_SUB;
	}
	public void setID_HMMR_SUB(SimpleStringProperty id_HMMR_SUB) {
		ID_HMMR_SUB = id_HMMR_SUB;
	}
	
	public String getIdStr() {
		return Id.get();
	}
	public String getID_HMMR_COMPLEXStr() {
		return ID_HMMR_COMPLEX.get();
	}
	public String getID_HMMR_SUBStr() {
		return ID_HMMR_SUB.get();
	}
}
