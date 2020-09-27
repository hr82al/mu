package ru.haval.dir;

import java.io.IOException;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import ru.haval.action.apwr_controller;
import ru.haval.application.conn_connector;
import ru.haval.db._query;
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
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ru.haval.share_class.s_class;

public class Order_Type_Controller {
	
	@FXML
	TableView<Hmmr_OrderType_Model> table_ot = new TableView<>();
	
	@FXML
	TableColumn<Hmmr_OrderType_Model, String> num_ot, name_ot, desc_ot;
	
	@FXML
	Label title_ot;
	
	@FXML
	JFXButton add_ot, upd_ot, del_ot, cancel_ot;
	
	_query qr = new _query();
	s_class scl = new s_class();
	Stage stage = new Stage();
	
	public static ObservableList<Hmmr_OrderType_Model> _table_update_ot = FXCollections.observableArrayList();
	Tooltip tip;
	
	public static String _id_ot, _name_ot, _desc_ot;
	
	@FXML
	public void initialize()
	{
		scl._style(add_ot);
		scl._style(upd_ot);
		scl._style(del_ot);
		scl._style(cancel_ot);
		
		upd_ot.setDisable(true);
		del_ot.setDisable(true);
		
		if(conn_connector.LANG_ID == 1)
			lang_fun("en", "EN");
		if(conn_connector.LANG_ID == 0)
			lang_fun("ru", "RU");
		if(conn_connector.LANG_ID == 2)
			lang_fun("zh", "CN");
		if(conn_connector.LANG_ID == -1)
			lang_fun("ru", "RU");
		
		num_ot.setCellValueFactory(CellData -> CellData.getValue().getId());
		name_ot.setCellValueFactory(CellData -> CellData.getValue().getName());
		desc_ot.setCellValueFactory(CellData -> CellData.getValue().getDesc());

		InitData();
		
		_table_update_ot.addListener(new ListChangeListener<Hmmr_OrderType_Model>() {
		    @Override
			public void onChanged(Change<? extends Hmmr_OrderType_Model> c) {
				table_ot.setItems(qr._select_data_ot());
		    	table_ot.getColumns().get(0).setVisible(false);
		        table_ot.getColumns().get(0).setVisible(true);
			}
		});
        add_ot.setOnAction(new EventHandler<ActionEvent>() {
			
			@SuppressWarnings("static-access")
			@Override
			public void handle(ActionEvent event) {
				try {
					upd_ot.setDisable(true);
					del_ot.setDisable(true);
					
					ot_add(stage);
				} catch (IOException e) {
					scl._AlertDialog(e.getMessage(), "Ошибка при открытии окна добавлении записи в ordertype_controller");
				}
				
			}
		});
        table_ot.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				upd_ot.setDisable(false);
				//устанавливаем права для кнопки удалить
				if(!conn_connector.USER_ROLE.equals("Engeneer"))
					del_ot.setDisable(false);
			}
		});
        upd_ot.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				upd_ot.setDisable(true);
				del_ot.setDisable(true);
				Hmmr_OrderType_Model _ccl1 = table_ot.getSelectionModel().getSelectedItem();
				try {
				func_upd(_ccl1.getIdStr());
				} catch (Exception e) {
					
				}
				
			}
		});
      //Вызываем окно обновления по двойному клику на строке
      table_ot.setOnMousePressed(new EventHandler<MouseEvent>() {
      	@Override
      	public void handle(MouseEvent event) {
      		if (event.getClickCount() == 2 ){
                     func_upd(table_ot.getSelectionModel().getSelectedItem().getIdStr());
                 }
      	}
      });
      cancel_ot.setOnAction(new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent event) {
			stage = (Stage) cancel_ot.getScene().getWindow();
			stage.close();
		}
	});
      del_ot.setOnAction(new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent event) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
		    alert.setTitle("M&U - Delete Record Window");
		    Hmmr_OrderType_Model _ccl = table_ot.getSelectionModel().getSelectedItem();
		    
		    alert.setHeaderText("Вы действительно хотите удалить запись № = " + _ccl.getId() + "?");
		    Optional<ButtonType> option = alert.showAndWait();
		    if (option.get() == null) {
		       
		    } else if (option.get() == ButtonType.OK) {
		  	   _ccl = table_ot.getSelectionModel().getSelectedItem();
		  	   try {
		  	   func_del(_ccl.getIdStr());
		  	   } catch (Exception e) {
				
			}
		    } else if (option.get() == ButtonType.CANCEL) {
		       //label.setText("Cancelled!");
		    } else {
		       //label.setText("-");
		    }
		}
	});
	}

	private void InitData()
	{
		table_ot.setItems(qr._select_data_ot());
	}
	
	private void func_upd(String str)
	{
		String _sql_rez = qr._select_for_update_ot(str);
		_id_ot = str;
		_name_ot = scl.parser_sql_str(_sql_rez, 0);
		_desc_ot = scl.parser_sql_str(_sql_rez, 1);
				
		try {
			ot_upd(stage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void func_del(String str)
	{
		qr._delete_rec_ot(str);
		qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + " - Удалил запись № = " + str + " в таблице Справочнике Order Type");
		_table_update_ot.addAll(qr._select_data_ot());
	}
	
	//Вызываем окно добавления записи к Спраавочнику приоритетов
	protected void ot_add(Stage stage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Add_Rec_OT.fxml"));
		   
	    Scene scene = new Scene(root);
	    stage.setTitle("M&U - Add Record Window");
	    stage.setResizable(false);
	    //stage.initModality(Modality.APPLICATION_MODAL);
	    stage.setScene(scene);
	    stage.show();
	}
	//Вызываем окно обновления записи
	protected void ot_upd(Stage stage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Upd_Rec_OT.fxml"));
		   
	    Scene scene = new Scene(root);
	    stage.setTitle("M&U - Update Record Window");
	    stage.setResizable(false);
	    //stage.initModality(Modality.APPLICATION_MODAL);
	    stage.setScene(scene);
	    stage.show();
	}
	
	private void lang_fun(String loc1, String loc2)
	{
		ResourceBundle lngBndl = ResourceBundle
	            .getBundle("bundles.LangBundle", new Locale(loc1, loc2));
		
		title_ot.setText(lngBndl.getString("lbl_ot"));
		name_ot.setText(lngBndl.getString("col_name_prior"));
		desc_ot.setText(lngBndl.getString("col_description"));
						
		add_ot.setText(lngBndl.getString("add_tsk"));
		upd_ot.setText(lngBndl.getString("upd_ap"));
		del_ot.setText(lngBndl.getString("del_inst"));
		cancel_ot.setText(lngBndl.getString("cancel_tsk"));
		
	}
}
