package ru.haval.application;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import net.sf.jasperreports.engine.util.JRStyledText;
import  ru.haval.application.conn_connector;
import  ru.haval.db._query;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ru.haval.share_class.s_class;


public class mu_main_controller
{
	@FXML
	Label start_info;
	
	@FXML
	VBox root1;
	
	@FXML
	MenuItem pm_cyc, typepm, pm_inst, pm, psdb, about_program, apwr, pmplan, prior, gc, ot, staff, parttype, partchar, spdb, partchardir, parts, compspec;
	
	@FXML
	MenuBar menubar_id;
	
	@FXML
	HBox hbox_id;
	
	@FXML
	Menu _action, _dir, _rep, _set, _ext, _store, _action_store;
	
	@FXML
	BorderPane _bpane;
	
	public static Stage pStage;
	_query qr = new _query();
	s_class scl = new s_class();
	//public static String SHOP_NAME, USER_S;
	
	public mu_main_controller()
	{
		
	}
	
	@FXML
	public void initialize()
	{
		//Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		DropShadow dropShadow = new DropShadow();
		dropShadow.setRadius(7.0);
		dropShadow.setOffsetX(7.0);
		dropShadow.setOffsetY(7.0);
		dropShadow.setColor(Color.GREY);
		
		//Double screen_width = primaryScreenBounds.getWidth();
				
		//root1.setPrefWidth(screen_width);// - 77
		//menubar_id.setPrefWidth(screen_width); // - 77
		//hbox_id.setPrefWidth(screen_width); // - 77
		
		start_info.setTextFill(Color.web("#2A5058"));
		start_info.setFont(Font.font("Arial Black", FontWeight.BOLD, 23));
		//start_info.setPrefWidth(screen_width);// - 500
		start_info.setEffect(dropShadow);
		//start_info.setText("Вы вошли в систему как "+conn_connector.USER_ROLE+"! Ваш логин: "+conn_connector.USER_LOGIN);
		@SuppressWarnings("unused")
		String user1 = scl.parser_str(qr._select_user(conn_connector.USER_ID), 1);
		start_info.setText("User ID: "+scl.parser_str(qr._select_user(conn_connector.USER_ID), 1)+"/"+scl.parser_str(qr._select_user(conn_connector.USER_ID), 2)+" "+scl.parser_str(qr._select_user(conn_connector.USER_ID), 3) +"  MU."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 4)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 5)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 6)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 0));
		//SHOP_NAME = scl.parser_str(qr._select_user(conn_connector.USER_ID), 5);
		//USER_S = scl.parser_str(qr._select_user(conn_connector.USER_ID), 1);//сокращенное имя пользователя из таблицы STAFF
		
		//устанавливаем права доступа к интерфейсу
		if(conn_connector.USER_ROLE.equals("Technics"))
		{
			pm_cyc.setDisable(true);
			typepm.setDisable(true);
			pm_inst.setDisable(true);
			pm.setDisable(true);
			psdb.setDisable(true);
			pmplan.setDisable(true);
			prior.setDisable(true);
			gc.setDisable(true);
			ot.setDisable(true);
			staff.setDisable(true);
			_store.setDisable(true);
			_action_store.setDisable(true);
		}
		
		if(conn_connector.USER_ROLE.equals("Engeneer"))
		{
			//pm_cyc.setDisable(true);
			//typepm.setDisable(true);
			//_store.setDisable(true);
		}
		
		if(conn_connector.LANG_ID == 1)
		{
			ResourceBundle lngBndl = ResourceBundle
		            .getBundle("bundles.LangBundle", new Locale("en", "EN"));
			
			_action.setText(lngBndl.getString("_action"));
			_dir.setText(lngBndl.getString("_dir"));
			_rep.setText(lngBndl.getString("_rep"));
			_set.setText(lngBndl.getString("_set"));
			_ext.setText(lngBndl.getString("_ext"));
			_store.setText(lngBndl.getString("_store"));
			_action_store.setText(lngBndl.getString("_store"));
			pm_cyc.setText(lngBndl.getString("pm_cyc"));
			typepm.setText(lngBndl.getString("typepm_key"));
			pm_inst.setText(lngBndl.getString("pm_inst"));
			pm.setText(lngBndl.getString("pm_key"));
			psdb.setText(lngBndl.getString("psdb_key"));
			pmplan.setText(lngBndl.getString("lbl_upd_pmplan"));
			about_program.setText(lngBndl.getString("about_program"));
			apwr.setText(lngBndl.getString("apwr_key"));
			prior.setText(lngBndl.getString("prior"));
			gc.setText(lngBndl.getString("lbl_gc"));
			ot.setText(lngBndl.getString("lbl_ot"));
			staff.setText(lngBndl.getString("lbl_staff"));
			parttype.setText(lngBndl.getString("part_type"));
			partchar.setText(lngBndl.getString("part_char"));
			spdb.setText(lngBndl.getString("sp_db"));
			parts.setText(lngBndl.getString("lbl_parts"));
			compspec.setText(lngBndl.getString("lbl_compspec"));
		}
		
		if(conn_connector.LANG_ID == 0)
		{
			ResourceBundle lngBndl = ResourceBundle
		            .getBundle("bundles.LangBundle", new Locale("ru", "RU"));
	
			_action.setText(lngBndl.getString("_action"));
			_dir.setText(lngBndl.getString("_dir"));
			_rep.setText(lngBndl.getString("_rep"));
			_set.setText(lngBndl.getString("_set"));
			_ext.setText(lngBndl.getString("_ext"));
			_store.setText(lngBndl.getString("_store"));
			_action_store.setText(lngBndl.getString("_store"));
			pm_cyc.setText(lngBndl.getString("pm_cyc"));
			typepm.setText(lngBndl.getString("typepm_key"));
			pm_inst.setText(lngBndl.getString("pm_inst"));
			pm.setText(lngBndl.getString("pm_key"));
			psdb.setText(lngBndl.getString("psdb_key"));
			pmplan.setText(lngBndl.getString("lbl_upd_pmplan"));
			about_program.setText(lngBndl.getString("about_program"));
			apwr.setText(lngBndl.getString("apwr_key"));
			prior.setText(lngBndl.getString("prior"));
			gc.setText(lngBndl.getString("lbl_gc"));
			ot.setText(lngBndl.getString("lbl_ot"));
			staff.setText(lngBndl.getString("lbl_staff"));
			parttype.setText(lngBndl.getString("part_type"));
			partchar.setText(lngBndl.getString("part_char"));
			spdb.setText(lngBndl.getString("sp_db"));
			parts.setText(lngBndl.getString("lbl_parts"));
			compspec.setText(lngBndl.getString("lbl_compspec"));
		}
		if(conn_connector.LANG_ID == 2)
		{
			ResourceBundle lngBndl = ResourceBundle
		            .getBundle("bundles.LangBundle", new Locale("zh", "CN"));
	
			_action.setText(lngBndl.getString("_action"));
			_dir.setText(lngBndl.getString("_dir"));
			_rep.setText(lngBndl.getString("_rep"));
			_set.setText(lngBndl.getString("_set"));
			_ext.setText(lngBndl.getString("_ext"));
			_store.setText(lngBndl.getString("_store"));
			_action_store.setText(lngBndl.getString("_store"));
			pm_cyc.setText(lngBndl.getString("pm_cyc"));
			typepm.setText(lngBndl.getString("typepm_key"));
			pm_inst.setText(lngBndl.getString("pm_inst"));
			pm.setText(lngBndl.getString("pm_key"));
			psdb.setText(lngBndl.getString("psdb_key"));
			pmplan.setText(lngBndl.getString("lbl_upd_pmplan"));
			about_program.setText(lngBndl.getString("about_program"));
			apwr.setText(lngBndl.getString("apwr_key"));
			prior.setText(lngBndl.getString("prior"));
			gc.setText(lngBndl.getString("lbl_gc"));
			ot.setText(lngBndl.getString("lbl_ot"));
			staff.setText(lngBndl.getString("lbl_staff"));
			parttype.setText(lngBndl.getString("part_type"));
			partchar.setText(lngBndl.getString("part_char"));
			spdb.setText(lngBndl.getString("sp_db"));
			parts.setText(lngBndl.getString("lbl_parts"));
			compspec.setText(lngBndl.getString("lbl_compspec"));
		}
		if(conn_connector.LANG_ID == -1)
		{
			ResourceBundle lngBndl = ResourceBundle
		            .getBundle("bundles.LangBundle", new Locale("ru", "RU"));
	
			_action.setText(lngBndl.getString("_action"));
			_dir.setText(lngBndl.getString("_dir"));
			_rep.setText(lngBndl.getString("_rep"));
			_set.setText(lngBndl.getString("_set"));
			_ext.setText(lngBndl.getString("_ext"));
			_store.setText(lngBndl.getString("_store"));
			_action_store.setText(lngBndl.getString("_store"));
			pm_cyc.setText(lngBndl.getString("pm_cyc"));
			typepm.setText(lngBndl.getString("typepm_key"));
			pm_inst.setText(lngBndl.getString("pm_inst"));
			pm.setText(lngBndl.getString("pm_key"));
			psdb.setText(lngBndl.getString("psdb_key"));
			pmplan.setText(lngBndl.getString("lbl_upd_pmplan"));
			about_program.setText(lngBndl.getString("about_program"));
			apwr.setText(lngBndl.getString("apwr_key"));
			prior.setText(lngBndl.getString("prior"));
			gc.setText(lngBndl.getString("lbl_gc"));
			ot.setText(lngBndl.getString("lbl_ot"));
			staff.setText(lngBndl.getString("lbl_staff"));
			parttype.setText(lngBndl.getString("part_type"));
			partchar.setText(lngBndl.getString("part_char"));
			spdb.setText(lngBndl.getString("sp_db"));
			parts.setText(lngBndl.getString("lbl_parts"));
			compspec.setText(lngBndl.getString("lbl_compspec"));
		}
		
		//Автоматически вызываем окно Action Plan & Work Recording
		/*Stage stage = new Stage();
		try {
			apwr_start(stage);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		//Эти все листенеры нужны чтобы снять у окна ActionPlan&Work Recording признак поверх всех окон
		//и если кликнуть мышкой на меню или самом главном окне признак снимется
		/*menubar_id.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				getPrimaryStage().setAlwaysOnTop(false);
				getPrimaryStage().toBack();
			}
		});
		_action.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				getPrimaryStage().setAlwaysOnTop(false);
				getPrimaryStage().toBack();
			}
		});
		_dir.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				getPrimaryStage().setAlwaysOnTop(false);
				getPrimaryStage().toBack();
			}
		});
		_rep.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				getPrimaryStage().setAlwaysOnTop(false);
				getPrimaryStage().toBack();
			}
		});
		_set.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				getPrimaryStage().setAlwaysOnTop(false);
				getPrimaryStage().toBack();
			}
		});
		_ext.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				getPrimaryStage().setAlwaysOnTop(false);
				getPrimaryStage().toBack();
			}
		});
		_bpane.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				getPrimaryStage().setAlwaysOnTop(false);
				getPrimaryStage().toBack();
			}
		});*/
	}
	
	//Вызываем из меню справочник PM_Cycle
	@FXML
	public void getPm_cyc() {
		Stage stage = new Stage();
        try {
			pmcycle_start(stage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void pmcycle_start(Stage stage) throws IOException {
		setPrimaryStage(stage);
	    Parent root = FXMLLoader.load(getClass().getResource("pm_cycle.fxml"));
	    
        Scene scene = new Scene(root);
        stage.setTitle("M&U - PM_Cycle Window"+" "+scl.parser_str(qr._select_user(conn_connector.USER_ID), 1)+"/"+scl.parser_str(qr._select_user(conn_connector.USER_ID), 2)+" "+scl.parser_str(qr._select_user(conn_connector.USER_ID), 3) +"  MU."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 4)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 5)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 6)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 0));
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        
        stage.show();
    }
	
	//Вызываем из меню справочник Type_PMe
		@FXML
		public void getType_pm() {
			Stage stage = new Stage();
	        try {
				pmtype_start(stage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	//Вызываем из меню PM_instruction
		@FXML
		public void getPM_inst()
		{
			Stage stage = new Stage();
			try {
				pminst_start(stage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//Вызываем из меню PM_DB
		@FXML
		public void getPM_pmdb()
		{
			Stage stage = new Stage();
			try {
				pm_start(stage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//Вызываем из меню AP & WR
		@FXML
		public void getPM_apwr()
		{
			Stage stage = new Stage();
			try {
				apwr_start(stage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//Вызываем из меню Plant Structure
		@FXML
		public void get_PS()
		{
			Stage stage = new Stage();
			try {
				ps_start(stage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//Вызываем из меню PM Plan
		@FXML
		public void get_PM_Plan()
		{
			Stage stage = new Stage();
			try {
				pmplan_start(stage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//Вызываем из меню справочник приоритетов
		@FXML
		public void getPrior() {
			Stage stage = new Stage();
	        try {
				prior_start(stage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//Вызываем из меню справочник Группа-Период
		@FXML
		public void getGC() {
			Stage stage = new Stage();
		       try {
				gc_start(stage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//Вызываем из меню справочник Order Type
		@FXML
		public void getOT() {
			Stage stage = new Stage();
		       try {
				ot_start(stage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//Вызываем из меню Редактор персонала
		@FXML
		public void getStaff() {
			Stage stage = new Stage();
		       try {
				staff_start(stage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//Вызываем из меню Редактор справочника склада - Part Type
		@FXML
		public void getPart() {
			Stage stage = new Stage();
		       try {
				part_start(stage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//Вызываем из меню Редактор справочника склада - Part Char
		@FXML
		public void getPartChar() {
			Stage stage = new Stage();
		       try {
				partchar_start(stage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//Вызываем из меню Редактор SP DB
		@FXML
		public void getSP() {
			Stage stage = new Stage();
		       try {
				sp_start(stage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//Вызываем из меню Редактор справочника склада - Part Char Dir
		@FXML
		public void getPartCharDir() {
			Stage stage = new Stage();
		       try {
				partchardir_start(stage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//Вызываем из меню Редактор склада - Parts Specification
		@FXML
		public void getParts() {
			Stage stage = new Stage();
		       try {
				parts_start(stage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//Вызываем из меню Редактор склада - Components Specification
		@FXML
		public void getCompSpec() {
			Stage stage = new Stage();
		       try {
				compspec_start(stage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		protected void pmtype_start(Stage stage) throws IOException {
			setPrimaryStage(stage);
		    Parent root = FXMLLoader.load(getClass().getResource("type_pm.fxml"));
		    
	        Scene scene = new Scene(root);
	        stage.setTitle("M&U - Type_PM Window"+" "+scl.parser_str(qr._select_user(conn_connector.USER_ID), 1)+"/"+scl.parser_str(qr._select_user(conn_connector.USER_ID), 2)+" "+scl.parser_str(qr._select_user(conn_connector.USER_ID), 3) +"  MU."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 4)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 5)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 6)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 0));
	        stage.setResizable(false);
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.setScene(scene);
	        
	        stage.show();
	    }
		
		protected void pminst_start(Stage stage) throws IOException {
			setPrimaryStage(stage);
			Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
			
			Parent root = FXMLLoader.load(getClass().getResource("pm_inst.fxml"));
		    
	        Scene scene = new Scene(root);
	        stage.setTitle("M&U - PM Instruction Window"+" "+scl.parser_str(qr._select_user(conn_connector.USER_ID), 1)+"/"+scl.parser_str(qr._select_user(conn_connector.USER_ID), 2)+" "+scl.parser_str(qr._select_user(conn_connector.USER_ID), 3) +"  MU."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 4)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 5)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 6)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 0));
	        stage.setWidth(primaryScreenBounds.getWidth());
	        //stage.setResizable(false);
	        //stage.initModality(Modality.APPLICATION_MODAL);
	        stage.setScene(scene);
	        
	        stage.show();
		}
		
		protected void pm_start(Stage stage) throws IOException {
			setPrimaryStage(stage);
			//Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
			
			Parent root = FXMLLoader.load(getClass().getResource("pmdb.fxml"));
		    
	        Scene scene = new Scene(root);
	        stage.setTitle("M&U - PM Window"+" "+scl.parser_str(qr._select_user(conn_connector.USER_ID), 1)+"/"+scl.parser_str(qr._select_user(conn_connector.USER_ID), 2)+" "+scl.parser_str(qr._select_user(conn_connector.USER_ID), 3) +"  MU."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 4)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 5)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 6)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 0));
	       // stage.setWidth(primaryScreenBounds.getWidth() - 230);
	       // stage.setHeight(primaryScreenBounds.getHeight() - 15);
	        //stage.setResizable(false);
	        //stage.initModality(Modality.APPLICATION_MODAL);
	        stage.setScene(scene);
	        
	        stage.show();
		}
		
		protected void apwr_start(Stage stage) throws IOException {
			setPrimaryStage(stage);
			Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
			
			Parent root = FXMLLoader.load(getClass().getResource("ap_wr.fxml"));
		    
	        Scene scene = new Scene(root);
	        stage.setTitle("M&U - Action Plan&Work Recording Window"+" "+scl.parser_str(qr._select_user(conn_connector.USER_ID), 1)+"/"+scl.parser_str(qr._select_user(conn_connector.USER_ID), 2)+" "+scl.parser_str(qr._select_user(conn_connector.USER_ID), 3) +"  MU."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 4)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 5)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 6)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 0));
	        stage.setWidth(primaryScreenBounds.getWidth() - 315);
	        stage.setHeight(primaryScreenBounds.getHeight() - 15);
	        //stage.setResizable(false);
	        //stage.initModality(Modality.APPLICATION_MODAL);
	        stage.setScene(scene);
	        //stage.setAlwaysOnTop(true);
	        
	        stage.show();
	        
		}
		
		protected void ps_start(Stage stage) throws IOException {
			setPrimaryStage(stage);
			//Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
			
			Parent root = FXMLLoader.load(getClass().getResource("psdb.fxml"));
		    
	        Scene scene = new Scene(root);
	        stage.setTitle("M&U - Plant Structure Window"+" "+scl.parser_str(qr._select_user(conn_connector.USER_ID), 1)+"/"+scl.parser_str(qr._select_user(conn_connector.USER_ID), 2)+" "+scl.parser_str(qr._select_user(conn_connector.USER_ID), 3) +"  MU."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 4)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 5)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 6)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 0));
	        //stage.setWidth(primaryScreenBounds.getWidth() - 531);
	        //stage.setHeight(primaryScreenBounds.getHeight() - 25);
	        stage.setResizable(true);
	        //stage.initModality(Modality.APPLICATION_MODAL);
	        stage.setScene(scene);
	        //stage.setAlwaysOnTop(true);
	        
	        stage.show();
	        
		}
		
		protected void pmplan_start(Stage stage) throws IOException {
			setPrimaryStage(stage);
			//Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
			
			Parent root = FXMLLoader.load(getClass().getResource("pm_plan.fxml"));
		    
	        Scene scene = new Scene(root);
	        stage.setTitle("M&U - PM Plan Window"+" "+scl.parser_str(qr._select_user(conn_connector.USER_ID), 1)+"/"+scl.parser_str(qr._select_user(conn_connector.USER_ID), 2)+" "+scl.parser_str(qr._select_user(conn_connector.USER_ID), 3) +"  MU."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 4)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 5)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 6)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 0));
	        //stage.setWidth(primaryScreenBounds.getWidth() - 30);
	        //stage.setHeight(primaryScreenBounds.getHeight() - 15);
	        stage.setResizable(false);
	        //stage.initModality(Modality.APPLICATION_MODAL);
	        stage.setScene(scene);
	        //stage.setAlwaysOnTop(true);
	        
	        stage.show();
	        
		}
		
		protected void prior_start(Stage stage) throws IOException {
			setPrimaryStage(stage);
			Parent root = FXMLLoader.load(getClass().getResource("prior.fxml"));
		    
	        Scene scene = new Scene(root);
	        stage.setTitle("M&U - Prior Window"+" "+scl.parser_str(qr._select_user(conn_connector.USER_ID), 1)+"/"+scl.parser_str(qr._select_user(conn_connector.USER_ID), 2)+" "+scl.parser_str(qr._select_user(conn_connector.USER_ID), 3) +"  MU."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 4)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 5)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 6)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 0));
	        stage.setResizable(false);
	        //stage.initModality(Modality.APPLICATION_MODAL);
	        stage.setScene(scene);
	        	        
	        stage.show();
	        
		}
		
		protected void gc_start(Stage stage) throws IOException {
			setPrimaryStage(stage);
			Parent root = FXMLLoader.load(getClass().getResource("group_cycle.fxml"));
		    
	        Scene scene = new Scene(root);
	        stage.setTitle("M&U - Group-Cycle Window"+" "+scl.parser_str(qr._select_user(conn_connector.USER_ID), 1)+"/"+scl.parser_str(qr._select_user(conn_connector.USER_ID), 2)+" "+scl.parser_str(qr._select_user(conn_connector.USER_ID), 3) +"  MU."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 4)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 5)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 6)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 0));
	        stage.setResizable(false);
	        stage.setScene(scene);
	        	        
	        stage.show();
	        
		}
		
		protected void ot_start(Stage stage) throws IOException {
			setPrimaryStage(stage);
			Parent root = FXMLLoader.load(getClass().getResource("ordertype.fxml"));
		    
	        Scene scene = new Scene(root);
	        stage.setTitle("M&U - Order Type Window"+" "+scl.parser_str(qr._select_user(conn_connector.USER_ID), 1)+"/"+scl.parser_str(qr._select_user(conn_connector.USER_ID), 2)+" "+scl.parser_str(qr._select_user(conn_connector.USER_ID), 3) +"  MU."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 4)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 5)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 6)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 0));
	        stage.setResizable(false);
	        stage.setScene(scene);
	        	        
	        stage.show();
	        
		}
		
		protected void staff_start(Stage stage) throws IOException {
			setPrimaryStage(stage);
			Parent root = FXMLLoader.load(getClass().getResource("Staff.fxml"));
		    
	        Scene scene = new Scene(root);
	        stage.setTitle("M&U - Staff Window"+" "+scl.parser_str(qr._select_user(conn_connector.USER_ID), 1)+"/"+scl.parser_str(qr._select_user(conn_connector.USER_ID), 2)+" "+scl.parser_str(qr._select_user(conn_connector.USER_ID), 3) +"  MU."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 4)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 5)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 6)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 0));
	        stage.setResizable(false);
	        stage.setScene(scene);
	        	        
	        stage.show();
	        
		}
		
		protected void part_start(Stage stage) throws IOException {
			setPrimaryStage(stage);
			Parent root = FXMLLoader.load(getClass().getResource("Part.fxml"));
		    
	        Scene scene = new Scene(root);
	        stage.setTitle("M&U - Part Type Window"+" "+scl.parser_str(qr._select_user(conn_connector.USER_ID), 1)+"/"+scl.parser_str(qr._select_user(conn_connector.USER_ID), 2)+" "+scl.parser_str(qr._select_user(conn_connector.USER_ID), 3) +"  MU."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 4)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 5)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 6)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 0));
	        stage.setResizable(false);
	        stage.setScene(scene);
	        	        
	        stage.show();
	        
		}
		
		protected void partchar_start(Stage stage) throws IOException {
			setPrimaryStage(stage);
			Parent root = FXMLLoader.load(getClass().getResource("PartChar.fxml"));
		    
	        Scene scene = new Scene(root);
	        stage.setTitle("M&U - Part Char Window"+" "+scl.parser_str(qr._select_user(conn_connector.USER_ID), 1)+"/"+scl.parser_str(qr._select_user(conn_connector.USER_ID), 2)+" "+scl.parser_str(qr._select_user(conn_connector.USER_ID), 3) +"  MU."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 4)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 5)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 6)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 0));
	        stage.setResizable(false);
	        stage.setScene(scene);
	        	        
	        stage.show();
	        
		}
		
		protected void sp_start(Stage stage) throws IOException {
			setPrimaryStage(stage);
			Parent root = FXMLLoader.load(getClass().getResource("SpDb.fxml"));
		    
	        Scene scene = new Scene(root);
	        stage.setTitle("M&U - SP DB Window"+" "+scl.parser_str(qr._select_user(conn_connector.USER_ID), 1)+"/"+scl.parser_str(qr._select_user(conn_connector.USER_ID), 2)+" "+scl.parser_str(qr._select_user(conn_connector.USER_ID), 3) +"  MU."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 4)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 5)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 6)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 0));
	        //stage.setResizable(false);
	        stage.setScene(scene);
	        	        
	        stage.show();
	        
		}
		
		protected void partchardir_start(Stage stage) throws IOException {
			setPrimaryStage(stage);
			Parent root = FXMLLoader.load(getClass().getResource("PartChar_Dir.fxml"));
		    
	        Scene scene = new Scene(root);
	        stage.setTitle("M&U - Part Char Dir Window"+" "+scl.parser_str(qr._select_user(conn_connector.USER_ID), 1)+"/"+scl.parser_str(qr._select_user(conn_connector.USER_ID), 2)+" "+scl.parser_str(qr._select_user(conn_connector.USER_ID), 3) +"  MU."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 4)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 5)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 6)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 0));
	        stage.setResizable(false);
	        stage.setScene(scene);
	        	        
	        stage.show();
	        
		}
		protected void parts_start(Stage stage) throws IOException {
			setPrimaryStage(stage);
			Parent root = FXMLLoader.load(getClass().getResource("PartSpec.fxml"));
		    
	        Scene scene = new Scene(root);
	        stage.setTitle("M&U - Parts Specification Window"+" "+scl.parser_str(qr._select_user(conn_connector.USER_ID), 1)+"/"+scl.parser_str(qr._select_user(conn_connector.USER_ID), 2)+" "+scl.parser_str(qr._select_user(conn_connector.USER_ID), 3) +"  MU."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 4)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 5)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 6)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 0));
	        stage.setResizable(false);
	        stage.setScene(scene);
	        	        
	        stage.show();
	    }
		protected void compspec_start(Stage stage) throws IOException {
			setPrimaryStage(stage);
			Parent root = FXMLLoader.load(getClass().getResource("CompSpec.fxml"));
		    
	        Scene scene = new Scene(root);
	        stage.setTitle("M&U - Parts Specification Window"+" "+scl.parser_str(qr._select_user(conn_connector.USER_ID), 1)+"/"+scl.parser_str(qr._select_user(conn_connector.USER_ID), 2)+" "+scl.parser_str(qr._select_user(conn_connector.USER_ID), 3) +"  MU."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 4)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 5)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 6)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 0));
	        stage.setResizable(false);
	        stage.setScene(scene);
	        	        
	        stage.show();
	    }
		
		private void setPrimaryStage(Stage pStage) {
	        mu_main_controller.pStage = pStage;
	    }
		public static Stage getPrimaryStage() {
	        return pStage;
	    }

	public void openWorkPlan(ActionEvent actionEvent) {
		String jarLocationUrl = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		String jarLocation = new File(jarLocationUrl.toString()).getParent();
		try {
			Runtime.getRuntime().exec("javaw.exe -jar \"" + jarLocation + "\\" + "work_plan.jar\"");
			System.out.println("javaw.exe -jar \"" + jarLocation + "\\" + "work_plan.jar\"");
		} catch (IOException e) {
			s_class._AlertDialog(e.getMessage());
		}
	}
}