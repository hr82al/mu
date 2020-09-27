package ru.haval.action;

import java.io.IOException;
import java.util.Optional;

import com.jfoenix.controls.JFXButton;

import ru.haval.application.conn_connector;
import ru.haval.application.mu_main_controller;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.haval.share_class.s_class;

public class PartSpec_Controller {
	
	@FXML
	TableView<Hmmr_PartS_Model> table_parts = new TableView<>();
	
	@FXML
	TableColumn<Hmmr_PartS_Model, String> col_id_ps, col_HMMR_Material_Num, col_Equipment, col_Drawing, col_Position_On_Drawing, col_Key_No_Backup_Yes, col_Key_No_Backup_No,
	col_Key_Yes_Backup_Yes, col_Key_Yes_Backup_No;
	
	@FXML
	JFXButton btn_add_rec, btn_upd_rec, btn_del_rec, btn_upd_tbl, btn_cancel, dub_ps;
	
	@FXML
	Label lbl_title_ps;
	
	_query qr = new _query();
	s_class scl = new s_class();
	
	public static ObservableList<Hmmr_PartS_Model> _table_update_parts = FXCollections.observableArrayList();
	public static boolean _flag_window_parts = true;
	public static String _id_parts = "0";
	Stage stage = new Stage();
	
	public void initialize()
	{
		scl._style(btn_add_rec);
		scl._style(btn_upd_rec);
		scl._style(btn_del_rec);
		scl._style(btn_upd_tbl);
		scl._style(btn_cancel);
		scl._style(dub_ps);
		btn_upd_rec.setDisable(true);
		btn_del_rec.setDisable(true);
		dub_ps.setDisable(true);
		
		initData();
		
		col_id_ps.setCellValueFactory(CellData -> CellData.getValue().getId());
		col_HMMR_Material_Num.setCellValueFactory(CellData -> CellData.getValue().getHMMR_Material_Num());
		col_Equipment.setCellValueFactory(CellData -> CellData.getValue().getEquipment());
		col_Drawing.setCellValueFactory(CellData -> CellData.getValue().getDrawing());
		col_Position_On_Drawing.setCellValueFactory(CellData -> CellData.getValue().getPosition_On_Drawing());
		col_Key_No_Backup_Yes.setCellValueFactory(CellData -> CellData.getValue().getKey_No_Backup_Yes());
		col_Key_No_Backup_No.setCellValueFactory(CellData -> CellData.getValue().getKey_No_Backup_No());
		col_Key_Yes_Backup_Yes.setCellValueFactory(CellData -> CellData.getValue().getKey_Yes_Backup_Yes());
		col_Key_Yes_Backup_No.setCellValueFactory(CellData -> CellData.getValue().getKey_Yes_Backup_No());
		
		
		//Ставим фокус и опускаемся на последнюю строку таблицы     
        table_parts.requestFocus();
        table_parts.getFocusModel().focus(0);
        table_parts.getSelectionModel().selectLast();
        table_parts.scrollTo(table_parts.getItems().size());
        
       /* col_Equipment.setCellValueFactory(
           new Callback<TableColumn.CellDataFeatures<Hmmr_PartS_Model, String>, ObservableValue<String>>() {
               @Override
               public ObservableValue<String> call(TableColumn.CellDataFeatures<Hmmr_PartS_Model, String> arg0) {
                   Hmmr_PartS_Model data = arg0.getValue();
                   return new SimpleObjectProperty<String>(qr._select_fillpm_equip(data.getEquip_id(), "hmmr_parts_spec"));
               }
          });
        col_HMMR_Material_Num.setCellValueFactory(
           new Callback<TableColumn.CellDataFeatures<Hmmr_PartS_Model, String>, ObservableValue<String>>() {
               @Override
               public ObservableValue<String> call(TableColumn.CellDataFeatures<Hmmr_PartS_Model, String> arg0) {
                   Hmmr_PartS_Model data = arg0.getValue();
                   return new SimpleObjectProperty<String>(qr._select_fillcs(data.getHMMR_Material_Id()));
               }
          }); */
        
        btn_add_rec.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				btn_upd_rec.setDisable(true);
				btn_del_rec.setDisable(true);
				dub_ps.setDisable(true);
				
				_flag_window_parts = true;
			try {					
				parts_add(stage);
			} catch (IOException e) {
				e.printStackTrace();
			}
			}
		});
        btn_upd_rec.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				btn_upd_rec.setDisable(true);
				btn_del_rec.setDisable(true);
				dub_ps.setDisable(true);
				
				_flag_window_parts = false;
			try {					
				parts_add(stage);
			} catch (IOException e) {
				e.printStackTrace();
			}
			}
		});
        table_parts.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				btn_upd_rec.setDisable(false);
				btn_del_rec.setDisable(false);
				dub_ps.setDisable(false);
				try {
					_id_parts = table_parts.getSelectionModel().getSelectedItem().getIdStr();
				}
				catch (Exception e) {
					
				}
			}
		});
    table_parts.setOnMousePressed(new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			if (event.getClickCount() == 2 ){
				_flag_window_parts = false;
				_id_parts = table_parts.getSelectionModel().getSelectedItem().getIdStr();
				try {
					parts_add(stage);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	});
    _table_update_parts.addListener(new ListChangeListener<Hmmr_PartS_Model> () {

			@Override
			public void onChanged(Change<? extends Hmmr_PartS_Model> arg0) {
				table_parts.setItems(qr._select_data_parts());
				table_parts.getColumns().get(0).setVisible(false);
		        table_parts.getColumns().get(0).setVisible(true);
		        btn_upd_rec.setDisable(true);
				btn_del_rec.setDisable(true);
				dub_ps.setDisable(true);
			}
			
    });
    btn_upd_tbl.setOnAction(new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent arg0) {
			table_parts.setItems(qr._select_data_parts());
			table_parts.getColumns().get(0).setVisible(false);
	        table_parts.getColumns().get(0).setVisible(true);
	        btn_upd_rec.setDisable(true);
			btn_del_rec.setDisable(true);
			dub_ps.setDisable(true);
		}
	});
    btn_del_rec.setOnAction(new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent arg0) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
		    alert.setTitle("M&U - Delete Record Window");
		    Hmmr_PartS_Model _ccl = table_parts.getSelectionModel().getSelectedItem();
		    
		    alert.setHeaderText("Вы действительно хотите удалить запись № = " + _ccl.getIdStr() + "?");
		    //alert.setContentText("C:/MyFile.txt");
		 
		    // option != null.
		    Optional<ButtonType> option = alert.showAndWait();
		    if (option.get() == null) {
		       //label.setText("No selection!");
		    } else if (option.get() == ButtonType.OK) {
		  	   _ccl = table_parts.getSelectionModel().getSelectedItem();
		  	   try {
		  	   qr._update_deleterec_parts(_ccl.getIdStr());
		  	   qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + " - Удалил запись № = " + _ccl.getIdStr() + " в таблице Parts Specification");
		  	   _table_update_parts.addAll(qr._select_data_parts());
		  	   } catch (Exception e) {
				
			}
		    } else if (option.get() == ButtonType.CANCEL) {
		       //label.setText("Cancelled!");
		    } else {
		       //label.setText("-");
		    }
		}
	});
    btn_cancel.setOnAction(new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent arg0) {
			stage = (Stage) btn_cancel.getScene().getWindow();
			stage.close();
		}
	});
    dub_ps.setOnAction(new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent arg0) {
			try {
				ps_dup();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	});
		
	}

	void initData()
	{
		table_parts.setItems(qr._select_data_parts());
	}
	protected void parts_add(Stage stage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Add_Rec_PartSpec.fxml"));
		   
	    Scene scene = new Scene(root);
	    Stage stage_set = new Stage();
	    stage_set.setTitle("M&U - Add Record Window");
	    stage_set.setResizable(false);
	    stage_set.initModality(Modality.WINDOW_MODAL);
	    stage_set.initOwner(mu_main_controller.getPrimaryStage());
	    stage_set.setScene(scene);
	    stage_set.show();
	}
	
	//Вызываем окно дублирования записи
	protected void ps_dup() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("duplicate_rec_pss.fxml"));
		Scene scene = new Scene(root);
	    Stage stage_set = new Stage();
		stage_set.initModality(Modality.WINDOW_MODAL);	
		stage_set.initOwner(mu_main_controller.getPrimaryStage());
	    stage_set.setTitle("M&U - Duplicate Record Window");
	    stage_set.setResizable(false);
	    stage_set.setScene(scene);
	    stage_set.show();
	}
}
