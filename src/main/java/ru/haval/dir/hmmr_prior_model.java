package ru.haval.dir;

import javafx.beans.property.SimpleStringProperty;

public class hmmr_prior_model {
	
	public SimpleStringProperty Id = new SimpleStringProperty();
	public SimpleStringProperty ID_TSK = new SimpleStringProperty();
	public SimpleStringProperty Name_Prior = new SimpleStringProperty();
	public SimpleStringProperty Description = new SimpleStringProperty();
	public SimpleStringProperty Icon = new SimpleStringProperty();
	
	public hmmr_prior_model()
	{
		
	}
	public hmmr_prior_model(String Id, String ID_TSK, String Name_Prior, String Description, String Icon)
	{
		this.Id.set(Id);
		this.ID_TSK.set(ID_TSK);
		this.Name_Prior.set(Name_Prior);
		this.Description.set(Description);
		this.Icon.set(Icon);
	}
	public SimpleStringProperty getID_TSK() {
		return ID_TSK;
	}
	public void setID_TSK(SimpleStringProperty iD_TSK) {
		ID_TSK = iD_TSK;
	}
	public SimpleStringProperty getName_Prior() {
		return Name_Prior;
	}
	public void setName_Prior(SimpleStringProperty name_Prior) {
		Name_Prior = name_Prior;
	}
	public SimpleStringProperty getDescription() {
		return Description;
	}
	public void setDescription(SimpleStringProperty description) {
		Description = description;
	}
	public SimpleStringProperty getIcon() {
		return Icon;
	}
	public void setIcon(SimpleStringProperty icon) {
		Icon = icon;
	}
	public String getIconStr() {
        return Icon.get();
    }
	public String getId() {
        return Id.get();
    }
	public String getDesc()
	{
		return Description.get();
	}
}
