package ru.haval.action;

import java.io.IOException;
import java.util.Optional;

import com.jfoenix.controls.JFXButton;

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
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ru.haval.share_class.s_class;

public class Stuff_Controller {
	
	@FXML
	ScrollPane sp_staff;
	
	@FXML
	AnchorPane ap_staff;
	
	@FXML
	Pane pane_staff;
	
	@FXML
	VBox vb_staff;
	
	@FXML
	HBox hb1_staff, hb2_staff, hb3_staff;
	
	@FXML
	Label title_staff;
	
	@FXML
	TableView<Hmmr_Stuff_Model> table_staff = new TableView<Hmmr_Stuff_Model>();
	
	@FXML
	TableColumn<Hmmr_Stuff_Model, String> col_num_staff, col_staff_id, col_id_staff, col_fam_staff, col_imya_staff, col_otch_staff, col_dob_staff, col_sec_staff, col_groups_staff,
	col_team_staff, col_position_staff, col_gwm_staff, col_ds_staff, col_cell_staff, col_adr_staff, col_avto_staff, col_shoes_staff, col_clothes_staff, col_login_staff;
	
	@FXML
	JFXButton add_staff, upd_staff, del_staff, upd_tbl_staff, cancel_staff;
	
	public static ObservableList<Hmmr_Stuff_Model> _table_update_staff = FXCollections.observableArrayList();
	public static int _id_staff;
	
	_query qr = new _query();
	s_class scl = new s_class();
	Stage stage = new Stage();
	
