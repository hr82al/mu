package ru.haval.action;

import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import ru.haval.application.conn_connector;
import ru.haval.data.FxDatePickerConverter;
import ru.haval.db._query;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import ru.haval.share_class.s_class;

public class updrec_ps_controller {
	@FXML
	private ComboBox<String> company_ps, plant_ps, shops_ps, groups_ps, lines_ps, oss_ps, equips_ps, shop_ps, line_ps, linerus_ps, os_ps, osrus_ps, equip_ps, stsupplier_ps, location_ps, 
	cham_ps, trcu_ps, trcul_ps, hazardous_ps, keyequip_ps, type_ps, mtc_ps, respons_ps, group_ps, shoprus_ps, grouprus_ps,
	equiprus_ps, groupotv_ps, costcenter_ps;
	
	@FXML
	private TextField company_ps_t, plant_ps_t, shops_ps_t, groups_ps_t, lines_ps_t, oss_ps_t, equips_ps_t, shop_ps_t, equip_ps_t, manual_ps_t, stsupplier_ps_t, 
	location_ps_t, coord_ps_t, alt_ps_t, cham_ps_t, trcu_ps_t, trcul_ps_t, keyequip_ps_t, type_ps_t, sn_ps_t, manuf_ps_t, mtc_ps_t, respons_ps_t, melec_ps_t, mair_ps_t,
	mwater_ps_t, mcwater_ps_t, mhwater_ps_t, rowater_ps_t, mgas_ps_t, id_ps, group_ps_t, invnum_ps_t, osnum_ps_t;
	
	@FXML
	private TextArea line_ps_t, linerus_ps_t, os_ps_t, osrus_ps_t, description_ps_t, grouprus_ps_t, equiprus_ps_t, shoprus_ps_t;
	
	@FXML
	private JFXButton exp_ps, add_ps, cancel_ps, btn_left, btn_right;
	
	@FXML
	Label head_ps_upd, lbl_company_ps, lbl_plant_ps, lbl_shops_ps, lbl_groups_ps, lbl_lines_ps, lbl_oss_ps, lbl_equips_ps, lbl_shop_ps, lbl_line_ps, lbl_linerus_ps,
	lbl_os_ps, lbl_osrus_ps, lbl_equip_ps, lbl_description_ps, lbl_manual_ps, lbl_stsupplier_ps, lbl_location_ps,
	lbl_coord_ps, lbl_alt_ps, lbl_cham_ps, lbl_trcu_ps, lbl_trcul_ps, lbl_hazardous_ps, lbl_keyequip_ps, lbl_type_ps, lbl_sn_ps, lbl_manuf_ps, lbl_mtc_ps, lbl_respons_ps,
	lbl_melec_ps, lbl_mair_ps, lbl_mwater_ps, lbl_mcwater_ps, lbl_mhwater_ps, lbl_rowater_ps, lbl_mgas_ps, lbl_group_ps, 
	lbl_shoprus_ps, lbl_grouprus_ps, lbl_equiprus_ps, lbl_groupotv_ps, lbl_invnum_ps, lbl_osnum_ps, lbl_startdate_ps,
	lbl_costcenter_ps;
	
	@FXML
	ScrollPane sp_ps;
	
	@FXML
	DatePicker startdate_ps;
	
	private String equip_label, _s_name, prev_id, next_id;
	private static String pathToPs_Upd="C://";

	_query qr = new _query();
	s_class sclass = new s_class();
	ps_controller ps = new ps_controller();
	FxDatePickerConverter fx_dp = new FxDatePickerConverter();
	Stage stage;
		
