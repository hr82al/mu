package ru.haval.action;

import javafx.beans.property.SimpleStringProperty;

public class hmmr_pmplan_model {
	
	public SimpleStringProperty Id = new SimpleStringProperty();
	public SimpleStringProperty num_pm = new SimpleStringProperty();
	public SimpleStringProperty dd = new SimpleStringProperty();
	public SimpleStringProperty resp = new SimpleStringProperty();
	public SimpleStringProperty pm_group = new SimpleStringProperty();
	public SimpleStringProperty pm_startdate = new SimpleStringProperty();
	public SimpleStringProperty pm_duration = new SimpleStringProperty();
	
	public hmmr_pmplan_model()
	{
		
	}
	
	public hmmr_pmplan_model (String Id, String num_pm, String dd, String resp, String pm_group, String pm_startdate, String pm_duration)
	{
		this.Id.set(Id);
		this.num_pm.set(num_pm);
		this.dd.set(dd);
		this.resp.set(resp);
		this.pm_group.set(pm_group);
		this.pm_startdate.set(pm_startdate);
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
    
    public String getnum_pm() {
        return num_pm.get();
    }

    public SimpleStringProperty num_pmProperty()
    {
    	return num_pm;
    }
    
    public void setnum_pm(String num_pm) {
        this.num_pm.set(num_pm);
    }
    
    public String getdd() {
        return dd.get();
    }

    public SimpleStringProperty ddProperty()
    {
    	return dd;
    }
    
    public void setdd(String dd) {
        this.dd.set(dd);
    }
    
    public String getresp() {
        return resp.get();
    }

    public SimpleStringProperty respProperty()
    {
    	return resp;
    }
    
    public void setresp(String resp) {
        this.resp.set(resp);
    }
    
    public String getpm_group() {
        return pm_group.get();
    }

    public SimpleStringProperty pm_groupProperty()
    {
    	return pm_group;
    }
    
    public void setpm_group(String pm_group) {
        this.pm_group.set(pm_group);
    }
    
    public String getpm_startdate() {
        return pm_startdate.get();
    }

    public SimpleStringProperty pm_startdateProperty()
    {
    	return pm_startdate;
    }
    
    public void setpm_startdate(String pm_startdate) {
        this.pm_startdate.set(pm_startdate);
    }
    
    public String getpm_duration() {
        return pm_duration.get();
    }

    public SimpleStringProperty pm_durationProperty()
    {
    	return pm_duration;
    }
    
    public void setpm_duration(String pm_duration) {
        this.pm_duration.set(pm_duration);
    }
    
    @Override
    public String toString() {
        return "hmmr_pmplan_model{" +
               "Id=" + Id.get() +
               ", num_pm='" + num_pm.get() + '\'' +
               ", dd='" + dd.get() + '\'' +
               ", resp='" + resp.get() + '\'' +
               ", pm_group='" + pm_group.get() + '\'' +
               '}';
    }
}
