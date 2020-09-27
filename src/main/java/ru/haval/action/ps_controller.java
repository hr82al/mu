package ru.haval.action;

import java.io.IOException;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import  ru.haval.application.conn_connector;
import ru.haval.application.mu_main_controller;
import  ru.haval.db._query;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ru.haval.share_class.s_class;

public class ps_controller {
	
	@FXML 
	TableView<hmmr_ps_model> table_ps = new TableView<hmmr_ps_model>();
	
	@FXML
	TableColumn<hmmr_ps_model, String> col_num_ps, col_company_ps, col_plant_ps, col_shops_ps, col_groups_ps, col_lines_ps, col_oss_ps, col_equips_ps, col_shop_ps, col_line_ps, col_linerus_ps,
		col_os_ps, col_osrus_ps, col_equip_ps, col_description_ps, col_equiplabel_ps, col_stationlabel_ps, col_manual_ps, col_stsupplier_ps, col_location_ps, 
		col_coord_ps, col_alt_ps, col_cham_ps, col_trcu_ps, col_trcul_ps, col_hazardous_ps, col_keyequip_ps, col_type_ps, col_sn_ps, col_manuf_ps, col_mtc_ps, col_respons_ps,
		col_melec_ps, col_mair_ps, col_mwater_ps, col_mcwater_ps, col_mhwater_ps, col_rowater_ps, col_mgas_ps, col_group_ps,
		col_shoprus_ps, col_grouprus_ps, col_equiprus_ps, col_groupotv_ps, col_invnum_ps, col_numos_ps, col_startdate_ps,
		col_cost_ps;
	
	@FXML
	JFXButton add_ps, upd_ps, del_ps, upd_table, btn_duplicate;
	
	@FXML
	ScrollPane sp_ps;
	
	@FXML
	AnchorPane ap_ps;
	
	@FXML
	VBox vb_ps;
	
	@FXML
	HBox hb1, hb2, hb3, hb4;
	
	@FXML
	private ComboBox<String> c_shop, c_group, c_line, c_os, c_equip;
	
	public static ObservableList<TableColumn<hmmr_ps_model, ?>> columns_ps;
	public static ObservableList<hmmr_ps_model> _table_update_ps = FXCollections.observableArrayList();
	
	_query qr = new _query();
	s_class scl = new s_class();
	Tooltip tip;
	
	public Stage stage = new Stage();
	
	public static String _id_ps, _num_ps, _company_ps, _plant_ps, _shops_ps, _groups_ps, _lines_ps, _oss_ps, _equips_ps, _shop_ps, _line_ps, _linerus_ps,
	_os_ps, _osrus_ps, _equip_ps, _description_ps, _manual_ps, _stsupplier_ps, _location_ps, 
	_coord_ps, _alt_ps, _cham_ps, _trcu_ps, _trcul_ps, _hazardous_ps, _keyequip_ps, _type_ps, _sn_ps, _manuf_ps, _mtc_ps, _respons_ps,
	_melec_ps, _mair_ps, _mwater_ps, _mcwater_ps, _mhwater_ps, _rowater_ps, _mgas_ps, _group_ps, _shoprus_ps, _grouprus_ps,
	_equiprus_ps, _gruopotv_ps, _invnum_ps, _numos_ps, _cost_ps, _startdate_ps;
		
	public static String _filter_shop, _filter_group, _filter_line, _filter_os, _filter_equip;
	
	public static int flag_ps = 0; // Флаг сортировки таблицы PS
	
