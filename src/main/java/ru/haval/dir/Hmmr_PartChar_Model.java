package ru.haval.dir;

import javafx.beans.property.SimpleStringProperty;

public class Hmmr_PartChar_Model {
	public SimpleStringProperty Id = new SimpleStringProperty();
	public SimpleStringProperty Part_Type = new SimpleStringProperty();
	public SimpleStringProperty SP_KIND = new SimpleStringProperty();
	public SimpleStringProperty Part_Sub_Type = new SimpleStringProperty();
	public SimpleStringProperty Part_Sub_Type_ENG = new SimpleStringProperty();
	public SimpleStringProperty Part_Characteristic_Name_1 = new SimpleStringProperty();
	public SimpleStringProperty Part_Characteristic_Name_2 = new SimpleStringProperty();
	public SimpleStringProperty Part_Characteristic_Name_3 = new SimpleStringProperty();
	public SimpleStringProperty Part_Characteristic_Name_4 = new SimpleStringProperty();
	
	public void Hmmr_Part_Model() {
		
	}
	
	public void Hmmr_Part_Model(String Id, String Part_Type, String SP_KIND, String Part_Sub_Type,
			String Part_Sub_Type_ENG, String Part_Characteristic_Name_1, String Part_Characteristic_Name_2, String Part_Characteristic_Name_3, String Part_Characteristic_Name_4) {
		this.Id.set(Id);
		this.Part_Type.set(Part_Type);
		this.SP_KIND.set(SP_KIND);
		this.Part_Sub_Type.set(Part_Sub_Type);
		this.Part_Sub_Type_ENG.set(Part_Sub_Type_ENG);
		this.Part_Characteristic_Name_1.set(Part_Characteristic_Name_1);
		this.Part_Characteristic_Name_2.set(Part_Characteristic_Name_2);
		this.Part_Characteristic_Name_3.set(Part_Characteristic_Name_3);
		this.Part_Characteristic_Name_4.set(Part_Characteristic_Name_4);
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

	public SimpleStringProperty getSP_KIND() {
		return SP_KIND;
	}

	public void setSP_KIND(SimpleStringProperty sP_KIND) {
		SP_KIND = sP_KIND;
	}

	public SimpleStringProperty getPart_Sub_Type() {
		return Part_Sub_Type;
	}

	public void setPart_Sub_Type(SimpleStringProperty part_Sub_Type) {
		Part_Sub_Type = part_Sub_Type;
	}

	public SimpleStringProperty getPart_Sub_Type_ENG() {
		return Part_Sub_Type_ENG;
	}

	public void setPart_Sub_Type_ENG(SimpleStringProperty part_Sub_Type_ENG) {
		Part_Sub_Type_ENG = part_Sub_Type_ENG;
	}

	public SimpleStringProperty getPart_Characteristic_Name_1() {
		return Part_Characteristic_Name_1;
	}

	public void setPart_Characteristic_Name_1(SimpleStringProperty part_Characteristic_Name_1) {
		Part_Characteristic_Name_1 = part_Characteristic_Name_1;
	}

	public SimpleStringProperty getPart_Characteristic_Name_2() {
		return Part_Characteristic_Name_2;
	}

	public void setPart_Characteristic_Name_2(SimpleStringProperty part_Characteristic_Name_2) {
		Part_Characteristic_Name_2 = part_Characteristic_Name_2;
	}

	public SimpleStringProperty getPart_Characteristic_Name_3() {
		return Part_Characteristic_Name_3;
	}

	public void setPart_Characteristic_Name_3(SimpleStringProperty part_Characteristic_Name_3) {
		Part_Characteristic_Name_3 = part_Characteristic_Name_3;
	}

	public SimpleStringProperty getPart_Characteristic_Name_4() {
		return Part_Characteristic_Name_4;
	}

	public void setPart_Characteristic_Name_4(SimpleStringProperty part_Characteristic_Name_4) {
		Part_Characteristic_Name_4 = part_Characteristic_Name_4;
	}
	
	public String getIdStr() {
		return Id.get();
	}

}
