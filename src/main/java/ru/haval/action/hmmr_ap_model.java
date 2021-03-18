package ru.haval.action;


import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import ru.haval.filter.WrTable;


public class hmmr_ap_model implements WrTable {

	public SimpleStringProperty Id = new SimpleStringProperty();
	public SimpleStringProperty PM_Num = new SimpleStringProperty();
	public SimpleStringProperty Type = new SimpleStringProperty();
	public SimpleStringProperty Desc = new SimpleStringProperty();
	public SimpleStringProperty Due_Date = new SimpleStringProperty();
	public SimpleStringProperty Equip = new SimpleStringProperty();
	public SimpleStringProperty inst_btn = new SimpleStringProperty();
	public SimpleStringProperty OFT = new SimpleStringProperty();
	public SimpleStringProperty OTV = new SimpleStringProperty();
	public SimpleStringProperty tsk_maker = new SimpleStringProperty();
	public SimpleStringProperty flag_otv = new SimpleStringProperty();
	public SimpleStringProperty flag_oft = new SimpleStringProperty();
	public SimpleStringProperty flag_tm = new SimpleStringProperty();
	public SimpleStringProperty icon = new SimpleStringProperty();
	public SimpleStringProperty icon_at = new SimpleStringProperty();
    public SimpleStringProperty user_id = new SimpleStringProperty();
    public SimpleStringProperty prior_img = new SimpleStringProperty();
    public SimpleStringProperty AT_img = new SimpleStringProperty();
    public SimpleStringProperty priorDescription = new SimpleStringProperty();
    public SimpleStringProperty ATDescription = new SimpleStringProperty();

    public SimpleStringProperty beginDate = new SimpleStringProperty();
    public SimpleStringProperty endDate = new SimpleStringProperty();
    public SimpleStringProperty beginTime = new SimpleStringProperty();
    public SimpleStringProperty endTime = new SimpleStringProperty();

    private Button oft;
	private Button tm;

    public Button getOft() {
        return oft;
    }

    public void setOft(Button oft) {
        this.oft = oft;
    }

    public Button getTm() {
        return tm;
    }

    public void setTm(Button tm) {
        this.tm = tm;
    }

    public static SimpleStringProperty total = new SimpleStringProperty();

    public hmmr_ap_model()
	{
		
	}
	public hmmr_ap_model(String Id, String PM_Num, String Type, String Desc, String Due_Date, String Equip, String inst_btn, String OFT, String OTV, String tsk_maker, String flag_otv, String flag_oft, String flag_tm, String icon, String icon_at)
	{
		this.Id.set(Id);
		this.PM_Num.set(PM_Num);
		this.Type.set(Type);
		this.Desc.set(Desc);
		this.Due_Date.set(Due_Date);
		this.Equip.set(Equip);
		this.inst_btn.set(inst_btn);
		this.OFT.set(OFT);
		this.OTV.set(OTV);
		this.tsk_maker.set(tsk_maker);
		this.flag_otv.set(flag_otv);
		this.flag_oft.set(flag_oft);
		this.flag_tm.set(flag_tm);
		this.icon.set(icon);
		this.icon_at.set(icon_at);
	}

    public static String getTotal() {
        return total.get();
    }

    public static SimpleStringProperty totalProperty() {
        return total;
    }

    public static void setTotal(String total) {
        hmmr_ap_model.total.set(total);
    }

    public String getPrior_img() {
        return prior_img.get();
    }

    public SimpleStringProperty prior_imgProperty() {
        return prior_img;
    }

    public void setPrior_img(String prior_img) {
        this.prior_img.set(prior_img);
    }

    public String getUser_id() {
        return user_id.get();
    }

    public SimpleStringProperty user_idProperty() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id.set(user_id);
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
    
    public String getPM_Num() {
        return PM_Num.get();
    }

    public SimpleStringProperty PM_NumProperty()
    {
    	return PM_Num;
    }
    
    public void setPM_Num(String PM_Num) {
        this.PM_Num.set(PM_Num);
    }
    
    public String getType() {
        return Type.get();
    }

    public SimpleStringProperty TypeProperty()
    {
    	return Type;
    }
    
    public void setType(String Type) {
        this.Type.set(Type);
    }
    
    public String getDesc() {
        return Desc.get();
    }

    public SimpleStringProperty DescProperty()
    {
    	return Desc;
    }
    
    public void setDesc(String Desc) {
        this.Desc.set(Desc);
    }
    
    public String getD_D() {
        return Due_Date.get();
    }

    public SimpleStringProperty D_DProperty()
    {
    	return Due_Date;
    }
    
    public void setD_D(String Due_Date) {
        this.Due_Date.set(Due_Date);
    }
    
    public String getEquip() {
        return Equip.get();
    }

    public SimpleStringProperty EquipProperty()
    {
    	return Equip;
    }
    
    public void setEquip(String Equip) {
        this.Equip.set(Equip);
    }
    
