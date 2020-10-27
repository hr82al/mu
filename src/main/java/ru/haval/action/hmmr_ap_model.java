package ru.haval.action;

import ru.haval.application.Main;

import javafx.event.ActionEvent;
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

	public Button otv = null;
	public Button oft = null;
	public Button tm = null;
	public JFXButton at = null;
	public Button apInstr = null;

    private static Main mn = new Main();

    private static _query qr = new _query();
	private static s_class scl = new s_class();


    //private static HashMap<String, String> pathToPriorImg = new HashMap<>();
    private static HashMap<String, Image> atImages = new HashMap<>();
    //private static HashMap<String, String>  pathToAtImg = new HashMap<>();
    private static HashMap<String, String> priorDescriptions = new HashMap<>();


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
        //init otv
        if (otv == null) {
            Button bOtv = new Button();
            //запрещаем бегунку прокрутки возвращаться назад после нажатия кнопки
            bOtv.setFocusTraversable(false);
            //устанавливаем номер ар в текст кнопки
            bOtv.setText("");
            bOtv.setPrefWidth(BUTTON_WIDTH);
            bOtv.setPrefHeight(35);
            bOtv.setText(getOTV());

            //Подтверждать задачу может только тот кто ее создал или ответсвенный за задачу
            if(user_id.get().equals(conn_connector.USER_ID) || OFT.get().equals(apwr_controller.USER_S))
                bOtv.setDisable(false);
            else
                bOtv.setDisable(true);

            //qr._update_calc_field(getId().substring(2));
            setOtv(bOtv);
        }

        //init At
        if (at == null) {
            Tooltip tooltip2 = new Tooltip();
            JFXButton iv2 = new JFXButton();
            //запрещаем бегунку прокрутки возвращаться назад после нажатия кнопки
            iv2.setFocusTraversable(false);
            BufferedImage bufferedImage2;
            try {
                if (!geticon_at().equals("1")) {
                    /*if (!pathToAtImg.containsKey(geticon())) {
                        pathToAtImg.put(geticon_at(), qr._select_recStr("hmmr_activity_type", "Icon",
                                "del_rec", "Name", geticon_at()));
                        String tmpIcon = pathToAtImg.get(geticon_at());

                        if (!atImages.containsKey(tmpIcon)) {
                            bufferedImage2 = ImageIO.read(new File(tmpIcon));
                            atImages.put(tmpIcon, SwingFXUtils.toFXImage(bufferedImage2, null));
                        }
                    }*/
                    String pathAT = correctPathToInstr(AT_img.get());
                    if (!atImages.containsKey(pathAT)) {
                        bufferedImage2 = ImageIO.read(new File(pathAT));
                        atImages.put(pathAT, SwingFXUtils.toFXImage(bufferedImage2, null));
                    }
                    Image image = atImages.get(pathAT);
                    iv2.setGraphic(new ImageView(image));
                }
            } catch (IOException e) {
                scl._AlertDialog(e.getMessage() + " prior_controller", "Ошибка загрузки изображения");
            }
            iv2.setOnMouseEntered(new EventHandler<Event>() {

                @Override
                public void handle(Event event) {
                    tooltip2.setText(qr._select_at_desc(getId().substring(2)));
                    tooltip2.setText(ATDescriptionProperty().get());
                    tooltip2.setStyle("-fx-font-size: 14px");
                    Tooltip.install(iv2, tooltip2);
                }
            });
            setAt(iv2);
        }

        //init apInstr
        if (apInstr == null) {
            Button btn = new Button();
            setinst_btn(correctPathToInstr(getinst_btn()));

            if (getinst_btn().equals("-") || getinst_btn().equals("null"))
                btn.setDisable(true);
            else {
                File file = new File(getinst_btn());
                if (file.exists()) {
                    btn.setDisable(false);
                } else {
                    btn.setDisable(true);
                }
            }

            btn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("document.png"))));

            //устанавливаем checkbox если в базе в этом поле стоит 1
            //     btn.setText(data.getap_num());
            //запрещаем бегунку прокрутки возвращаться назад после нажатия кнопки
            //btn.setFocusTraversable(false);
            btn.setOnAction(new EventHandler<ActionEvent>() {

                @SuppressWarnings("static-access")
                @Override
                public void handle(ActionEvent event) {
                    //FIXME Need to change other cells
                    try {
                        File inst_path = new File(qr._select_inst_for_ap(getId()));//table_ap.getSelectionModel().getSelectedItem()
                        mn._run_excel(inst_path);
                    } catch (Exception e) {
                        scl._AlertDialog("Сначала выделите строку!", "Ошибка!");
                    }
//			                    	Runtime runtime = Runtime.getRuntime();
//			                    	if(inst_path.length() != 0)
//										runtime.exec("excel " + inst_path);
                    //btn.setDisable(true);
                }
            });
            setApInstr(btn);
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

    public Button getOtv() {
        if (otv == null) {
            init();
        }
        // Check if the button changed color
        //Если Все записи в WR по этой задаче подтверждены то ставим кнопку в AP на исполнителе желтой
        if (getflag_otv().equals("2"))
            otv.setStyle("-fx-background-color: green");
        else if (getflag_otv().equals("1"))
            otv.setStyle("-fx-background-color: yellow");
        return otv;
    }

    public void setOtv(Button otv) {
        this.otv = otv;
    }

    public JFXButton getPrior() {
        if (prior == null) {
            init();
        }
        return prior;
    }

    public void setPrior(JFXButton prior) {
        this.prior = prior;
    }

    public JFXButton getAt() {
        if (at == null) {
            init();
        }
        return at;
    }

    public void setAt(JFXButton at) {
        this.at = at;
    }

    public Button getApInstr() {
        if (apInstr == null) {
            init();
        }
        return apInstr;
    }

    public void setApInstr(Button apInstr) {
        this.apInstr = apInstr;
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
        otv = null;
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