	@SuppressWarnings("static-access")
	@FXML
	public void initialize()
	{
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		Double screen_width = primaryScreenBounds.getWidth();
		Double screen_hight = primaryScreenBounds.getHeight(); 
		
		sp_ps.setPrefWidth(screen_width - 615);
		sp_ps.setPrefHeight(screen_hight - 180);
		
		if(conn_connector.LANG_ID == 1)
			lang_fun("en", "EN");
		if(conn_connector.LANG_ID == 0)
			lang_fun("ru", "RU");
		if(conn_connector.LANG_ID == 2)
			lang_fun("zh", "CN");
		if(conn_connector.LANG_ID == -1)
			lang_fun("ru", "RU");
		
		sclass._style(add_ps);
		sclass._style(cancel_ps);
		sclass._style(exp_ps);
		//sclass._style(btn_left);
		//sclass._style(btn_right);
		
		osnum_ps_t.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(!newValue.isEmpty()) {
		
				if (!newValue.matches("\\d*|#|\\*")) {
					osnum_ps_t.setText(newValue.replaceAll("[^\\d|#|\\*]", ""));
		        }
				if(newValue.length() > 15) {
					
					osnum_ps_t.setText(newValue.replaceAll("[0-9]", ""));
	            	
				}
			}
			}
		});
		invnum_ps_t.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(!newValue.isEmpty()) {
		
				if (!newValue.matches("\\d*|#|\\*")) {
					invnum_ps_t.setText(newValue.replaceAll("[^\\d|#|\\*]", ""));
		        }
				if(newValue.length() > 15) {
					
					invnum_ps_t.setText(newValue.replaceAll("[0-9]", ""));
	            	
				}
			}
			}
		});
		
		Platform.runLater(() -> {
			Image imageOk = new Image(getClass().getResourceAsStream("left.png"));
			btn_left.setGraphic(new ImageView(imageOk)); });
		Platform.runLater(() -> {
			Image imageOk = new Image(getClass().getResourceAsStream("right.png"));
			btn_right.setGraphic(new ImageView(imageOk)); });
		
		add_ps.setDisable(true);
				
		company_ps.setValue("HAVAL");
		company_ps.setItems(qr._select_shop_ps("FL01_Company"));
		if(company_ps.getValue().length() != 0) {
			plant_ps.setItems(qr._select_shop_ps("FL02_Plant"));
			company_ps_t.setText(company_ps.getValue());
		}
		plant_ps.setValue("HMMR");
		if(plant_ps.getValue().length() != 0) {
			shops_ps.setItems(qr._select_shop_ps("FL03_Shop_s", "FL03_Shop_ENG", "FL02_Plant", plant_ps.getValue()));
			shop_ps.setItems(qr._select_shop_ps("FL03_Shop_s", "FL03_Shop_ENG", "FL02_Plant", plant_ps.getValue()));
			//shoprus_ps.setItems(qr._select_shop_ps("FL03_Shop_RUS", "FL02_Plant", plant_ps.getValue()));
			plant_ps_t.setText(plant_ps.getValue());
		}
		
		if(apwr_controller.SHOP_NAME.equals("A"))
			_s_name = apwr_controller.SHOP_NAME + " - Assembly Shop";
		if(apwr_controller.SHOP_NAME.equals("P"))
			_s_name = apwr_controller.SHOP_NAME + " - Painting Shop";
		if(apwr_controller.SHOP_NAME.equals("S"))
			_s_name = apwr_controller.SHOP_NAME + " - Stampting Shop";
		if(apwr_controller.SHOP_NAME.equals("W"))
			_s_name = apwr_controller.SHOP_NAME + " - Welding Shop";
		if(apwr_controller.SHOP_NAME.equals("E"))
			_s_name = apwr_controller.SHOP_NAME + " - Maintenance Shop";
		if(apwr_controller.SHOP_NAME.equals("L"))
			_s_name = apwr_controller.SHOP_NAME + " - Logistic Shop";
		if(apwr_controller.SHOP_NAME.equals("S,W"))
			_s_name = apwr_controller.SHOP_NAME + " - S,W";
		if(apwr_controller.SHOP_NAME.equals("MU"))
			_s_name = apwr_controller.SHOP_NAME + " - MU";
		if(apwr_controller.SHOP_NAME.equals("UTL"))
			_s_name = apwr_controller.SHOP_NAME + " - UTL";
		
		shops_ps.setValue(_s_name);
		shoprus_ps.setValue(qr._select_shop_ps_str("FL03_Shop_RUS", "FL03_Shop_s", sclass.parser_str(shops_ps.getValue(), 0)));
		if(shops_ps.getValue().toString().length() != 0) {
			groups_ps.setItems(qr._select_shop_ps("FL04_Group_s","FL03_Shop_s", sclass.parser_str(shops_ps.getValue(), 0)));
			shops_ps_t.setText(sclass.parser_str(shops_ps.getValue(), 0));
			shop_ps_t.setText(sclass.parser_str(shops_ps.getValue(), 1));
			shop_ps.setValue(sclass.parser_str(shops_ps.getValue(), 0));
			shoprus_ps.setValue(shoprus_ps.getValue());
			shoprus_ps_t.setText(shoprus_ps.getValue());
		}
		
		company_ps.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(company_ps.getValue().length() != 0) {
					plant_ps.setItems(qr._select_shop_ps("FL02_Plant"));
					company_ps_t.setText(company_ps.getValue());
					
				}
			}
		});
		
		plant_ps.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(plant_ps.getValue().length() != 0) {
					shops_ps.setItems(qr._select_shop_ps("FL03_Shop_s", "FL03_Shop_ENG", "FL02_Plant", plant_ps.getValue()));
					shop_ps.setItems(qr._select_shop_ps("FL03_Shop_s", "FL03_Shop_ENG", "FL02_Plant", plant_ps.getValue()));
					plant_ps_t.setText(plant_ps.getValue());
					
				}
			}
		});
		
		shops_ps.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(shops_ps.getValue().toString().length() != 0) {
					groups_ps.valueProperty().set(null);
					lines_ps.valueProperty().set(null);
					oss_ps.valueProperty().set(null);
					equips_ps.valueProperty().set(null);
					groups_ps.setItems(qr._select_shop_ps("FL04_Group_s","FL03_Shop_s", sclass.parser_str(shops_ps.getValue(), 0)));
					group_ps.setItems(qr._select_shop_ps("FL04_Group_ENG","FL03_Shop_s", sclass.parser_str(shops_ps.getValue(), 0)));
					grouprus_ps.setItems(qr._select_shop_ps("FL04_Group_RUS","FL03_Shop_s", sclass.parser_str(shops_ps.getValue(), 0)));
					shops_ps_t.setText(sclass.parser_str(shops_ps.getValue(), 0));
					shop_ps_t.setText(sclass.parser_str(shops_ps.getValue(), 1));
					shop_ps.setValue(sclass.parser_str(shops_ps.getValue(), 0));
					shoprus_ps.setValue(qr._select_shop_ps_str("FL03_Shop_RUS", "FL03_Shop_s", sclass.parser_str(shops_ps.getValue(), 0)));
					shoprus_ps_t.setText(shoprus_ps.getValue());
				}
			}
		});
		
		groups_ps.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(groups_ps.getValue().toString().length() != 0) {
					lines_ps.valueProperty().set(null);
					oss_ps.valueProperty().set(null);
					equips_ps.valueProperty().set(null);
					lines_ps.setItems(qr._select_shop_ps("FL05_Line_s","FL05_Line_ENG", "FL04_Group_s", groups_ps.getValue()));
					line_ps.setItems(qr._select_shop_ps("FL05_Line_s","FL05_Line_ENG", "FL04_Group_s", groups_ps.getValue()));
					linerus_ps.setItems(qr._select_shop_ps("FL05_Line_s","FL05_Line_RUS", "FL04_Group_s", groups_ps.getValue()));
					groups_ps_t.setText(groups_ps.getValue());
					group_ps.setValue(sclass.parser_str(groups_ps.getValue(), 0));
					group_ps_t.setText(group_ps.getValue());
					grouprus_ps.setValue(qr._select_shop_ps_str("FL04_Group_RUS","FL04_Group_s", sclass.parser_str(groups_ps.getValue(), 0)));
					grouprus_ps_t.setText(grouprus_ps.getValue());
				}
			}
		});
		lines_ps.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(lines_ps.getValue().toString().length() != 0) {
					try {
						oss_ps.valueProperty().set(null);
						equips_ps.valueProperty().set(null);
						oss_ps.setItems(qr._select_shop_ps("FL06_Station_s","FL06_Station_ENG", "FL05_Line_s", sclass.parser_str(lines_ps.getValue(), 0)));
						os_ps.setItems(qr._select_shop_ps("FL06_Station_s","FL06_Station_ENG", "FL05_Line_s", sclass.parser_str(lines_ps.getValue(), 0)));
						osrus_ps.setItems(qr._select_shop_ps("FL06_Station_s","FL05_Line_RUS", "FL05_Line_s", sclass.parser_str(lines_ps.getValue(), 0)));
						lines_ps_t.setText(sclass.parser_str(lines_ps.getValue(), 0));
						line_ps_t.setText(sclass.parser_str(lines_ps.getValue(), 1));
						line_ps.setValue(sclass.parser_str(lines_ps.getValue(), 1));
						linerus_ps.setValue(qr._select_shop_ps_str("FL05_Line_RUS", "FL05_Line_s", sclass.parser_str(lines_ps.getValue(), 0)));
						linerus_ps_t.setText(qr._select_shop_ps_str("FL05_Line_RUS", "FL05_Line_s", sclass.parser_str(lines_ps.getValue(), 0)));
					}
					catch (Exception e) {
						
					}
				}
			}
		});
		oss_ps.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(oss_ps.getValue().toString().length() != 0) {
					try {
						equips_ps.valueProperty().set(null);
						equips_ps.setItems(qr._select_shop_ps("FL07_Equipment_s","FL07_Equipment_ENG", "FL06_Station_s", sclass.parser_str(oss_ps.getValue(), 0)));
						equip_ps.setItems(qr._select_shop_ps("FL07_Equipment_s","FL07_Equipment_ENG", "FL06_Station_s", sclass.parser_str(oss_ps.getValue(), 0)));
						equiprus_ps.setItems(qr._select_shop_ps("FL07_Equipment_RUS", "FL06_Station_s", sclass.parser_str(oss_ps.getValue(), 0)));
						oss_ps_t.setText(sclass.parser_str(oss_ps.getValue(), 0));
						os_ps_t.setText(sclass.parser_str(oss_ps.getValue(), 1));
						os_ps.setValue(sclass.parser_str(oss_ps.getValue(), 1));
						osrus_ps.setValue(qr._select_shop_ps_str("FL06_Station_RUS", "FL06_Station_s", sclass.parser_str(oss_ps.getValue(), 0)));
						osrus_ps_t.setText(qr._select_shop_ps_str("FL06_Station_RUS", "FL06_Station_s", sclass.parser_str(oss_ps.getValue(), 0)));
					}
					catch (Exception e) {
						
					}
					
				}
			}
		});
		equips_ps.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				try {
					equips_ps_t.setText(sclass.parser_str(equips_ps.getValue(), 0));
					equip_ps_t.setText(sclass.parser_str(equips_ps.getValue(), 1));
					equip_ps.setValue(sclass.parser_str(equips_ps.getValue(), 1));
					equiprus_ps.setValue(qr._select_shop_ps_str("FL07_Equipment_RUS", "FL07_Equipment_s", sclass.parser_str(equips_ps.getValue(), 0)));
					equiprus_ps_t.setText(equiprus_ps.getValue());
				}
				catch (Exception e) {
					
				}
				//stsupplier_ps.setItems(qr._select_shop_ps("Station_Supplier"));
			}
		});
		shoprus_ps.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				shoprus_ps_t.setText(shoprus_ps.getValue());
			}
		});
		grouprus_ps.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				grouprus_ps_t.setText(grouprus_ps.getValue());
			}
		});
		linerus_ps.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				linerus_ps_t.setText(linerus_ps.getValue());
			}
		});
		osrus_ps.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				osrus_ps_t.setText(osrus_ps.getValue());
			}
		});
		equiprus_ps.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				equiprus_ps_t.setText(equiprus_ps.getValue());
			}
		});
		FileChooser fc = new FileChooser();
		fc.setTitle("Выберите файл:");
		exp_ps.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
			   fc.setInitialDirectory(new File(pathToPs_Upd));			   
			   fc.getExtensionFilters().addAll(
			        new ExtensionFilter(
			            "Excel Files",
			            "*.xlsx","*.xls"),
			        new ExtensionFilter(
			            "All Files", 
			            "*.*"));
			    //showing the file chooser
			    File phil = 
			        fc.showOpenDialog(
			            ps.stage);
			    
			    // checking that a file was
			    // chosen by the user
			    if (phil != null) {
			    	try {
				    	pathToPs_Upd =  phil.getParent();
				        manual_ps_t.setText(phil.getPath());
			    	}
			    	catch (Exception e) {
						
					}
			    }
			}
	   });
		stsupplier_ps.setItems(qr._select_shop_ps("EQ_Integrator"));
		stsupplier_ps.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				//stsupplier_ps.setValue(qr._select_shop_ps("Station_Supplier").toString());
				stsupplier_ps_t.setText(stsupplier_ps.getValue());
				//location_ps.setItems(qr._select_shop_ps("Location"));
			}
		});
		location_ps.setItems(qr._select_shop_ps("Site_Location"));
		location_ps.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				location_ps_t.setText(location_ps.getValue());
				//room_ps.setItems(qr._select_shop_ps("Room_category"));
			}
		});
		
		cham_ps.setItems(qr._select_shop_ps("Site_CHAMBER"));
		cham_ps.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				cham_ps_t.setText(cham_ps.getValue());
				//trcu_ps.setItems(qr._select_shop_ps("TR_CU"));
			}
		});
		trcu_ps.setItems(qr._select_shop_ps("TR_CU"));
		trcu_ps.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				trcu_ps_t.setText(trcu_ps.getValue());
				//trcul_ps.setItems(qr._select_shop_ps("TR_CU_Link"));
			}
		});
		trcul_ps.setItems(qr._select_shop_ps("TR_CU_Link"));
		trcul_ps.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				trcul_ps_t.setText(trcul_ps.getValue());
				//hazardous_ps.setItems(qr._select_shop_ps("Hazardous"));
			}
		});
		hazardous_ps.setItems(qr._select_shop_ps("Hazardous"));
		hazardous_ps.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				chk_btn();
			}
		});
		keyequip_ps.setItems(qr._select_shop_ps("Key_equipment"));
		keyequip_ps.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				keyequip_ps_t.setText(keyequip_ps.getValue());
				//type_ps.setItems(qr._select_shop_ps("Type"));
			}
		});
		type_ps.setItems(qr._select_shop_ps("EQ_Type"));
		type_ps.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				type_ps_t.setText(type_ps.getValue());
				//mtc_ps.setItems(qr._select_shop_ps("Main_Technical_Characteristic"));
			}
		});
		mtc_ps.setItems(qr._select_shop_ps("EQ_Technical_Characteristic"));
		mtc_ps.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				mtc_ps_t.setText(mtc_ps.getValue());
				//respons_ps.setItems(qr._select_shop_ps("Responsobility"));
			}
		});
		respons_ps.setItems(qr._select_shop_ps("Responsobility"));
		respons_ps.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				respons_ps_t.setText(respons_ps.getValue());
			}
		});
		
		//Проверяем заполненность полей
				company_ps_t.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				});
				plant_ps_t.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				}); 
				shops_ps_t.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				});
				groups_ps_t.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				});
				lines_ps_t.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				});
				oss_ps_t.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				});
				equips_ps_t.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				});
				shop_ps_t.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				});
				equip_ps_t.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				});
				
				manual_ps_t.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				}); 
				stsupplier_ps_t.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				}); 
				location_ps_t.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				});
				coord_ps_t.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				});
				alt_ps_t.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				}); 
				cham_ps_t.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				});
				trcu_ps_t.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				});
				trcul_ps_t.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				});
				keyequip_ps_t.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				});
				type_ps_t.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				});
				sn_ps_t.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				});
				manuf_ps_t.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				});
				mtc_ps_t.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				});
				respons_ps_t.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				});
				melec_ps_t.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				});
				mair_ps_t.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				});
				mwater_ps_t.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				});
				mcwater_ps_t.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				});
				mhwater_ps_t.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				}); 
				rowater_ps_t.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				});
				mgas_ps_t.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				});
				line_ps_t.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				});
				linerus_ps_t.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				});
				os_ps_t.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				}); 
				osrus_ps_t.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				});
				description_ps_t.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				});
				invnum_ps_t.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event arg0) {
						chk_btn();
					}
				});
				osnum_ps_t.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event arg0) {
						chk_btn();
					}
				});
		
		melec_ps_t.setText("-");
		mair_ps_t.setText("-");
		mwater_ps_t.setText("-");
		mcwater_ps_t.setText("-");
		mhwater_ps_t.setText("-");
		rowater_ps_t.setText("-");
		mgas_ps_t.setText("-");
		
		company_ps.getSelectionModel().select(ps._company_ps);
		plant_ps.getSelectionModel().select(ps._plant_ps);
		shops_ps.getSelectionModel().select(ps._shops_ps);
		groups_ps.getSelectionModel().select(ps._groups_ps);
		lines_ps.getSelectionModel().select(ps._lines_ps);
		oss_ps.getSelectionModel().select(ps._oss_ps);
		equips_ps.getSelectionModel().select(ps._equips_ps);
		shop_ps.getSelectionModel().select(ps._shop_ps);
		group_ps.getSelectionModel().select(ps._group_ps);
		line_ps.getSelectionModel().select(ps._line_ps);
		os_ps.getSelectionModel().select(ps._os_ps);
		equip_ps.getSelectionModel().select(ps._equip_ps);
		shoprus_ps.getSelectionModel().select(ps._shoprus_ps);
		grouprus_ps.getSelectionModel().select(ps._grouprus_ps);
		linerus_ps.getSelectionModel().select(ps._linerus_ps);
		osrus_ps.getSelectionModel().select(ps._osrus_ps);
		equiprus_ps.getSelectionModel().select(ps._equiprus_ps);
		stsupplier_ps.getSelectionModel().select(ps._stsupplier_ps);
		location_ps.getSelectionModel().select(ps._location_ps); 
		cham_ps.getSelectionModel().select(ps._cham_ps);
		trcu_ps.getSelectionModel().select(ps._trcu_ps); 
		trcul_ps.getSelectionModel().select(ps._trcul_ps);
		hazardous_ps.getSelectionModel().select(ps._hazardous_ps);
		keyequip_ps.getSelectionModel().select(ps._keyequip_ps);
		type_ps.getSelectionModel().select(ps._type_ps); 
		mtc_ps.getSelectionModel().select(ps._mtc_ps);
		respons_ps.getSelectionModel().select(ps._respons_ps);
		groupotv_ps.getSelectionModel().select(ps._gruopotv_ps);
		costcenter_ps.getSelectionModel().select(ps._cost_ps);
		company_ps_t.setText(ps._company_ps);
		plant_ps_t.setText(ps._plant_ps);
		shops_ps_t.setText(ps._shops_ps);
		groups_ps_t.setText(ps._groups_ps);
		lines_ps_t.setText(ps._lines_ps);
		oss_ps_t.setText(ps._oss_ps);
		equips_ps_t.setText(ps._equips_ps);
		shop_ps_t.setText(ps._shop_ps);
		equip_ps_t.setText(ps._equip_ps);
		manual_ps_t.setText(ps._manual_ps);
		invnum_ps_t.setText(ps._invnum_ps);
		osnum_ps_t.setText(ps._numos_ps);
		startdate_ps.setValue(fx_dp.fromString(ps._startdate_ps));
		costcenter_ps.getSelectionModel().select(ps._cost_ps);
		stsupplier_ps_t.setText(ps._stsupplier_ps); 
		location_ps_t.setText(ps._location_ps);
		coord_ps_t.setText(ps._coord_ps);
		alt_ps_t.setText(ps._alt_ps);
		cham_ps_t.setText(ps._cham_ps);
		trcu_ps_t.setText(ps._trcu_ps);
		trcul_ps_t.setText(ps._trcul_ps);
		keyequip_ps_t.setText(ps._keyequip_ps);
		type_ps_t.setText(ps._type_ps);
		sn_ps_t.setText(ps._sn_ps);
		manuf_ps_t.setText(ps._manuf_ps);
		mtc_ps_t.setText(ps._mtc_ps);
		respons_ps_t.setText(ps._respons_ps);
		melec_ps_t.setText(ps._melec_ps);
		mair_ps_t.setText(ps._mair_ps);
		mwater_ps_t.setText(ps._mwater_ps);
		mcwater_ps_t.setText(ps._mcwater_ps);
		mhwater_ps_t.setText(ps._mhwater_ps);
		rowater_ps_t.setText(ps._rowater_ps);
		mgas_ps_t.setText(ps._mgas_ps);
		line_ps_t.setText(ps._line_ps);
		linerus_ps_t.setText(ps._linerus_ps);
		os_ps_t.setText(ps._os_ps);
		osrus_ps_t.setText(ps._osrus_ps);
		description_ps_t.setText(ps._description_ps);
		group_ps_t.setText(ps._group_ps);
		grouprus_ps.getSelectionModel().select(ps._grouprus_ps);
		grouprus_ps_t.setText(ps._grouprus_ps);
		shoprus_ps_t.setText(ps._shoprus_ps);
		equiprus_ps_t.setText(ps._equiprus_ps);
		id_ps.setText(ps._id_ps);
		
		add_ps.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				//if(apwr_controller.SHOP_NAME.equals("W"))
				//	equip_label = oss_ps_t.getText()+"."+sub_number_t.getText()+"-"+equips_ps_t.getText();
				//else
					equip_label = oss_ps_t.getText()+"."+equips_ps_t.getText();
				
				qr._update_rec_ps(ps._id_ps, company_ps_t.getText(), plant_ps_t.getText(), shops_ps_t.getText(), groups_ps_t.getText(), lines_ps_t.getText(), oss_ps_t.getText(), equips_ps_t.getText(), shop_ps_t.getText(), group_ps_t.getText(), line_ps_t.getText(), os_ps_t.getText(), equip_ps_t.getText(), shoprus_ps_t.getText(), grouprus_ps_t.getText(), linerus_ps_t.getText(), osrus_ps_t.getText(), equiprus_ps_t.getText(), description_ps_t.getText(), equip_label, line_ps_t.getText()+"."+oss_ps_t.getText(), manual_ps_t.getText(), groupotv_ps.getValue(), invnum_ps_t.getText(), osnum_ps_t.getText(), startdate_ps.getValue(), costcenter_ps.getValue(), location_ps_t.getText(), cham_ps_t.getText(), coord_ps_t.getText(), alt_ps_t.getText(), trcu_ps_t.getText(), trcul_ps_t.getText(), hazardous_ps.getValue(), keyequip_ps_t.getText(), stsupplier_ps_t.getText(), manuf_ps_t.getText(), type_ps_t.getText(), sn_ps_t.getText(), mtc_ps_t.getText(), respons_ps_t.getText(), melec_ps_t.getText(), mair_ps_t.getText(), mwater_ps_t.getText(), mcwater_ps_t.getText(), mhwater_ps_t.getText(), rowater_ps_t.getText(), mgas_ps_t.getText());
				
				qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + " - Обновил запись № = " + ps._id_ps + " в таблице Plant Structure");
				//апдейтим таблицу
				if(ps.flag_ps == 0)
					ps._table_update_ps.addAll(qr._select_data_ps());
				if(ps.flag_ps == 1)
					ps._table_update_ps.addAll(qr._select_data_filter_ps(ps._filter_shop));
				if(ps.flag_ps == 2)
					ps._table_update_ps.addAll(qr._select_data_filter_ps(ps._filter_shop, ps._filter_group));
				if(ps.flag_ps == 3)
					ps._table_update_ps.addAll(qr._select_data_filter_ps(ps._filter_shop, ps._filter_group, ps._filter_line));
				if(ps.flag_ps == 4)
					ps._table_update_ps.addAll(qr._select_data_filter_ps(ps._filter_shop, ps._filter_group, ps._filter_line, ps._filter_os));
				if(ps.flag_ps == 5)
					ps._table_update_ps.addAll(qr._select_data_filter_ps(ps._filter_shop, ps._filter_group, ps._filter_line, ps._filter_os, ps._filter_equip));
