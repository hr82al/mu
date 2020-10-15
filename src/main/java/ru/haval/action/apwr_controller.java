package ru.haval.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;

import org.apache.commons.lang3.ArrayUtils;
import ru.haval.application.Main;
import  ru.haval.application.conn_connector;
import  ru.haval.data.FxDatePickerConverter;
import  ru.haval.db._query;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;
import net.sf.jasperreports.engine.JRException;
import report.ExportToExcel;
import report.PrintReport;
import ru.haval.share_class.TooltippedTableCell;
import ru.haval.share_class.s_class;

	
public class apwr_controller {
	
	@FXML
	public TableView<hmmr_ap_model> table_ap = new TableView<>();
	
	@FXML
	TableView<hmmr_wr_model> table_wr = new TableView<hmmr_wr_model>();
	
	@FXML
	public TableView<hmmr_wp_model> table_wp = new TableView<>();
	
	@FXML
	TableColumn<hmmr_ap_model, String> n_ap, n_pm_ap, type_ap, desc_ap, dd_ap, equip_ap, otv_task_ap, otv_ap;
	
	@FXML
	TableColumn<hmmr_wr_model, String> num_wr, shift_report_wr, req_action_wr, actual_time_wr, actual_time1_wr, data_wr, equip_wr, record_type_wr, resp_wr, 
									   status_wr, qty_wr, ap_num_wr; 
	
	@FXML
	TableColumn<hmmr_wp_model, String> n_wp, n_pm_wp, type_wp, desc_wp, dd_wp, equip_wp, otv_task_wp, otv_wp, col_tm_wp, col_oft_wp, col_otv_wp;
	
	@FXML
	DatePicker begin_data, last_data;
	
	@FXML
	ScrollPane sp_wr;
	
	@FXML
	JFXButton print_tsk, add_wr, create_ap, upd_ap, private_ap, showall_ap, upd_table_ap, upd_wr, clear_filter, upd_table_wr, set_btn, rus_btn, chn_btn, usa_btn, assembly, logistics, paint, stamp, welding, export_excel, upd_tbl_wp, upd_rec_wp;
	
	@FXML
	Label from_wr, to_wr, title_wo, title_wr, title_wp;
	
	@FXML
	HBox hb1, hb2, hb3, hb_wr1, hb_wr2, hb_wr3, hb1_wp, hb2_wp, hb3_wp;
	
	@FXML
	VBox vb, vb_wr, vb_wp;
	
	@FXML
	AnchorPane an_pn1, an_pn2, an_pn_wr, an_pn_wp;
	
	@FXML
	TabPane tb_pn;
	
	@FXML
	ComboBox<String> filtre_apwr, shop_resp_wr;
	
	@FXML
	JFXRadioButton r_shop_wr, r_resp_wr, r_OFT_wr;
	
	@FXML
	Tab tab_wp, tab_wo, tab_wr;
	
	_query qr = new _query();
	s_class scl = new s_class();
	FxDatePickerConverter fx_dp = new FxDatePickerConverter();
	Main mn = new Main();
	
	private String _date, _count;
	int _duration;
	
	@SuppressWarnings("unused")
	private String _due_date, _instruct, _shop, _lm, _os, _equip, _id_pm, _pmname,_type, _otf, _id, _group_pm, _sql_rez, _group_eq, _pm_exec, _activity_type; 
	@SuppressWarnings("unused")
	private String _due_date_wo, _instruct_wo, _shop_wo, _lm_wo, _os_wo, _equip_wo, _id_pm_wo, _pmname_wo,_type_wo, _otf_wo, _id_wo, _group_pm_wo, _sql_rez_wo, _group_eq_wo, _pm_exec_wo, _icon_at_wo; 
	public Stage stage = new Stage();
	//Thread t, b;
	private HashMap<String, Image> priorImages = new HashMap<>();
	private boolean _flag = true;
	public static ObservableList<TableColumn<hmmr_wr_model, ?>> columns_wr;
	public static ObservableList<TableColumn<hmmr_ap_model, ?>> columns_ap;

	private String table_wr_filter_state = null;
		
	ObservableList<String> _chk = FXCollections.observableArrayList();
	ObservableList<String> _chk_color = FXCollections.observableArrayList();
	ObservableList<String> _get_field = FXCollections.observableArrayList();
	ObservableList<String> _get_data_from_wp = FXCollections.observableArrayList();
	ObservableList<String> _get_data_dly = FXCollections.observableArrayList();
	public static ObservableList<hmmr_ap_model> _table_update = FXCollections.observableArrayList();
	public static ObservableList<hmmr_wr_model> _table_update_wr = FXCollections.observableArrayList();
	public static ObservableList<hmmr_wp_model> _table_update_wp = FXCollections.observableArrayList();
	
	public static List<hmmr_ap_model> row;
	public static String _pmnum_ap, _type_ap, _description_ap, _due_date_ap, _equip_ap, _inst_ap, _oft_ap, _otv_ap, _id_ap, _idap_for_wr = "null", _icon, _icon_at;
	public static String _pmnum_wp, _type_wp, _description_wp, _due_date_wp, _equip_wp, _inst_wp, _oft_wp, _otv_wp, _id_wp;
	
	public static String _id_wr, _qty_wr, _user_wr, _ap_num_wr, _data_wr, _equip_wr, _record_type_wr, _work_time,  _resp_wr,_resp2_wr,_resp3_wr,_resp4_wr, _status_wr, _shift_report_wr, 
	_req_action_wr, _actual_time_wr, _actual_time1_wr,_actual_time2_wr,_actual_time3_wr,_actual_time4_wr, _actual_date,_actual_date_2,_actual_date_3,_actual_date_4, 
	_actual_date1,_actual_date2,_actual_date3,_actual_date4, _hours1,_hours1_2,_hours1_3,_hours1_4, _min1, _hours2,_hours2_2,_hours2_3,_hours2_4, _min2, _user_number, _activity_type_wr;
	public static String before_date, after_date;
	String _get_text_btn;
	private String conf_l, inst_l, c_resp, c_oft, c_own, lbl_assembly, lbl_logistics, lbl_paint, lbl_stamp, lbl_welding, prior_img, at_title;
	public static String SHOP_NAME, USER_S;
	TableColumn<hmmr_wr_model, Button> favoriteColumn = new TableColumn<hmmr_wr_model, Button>(conf_l);
	TableColumn<hmmr_ap_model, Button> favoriteColumn2 = new TableColumn<hmmr_ap_model, Button>(inst_l);
	TableColumn<hmmr_ap_model, Button> otv = new TableColumn<hmmr_ap_model, Button>(c_resp);
	TableColumn<hmmr_ap_model, Button> oft = new TableColumn<hmmr_ap_model, Button>(c_oft);
	TableColumn<hmmr_ap_model, Button> tm = new TableColumn<hmmr_ap_model, Button>(c_own);
	TableColumn<hmmr_ap_model, JFXButton> prior = new TableColumn<hmmr_ap_model, JFXButton>(prior_img);
	TableColumn<hmmr_ap_model, JFXButton> at = new TableColumn<hmmr_ap_model, JFXButton>(at_title);
	TableColumn<hmmr_wr_model, JFXButton> at_wr = new TableColumn<hmmr_wr_model, JFXButton>(at_title);
	TableColumn<hmmr_wp_model, Button> wp_inst = new TableColumn<hmmr_wp_model, Button>(inst_l);
	Tooltip tip;
	String str_set_btn = "Вызов окна редактирования таблиц БД", sort_filter = "Фильтр", sort_tsk = "Выполненные задачи", sort_w_otv = "Задачи без исполнителя", sort_clear_filter = "Сбросить фильтр";
	boolean chk_btn = true; //Проверяем какая из кнопок нажата: Личные - true; Показать все - false
	public static int flag = 0; //Переменная нужна для обновления таблицы, например: если отсортировали таблицу по номеру, то при нажатии на кнопку обновить таблицу
	//таблица примет первоначальный вид заполнения данными, а надо чтобы при нажатии на кнопку сохранился бы тот вид который стал во время сортировки
	//т. е. в зависимости от значения этого флага при нажатии на кнопку обновления таблицы, будем подставлять тот запрос, который использовался для фильтра
	public String ID_WR, SHOP_NAME_A = "A";
	public static String  SORT_SHOP, SORT_RESP;
	ObservableList<String> filtre = FXCollections.observableArrayList();

	private JFXButton[] shopButtons;

	final static String[] SHOP_LETTERS = new String[]{"A", "L", "P", "S", "W"};

	private Set<String> availableShops;
		
