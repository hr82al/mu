package ru.haval.action;

import ru.haval.application.Main;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleStringProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ru.haval.application.conn_connector;
import ru.haval.config.Config;
import ru.haval.db._query;
import ru.haval.share_class.s_class;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class hmmr_ap_model {
    private static int BUTTON_WIDTH = 50;

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

	public Button oft = null;
	public Button tm = null;

    private static Main mn = new Main();

    private static _query qr = new _query();
	private static s_class scl = new s_class();


    //private static HashMap<String, String> pathToPriorImg = new HashMap<>();

    //private static HashMap<String, String>  pathToAtImg = new HashMap<>();
    //private static HashMap<String, String> priorDescriptions = new HashMap<>();


    public void init() {
        //init tm owner
        if (tm == null) {
            Button bTm = new Button();
            //запрещаем бегунку прокрутки возвращаться назад после нажатия кнопки
            bTm.setFocusTraversable(false);
            //устанавливаем номер ар в текст кнопки
            bTm.setText("");
            bTm.setPrefWidth(BUTTON_WIDTH);
            bTm.setPrefHeight(35);
            bTm.setText(gettsk_maker());

            //Подтверждать задачу может только тот кто ее создал
            if (user_id.get().equals(conn_connector.USER_ID)) //|| qr._select_oft(data.getId().substring(2)).equals(USER_S))
                bTm.setDisable(false);
            else
                bTm.setDisable(true);

            setTm(bTm);
        }
        //init oft
        if (oft == null) {
            Button bOft = new Button();
            //запрещаем бегунку прокрутки возвращаться назад после нажатия кнопки
            bOft.setFocusTraversable(false);
            //устанавливаем номер ар в текст кнопки
            bOft.setText("");
            bOft.setPrefWidth(BUTTON_WIDTH);
            bOft.setPrefHeight(35);
            bOft.setText(getOFT());


            //Подтверждать задачу может только тот кто ее создал или ответсвенный за задачу
            if (user_id.get().equals(conn_connector.USER_ID) || OFT.get().equals(apwr_controller.USER_S))
                bOft.setDisable(false);
            else
                bOft.setDisable(true);

            //qr._update_calc_field(data.getId().substring(2));
            setOft(bOft);
        }
    }

    private String correctPathToInstr(String path) {
        if (path.length() < 2) {
            return path;
        }
/*        if (path.charAt(1) == ':') {
            return "//" + Config.getInstance().getAddress() + "/mu/" + path.substring(3);
        }*/
        Pattern pattern = Pattern.compile("^//\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}/");
        Matcher matcher = pattern.matcher(path);
        if (matcher.find()) {
            return "//" + Config.getInstance().getAddress() + "/" + path.substring(matcher.end());
        }
        return path;
    }

    public Button getTm() {
        if (tm == null) {
            init();
        }

        //Если ответственный или владелец по этой задаче ее подтверждает то ставим кнопку в AP на владельце за задачу зеленой
        if (getflag_tm().equals("2"))
            tm.setStyle("-fx-background-color: green");
        else if (getflag_tm().equals("1"))
            tm.setStyle("-fx-background-color: yellow");
        return tm;
    }

    public void setTm(Button tm) {

        this.tm = tm;
    }

    public Button getOft() {
        if (oft == null) {
            init();
        }
        //Если ответственный или владелец по этой задаче ее подтверждает то ставим кнопку в AP на ответственном за задачу желтой
        if (getflag_oft().equals("2"))
            oft.setStyle("-fx-background-color: green");
        else if (getflag_oft().equals("1"))
            oft.setStyle("-fx-background-color: yellow");
        return oft;
    }

    public void setOft(Button oft) {
        this.oft = oft;
    }

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
    public void update(){
        tm = null;
        oft = null;
        init();
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
}