//				stage = (Stage) add_ps.getScene().getWindow();
//				stage.close();
			}
		});
		
		prev_id = ps._id_ps;
		
		btn_left.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				String _sql_rez = qr._select_for_update_ps(qr._select_data_prew(prev_id, "hmmr_plant_structure"));
				
				fill_fields(_sql_rez);
				
				FillUpdForm();
				prev_id = qr._select_data_prew(prev_id, "hmmr_plant_structure");
				ps._id_ps = prev_id;
				id_ps.setText(ps._id_ps);
			}
		});
		next_id = ps._id_ps;
		btn_right.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				String _sql_rez = qr._select_for_update_ps(qr._select_data_next(next_id, "hmmr_plant_structure"));
				fill_fields(_sql_rez);
				
				FillUpdForm();
				next_id = qr._select_data_next(next_id, "hmmr_plant_structure");
				ps._id_ps = next_id;
				id_ps.setText(ps._id_ps);
			}
		});
		cancel_ps.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				stage = (Stage) add_ps.getScene().getWindow();
				stage.close();
			}
		});
		exp_ps.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				FileChooser fc = new FileChooser();
			    fc.setTitle("Get Text");
			    fc.getExtensionFilters().addAll(
			        new ExtensionFilter(
			            "Excel Files", 
			            "*.xlsx"),
			        new ExtensionFilter(
			            "All Files", 
			            "*.*"));
			    
			    //showing the file chooser
			    File phil = 
			        fc.showOpenDialog(
			            ps.stage);
			    
			    // checking that a file was
			    // chosen by the user
			    if (phil != null) 
			    	 manual_ps_t.setText(phil.getPath());
			}
		});
	}
	@SuppressWarnings("static-access")
	private void FillUpdForm()
	{
		melec_ps_t.setText("-");
		mair_ps_t.setText("-");
		mwater_ps_t.setText("-");
		mcwater_ps_t.setText("-");
		mhwater_ps_t.setText("-");
		rowater_ps_t.setText("-");
		mgas_ps_t.setText("-");
		
		company_ps.getSelectionModel().select(ps._company_ps);
		plant_ps.getSelectionModel().select(ps._plant_ps);
		shops_ps.getSelectionModel().select(ps._shops_ps);
		groups_ps.getSelectionModel().select(ps._groups_ps);
		lines_ps.getSelectionModel().select(ps._lines_ps);
		oss_ps.getSelectionModel().select(ps._oss_ps);
		equips_ps.getSelectionModel().select(ps._equips_ps);
		shop_ps.getSelectionModel().select(ps._shop_ps);
		group_ps.getSelectionModel().select(ps._group_ps);
		line_ps.getSelectionModel().select(ps._line_ps);
		os_ps.getSelectionModel().select(ps._os_ps);
		equip_ps.getSelectionModel().select(ps._equip_ps);
		shoprus_ps.getSelectionModel().select(ps._shoprus_ps);
		grouprus_ps.getSelectionModel().select(ps._grouprus_ps);
		linerus_ps.getSelectionModel().select(ps._linerus_ps);
		osrus_ps.getSelectionModel().select(ps._osrus_ps);
		equiprus_ps.getSelectionModel().select(ps._equiprus_ps);
		stsupplier_ps.getSelectionModel().select(ps._stsupplier_ps);
		location_ps.getSelectionModel().select(ps._location_ps); 
		cham_ps.getSelectionModel().select(ps._cham_ps);
		trcu_ps.getSelectionModel().select(ps._trcu_ps); 
		trcul_ps.getSelectionModel().select(ps._trcul_ps);
		hazardous_ps.getSelectionModel().select(ps._hazardous_ps);
		keyequip_ps.getSelectionModel().select(ps._keyequip_ps);
		type_ps.getSelectionModel().select(ps._type_ps); 
		mtc_ps.getSelectionModel().select(ps._mtc_ps);
		respons_ps.getSelectionModel().select(ps._respons_ps);
		groupotv_ps.getSelectionModel().select(ps._gruopotv_ps);
		costcenter_ps.getSelectionModel().select(ps._cost_ps);
		company_ps_t.setText(ps._company_ps);
		plant_ps_t.setText(ps._plant_ps);
		shops_ps_t.setText(ps._shops_ps);
		groups_ps_t.setText(ps._groups_ps);
		lines_ps_t.setText(ps._lines_ps);
		oss_ps_t.setText(ps._oss_ps);
		equips_ps_t.setText(ps._equips_ps);
		shop_ps_t.setText(ps._shop_ps);
		equip_ps_t.setText(ps._equip_ps);
		manual_ps_t.setText(ps._manual_ps);
		invnum_ps_t.setText(ps._invnum_ps);
		osnum_ps_t.setText(ps._numos_ps);
		startdate_ps.setValue(fx_dp.fromString(ps._startdate_ps));
		costcenter_ps.getSelectionModel().select(ps._cost_ps);
		stsupplier_ps_t.setText(ps._stsupplier_ps); 
		location_ps_t.setText(ps._location_ps);
		coord_ps_t.setText(ps._coord_ps);
		alt_ps_t.setText(ps._alt_ps);
		cham_ps_t.setText(ps._cham_ps);
		trcu_ps_t.setText(ps._trcu_ps);
		trcul_ps_t.setText(ps._trcul_ps);
		keyequip_ps_t.setText(ps._keyequip_ps);
		type_ps_t.setText(ps._type_ps);
		sn_ps_t.setText(ps._sn_ps);
		manuf_ps_t.setText(ps._manuf_ps);
		mtc_ps_t.setText(ps._mtc_ps);
		respons_ps_t.setText(ps._respons_ps);
		melec_ps_t.setText(ps._melec_ps);
		mair_ps_t.setText(ps._mair_ps);
		mwater_ps_t.setText(ps._mwater_ps);
		mcwater_ps_t.setText(ps._mcwater_ps);
		mhwater_ps_t.setText(ps._mhwater_ps);
		rowater_ps_t.setText(ps._rowater_ps);
		mgas_ps_t.setText(ps._mgas_ps);
		line_ps_t.setText(ps._line_ps);
		linerus_ps_t.setText(ps._linerus_ps);
		osnum_ps_t.setText(ps._numos_ps);
		osrus_ps_t.setText(ps._osrus_ps);
		description_ps_t.setText(ps._description_ps);
		group_ps_t.setText(ps._group_ps);
		grouprus_ps.getSelectionModel().select(ps._grouprus_ps);
		grouprus_ps_t.setText(ps._grouprus_ps);
		shoprus_ps_t.setText(ps._shoprus_ps);
		equiprus_ps_t.setText(ps._equiprus_ps);
		//id_ps.setText(ps._id_ps);
	}
	
	@SuppressWarnings("static-access")
	private void fill_fields(String _sql_rez)
	{
		ps._num_ps = sclass.parser_str_str_str(_sql_rez, 0);
		ps._company_ps = sclass.parser_str_str_str(_sql_rez, 1);
		ps._plant_ps = sclass.parser_str_str_str(_sql_rez, 2);
		ps._shops_ps = sclass.parser_str_str_str(_sql_rez, 3);
		ps._groups_ps = sclass.parser_str_str_str(_sql_rez, 4);
		ps._lines_ps = sclass.parser_str_str_str(_sql_rez, 5);
		ps._oss_ps = sclass.parser_str_str_str(_sql_rez, 6);
		ps._equips_ps = sclass.parser_str_str_str(_sql_rez, 7);
		ps._shop_ps = sclass.parser_str_str_str(_sql_rez, 8);
		ps._group_ps = sclass.parser_str_str_str(_sql_rez, 9);
		ps._line_ps = sclass.parser_str_str_str(_sql_rez, 10);
		ps._os_ps = sclass.parser_str_str_str(_sql_rez, 11);
		ps._equip_ps = sclass.parser_str_str_str(_sql_rez, 12);
		ps._shoprus_ps = sclass.parser_str_str_str(_sql_rez, 13);
		ps._grouprus_ps = sclass.parser_str_str_str(_sql_rez, 14);
		ps._linerus_ps = sclass.parser_str_str_str(_sql_rez, 15);
		ps._osrus_ps = sclass.parser_str_str_str(_sql_rez, 16);
		ps._equiprus_ps = sclass.parser_str_str_str(_sql_rez, 17);
		ps._description_ps = sclass.parser_str_str_str(_sql_rez, 18);
		ps._manual_ps = sclass.parser_str_str_str(_sql_rez, 19);
		ps._gruopotv_ps = sclass.parser_str_str_str(_sql_rez, 20);
		ps._invnum_ps = sclass.parser_str_str_str(_sql_rez, 21);
		ps._numos_ps = sclass.parser_str_str_str(_sql_rez, 22);
		ps._startdate_ps = sclass.parser_str_str_str(_sql_rez, 23);
		ps._cost_ps = sclass.parser_str_str_str(_sql_rez, 24);
		ps._location_ps = sclass.parser_str_str_str(_sql_rez, 25);
		ps._cham_ps = sclass.parser_str_str_str(_sql_rez, 26);
		ps._coord_ps = sclass.parser_str_str_str(_sql_rez, 27);
		ps._alt_ps = sclass.parser_str_str_str(_sql_rez, 28);
		ps._trcu_ps = sclass.parser_str_str_str(_sql_rez, 29);
		ps._trcul_ps = sclass.parser_str_str_str(_sql_rez, 30);
		ps._hazardous_ps = sclass.parser_str_str_str(_sql_rez, 31);
		ps._keyequip_ps = sclass.parser_str_str_str(_sql_rez, 32);
		ps._stsupplier_ps = sclass.parser_str_str_str(_sql_rez, 33);
		ps._manuf_ps = sclass.parser_str_str_str(_sql_rez, 34);
		ps._type_ps = sclass.parser_str_str_str(_sql_rez, 35);
		ps._sn_ps = sclass.parser_str_str_str(_sql_rez, 36);
		ps._mtc_ps = sclass.parser_str_str_str(_sql_rez, 37);
		ps._respons_ps = sclass.parser_str_str_str(_sql_rez, 38);
		ps._melec_ps = sclass.parser_str_str_str(_sql_rez, 39);
		ps._mair_ps = sclass.parser_str_str_str(_sql_rez, 40);
		ps._mwater_ps = sclass.parser_str_str_str(_sql_rez, 41);
		ps._mcwater_ps = sclass.parser_str_str_str(_sql_rez, 42);
		ps._mhwater_ps = sclass.parser_str_str_str(_sql_rez, 43);
		ps._rowater_ps = sclass.parser_str_str_str(_sql_rez, 44);
		ps._mgas_ps = sclass.parser_str_str_str(_sql_rez, 45);
	}
	
	private void lang_fun(String loc1, String loc2)
	{
		ResourceBundle lngBndl = ResourceBundle
	            .getBundle("bundles.LangBundle", new Locale(loc1, loc2));
		
		head_ps_upd.setText(lngBndl.getString("head_ps_upd"));
		lbl_company_ps.setText(lngBndl.getString("col_company_ps")+":");
		lbl_plant_ps.setText(lngBndl.getString("col_plant_ps")+":");
		lbl_shops_ps.setText(lngBndl.getString("col_shop_pm")+","+lngBndl.getString("lbl_short")+":");
		lbl_groups_ps.setText(lngBndl.getString("col_group_pm")+","+lngBndl.getString("lbl_short")+":");
		lbl_lines_ps.setText(lngBndl.getString("col_lm_pm")+","+lngBndl.getString("lbl_short")+":");
		lbl_oss_ps.setText(lngBndl.getString("col_os_pm")+","+lngBndl.getString("lbl_short")+":");
		lbl_equips_ps.setText(lngBndl.getString("col_equip_pm")+","+lngBndl.getString("lbl_short")+":");
		lbl_shop_ps.setText(lngBndl.getString("col_shop_pm")+":");
		lbl_line_ps.setText(lngBndl.getString("col_lm_pm")+":");
		lbl_linerus_ps.setText(lngBndl.getString("col_lm_pm")+","+lngBndl.getString("lbl_rus")+":");
		lbl_os_ps.setText(lngBndl.getString("col_os_pm")+":");
		lbl_osrus_ps.setText(lngBndl.getString("col_os_pm")+","+lngBndl.getString("lbl_rus")+":");
		lbl_equip_ps.setText(lngBndl.getString("col_equip_pm")+":");
		
		lbl_description_ps.setText(lngBndl.getString("desc_ap")+":");
		
		lbl_manual_ps.setText(lngBndl.getString("col_sdoc_inst")+":");
		lbl_stsupplier_ps.setText(lngBndl.getString("col_stsupplier_ps")+":");
		lbl_location_ps.setText(lngBndl.getString("col_location_ps")+":");
		lbl_coord_ps.setText(lngBndl.getString("col_coord_ps")+":");
		lbl_alt_ps.setText(lngBndl.getString("col_alt_ps")+":");
		lbl_cham_ps.setText(lngBndl.getString("col_cham_ps")+":");
		lbl_trcu_ps.setText(lngBndl.getString("col_trcu_ps")+":");
		lbl_trcul_ps.setText(lngBndl.getString("col_trcul_ps")+":");
		lbl_hazardous_ps.setText(lngBndl.getString("col_hazardous_ps")+":");
		lbl_keyequip_ps.setText(lngBndl.getString("col_keyequip_ps")+":");
		lbl_type_ps.setText(lngBndl.getString("lbl_type_ap")+":");
		lbl_sn_ps.setText(lngBndl.getString("col_sn_ps")+":");
		lbl_manuf_ps.setText(lngBndl.getString("col_manuf_ps")+":");
		lbl_mtc_ps.setText(lngBndl.getString("col_mtc_ps")+":");
		lbl_respons_ps.setText(lngBndl.getString("lbl_otv_ap")+":");
		lbl_melec_ps.setText(lngBndl.getString("col_melec_ps")+":");
		lbl_mair_ps.setText(lngBndl.getString("col_mair_ps")+":");
		lbl_mwater_ps.setText(lngBndl.getString("col_mwater_ps")+":");
		lbl_mcwater_ps.setText(lngBndl.getString("col_mcwater_ps")+":");
		lbl_mhwater_ps.setText(lngBndl.getString("col_mhwater_ps")+":");
		lbl_rowater_ps.setText(lngBndl.getString("col_rowater_ps")+":");
		lbl_mgas_ps.setText(lngBndl.getString("col_mgas_ps")+":");
		
		lbl_group_ps.setText(lngBndl.getString("lbl_group")+":");
		lbl_shoprus_ps.setText(lngBndl.getString("col_shop_pm")+","+lngBndl.getString("lbl_rus")+":");
		lbl_grouprus_ps.setText(lngBndl.getString("lbl_group")+","+lngBndl.getString("lbl_rus")+":");
		lbl_equiprus_ps.setText(lngBndl.getString("col_equip_pm")+","+lngBndl.getString("lbl_rus")+":");
		lbl_groupotv_ps.setText(lngBndl.getString("col_groupotv_ps")+":");
		lbl_invnum_ps.setText(lngBndl.getString("col_invnum_ps")+" №"+":");
		lbl_osnum_ps.setText(lngBndl.getString("col_numos_ps")+":");
		lbl_startdate_ps.setText(lngBndl.getString("col_startdate_ps")+":");
		lbl_costcenter_ps.setText(lngBndl.getString("col_cost_ps")+":");
		
		add_ps.setText(lngBndl.getString("lbl_apply"));
		cancel_ps.setText(lngBndl.getString("cancel_tsk"));
		exp_ps.setText(lngBndl.getString("sdoc_inst"));
	}
	
	private void chk_btn()
	{
		try {
			if(hazardous_ps.getValue().length() != 0 && company_ps_t.getText().length() != 0 &&
					plant_ps_t.getText().length() != 0 && shops_ps_t.getText().length() != 0 && groups_ps_t.getText().length() != 0 && lines_ps_t.getText().length() != 0 &&
					oss_ps_t.getText().length() != 0 && equips_ps_t.getText().length() != 0 && shop_ps_t.getText().length() != 0 && equip_ps_t.getText().length() != 0 &&
					manual_ps_t.getText().length() != 0 && stsupplier_ps_t.getText().length() != 0 &&
					location_ps_t.getText().length() != 0 && coord_ps_t.getText().length() != 0 && alt_ps_t.getText().length() != 0 &&
					cham_ps_t.getText().length() != 0 && trcu_ps_t.getText().length() != 0 && trcul_ps_t.getText().length() != 0 && keyequip_ps_t.getText().length() != 0 &&
					type_ps_t.getText().length() != 0 && sn_ps_t.getText().length() != 0 && manuf_ps_t.getText().length() != 0 && mtc_ps_t.getText().length() != 0 &&
					respons_ps_t.getText().length() != 0 && melec_ps_t.getText().length() != 0 && mair_ps_t.getText().length() != 0 && mwater_ps_t.getText().length() != 0 &&
					mcwater_ps_t.getText().length() != 0 && mhwater_ps_t.getText().length() != 0 && rowater_ps_t.getText().length() != 0 && mgas_ps_t.getText().length() != 0 &&
					line_ps_t.getText().length() != 0 && linerus_ps_t.getText().length() != 0 && os_ps_t.getText().length() != 0 && osrus_ps_t.getText().length() != 0 && description_ps_t.getText().length() != 0
					&& invnum_ps_t.getText().length() != 0 && osnum_ps_t.getText().length() != 0)
				add_ps.setDisable(false);
			else
				add_ps.setDisable(true);
			}
			catch (Exception e) {
				
			}
	}
}