    public String getinst_btn() {
        return inst_btn.get();
    }

    public SimpleStringProperty inst_btnProperty()
    {
    	return inst_btn;
    }
    
    public void setinst_btn(String inst_btn) {
        this.inst_btn.set(inst_btn);
    }
    
    public String getOFT() {
        return OFT.get();
    }

    public SimpleStringProperty OFTProperty()
    {
    	return OFT;
    }
    
    public void setOFT(String OFT) {
        this.OFT.set(OFT);
    }
    
    public String getOTV() {
        return OTV.get();
    }

    public SimpleStringProperty OTVProperty()
    {
    	return OTV;
    }
    
    public void setOTV(String OTV) {
        this.OTV.set(OTV);
    }
    
    public String gettsk_maker() {
        return tsk_maker.get();
    }

    public SimpleStringProperty tsk_makerProperty()
    {
    	return tsk_maker;
    }
    
    public void settsk_maker(String tsk_maker) {
        this.tsk_maker.set(tsk_maker);
    }
    
    public String getflag_otv() {
        return flag_otv.get();
    }

    public SimpleStringProperty flag_otvProperty()
    {
    	return flag_otv;
    }
    
    public void setflag_otv(String flag_otv) {
        this.flag_otv.set(flag_otv);
    }
    
    public String getflag_oft() {
        return flag_oft.get();
    }

    public SimpleStringProperty flag_oftProperty()
    {
    	return flag_oft;
    }
    
    public void setflag_oft(String flag_oft) {
        this.flag_oft.set(flag_oft);
    }
    
    public String getflag_tm() {
        return flag_tm.get();
    }

    public SimpleStringProperty flag_tmProperty()
    {
    	return flag_tm;
    }
    
    public void setflag_tm(String flag_tm) {
        this.flag_tm.set(flag_tm);
    }
    
    public String geticon() {
        return icon.get();
    }

    public SimpleStringProperty iconProperty()
    {
    	return icon;
    }
    
    public void seticon(String icon) {
        this.icon.set(icon);
    }
    
    public String geticon_at() {
        return icon_at.get();
    }

    public SimpleStringProperty icon_atProperty()
    {
    	return icon_at;
    }

    public String getPriorDescription() {
        return priorDescription.get();
    }

    public SimpleStringProperty priorDescriptionProperty() {
        return priorDescription;
    }

    public void setPriorDescription(String priorDescription) {
        this.priorDescription.set(priorDescription);
    }

    public String getATDescription() {
        return ATDescription.get();
    }

    public SimpleStringProperty ATDescriptionProperty() {
        return ATDescription;
    }

    public void setATDescription(String ATDescription) {
        this.ATDescription.set(ATDescription);
    }

    public void seticon_at(String icon_at) {
        this.icon_at.set(icon_at);
    }
    
    @Override
    public String toString() {
        return "hmmr_ap_model{" +
               "Id=" + Id.get() +
               ", PM_Num='" + PM_Num.get() + '\'' +
                ", Type='" + Type.get() + '\'' +
                ", Desc='" + Desc.get() + '\'' +
                ", Due_Date='" + Due_Date.get() + '\'' +
                ", Equip='" + Equip.get() + '\'' +
                ", OFT='" + OFT.get() + '\'' +
                ", OTV='" + OTV.get() + '\'' +
                ", tsk_maker='" + tsk_maker.get() + '\'' +
                ", flag_otv='" + flag_otv.get() + '\'' +
                ", flag_oft='" + flag_oft.get() + '\'' +
                ", flag_tm='" + flag_tm.get() + '\'' +
                ", icon='" + icon.get() + '\'' +
                ", icon_at='" + icon_at.get() + '\'' +
                '}';
    }
    //getters and setters for make work records

    public String getBeginDate() {
        return beginDate.get();
    }

    public SimpleStringProperty beginDateProperty() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate.set(beginDate);
    }

    public String getEndDate() {
        return endDate.get();
    }

    public SimpleStringProperty endDateProperty() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate.set(endDate);
    }

    public String getBeginTime() {
        return beginTime.get();
    }

    public SimpleStringProperty beginTimeProperty() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime.set(beginTime);
    }

    public String getEndTime() {
        return endTime.get();
    }

    public SimpleStringProperty endTimeProperty() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime.set(endTime);
    }

    //getters for dynamic filter


    @Override
    public String getOtv() {
        return OTV.get();
    }

    @Override
    public String getOtv_Isp() {
        return OFT.get();
    }

    @Override
    public String getPMC() {
        return null;
    }

    @Override
    public String getGroup_PM() {
        return null;
    }

    @Override
    public String getnum_inst() {
        return PM_Num.get();
    }

    @Override
    public String getap_num() {
        return Id.get();
    }

    @Override
    public String getdata() {
        return D_DProperty().get();
    }

    @Override
    public String getshift_report() {
        return Desc.get();
    }
}
