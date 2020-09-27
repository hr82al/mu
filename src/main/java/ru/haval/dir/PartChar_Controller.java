package ru.haval.dir;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.haval.share_class.s_class;

public class PartChar_Controller {
	
	@FXML
	TableView<Hmmr_PartChar_Model> table_partchar = new TableView<>();
	
	@FXML
	TableColumn<Hmmr_PartChar_Model, String> col_id_pc, col_Part_Type, col_SP_KIND, col_Part_Sub_Type, 
		col_Part_Sub_Type_ENG, col_Part_Characteristic_Name_1, col_Part_Characteristic_Name_2, col_Part_Characteristic_Name_3, col_Part_Characteristic_Name_4;
	
	@FXML
	JFXButton btn_add_pc, btn_upd_pc, btn_del_pc, btn_upd_tbl_pc, btn_cancel_pc;
	
	public static ObservableList<Hmmr_PartChar_Model> _table_update_partchar = FXCollections.observableArrayList();
	public static boolean _flag_window_pc = true;
	public static String _id_part_char = "0";
	
	_query qr = new _query();
	s_class scl = new s_class();
	Stage stage = new Stage();
	
	@FXML
	public void initialize()
	{
		scl._style(btn_add_pc);
		scl._style(btn_upd_pc);
		scl._style(btn_del_pc);
		scl._style(btn_cancel_pc);
		scl._style(btn_upd_tbl_pc);
		
		btn_upd_pc.setDisable(true);
		btn_del_pc.setDisable(true);
		
		initData();
		
		col_id_pc.setCellValueFactory(CellData -> CellData.getValue().getId());
		col_Part_Type.setCellValueFactory(CellData -> CellData.getValue().getPart_Type());
		col_SP_KIND.setCellValueFactory(CellData -> CellData.getValue().getSP_KIND());
		col_Part_Sub_Type.setCellValueFactory(CellData -> CellData.getValue().getPart_Sub_Type());
		col_Part_Sub_Type_ENG.setCellValueFactory(CellData -> CellData.getValue().getPart_Sub_Type_ENG());
		col_Part_Characteristic_Name_1.setCellValueFactory(CellData -> CellData.getValue().getPart_Characteristic_Name_1());
		col_Part_Characteristic_Name_2.setCellValueFactory(CellData -> CellData.getValue().getPart_Characteristic_Name_2());
		col_Part_Characteristic_Name_3.setCellValueFactory(CellData -> CellData.getValue().getPart_Characteristic_Name_3());
		col_Part_Characteristic_Name_4.setCellValueFactory(CellData -> CellData.getValue().getPart_Characteristic_Name_4());
		
		btn_add_pc.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				btn_upd_pc.setDisable(true);
				btn_del_pc.setDisable(true);
				
				_flag_window_pc = true;
			try {					
				part_add(stage);
			} catch (IOException e) {
				e.printStackTrace();
			}
			}
		});
		
		_table_update_partchar.addListener(new ListChangeListener<Hmmr_PartChar_Model> () {

			@Override
			public void onChanged(Change<? extends Hmmr_PartChar_Model> arg0) {
				table_partchar.setItems(qr._select_data_partchar());
				table_partchar.getColumns().get(0).setVisible(false);
		        table_partchar.getColumns().get(0).setVisible(true);
			}
			
		});
		table_partchar.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				btn_upd_pc.setDisable(false);
				btn_del_pc.setDisable(false);
				try {
					_id_part_char = table_partchar.getSelectionModel().getSelectedItem().getIdStr();
				}
				catch (Exception e) {
					
				}
			}
		});
		table_partchar.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() == 2 ){
					_flag_window_pc = false;
					_id_part_char = table_partchar.getSelectionModel().getSelectedItem().getIdStr();
					try {
						part_add(stage);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		btn_upd_pc.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				_flag_window_pc = false;
				btn_upd_pc.setDisable(true);
				btn_del_pc.setDisable(true);
				try {
					part_add(stage);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	void initData()
	{
		table_partchar.setItems(qr._select_data_partchar());
	}
	
	protected void part_add(Stage stage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Add_Rec_PartChar.fxml"));
		   
	    Scene scene = new Scene(root);
	    Stage stage_set = new Stage();
	    stage_set.setTitle("M&U - Add Record Window");
	    stage_set.setResizable(false);
	    stage_set.initModality(Modality.WINDOW_MODAL);
	    stage_set.initOwner(mu_main_controller.getPrimaryStage());
	    stage_set.setScene(scene);
	    stage_set.show();
	}
	
}
