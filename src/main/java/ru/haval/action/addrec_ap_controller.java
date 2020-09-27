package ru.haval.action;

import java.util.Locale;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import  ru.haval.application.conn_connector;
import  ru.haval.db._query;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import ru.haval.share_class.s_class;

public class addrec_ap_controller {
	
	@FXML
	private ComboBox<String> shop_tsk, lm_tsk, os_tsk, equip_tsk, oft_tsk, otv_tsk,group_tsk, prior_ap, list_at_ap;
	
	@FXML
	TextArea description_tsk;
	
	@FXML
	DatePicker edate_tsk;
	
	@FXML
	Button tsk_ap, cm_ap;
	
	@FXML
	Label err_msg, lbl_create_tsk_ap, lbl_type_ap, lbl_desc_ap, lbl_dd_ap, lbl_shop_ap, lbl_group_ap, lbl_lm_ap, lbl_os_ap, lbl_equip_ap, lbl_oft_ap, lbl_oft_ap1, 
		lbl_otv_ap, lbl_otv_ap1, lbl_tsk_maker_ap, lbl_prior, lbl_at_ap;
	
	@FXML
	JFXButton add_tsk, cancel_tsk; 
	
	@FXML
	TextField tsk_maker_ap;
	
	_query qr = new _query();
	s_class sclass = new s_class();
	apwr_controller pic = new apwr_controller();
	private Stage stage;
	Tooltip tip;
	String type_tsk = "Nothing";
	boolean _flag_bt = false; //проверяем нажата ли одна из кнопок - TSK или CN
	int OtId = 0;
	
