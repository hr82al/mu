package ru.haval.action;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import  ru.haval.application.conn_connector;
import  ru.haval.db._query;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import ru.haval.share_class.s_class;

public class AddRec_PartS_Controller {
	
	@FXML
	ComboBox<String> num_parts, shop_parts, group_parts, line_parts, os_parts, equip_parts;
	
	@FXML
	TextField draw_parts, pos_draw_parts, qty_parts;
	
	@FXML
	JFXRadioButton knby_parts, knbn_parts, kyby_parts, kybn_parts;
	
	@FXML
	JFXButton btn_accept, btn_cancel;
	
	@FXML
	Label lbl_title_addparts;
	
	String knby_parts_add = "0", knbn_parts_add = "0", kyby_parts_add = "0", kybn_parts_add = "0";
	
	_query qr = new _query();
	s_class scl = new s_class();
	PartSpec_Controller psc = new PartSpec_Controller();
	Tooltip tip;
	Stage stage;
	
	@SuppressWarnings("static-access")
	public void initialize()
	{
		scl._style(btn_accept);
		scl._style(btn_cancel);
		
		btn_accept.setDisable(true);
		
		num_parts.setItems(qr._select_num());
		
		shop_parts.setItems(qr._select_shop_pm());
		shop_parts.setValue(apwr_controller.SHOP_NAME);
		
		if(shop_parts.getValue().toString().length() != 0)
			group_parts.setItems(qr._select_group_pm(scl.parser_str(shop_parts.getValue(), 0)));
		
		try {
			shop_parts.getSelectionModel().selectedItemProperty().addListener(new  ChangeListener<String>() {
	
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if(shop_parts.getValue().toString().length() != 0) {
						group_parts.valueProperty().set(null);
						line_parts.valueProperty().set(null);
						os_parts.valueProperty().set(null);
						equip_parts.valueProperty().set(null);
						
						group_parts.setItems(qr._select_group_pm(scl.parser_str(shop_parts.getValue(), 0)));
					}
				}
			});
		} catch (Exception e) {
			
		}
		
