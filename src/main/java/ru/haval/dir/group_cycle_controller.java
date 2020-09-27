package ru.haval.dir;

import java.io.IOException;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import ru.haval.action.apwr_controller;
import  ru.haval.application.conn_connector;
import ru.haval.application.mu_main_controller;
import  ru.haval.db._query;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ru.haval.share_class.TooltippedTableCell;
import ru.haval.share_class.s_class;

public class group_cycle_controller {
	
	@FXML
	TableView<hmmr_groupcycle_model> table_gc;
	
	@FXML
	TableColumn<hmmr_groupcycle_model, String> col_id_gc, col_group_pm, col_cycle_pm, col_days_gc, col_start_date, col_duration;
	
	@FXML
	JFXButton add_rec, upd_rec, del_rec, cancel_form;
	
	@FXML
	Label title_label;
	
	s_class scl = new s_class();
	_query qr = new _query();
	private Stage stage = new Stage();
	
	public static ObservableList<hmmr_groupcycle_model> _table_update_gc = FXCollections.observableArrayList();
	public static int _id_gc;
	
	@FXML
	public void initialize()
	{
		scl._style(add_rec);
		scl._style(upd_rec);
		scl._style(del_rec);
		scl._style(cancel_form);
		
		if(conn_connector.LANG_ID == 1)
			lang_fun("en", "EN");
		if(conn_connector.LANG_ID == 0)
			lang_fun("ru", "RU");
		if(conn_connector.LANG_ID == 2)
			lang_fun("zh", "CN");
		if(conn_connector.LANG_ID == -1)
			lang_fun("ru", "RU");
		
		upd_rec.setDisable(true);
		del_rec.setDisable(true);

		InitData();
		
		col_id_gc.setCellValueFactory(CellData -> CellData.getValue().getId_());
		col_group_pm.setCellValueFactory(CellData -> CellData.getValue().getGroup_pm());
		col_cycle_pm.setCellValueFactory(CellData -> CellData.getValue().getCycle_pm());
		col_days_gc.setCellValueFactory(CellData -> CellData.getValue().getdays());
		col_start_date.setCellValueFactory(CellData -> CellData.getValue().getpm_startdate());
		col_duration.setCellValueFactory(CellData -> CellData.getValue().getpm_duration());
		
		TooltippedTableCell.flag_ui = 2;
		col_group_pm.setCellFactory(TooltippedTableCell.forTableColumn());
		
		add_rec.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				upd_rec.setDisable(true);
				del_rec.setDisable(true);
				try {
					gc_add(stage);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		upd_rec.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				try {
						func_upd();
					} catch (Exception e) {
						
					}
			}
		});
		cancel_form.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				TooltippedTableCell.flag_ui = 1;
				stage = (Stage) cancel_form.getScene().getWindow();
				stage.close();
			}
		});
		_table_update_gc.addListener(new ListChangeListener<hmmr_groupcycle_model>() {
		    @Override
			public void onChanged(Change<? extends hmmr_groupcycle_model> c) {
				table_gc.setItems(qr._select_for_gc());
		    	table_gc.getColumns().get(0).setVisible(false);
		        table_gc.getColumns().get(0).setVisible(true);
			}
		});
		table_gc.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				try {
					_id_gc = Integer.parseInt(table_gc.getSelectionModel().getSelectedItem().getId());
					upd_rec.setDisable(false);
					del_rec.setDisable(false);
				}
				catch (Exception e) {
					
				}
			}
		});
		//Вызываем окно обновления по двойному клику на строке
		table_gc.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() == 2 ){
		               func_upd();
		           }
			}
		});
		del_rec.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
			    alert.setTitle("M&U - Delete Record Window");
			    hmmr_groupcycle_model _ccl = table_gc.getSelectionModel().getSelectedItem();
			    
			    alert.setHeaderText("Вы действительно хотите удалить запись № = " + _ccl.getId() + "?\nВНИМАНИЕ!!! Группа № "+_ccl.getId()+" будет полностью удалена и из PM PLAN!!!");
			    
			    Optional<ButtonType> option = alert.showAndWait();
			    if (option.get() == null) {
			       
			    } else if (option.get() == ButtonType.OK) {
			  	   _ccl = table_gc.getSelectionModel().getSelectedItem();
			  	   try {
			  	   func_del(_ccl.getId());
			  	   del_rec.setDisable(true);
			  	   } catch (Exception e) {
					
				}
			    } else if (option.get() == ButtonType.CANCEL) {
			       return;
			    } else {
			       //label.setText("-");
			    }
			}
		});
		mu_main_controller.getPrimaryStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent arg0) {
				TooltippedTableCell.flag_ui = 1;
			}
		});
	}
	private void InitData()
	{
		table_gc.setItems(qr._select_for_gc());
	}
	protected void gc_add(Stage stage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("add_rec_groupcycle.fxml"));
		   
	    Scene scene = new Scene(root);
	    stage.setTitle("M&U - Add Record Window");
	    stage.setResizable(false);
	    stage.setScene(scene);
	    stage.show();
	}
	private void func_upd()
	{
		try {
			gc_upd(stage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void gc_upd(Stage stage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("upd_rec_groupcycle.fxml"));
				   
	    Scene scene = new Scene(root);
	    stage.setTitle("M&U - Update Record Window");
	    stage.setResizable(false);
	    stage.setScene(scene);
	    stage.show();
	}
	private void func_del(String str)
	{
		//при удалении группы из справочника эта группа будет также удалена и из PM PLAN
		qr._delete_for_pmplan(qr._select_rec("hmmr_group_cycle", "PM_Group", "del_rec", "id", str));
		
		qr._delete_for_gc(str);
		
		qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + " - Удалил запись № = " + str + " в справочнике Группа-Период");
		_table_update_gc.addAll(qr._select_for_gc());
	}
	
	private void lang_fun(String loc1, String loc2)
	{
		ResourceBundle lngBndl = ResourceBundle
	            .getBundle("bundles.LangBundle", new Locale(loc1, loc2));
		 
		title_label.setText(lngBndl.getString("lbl_title"));
		col_group_pm.setText(lngBndl.getString("lbl_group"));
		col_cycle_pm.setText(lngBndl.getString("lbl_pm_cycle"));
		col_days_gc.setText(lngBndl.getString("col_days_exp"));
		col_start_date.setText(lngBndl.getString("col_startdate_ps"));
		col_duration.setText(lngBndl.getString("lbl_duration"));
		
		add_rec.setText(lngBndl.getString("add_tsk"));
		upd_rec.setText(lngBndl.getString("upd_ap"));
		del_rec.setText(lngBndl.getString("del_inst"));
		cancel_form.setText(lngBndl.getString("cancel_tsk"));
	}
}