	@SuppressWarnings("static-access")
	@FXML
	public void initialize()
	{
		if(conn_connector.LANG_ID == 1)
			lang_fun("en", "EN");
		if(conn_connector.LANG_ID == 0)
			lang_fun("ru", "RU");
		if(conn_connector.LANG_ID == 2)
			lang_fun("zh", "CN");
		if(conn_connector.LANG_ID == -1)
			lang_fun("ru", "RU");
				
		add_tsk.setDisable(true);
		
		tsk_maker_ap.setText(sclass.parser_str(qr._select_user(conn_connector.USER_ID), 1));
		
		prior_ap.setItems(qr._select_prior());
		
		
		list_at_ap.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				if(!type_tsk.equals("Nothing")) {
					if(type_tsk.equals("TSK"))
						OtId = 3;
					if(type_tsk.equals("CM"))
						OtId = 2;
					list_at_ap.setItems(qr._select_recArr("hmmr_activity_type", "Name", "Description", "del_rec", "ID_OT", OtId));
				}
				else
					sclass._AlertDialog("Сначала выберите Тип: TSK или CM", "Внимание!");	
			}
		});
					
		shop_tsk.setItems(qr._select_shop_pm());
		shop_tsk.setValue(apwr_controller.SHOP_NAME);//mu_main_controller.SHOP_NAME
		if(shop_tsk.getValue().toString().length() != 0)
			group_tsk.setItems(qr._select_group_pm(sclass.parser_str(shop_tsk.getValue(), 0)));
		
		try {
			shop_tsk.getSelectionModel().selectedItemProperty().addListener(new  ChangeListener<String>() {
	
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if(shop_tsk.getValue().toString().length() != 0) {
						group_tsk.valueProperty().set(null);
						lm_tsk.valueProperty().set(null);
						os_tsk.valueProperty().set(null);
						equip_tsk.valueProperty().set(null);
						add_tsk.setDisable(true);
						group_tsk.setItems(qr._select_group_pm(sclass.parser_str(shop_tsk.getValue(), 0)));
					}
					chk_btn();
				}
			});
		} catch (Exception e) {
			
		}
		
		group_tsk.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				
				try {
					if(group_tsk.getValue().toString().length() != 0) {
						lm_tsk.valueProperty().set(null);
						os_tsk.valueProperty().set(null);
						equip_tsk.valueProperty().set(null);
						add_tsk.setDisable(true);
						lm_tsk.setItems(qr._select_lm_pm(sclass.parser_str(group_tsk.getValue(), 0)));
					}
					//if(lm_wr_add.getValue().toString().length() != 0)
					//	os_wr_add.setItems(qr._select_os_pm(sclass.parser_str(shop_wr_add.getValue(), 0), sclass.parser_str(lm_wr_add.getValue(), 0)));
					} catch (Exception e) {
						
					}
				chk_btn();
			}
		});
		group_tsk.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip = new Tooltip(group_tsk.getValue());
				Point2D p = group_tsk.localToScreen(group_tsk.getLayoutBounds().getMaxX(), group_tsk.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(group_tsk, p.getX(), p.getY());
			}
		});
		group_tsk.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip.hide();
			}
		});
		
		lm_tsk.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				try {
					if(lm_tsk.getValue().toString().length() != 0) {
						os_tsk.valueProperty().set(null);
						equip_tsk.valueProperty().set(null);
						add_tsk.setDisable(true);
						os_tsk.setItems(qr._select_os_pm(sclass.parser_str(group_tsk.getValue(), 0), sclass.parser_str(lm_tsk.getValue(), 0)));
					}
					} catch (Exception e) {
						
					}
				chk_btn();
			}
		});
		lm_tsk.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip = new Tooltip(lm_tsk.getValue());
				Point2D p = lm_tsk.localToScreen(lm_tsk.getLayoutBounds().getMaxX(), lm_tsk.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(lm_tsk, p.getX(), p.getY());
			}
		});
		lm_tsk.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip.hide();
			}
		});
			
		
		os_tsk.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
	
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				try {
					if(os_tsk.getValue().toString().length() != 0) {
						equip_tsk.valueProperty().set(null);
						add_tsk.setDisable(true);
						equip_tsk.setItems(qr._select_equip_pm(sclass.parser_str(os_tsk.getValue(), 0), sclass.parser_str(group_tsk.getValue(), 0), sclass.parser_str(lm_tsk.getValue(), 0)));
					}
					} catch (Exception e) {
					}
				chk_btn();
			}
		});
		os_tsk.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip = new Tooltip(os_tsk.getValue());
				Point2D p = os_tsk.localToScreen(os_tsk.getLayoutBounds().getMaxX(), os_tsk.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(os_tsk, p.getX(), p.getY());
			}
		});
		os_tsk.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip.hide();
			}
		});
		
		
		equip_tsk.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip = new Tooltip(equip_tsk.getValue());
				Point2D p = equip_tsk.localToScreen(equip_tsk.getLayoutBounds().getMaxX(), equip_tsk.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(equip_tsk, p.getX(), p.getY());
			}
		});
		equip_tsk.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip.hide();
			}
		});
		
		equip_tsk.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				try {
					//if(oft_tsk.getValue().toString().length() != 0)
						oft_tsk.setItems(qr._select_fio_for_ap(1, sclass.parser_str(shop_tsk.getValue(), 0)));
						chk_btn();
					} catch (Exception e) {
					}
			}
		});
		oft_tsk.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip = new Tooltip(oft_tsk.getValue());
				Point2D p = oft_tsk.localToScreen(oft_tsk.getLayoutBounds().getMaxX(), oft_tsk.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(oft_tsk, p.getX(), p.getY());
			}
		});
		oft_tsk.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip.hide();
			}
		});
		oft_tsk.setValue(sclass.parser_str(qr._select_user(conn_connector.USER_ID), 1));
		otv_tsk.setItems(qr._select_fio_for_ap(2, sclass.parser_str(shop_tsk.getValue(), 0)));
		oft_tsk.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				try {
					//if(oft_tsk.getValue().toString().length() != 0)
						//otv_tsk.setItems(qr._select_fio_for_ap());
						chk_btn();
					} catch (Exception e) {
					}
			}
		});
		otv_tsk.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				chk_btn();
			}
		});
		otv_tsk.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip = new Tooltip(otv_tsk.getValue());
				Point2D p = otv_tsk.localToScreen(otv_tsk.getLayoutBounds().getMaxX(), otv_tsk.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(otv_tsk, p.getX(), p.getY());
			}
		});
		otv_tsk.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip.hide();
			}
		});
		prior_ap.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				chk_btn();
			}
		});
		tsk_ap.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				type_tsk = "TSK";
				tsk_ap.setDisable(true);
				cm_ap.setDisable(false);
				_flag_bt = true;
				list_at_ap.setDisable(false);
				
				Platform.runLater(new Runnable() {
				    @Override
				    public void run() {
				        description_tsk.requestFocus();
				    }
				});
				
				chk_btn();
			}
		});
		tsk_ap.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip = new Tooltip(tsk_ap.getText());
				Point2D p = tsk_ap.localToScreen(tsk_ap.getLayoutBounds().getMaxX(), tsk_ap.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(tsk_ap, p.getX(), p.getY());
			}
		});
		tsk_ap.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip.hide();
			}
		});
		cm_ap.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				type_tsk = "CM";
				tsk_ap.setDisable(false);
				cm_ap.setDisable(true);
				_flag_bt = true;
				list_at_ap.setDisable(false);
				
				Platform.runLater(new Runnable() {
				    @Override
				    public void run() {
				        description_tsk.requestFocus();
				    }
				});
				
				chk_btn();
			}
		});
		cm_ap.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip = new Tooltip(cm_ap.getText());
				Point2D p = cm_ap.localToScreen(cm_ap.getLayoutBounds().getMaxX(), cm_ap.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(cm_ap, p.getX(), p.getY());
			}
		});
		cm_ap.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip.hide();
			}
		});
		description_tsk.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				chk_btn();
			}
		});
		list_at_ap.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				chk_btn();
			}
		});
				
		sclass._style(add_tsk);
		
		add_tsk.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				//if(type_tsk.length() != 0 && shop_tsk.getValue().length() != 0 && group_tsk.getValue().length() != 0 && lm_tsk.getValue().length() != 0 && os_tsk.getValue().length() != 0 &&
				//		equip_tsk.getValue().length() != 0 && oft_tsk.getValue().length() != 0 && otv_tsk.getValue().length() != 0 && 
				//		description_tsk.getText().length() != 0 && edate_tsk.getValue().toString().length() != 0 )
				//{
				//	err_msg.setVisible(false);
				//if(type_tsk.equals("CM"))
				//	list_at_ap.setValue("1");
				qr._insert_ap1(type_tsk, description_tsk.getText(), edate_tsk.getValue(), sclass.parser_str(shop_tsk.getValue(), 0)+"."+group_tsk.getValue()+"."+sclass.parser_str(lm_tsk.getValue(), 0)+"."+sclass.parser_str(os_tsk.getValue(), 0)+"."+sclass.parser_str(equip_tsk.getValue(), 0), tsk_maker_ap.getText(), sclass.parser_str(oft_tsk.getValue(), 0), sclass.parser_str(otv_tsk.getValue(), 0), conn_connector.USER_ID, sclass.parser_str(shop_tsk.getValue(), 0), sclass.parser_str(prior_ap.getValue(), 0), sclass.parser_str(list_at_ap.getValue(), 0));
					//pic.refreshTable_ap(apwr_controller.columns_ap);
				qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + " - Создал запись № = " + qr._select_last_id("hmmr_action_plan") + " в Action Plan");
				pic._table_update.addAll(qr._select_data_ap(pic.USER_S));
				stage = (Stage) add_tsk.getScene().getWindow();
				stage.close();
				//}
				//else
				//	err_msg.setVisible(true);
			}
		});
		
		sclass._style(cancel_tsk);
		
		cancel_tsk.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				stage = (Stage) cancel_tsk.getScene().getWindow();
				stage.close();
			}
		});
		
	}
	
	private void lang_fun(String loc1, String loc2)
	{
		ResourceBundle lngBndl = ResourceBundle
	            .getBundle("bundles.LangBundle", new Locale(loc1, loc2));
		
		lbl_create_tsk_ap.setText(lngBndl.getString("lbl_create_tsk_ap"));
		lbl_type_ap.setText(lngBndl.getString("lbl_type_ap")+":");
		lbl_desc_ap.setText(lngBndl.getString("lbl_desc_ap"));
		lbl_dd_ap.setText(lngBndl.getString("lbl_dd_ap"));
		lbl_shop_ap.setText(lngBndl.getString("lbl_shop_ap"));
		lbl_group_ap.setText(lngBndl.getString("lbl_group_ap"));
		lbl_lm_ap.setText(lngBndl.getString("lbl_lm_ap"));
		lbl_os_ap.setText(lngBndl.getString("lbl_os_ap"));
		lbl_equip_ap.setText(lngBndl.getString("lbl_equip_ap"));
		lbl_oft_ap.setText(lngBndl.getString("lbl_oft_ap"));
		lbl_oft_ap1.setText(lngBndl.getString("lbl_oft_ap1"));
		lbl_otv_ap.setText(lngBndl.getString("lbl_otv_ap"));
		lbl_otv_ap1.setText(lngBndl.getString("lbl_otv_ap1"));
		lbl_tsk_maker_ap.setText(lngBndl.getString("lbl_tsk_maker_ap")+":");
		lbl_prior.setText(lngBndl.getString("lbl_prior")+":");
		lbl_at_ap.setText(lngBndl.getString("at_title")+":");
		add_tsk.setText(lngBndl.getString("lbl_apply"));
		cancel_tsk.setText(lngBndl.getString("cancel_tsk"));
	}
	
	private void chk_btn()
	{
		try {
			if(shop_tsk.getValue().length() != 0 && 
					lm_tsk.getValue().length() != 0 && 
					os_tsk.getValue().length() != 0 && 
					equip_tsk.getValue().length() != 0 &&
					oft_tsk.getValue().length() != 0 &&
					otv_tsk.getValue().length() != 0 &&
					group_tsk.getValue().length() != 0 && 
					prior_ap.getValue().length() != 0 &&
					description_tsk.getText().length() != 0 &&
					edate_tsk.getValue().toString().length() != 0 && _flag_bt && list_at_ap.getValue().length() != 0) //otv_tsk.getValue().length() != 0 &&  
				add_tsk.setDisable(false);
			else
				add_tsk.setDisable(true);
			}
		catch (Exception e) {
			
		}
	}
}