		group_parts.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				
				try {
					if(group_parts.getValue().toString().length() != 0) {
						line_parts.valueProperty().set(null);
						os_parts.valueProperty().set(null);
						equip_parts.valueProperty().set(null);
						
						line_parts.setItems(qr._select_lm_pm(scl.parser_str(group_parts.getValue(), 0)));
					}
					
					} catch (Exception e) {
						
					}
			}
		});
		group_parts.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip = new Tooltip(group_parts.getValue());
				Point2D p = group_parts.localToScreen(group_parts.getLayoutBounds().getMaxX(), group_parts.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(group_parts, p.getX(), p.getY());
			}
		});
		group_parts.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip.hide();
			}
		});
		
		line_parts.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				try {
					if(line_parts.getValue().toString().length() != 0) {
						os_parts.valueProperty().set(null);
						equip_parts.valueProperty().set(null);
						
						os_parts.setItems(qr._select_os_pm(scl.parser_str(group_parts.getValue(), 0), scl.parser_str(line_parts.getValue(), 0)));
					}
					} catch (Exception e) {
						
					}
			}
		});
		line_parts.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip = new Tooltip(line_parts.getValue());
				Point2D p = line_parts.localToScreen(line_parts.getLayoutBounds().getMaxX(), line_parts.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(line_parts, p.getX(), p.getY());
			}
		});
		line_parts.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip.hide();
			}
		});
			
		
		os_parts.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
	
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				try {
					if(os_parts.getValue().toString().length() != 0) {
						equip_parts.valueProperty().set(null);
						
						equip_parts.setItems(qr._select_equip_pm(scl.parser_str(os_parts.getValue(), 0), scl.parser_str(group_parts.getValue(), 0), scl.parser_str(line_parts.getValue(), 0)));
					}
					} catch (Exception e) {
					}
			}
		});
		os_parts.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip = new Tooltip(os_parts.getValue());
				Point2D p = os_parts.localToScreen(os_parts.getLayoutBounds().getMaxX(), os_parts.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(os_parts, p.getX(), p.getY());
			}
		});
		os_parts.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip.hide();
			}
		});
		
		equip_parts.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip = new Tooltip(equip_parts.getValue());
				Point2D p = equip_parts.localToScreen(equip_parts.getLayoutBounds().getMaxX(), equip_parts.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(equip_parts, p.getX(), p.getY());
			}
		});
		equip_parts.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip.hide();
			}
		});
		
		knby_parts.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				knby_parts.setSelected(true);
				knbn_parts.setSelected(false);
				kyby_parts.setSelected(false);
				kybn_parts.setSelected(false);
				knby_parts_add = "1";
				knbn_parts_add = "0";
				kyby_parts_add = "0";
				kybn_parts_add = "0";
			}
		});
		knbn_parts.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				knby_parts.setSelected(false);
				knbn_parts.setSelected(true);
				kyby_parts.setSelected(false);
				kybn_parts.setSelected(false);
				knby_parts_add = "0";
				knbn_parts_add = "1";
				kyby_parts_add = "0";
				kybn_parts_add = "0";
			}
		});
		kyby_parts.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				kyby_parts.setSelected(true);
				kybn_parts.setSelected(false);
				knby_parts.setSelected(false);
				knbn_parts.setSelected(false);
				knby_parts_add = "0";
				knbn_parts_add = "0";
				kyby_parts_add = "1";
				kybn_parts_add = "0";
			}
		});
		kybn_parts.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				kyby_parts.setSelected(false);
				kybn_parts.setSelected(true);
				knby_parts.setSelected(false);
				knbn_parts.setSelected(false);
				knby_parts_add = "0";
				knbn_parts_add = "0";
				kyby_parts_add = "0";
				kybn_parts_add = "1";
			}
		});
		
		if(!psc._flag_window_parts)
		{
			String _sql_rez = qr._select_for_update_parts(psc._id_parts);
			
			if(scl.parser_str_str_str(_sql_rez, 0).equals("0")) {
				num_parts.getSelectionModel().select("Введите номер");
				num_parts.setDisable(false);
			}
			else	
				num_parts.setDisable(true);
			
			num_parts.getSelectionModel().select(qr._select_matnum(scl.parser_str_str_str(_sql_rez, 0), "HMMR_Material_Num", "hmmr_sp_db"));
			
			shop_parts.getSelectionModel().select(scl.parser_str_str(qr._select_fillpm_equip(scl.parser_str_str_str(_sql_rez, 1), "hmmr_parts_spec"), 0));
			group_parts.getSelectionModel().select(scl.parser_str_str(qr._select_fillpm_equip(scl.parser_str_str_str(_sql_rez, 1), "hmmr_parts_spec"), 1));
			line_parts.getSelectionModel().select(scl.parser_str_str(qr._select_fillpm_equip(scl.parser_str_str_str(_sql_rez, 1), "hmmr_parts_spec"), 2));
			os_parts.getSelectionModel().select(scl.parser_str_str(qr._select_fillpm_equip(scl.parser_str_str_str(_sql_rez, 1), "hmmr_parts_spec"), 3));
			equip_parts.getSelectionModel().select(scl.parser_str_str(qr._select_fillpm_equip(scl.parser_str_str_str(_sql_rez, 1), "hmmr_parts_spec"), 4));
			draw_parts.setText(scl.parser_str_str_str(_sql_rez, 2));
			pos_draw_parts.setText(scl.parser_str_str_str(_sql_rez, 3));
			if(Integer.parseInt(scl.parser_str_str_str(_sql_rez, 4)) == 1)
				knby_parts.setSelected(true);
			if(Integer.parseInt(scl.parser_str_str_str(_sql_rez, 5)) == 1)
				knbn_parts.setSelected(true);
			if(Integer.parseInt(scl.parser_str_str_str(_sql_rez, 6)) == 1)
				kyby_parts.setSelected(true);
			if(Integer.parseInt(scl.parser_str_str_str(_sql_rez, 7)) == 1)
				kybn_parts.setSelected(true);
			
			lbl_title_addparts.setText("Обновить запись Parts Specification");
		}
		else
			lbl_title_addparts.setText("Добавить запись к Parts Specification");
		
		shop_parts.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				chk_btn();
			}
		});
		group_parts.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				chk_btn();
			}
		});
		line_parts.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				chk_btn();
			}
		});
		os_parts.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				chk_btn();
			}
		});
		equip_parts.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				chk_btn();
			}
		});
		draw_parts.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		pos_draw_parts.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		/*knby_parts.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		knbn_parts.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		kyby_parts.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		kybn_parts.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});*/
		num_parts.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				chk_btn();
			}
		});
		
		btn_accept.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				
				String eq_id = qr._select_data_filter_ps_id(scl.parser_str(shop_parts.getValue(), 0), scl.parser_str(group_parts.getValue(), 0), scl.parser_str(line_parts.getValue(), 0), scl.parser_str(os_parts.getValue(), 0), scl.parser_str(equip_parts.getValue(), 0));
				
				if(psc._flag_window_parts) {
					qr._insert_parts(eq_id, draw_parts.getText(), pos_draw_parts.getText(), knby_parts_add, knbn_parts_add, kyby_parts_add, kybn_parts_add, qr._select_matnum_id(scl.parser_str(num_parts.getValue(), 0)));
					qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + " - Создал запись № = " + qr._select_last_id("hmmr_parts_spec") + " в Parts Specification");
				}
				else
				{
					qr._update_parts(psc._id_parts, eq_id, draw_parts.getText(), pos_draw_parts.getText(), knby_parts_add, knbn_parts_add, kyby_parts_add, kybn_parts_add, qr._select_matnum_id(scl.parser_str(num_parts.getValue(), 0)));
					qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + " - Обновил запись № = " + psc._id_parts + " в Parts Specification");
				}
					psc._table_update_parts.addAll(qr._select_data_parts());
					
					stage = (Stage) btn_accept.getScene().getWindow();
					stage.close();
				
				
			}
		});
		btn_cancel.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				stage = (Stage) btn_cancel.getScene().getWindow();
				stage.close();
			}
		});
	}
	
	void chk_btn()
	{ 
		try { 
			if(num_parts.getValue().length() != 0 && shop_parts.getValue().length() != 0 && group_parts.getValue().length() != 0 && line_parts.getValue().length() != 0 && 
					os_parts.getValue().length() != 0 && equip_parts.getValue().length() != 0 && draw_parts.getText().length() != 0 && pos_draw_parts.getText().length() != 0 && 
					knby_parts.getText().length() != 0 && knbn_parts.getText().length() != 0 && kyby_parts.getText().length() != 0 && kybn_parts.getText().length() != 0 && 
					!num_parts.getValue().equals("Введите номер"))
				btn_accept.setDisable(false);
			else
				btn_accept.setDisable(true);
		}
		catch (Exception e) {
			
		}
	}

}
