package ru.haval.action;

import javafx.css.Match;
import ru.haval.application.Main;

import javafx.event.ActionEvent;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

	public JFXButton prior = null;
	public JFXButton at = null;
	public Button apInstr = null;

    Main mn = new Main();

    private Image document = new Image(getClass().getResourceAsStream("document.png"));


	private static _query qr = new _query();
	private static s_class scl = new s_class();

    private HashMap<String, Image> priorImages = new HashMap<>();
    private HashMap<String, String> pathToPriorImg = new HashMap<>();
    private HashMap<String, Image> atImages = new HashMap<>();
    private HashMap<String, String>  pathToAtImg = new HashMap<>();
    private HashMap<String, String> priorDescriptions = new HashMap<>();
    private HashMap<String, String> pathToInstrs = new HashMap<>();

    public void init() {
        //init Prior
        if(prior == null) {
            Tooltip tooltip = new Tooltip();
            JFXButton iv = new JFXButton();
            //запрещаем бегунку прокрутки возвращаться назад после нажатия кнопки
            iv.setFocusTraversable(false);


            //       String test = data.geticon();
            BufferedImage bufferedImage;
            try {
                if (!geticon().equals("1")) {
                    if (!pathToPriorImg.containsKey(geticon())) {
                        pathToPriorImg.put(geticon(), qr._select_prior_img(geticon()));
                    }
                    String tmpIcon = pathToPriorImg.get(geticon());
                    //System.out.println(tmpIcon);
                    if (!priorImages.containsKey(tmpIcon)) {
                        bufferedImage = ImageIO.read(new File(tmpIcon));
                        priorImages.put(tmpIcon, SwingFXUtils.toFXImage(bufferedImage, null));
                    }
                    Image image = priorImages.get(tmpIcon);
                    //iv.setImage(image);
                    iv.setGraphic(new ImageView(image));
                }
            } catch (IOException e) {
                scl._AlertDialog(e.getMessage() + " prior_controller", "Ошибка загрузки изображения");
            }
            String tmpID = getId().substring(2);
            if (!priorDescriptions.containsKey(tmpID)) {
                priorDescriptions.put(tmpID, qr._select_prior_desc(tmpID));
            }

            iv.setOnMouseEntered(new EventHandler<Event>() {

                @Override
                public void handle(Event event) {
                    tooltip.setText(priorDescriptions.get(tmpID));
                    tooltip.setStyle("-fx-font-size: 14px");
                    Tooltip.install(iv, tooltip);
                }
            });
            setPrior(iv);
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
                    if (!pathToAtImg.containsKey(geticon())) {
                        pathToAtImg.put(geticon_at(), qr._select_recStr("hmmr_activity_type", "Icon",
                                "del_rec", "Name", geticon_at()));
                        String tmpIcon = pathToAtImg.get(geticon_at());
                        if (!atImages.containsKey(tmpIcon)) {
                            bufferedImage2 = ImageIO.read(new File(tmpIcon));
                            atImages.put(tmpIcon, SwingFXUtils.toFXImage(bufferedImage2, null));
                        }
                    }

                    Image image = atImages.get(pathToAtImg.get(geticon_at()));
                    iv2.setGraphic(new ImageView(image));
                }
            } catch (IOException e) {
                scl._AlertDialog(e.getMessage() + " prior_controller", "Ошибка загрузки изображения");
            }
            iv2.setOnMouseEntered(new EventHandler<Event>() {

                @Override
                public void handle(Event event) {
                    tooltip2.setText(qr._select_at_desc(getId().substring(2)));
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
            //System.out.println(getinst_btn());
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
            Platform.runLater(() -> {
                btn.setGraphic(new ImageView(document));
            });

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
        if (path.charAt(1) == ':') {
            return "//" + Config.getInstance().getAddress() + "/mu/" + path.substring(3);
        }
        Pattern pattern = Pattern.compile("^//\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}/");
        Matcher matcher = pattern.matcher(path);
        if (matcher.find()) {
            return "//" + Config.getInstance().getAddress() + "/" + path.substring(matcher.end());
        }
        return path;
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
}