	@SuppressWarnings({ "unchecked" })
	@FXML
	public void initialize()
	{
		shopButtons = new JFXButton[]{assembly, logistics, paint, stamp, welding};
		initSops();

		USER_S = scl.parser_str(qr._select_user(conn_connector.USER_ID), 1);//сокращенное имя пользователя из таблицы STAFF
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		Double screen_width = primaryScreenBounds.getWidth();
		Double screen_hight = primaryScreenBounds.getHeight(); 
		
			sp_wr.setPrefWidth(screen_width);
			sp_wr.setPrefHeight(screen_hight);
			an_pn1.setPrefWidth(screen_width - 19);
			an_pn1.setPrefHeight(screen_hight - 50);
			tb_pn.setPrefWidth(screen_width - 19);
			tb_pn.setPrefHeight(screen_hight - 50);
			an_pn2.setPrefWidth(screen_width - 50);
			an_pn2.setPrefHeight(screen_hight - 50);
			vb.setPrefWidth(screen_width - 50);
			vb.setPrefHeight(screen_hight - 50);
			hb1.setPrefWidth(screen_width - 50);
			hb1.setPrefHeight(70.0);
			hb2.setPrefWidth(screen_width - 50);
			hb2.setPrefHeight(screen_hight - 220);
			hb3.setPrefWidth(screen_width - 50);
			hb3.setPrefHeight(70.0);
			table_ap.setPrefWidth(screen_width-100);
			table_ap.setPrefHeight(screen_hight-200);
			title_wo.setPrefWidth(830.0);
			if(screen_width == 1920.0)
				desc_ap.setPrefWidth(810.0);
			if(screen_width == 1768.0)
				desc_ap.setPrefWidth(860.0);
			if(screen_width == 1600.0)
				desc_ap.setPrefWidth(520.0);
			if(screen_width == 1440.0)
				desc_ap.setPrefWidth(340.0);
			
			an_pn_wr.setPrefWidth(screen_width - 20);
			an_pn_wr.setPrefHeight(screen_hight - 20);
			vb_wr.setPrefWidth(screen_width - 20);
			vb_wr.setPrefHeight(screen_hight - 20);
			hb_wr1.setPrefWidth(screen_width - 20);
			hb_wr1.setPrefHeight(70.0);
			hb_wr2.setPrefWidth(screen_width - 20);
			hb_wr2.setPrefHeight(screen_hight - 220);
			hb_wr3.setPrefWidth(screen_width - 20);
			hb_wr3.setPrefHeight(70.0);
			table_wr.setPrefWidth(screen_width-70);
			table_wr.setPrefHeight(screen_hight-200);
			
			if(screen_width == 1920.0) {
				shift_report_wr.setPrefWidth(490.0);
				req_action_wr.setPrefWidth(490.0);
				title_wr.setPrefWidth(850.0);
			}
			if(screen_width == 1768.0) {
				shift_report_wr.setPrefWidth(420.0);
				req_action_wr.setPrefWidth(420.0);
				title_wr.setPrefWidth(750.0);
			}
			if(screen_width == 1600.0) {
				shift_report_wr.setPrefWidth(310.0);
				req_action_wr.setPrefWidth(310.0);
				title_wr.setPrefWidth(580.0);
			}
			if(screen_width == 1440.0) {
				shift_report_wr.setPrefWidth(250.0);
				req_action_wr.setPrefWidth(250.0);
			}
			
			sp_wr.setPrefWidth(screen_width);
			sp_wr.setPrefHeight(screen_hight);
			an_pn1.setPrefWidth(screen_width - 19);
			an_pn1.setPrefHeight(screen_hight - 50);
			tb_pn.setPrefWidth(screen_width - 19);
			tb_pn.setPrefHeight(screen_hight - 50);
			an_pn_wp.setPrefWidth(screen_width - 50);
			an_pn_wp.setPrefHeight(screen_hight - 50);
			vb_wp.setPrefWidth(screen_width - 50);
			vb_wp.setPrefHeight(screen_hight - 50);
			hb1_wp.setPrefWidth(screen_width - 50);
			hb1_wp.setPrefHeight(70.0);
			hb2_wp.setPrefWidth(screen_width - 50);
			hb2_wp.setPrefHeight(screen_hight - 220);
			hb3_wp.setPrefWidth(screen_width - 50);
			hb3_wp.setPrefHeight(70.0);
			table_wp.setPrefWidth(screen_width-100);
			table_wp.setPrefHeight(screen_hight-200);
			title_wp.setPrefWidth(900.0);
			if(screen_width == 1920.0)
				desc_wp.setPrefWidth(900.0);
			if(screen_width == 1768.0)
				desc_wp.setPrefWidth(750.0);
			if(screen_width == 1600.0)
				desc_wp.setPrefWidth(610.0);
			if(screen_width == 1440.0)
				desc_wp.setPrefWidth(420.0);
			
		begin_data.setValue(LocalDate.now().minusDays(7));
		last_data.setValue(LocalDate.now());
		
		private_ap.setDisable(true);
		clear_filter.setDisable(true);
		print_tsk.setDisable(true);
		export_excel.setDisable(true);
		
		if(conn_connector.USER_ROLE.equals("Technics"))
		{
			create_ap.setDisable(true);
		}
		
		shopsEnableAll();
		//sopsSelectInitial();



		if(conn_connector.LANG_ID == 1)
			lang_fun("en", "EN");
		if(conn_connector.LANG_ID == 0)
			lang_fun("ru", "RU");
		if(conn_connector.LANG_ID == 2)
			lang_fun("zh", "CN");
		if(conn_connector.LANG_ID == -1)
			lang_fun("ru", "RU");
		
		Platform.runLater(() -> {
			Image imageOk = new Image(getClass().getResourceAsStream("settings.png"));
			set_btn.setGraphic(new ImageView(imageOk)); });
		
		Platform.runLater(() -> {
			Image imageOk = new Image(getClass().getResourceAsStream("russia_flag.png"));
			rus_btn.setGraphic(new ImageView(imageOk)); });
		
		Platform.runLater(() -> {
			Image imageOk = new Image(getClass().getResourceAsStream("china_flag.png"));
			chn_btn.setGraphic(new ImageView(imageOk)); });
		
		Platform.runLater(() -> {
			Image imageOk = new Image(getClass().getResourceAsStream("united_flag.png"));
			usa_btn.setGraphic(new ImageView(imageOk)); });
		Platform.runLater(() -> {
			Image imageOk = new Image(getClass().getResourceAsStream("assembly.png"));
			assembly.setGraphic(new ImageView(imageOk)); });
		Platform.runLater(() -> {
			Image imageOk = new Image(getClass().getResourceAsStream("logistics.png"));
			logistics.setGraphic(new ImageView(imageOk)); });
		Platform.runLater(() -> {
			Image imageOk = new Image(getClass().getResourceAsStream("Painting.png"));
			paint.setGraphic(new ImageView(imageOk)); });
		Platform.runLater(() -> {
			Image imageOk = new Image(getClass().getResourceAsStream("stamp.png"));
			stamp.setGraphic(new ImageView(imageOk)); });
		Platform.runLater(() -> {
			Image imageOk = new Image(getClass().getResourceAsStream("welding.png"));
			welding.setGraphic(new ImageView(imageOk)); });
		
		if(conn_connector.LANG_ID == 0 || conn_connector.LANG_ID == -1)
		{
			rus_btn.setDisable(true);
			chn_btn.setDisable(false);
			usa_btn.setDisable(false);
			favoriteColumn.setText(conf_l);
			favoriteColumn2.setText(inst_l);
			wp_inst.setText(inst_l);
			otv.setText(c_resp);
			oft.setText(c_oft);
			tm.setText(c_own);
			prior.setText(prior_img);
			at.setText(at_title);
			at_wr.setText(at_title);
		}
		if(conn_connector.LANG_ID == 1)
		{
			rus_btn.setDisable(false);
			chn_btn.setDisable(false);
			usa_btn.setDisable(true);
			favoriteColumn.setText(conf_l);
			favoriteColumn2.setText(inst_l);
			wp_inst.setText(inst_l);
			otv.setText(c_resp);
			oft.setText(c_oft);
			tm.setText(c_own);
			prior.setText(prior_img);
			at.setText(at_title);
			at_wr.setText(at_title);
		}
		if(conn_connector.LANG_ID == 2)
		{
			rus_btn.setDisable(false);
			chn_btn.setDisable(true);
			usa_btn.setDisable(false);
			favoriteColumn.setText(conf_l);
			favoriteColumn2.setText(inst_l);
			wp_inst.setText(inst_l);
			otv.setText(c_resp);
			oft.setText(c_oft);
			tm.setText(c_own);
			prior.setText(prior_img);
			at.setText(at_title);
			at_wr.setText(at_title);
		}
		
		scl._style(upd_tbl_wp);
		scl._style(upd_rec_wp);
		
		rus_btn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				conn_connector.LANG_ID = 0;
				lang_fun("ru", "RU");
				favoriteColumn.setText(conf_l);
				favoriteColumn2.setText(inst_l);
				wp_inst.setText(inst_l);
				otv.setText(c_resp);
				oft.setText(c_oft);
				tm.setText(c_own);
				prior.setText(prior_img);
				at.setText(at_title);
				at_wr.setText(at_title);
				rus_btn.setDisable(true);
				chn_btn.setDisable(false);
				usa_btn.setDisable(false);
				filtre.removeAll(filtre);
				filtre.add(sort_filter);
				filtre.add(sort_tsk);
				filtre.add(sort_w_otv);
				filtre.add(sort_clear_filter);
				filtre_apwr.getSelectionModel().selectFirst();
				shop_resp_wr.setPromptText(sort_filter);
			}
		});
		usa_btn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				conn_connector.LANG_ID = 1;
				lang_fun("en", "EN");
				favoriteColumn.setText(conf_l);
				favoriteColumn2.setText(inst_l);
				wp_inst.setText(inst_l);
				otv.setText(c_resp);
				oft.setText(c_oft);
				tm.setText(c_own);
				prior.setText(prior_img);
				at.setText(at_title);
				at_wr.setText(at_title);
				rus_btn.setDisable(false);
				chn_btn.setDisable(false);
				usa_btn.setDisable(true);
				filtre.removeAll(filtre);
				filtre.add(sort_filter);
				filtre.add(sort_tsk);
				filtre.add(sort_w_otv);
				filtre.add(sort_clear_filter);
				filtre_apwr.getSelectionModel().selectFirst();
				shop_resp_wr.setPromptText(sort_filter);
			}
		});
		chn_btn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				conn_connector.LANG_ID = 2;
				lang_fun("zh", "CN");
				favoriteColumn.setText(conf_l);
				favoriteColumn2.setText(inst_l);
				wp_inst.setText(inst_l);
				otv.setText(c_resp);
				oft.setText(c_oft);
				tm.setText(c_own);
				prior.setText(prior_img);
				at.setText(at_title);
				at_wr.setText(at_title);
				rus_btn.setDisable(false);
				chn_btn.setDisable(true);
				usa_btn.setDisable(false);
				filtre.removeAll(filtre);
				filtre.add(sort_filter);
				filtre.add(sort_tsk);
				filtre.add(sort_w_otv);
				filtre.add(sort_clear_filter);
				filtre_apwr.getSelectionModel().selectFirst();
				shop_resp_wr.setPromptText(sort_filter);
			}
		});
		set_btn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				//Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
			    
			    Parent root = null;
				try {
					root = FXMLLoader.load(getClass().getResource("mu_main_window.fxml"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				Stage stage_set = new Stage();
				stage_set.initModality(Modality.WINDOW_MODAL);	
				stage_set.initOwner(conn_connector.getPrimaryStage());
		        Scene scene = new Scene(root);
		        stage_set.setTitle("M&U - Main Window"+" "+scl.parser_str(qr._select_user(conn_connector.USER_ID), 1)+"/"+scl.parser_str(qr._select_user(conn_connector.USER_ID), 2)+" "+scl.parser_str(qr._select_user(conn_connector.USER_ID), 3) +"  MU."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 4)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 5)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 6)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 0));
		        stage_set.setResizable(false);
		        
		        //stage.setWidth(primaryScreenBounds.getWidth() - 315);// - 77
		        //stage.setHeight(primaryScreenBounds.getHeight() - 15);// - 77
		        stage_set.setScene(scene);
		        
		        stage_set.show();
			}
		});
		
		filtre.add(sort_filter);
		filtre.add(sort_tsk);
		filtre.add(sort_w_otv);
		filtre.add(sort_clear_filter);
		
		filtre_apwr.setItems(filtre);
		filtre_apwr.getSelectionModel().selectFirst();
		
		filtre_apwr.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				//Вводим новую переменную SHOP_NAME_A т .к. в режиме Administrator мы можем просмотреть информацию по всем цехам, в любой другой
				//роли кроме Administrator мы можем работать только со своим цехом
				if(filtre_apwr.getSelectionModel().getSelectedIndex() == 1)
				{
					if(conn_connector.USER_ROLE.equals("Administrator"))
					{
							table_ap.setItems(qr._select_data_exectsk(SHOP_NAME_A));
							table_ap.getColumns().get(0).setVisible(false);
					        table_ap.getColumns().get(0).setVisible(true);
					        tableCellAlignCenter_green(dd_ap);
					}
					else
					{
							table_ap.setItems(qr._select_data_exectsk(SHOP_NAME));
							table_ap.getColumns().get(0).setVisible(false);
					        table_ap.getColumns().get(0).setVisible(true);
					        tableCellAlignCenter_green(dd_ap);
					}
					
				}
				if(filtre_apwr.getSelectionModel().getSelectedIndex() == 2)
				{
					if(conn_connector.USER_ROLE.equals("Administrator"))
					{
							table_ap.setItems(qr._select_data_without_otv(SHOP_NAME_A));
							table_ap.getColumns().get(0).setVisible(false);
					        table_ap.getColumns().get(0).setVisible(true);
					        tableCellAlignCenter_green(dd_ap);
					}
					else
					{
							table_ap.setItems(qr._select_data_without_otv(SHOP_NAME));
							table_ap.getColumns().get(0).setVisible(false);
					        table_ap.getColumns().get(0).setVisible(true);
					        tableCellAlignCenter_green(dd_ap);
					}
					
				}
				if(filtre_apwr.getSelectionModel().getSelectedIndex() == 3)
				{
					if(conn_connector.USER_ROLE.equals("Administrator"))
					{
						table_ap.setItems(qr._select_data_all_shop(SHOP_NAME_A));
						table_ap.getColumns().get(0).setVisible(false);
				        table_ap.getColumns().get(0).setVisible(true);
						for (JFXButton shop : shopButtons) {
							shop.setDisable(false);
						}
				        tableCellAlignCenter(dd_ap);
					}
					else
					{
						table_ap.setItems(qr._select_data_all_shop(SHOP_NAME));
						table_ap.getColumns().get(0).setVisible(false);
					    table_ap.getColumns().get(0).setVisible(true);
					    tableCellAlignCenter(dd_ap);
					}
				}
			}
		});
		
		@SuppressWarnings("rawtypes")
		TableColumn col_action = new TableColumn<>(inst_l);
		
		initData();
		
		n_ap.setCellValueFactory(CellData -> CellData.getValue().IdProperty());
		n_pm_ap.setCellValueFactory(CellData -> CellData.getValue().PM_NumProperty());
		type_ap.setCellValueFactory(CellData -> CellData.getValue().TypeProperty());
		desc_ap.setCellValueFactory(CellData -> CellData.getValue().DescProperty());
		dd_ap.setCellValueFactory(CellData -> CellData.getValue().D_DProperty());
		equip_ap.setCellValueFactory(CellData -> CellData.getValue().EquipProperty());
		col_action.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
			
		upd_ap.setDisable(true);
		upd_rec_wp.setDisable(true);
		table_ap.setEditable(true);
				
		num_wr.setCellValueFactory(CellData -> CellData.getValue().IdProperty());
		shift_report_wr.setCellValueFactory(CellData -> CellData.getValue().shift_reportProperty());
		req_action_wr.setCellValueFactory(CellData -> CellData.getValue().req_actionProperty());
		actual_time_wr.setCellValueFactory(CellData -> CellData.getValue().actual_timeProperty());
		actual_time1_wr.setCellValueFactory(CellData -> CellData.getValue().actual_time1Property());
		data_wr.setCellValueFactory(CellData -> CellData.getValue().dataProperty());
		equip_wr.setCellValueFactory(CellData -> CellData.getValue().equipProperty());
		record_type_wr.setCellValueFactory(CellData -> CellData.getValue().record_typeProperty());
		resp_wr.setCellValueFactory(CellData -> CellData.getValue().respProperty());
		status_wr.setCellValueFactory(CellData -> CellData.getValue().statusProperty());
		table_wr.setEditable(true);
		
		n_wp.setCellValueFactory(CellData -> CellData.getValue().IdProperty());
		n_pm_wp.setCellValueFactory(CellData -> CellData.getValue().PM_NumProperty());
		type_wp.setCellValueFactory(CellData -> CellData.getValue().TypeProperty());
		desc_wp.setCellValueFactory(CellData -> CellData.getValue().DescProperty());
		dd_wp.setCellValueFactory(CellData -> CellData.getValue().D_DProperty());
		equip_wp.setCellValueFactory(CellData -> CellData.getValue().EquipProperty());
		col_tm_wp.setCellValueFactory(CellData -> CellData.getValue().tsk_makerProperty());
		col_oft_wp.setCellValueFactory(CellData -> CellData.getValue().OFTProperty());
		col_otv_wp.setCellValueFactory(CellData -> CellData.getValue().OTVProperty());
		col_action.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
		
		//agree_wr.setDisable(true);
		upd_wr.setDisable(true);
		add_wr.setDisable(true);
		//Добавляем checkbox в table_wr
		final ObservableList<TableColumn<hmmr_wr_model, ?>> columns = table_wr.getColumns();
		favoriteColumn.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<hmmr_wr_model, Button>, ObservableValue<Button>>() {

                    @Override
                    public ObservableValue<Button> call(TableColumn.CellDataFeatures<hmmr_wr_model, Button> arg0) {
                        hmmr_wr_model data = arg0.getValue();
                        Button btn = new Button();
                      //запрещаем бегунку прокрутки возвращаться назад после нажатия кнопки
                        btn.setFocusTraversable(false);
                        //устанавливаем номер ар в текст кнопки
                        btn.setText("");
                        btn.setPrefWidth(30);
                        btn.setPrefHeight(30);
                        favoriteColumn.setStyle( "-fx-alignment: CENTER;");
                        
                        //Подтверждать задачу может только тот кто ее создал или ответсвенный за задачу
                        if(qr._select_userid(data.getap_num()).equals(conn_connector.USER_ID) || qr._select_oft(data.getap_num()).equals(USER_S))
                        	btn.setDisable(false);
                        else
                        	btn.setDisable(true);
                        
                        //Если хозяин задачи ее подтвердил, то делаем кнопку зеленой при инициализации таблицы
                        if(data.getqty())
                        	btn.setStyle("-fx-background-color: green");
                        else
                        	btn.setStyle("-fx-background-color: yellow");
                        
                        //Если задачу подтвердил ответственный за задачу, то делаем кнопку оранжевой при инициализации таблицы
                        if(data.getuser() && !data.getqty())
                        	btn.setStyle("-fx-background-color: green");
                        
                        //Если техник изменил статус задачи на Done, то делаем кнопку желтой при инициализации таблицы
                        if(qr._select_status(data.IdProperty().get().substring(2)).equals("New WR") && !data.getuser())
							btn.setStyle("-fx-background-color: yellow");
                        else if(qr._select_status(data.IdProperty().get().substring(2)).equals("Confirmed WR") && !data.getuser())
                        	btn.setStyle("-fx-background-color: yellow");
                        
                        btn.setOnAction(new EventHandler<ActionEvent>() {
							
							@Override
							public void handle(ActionEvent event) {
								//Если задачу подтверждает ответственный за задачу
								if(qr._select_oft(data.getap_num()).equals(USER_S) && qr._select_status(data.IdProperty().get().substring(2)).equals("Confirmed WR") && !data.getqty())
								{
									qr._update_oft_wr("1",data.IdProperty().get().substring(2));
									qr._update_qty_wr("1",data.IdProperty().get().substring(2));
									btn.setStyle("-fx-background-color: green");
									switch (flag) {
										case 0:
										case 2:
											table_wr.setItems(qr._select_data_wr(fx_dp.toString(begin_data.getValue()), fx_dp.toString(last_data.getValue())));
											table_wr.getColumns().get(0).setVisible(false);
											table_wr.getColumns().get(0).setVisible(true);
											break;
										case 1:
											table_wr.setItems(qr._select_sort_apnum_wr(data.getap_num()));
											break;
										case 3:
											table_wr.setItems(qr._select_sort_shop_wr(fx_dp.toString(begin_data.getValue()), fx_dp.toString(last_data.getValue()), SORT_SHOP));
											table_wr.getColumns().get(0).setVisible(false);
											table_wr.getColumns().get(0).setVisible(true);
											break;
										case 4:
											table_wr.setItems(qr._select_sort_resp_wr(fx_dp.toString(begin_data.getValue()), fx_dp.toString(last_data.getValue()), scl.parser_str(SORT_RESP, 0)));
											table_wr.getColumns().get(0).setVisible(false);
											table_wr.getColumns().get(0).setVisible(true);
											break;
										case 5:
											table_wr.setItems(qr._select_sort_OFT_wr(fx_dp.toString(begin_data.getValue()), fx_dp.toString(last_data.getValue()), getUserLettersID(table_wr_filter_state)));
											table_wr.getColumns().get(0).setVisible(false);
											table_wr.getColumns().get(0).setVisible(true);
											break;
									}
									/*if(flag == 1)
										table_wr.setItems(qr._select_sort_apnum_wr(data.getap_num()));
									if(flag == 0 || flag == 2) 
										table_wr.setItems(qr._select_data_wr(fx_dp.toString(begin_data.getValue()), fx_dp.toString(last_data.getValue())));
									table_wr.getColumns().get(0).setVisible(false);
							        table_wr.getColumns().get(0).setVisible(true);
							        if(flag == 3)
							        {
							        	table_wr.setItems(qr._select_sort_shop_wr(fx_dp.toString(begin_data.getValue()), fx_dp.toString(last_data.getValue()), SORT_SHOP));
								    	table_wr.getColumns().get(0).setVisible(false);
								        table_wr.getColumns().get(0).setVisible(true);
							        }
							        if(flag == 4)
							        {
							        	table_wr.setItems(qr._select_sort_resp_wr(fx_dp.toString(begin_data.getValue()), fx_dp.toString(last_data.getValue()), scl.parser_str(SORT_RESP, 0)));
								    	table_wr.getColumns().get(0).setVisible(false);
								        table_wr.getColumns().get(0).setVisible(true);
							        }
							        if (flag == 5) {
										table_wr.setItems(qr._select_sort_OFT_wr(fx_dp.toString(begin_data.getValue()), fx_dp.toString(last_data.getValue()), getUserLettersID(table_wr_filter_state)));
										table_wr.getColumns().get(0).setVisible(false);
										table_wr.getColumns().get(0).setVisible(true);
										break;
									}*/
								}
								
								//Если задачу подтвердил ее хозяин или если хозяин задачи совпадает с ответственным за задачу
								if(qr._select_userid(data.getap_num()).equals(conn_connector.USER_ID) && qr._select_oft(data.getap_num()).equals(USER_S) && qr._select_status(data.IdProperty().get().substring(2)).equals("Done")) {
									qr._update_qty_wr("1",data.IdProperty().get().substring(2));
									qr._update_oft_wr("1",data.IdProperty().get().substring(2));
									btn.setStyle("-fx-background-color: green");
									if(flag == 1)
										table_wr.setItems(qr._select_sort_apnum_wr(data.getap_num()));
									if(flag == 0 || flag == 2)
										table_wr.setItems(qr._select_data_wr(fx_dp.toString(begin_data.getValue()), fx_dp.toString(last_data.getValue())));
									table_wr.getColumns().get(0).setVisible(false);
								    table_wr.getColumns().get(0).setVisible(true);
									
									if(flag == 3)
							        {
							        	table_wr.setItems(qr._select_sort_shop_wr(fx_dp.toString(begin_data.getValue()), fx_dp.toString(last_data.getValue()), SORT_SHOP));
								    	table_wr.getColumns().get(0).setVisible(false);
								        table_wr.getColumns().get(0).setVisible(true);
							        }
							        if(flag == 4)
							        {
							        	table_wr.setItems(qr._select_sort_resp_wr(fx_dp.toString(begin_data.getValue()), fx_dp.toString(last_data.getValue()), scl.parser_str(SORT_RESP, 0)));
								    	table_wr.getColumns().get(0).setVisible(false);
								        table_wr.getColumns().get(0).setVisible(true);
							        }
								}
								
								//Если задачу подтвердил ее хозяин и если хозяин задачи не совпадает с ответственным за задачу
								if(qr._select_userid(data.getap_num()).equals(conn_connector.USER_ID)) { // && data.getuser()
									qr._update_qty_wr("1",data.IdProperty().get().substring(2));
									btn.setStyle("-fx-background-color: green");
									if(flag == 1)
										table_wr.setItems(qr._select_sort_apnum_wr(data.getap_num()));
									if(flag == 0 || flag == 2) 
										table_wr.setItems(qr._select_data_wr(fx_dp.toString(begin_data.getValue()), fx_dp.toString(last_data.getValue())));
									table_wr.getColumns().get(0).setVisible(false);
								    table_wr.getColumns().get(0).setVisible(true);
									
							        if(flag == 3)
							        {
							        	table_wr.setItems(qr._select_sort_shop_wr(fx_dp.toString(begin_data.getValue()), fx_dp.toString(last_data.getValue()), SORT_SHOP));
								    	table_wr.getColumns().get(0).setVisible(false);
								        table_wr.getColumns().get(0).setVisible(true);
							        }
							        if(flag == 4)
							        {
							        	table_wr.setItems(qr._select_sort_resp_wr(fx_dp.toString(begin_data.getValue()), fx_dp.toString(last_data.getValue()), scl.parser_str(SORT_RESP, 0)));
								    	table_wr.getColumns().get(0).setVisible(false);
								        table_wr.getColumns().get(0).setVisible(true);
							        }
								}
								
								//Зеленим исполнителя и желтим ответственного таблицы AP, если все подтверждено в WR
								if(qr._select_confirm(qr._select_apnum(data.IdProperty().get().substring(2))).equals("YES"))
								{
									qr._update_delrec_ap(qr._select_apnum(data.IdProperty().get().substring(2)));
									table_ap.setItems(qr._select_data_ap(USER_S));
									table_ap.getColumns().get(0).setVisible(false);
							        table_ap.getColumns().get(0).setVisible(true);
								}
							}
						});
                        qr._update_calc_field(data.getId().substring(2));
                        return new SimpleObjectProperty<Button>(btn);
                    }

                });
        
        columns.add(favoriteColumn);
                
        //Добавляем кнопку в table_wr
        final ObservableList<TableColumn<hmmr_wr_model, ?>> columns1 = table_wr.getColumns();
		final TableColumn<hmmr_wr_model, Button> favoriteColumn1 = new TableColumn<hmmr_wr_model, Button>("№ AP");
        favoriteColumn1.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<hmmr_wr_model, Button>, ObservableValue<Button>>() {

                    @Override
                    public ObservableValue<Button> call(TableColumn.CellDataFeatures<hmmr_wr_model, Button> arg0) {
                        hmmr_wr_model data = arg0.getValue();
                        Button btn = new Button();
                      //запрещаем бегунку прокрутки возвращаться назад после нажатия кнопки
                        btn.setFocusTraversable(false);
                        //устанавливаем номер ар в текст кнопки
                        btn.setText("AP"+data.getap_num());
                        favoriteColumn1.setStyle( "-fx-alignment: CENTER;");
                        

                       btn.setOnAction(new EventHandler<ActionEvent>() {
							
							@Override
							public void handle(ActionEvent event) {
								//mu_main_controller.getPrimaryStage().setAlwaysOnTop(false);
								table_wr.setItems(qr._select_sort_apnum_wr(btn.getText().substring(2)));
								ID_WR = btn.getText().substring(2);
								_get_text_btn = btn.getText();
								table_wr.getColumns().get(0).setVisible(false);
						        table_wr.getColumns().get(0).setVisible(true);
						        clear_filter.setDisable(false);
						        flag = 1;
							}
						});

                        return new SimpleObjectProperty<Button>(btn);
                    }

                });
        columns1.add(favoriteColumn1);
				
		n_ap.setOnEditStart(new EventHandler<TableColumn.CellEditEvent<hmmr_ap_model,String>>() {
			
			@Override
			public void handle(CellEditEvent<hmmr_ap_model, String> event) {
				try {
						String tst = event.getTableView().getItems().get(event.getTablePosition().getRow()).getId().substring(2);
						ID_WR = tst;
						table_wr.setItems(qr._select_sort_apnum_wr(tst));
						
						table_wr.getColumns().get(0).setVisible(false);
				        table_wr.getColumns().get(0).setVisible(true);
				        
				        clear_filter.setDisable(false);
				        flag = 1;
				}
				catch (Exception e) {
					
				}
			}
		});
	
		//Вызываем окно обновления по двойному клику на строке table AP
		table_ap.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() == 2 ){
					if(!conn_connector.USER_ROLE.equals("Technics"))
						func_upd(table_ap.getSelectionModel().getSelectedItem().getId());
					if(conn_connector.USER_ROLE.equals("Technics")) {
						try {
							addwr_start();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
	            }
			}
		});
		//Выделяем строку в WO нажимаем Enter и открывается WR отсортированный по этой задаче 
		table_ap.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER){
					ID_WR = table_ap.getSelectionModel().getSelectedItem().getId().substring(2);
					table_wr.setItems(qr._select_sort_apnum_wr(ID_WR));
					
					table_wr.getColumns().get(0).setVisible(false);
			        table_wr.getColumns().get(0).setVisible(true);
			        
			        clear_filter.setDisable(false);
			        flag = 1;
			        tb_pn.getSelectionModel().select(tab_wr);
					}
			}
		});
		
		//Вызываем окно обновления по двойному клику на строке table WR
		table_wr.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() == 2 ){
	                func_upd_wr(table_wr.getSelectionModel().getSelectedItem().getId());
	            }
			}
		});
		//Вызываем окно обновления по двойному клику на строке table WP
				table_wp.setOnMousePressed(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						if (event.getClickCount() == 2 ){
							try {
								_fill_rec_wp(table_wp.getSelectionModel().getSelectedItem().getId());
							}
							catch (Exception e) {
								
							}
			            }
					}
				});
			
		final ObservableList<TableColumn<hmmr_ap_model, ?>> columns_otv = table_ap.getColumns();
		otv.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<hmmr_ap_model, Button>, ObservableValue<Button>>() {

                    @Override
                    public ObservableValue<Button> call(TableColumn.CellDataFeatures<hmmr_ap_model, Button> arg0) {
                        hmmr_ap_model data = arg0.getValue();
                        Button btn = new Button();
                      //запрещаем бегунку прокрутки возвращаться назад после нажатия кнопки
                        btn.setFocusTraversable(false);
                        //устанавливаем номер ар в текст кнопки
                        btn.setText("");
                        btn.setPrefWidth(39);
                        btn.setPrefHeight(35);
                        btn.setText(data.getOTV());
                        otv.setStyle( "-fx-alignment: CENTER;");
                        
                        //Подтверждать задачу может только тот кто ее создал или ответсвенный за задачу
                        if(qr._select_userid(data.getId().substring(2)).equals(conn_connector.USER_ID) || qr._select_oft(data.getId().substring(2)).equals(USER_S))
                        	btn.setDisable(false);
                        else
                        	btn.setDisable(true);
                        
                        //Если Все записи в WR по этой задаче подтверждены то ставим кнопку в AP на исполнителе желтой
                        if(data.getflag_otv().equals("2"))
                        	btn.setStyle("-fx-background-color: green");
                        else if(data.getflag_otv().equals("1"))
                        	btn.setStyle("-fx-background-color: yellow");
                        
                        btn.setOnAction(new EventHandler<ActionEvent>() {
							
							@Override
							public void handle(ActionEvent event) {
								
								if(!data.getflag_otv().equals("0")) {									
								//Если задачу подтверждает ответственный за задачу в поле исполнитель AP
							/*	if(qr._select_oft(data.getId().substring(2)).equals(USER_S))
								{
									qr._update_otv_ap(data.getId().substring(2), "flag_otv", "2");
									btn.setStyle("-fx-background-color: green");
									//Если владелец или ответственный задачи подвердили что исполнитель ее сделал то переходим к полю oft и делаем его желтым
							        qr._update_otv_ap(data.getId().substring(2), "flag_oft", "1");
									table_ap.setItems(qr._select_data_ap(USER_S));
									table_ap.getColumns().get(0).setVisible(false);
							        table_ap.getColumns().get(0).setVisible(true);
							    }
								
								//Если задачу подтвердил ее хозяин или если хозяин задачи совпадает с ответственным за задачу
								/*if(qr._select_userid(data.getId().substring(2)).equals(conn_connector.USER_ID) && qr._select_oft(data.getId().substring(2)).equals(USER_S)) {
									qr._update_otv_ap(data.getId().substring(2), "flag_otv", "2");
									btn.setStyle("-fx-background-color: green");
									//Если владелец или ответственный задачи подвердили что исполнитель ее сделал то переходим к полю oft и делаем его желтым
							        qr._update_otv_ap(data.getId().substring(2), "flag_oft", "1");
									table_ap.setItems(qr._select_data_ap(USER_S));
									table_ap.getColumns().get(0).setVisible(false);
							        table_ap.getColumns().get(0).setVisible(true);
							    }
								
								//Владелец задачи может ее подтвердить в любом случае
								if(qr._select_userid(data.getId().substring(2)).equals(conn_connector.USER_ID)) {
									qr._update_otv_ap(data.getId().substring(2), "flag_otv", "2");
									btn.setStyle("-fx-background-color: green");
									//Если владелец или ответственный задачи подвердили что исполнитель ее сделал то переходим к полю oft и делаем его желтым
							        qr._update_otv_ap(data.getId().substring(2), "flag_oft", "1");
									table_ap.setItems(qr._select_data_ap(USER_S));
									table_ap.getColumns().get(0).setVisible(false);
							        table_ap.getColumns().get(0).setVisible(true);
							    }*/
								}
							}
						});
                        qr._update_calc_field(data.getId().substring(2));
                        return new SimpleObjectProperty<Button>(btn);
                    }

                });
        columns_otv.add(otv);
        
        final ObservableList<TableColumn<hmmr_ap_model, ?>> columns_oft = table_ap.getColumns();
		oft.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<hmmr_ap_model, Button>, ObservableValue<Button>>() {

                    @Override
                    public ObservableValue<Button> call(TableColumn.CellDataFeatures<hmmr_ap_model, Button> arg0) {
                        hmmr_ap_model data = arg0.getValue();
                        Button btn = new Button();
                      //запрещаем бегунку прокрутки возвращаться назад после нажатия кнопки
                        btn.setFocusTraversable(false);
                        //устанавливаем номер ар в текст кнопки
                        btn.setText("");
                        btn.setPrefWidth(39);
                        btn.setPrefHeight(35);
                        btn.setText(data.getOFT());
                        oft.setStyle( "-fx-alignment: CENTER;");
                        
                        //Подтверждать задачу может только тот кто ее создал или ответсвенный за задачу
                        if(qr._select_userid(data.getId().substring(2)).equals(conn_connector.USER_ID) || qr._select_oft(data.getId().substring(2)).equals(USER_S))
                        	btn.setDisable(false);
                        else
                        	btn.setDisable(true);
                        
                        //Если ответственный или владелец по этой задаче ее подтверждает то ставим кнопку в AP на ответственном за задачу желтой
                        if(data.getflag_oft().equals("2"))
                        	btn.setStyle("-fx-background-color: green");
                        else if(data.getflag_oft().equals("1"))
                        	btn.setStyle("-fx-background-color: yellow");
                        
                        btn.setOnAction(new EventHandler<ActionEvent>() {
							
							@Override
							public void handle(ActionEvent event) {
								if(!data.getflag_otv().equals("0") && !data.getflag_otv().equals("1")) {									
								//Если задачу подтверждает ответственный за задачу в поле ответственный AP
								if(qr._select_oft(data.getId().substring(2)).equals(USER_S))
								{
									qr._update_otv_ap(data.getId().substring(2), "flag_oft", "2");
									qr._update_otv_ap(data.getId().substring(2), "flag_otv", "2");
									btn.setStyle("-fx-background-color: green");
									//Если владелец или ответственный задачи подвердили что задача проверена то переходим к полю tm и делаем его желтым
							        qr._update_otv_ap(data.getId().substring(2), "flag_tm", "1");
									table_ap.setItems(qr._select_data_ap(USER_S));
									table_ap.getColumns().get(0).setVisible(false);
							        table_ap.getColumns().get(0).setVisible(true);
							    }
								
								//Если задачу подтвердил ее хозяин или если хозяин задачи совпадает с ответственным за задачу
								if(qr._select_userid(data.getId().substring(2)).equals(conn_connector.USER_ID) && qr._select_oft(data.getId().substring(2)).equals(USER_S)) {
									qr._update_otv_ap(data.getId().substring(2), "flag_oft", "2");
									qr._update_otv_ap(data.getId().substring(2), "flag_otv", "2");
									btn.setStyle("-fx-background-color: green");
									//Если владелец или ответственный задачи подвердили что задача проверена то переходим к полю tm и делаем его желтым
							        qr._update_otv_ap(data.getId().substring(2), "flag_tm", "1");
									table_ap.setItems(qr._select_data_ap(USER_S));
									table_ap.getColumns().get(0).setVisible(false);
							        table_ap.getColumns().get(0).setVisible(true);
							    }
								
								//Владелец задачи может ее подтвердить в любом случае
								if(qr._select_userid(data.getId().substring(2)).equals(conn_connector.USER_ID)) {
									qr._update_otv_ap(data.getId().substring(2), "flag_oft", "2");
									qr._update_otv_ap(data.getId().substring(2), "flag_otv", "2");
									btn.setStyle("-fx-background-color: green");
									//Если владелец или ответственный задачи подвердили что задача проверена то переходим к полю tm и делаем его желтым
							        qr._update_otv_ap(data.getId().substring(2), "flag_tm", "1");
									table_ap.setItems(qr._select_data_ap(USER_S));
									table_ap.getColumns().get(0).setVisible(false);
							        table_ap.getColumns().get(0).setVisible(true);
								}
								}
							}
						});
                        qr._update_calc_field(data.getId().substring(2));
                        return new SimpleObjectProperty<Button>(btn);
                    }

                });
        columns_oft.add(oft);
        
        final ObservableList<TableColumn<hmmr_ap_model, ?>> columns_tm = table_ap.getColumns();
		tm.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<hmmr_ap_model, Button>, ObservableValue<Button>>() {

                    @Override
                    public ObservableValue<Button> call(TableColumn.CellDataFeatures<hmmr_ap_model, Button> arg0) {
                        hmmr_ap_model data = arg0.getValue();
                        Button btn = new Button();
                      //запрещаем бегунку прокрутки возвращаться назад после нажатия кнопки
                        btn.setFocusTraversable(false);
                        //устанавливаем номер ар в текст кнопки
                        btn.setText("");
                        btn.setPrefWidth(39);
                        btn.setPrefHeight(35);
                        btn.setText(data.gettsk_maker());
                        tm.setStyle( "-fx-alignment: CENTER;");
                        
                        //Подтверждать задачу может только тот кто ее создал
                        if(qr._select_userid(data.getId().substring(2)).equals(conn_connector.USER_ID)) //|| qr._select_oft(data.getId().substring(2)).equals(USER_S))
                        	btn.setDisable(false);
                        else
                        	btn.setDisable(true);
                        
                        //Если ответственный или владелец по этой задаче ее подтверждает то ставим кнопку в AP на владельце за задачу зеленой
                        if(data.getflag_tm().equals("2"))
                        	btn.setStyle("-fx-background-color: green");
                        else if(data.getflag_tm().equals("1"))
                        	btn.setStyle("-fx-background-color: yellow");
                        
                        btn.setOnAction(new EventHandler<ActionEvent>() {
							
							@Override
							public void handle(ActionEvent event) {
								
							if(!data.getflag_otv().equals("0") && !data.getflag_oft().equals("0")) {									
								//Если задачу подтвердил ее хозяин или если хозяин задачи совпадает с ответственным за задачу
								if(qr._select_userid(data.getId().substring(2)).equals(conn_connector.USER_ID) && qr._select_oft(data.getId().substring(2)).equals(USER_S)) {
									qr._update_otv_ap(data.getId().substring(2), "flag_tm", "2");
									btn.setStyle("-fx-background-color: green");
									
									table_ap.setItems(qr._select_data_ap(USER_S));
									table_ap.getColumns().get(0).setVisible(false);
							        table_ap.getColumns().get(0).setVisible(true);
							    }
								
								//Владелец задачи может ее подтвердить в любом случае
								if(qr._select_userid(data.getId().substring(2)).equals(conn_connector.USER_ID)) {
									qr._update_otv_ap(data.getId().substring(2), "flag_tm", "2");
									btn.setStyle("-fx-background-color: green");
									
									table_ap.setItems(qr._select_data_ap(USER_S));
									table_ap.getColumns().get(0).setVisible(false);
							        table_ap.getColumns().get(0).setVisible(true);
								}
								
								//Владелец задачи может ее подтвердить в любом случае, даже если она только подтверждена из WR
								if(qr._select_userid(data.getId().substring(2)).equals(conn_connector.USER_ID) && data.getflag_otv().equals("2") || qr._select_userid(data.getId().substring(2)).equals(conn_connector.USER_ID) && data.getflag_oft().equals("1") || qr._select_userid(data.getId().substring(2)).equals(conn_connector.USER_ID) && data.getflag_oft().equals("2")) {
									qr._update_otv_ap(data.getId().substring(2), "flag_tm", "2");
									qr._update_otv_ap(data.getId().substring(2), "flag_oft", "2");
									qr._update_otv_ap(data.getId().substring(2), "flag_otv", "2");
									btn.setStyle("-fx-background-color: green");
									qr._update_calc_field(data.getId().substring(2));
									qr._update_deleterec_ap(data.getId().substring(2));
									table_ap.setItems(qr._select_data_ap(USER_S));
									table_ap.getColumns().get(0).setVisible(false);
							        table_ap.getColumns().get(0).setVisible(true);
								}
								//Убираем задачу из AP если она всеми подтвержденна
								/*if(data.getflag_otv().equals("2") && data.getflag_oft().equals("2") && data.getflag_tm().equals("2"))
								{
									qr._update_deleterec_ap(data.getId().substring(2));
									
									table_ap.setItems(qr._select_data_ap(USER_S));
									table_ap.getColumns().get(0).setVisible(false);
							        table_ap.getColumns().get(0).setVisible(true);
								}*/
							}
							}
						});
                        
                        return new SimpleObjectProperty<Button>(btn);
                    }

                });
        columns_tm.add(tm);
        
        final ObservableList<TableColumn<hmmr_ap_model, ?>> columns_prior = table_ap.getColumns();
		prior.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<hmmr_ap_model, JFXButton>, ObservableValue<JFXButton>>() {
                	Tooltip tooltip = new Tooltip();
                    @SuppressWarnings("static-access")
					@Override
                    public ObservableValue<JFXButton> call(TableColumn.CellDataFeatures<hmmr_ap_model, JFXButton> arg0) {
                        hmmr_ap_model data = arg0.getValue();
                        JFXButton iv = new JFXButton();
                      //запрещаем бегунку прокрутки возвращаться назад после нажатия кнопки
                        iv.setFocusTraversable(false);
                        
                        prior.setStyle( "-fx-alignment: CENTER;");
                 //       String test = data.geticon();
                        BufferedImage bufferedImage;
						try {
							if (!data.geticon().equals("1")) {
								String tmpIcon = qr._select_prior_img(data.geticon());
								if (!priorImages.containsKey(tmpIcon)) {
									bufferedImage = ImageIO.read(new File(tmpIcon));
									priorImages.put(tmpIcon, SwingFXUtils.toFXImage(bufferedImage, null));
								}
								Image image = priorImages.get(tmpIcon);
								//iv.setImage(image);
								iv.setGraphic(new ImageView(image));
							}
						} catch (IOException e) {
							scl._AlertDialog(e.getMessage()+" prior_controller", "Ошибка загрузки изображения");
						}
                        iv.setOnMouseEntered(new EventHandler<Event>() {

							@Override
							public void handle(Event event) {
								tooltip.setText(qr._select_prior_desc(data.getId().substring(2)));
								tooltip.setStyle("-fx-font-size: 14px");
								Tooltip.install(iv, tooltip);
							}
						});        		    
                       
                        return new SimpleObjectProperty<JFXButton>(iv);
                    }
                    
                });
        columns_prior.add(prior);
        
        final ObservableList<TableColumn<hmmr_ap_model, ?>> columns_at = table_ap.getColumns();
		at.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<hmmr_ap_model, JFXButton>, ObservableValue<JFXButton>>() {
                	Tooltip tooltip = new Tooltip();
                    @SuppressWarnings("static-access")
					@Override
                    public ObservableValue<JFXButton> call(TableColumn.CellDataFeatures<hmmr_ap_model, JFXButton> arg0) {
                        hmmr_ap_model data = arg0.getValue();
                        JFXButton iv = new JFXButton();
                      //запрещаем бегунку прокрутки возвращаться назад после нажатия кнопки
                        iv.setFocusTraversable(false);
                        
                        at.setStyle( "-fx-alignment: CENTER;");
                       // String test = data.geticon_at();
                        BufferedImage bufferedImage;
						try {
							if(!data.geticon_at().equals("1")) {
								String tmpIcon = qr._select_recStr("hmmr_activity_type", "Icon", "del_rec", "Name", data.geticon_at());
								if (!priorImages.containsKey(tmpIcon)) {
									bufferedImage = ImageIO.read(new File(tmpIcon));
									priorImages.put(tmpIcon, SwingFXUtils.toFXImage(bufferedImage, null));
								}
								Image image = priorImages.get(tmpIcon);
								//iv.setImage(image);
								iv.setGraphic(new ImageView(image));
							}
						} catch (IOException e) {
							scl._AlertDialog(e.getMessage()+" prior_controller", "Ошибка загрузки изображения");
						}
                        iv.setOnMouseEntered(new EventHandler<Event>() {

							@Override
							public void handle(Event event) {
								tooltip.setText(qr._select_at_desc(data.getId().substring(2)));
								tooltip.setStyle("-fx-font-size: 14px");
								Tooltip.install(iv, tooltip);
							}
						});        		    
                       
                        return new SimpleObjectProperty<JFXButton>(iv);
                    }
                    
                });
        columns_at.add(at);
        
        final ObservableList<TableColumn<hmmr_wr_model, ?>> columns_at_wr = table_wr.getColumns();
		at_wr.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<hmmr_wr_model, JFXButton>, ObservableValue<JFXButton>>() {
                	Tooltip tooltip = new Tooltip();
                    @SuppressWarnings("static-access")
					@Override
                    public ObservableValue<JFXButton> call(TableColumn.CellDataFeatures<hmmr_wr_model, JFXButton> arg0) {
                        hmmr_wr_model data = arg0.getValue();
                        JFXButton iv = new JFXButton();
                      //запрещаем бегунку прокрутки возвращаться назад после нажатия кнопки
                        iv.setFocusTraversable(false);
                        
                        at_wr.setStyle( "-fx-alignment: CENTER;");
                        
                        BufferedImage bufferedImage;
						try {
							if(!data.geticon_at().equals("1")) {
								String tmpIcon = qr._select_recStr("hmmr_activity_type", "Icon", "del_rec", "Name", data.geticon_at());
								if (!priorImages.containsKey(tmpIcon)) {
									bufferedImage = ImageIO.read(new File(tmpIcon));
									priorImages.put(tmpIcon, SwingFXUtils.toFXImage(bufferedImage, null));
								}
								Image image = priorImages.get(tmpIcon);
								//iv.setImage(image);
								iv.setGraphic(new ImageView(image));
							}
						} catch (IOException e) {
							scl._AlertDialog(e.getMessage()+" prior_controller", "Ошибка загрузки изображения");
						}
                        iv.setOnMouseEntered(new EventHandler<Event>() {

							@Override
							public void handle(Event event) {
								tooltip.setText(qr._select_at_desc_wr(data.getId().substring(2)));
								tooltip.setStyle("-fx-font-size: 14px");
								Tooltip.install(iv, tooltip);
							}
						});        		    
                       
                        return new SimpleObjectProperty<JFXButton>(iv);
                    }
                    
                });
        columns_at_wr.add(at_wr);
        
		//Устанавливаем подсказку для строки Описание в таблице AP
       /* table_ap.setRowFactory((hmmr_ap_model) -> { 
            return new TooltipTableRow<hmmr_ap_model>((hmmr_ap_model ham) -> { 
            return ham.getDesc(); 
            }); 
       });*/ 
      
        //Устанавливаем подсказки по определенному столбцу таблиц AP и WR
        desc_ap.setCellFactory(TooltippedTableCell.forTableColumn());
        req_action_wr.setCellFactory(TooltippedTableCell.forTableColumn());
        shift_report_wr.setCellFactory(TooltippedTableCell.forTableColumn());            		
		addButtonToTable();
		addButtonToTable_wp();
		
		//Получаем текущую дату
		LocalDate date_cur = LocalDate.now();
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Проверяем есть ли что-то, что можно добавить в hmmr_work_plan!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		_chk.addAll(qr._select_pmplan());
		for(int i = 0; i < _chk.size(); i++)
		{
			_date = scl.parser_str(_chk.get(i), 0); //Дата окончания
			_count = scl.parser_str(_chk.get(i), 1);//За сколько дней предупреждать техника до даты окончания
			_duration = Integer.parseInt(scl.parser_str(_chk.get(i), 2));//сколько надо прибавить к дате из PM Plana чтобы получить дату окончания(due date)
			if(!_count.equals("0") && _duration != 0) {
				int day_edate = fx_dp.fromString(_date).getDayOfMonth();
				int month_edate = fx_dp.fromString(_date).getMonthValue();
				int year_edate = fx_dp.fromString(_date).getYear();
				
				int _count1 = Integer.parseInt(_count);
				for(int k = -1 ; k >= _count1 ; k--) {
					LocalDate days = LocalDate.of(year_edate, month_edate, day_edate).plusDays(k);//Расчитываем дату начиная с которой помещаем заявку в Action Plan
					
					//Переводим даты в Стринг
					String _chk_cur_date = fx_dp.toString(date_cur);
					String _chk_new_date = fx_dp.toString(days);
					
					//Проверяем на совпадение расчетной даты с текущей и если совпадает создаем запись в Action Plan
					if(_chk_cur_date.equals(_chk_new_date))
					{
						//Получаем поля необходимые для инсерта в Work Plan
						_get_field.addAll(qr._select_getfield_for_ap(_date));
						for(int j = 0; j < _get_field.size(); j++) {  //new
							_due_date = scl.parser_str(_get_field.get(j), 0);
							_otf = scl.parser_str(_get_field.get(j), 1);
							_instruct = scl.parser_str(_get_field.get(j), 2);
							_id_pm = scl.parser_str(_get_field.get(j), 3);
							_group_pm = scl.parser_str(_get_field.get(j), 4);
							_pmname = scl.parser_str(_get_field.get(j), 5);
							_shop = scl.parser_str(_get_field.get(j), 6);
							_group_eq = scl.parser_str(_get_field.get(j), 7);
							_lm = scl.parser_str(_get_field.get(j), 8);
							_os = scl.parser_str(_get_field.get(j), 9);
							_equip = scl.parser_str(_get_field.get(j), 10);
							_id = scl.parser_str(_get_field.get(j), 11);
							_pm_exec = scl.parser_str(_get_field.get(j), 12);
							_activity_type = scl.parser_str(_get_field.get(j), 13);
							_type = "PM";
							//if(_record.equals("0")) _insert_ap
								qr._insert_wp(_id_pm, _type, _pmname, _due_date, _shop+"."+_group_eq+"."+_lm+"."+_os+"."+_equip, _instruct, _otf, qr._select_userid_(_otf), _shop, "4M",_pm_exec,_activity_type);
							//Чтобы задача не добавлясь в WP каждый раз с запуском приложения, поэтому ставим признак - 1, после первого заполнения
							qr._update_hpy_record(_id, "1");
						}
						_chk.removeAll(_chk);
						_get_field.removeAll(_get_field);
						_chk.addAll(qr._select_pmplan());
						//table_ap.setItems(qr._select_data_ap(USER_S));
						//table_ap.getColumns().get(0).setVisible(false);
				        //table_ap.getColumns().get(0).setVisible(true);
						table_wp.setItems(qr._select_data_wp(USER_S));
						table_wp.getColumns().get(0).setVisible(false);
				        table_wp.getColumns().get(0).setVisible(true);
					}
				}
			}
		}
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	//!!!!!!!!!Теперь проверяем все даты в Work Plane на совпадение с текущей и если совпадает эту запись из Work Plana удаляем и добавляем ее в Work Order!!!!!!!!!!!!!!!!!!!!!!!
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		_get_data_from_wp.addAll(qr._select_date_wp(fx_dp.toString(date_cur)));
		for(int l = 0; l < _get_data_from_wp.size(); l++) {  
			
			String _id = scl.parser_str(_get_data_from_wp.get(l), 0);
			String _pm_num = scl.parser_str(_get_data_from_wp.get(l), 1);
			String _type = scl.parser_str(_get_data_from_wp.get(l), 2);
			String _desc = scl.parser_str(_get_data_from_wp.get(l), 3);
			String _dd = scl.parser_str(_get_data_from_wp.get(l), 4);
		//	LocalDate test_dd = fx_dp.fromString(_dd).plusDays(_duration);
			String _equip = scl.parser_str(_get_data_from_wp.get(l), 5);
			String _inst = scl.parser_str(_get_data_from_wp.get(l), 6);
			String _oft = scl.parser_str(_get_data_from_wp.get(l), 7);
			String _otv = scl.parser_str(_get_data_from_wp.get(l), 8);
			//String _tm = scl.parser_str(_get_data_from_wp.get(l), 9);
			String _icon = scl.parser_str(_get_data_from_wp.get(l), 13);
			String _shop = scl.parser_str(_get_data_from_wp.get(l), 14);
			String _act_type = scl.parser_str(_get_data_from_wp.get(l), 15);
			
			qr._insert_ap(_pm_num, _type, _desc, fx_dp.fromString(_dd).plusDays(_duration), _equip, _inst, _oft, qr._select_userid_(_oft), _shop, _icon, _otv, _act_type);
			//Удаляем PM из WP
			qr._update_wp_record(_id);
			
			table_ap.setItems(qr._select_data_ap(USER_S));
			table_ap.getColumns().get(0).setVisible(false);
	        table_ap.getColumns().get(0).setVisible(true);
	        table_wp.setItems(qr._select_data_wp(USER_S));
			table_wp.getColumns().get(0).setVisible(false);
	        table_wp.getColumns().get(0).setVisible(true);
		}
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Ежедневный ППР добавляем напрямую в WO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		
		_get_data_dly.addAll(qr._select_dly_wo(fx_dp.toString(date_cur)));
		for(int j = 0; j < _get_data_dly.size(); j++) {  //new
			_due_date_wo = scl.parser_str(_get_data_dly.get(j), 0);
			_otf_wo = scl.parser_str(_get_data_dly.get(j), 1);
			_instruct_wo = scl.parser_str(_get_data_dly.get(j), 2);
			_id_pm_wo = scl.parser_str(_get_data_dly.get(j), 3);
			_group_pm_wo = scl.parser_str(_get_data_dly.get(j), 4);
			_pmname_wo = scl.parser_str(_get_data_dly.get(j), 5);
			_shop_wo = scl.parser_str(_get_data_dly.get(j), 6);
			_group_eq_wo = scl.parser_str(_get_data_dly.get(j), 7);
			_lm_wo = scl.parser_str(_get_data_dly.get(j), 8);
			_os_wo = scl.parser_str(_get_data_dly.get(j), 9);
			_equip_wo = scl.parser_str(_get_data_dly.get(j), 10);
			_id_wo = scl.parser_str(_get_data_dly.get(j), 11);
			_pm_exec_wo = scl.parser_str(_get_data_dly.get(j), 12);
			_icon_at_wo = scl.parser_str(_get_data_dly.get(j), 13);
			_type_wo = "PM";
			//На выходных ППР не добавляем в WO			 
			if(!date_cur.getDayOfWeek().toString().equals("SATURDAY") && !date_cur.getDayOfWeek().toString().equals("SUNDAY")) //_insert_ap  
				qr._insert_ap(_id_pm_wo, _type_wo, _pmname_wo, fx_dp.fromString(_due_date_wo), _shop_wo+"."+_group_eq_wo+"."+_lm_wo+"."+_os_wo+"."+_equip_wo, _instruct_wo, _otf_wo, qr._select_userid_(_otf_wo), _shop_wo, "4M",_pm_exec_wo,_icon_at_wo);
			//Чтобы задача не добавлясь в WP каждый раз с запуском приложения, поэтому ставим признак - 1, после первого заполнения
			qr._update_hpy_record(_id_wo, "1");
		}
		_get_data_dly.removeAll(_get_data_dly);
		table_ap.setItems(qr._select_data_ap(USER_S));
		table_ap.getColumns().get(0).setVisible(false);
        table_ap.getColumns().get(0).setVisible(true);
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!	
		scl._style(create_ap);
		
		create_ap.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				try {
					upd_ap.setDisable(true);
					pminst_add();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		scl._style(upd_ap);
		
		upd_ap.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				hmmr_ap_model _ccl1 = table_ap.getSelectionModel().getSelectedItem();
				try {
					//mu_main_controller.getPrimaryStage().setAlwaysOnTop(false);
					upd_ap.setDisable(true);
					func_upd(_ccl1.getId());
				} catch (Exception e) {
					
				}
			}
		});
		
		upd_rec_wp.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				hmmr_wp_model _ccl1 = table_wp.getSelectionModel().getSelectedItem();
				try {
					//mu_main_controller.getPrimaryStage().setAlwaysOnTop(false);
					upd_rec_wp.setDisable(true);
					_fill_rec_wp(_ccl1.getId());
				} catch (Exception e) {
					
				}
			}
		});
		
		scl._style(upd_wr);
		
		upd_wr.setFocusTraversable(false);
		upd_wr.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				hmmr_wr_model _ccl1 = table_wr.getSelectionModel().getSelectedItem();
				try {
					//mu_main_controller.getPrimaryStage().setAlwaysOnTop(false);
					upd_wr.setDisable(true);
					func_upd_wr(_ccl1.getId());
				} catch (Exception e) {
					
				}
			}
		});
		
		scl._style(add_wr);
		
		add_wr.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				try {
					upd_ap.setDisable(true);
					upd_wr.setDisable(true);
					//даты для сортировки таблицы
					before_date = fx_dp.toString(begin_data.getValue());
					after_date = fx_dp.toString(last_data.getValue().plusDays(1));
					
					addwr_start();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		tableCellAlignCenter(dd_ap);
		columns_wr = table_wr.getColumns();
		columns_ap = table_ap.getColumns();
		
		begin_data.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				apply_table_wr_filter_selection();
				table_wr.getColumns().get(0).setVisible(false);
		        table_wr.getColumns().get(0).setVisible(true);
		        clear_filter.setDisable(false);
		        flag = 2;
			}
		});
		table_wr.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				upd_wr.setDisable(false);
				upd_ap.setDisable(true);
				//mu_main_controller.getPrimaryStage().setAlwaysOnTop(false);
			}
		});
		
		scl._style(private_ap);
		
		private_ap.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				table_ap.setItems(qr._select_data_ap(USER_S));
				table_ap.getColumns().get(0).setVisible(false);
		        table_ap.getColumns().get(0).setVisible(true);
		        private_ap.setDisable(true);
		        showall_ap.setDisable(false);
		        chk_btn = true;
				shopsEnableAll();
		        tableCellAlignCenter(dd_ap);
			}
		});
		
		scl._style(showall_ap);
		
		showall_ap.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				//if (SHOP_NAME.equals("S,W"))
				//{
				//	table_ap.setItems(qr._select_data_ap_sw(USER_S));
				//	table_ap.getColumns().get(0).setVisible(false);
			     //   table_ap.getColumns().get(0).setVisible(true);
			      //  private_ap.setDisable(false);
			      //  showall_ap.setDisable(true);
				//}
				//else
				//{
					table_ap.setItems(qr._select_data_ap_shop(SHOP_NAME, USER_S));
					table_ap.getColumns().get(0).setVisible(false);
			        table_ap.getColumns().get(0).setVisible(true);
			        private_ap.setDisable(false);
			        showall_ap.setDisable(true);
				//}
				shopsEnableAll();
				sopsSelectInitial();

				chk_btn = false;
				tableCellAlignCenter(dd_ap);
			}
		});
		
		assembly.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				table_ap.setItems(qr._select_data_all_shop("A"));
				table_ap.getColumns().get(0).setVisible(false);
		        table_ap.getColumns().get(0).setVisible(true);
		        
		        SHOP_NAME_A = "A";

		        shopsSelectOne(assembly);
		        
		        filtre_apwr.setValue(sort_filter);
		        tableCellAlignCenter(dd_ap);
			}
		});

		logistics.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				table_ap.setItems(qr._select_data_all_shop("L"));
				table_ap.getColumns().get(0).setVisible(false);
				table_ap.getColumns().get(0).setVisible(true);

				SHOP_NAME_A = "L";

				shopsSelectOne(logistics);

				filtre_apwr.setValue(sort_filter);
				tableCellAlignCenter(dd_ap);
			}
		});

		paint.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				table_ap.setItems(qr._select_data_all_shop("P"));
				table_ap.getColumns().get(0).setVisible(false);
		        table_ap.getColumns().get(0).setVisible(true);
		        
		        SHOP_NAME_A = "P";
		        
		        shopsSelectOne(paint);
		        
		        filtre_apwr.setValue(sort_filter);
		        tableCellAlignCenter(dd_ap);
			}
		});
		stamp.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				table_ap.setItems(qr._select_data_all_shop("S"));
				table_ap.getColumns().get(0).setVisible(false);
		        table_ap.getColumns().get(0).setVisible(true);
		        
		        SHOP_NAME_A = "S";
		        
		        shopsSelectOne(stamp);
		        
		        filtre_apwr.setValue(sort_filter);
		        tableCellAlignCenter(dd_ap);
			}
		});
		welding.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				table_ap.setItems(qr._select_data_all_shop("W"));
				table_ap.getColumns().get(0).setVisible(false);
		        table_ap.getColumns().get(0).setVisible(true);
		        
		        SHOP_NAME_A = "W";
		        
		        shopsSelectOne(welding);
		        
		        filtre_apwr.setValue(sort_filter);
		        tableCellAlignCenter(dd_ap);
			}
		});
		
		scl._style(clear_filter);
		
		clear_filter.setFocusTraversable(false);
		clear_filter.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				begin_data.setValue(LocalDate.now().minusDays(7));
				last_data.setValue(LocalDate.now());
				table_wr.setItems(qr._select_data_wr(fx_dp.toString(begin_data.getValue()), fx_dp.toString(last_data.getValue())));
				table_wr.getColumns().get(0).setVisible(false);
		        table_wr.getColumns().get(0).setVisible(true);
		        clear_filter.setDisable(true);
		        flag = 0;
			}
		});
		
		scl._style(upd_table_ap);
		
		upd_table_ap.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				initSops();
				if(chk_btn)
				{
					table_ap.setItems(qr._select_data_ap(USER_S));
					columns_ap.get(0).setVisible(false);
				    columns_ap.get(0).setVisible(true);
				    private_ap.setDisable(true);
				    showall_ap.setDisable(false);
				}
				else
				{
					table_ap.setItems(qr._select_data_ap_shop(SHOP_NAME, USER_S));
					table_ap.getColumns().get(0).setVisible(false);
			        table_ap.getColumns().get(0).setVisible(true);
			        private_ap.setDisable(false);
			        showall_ap.setDisable(true);
				}

				shopsEnableAll();

			    add_wr.setDisable(true);
			    upd_ap.setDisable(true);
			    print_tsk.setDisable(true);
			    export_excel.setDisable(true);
			}
		});
		
		upd_tbl_wp.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				table_wp.setItems(qr._select_data_wp(USER_S));
				table_wp.getColumns().get(0).setVisible(false);
			    table_wp.getColumns().get(0).setVisible(true);
			}
		});
		
		scl._style(upd_table_wr);
		
		upd_table_wr.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				//0 - без сортировки; 1 - сортировка по номеру задачи; 2 - сортировка по времени
				switch (flag) {
					case 0:
						table_wr.setItems(qr._select_data_wr(fx_dp.toString(begin_data.getValue()), fx_dp.toString(last_data.getValue())));
						columns_wr.get(0).setVisible(false);
						columns_wr.get(0).setVisible(true);
						upd_wr.setDisable(true);
						break;
					case 1:
						table_wr.setItems(qr._select_sort_apnum_wr(ID_WR));
						table_wr.getColumns().get(0).setVisible(false);
						table_wr.getColumns().get(0).setVisible(true);
						break;
					case 2:
						table_wr.setItems(qr._select_data_wr(fx_dp.toString(begin_data.getValue()), fx_dp.toString(last_data.getValue())));
						table_wr.getColumns().get(0).setVisible(false);
						table_wr.getColumns().get(0).setVisible(true);
						clear_filter.setDisable(false);
						break;
					case 3:
						table_wr.setItems(qr._select_sort_shop_wr(fx_dp.toString(begin_data.getValue()), fx_dp.toString(last_data.getValue()), SORT_SHOP));
						table_wr.getColumns().get(0).setVisible(false);
						table_wr.getColumns().get(0).setVisible(true);
						break;
					case 4:
						table_wr.setItems(qr._select_sort_resp_wr(fx_dp.toString(begin_data.getValue()), fx_dp.toString(last_data.getValue()), scl.parser_str(SORT_RESP, 0)));
						table_wr.getColumns().get(0).setVisible(false);
						table_wr.getColumns().get(0).setVisible(true);
						break;
					case 5:
						table_wr.setItems(qr._select_sort_OFT_wr(fx_dp.toString(begin_data.getValue()), fx_dp.toString(last_data.getValue()), getUserLettersID(table_wr_filter_state)));
						table_wr.getColumns().get(0).setVisible(false);
						table_wr.getColumns().get(0).setVisible(true);
						break;
				}
				/*if(flag == 1)
				{
					table_wr.setItems(qr._select_sort_apnum_wr(ID_WR));
					table_wr.getColumns().get(0).setVisible(false);
			        table_wr.getColumns().get(0).setVisible(true);
				}
				else if(flag == 2)
				{
					table_wr.setItems(qr._select_data_wr(fx_dp.toString(begin_data.getValue()), fx_dp.toString(last_data.getValue())));
					table_wr.getColumns().get(0).setVisible(false);
			        table_wr.getColumns().get(0).setVisible(true);
			        clear_filter.setDisable(false);
				}
				else if(flag == 0)
				{
					table_wr.setItems(qr._select_data_wr(fx_dp.toString(begin_data.getValue()), fx_dp.toString(last_data.getValue())));
					columns_wr.get(0).setVisible(false);
				    columns_wr.get(0).setVisible(true);
				    upd_wr.setDisable(true);
				}
				else if(flag == 3)
		        {
		        	table_wr.setItems(qr._select_sort_shop_wr(fx_dp.toString(begin_data.getValue()), fx_dp.toString(last_data.getValue()), SORT_SHOP));
			    	table_wr.getColumns().get(0).setVisible(false);
			        table_wr.getColumns().get(0).setVisible(true);
		        }
				else if(flag == 4)
		        {
		        	table_wr.setItems(qr._select_sort_resp_wr(fx_dp.toString(begin_data.getValue()), fx_dp.toString(last_data.getValue()), scl.parser_str(SORT_RESP, 0)));
			    	table_wr.getColumns().get(0).setVisible(false);
			        table_wr.getColumns().get(0).setVisible(true);
		        }*/
			}
		});
		
		scl._style(print_tsk);
		
		print_tsk.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				try {
	                // --- Show Jasper Report on click-----
					new PrintReport().showReport(table_ap.getSelectionModel().getSelectedItem().getId().substring(2));
	            } catch (ClassNotFoundException | JRException | SQLException e1) {
	                e1.printStackTrace();
	            }
			}
		});
		
		scl._style(export_excel);
		
		export_excel.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				try {
	                // --- Show Jasper Report on click-----
					new ExportToExcel().showReport(table_ap.getSelectionModel().getSelectedItem().getId().substring(2));
	            } catch (ClassNotFoundException | JRException | SQLException e1) {
	                e1.printStackTrace();
	            }
			}
		});
		
		set_btn.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				//tip = new Tooltip(str_set_btn);
				//Point2D p = set_btn.localToScreen(set_btn.getLayoutBounds().getMaxX(), set_btn.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        //tip.show(set_btn, p.getX(), p.getY());
				Platform.runLater(() -> {
					Tooltip tip = new Tooltip(str_set_btn);
					set_btn.setTooltip(tip);
				});
		    }
		});
		//set_btn.setOnMouseExited(new EventHandler<Event>() {

		//	@Override
		//	public void handle(Event event) {
		//		tip.hide();
		//	}
		//});
		rus_btn.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip = new Tooltip("Русский");
				Point2D p = rus_btn.localToScreen(rus_btn.getLayoutBounds().getMaxX(), rus_btn.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(rus_btn, p.getX(), p.getY());
			}
		});
		rus_btn.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip.hide();
			}
		});
		usa_btn.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip = new Tooltip("English");
				Point2D p = usa_btn.localToScreen(usa_btn.getLayoutBounds().getMaxX(), usa_btn.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(usa_btn, p.getX(), p.getY());
			}
		});
		usa_btn.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip.hide();
			}
		});
		chn_btn.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip = new Tooltip("中文");
				Point2D p = chn_btn.localToScreen(chn_btn.getLayoutBounds().getMaxX(), chn_btn.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(chn_btn, p.getX(), p.getY());
			}
		});
		chn_btn.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip.hide();
			}
		});
		
		assembly.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip = new Tooltip(lbl_assembly);
				Point2D p = assembly.localToScreen(assembly.getLayoutBounds().getMaxX(), assembly.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(assembly, p.getX(), p.getY());
			}
		});
		assembly.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip.hide();
			}
		});

		logistics.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip = new Tooltip(lbl_logistics);
				Point2D p = logistics.localToScreen(logistics.getLayoutBounds().getMaxX(), logistics.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
				tip.show(logistics, p.getX(), p.getY());
			}
		});
		logistics.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip.hide();
			}
		});
		
		paint.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip = new Tooltip(lbl_paint);
				Point2D p = paint.localToScreen(paint.getLayoutBounds().getMaxX(), paint.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(paint, p.getX(), p.getY());
			}
		});
		paint.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip.hide();
			}
		});
		
		stamp.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip = new Tooltip(lbl_stamp);
				Point2D p = stamp.localToScreen(stamp.getLayoutBounds().getMaxX(), stamp.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(stamp, p.getX(), p.getY());
			}
		});
		stamp.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip.hide();
			}
		});
		
		welding.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip = new Tooltip(lbl_welding);
				Point2D p = welding.localToScreen(welding.getLayoutBounds().getMaxX(), welding.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(welding, p.getX(), p.getY());
			}
		});
		welding.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip.hide();
			}
		});
		
		_table_update.addListener(new ListChangeListener<hmmr_ap_model>() {
		    @Override
			public void onChanged(Change<? extends hmmr_ap_model> c) {
				table_ap.setItems(qr._select_data_ap(USER_S));
		    	table_ap.getColumns().get(0).setVisible(false);
		        table_ap.getColumns().get(0).setVisible(true);
			}
		     });
		_table_update_wp.addListener(new ListChangeListener<hmmr_wp_model>() {
		    @Override
			public void onChanged(Change<? extends hmmr_wp_model> c) {
				table_wp.setItems(qr._select_data_wp(USER_S));
		    	table_wp.getColumns().get(0).setVisible(false);
		        table_wp.getColumns().get(0).setVisible(true);
			}
		     });
		_table_update_wr.addListener(new ListChangeListener<hmmr_wr_model>() {
		    @Override
			public void onChanged(Change<? extends hmmr_wr_model> c) {
				table_wr.setItems(qr._select_data_wr(fx_dp.toString(begin_data.getValue()), fx_dp.toString(last_data.getValue())));
		    	table_wr.getColumns().get(0).setVisible(false);
		        table_wr.getColumns().get(0).setVisible(true);
		        if(flag == 3)
		        {
		        	table_wr.setItems(qr._select_sort_shop_wr(fx_dp.toString(begin_data.getValue()), fx_dp.toString(last_data.getValue()), SORT_SHOP));
			    	table_wr.getColumns().get(0).setVisible(false);
			        table_wr.getColumns().get(0).setVisible(true);
		        }
		        if(flag == 4)
		        {
		        	table_wr.setItems(qr._select_sort_resp_wr(fx_dp.toString(begin_data.getValue()), fx_dp.toString(last_data.getValue()), scl.parser_str(SORT_RESP, 0)));
			    	table_wr.getColumns().get(0).setVisible(false);
			        table_wr.getColumns().get(0).setVisible(true);
		        }
			}
		     });
		
		shop_resp_wr.setPromptText(sort_filter);
		ObservableList<String> shop_n = FXCollections.observableArrayList();
		shop_n.removeAll(shop_n);
		shop_n.add("A");
		shop_n.add("L");
		shop_n.add("P");
		shop_n.add("S");
		shop_n.add("W");
		
		shop_resp_wr.setItems(shop_n);
		
		r_shop_wr.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				r_resp_wr.setSelected(false);
				r_OFT_wr.setSelected(false);
				r_shop_wr.setSelected(true);
								
				ObservableList<String> shop_n1 = FXCollections.observableArrayList();
				shop_n1.removeAll(shop_n1);
				shop_n1.add("A");
				shop_n1.add("L");
				shop_n1.add("P");
				shop_n1.add("S");
				shop_n1.add("W");
				
				shop_resp_wr.setItems(shop_n1);
				shop_resp_wr.setValue(SHOP_NAME);
			}
		});

		r_OFT_wr.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				r_resp_wr.setSelected(false);
				r_OFT_wr.setSelected(true);
				r_shop_wr.setSelected(false);
				shop_resp_wr.setItems(qr._select_fio_for_ap(3, SHOP_NAME));
				//shop_resp_wr.setValue("Фильтр");
			}
		});

		r_resp_wr.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				r_resp_wr.setSelected(true);
				r_OFT_wr.setSelected(false);
				r_shop_wr.setSelected(false);
				
				shop_resp_wr.setItems(qr._select_fio_for_ap(2, SHOP_NAME));
				//shop_resp_wr.setValue("Фильтр");
			}
		});
		shop_resp_wr.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				table_wr_filter_state = shop_resp_wr.getSelectionModel().getSelectedItem();
				apply_table_wr_filter_selection();
			}
		});
				
		//Ставим фокус и опускаемся на последнюю строку таблицы     
        table_wr.requestFocus();
        table_wr.getFocusModel().focus(0);
        table_wr.getSelectionModel().selectLast();
        table_wr.scrollTo(table_wr.getItems().size());
	}

	private void initSops() {
		SHOP_NAME = scl.parser_str(qr._select_user(conn_connector.USER_ID), 5);
		if (SHOP_NAME != "S,W") {
			availableShops = Arrays.stream(SHOP_NAME.split(",")).map(String::trim).collect(Collectors.toSet());
			SHOP_NAME = Arrays.stream(SHOP_NAME.split(",")).map(String::trim).toArray(String[]::new)[0];
		} else {
			availableShops.add(SHOP_NAME);
		}
	}

	private void sopsSelectInitial() {
		if(!(conn_connector.USER_ROLE.equals("Administrator") || conn_connector.USER_ROLE.equals("Group Lead") ||
				conn_connector.USER_ROLE.equals("Technics"))) {
			//Кнопка выбранного выброанного цеха даложны быть не активной
			shopButtons[ArrayUtils.indexOf(SHOP_LETTERS, SHOP_NAME)].setDisable(true);
		}
	}

	private void shopsSelectOne(JFXButton selectedShop) {
		shopsEnableAll();
		selectedShop.setDisable(true);
		if (!(conn_connector.USER_ROLE.equals("Administrator") || conn_connector.USER_ROLE.equals("Group Lead") ||
				conn_connector.USER_ROLE.equals("Technics"))) {
			private_ap.setDisable(false);
			showall_ap.setDisable(false);
			chk_btn = false;
		}
	}

	private void shopsEnableAll() {
		if(conn_connector.USER_ROLE.equals("Administrator") || conn_connector.USER_ROLE.equals("Group Lead"))
		{
			for (JFXButton shop : shopButtons) {
				shop.setDisable(false);
			}
		} else if(! conn_connector.USER_ROLE.equals("Technics")) {
			if (SHOP_NAME == "S,W") {
				return;
			}
			for (int i = 0; i < shopButtons.length; i++) {
				if (availableShops.contains(SHOP_LETTERS[i])) {
					shopButtons[i].setDisable(false);
				} else {
					shopButtons[i].setDisable(true);
				}
			}
		}

	}

	private void apply_table_wr_filter_selection() {
		if(table_wr_filter_state != null)
		{
			if(r_shop_wr.isSelected()) {
				table_wr.setItems(qr._select_sort_shop_wr(fx_dp.toString(begin_data.getValue()), fx_dp.toString(last_data.getValue()), table_wr_filter_state));
				table_wr.getColumns().get(0).setVisible(false);
				table_wr.getColumns().get(0).setVisible(true);
				SORT_SHOP = table_wr_filter_state;
				flag = 3;
			}
			if(r_resp_wr.isSelected())
			{
				table_wr.setItems(qr._select_sort_resp_wr(fx_dp.toString(begin_data.getValue()), fx_dp.toString(last_data.getValue()), scl.parser_str(table_wr_filter_state, 0)));
				table_wr.getColumns().get(0).setVisible(false);
				table_wr.getColumns().get(0).setVisible(true);
				SORT_RESP = table_wr_filter_state;
				flag = 4;
			}
			if(r_OFT_wr.isSelected())
			{

				table_wr.setItems(qr._select_sort_OFT_wr(fx_dp.toString(begin_data.getValue()), fx_dp.toString(last_data.getValue()), getUserLettersID(table_wr_filter_state)));
				table_wr.getColumns().get(0).setVisible(false);
				table_wr.getColumns().get(0).setVisible(true);
				SORT_RESP = table_wr_filter_state;
				flag = 5;
			}
		}
	}

	private String getUserLettersID(String newValue) {
		Pattern firstLetters = Pattern.compile("^\\S{1,5}");
		Matcher matcher = firstLetters.matcher(newValue);
		matcher.find();
		return matcher.group(0);
	}

	private void initData()
	{
		table_ap.setItems(qr._select_data_ap(USER_S));
		table_wp.setItems(qr._select_data_wp(USER_S));
		
		table_wr.setItems(qr._select_data_wr(fx_dp.toString(begin_data.getValue()), fx_dp.toString(last_data.getValue())));
	}
	
	private void lang_fun(String loc1, String loc2)
	{
		ResourceBundle lngBndl = ResourceBundle
	            .getBundle("bundles.LangBundle", new Locale(loc1, loc2));
		
		type_ap.setText(lngBndl.getString("type_ap"));
		desc_ap.setText(lngBndl.getString("desc_ap"));
		dd_ap.setText(lngBndl.getString("dd_ap"));
		equip_ap.setText(lngBndl.getString("equip_ap"));
		//otv_task_ap.setText(lngBndl.getString("otv_task_ap"));
		//otv_ap.setText(lngBndl.getString("otv_ap"));
		private_ap.setText(lngBndl.getString("private_ap"));
		showall_ap.setText(lngBndl.getString("showall_ap"));
		upd_table_ap.setText(lngBndl.getString("upd_table_ap"));
		print_tsk.setText(lngBndl.getString("print_tsk"));
		export_excel.setText(lngBndl.getString("export_excel"));
		add_wr.setText(lngBndl.getString("add_wr"));
		create_ap.setText(lngBndl.getString("create_ap"));
		upd_ap.setText(lngBndl.getString("upd_ap"));
		
		shift_report_wr.setText(lngBndl.getString("shift_report_wr"));
		req_action_wr.setText(lngBndl.getString("req_action_wr"));
		actual_time_wr.setText(lngBndl.getString("actual_time_wr"));
		actual_time1_wr.setText(lngBndl.getString("actual_time1_wr"));
		data_wr.setText(lngBndl.getString("data_wr"));
		equip_wr.setText(lngBndl.getString("equip_wr"));
		record_type_wr.setText(lngBndl.getString("record_type_wr"));
		resp_wr.setText(lngBndl.getString("resp_wr"));
		status_wr.setText(lngBndl.getString("status_wr"));
		clear_filter.setText(lngBndl.getString("clear_filter"));
		upd_table_wr.setText(lngBndl.getString("upd_table_wr"));
		upd_wr.setText(lngBndl.getString("upd_wr"));
		from_wr.setText(lngBndl.getString("from_wr"));
		to_wr.setText(lngBndl.getString("to_wr"));
		inst_l = lngBndl.getString("inst_l");
		conf_l = lngBndl.getString("conf_l");
		c_resp = lngBndl.getString("resp_wr");
		c_oft = lngBndl.getString("otv_task_ap");
		c_own = lngBndl.getString("c_own");
		prior_img = lngBndl.getString("prior");
		at_title = lngBndl.getString("at_title");
		str_set_btn = lngBndl.getString("str_set_btn");
		
		type_wp.setText(lngBndl.getString("type_ap"));
		desc_wp.setText(lngBndl.getString("desc_ap"));
		dd_wp.setText(lngBndl.getString("dd_wp"));
		equip_wp.setText(lngBndl.getString("equip_ap"));
		
		lbl_assembly = lngBndl.getString("lbl_assembly");
		lbl_logistics = lngBndl.getString("lbl_logistics");
		lbl_paint = lngBndl.getString("lbl_paint");
		lbl_stamp = lngBndl.getString("lbl_stamp");
		lbl_welding = lngBndl.getString("lbl_welding");
		
		sort_filter = lngBndl.getString("sort_filter");
		sort_tsk = lngBndl.getString("sort_tsk");
		sort_w_otv = lngBndl.getString("sort_w_otv");
		sort_clear_filter = lngBndl.getString("sort_clear_filter");
		r_shop_wr.setText(lngBndl.getString("col_shop_pm"));
		r_resp_wr.setText(lngBndl.getString("resp_wr"));
		r_OFT_wr.setText(lngBndl.getString("lbl_oft_ap"));
				
		//if(loc1.equals("en") && loc2.equals("EN"))
		//{
		//	hb_btn_ap.setPrefWidth(888);
		//	hb_btn_wr.setPrefWidth(1270);
		//}
	}
	
	protected void pminst_add() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("add_rec_ap.fxml"));
		Scene scene = new Scene(root);
		Stage stage_set = new Stage();
		stage_set.initModality(Modality.WINDOW_MODAL);	
		stage_set.initOwner(conn_connector.getPrimaryStage());
	    stage_set.setTitle("M&U - Add Record Window");
	    stage_set.setResizable(false);
	    stage_set.setScene(scene);
	    stage_set.show();
	}
	//Вызываем окно обновления записи
	protected void ap_upd() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("upd_rec_ap.fxml"));
		Scene scene = new Scene(root);
		Stage stage_set = new Stage();
		stage_set.initModality(Modality.WINDOW_MODAL);	
		stage_set.initOwner(conn_connector.getPrimaryStage());
	    stage_set.setTitle("M&U - Update Record Window");
	    stage_set.setResizable(false);
	    stage_set.setScene(scene);
	    stage_set.show();
	}
	//Вызываем окно обновления записи WP
	protected void wp_upd() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Upd_Rec_WP.fxml"));
		Scene scene = new Scene(root);
		Stage stage_set = new Stage();
		stage_set.initModality(Modality.WINDOW_MODAL);	
		stage_set.initOwner(conn_connector.getPrimaryStage());
		stage_set.setTitle("M&U - Update Record Window");
		stage_set.setResizable(false);
		stage_set.setScene(scene);
		stage_set.show();
		}
	//Вызываем окно обновления записи WR
	protected void wr_upd() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("upd_rec_wr.fxml"));
		Scene scene = new Scene(root);
		Stage stage_set = new Stage();
		stage_set.initModality(Modality.WINDOW_MODAL);	
		stage_set.initOwner(conn_connector.getPrimaryStage());
	    stage_set.setTitle("M&U - Update Record Window");
	    stage_set.setResizable(false);
	    stage_set.setScene(scene);
		stage_set.show();
	}
	//Вызываем окно записи для WR
	protected void addwr_start() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("add_rec_wr.fxml"));
		Scene scene = new Scene(root);
		Stage stage_set = new Stage();
		stage_set.initModality(Modality.WINDOW_MODAL);	
		stage_set.initOwner(conn_connector.getPrimaryStage());
	    stage_set.setTitle("M&U - Add Record Window");
	    stage_set.setResizable(false);
	    stage_set.setScene(scene);
	    stage_set.show();
	}	
	
	
	private void addButtonToTable() {
       // TableColumn<Data, Void> colBtn = new TableColumn("Button Column");

		//Добавляем кнопку в table_wr
        final ObservableList<TableColumn<hmmr_ap_model, ?>> ap_instr_col = table_ap.getColumns();
		//final TableColumn<hmmr_ap_model, Button> favoriteColumn2 = new TableColumn<hmmr_ap_model, Button>(inst_l);
        favoriteColumn2.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<hmmr_ap_model, Button>, ObservableValue<Button>>() {

                    @Override
                    public ObservableValue<Button> call(TableColumn.CellDataFeatures<hmmr_ap_model, Button> arg0) {
                        hmmr_ap_model data = arg0.getValue();
                        Button btn = new Button();
                        favoriteColumn2.setStyle( "-fx-alignment: CENTER;");
                        if(data.getinst_btn().equals("-") || data.getinst_btn().equals("null"))
                        	btn.setDisable(true);
                        else {
                        	File file = new File(data.getinst_btn());
                        	if(file.exists()) {
                        		btn.setDisable(false);
                        	}
                        	else {
                        		btn.setDisable(true);
                        	}
                        }
                        
                        {
                        	Platform.runLater(() -> {
            					Image imageOk = new Image(getClass().getResourceAsStream("document.png"));
            					btn.setGraphic(new ImageView(imageOk)); });
                        	
            				table_ap.setOnMouseClicked(new EventHandler<Event>() {

                    			@Override
                    			public void handle(Event event) {
                    				upd_wr.setDisable(true);
                    				
                    				//mu_main_controller.getPrimaryStage().setAlwaysOnTop(false);
                    				try {
	                    				                  				
	                    				//Получаем № ap для использования его в таблице wr
	                    				if(!table_ap.getSelectionModel().getSelectedItem().getId().equals("null") || table_ap.getSelectionModel().getSelectedItem().getId() != null) {
	                    					_idap_for_wr = table_ap.getSelectionModel().getSelectedItem().getId();
	                    					add_wr.setDisable(false);
	                    					if(!conn_connector.USER_ROLE.equals("Technics"))
	                        					upd_ap.setDisable(false);
	                    				}
                    				
	                    				_fill_rec(_idap_for_wr.substring(2));
	                    				//mu_main_controller.getPrimaryStage().setAlwaysOnTop(false);
	                    				//table_wr.setItems(qr._select_data_wr(fx_dp.toString(begin_data.getValue()), fx_dp.toString(last_data.getValue())));       				        				
	                    				           						
	                    				//col_action.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
	                    				if(!conn_connector.USER_ROLE.equals("Technics")) {
	                    					upd_ap.setDisable(false);
	                    					print_tsk.setDisable(false);
	                    					export_excel.setDisable(false);
	                    				}
	                    				table_ap.setEditable(true);
	                    				
                    				}
                    				catch (Exception e) {
                    					print_tsk.setDisable(true);
                    					export_excel.setDisable(true);
                    					upd_ap.setDisable(true);
                    					add_wr.setDisable(true);
									}
                    			}
                    		});
            			 }
                        
                        //устанавливаем checkbox если в базе в этом поле стоит 1
                   //     btn.setText(data.getap_num());
                        //запрещаем бегунку прокрутки возвращаться назад после нажатия кнопки
                        //btn.setFocusTraversable(false);
                        btn.setOnAction(new EventHandler<ActionEvent>() {
							
							@SuppressWarnings("static-access")
							@Override
							public void handle(ActionEvent event) {
								try {
									File inst_path = new File(qr._select_inst_for_ap(data.getId()));//table_ap.getSelectionModel().getSelectedItem()
									mn._run_excel(inst_path);
								}
								catch (Exception e) {
									scl._AlertDialog("Сначала выделите строку!", "Ошибка!");
								}
//			                    	Runtime runtime = Runtime.getRuntime();
//			                    	if(inst_path.length() != 0)
//										runtime.exec("excel " + inst_path);
								//btn.setDisable(true);
							}
						});

                        return new SimpleObjectProperty<Button>(btn);
                    }

                });
        ap_instr_col.add(favoriteColumn2);
    }
	
	private void addButtonToTable_wp() {
        // TableColumn<Data, Void> colBtn = new TableColumn("Button Column");

 		//Добавляем кнопку в table_wr
         final ObservableList<TableColumn<hmmr_wp_model, ?>> columns_wp = table_wp.getColumns();
 		//final TableColumn<hmmr_ap_model, Button> favoriteColumn2 = new TableColumn<hmmr_ap_model, Button>(inst_l);
         wp_inst.setCellValueFactory(
                 new Callback<TableColumn.CellDataFeatures<hmmr_wp_model, Button>, ObservableValue<Button>>() {

                     @Override
                     public ObservableValue<Button> call(TableColumn.CellDataFeatures<hmmr_wp_model, Button> arg0) {
                         hmmr_wp_model data = arg0.getValue();
                         Button btn1 = new Button();
                         wp_inst.setStyle( "-fx-alignment: CENTER;");
                         if(data.getinst_btn().equals("-") || data.getinst_btn().equals("null"))
                         	btn1.setDisable(true);
                         else {
                         	File file = new File(data.getinst_btn());
                         	if(file.exists()) {
                         		btn1.setDisable(false);
                         	}
                         	else {
                         		btn1.setDisable(true);
                         	}
                         }
                         
                         {
                         	Platform.runLater(() -> {
             					Image imageOk = new Image(getClass().getResourceAsStream("document.png"));
             					btn1.setGraphic(new ImageView(imageOk)); });
                         	
             				table_wp.setOnMouseClicked(new EventHandler<Event>() {

                     			@Override
                     			public void handle(Event event) {
                     				table_wp.setEditable(true);
 	                    			if(!conn_connector.USER_ROLE.equals("Technics"))
 	                    				upd_rec_wp.setDisable(false);
                     			}
                     		});
             			 }
                        
                         btn1.setOnAction(new EventHandler<ActionEvent>() {
 							
 							@SuppressWarnings("static-access")
 							@Override
 							public void handle(ActionEvent event) {
 								
 								try {
 									File inst_path = new File(qr._select_inst_for_wp(data.getId()));//table_wp.getSelectionModel().getSelectedItem()
 									mn._run_excel(inst_path);
 								}
 								catch (Exception e) {
 									scl._AlertDialog("Сначала выделите строку!", "Ошибка!");
 								}
 							}
 						});

                         return new SimpleObjectProperty<Button>(btn1);
                     }

                 });
         columns_wp.add(wp_inst);
	}
	
	public void TabSelect(Tab tab)
	{
		//переключаемся сразу на вкладку WR
        SingleSelectionModel<Tab> selectionModel = tb_pn.getSelectionModel();
        selectionModel.select(tab);
	}
