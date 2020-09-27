package ru.haval.application;

import javafx.beans.property.SimpleStringProperty;

public class User {

    public SimpleStringProperty Number = new SimpleStringProperty();
    public SimpleStringProperty Date = new SimpleStringProperty();
    public SimpleStringProperty WSH = new SimpleStringProperty();
    public SimpleStringProperty Group = new SimpleStringProperty();
    public SimpleStringProperty Line = new SimpleStringProperty();
    public SimpleStringProperty Station = new SimpleStringProperty();
    public SimpleStringProperty Equipment = new SimpleStringProperty();
    public SimpleStringProperty Record_Type = new SimpleStringProperty();
    public SimpleStringProperty Shift = new SimpleStringProperty();
    public SimpleStringProperty Work_Time = new SimpleStringProperty();
    public SimpleStringProperty Resp1 = new SimpleStringProperty();
    public SimpleStringProperty Resp2 = new SimpleStringProperty();
    public SimpleStringProperty Resp3 = new SimpleStringProperty();
    public SimpleStringProperty Resp4 = new SimpleStringProperty();
    public SimpleStringProperty Status = new SimpleStringProperty();
    public SimpleStringProperty Shift_Report = new SimpleStringProperty();
    public SimpleStringProperty Required_Action = new SimpleStringProperty();
    public SimpleStringProperty Due_Date = new SimpleStringProperty();
    public SimpleStringProperty Otv = new SimpleStringProperty();
    public SimpleStringProperty Change_Dev_Comp = new SimpleStringProperty();
    public SimpleStringProperty Change_Dev_Part = new SimpleStringProperty();
    public SimpleStringProperty Qty = new SimpleStringProperty();
    public SimpleStringProperty Change_Dev_Unpart = new SimpleStringProperty();
    public SimpleStringProperty Actual_Date = new SimpleStringProperty();
    public SimpleStringProperty Change_Actual_Date = new SimpleStringProperty();
    public SimpleStringProperty User_Name = new SimpleStringProperty();
    
   public User()
    {
    	
    }
    
    public User(String Number, String Date, String WSH, String Group, String Line, String Station, String Equipment, String Record_Type,
    			String Shift, String Work_Time, String Resp1, String Resp2, String Resp3, String Resp4, String Status,
    			String Shift_Report, String Required_Action, String Due_Date, String Otv, String Change_Dev_Comp, String Change_Dev_Part,
    			String Qty, String Change_Dev_Unpart, String Actual_Date, String Change_Actual_Date, String User_Name) {
    	this.Number.set(Number);
        this.Date.set(Date);
        this.WSH.set(WSH);
        this.Group.set(Group);
        this.Line.set(Line);
        this.Station.set(Station);
        this.Equipment.set(Equipment);
        this.Record_Type.set(Record_Type);
        this.Shift.set(Shift);
        this.Work_Time.set(Work_Time);
        this.Resp1.set(Resp1);
        this.Resp2.set(Resp2);
        this.Resp3.set(Resp3);
        this.Resp4.set(Resp4);
        this.Status.set(Status);
        this.Shift_Report.set(Shift_Report);
        this.Required_Action.set(Required_Action);
        this.Due_Date.set(Due_Date);
        this.Otv.set(Otv);
        this.Change_Dev_Comp.set(Change_Dev_Comp);
        this.Change_Dev_Part.set(Change_Dev_Part);
        this.Qty.set(Qty);
        this.Change_Dev_Unpart.set(Change_Dev_Unpart);
        this.Actual_Date.set(Actual_Date);
        this.Change_Actual_Date.set(Change_Actual_Date);
        this.User_Name.set(User_Name);
	}

    public String getNumber() {
        return Number.get();
    }

    public SimpleStringProperty NumberProperty()
    {
    	return Number;
    }
    
    public void setNumber(String Number) {
        this.Number.set(Number);
    }

    public String getDate() {
        return Date.get();
    }

    public SimpleStringProperty DateProperty()
    {
    	return Date;
    }
    
    public void setDate(String Date) {
        this.Date.set(Date);
    }

    public String getWSH() {
        return WSH.get();
    }
    
