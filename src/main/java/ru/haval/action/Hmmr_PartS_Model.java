package ru.haval.action;

import javafx.beans.property.SimpleStringProperty;

public class Hmmr_PartS_Model {
	
	public SimpleStringProperty Id = new SimpleStringProperty();
	public SimpleStringProperty HMMR_Material_Num = new SimpleStringProperty();
	public SimpleStringProperty Equipment = new SimpleStringProperty();
	public SimpleStringProperty Drawing = new SimpleStringProperty();
	public SimpleStringProperty Position_On_Drawing = new SimpleStringProperty();
	public SimpleStringProperty Key_No_Backup_Yes = new SimpleStringProperty();
	public SimpleStringProperty Key_No_Backup_No =new SimpleStringProperty();
	public SimpleStringProperty Key_Yes_Backup_Yes = new SimpleStringProperty();
	public SimpleStringProperty Key_Yes_Backup_No = new SimpleStringProperty();
	
	public Hmmr_PartS_Model()
	{
		
	}
	public Hmmr_PartS_Model(String Id, String HMMR_Material_Num, String Equipment, String Drawing, String Position_On_Drawing, String Key_No_Backup_Yes, String Key_No_Backup_No,
			String Key_Yes_Backup_Yes, String Key_Yes_Backup_No)
	{
		this.Id.set(Id);
		this.HMMR_Material_Num.set(HMMR_Material_Num);
		this.Equipment.set(Equipment);
		this.Drawing.set(Drawing);
		this.Position_On_Drawing.set(Position_On_Drawing);
		this.Key_No_Backup_Yes.set(Key_No_Backup_Yes);
		this.Key_No_Backup_No.set(Key_No_Backup_No);
		this.Key_Yes_Backup_Yes.set(Key_Yes_Backup_Yes);
		this.Key_Yes_Backup_No.set(Key_Yes_Backup_No);
	}
	public SimpleStringProperty getId() {
		return Id;
	}
	public void setId(SimpleStringProperty id) {
		Id = id;
	}
	public SimpleStringProperty getHMMR_Material_Num() {
		return HMMR_Material_Num;
	}
	public void setHMMR_Material_Num(SimpleStringProperty hMMR_Material_Num) {
		HMMR_Material_Num = hMMR_Material_Num;
	}
	public SimpleStringProperty getEquipment() {
		return Equipment;
	}
	public void setEquipment(SimpleStringProperty equipment) {
		Equipment = equipment;
	}
	public SimpleStringProperty getDrawing() {
		return Drawing;
	}
	public void setDrawing(SimpleStringProperty drawing) {
		Drawing = drawing;
	}
	public SimpleStringProperty getPosition_On_Drawing() {
		return Position_On_Drawing;
	}
	public void setPosition_On_Drawing(SimpleStringProperty position_On_Drawing) {
		Position_On_Drawing = position_On_Drawing;
	}
	public SimpleStringProperty getKey_No_Backup_Yes() {
		return Key_No_Backup_Yes;
	}
	public void setKey_No_Backup_Yes(SimpleStringProperty key_No_Backup_Yes) {
		Key_No_Backup_Yes = key_No_Backup_Yes;
	}
	public SimpleStringProperty getKey_No_Backup_No() {
		return Key_No_Backup_No;
	}
	public void setKey_No_Backup_No(SimpleStringProperty key_No_Backup_No) {
		Key_No_Backup_No = key_No_Backup_No;
	}
	public SimpleStringProperty getKey_Yes_Backup_Yes() {
		return Key_Yes_Backup_Yes;
	}
	public void setKey_Yes_Backup_Yes(SimpleStringProperty key_Yes_Backup_Yes) {
		Key_Yes_Backup_Yes = key_Yes_Backup_Yes;
	}
	public SimpleStringProperty getKey_Yes_Backup_No() {
		return Key_Yes_Backup_No;
	}
	public void setKey_Yes_Backup_No(SimpleStringProperty key_Yes_Backup_No) {
		Key_Yes_Backup_No = key_Yes_Backup_No;
	}
	
	public String getIdStr() {
		return Id.get();
	}
	
	public String getEquip_id() {
		return Equipment.get();
	}
	
	public String getHMMR_Material_Id() {
		return HMMR_Material_Num.get();
	}
}
