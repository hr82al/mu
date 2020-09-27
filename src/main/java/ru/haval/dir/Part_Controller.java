package ru.haval.dir;

import java.io.IOException;
import java.util.Optional;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.haval.share_class.s_class;

public class Part_Controller {
	
	@FXML
	TableView<Hmmr_Part_Model> table_part = new TableView<>();
	
	@FXML
	TableColumn<Hmmr_Part_Model, String> col_id_part, col_part_type, col_part_type_eng;
	
	@FXML
	JFXButton btn_add_part, btn_upd_part, btn_del_part, btn_upd_tbl_part;
	
	@FXML
	Label lbl_title_part;
	
	public static ObservableList<Hmmr_Part_Model> _table_update_part = FXCollections.observableArrayList();
	public static boolean _flag_window = true;//true - создать запись, false - обновить запись
	public static String _id_part = "0";
	
	_query qr = new _query();
	s_class scl = new s_class();
	Stage stage = new Stage();
	
	@FXML
	public void initialize()
	{
		scl._style(btn_add_part);
		scl._style(btn_upd_part);
		scl._style(btn_del_part);
		scl._style(btn_upd_tbl_part);
		
		btn_upd_part.setDisable(true);
		btn_del_part.setDisable(true);
		
		initData();
		
		col_id_part.setCellValueFactory(CellData -> CellData.getValue().getId());
		col_part_type.setCellValueFactory(CellData -> CellData.getValue().getPart_Type());
		col_part_type_eng.setCellValueFactory(CellData -> CellData.getValue().getPart_Type_Eng());
		
		_table_update_part.addListener(new ListChangeListener<Hmmr_Part_Model>() {
			@Override
			public void onChanged(Change<? extends Hmmr_Part_Model> c) {
				table_part.setItems(qr._select_data_part());
		    	table_part.getColumns().get(0).setVisible(false);
		        table_part.getColumns().get(0).setVisible(true);
			}
			
		});
		table_part.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				btn_upd_part.setDisable(false);
				btn_del_part.setDisable(false);
				
				_id_part = table_part.getSelectionModel().getSelectedItem().getIdStr();
			}
		});
		btn_add_part.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
			try {	
				_flag_window = true;
				btn_upd_part.setDisable(true);
				btn_del_part.setDisable(true);
								
				part_add(stage);
			} catch (IOException e) {
				e.printStackTrace();
			}
			}
		});
		btn_upd_part.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				_flag_window = false;
				btn_upd_part.setDisable(true);
				btn_del_part.setDisable(true);
								
				try {
					part_add(stage);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		table_part.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() == 2 ){
					_flag_window = false;
					_id_part = table_part.getSelectionModel().getSelectedItem().getIdStr();
					try {
						part_add(stage);
					} catch (IOException e) {
						e.printStackTrace();
					}
		           }
			}
		});
		btn_del_part.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
			    alert.setTitle("M&U - Delete Record Window");
			    			    
			    alert.setHeaderText("Вы действительно хотите удалить запись № = " + table_part.getSelectionModel().getSelectedItem().getIdStr() + "?");
			    //alert.setContentText("C:/MyFile.txt");
			 
			    // option != null.
			    Optional<ButtonType> option = alert.showAndWait();
			    if (option.get() == null) {
			       //label.setText("No selection!");
			    } else if (option.get() == ButtonType.OK) {
			    	_id_part = table_part.getSelectionModel().getSelectedItem().getIdStr();
			  	   try {
			  	   func_del(_id_part);
			  	   btn_del_part.setDisable(true);
			  	   btn_upd_part.setDisable(true);
			  	   } catch (Exception e) {
					
				}
			    } else if (option.get() == ButtonType.CANCEL) {
			       //label.setText("Cancelled!");
			    } else {
			       //label.setText("-");
			    }
			}
			
		});
		btn_upd_tbl_part.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				table_part.setItems(qr._select_data_part());
				table_part.getColumns().get(0).setVisible(false);
			    table_part.getColumns().get(0).setVisible(true);
			}
		});
	}
	
	void initData()
	{
		table_part.setItems(qr._select_data_part());
	}
	
	protected void part_add(Stage stage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Add_Rec_Parttype.fxml"));
		   
	    Scene scene = new Scene(root);
	    Stage stage_set = new Stage();
	    stage_set.setTitle("M&U - Add Record Window");
	    stage_set.setResizable(false);
	    stage_set.initModality(Modality.WINDOW_MODAL);
	    stage_set.initOwner(mu_main_controller.getPrimaryStage());
	    stage_set.setScene(scene);
	    stage_set.show();
	}
	
	void func_del(String str)
	{
		qr._update_part_del(str);
		qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + " - Удалил запись № = " + str + " в таблице Part Type");
		_table_update_part.addAll(qr._select_data_part());
	}
}