    public SimpleStringProperty WSHProperty()
    {
    	return WSH;
    }

    public void setWSH(String WSH) {
        this.WSH.set(WSH);
    }
    
    public String getGroup()
    {
    	return Group.get();
    }
    
    public SimpleStringProperty GroupProperty()
    {
    	return Group;
    }
    
    public void setGroup(String Group)
    {
    	this.Group.set(Group);
    }
    
    public String getLine() {
        return Line.get();
    }

    public SimpleStringProperty LineProperty()
    {
    	return Line;
    }
    
    public void setLine(String Line) {
        this.Line.set(Line);
    }
    
    public String getStation() {
    	return Station.get();
    }
    public SimpleStringProperty StationProperty()
    {
    	return Station;
    }
    
    public void setdate(String Station)
    {
    	this.Station.set(Station);
    }
        
    public String getEquipment() {
    	return Equipment.get();
    }
    public SimpleStringProperty EquipmentProperty()
    {
    	return Equipment;
    }
    
    public void setEquipment(String Equipment)
    {
    	this.Equipment.set(Equipment);
    }
    
    public String getRecord_Type() {
    	return Record_Type.get();
    }
    public SimpleStringProperty Record_TypeProperty()
    {
    	return Record_Type;
    }
    
    public void setRecord_Type(String Record_Type)
    {
    	this.Record_Type.set(Record_Type);
    }
    
    public String getShift() {
    	return Shift.get();
    }
    public SimpleStringProperty ShiftProperty()
    {
    	return Shift;
    }
    
    public void setShift(String Shift)
    {
    	this.Shift.set(Shift);
    }
    
    public String Work_Time() {
    	return Work_Time.get();
    }
    public SimpleStringProperty Work_TimeProperty()
    {
    	return Work_Time;
    }
    
    public void setWork_Time(String Work_Time)
    {
    	this.Work_Time.set(Work_Time);
    }
    
    public String getResp1() {
    	return Resp1.get();
    }
    public SimpleStringProperty Resp1Property()
    {
    	return Resp1;
    }
    
    public void setResp1(String Resp1)
    {
    	this.Resp1.set(Resp1);
    }
    
    public String getResp2() {
    	return Resp2.get();
    }
    public SimpleStringProperty Resp2Property()
    {
    	return Resp2;
    }
    
    public void setResp2(String Resp2)
    {
    	this.Resp2.set(Resp2);
    }
    
    public String getResp3() {
    	return Resp3.get();
    }
    public SimpleStringProperty Resp3Property()
    {
    	return Resp3;
    }
    
    public void setResp3(String Resp3)
    {
    	this.Resp3.set(Resp3);
    }
    
    public String getResp4() {
    	return Resp4.get();
    }
    public SimpleStringProperty Resp4Property()
    {
    	return Resp4;
    }
    
    public void setResp4(String Resp4)
    {
    	this.Resp4.set(Resp4);
    }
    
    public String getStatus() {
    	return Status.get();
    }
    public SimpleStringProperty StatusProperty()
    {
    	return Status;
    }
    
    public void setStatus(String Status)
    {
    	this.Status.set(Status);
    }
    
    public String getShift_Report() {
    	return Shift_Report.get();
    }
    public SimpleStringProperty Shift_ReportProperty()
    {
    	return Shift_Report;
    }
    
    public void setShift_Report(String Shift_Report)
    {
    	this.Shift_Report.set(Shift_Report);
    }
    
    public String getRequired_Action() {
    	return Required_Action.get();
    }
    public SimpleStringProperty Required_ActionProperty()
    {
    	return Required_Action;
    }
    
    public void setRequired_Action(String Required_Action)
    {
    	this.Required_Action.set(Required_Action);
    }
    
    public String getDue_Date() {
    	return Due_Date.get();
    }
    public SimpleStringProperty Due_dateProperty()
    {
    	return Due_Date;
    }
    
    public void setDue_Date(String Due_Date)
    {
    	this.Due_Date.set(Due_Date);
    }
    
    public String getOtv() {
    	return Otv.get();
    }
    public SimpleStringProperty OtvProperty()
    {
    	return Otv;
    }
    