	@FXML
	public void initialize()
	{
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		Double screen_width = primaryScreenBounds.getWidth();
		Double screen_hight = primaryScreenBounds.getHeight(); 
		
		sp_ps.setPrefWidth(screen_width);
		sp_ps.setPrefHeight(screen_hight);
		ap_ps.setPrefWidth(screen_width - 20);
		ap_ps.setPrefHeight(screen_hight - 20);
		vb_ps.setPrefWidth(screen_width - 20);
		vb_ps.setPrefHeight(screen_hight - 20);
		hb1.setPrefWidth(screen_width - 20);
		hb1.setPrefHeight(30.0);
		hb2.setPrefWidth(screen_width - 20);
		hb2.setPrefHeight(50.0);
		hb3.setPrefWidth(screen_width - 20);
		hb3.setPrefHeight(screen_hight - 220);
		hb4.setPrefWidth(screen_width - 20);
		hb4.setPrefHeight(70.0);
		table_ps.setPrefWidth(screen_width-70);
		table_ps.setPrefHeight(screen_hight-200);
		
		
		
/*		sp_ps.setPrefWidth(screen_width - 530);
		sp_ps.setPrefHeight(screen_hight - 30);
		
		table_ps.setPrefHeight(screen_hight - 230);
		table_ps.setPrefWidth(screen_width - 70);
		
		add_ps.setLayoutY(screen_hight - 110);
		upd_ps.setLayoutY(screen_hight - 110);
		del_ps.setLayoutY(screen_hight - 110);
		upd_table.setLayoutY(screen_hight - 110);
		btn_duplicate.setLayoutY(screen_hight - 110);*/
		
		upd_ps.setDisable(true);
		del_ps.setDisable(true);
		btn_duplicate.setDisable(true);
		
		if(conn_connector.USER_ROLE.equals("Technics") || conn_connector.USER_ROLE.equals("Engeneer"))
			del_ps.setDisable(true);
		
		if(conn_connector.LANG_ID == 1)
			lang_fun("en", "EN");
		if(conn_connector.LANG_ID == 0)
			lang_fun("ru", "RU");
		if(conn_connector.LANG_ID == 2)
			lang_fun("zh", "CN");
		if(conn_connector.LANG_ID == -1)
			lang_fun("ru", "RU");
		
		scl._style(add_ps);
		scl._style(upd_ps);
		scl._style(del_ps);
		scl._style(upd_table);
		scl._style(btn_duplicate);
		
		initData();
		
		col_num_ps.setCellValueFactory(CellData -> CellData.getValue().IdProperty());
		col_company_ps.setCellValueFactory(CellData -> CellData.getValue().CompanyProperty());
		col_plant_ps.setCellValueFactory(CellData -> CellData.getValue().PlantProperty());
		col_shops_ps.setCellValueFactory(CellData -> CellData.getValue().Shop_sProperty());
		col_groups_ps.setCellValueFactory(CellData -> CellData.getValue().Group_PMProperty());
		col_lines_ps.setCellValueFactory(CellData -> CellData.getValue().Line_Machine_sProperty());
		col_oss_ps.setCellValueFactory(CellData -> CellData.getValue().Operation_Station_sProperty());
		col_equips_ps.setCellValueFactory(CellData -> CellData.getValue().Equipment_sProperty());
		col_shop_ps.setCellValueFactory(CellData -> CellData.getValue().ShopProperty());
		col_group_ps.setCellValueFactory(CellData -> CellData.getValue().FL04_Group_ENGProperty());
		col_line_ps.setCellValueFactory(CellData -> CellData.getValue().Line_MachineProperty());
		col_os_ps.setCellValueFactory(CellData -> CellData.getValue().Operation_StationProperty());
		col_equip_ps.setCellValueFactory(CellData -> CellData.getValue().EquipmentProperty());
		col_shoprus_ps.setCellValueFactory(CellData -> CellData.getValue().FL03_Shop_RUSProperty());
		col_grouprus_ps.setCellValueFactory(CellData -> CellData.getValue().FL04_Group_RUSProperty());
		col_linerus_ps.setCellValueFactory(CellData -> CellData.getValue().Line_Machine_RUSProperty());
		col_osrus_ps.setCellValueFactory(CellData -> CellData.getValue().Operation_Station_RUSProperty());
		col_equiprus_ps.setCellValueFactory(CellData -> CellData.getValue().FL07_Equipment_RUSProperty());
		col_description_ps.setCellValueFactory(CellData -> CellData.getValue().DescriptionProperty());
		col_equiplabel_ps.setCellValueFactory(CellData -> CellData.getValue().Equip_labelProperty());
		col_stationlabel_ps.setCellValueFactory(CellData -> CellData.getValue().Station_labelProperty());
		col_manual_ps.setCellValueFactory(CellData -> CellData.getValue().manualProperty());
		col_groupotv_ps.setCellValueFactory(CellData -> CellData.getValue().RespPlannerGroupProperty());
		col_invnum_ps.setCellValueFactory(CellData -> CellData.getValue().AssetsInvNumProperty());
		col_numos_ps.setCellValueFactory(CellData -> CellData.getValue().AssetsOsNumProperty());
		col_startdate_ps.setCellValueFactory(CellData -> CellData.getValue().AssetsStartDateProperty());
		col_cost_ps.setCellValueFactory(CellData -> CellData.getValue().CostCenterProperty());
		col_location_ps.setCellValueFactory(CellData -> CellData.getValue().LocationProperty());
		col_coord_ps.setCellValueFactory(CellData -> CellData.getValue().CoordinatesProperty());
		col_alt_ps.setCellValueFactory(CellData -> CellData.getValue().AltitudeProperty());
		col_cham_ps.setCellValueFactory(CellData -> CellData.getValue().CHAMBERProperty());
		col_trcu_ps.setCellValueFactory(CellData -> CellData.getValue().TR_CUProperty());
		col_trcul_ps.setCellValueFactory(CellData -> CellData.getValue().TR_CU_LinkProperty());
		col_hazardous_ps.setCellValueFactory(CellData -> CellData.getValue().HazardousProperty());
		col_keyequip_ps.setCellValueFactory(CellData -> CellData.getValue().Key_equipmentProperty());
		col_stsupplier_ps.setCellValueFactory(CellData -> CellData.getValue().Station_SupplierProperty());
		col_type_ps.setCellValueFactory(CellData -> CellData.getValue().TypetProperty());
		col_sn_ps.setCellValueFactory(CellData -> CellData.getValue().S_NProperty());
		col_manuf_ps.setCellValueFactory(CellData -> CellData.getValue().ManufProperty());
		col_mtc_ps.setCellValueFactory(CellData -> CellData.getValue().MTCProperty());
		col_respons_ps.setCellValueFactory(CellData -> CellData.getValue().RespProperty());
		col_melec_ps.setCellValueFactory(CellData -> CellData.getValue().M_ElectricProperty());
		col_mair_ps.setCellValueFactory(CellData -> CellData.getValue().M_AirProperty());
		col_mwater_ps.setCellValueFactory(CellData -> CellData.getValue().M_WaterProperty());
		col_mcwater_ps.setCellValueFactory(CellData -> CellData.getValue().M_Cold_waterProperty());
		col_mhwater_ps.setCellValueFactory(CellData -> CellData.getValue().M_Hot_waterProperty());
		col_rowater_ps.setCellValueFactory(CellData -> CellData.getValue().RO_WaterProperty());
		col_mgas_ps.setCellValueFactory(CellData -> CellData.getValue().M_GasProperty());
		
		//Разрешаем ходить по ячейкам, а не целой строкой
        table_ps.getSelectionModel().setCellSelectionEnabled(true);
        
        table_ps.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);;
		
