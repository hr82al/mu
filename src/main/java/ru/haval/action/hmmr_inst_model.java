package ru.haval.action;

import javafx.beans.property.SimpleStringProperty;

public class hmmr_inst_model {
	
	public SimpleStringProperty Id = new SimpleStringProperty();
    public SimpleStringProperty num_instruction = new SimpleStringProperty();
    public SimpleStringProperty date_create = new SimpleStringProperty();
    public SimpleStringProperty date_change = new SimpleStringProperty();
    public SimpleStringProperty inst_pdf = new SimpleStringProperty();
    public SimpleStringProperty Version = new SimpleStringProperty();
    public SimpleStringProperty Model_Type_task = new SimpleStringProperty();
    public SimpleStringProperty PM_name = new SimpleStringProperty();
    public SimpleStringProperty Type_PM = new SimpleStringProperty();
    public SimpleStringProperty PM_Cycle1 = new SimpleStringProperty();
    public SimpleStringProperty PM_Cycle2 = new SimpleStringProperty();
    public SimpleStringProperty ON_Line_OFF_Line = new SimpleStringProperty();
    public SimpleStringProperty Power_ON_Power_OFF = new SimpleStringProperty();
    public SimpleStringProperty Position = new SimpleStringProperty();
    public SimpleStringProperty Src_info = new SimpleStringProperty();
    public SimpleStringProperty Src_Doc = new SimpleStringProperty();
    public SimpleStringProperty Qty_Specialist = new SimpleStringProperty();
    public SimpleStringProperty Prep_Work_time = new SimpleStringProperty();
    public SimpleStringProperty Work_Time = new SimpleStringProperty();
    public SimpleStringProperty Admission_2 = new SimpleStringProperty();
    public SimpleStringProperty Admission_3 = new SimpleStringProperty();
    public SimpleStringProperty Outfit_1 = new SimpleStringProperty();
    public SimpleStringProperty Outfit_2 = new SimpleStringProperty();
       
    public hmmr_inst_model()
    {
    	
    }
    