/*	@SuppressWarnings({ "unchecked", "unused" })
	private void addButtonToTable_WR() {
       // TableColumn<Data, Void> colBtn = new TableColumn("Button Column");

      Callback<TableColumn<hmmr_wr_model, String>, TableCell<hmmr_wr_model, String>> cellFactory
        = //
        new Callback<TableColumn<hmmr_wr_model, String>, TableCell<hmmr_wr_model, String>>() {
    @SuppressWarnings("rawtypes")
	@Override
    public TableCell call(final TableColumn<hmmr_wr_model, String> param) {
        final TableCell<hmmr_wr_model, String> cell = new TableCell<hmmr_wr_model, String>() {
        	hmmr_wr_model hwr;
        	public Button btn1 = new Button();
        	{
            btn1.setText(hwr.getap_num());
        	}
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                	//btn.setDisable(false);
                    btn1.setOnAction(event -> {

                    	String inst_path = qr._select_inst_for_ap(table_ap.getSelectionModel().getSelectedItem().getId());
                    });
                    setGraphic(btn1);
                    setText(null);
                }
            }
        };
        return cell;
    }
};

       col_action1.setCellFactory(cellFactory);

       table_wr.getColumns().add(col_action1);

    }*/
	

	/*class BooleanCell extends TableCell<hmmr_wr_model, Boolean> {
        private CheckBox checkBox;

        public BooleanCell() {
            checkBox = new CheckBox();
            checkBox.setDisable(true);
            checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if (isEditing())
                        commitEdit(newValue == null ? false : newValue);
                }
            });
            this.setGraphic(checkBox);
            this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            this.setEditable(true);
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();
            checkBox.setDisable(true);
        }

        public void commitEdit(Boolean value) {
            super.commitEdit(value);

            checkBox.setDisable(true);
        }

        @Override
        public void startEdit() {
            super.startEdit();
            if (isEmpty()) {
                return;
            }
            checkBox.setDisable(false);
            checkBox.requestFocus();
        }

        @Override
        public void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);
            if (!isEmpty()) {
                checkBox.setSelected(item);
            }
        }
    }*/
	
	
	private void func_upd(String str)
	{
		_sql_rez = qr._select_for_update_ap(str.substring(2));
		_id_ap = str.substring(2); 
		_pmnum_ap = scl.parser_str_str_str(_sql_rez, 0);
		_type_ap = scl.parser_str_str_str(_sql_rez, 1);
		_description_ap = scl.parser_str_str_str(_sql_rez, 2);
		_due_date_ap = scl.parser_str_str_str(_sql_rez, 3); 
		_equip_ap = scl.parser_str_str_str(_sql_rez, 4);
		_oft_ap = scl.parser_str_str_str(_sql_rez, 5);
		_otv_ap = scl.parser_str_str_str(_sql_rez, 6);
		_icon = scl.parser_str_str_str(_sql_rez, 7);
		_icon_at = scl.parser_str_str_str(_sql_rez, 8);
		
		try {
			//_flag = false;
			ap_upd();
			//_flag = true;
			//t = new Thread(update_table());
    		//t.setDaemon(true);
    		//t.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//Обноавляем запись в WR
	private void func_upd_wr(String str)
	{
		String _sql_rez_wr = qr._select_for_update_wr(str.substring(2));
		_id_wr = str; 
		_shift_report_wr = scl.parser_str_str_str(_sql_rez_wr, 0);
		_req_action_wr = scl.parser_str_str_str(_sql_rez_wr, 1);
		_actual_time_wr = scl.parser_str_str_str(_sql_rez_wr, 2);
		_actual_time1_wr = scl.parser_str_str_str(_sql_rez_wr, 3); 
		_actual_time2_wr = scl.parser_str_str_str(_sql_rez_wr, 4);
		_actual_time3_wr = scl.parser_str_str_str(_sql_rez_wr, 5);
		_actual_time4_wr = scl.parser_str_str_str(_sql_rez_wr, 6); 
		_data_wr = scl.parser_str_str_str(_sql_rez_wr, 7);
		_equip_wr = scl.parser_str_str_str(_sql_rez_wr, 8);
		_record_type_wr = scl.parser_str_str_str(_sql_rez_wr, 9);
		_resp_wr = scl.parser_str_str_str(_sql_rez_wr, 10);
		_resp2_wr = scl.parser_str_str_str(_sql_rez_wr, 11);
		_resp3_wr = scl.parser_str_str_str(_sql_rez_wr, 12);
		_resp4_wr = scl.parser_str_str_str(_sql_rez_wr, 13);
		_status_wr = scl.parser_str_str_str(_sql_rez_wr, 14);
		_qty_wr = scl.parser_str_str_str(_sql_rez_wr, 15);
		_ap_num_wr = scl.parser_str_str_str(_sql_rez_wr, 16);
		_work_time = scl.parser_str_str_str(_sql_rez_wr, 17);
		_actual_date = scl.parser_str_str_str(_sql_rez_wr, 18);
		_actual_date_2 = scl.parser_str_str_str(_sql_rez_wr, 19);
		_actual_date_3 = scl.parser_str_str_str(_sql_rez_wr, 20);
		_actual_date_4 = scl.parser_str_str_str(_sql_rez_wr, 21);
		_actual_date1 = scl.parser_str_str_str(_sql_rez_wr, 22);
		_actual_date2 = scl.parser_str_str_str(_sql_rez_wr, 23);
		_actual_date3 = scl.parser_str_str_str(_sql_rez_wr, 24);
		_actual_date4 = scl.parser_str_str_str(_sql_rez_wr, 25);
		_user_wr = scl.parser_str_str_str(_sql_rez_wr, 26);
		_hours1 = scl.parser_str_str_str(_sql_rez_wr, 27);
		_hours1_2 = scl.parser_str_str_str(_sql_rez_wr, 28);
		_hours1_3 = scl.parser_str_str_str(_sql_rez_wr, 29);
		_hours1_4 = scl.parser_str_str_str(_sql_rez_wr, 30);
		_min1 = scl.parser_str_str_str(_sql_rez_wr, 31);
		_hours2 = scl.parser_str_str_str(_sql_rez_wr, 32);
		_hours2_2 = scl.parser_str_str_str(_sql_rez_wr, 33);
		_hours2_3 = scl.parser_str_str_str(_sql_rez_wr, 34);
		_hours2_4 = scl.parser_str_str_str(_sql_rez_wr, 35);
		_min2 = scl.parser_str_str_str(_sql_rez_wr, 36);
		_user_number = scl.parser_str_str_str(_sql_rez_wr, 37);
		_activity_type_wr = scl.parser_str_str_str(_sql_rez_wr, 38);
		//даты для сортировки таблицы
		before_date = fx_dp.toString(begin_data.getValue());
		after_date = fx_dp.toString(last_data.getValue());		
		try {
			//_flag = false;
			wr_upd();
			_flag = true;

			//t = new Thread(update_table_wr());
    		//t.setDaemon(true);
    		//t.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void refreshTable_ap(ObservableList<TableColumn<hmmr_ap_model, ?>> col) {
		// table_ap.getColumns().removeAll(columns_ap);
		// table_ap = new TableView<hmmr_ap_model>();
		// table_ap.getColumns().addAll(columns_ap);
		
		Platform.runLater(new Runnable() {
	        @Override
	        public void run() {
	        	table_ap.getColumns().removeAll(col);
	    		table_ap = new TableView<hmmr_ap_model>();
	    		table_ap.getColumns().addAll(col);
	        	table_ap.setItems(qr._select_data_ap(USER_S));
	        	col.get(0).setVisible(false);
	   	     	col.get(0).setVisible(true);
	        }
	      });
		
		 //table_ap.setItems(qr._select_data_ap(mu_main_controller.USER_S));
		 //col.get(0).setVisible(false);
	     //col.get(0).setVisible(true);
	 }
	
	public void refreshTable_wr(ObservableList<TableColumn<hmmr_wr_model, ?>> col, String bdata, String ldata) {
		 table_wr.getColumns().removeAll(col);
		 table_wr.getColumns().addAll(col);
		 table_wr.setItems(qr._select_data_wr(bdata, ldata));
		 col.get(0).setVisible(false);
	     col.get(0).setVisible(true);
	 }
	
	public void refreshTable(final TableView<hmmr_ap_model> tableView, final List<TableColumn<hmmr_ap_model,?>> columns, final List<hmmr_ap_model> rows) {
		columns.clear();
	    columns.addAll(columns);

	    ObservableList<hmmr_ap_model> list = FXCollections.observableArrayList(rows);
	    tableView.setItems(list);
	 }
	
	//Заполняем переменные для использования в обновлении строки в WO
	private void _fill_rec(String str)
	{
		_sql_rez = qr._select_for_update_ap(str);
		_id_ap = str; 
		_pmnum_ap = scl.parser_str_str_str(_sql_rez, 0);
		_type_ap = scl.parser_str_str_str(_sql_rez, 1);
		_description_ap = scl.parser_str_str_str(_sql_rez, 2);
		_due_date_ap = scl.parser_str_str_str(_sql_rez, 3); 
		_equip_ap = scl.parser_str_str_str(_sql_rez, 4);
		_oft_ap = scl.parser_str_str_str(_sql_rez, 5);
		_otv_ap = scl.parser_str_str_str(_sql_rez, 6);
		_icon = scl.parser_str_str_str(_sql_rez, 7);
		_icon_at = scl.parser_str_str_str(_sql_rez, 8);
	}
	
	//Заполняем переменные для использования в обновлении строки в WP
		private void _fill_rec_wp(String str)
		{
			String _sql_rez_wp = qr._select_for_update_wp(str.substring(2));
			_id_wp = str.substring(2); 
			_pmnum_wp = scl.parser_str_str_str(_sql_rez_wp, 0);
			_type_wp = scl.parser_str_str_str(_sql_rez_wp, 1);
			_description_wp = scl.parser_str_str_str(_sql_rez_wp, 2);
			_due_date_wp = scl.parser_str_str_str(_sql_rez_wp, 3); 
			_equip_wp = scl.parser_str_str_str(_sql_rez_wp, 4);
			_oft_wp = scl.parser_str_str_str(_sql_rez_wp, 5);
			_otv_wp = scl.parser_str_str_str(_sql_rez_wp, 6);
			try {
				wp_upd();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	//Меняем цвет ячейки для даты в Action Plan
	//Путем вычитания дней(которые берем из таблицы colors) из текущей даты узнаем каким цветом надо закрасить соответсвующую ячейку
	@SuppressWarnings({ "unused", "unchecked" })
	private void tableCellAlignCenter(@SuppressWarnings("rawtypes") TableColumn col) {
		col.setCellFactory(column -> {
    	    return new TableCell<apwr_controller, String>() {
    	       	@Override
    	        protected void updateItem(String item, boolean empty) {
    	       		setStyle("");
    	            super.updateItem(item, empty);

    	            if (item == null || empty) {
    	                setText(null);
    	                setStyle("");
    	            } else {
    	                // формат строки
    	            	setText(item.toString());
    	            	_chk_color.addAll(qr._select_data_color());
    	            	LocalDate date_cur = LocalDate.now();
    	            	
    	            	for(int i = 0; i < _chk_color.size(); i++)
    	            	{
    	            		int test_data = Integer.parseInt(scl.parser_sql_str(_chk_color.get(i).toString(), 0));//Кол-во дней до изменения цвета ячейки
    	            		String color_data = scl.parser_sql_str(_chk_color.get(i).toString(), 1); //цвет в который будет установлена ячейка
    	            		
    	            		int _day = fx_dp.fromString(item).getDayOfMonth();
    	        			int _month = fx_dp.fromString(item).getMonthValue();
    	        			int _year = fx_dp.fromString(item).getYear();
    	        			
    	        			int _cur_day = date_cur.getDayOfMonth();
    	        			int _cur_month = date_cur.getMonthValue();
    	        			int _cur_year = date_cur.getYear();
    	        			
    	        			Date d = new Date(_cur_year);
    	        			
    	        			LocalDate days = LocalDate.of(_year, _month, _day).plusDays(test_data);//Расчитываем дату начиная с которой меняем цвет заявки в Action Plan
    	        			
    	        			int days_count_new = LocalDate.of(_year, _month, _day).getDayOfYear();
    	        			int days_count_cur = LocalDate.of(_cur_year, _cur_month, _cur_day).getDayOfYear();
    	        			
    	        			//Переводим даты в Стринг
    	        			String _chk_cur_date = fx_dp.toString(date_cur);
    	        			String _chk_new_date = fx_dp.toString(days);
    	            		
	    	                if(_chk_new_date.equals(_chk_cur_date))
	    	                	setStyle("-fx-background-color: "+color_data);
	    	                if(days_count_new < days_count_cur)
	    	                	setStyle("-fx-background-color: red");
	    	                if(_year > _cur_year)
	    	                	setStyle("-fx-background-color: "+color_data);
	    	                //if(qr._select_for_exec_task(i).equals("1"))
	    	                //	setStyle("-fx-background-color: green");

	    	            }
    	            	_chk_color.removeAll(_chk_color);
    	            }
    	           // table_ap.getColumns().forEach(col -> Platform.runLater(() -> {
	            //	    col.setVisible(false);
	            //	    col.setVisible(true);
	            //	}));
    	        }
    	    };
    	});
	}
	//Закрашиваем ячейки в зеленый цвет, если выбран фильтр - выполненные задачи
	@SuppressWarnings("unchecked")
	private void tableCellAlignCenter_green(@SuppressWarnings("rawtypes") TableColumn col) {
		col.setCellFactory(column -> {
    	    return new TableCell<apwr_controller, String>() {
    	       	@Override
    	        protected void updateItem(String item, boolean empty) {
    	       		setStyle("");
    	            super.updateItem(item, empty);

    	            if (item == null || empty) {
    	                setText(null);
    	                setStyle("");
    	            } else {
    	            	setText(item.toString());
    	                setStyle("-fx-background-color: green");
	    	         }
    	        }
    	    };
    	});
	}	
	
	//Запускаем поток для реального обновления данных в таблице
		@SuppressWarnings("rawtypes")
		public Task update_table()
		{
			Task task = new Task<Void>() {
				
				@Override public Void call() throws InterruptedException {
					while(_flag)
					{
						Platform.runLater(new Runnable() {
					        
							@Override
					        public void run() {
					        	table_ap.setItems(qr._select_data_ap(USER_S));
								table_ap.getColumns().get(0).setVisible(false);
						        table_ap.getColumns().get(0).setVisible(true);
					        }
					      });
				    	
				        TimeUnit.SECONDS.sleep(1);
					}
					return null;
				}
			};
		return task;
		}
		
		//Запускаем поток для реального обновления данных в таблице
				@SuppressWarnings("rawtypes")
				public Task update_table_wr()
				{
					Task task = new Task<Void>() {
						
						@Override public Void call() throws InterruptedException {
							while(_flag)
							{
								Platform.runLater(new Runnable() {
							        
									@Override
							        public void run() {
//							        	table_wr.setItems(qr._select_data_wr());
//										table_wr.getColumns().get(0).setVisible(false);
//								        table_wr.getColumns().get(0).setVisible(true);
							        }
							      });
						    	
						        TimeUnit.SECONDS.sleep(1);
							}
							return null;
						}
					};
				return task;
				}
			
				//Запускаем поток для проверки состояния чекбоксов и установки их
				
/*				public Task update_btn_wr()
				{
					Task task = new Task<Void>() {
						
						@Override public Void call() throws InterruptedException {
							while(_flag)
							{
								Platform.runLater(new Runnable() {
							        
									@Override
							        public void run() {
										
										
										for( final hmmr_wr_model hwr : table_wr.getItems()) {
								            if( hwr.qtyProperty().get()) {
								              qr._update_qty_wr("1",hwr.IdProperty().get().substring(2));
								              agree_wr.setDisable(true);
								            }
								            else
								            {
								            	qr._update_qty_wr("0",hwr.IdProperty().get().substring(2));
									            agree_wr.setDisable(true);
								            }
								         }
										//mu_main_controller.getPrimaryStage().getScene().cursorProperty().bind(Bindings.when(task.runningProperty())
								        //        .then(CURSOR_WAIT).otherwise(CURSOR_DEFAULT));
							        }
							      });
						    	
						        TimeUnit.SECONDS.sleep(1);
							}
							return null;
						}
					};
				return task;
				}*/
}
