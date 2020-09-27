package ru.haval.action;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class hmmr_wr_model {

	public SimpleStringProperty Id = new SimpleStringProperty();
	public SimpleStringProperty shift_report = new SimpleStringProperty();
	public SimpleStringProperty req_action = new SimpleStringProperty();
	public SimpleStringProperty actual_time = new SimpleStringProperty();
	public SimpleStringProperty actual_time1 = new SimpleStringProperty();
	public SimpleStringProperty data = new SimpleStringProperty();
	public SimpleStringProperty equip = new SimpleStringProperty();
	public SimpleStringProperty record_type = new SimpleStringProperty();
	public SimpleStringProperty resp = new SimpleStringProperty();
	public SimpleStringProperty status = new SimpleStringProperty();
	private SimpleBooleanProperty qty = new SimpleBooleanProperty();
	public SimpleStringProperty ap_num = new SimpleStringProperty();
	public SimpleStringProperty user_id = new SimpleStringProperty();
	private SimpleBooleanProperty user = new SimpleBooleanProperty();
	public SimpleStringProperty icon_at = new SimpleStringProperty();
	
	public hmmr_wr_model()
	{
		
	}
	public hmmr_wr_model(String Id, String shift_report, String req_action, String actual_time, String actual_time1, String data, String equip, String record_type,
			String resp, String status, Boolean qty, String ap_num, String user_id, Boolean user, String icon_at)
	{
		this.Id.set(Id);
		this.shift_report.set(shift_report);
		this.req_action.set(req_action);
		this.actual_time.set(actual_time);
		this.actual_time1.set(actual_time1);
		this.data.set(data);
		this.equip.set(equip);
		this.record_type.set(record_type);
		this.resp.set(resp);
		this.status.set(status);
		this.qty.set(qty);
		this.ap_num.set(ap_num);
		this.user_id.set(user_id);
		this.user.set(user);
		this.icon_at.set(icon_at);
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
    
    public String getshift_report() {
        return shift_report.get();
    }

    public SimpleStringProperty shift_reportProperty()
    {
    	return shift_report;
    }
    
    public void setshift_report(String shift_report) {
        this.shift_report.set(shift_report);
    }
    
    public String getreq_action() {
        return req_action.get();
    }

    public SimpleStringProperty req_actionProperty()
    {
    	return req_action;
    }
    
    public void setreq_action(String req_action) {
        this.req_action.set(req_action);
    }
    
    public String getactual_time() {
        return actual_time.get();
    }

    public SimpleStringProperty actual_timeProperty()
    {
    	return actual_time;
    }
    
    public void setactual_time(String actual_time) {
        this.actual_time.set(actual_time);
    }
    
    public String getactual_time1() {
        return actual_time1.get();
    }

    public SimpleStringProperty actual_time1Property()
    {
    	return actual_time1;
    }
    
    public void setactual_time1(String actual_time1) {
        this.actual_time1.set(actual_time1);
    }
    
    public String getdata() {
        return data.get();
    }

    public SimpleStringProperty dataProperty()
    {
    	return data;
    }
    
    public void setdata(String data) {
        this.data.set(data);
    }
    
    public String getequip() {
        return equip.get();
    }

    public SimpleStringProperty equipProperty()
    {
    	return equip;
    }
    
    public void setequip(String equip) {
        this.equip.set(equip);
    }
    
    public String getrecord_type() {
        return record_type.get();
    }

    public SimpleStringProperty record_typeProperty()
    {
    	return record_type;
    }
    
    public void setrecord_type(String record_type) {
        this.record_type.set(record_type);
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
    
    public String getstatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty()
    {
    	return status;
    }
    
    public void setstatus(String status) {
        this.status.set(status);
    }
    
    public Boolean getqty() {
        return qty.get();
    }

    public SimpleBooleanProperty qtyProperty()
    {
    	return qty;
    }
    
    public void setqty(Boolean qty) {
        this.qty.set(qty);
    }
    
    public String getap_num() {
        return ap_num.get();
    }

    public SimpleStringProperty ap_numProperty()
    {
    	return ap_num;
    }
    
    public void setap_num(String ap_num) {
        this.ap_num.set(ap_num);
    }
    
    public String getuser_id() {
        return user_id.get();
    }

    public SimpleStringProperty user_idProperty()
    {
    	return user_id;
    }
    
    public void setuser_id(String user_id) {
        this.user_id.set(user_id);
    }
    
    public Boolean getuser() {
        return user.get();
    }

    public SimpleBooleanProperty userProperty()
    {
    	return user;
    }
    
    public void setuser(Boolean user) {
        this.user.set(user);
    }
    
    public String geticon_at() {
        return icon_at.get();
    }

    public SimpleStringProperty icon_atProperty()
    {
    	return icon_at;
    }
    
    public void seticon_at(String icon_at) {
        this.icon_at.set(icon_at);
    }
    
    @Override
    public String toString() {
        return "hmmr_wr_model{" +
               "Id=" + Id.get() +
               ", shift_report='" + shift_report.get() + '\'' +
                ", req_action='" + req_action.get() + '\'' +
                ", actual_time='" + actual_time.get() + '\'' +
                ", actual_time1='" + actual_time1.get() + '\'' +
                ", data='" + data.get() + '\'' +
                ", equip='" + equip.get() + '\'' +
                ", record_type='" + record_type.get() + '\'' +
                ", resp='" + resp.get() + '\'' +
                ", status='" + status.get() + '\'' +
                ", qty='" + qty.get() + '\'' +
                ", ap_num='" + ap_num.get() + '\'' +
                ", user_id='" + user_id.get() + '\'' +
                ", user='" + user.get() + '\'' +
                ", icon_at='" + icon_at.get() + '\'' +
                '}';
    }
}