	@FXML
	public void initialize()
	{
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		Double screen_width = primaryScreenBounds.getWidth();
		Double screen_hight = primaryScreenBounds.getHeight();
		
		scl._style(add_staff);
		scl._style(upd_staff);
		scl._style(del_staff);
		scl._style(upd_tbl_staff);
		scl._style(cancel_staff);
		
		upd_staff.setDisable(true);
		del_staff.setDisable(true);
		
		sp_staff.setPrefWidth(screen_width);
		sp_staff.setPrefHeight(screen_hight - 30);
		ap_staff.setPrefWidth(screen_width - 19);
		ap_staff.setPrefHeight(screen_hight - 50);
		pane_staff.setPrefWidth(screen_width - 19);
		pane_staff.setPrefHeight(screen_hight - 50);
		vb_staff.setPrefWidth(screen_width - 50);
		vb_staff.setPrefHeight(screen_hight - 50);
		hb1_staff.setPrefWidth(screen_width - 50);
		hb1_staff.setPrefHeight(70.0);
		hb2_staff.setPrefWidth(screen_width - 50);
		hb2_staff.setPrefHeight(screen_hight - 220);
		hb3_staff.setPrefWidth(screen_width - 50);
		hb3_staff.setPrefHeight(90.0);
		table_staff.setPrefWidth(screen_width-100);
		table_staff.setPrefHeight(screen_hight-200);
		title_staff.setPrefWidth(900.0);
		if(screen_width == 1920.0)
			col_position_staff.setPrefWidth(700.0);
		if(screen_width == 1768.0)
			col_position_staff.setPrefWidth(540.0);
		if(screen_width == 1600.0)
			col_position_staff.setPrefWidth(380.0);
		if(screen_width == 1440.0)
			col_position_staff.setPrefWidth(210.0);
		
		InitData();
		
		col_num_staff.setCellValueFactory(CellData -> CellData.getValue().getId());
		col_staff_id.setCellValueFactory(CellData -> CellData.getValue().getStaffId());
		col_id_staff.setCellValueFactory(CellData -> CellData.getValue().getID());
		col_fam_staff.setCellValueFactory(CellData -> CellData.getValue().getL_Name_Rus());
		col_imya_staff.setCellValueFactory(CellData -> CellData.getValue().getF_Name_Rus());
		col_otch_staff.setCellValueFactory(CellData -> CellData.getValue().getOtchestvo());
		col_dob_staff.setCellValueFactory(CellData -> CellData.getValue().getDoB());
		col_sec_staff.setCellValueFactory(CellData -> CellData.getValue().getSec());
		col_groups_staff.setCellValueFactory(CellData -> CellData.getValue().getGroup_S());
		col_team_staff.setCellValueFactory(CellData -> CellData.getValue().getTeam());
		col_position_staff.setCellValueFactory(CellData -> CellData.getValue().getPosition_RUS());
		col_gwm_staff.setCellValueFactory(CellData -> CellData.getValue().getGWM_ID());
		col_ds_staff.setCellValueFactory(CellData -> CellData.getValue().getDate_of_Start());
		col_cell_staff.setCellValueFactory(CellData -> CellData.getValue().getCell_1());
		col_adr_staff.setCellValueFactory(CellData -> CellData.getValue().getAddress());
		col_avto_staff.setCellValueFactory(CellData -> CellData.getValue().getAvto());
		col_shoes_staff.setCellValueFactory(CellData -> CellData.getValue().getShoes_Size());
		col_clothes_staff.setCellValueFactory(CellData -> CellData.getValue().getClothes_Size());
		col_login_staff.setCellValueFactory(CellData -> CellData.getValue().getLogin());
		
		add_staff.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				try {
					addstaff_start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		_table_update_staff.addListener(new ListChangeListener<Hmmr_Stuff_Model>() {
		    @Override
			public void onChanged(Change<? extends Hmmr_Stuff_Model> c) {
		    	table_staff.setItems(qr._select_data_staff());
		    	table_staff.getColumns().get(0).setVisible(false);
			    table_staff.getColumns().get(0).setVisible(true);
			    
			    upd_staff.setDisable(true);
			    del_staff.setDisable(true);
		    }
		});
		table_staff.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				try {
				_id_staff = Integer.parseInt(table_staff.getSelectionModel().getSelectedItem().getIdStr());
				upd_staff.setDisable(false);
				if(!conn_connector.USER_ROLE.equals("Technics") || !conn_connector.USER_ROLE.equals("Engeneer"))
					del_staff.setDisable(false);
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		});
		upd_staff.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				try {
					upd_staff_start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		//Вызываем окно обновления по двойному клику на строке
		table_staff.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() == 2 ){
		               try {
						upd_staff_start();
					} catch (IOException e) {
						e.printStackTrace();
					}
		           }
			}
		});
		del_staff.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
			    alert.setTitle("M&U - Delete Record Window");
			    Hmmr_Stuff_Model _hsm = table_staff.getSelectionModel().getSelectedItem();
			    
			    alert.setHeaderText("Вы действительно хотите удалить запись № = " + _hsm.getIdStr() + "?");
			    //alert.setContentText("C:/MyFile.txt");
			 
			    // option != null.
			    Optional<ButtonType> option = alert.showAndWait();
			    if (option.get() == null) {
			       //label.setText("No selection!");
			    } else if (option.get() == ButtonType.OK) {
			  	   _hsm = table_staff.getSelectionModel().getSelectedItem();
			  	   try {
			  	   func_del(_hsm.getIdStr());
			  	   } catch (Exception e) {
					
				}
			    } else if (option.get() == ButtonType.CANCEL) {
			       //label.setText("Cancelled!");
			    } else {
			       //label.setText("-");
			    }
			}
			
		});
		upd_tbl_staff.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				table_staff.setItems(qr._select_data_staff());
				table_staff.getColumns().get(0).setVisible(false);
			    table_staff.getColumns().get(0).setVisible(true);
			}
		});
	}
	
	private void InitData()
	{
		table_staff.setItems(qr._select_data_staff());
	}
	
	//Вызываем окно записи для Staff
	protected void addstaff_start() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Add_Rec_Staff.fxml"));
		Scene scene = new Scene(root);
		Stage stage_set = new Stage();
		stage_set.initModality(Modality.WINDOW_MODAL);	
		stage_set.initOwner(mu_main_controller.getPrimaryStage());
		stage_set.setTitle("M&U - Add Record Window");
		stage_set.setResizable(true);
		stage_set.setScene(scene);
		stage_set.show();
		}
	protected void upd_staff_start() throws IOException
	{
		Parent root;
		root = FXMLLoader.load(getClass().getResource("Upd_Rec_Staff.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("M&U - Update Record Window");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
	}
	
	private void func_del(String str)
	{
		
		qr._update_rec_del(qr._select_rec("hmmr_mu_staff", "user_id", "user_del", "id_num", str), "users", "user_del", "id");
		qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + " - Удалил запись № = " + str + " в таблице users");
		qr._update_rec_del(str, "hmmr_mu_staff", "user_del", "id_num");
		qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + " - Удалил запись № = " + str + " в таблице hmmr_mu_staff");
		
		_table_update_staff.addAll(qr._select_data_staff());
	}
}
