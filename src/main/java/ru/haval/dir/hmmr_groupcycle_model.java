package ru.haval.dir;

import javafx.beans.property.SimpleStringProperty;

public class hmmr_groupcycle_model {
	
	public SimpleStringProperty group_pm = new SimpleStringProperty();
	public SimpleStringProperty cycle_pm = new SimpleStringProperty();
	public SimpleStringProperty Id = new SimpleStringProperty();
	public SimpleStringProperty days = new SimpleStringProperty();
	public SimpleStringProperty pm_startdate = new SimpleStringProperty();
	public SimpleStringProperty pm_duration = new SimpleStringProperty();
	
	public hmmr_groupcycle_model()
	{
		
	}
	
	public hmmr_groupcycle_model(String Id, String gruop_pm, String cycle_pm, String days, String pm_startdate, String pm_duration)
	{
		this.Id.set(Id);
		this.group_pm.set(gruop_pm);
		this.cycle_pm.set(cycle_pm);
		this.days.set(days);
		this.pm_startdate.set(pm_startdate);
		this.pm_duration.set(pm_duration);
	}
	
	public SimpleStringProperty getId_() {
		return Id;
	}

	public void setId(SimpleStringProperty Id) {
		this.Id = Id;
	}

	public SimpleStringProperty getGroup_pm() {
		return group_pm;
	}

	public void setGroup_pm(SimpleStringProperty group_pm) {
		this.group_pm = group_pm;
	}

	public SimpleStringProperty getCycle_pm() {
		return cycle_pm;
	}

	public void setCycle_pm(SimpleStringProperty cycle_pm) {
		this.cycle_pm = cycle_pm;
	}
	
	public SimpleStringProperty getpm_startdate() {
		return pm_startdate;
	}

	public void setpm_startdate(SimpleStringProperty pm_startdate) {
		this.pm_startdate = pm_startdate;
	}
	
	public String getpm_startdateStr()
	{
		return pm_startdate.get();
	}
	
	public SimpleStringProperty getpm_duration() {
		return pm_duration;
	}

	public void setpm_duration(SimpleStringProperty pm_duration) {
		this.pm_duration = pm_duration;
	}
	
	public String getpm_durationStr()
	{
		return pm_duration.get();
	}
	
	public String getId()
	{
		return Id.get();
	}
	
	public String getGroupStr()
	{
		return group_pm.get();
	}
	
	public String getCycleStr()
	{
		return cycle_pm.get();
	}
	
	public SimpleStringProperty getdays() {
		return days;
	}

	public void setdays(SimpleStringProperty days) {
		this.days = days;
	}
	
	public String getDaysStr()
	{
		return days.get();
	}

}