    public void setOtv(String Otv)
    {
    	this.Otv.set(Otv);
    }
    
    public String getChange_Dev_Comp() {
    	return Change_Dev_Comp.get();
    }
    public SimpleStringProperty Change_Dev_CompProperty()
    {
    	return Change_Dev_Comp;
    }
    
    public void setChange_Dev_Comp(String Change_Dev_Comp)
    {
    	this.Change_Dev_Comp.set(Change_Dev_Comp);
    }
    
    public String getChange_Dev_Part() {
    	return Change_Dev_Part.get();
    }
    public SimpleStringProperty Change_Dev_PartProperty()
    {
    	return Change_Dev_Part;
    }
    
    public void setChange_Dev_Part(String Change_Dev_Part)
    {
    	this.Change_Dev_Part.set(Change_Dev_Part);
    }
    
    public String getQty() {
    	return Qty.get();
    }
    public SimpleStringProperty QtyProperty()
    {
    	return Qty;
    }
    
    public void setQty(String Qty)
    {
    	this.Qty.set(Qty);
    }
    public String getChange_Dev_Unpart() {
    	return Change_Dev_Unpart.get();
    }
    public SimpleStringProperty Change_Dev_UnpartProperty()
    {
    	return Change_Dev_Unpart;
    }
    
    public void setChange_Dev_Unpart(String Change_Dev_Unpart)
    {
    	this.Change_Dev_Unpart.set(Change_Dev_Unpart);
    }
    
    public String getActual_Date() {
    	return Actual_Date.get();
    }
    public SimpleStringProperty Actual_DateProperty()
    {
    	return Actual_Date;
    }
    
    public void setActual_Date(String Actual_Date)
    {
    	this.Actual_Date.set(Actual_Date);
    }
    
    public String getChange_Actual_Date() {
    	return Change_Actual_Date.get();
    }
    public SimpleStringProperty Change_Actual_DateProperty()
    {
    	return Change_Actual_Date;
    }
    
    public void setChange_Actual_Date(String Change_Actual_Date)
    {
    	this.Change_Actual_Date.set(Change_Actual_Date);
    }
    
    public String getUser_Name() {
    	return User_Name.get();
    }
    public SimpleStringProperty User_NameProperty()
    {
    	return User_Name;
    }
    
    public void setUser_Name(String User_Name)
    {
    	this.User_Name.set(User_Name);
    }
    @Override
    public String toString() {
        return "User{" +
               "Number=" + Number.get() +
                ", Date='" + Date.get() + '\'' +
                ", WSH='" + WSH.get() + '\'' +
                ", Group='" + Group.get() + '\'' +
                ", Line='" + Line.get() + '\'' +
                ", Station='" + Station.get() + '\'' +
                ", Equipment='" + Equipment.get() + '\'' +
                ", Record_Type='" + Record_Type.get() + '\'' +
                ", Shift='" + Shift.get() + '\'' +
                ", Work_Time='" + Work_Time.get() + '\'' +
                ", Resp1='" + Resp1.get() + '\'' +
                ", Resp2='" + Resp2.get() + '\'' +
                ", Resp3='" + Resp3.get() + '\'' +
                ", Resp4='" + Resp4.get() + '\'' +
                ", Status='" + Status.get() + '\'' +
                ", Shift_Report='" + Shift_Report.get() + '\'' +
                ", Required_Action='" + Required_Action.get() + '\'' +
                ", Due_Date='" + Due_Date.get() + '\'' +
                ", Otv='" + Otv.get() + '\'' +
                ", Change_Dev_Comp='" + Change_Dev_Comp.get() + '\'' +
                ", Change_Dev_Part='" + Change_Dev_Part.get() + '\'' +
                ", Qty='" + Qty.get() + '\'' +
                ", Change_Dev_Unpart='" + Change_Dev_Unpart.get() + '\'' +
                ", Actual_Date='" + Actual_Date.get() + '\'' +
                ", Change_Actual_Date='" + Change_Actual_Date.get() + '\'' +
                ", User_Name='" + User_Name.get() + '\'' +
                '}';
    }
}