    public hmmr_inst_model(String Id, String num_instruction, String date_create, String date_change, String inst_pdf, String Version, String Model_Type_Task, String PM_name, String Type_PM, String PM_Cycle1, String PM_Cycle2,
    					   String ON_Line_OFF_Line, String Power_ON_Power_OFF, String Position, String Src_info, String Src_Doc, String Qty_Specialist,
    					   String Prep_Work_Time, String Work_Time, String Admission_2, String Admission_3, String Outfit_1, String Outfit_2)
    {
    	this.Id.set(Id);
    	this.num_instruction.set(num_instruction);
    	this.date_create.set(date_create);
    	this.date_change.set(date_change);
    	this.inst_pdf.set(inst_pdf);
    	this.Version.set(Version);
    	this.Model_Type_task.set(Model_Type_Task);
    	this.PM_name.set(PM_name);
    	this.Type_PM.set(Type_PM);
    	this.PM_Cycle1.set(PM_Cycle1);
    	this.PM_Cycle2.set(PM_Cycle2);
    	this.ON_Line_OFF_Line.set(ON_Line_OFF_Line);
    	this.Power_ON_Power_OFF.set(Power_ON_Power_OFF);
    	this.Position.set(Position);
    	this.Src_info.set(Src_info);
    	this.Src_Doc.set(Src_Doc);
    	this.Qty_Specialist.set(Qty_Specialist);
    	this.Prep_Work_time.set(Prep_Work_Time);
    	this.Work_Time.set(Work_Time);
    	this.Admission_2.set(Admission_2);
    	this.Admission_3.set(Admission_3);
    	this.Outfit_1.set(Outfit_1);
    	this.Outfit_2.set(Outfit_2);
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
    
    public String getnum_inst() {
        return num_instruction.get();
    }

    public SimpleStringProperty num_instProperty()
    {
    	return num_instruction;
    }
    
    public void setnum_inst(String num_instruction) {
        this.num_instruction.set(num_instruction);
    }
    
    public String getdate_create() {
        return date_create.get();
    }

    public SimpleStringProperty date_createProperty()
    {
    	return date_create;
    }
    
    public void sedate_create(String date_create) {
        this.date_create.set(date_create);
    }
    
    public String getdate_change() {
        return date_change.get();
    }

    public SimpleStringProperty date_changeProperty()
    {
    	return date_change;
    }
    
    public void setdate_change(String date_change) {
        this.date_change.set(date_change);
    }
    
    public String getinst_pdf() {
        return inst_pdf.get();
    }

    public SimpleStringProperty inst_pdfProperty()
    {
    	return inst_pdf;
    }
    
    public void setinst_pdf(String inst_pdf) {
        this.inst_pdf.set(inst_pdf);
    }
    
    public String getVer() {
        return Version.get();
    }

    public SimpleStringProperty VerProperty()
    {
    	return Version;
    }
    
    public void setVer(String Version) {
        this.Version.set(Version);
    }
    
    public String getMTT() {
        return Model_Type_task.get();
    }

    public SimpleStringProperty MTTProperty()
    {
    	return Model_Type_task;
    }
    
    public void setMTT(String Model_Type_task) {
        this.Model_Type_task.set(Model_Type_task);
    }
    
    public String getPM_name() {
        return PM_name.get();
    }

    public SimpleStringProperty PM_nameProperty()
    {
    	return PM_name;
    }
    
    public void setPM_name(String PM_name) {
        this.PM_name.set(PM_name);
    }
    
    public String gettype_PM() {
        return Type_PM.get();
    }

    public SimpleStringProperty type_PMProperty()
    {
    	return Type_PM;
    }
    
    public void settype_PM(String Type_PM) {
        this.Type_PM.set(Type_PM);
    }
    
    public String getPM_cycle1() {
        return PM_Cycle1.get();
    }

    public SimpleStringProperty PM_Cycle1Property()
    {
    	return PM_Cycle1;
    }
    
    public void setPM_Cycle1(String PM_Cycle1) {
        this.PM_Cycle1.set(PM_Cycle1);
    }
    
    public String getPM_cycle2() {
        return PM_Cycle2.get();
    }

    public SimpleStringProperty PM_Cycle2Property()
    {
    	return PM_Cycle2;
    }
    
    public void setPM_Cycle2(String PM_Cycle2) {
        this.PM_Cycle2.set(PM_Cycle2);
    }
    
    public String getOlOl() {
        return ON_Line_OFF_Line.get();
    }

    public SimpleStringProperty OlOlProperty()
    {
    	return ON_Line_OFF_Line;
    }
    
    public void setOlOl(String ON_Line_OFF_Line) {
        this.ON_Line_OFF_Line.set(ON_Line_OFF_Line);
    }
    
    public String getPoPo() {
        return Power_ON_Power_OFF.get();
    }

    public SimpleStringProperty PoPoProperty()
    {
    	return Power_ON_Power_OFF;
    }
    
    public void setPoPo(String Power_ON_Power_OFF) {
        this.Power_ON_Power_OFF.set(Power_ON_Power_OFF);
    }
    
    public String getPos() {
        return Position.get();
    }

    public SimpleStringProperty PosProperty()
    {
    	return Position;
    }
    
    public void setPos(String Position) {
        this.Position.set(Position);
    }
    
    public String getS_info() {
        return Src_info.get();
    }

    public SimpleStringProperty S_infoProperty()
    {
    	return Src_info;
    }
    
    public void setS_info(String Src_info) {
        this.Src_info.set(Src_info);
    }
    
    public String getS_doc() {
        return Src_Doc.get();
    }

    public SimpleStringProperty S_docProperty()
    {
    	return Src_Doc;
    }
    
    public void setS_doc(String Src_Doc) {
        this.Src_Doc.set(Src_Doc);
    }
    
    public String getQty_s() {
        return Qty_Specialist.get();
    }

    public SimpleStringProperty Qty_sProperty()
    {
    	return Qty_Specialist;
    }
    
    public void setQty_s(String Qty_Specialist) {
        this.Qty_Specialist.set(Qty_Specialist);
    }
    
    public String getPWT() {
        return Prep_Work_time.get();
    }

    public SimpleStringProperty PWTProperty()
    {
    	return Prep_Work_time;
    }
    
    public void setPWT(String Prep_Work_time) {
        this.Prep_Work_time.set(Prep_Work_time);
    }
    
    public String getWT() {
        return Work_Time.get();
    }

    public SimpleStringProperty WTProperty()
    {
    	return Work_Time;
    }
    
    public void setWT(String Work_Time) {
        this.Work_Time.set(Work_Time);
    }
    
    public String getAdm_2() {
        return Admission_2.get();
    }

    public SimpleStringProperty Adm_2Property()
    {
    	return Admission_2;
    }
    
    public void setAdm_2(String Admission_2) {
        this.Admission_2.set(Admission_2);
    }
    
    public String getAdm_3() {
        return Admission_3.get();
    }

    public SimpleStringProperty Adm_3Property()
    {
    	return Admission_3;
    }
    
    public void setAdm_3(String Admission_3) {
        this.Admission_3.set(Admission_3);
    }
    
    public String getOF_1() {
        return Outfit_1.get();
    }

    public SimpleStringProperty OF_1Property()
    {
    	return Outfit_1;
    }
    
    public void setOF_1(String Outfit_1) {
        this.Outfit_1.set(Outfit_1);
    }
    
    public String getOF_2() {
        return Outfit_2.get();
    }

    public SimpleStringProperty OF_2Property()
    {
    	return Outfit_2;
    }
    
    public void setOF_2(String Outfit_2) {
        this.Outfit_2.set(Outfit_2);
    }
    
    @Override
    public String toString() {
        return "hmmr_inst_model{" +
               "Id=" + Id.get() +
                ", num_instruction='" + num_instruction.get() + '\'' +
                ", date_create='" + date_create.get() + '\'' +
                ", date_change='" + date_change.get() + '\'' +
                ", inst_pdf='" + inst_pdf.get() + '\'' +
                ", Version='" + Version.get() + '\'' +
                ", Model_Type_task='" + Model_Type_task.get() + '\'' +
                ", PM_name='" + PM_name.get() + '\'' +
                ", Type_PM='" + Type_PM.get() + '\'' +
                ", PM_Cycle1='" + PM_Cycle1.get() + '\'' +
                ", PM_Cycle2='" + PM_Cycle2.get() + '\'' +
                ", ON_Line_OFF_Line='" + ON_Line_OFF_Line.get() + '\'' +
                ", Power_ON_Power_OFF='" + Power_ON_Power_OFF.get() + '\'' +
                ", Position='" + Position.get() + '\'' +
                ", Src_info='" + Src_info.get() + '\'' +
                ", Src_Doc='" + Src_Doc.get() + '\'' +
                ", Qty_Specialist='" + Qty_Specialist.get() + '\'' +
                ", Prep_Work_time='" + Prep_Work_time.get() + '\'' +
                ", Work_Time='" + Work_Time.get() + '\'' +
                ", Admission_2='" + Admission_2.get() + '\'' +
                ", Admission_3='" + Admission_3.get() + '\'' +
                ", Outfit_1='" + Outfit_1.get() + '\'' +
                ", Outfit_2='" + Outfit_2.get() + '\'' +
                '}';
    }

}