		add_ps.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				try {
					addps_start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		columns_ps = table_ps.getColumns();	
		upd_ps.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				hmmr_ps_model _ccl1 = table_ps.getSelectionModel().getSelectedItem();
				
				upd_ps.setDisable(true);
				func_upd(_ccl1.getId());
			}
		});
		
		btn_duplicate.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				try {
					ps_dup();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		table_ps.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				upd_ps.setDisable(false);
				btn_duplicate.setDisable(false);
				if(!conn_connector.USER_ROLE.equals("Technics") || !conn_connector.USER_ROLE.equals("Engeneer"))
					del_ps.setDisable(false);
				try {
					addrec_ps_controller._last_id = Integer.parseInt(table_ps.getSelectionModel().getSelectedItem().getId());
				}
				catch (Exception e) {
					
				}
			}
		});
		upd_table.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				/*if(flag_ps == 0)
					_table_update_ps.addAll(qr._select_data_ps());
				if(flag_ps == 1)
					_table_update_ps.addAll(qr._select_data_filter_ps(_filter_shop));
				if(flag_ps == 2)
					_table_update_ps.addAll(qr._select_data_filter_ps(_filter_shop, _filter_group));
				if(flag_ps == 3)
					_table_update_ps.addAll(qr._select_data_filter_ps(_filter_shop, _filter_group, _filter_line));
				if(flag_ps == 4)
					_table_update_ps.addAll(qr._select_data_filter_ps(_filter_shop, _filter_group, _filter_line, _filter_os));
				if(flag_ps == 5)
					_table_update_ps.addAll(qr._select_data_filter_ps(_filter_shop, _filter_group, _filter_line, _filter_os, _filter_equip));*/
				table_ps.setItems(qr._select_data_ps());
				columns_ps.get(0).setVisible(false);
			    columns_ps.get(0).setVisible(true);
			   
			    c_group.valueProperty().set(null);
			    c_line.valueProperty().set(null);
			    c_os.valueProperty().set(null);
				c_equip.valueProperty().set(null);
			}
		});
		del_ps.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
			    alert.setTitle("M&U - Delete Record Window");
			    hmmr_ps_model _ccl = table_ps.getSelectionModel().getSelectedItem();
			    
			    alert.setHeaderText("Вы действительно хотите удалить запись № = " + _ccl.getId() + "?");
			    //alert.setContentText("C:/MyFile.txt");
			 
			    // option != null.
			    Optional<ButtonType> option = alert.showAndWait();
			    if (option.get() == null) {
			       //label.setText("No selection!");
			    } else if (option.get() == ButtonType.OK) {
			  	   _ccl = table_ps.getSelectionModel().getSelectedItem();
			  	   try {
			  	   qr._update_rec_ps_del(_ccl.getId());
			  	   qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + " - Удалил запись № = " + _ccl.getId() + " в таблице Plant Structure");
			  	   _table_update_ps.addAll(qr._select_data_ps());
			  	   } catch (Exception e) {
					
				}
			    } else if (option.get() == ButtonType.CANCEL) {
			       //label.setText("Cancelled!");
			    } else {
			       //label.setText("-");
			    }
			}
		});
		_table_update_ps.addListener(new ListChangeListener<hmmr_ps_model>() {
		    @Override
			public void onChanged(Change<? extends hmmr_ps_model> c) {
				
			    table_ps.setItems(qr._select_data_ps());
			    table_ps.getColumns().get(0).setVisible(false);
			    table_ps.getColumns().get(0).setVisible(true);
		    	
		        if(flag_ps == 1) {
		        	table_ps.setItems(qr._select_data_filter_ps(_filter_shop));
					table_ps.getColumns().get(0).setVisible(false);
			        table_ps.getColumns().get(0).setVisible(true);
		        }
				if(flag_ps == 2) {
					table_ps.setItems(qr._select_data_filter_ps(_filter_shop, _filter_group));
					table_ps.getColumns().get(0).setVisible(false);
			        table_ps.getColumns().get(0).setVisible(true);
				}
				if(flag_ps == 3) {
					table_ps.setItems(qr._select_data_filter_ps(_filter_shop, _filter_group, _filter_line));
					table_ps.getColumns().get(0).setVisible(false);
			        table_ps.getColumns().get(0).setVisible(true);
				}
				if(flag_ps == 4) {
					table_ps.setItems(qr._select_data_filter_ps(_filter_shop, _filter_group, _filter_line, _filter_os));
					table_ps.getColumns().get(0).setVisible(false);
			        table_ps.getColumns().get(0).setVisible(true);
				}
				if(flag_ps == 5) {
					table_ps.setItems(qr._select_data_filter_ps(_filter_shop, _filter_group, _filter_line, _filter_os, _filter_equip));
					table_ps.getColumns().get(0).setVisible(false);
			        table_ps.getColumns().get(0).setVisible(true);
				}
			}
		});
		//Вызываем окно обновления по двойному клику на строке
		table_ps.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() == 2 ){
	                func_upd(table_ps.getSelectionModel().getSelectedItem().getId());
	            }
			}
		});
		
		c_shop.setItems(qr._select_shop_pm());
		c_shop.setValue(apwr_controller.SHOP_NAME);
		
		if(c_shop.getValue().toString().length() != 0)
			c_group.setItems(qr._select_group_pm(scl.parser_str(c_shop.getValue(), 0)));
		
		try {
			c_shop.getSelectionModel().selectedItemProperty().addListener(new  ChangeListener<String>() {
	
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if(c_shop.getValue().toString().length() != 0) {
						c_group.valueProperty().set(null);
						c_line.valueProperty().set(null);
						c_os.valueProperty().set(null);
						c_equip.valueProperty().set(null);
						
						c_group.setItems(qr._select_group_pm(scl.parser_str(c_shop.getValue(), 0)));
						table_ps.setItems(qr._select_data_filter_ps(scl.parser_str(c_shop.getValue(), 0)));
						_filter_shop = scl.parser_str(c_shop.getValue(), 0);
						flag_ps = 1;
					}
				}
			});
		} catch (Exception e) {
			
		}
		
		c_group.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				
				try {
					if(c_group.getValue().toString().length() != 0) {
						c_line.valueProperty().set(null);
						c_os.valueProperty().set(null);
						c_equip.valueProperty().set(null);
						
						c_line.setItems(qr._select_lm_pm(scl.parser_str(c_group.getValue(), 0)));
						table_ps.setItems(qr._select_data_filter_ps(scl.parser_str(c_shop.getValue(), 0), scl.parser_str(c_group.getValue(), 0)));
						_filter_group = scl.parser_str(c_group.getValue(), 0);
						flag_ps = 2;
					}
					//if(lm_wr_add.getValue().toString().length() != 0)
					//	os_wr_add.setItems(qr._select_os_pm(sclass.parser_str(shop_wr_add.getValue(), 0), sclass.parser_str(lm_wr_add.getValue(), 0)));
					} catch (Exception e) {
						
					}
			}
		});
		c_group.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip = new Tooltip(c_group.getValue());
				Point2D p = c_group.localToScreen(c_group.getLayoutBounds().getMaxX(), c_group.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(c_group, p.getX(), p.getY());
			}
		});
		c_group.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip.hide();
			}
		});
		
		c_line.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				try {
					if(c_line.getValue().toString().length() != 0) {
						c_os.valueProperty().set(null);
						c_equip.valueProperty().set(null);
						
						c_os.setItems(qr._select_os_pm(scl.parser_str(c_group.getValue(), 0), scl.parser_str(c_line.getValue(), 0)));
						table_ps.setItems(qr._select_data_filter_ps(scl.parser_str(c_shop.getValue(), 0), scl.parser_str(c_group.getValue(), 0), scl.parser_str(c_line.getValue(), 0)));
						_filter_line = scl.parser_str(c_line.getValue(), 0);
						flag_ps = 3;
					}
					} catch (Exception e) {
						
					}
			}
		});
		c_line.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip = new Tooltip(c_line.getValue());
				Point2D p = c_line.localToScreen(c_line.getLayoutBounds().getMaxX(), c_line.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(c_line, p.getX(), p.getY());
			}
		});
		c_line.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip.hide();
			}
		});
			
		
		c_os.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
	
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				try {
					if(c_os.getValue().toString().length() != 0) {
						c_equip.valueProperty().set(null);
						
						c_equip.setItems(qr._select_equip_pm(scl.parser_str(c_os.getValue(), 0), scl.parser_str(c_group.getValue(), 0), scl.parser_str(c_line.getValue(), 0)));
						table_ps.setItems(qr._select_data_filter_ps(scl.parser_str(c_shop.getValue(), 0), scl.parser_str(c_group.getValue(), 0), scl.parser_str(c_line.getValue(), 0), scl.parser_str(c_os.getValue(), 0)));
						_filter_os = scl.parser_str(c_os.getValue(), 0);
						flag_ps = 4;
					}
					} catch (Exception e) {
					}
			}
		});
		c_os.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip = new Tooltip(c_os.getValue());
				Point2D p = c_os.localToScreen(c_os.getLayoutBounds().getMaxX(), c_os.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(c_os, p.getX(), p.getY());
			}
		});
		c_os.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip.hide();
			}
		});
		
		c_equip.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				try {
					if(c_os.getValue().toString().length() != 0) {
						table_ps.setItems(qr._select_data_filter_ps(scl.parser_str(c_shop.getValue(), 0), scl.parser_str(c_group.getValue(), 0), scl.parser_str(c_line.getValue(), 0), scl.parser_str(c_os.getValue(), 0), scl.parser_str(c_equip.getValue(), 0)));
						_filter_equip = scl.parser_str(c_equip.getValue(), 0);
						flag_ps = 5;
					}
				}
				catch (Exception e) {
					
				}
			}
		});
		c_equip.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip = new Tooltip(c_equip.getValue());
				Point2D p = c_equip.localToScreen(c_equip.getLayoutBounds().getMaxX(), c_equip.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(c_equip, p.getX(), p.getY());
			}
		});
		c_equip.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip.hide();
			}
		});
		
		//Ставим фокус и опускаемся на последнюю строку таблицы     
        table_ps.requestFocus();
        table_ps.getFocusModel().focus(0);
        table_ps.getSelectionModel().selectLast();
        table_ps.scrollTo(table_ps.getItems().size());
	}
	
	private void initData()
	{
		table_ps.setItems(qr._select_data_ps());
	}
	//Вызываем окно записи для PS
	protected void addps_start() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("add_rec_ps.fxml"));
		Scene scene = new Scene(root);
	    Stage stage_set = new Stage();
		stage_set.initModality(Modality.WINDOW_MODAL);	
		stage_set.initOwner(mu_main_controller.getPrimaryStage());
	    stage_set.setTitle("M&U - Add Record Window");
	    stage_set.setResizable(true);
	    stage_set.setScene(scene);
	    stage_set.show();
	}
	
	public void refreshTable_ps(@SuppressWarnings("rawtypes") ObservableList col) {
		 table_ps.getColumns().removeAll(columns_ps);
		 table_ps.getColumns().addAll(columns_ps);
		 table_ps.setItems(qr._select_data_ps());
		 columns_ps.get(0).setVisible(false);
	     columns_ps.get(0).setVisible(true);
	 }
	
	private void func_upd(String id)
	{
		String _sql_rez = qr._select_for_update_ps(id);
		_id_ps = id;
		
		_num_ps = scl.parser_str_str_str(_sql_rez, 0);
		_company_ps = scl.parser_str_str_str(_sql_rez, 1);
		_plant_ps = scl.parser_str_str_str(_sql_rez, 2);
		_shops_ps = scl.parser_str_str_str(_sql_rez, 3);
		_groups_ps = scl.parser_str_str_str(_sql_rez, 4);
		_lines_ps = scl.parser_str_str_str(_sql_rez, 5);
		_oss_ps = scl.parser_str_str_str(_sql_rez, 6);
		_equips_ps = scl.parser_str_str_str(_sql_rez, 7);
		_shop_ps = scl.parser_str_str_str(_sql_rez, 8);
		_group_ps = scl.parser_str_str_str(_sql_rez, 9);
		_line_ps = scl.parser_str_str_str(_sql_rez, 10);
		_os_ps = scl.parser_str_str_str(_sql_rez, 11);
		_equip_ps = scl.parser_str_str_str(_sql_rez, 12);
		_shoprus_ps = scl.parser_str_str_str(_sql_rez, 13);
		_grouprus_ps = scl.parser_str_str_str(_sql_rez, 14);
		_linerus_ps = scl.parser_str_str_str(_sql_rez, 15);
		_osrus_ps = scl.parser_str_str_str(_sql_rez, 16);
		_equiprus_ps = scl.parser_str_str_str(_sql_rez, 17);
		_description_ps = scl.parser_str_str_str(_sql_rez, 18);
		_manual_ps = scl.parser_str_str_str(_sql_rez, 19);
		_gruopotv_ps = scl.parser_str_str_str(_sql_rez, 20);
		_invnum_ps = scl.parser_str_str_str(_sql_rez, 21);
		_numos_ps = scl.parser_str_str_str(_sql_rez, 22);
		_startdate_ps = scl.parser_str_str_str(_sql_rez, 23);
		_cost_ps = scl.parser_str_str_str(_sql_rez, 24);
		_location_ps = scl.parser_str_str_str(_sql_rez, 25);
		_cham_ps = scl.parser_str_str_str(_sql_rez, 26);
		_coord_ps = scl.parser_str_str_str(_sql_rez, 27);
		_alt_ps = scl.parser_str_str_str(_sql_rez, 28);
		_trcu_ps = scl.parser_str_str_str(_sql_rez, 29);
		_trcul_ps = scl.parser_str_str_str(_sql_rez, 30);
		_hazardous_ps = scl.parser_str_str_str(_sql_rez, 31);
		_keyequip_ps = scl.parser_str_str_str(_sql_rez, 32);
		_stsupplier_ps = scl.parser_str_str_str(_sql_rez, 33);
		_manuf_ps = scl.parser_str_str_str(_sql_rez, 34);
		_type_ps = scl.parser_str_str_str(_sql_rez, 35);
		_sn_ps = scl.parser_str_str_str(_sql_rez, 36);
		_mtc_ps = scl.parser_str_str_str(_sql_rez, 37);
		_respons_ps = scl.parser_str_str_str(_sql_rez, 38);
		_melec_ps = scl.parser_str_str_str(_sql_rez, 39);
		_mair_ps = scl.parser_str_str_str(_sql_rez, 40);
		_mwater_ps = scl.parser_str_str_str(_sql_rez, 41);
		_mcwater_ps = scl.parser_str_str_str(_sql_rez, 42);
		_mhwater_ps = scl.parser_str_str_str(_sql_rez, 43);
		_rowater_ps = scl.parser_str_str_str(_sql_rez, 44);
		_mgas_ps = scl.parser_str_str_str(_sql_rez, 45);
		try {
			ps_upd();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Вызываем окно обновления записи
	protected void ps_upd() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("upd_rec_ps.fxml"));
		Scene scene = new Scene(root);
	    Stage stage_set = new Stage();
		stage_set.initModality(Modality.WINDOW_MODAL);	
		stage_set.initOwner(mu_main_controller.getPrimaryStage());
	    stage_set.setTitle("M&U - Update Record Window");
	    stage_set.setResizable(true);
	    stage_set.setScene(scene);
	    stage_set.show();
		}
	//Вызываем окно дублирования записи
	protected void ps_dup() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("duplicate_rec_ps.fxml"));
		Scene scene = new Scene(root);
	    Stage stage_set = new Stage();
		stage_set.initModality(Modality.WINDOW_MODAL);	
		stage_set.initOwner(mu_main_controller.getPrimaryStage());
	    stage_set.setTitle("M&U - Duplicate Record Window");
	    stage_set.setResizable(false);
	    stage_set.setScene(scene);
	    stage_set.show();
	}
	
	private void lang_fun(String loc1, String loc2)
	{
		ResourceBundle lngBndl = ResourceBundle
	            .getBundle("bundles.LangBundle", new Locale(loc1, loc2));
				
		col_company_ps.setText(lngBndl.getString("col_company_ps"));
		col_plant_ps.setText(lngBndl.getString("col_plant_ps"));
		col_shops_ps.setText(lngBndl.getString("col_shop_pm")+","+lngBndl.getString("lbl_short"));
		col_groups_ps.setText(lngBndl.getString("col_group_pm")+","+lngBndl.getString("lbl_short"));
		col_lines_ps.setText(lngBndl.getString("col_lm_pm")+","+lngBndl.getString("lbl_short"));
		col_oss_ps.setText(lngBndl.getString("col_os_pm")+","+lngBndl.getString("lbl_short"));
		col_equips_ps.setText(lngBndl.getString("col_equip_pm")+","+lngBndl.getString("lbl_short"));
		col_shop_ps.setText(lngBndl.getString("col_shop_pm"));
		col_line_ps.setText(lngBndl.getString("col_lm_pm"));
		col_linerus_ps.setText(lngBndl.getString("col_lm_pm")+","+lngBndl.getString("lbl_rus"));
		col_os_ps.setText(lngBndl.getString("col_os_pm"));
		col_osrus_ps.setText(lngBndl.getString("col_os_pm")+","+lngBndl.getString("lbl_rus"));
		col_equip_ps.setText(lngBndl.getString("col_equip_pm"));
		col_description_ps.setText(lngBndl.getString("desc_ap"));
		col_equiplabel_ps.setText(lngBndl.getString("col_equiplabel_ps"));
		col_stationlabel_ps.setText(lngBndl.getString("col_stationlabel_ps"));
		col_manual_ps.setText(lngBndl.getString("col_sdoc_inst"));
		col_stsupplier_ps.setText(lngBndl.getString("col_stsupplier_ps"));
		col_location_ps.setText(lngBndl.getString("col_location_ps"));
		col_coord_ps.setText(lngBndl.getString("col_coord_ps"));
		col_alt_ps.setText(lngBndl.getString("col_alt_ps"));
		col_cham_ps.setText(lngBndl.getString("col_cham_ps"));
		col_trcu_ps.setText(lngBndl.getString("col_trcu_ps"));
		col_trcul_ps.setText(lngBndl.getString("col_trcul_ps"));
		col_hazardous_ps.setText(lngBndl.getString("col_hazardous_ps"));
		col_keyequip_ps.setText(lngBndl.getString("col_keyequip_ps"));
		col_type_ps.setText(lngBndl.getString("lbl_type_ap"));
		col_sn_ps.setText(lngBndl.getString("col_sn_ps"));
		col_manuf_ps.setText(lngBndl.getString("col_manuf_ps"));
		col_mtc_ps.setText(lngBndl.getString("col_mtc_ps"));
		col_respons_ps.setText(lngBndl.getString("lbl_otv_ap"));
		col_melec_ps.setText(lngBndl.getString("col_melec_ps"));
		col_mair_ps.setText(lngBndl.getString("col_mair_ps"));
		col_mwater_ps.setText(lngBndl.getString("col_mwater_ps"));
		col_mcwater_ps.setText(lngBndl.getString("col_mcwater_ps"));
		col_mhwater_ps.setText(lngBndl.getString("col_mhwater_ps"));
		col_rowater_ps.setText(lngBndl.getString("col_rowater_ps"));
		col_mgas_ps.setText(lngBndl.getString("col_mgas_ps"));
		col_group_ps.setText(lngBndl.getString("lbl_group"));
		col_shoprus_ps.setText(lngBndl.getString("col_shop_pm")+","+lngBndl.getString("lbl_rus"));
		col_grouprus_ps.setText(lngBndl.getString("lbl_group")+","+lngBndl.getString("lbl_rus"));
		col_equiprus_ps.setText(lngBndl.getString("col_equip_pm")+","+lngBndl.getString("lbl_rus"));
		col_groupotv_ps.setText(lngBndl.getString("col_groupotv_ps"));
		col_invnum_ps.setText(lngBndl.getString("col_invnum_ps")+" №");
		col_numos_ps.setText(lngBndl.getString("col_numos_ps"));
		col_startdate_ps.setText(lngBndl.getString("col_startdate_ps"));
		col_cost_ps.setText(lngBndl.getString("col_cost_ps"));
		upd_ps.setText(lngBndl.getString("upd_wr"));
		del_ps.setText(lngBndl.getString("del_inst"));
		upd_table.setText(lngBndl.getString("upd_table_wr"));
		btn_duplicate.setText(lngBndl.getString("btn_duplicate")+" x");
	}
}
