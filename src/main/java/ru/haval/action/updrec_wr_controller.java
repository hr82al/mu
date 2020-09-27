package ru.haval.action;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTimePicker;
import ru.haval.application.conn_connector;
import ru.haval.data.FxDatePickerConverter;
import ru.haval.db._query;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ru.haval.share_class.s_class;

public class updrec_wr_controller {
	
	@FXML
	TextArea shift_report_wr_upd, req_action_wr_upd;
	
	@FXML
	TextField actual_time_wr_upd, actual_time1_wr_upd, actual_time1_wr_upd2, actual_time1_wr_upd3, actual_time1_wr_upd4, numap_wr_upd, actual_time1_wr_upd5, 
	actual_time1_wr_upd6, actual_time1_wr_upd7, actual_time1_wr_upd8, actual_time1_wr_upd9;//, whb_upd, wmb_upd, whe_upd, wme_upd
	
	@FXML
	DatePicker w_data_b_upd, w_data_e_upd, w_data_b1_upd, w_data_e1_upd, w_data_b2_upd, w_data_e2_upd, w_data_b3_upd, w_data_e3_upd, w_data_b_upd15, w_data_e_upd15,
	w_data_b4_upd, w_data_b5_upd, w_data_b6_upd, w_data_b7_upd, w_data_b8_upd, w_data_e4_upd, w_data_e5_upd, w_data_e6_upd, w_data_e7_upd, w_data_e8_upd;
	
	@FXML 
	ComboBox<String> shop_wr_upd, group_wr_upd, lm_wr_upd, os_wr_upd, equip_wr_upd, record_type_wr_upd, resp_wr_upd, resp_wr_upd2, resp_wr_upd3, resp_wr_upd4, status_wr_upd, 
		list_at_wr_upd, resp_wr_upd5, resp_wr_upd6, resp_wr_upd7, resp_wr_upd8, resp_wr_upd9;
	
	@FXML
	Label err_msg, lbl_add_rec_wr_upd, shift_report_wr, req_action_wr, lbl_trt_wr, actual_time_wr, actual_time1_wr, 
	lbl_shop_ap, lbl_group_ap, lbl_lm_ap, lbl_os_ap, lbl_equip_ap, record_type_wr, resp_wr, status_wr, confirm_rec_wr, lbl_trt_wr1, lbl_trt_wr2, lbl_trt_wr3, actual_time1_wr1,
	actual_time1_wr2, actual_time1_wr3, resp_wr1, resp_wr2, resp_wr3, lbl_num_ap, lbl_at_wr_upd, resp_wr4, resp_wr5, resp_wr6, resp_wr7, resp_wr8, actual_time1_wr4,
	actual_time1_wr5, actual_time1_wr6, actual_time1_wr7, actual_time1_wr8;
	
	@FXML
	JFXButton add_wr_upd, cancel_wr_upd, plus, plus1, plus2, plus3, plus4, plus5, plus6, plus7, minus1, minus2, minus3, minus4, minus5, minus6, minus7, minus8;
	
	@FXML
	JFXTimePicker b_picker_upd, e_picker_upd, b_picker1_upd, e_picker1_upd, b_picker2_upd, e_picker2_upd, b_picker3_upd, e_picker3_upd, b_picker_upd15, e_picker_upd15,
	b_picker4_upd, e_picker4_upd, b_picker5_upd, e_picker5_upd, b_picker6_upd, e_picker6_upd, b_picker7_upd, e_picker7_upd, b_picker8_upd, e_picker8_upd;
	
	@FXML
	Pane pane;
	
	private String id_wr, qty_wr, user_wr, user_number;
	//private int hours_b, min_b, hours_e, min_e;
		
	_query qr = new _query();
	s_class sclass = new s_class();
	apwr_controller pic = new apwr_controller();
	FxDatePickerConverter fx_dp = new FxDatePickerConverter();
	FxDatePickerConverter fx_time = new FxDatePickerConverter("HH:mm");
	
	private Stage stage;
	Tooltip tip;
	double wt_rezult = 0;
	private String b_gdw, e_gdw, b_gtw, e_gtw;
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
		
		//if(conn_connector.USER_ROLE.equals("Technics"))
		//	status_wr_upd.setDisable(true);
				
		actual_time_wr_upd.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(!newValue.isEmpty()) {
		
				if (!newValue.matches("\\d*|#|\\*")) {
					actual_time_wr_upd.setText(newValue.replaceAll("[^\\d|#|\\*]", ""));
		        }
				if(newValue.length() > 4) {
					
					actual_time_wr_upd.setText(newValue.replaceAll("[0-9]", ""));
	            	
				}
			}
			}
		});
		actual_time1_wr_upd.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(!newValue.isEmpty()) {
		
				if (!newValue.matches("\\d*|#|\\*")) {
					actual_time1_wr_upd.setText(newValue.replaceAll("[^\\d|#|\\*]", ""));
		        }
				if(newValue.length() > 4) {
					
					actual_time1_wr_upd.setText(newValue.replaceAll("[0-9]", ""));
	            	
				}
			}
			}
		});
		actual_time1_wr_upd2.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(!newValue.isEmpty()) {
		
				if (!newValue.matches("\\d*|#|\\*")) {
					actual_time1_wr_upd2.setText(newValue.replaceAll("[^\\d|#|\\*]", ""));
		        }
				if(newValue.length() > 4) {
					
					actual_time1_wr_upd2.setText(newValue.replaceAll("[0-9]", ""));
	            	
				}
				}
			}
		});
		actual_time1_wr_upd3.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(!newValue.isEmpty()) {
		
				if (!newValue.matches("\\d*|#|\\*")) {
					actual_time1_wr_upd3.setText(newValue.replaceAll("[^\\d|#|\\*]", ""));
		        }
				if(newValue.length() > 4) {
					
					actual_time1_wr_upd3.setText(newValue.replaceAll("[0-9]", ""));
	            	
				}
				}
			}
		});
		actual_time1_wr_upd4.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(!newValue.isEmpty()) {
		
				if (!newValue.matches("\\d*|#|\\*")) {
					actual_time1_wr_upd4.setText(newValue.replaceAll("[^\\d|#|\\*]", ""));
		        }
				if(newValue.length() > 4) {
					
					actual_time1_wr_upd4.setText(newValue.replaceAll("[0-9]", ""));
	            	
				}
				}
			}
		});
		
		b_picker_upd._24HourViewProperty();
		b_picker_upd.setIs24HourView(true);
		
		e_picker_upd._24HourViewProperty();
		e_picker_upd.setIs24HourView(true);
		
		b_picker1_upd._24HourViewProperty();
		b_picker1_upd.setIs24HourView(true);
		
		e_picker1_upd._24HourViewProperty();
		e_picker1_upd.setIs24HourView(true);
		
		b_picker2_upd._24HourViewProperty();
		b_picker2_upd.setIs24HourView(true);
		
		e_picker2_upd._24HourViewProperty();
		e_picker2_upd.setIs24HourView(true);
		
		b_picker3_upd._24HourViewProperty();
		b_picker3_upd.setIs24HourView(true);
		
		e_picker3_upd._24HourViewProperty();
		e_picker3_upd.setIs24HourView(true);
		
		b_picker4_upd._24HourViewProperty();
		b_picker4_upd.setIs24HourView(true);
		e_picker4_upd._24HourViewProperty();
		e_picker4_upd.setIs24HourView(true);
		
		b_picker5_upd._24HourViewProperty();
		b_picker5_upd.setIs24HourView(true);
		e_picker5_upd._24HourViewProperty();
		e_picker5_upd.setIs24HourView(true);
		
		b_picker6_upd._24HourViewProperty();
		b_picker6_upd.setIs24HourView(true);
		e_picker6_upd._24HourViewProperty();
		e_picker6_upd.setIs24HourView(true);
		
		b_picker7_upd._24HourViewProperty();
		b_picker7_upd.setIs24HourView(true);
		e_picker7_upd._24HourViewProperty();
		e_picker7_upd.setIs24HourView(true);
		
		b_picker8_upd._24HourViewProperty();
		b_picker8_upd.setIs24HourView(true);
		e_picker8_upd._24HourViewProperty();
		e_picker8_upd.setIs24HourView(true);
		
		w_data_b_upd.setValue(LocalDate.now());
		w_data_e_upd.setValue(LocalDate.now());
		b_picker_upd.setValue(LocalTime.parse(fx_time.toStringt(LocalTime.now())));
		e_picker_upd.setValue(LocalTime.parse(fx_time.toStringt(LocalTime.now().plusHours(1))));
		
		w_data_b1_upd.setValue(LocalDate.now());
		w_data_e1_upd.setValue(LocalDate.now());
		b_picker1_upd.setValue(LocalTime.parse(fx_time.toStringt(LocalTime.now())));
		e_picker1_upd.setValue(LocalTime.parse(fx_time.toStringt(LocalTime.now().plusHours(1))));
		
		w_data_b2_upd.setValue(LocalDate.now());
		w_data_e2_upd.setValue(LocalDate.now());
		b_picker2_upd.setValue(LocalTime.parse(fx_time.toStringt(LocalTime.now())));
		e_picker2_upd.setValue(LocalTime.parse(fx_time.toStringt(LocalTime.now().plusHours(1))));
		
		w_data_b3_upd.setValue(LocalDate.now());
		w_data_e3_upd.setValue(LocalDate.now());
		b_picker3_upd.setValue(LocalTime.parse(fx_time.toStringt(LocalTime.now())));
		e_picker3_upd.setValue(LocalTime.parse(fx_time.toStringt(LocalTime.now().plusHours(1))));
		
		w_data_b4_upd.setValue(LocalDate.now());
		w_data_e4_upd.setValue(LocalDate.now());
		b_picker4_upd.setValue(LocalTime.parse(fx_time.toStringt(LocalTime.now())));
		e_picker4_upd.setValue(LocalTime.parse(fx_time.toStringt(LocalTime.now().plusHours(1))));
		
		w_data_b5_upd.setValue(LocalDate.now());
		w_data_e5_upd.setValue(LocalDate.now());
		b_picker5_upd.setValue(LocalTime.parse(fx_time.toStringt(LocalTime.now())));
		e_picker5_upd.setValue(LocalTime.parse(fx_time.toStringt(LocalTime.now().plusHours(1))));
		
		w_data_b6_upd.setValue(LocalDate.now());
		w_data_e6_upd.setValue(LocalDate.now());
		b_picker6_upd.setValue(LocalTime.parse(fx_time.toStringt(LocalTime.now())));
		e_picker6_upd.setValue(LocalTime.parse(fx_time.toStringt(LocalTime.now().plusHours(1))));
		
		w_data_b7_upd.setValue(LocalDate.now());
		w_data_e7_upd.setValue(LocalDate.now());
		b_picker7_upd.setValue(LocalTime.parse(fx_time.toStringt(LocalTime.now())));
		e_picker7_upd.setValue(LocalTime.parse(fx_time.toStringt(LocalTime.now().plusHours(1))));
		
		w_data_b8_upd.setValue(LocalDate.now());
		w_data_e8_upd.setValue(LocalDate.now());
		b_picker8_upd.setValue(LocalTime.parse(fx_time.toStringt(LocalTime.now())));
		e_picker8_upd.setValue(LocalTime.parse(fx_time.toStringt(LocalTime.now().plusHours(1))));
		
		actual_time1_wr_upd2.setText("0");
		actual_time1_wr_upd3.setText("0");
		actual_time1_wr_upd4.setText("0");
		actual_time1_wr_upd5.setText("0");
		actual_time1_wr_upd6.setText("0");
		actual_time1_wr_upd7.setText("0");
		actual_time1_wr_upd8.setText("0");
		actual_time1_wr_upd9.setText("0");
		
		resp_wr_upd2.setValue("0");
		resp_wr_upd3.setValue("0");
		resp_wr_upd4.setValue("0");
		resp_wr_upd5.setValue("0");
		resp_wr_upd6.setValue("0");
		resp_wr_upd7.setValue("0");
		resp_wr_upd8.setValue("0");
		resp_wr_upd9.setValue("0");
		
		add_wr_upd.setDisable(true);
		//инициализируем комбобоксы
				String line1 = new String("PM");
				String line2 = new String("TSK");
				String line3 = new String("CM");
				record_type_wr_upd.getItems().addAll(line1, line2, line3);
				
				String line4 = new String("New WR");
				String line5 = new String("Confirmed WR");
				status_wr_upd.getItems().addAll(line4, line5);
				status_wr_upd.setValue(line5);
				
				if(conn_connector.USER_ROLE.equals("Technics"))
				{
					shop_wr_upd.setDisable(true);
					group_wr_upd.setDisable(true);
					lm_wr_upd.setDisable(true);
					os_wr_upd.setDisable(true);
					equip_wr_upd.setDisable(true);
				}
				
				shop_wr_upd.setItems(qr._select_shop_pm());
				try {
					shop_wr_upd.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			
						@Override
						public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
							if(shop_wr_upd.getValue().toString().length() != 0) {
								group_wr_upd.valueProperty().set(null);
								lm_wr_upd.valueProperty().set(null);
								os_wr_upd.valueProperty().set(null);
								equip_wr_upd.valueProperty().set(null);
								add_wr_upd.setDisable(true);
								group_wr_upd.setItems(qr._select_group_pm(sclass.parser_str(shop_wr_upd.getValue(), 0)));
							}
							chk_btn();
						}
					});
				} catch (Exception e) {
					
				}
				
				group_wr_upd.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
						
						try {
							if(group_wr_upd.getValue().toString().length() != 0) {
								lm_wr_upd.valueProperty().set(null);
								os_wr_upd.valueProperty().set(null);
								equip_wr_upd.valueProperty().set(null);
								add_wr_upd.setDisable(true);
								lm_wr_upd.setItems(qr._select_lm_pm(sclass.parser_str(group_wr_upd.getValue(), 0)));
							}
							//if(lm_wr_add.getValue().toString().length() != 0)
							//	os_wr_add.setItems(qr._select_os_pm(sclass.parser_str(shop_wr_add.getValue(), 0), sclass.parser_str(lm_wr_add.getValue(), 0)));
							} catch (Exception e) {
								
							}
						chk_btn();
					}
				});
				group_wr_upd.setOnMouseEntered(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						tip = new Tooltip(group_wr_upd.getValue());
						Point2D p = group_wr_upd.localToScreen(group_wr_upd.getLayoutBounds().getMaxX(), group_wr_upd.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
				        tip.show(group_wr_upd, p.getX(), p.getY());
					}
				});
				group_wr_upd.setOnMouseExited(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						tip.hide();
					}
				});
				
				lm_wr_upd.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
						try {
							if(lm_wr_upd.getValue().toString().length() != 0) {
								os_wr_upd.valueProperty().set(null);
								equip_wr_upd.valueProperty().set(null);
								add_wr_upd.setDisable(true);
								os_wr_upd.setItems(qr._select_os_pm(sclass.parser_str(group_wr_upd.getValue(), 0), sclass.parser_str(lm_wr_upd.getValue(), 0)));
							}
							} catch (Exception e) {
								
							}
						chk_btn();
					}
				});
				lm_wr_upd.setOnMouseEntered(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						tip = new Tooltip(lm_wr_upd.getValue());
						Point2D p = lm_wr_upd.localToScreen(lm_wr_upd.getLayoutBounds().getMaxX(), lm_wr_upd.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
				        tip.show(lm_wr_upd, p.getX(), p.getY());
					}
				});
				lm_wr_upd.setOnMouseExited(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						tip.hide();
					}
				});
					
				
				os_wr_upd.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			
					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
						try {
							if(os_wr_upd.getValue().toString().length() != 0) {
								equip_wr_upd.valueProperty().set(null);
								add_wr_upd.setDisable(true);
								equip_wr_upd.setItems(qr._select_equip_pm(sclass.parser_str(os_wr_upd.getValue(), 0), sclass.parser_str(group_wr_upd.getValue(), 0), sclass.parser_str(lm_wr_upd.getValue(), 0)));
							}
							} catch (Exception e) {
							}
						chk_btn();
					}
				});
				os_wr_upd.setOnMouseEntered(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						tip = new Tooltip(os_wr_upd.getValue());
						Point2D p = os_wr_upd.localToScreen(os_wr_upd.getLayoutBounds().getMaxX(), os_wr_upd.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
				        tip.show(os_wr_upd, p.getX(), p.getY());
					}
				});
				os_wr_upd.setOnMouseExited(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						tip.hide();
					}
				});
				
				
				equip_wr_upd.setOnMouseEntered(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						tip = new Tooltip(equip_wr_upd.getValue());
						Point2D p = equip_wr_upd.localToScreen(equip_wr_upd.getLayoutBounds().getMaxX(), equip_wr_upd.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
				        tip.show(equip_wr_upd, p.getX(), p.getY());
					}
				});
				equip_wr_upd.setOnMouseExited(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						tip.hide();
					}
				});
				
				equip_wr_upd.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
					
					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
						try {
							//if(oft_tsk.getValue().toString().length() != 0)
								resp_wr_upd.setItems(qr._select_fio_for_ap(2, sclass.parser_str(shop_wr_upd.getValue(), 0)));
								resp_wr_upd2.setItems(qr._select_fio_for_ap(2, sclass.parser_str(shop_wr_upd.getValue(), 0)));
								resp_wr_upd3.setItems(qr._select_fio_for_ap(2, sclass.parser_str(shop_wr_upd.getValue(), 0)));
								resp_wr_upd4.setItems(qr._select_fio_for_ap(2, sclass.parser_str(shop_wr_upd.getValue(), 0)));
							} catch (Exception e) {
							}
						chk_btn();
					}
				});
				resp_wr_upd.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue,
							String newValue) {
							chk_btn();
					}
				});
				resp_wr_upd.setOnMouseEntered(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						tip = new Tooltip(resp_wr_upd.getValue());
						Point2D p = resp_wr_upd.localToScreen(resp_wr_upd.getLayoutBounds().getMaxX(), resp_wr_upd.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
				        tip.show(resp_wr_upd, p.getX(), p.getY());
					}
				});
				resp_wr_upd.setOnMouseExited(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						tip.hide();
					}
				});
				
				list_at_wr_upd.setOnMouseClicked(new EventHandler<Event>() {
					@Override
					public void handle(Event arg0) {
						
							if(record_type_wr_upd.getValue().equals("TSK"))
								OtId = 3;
							if(record_type_wr_upd.getValue().equals("CM"))
								OtId = 2;
							if(record_type_wr_upd.getValue().equals("PM"))
								OtId = 1;
							list_at_wr_upd.setItems(qr._select_recArr("hmmr_activity_type", "Name", "Description", "del_rec", "ID_OT", OtId));
						
					}
				});
				
				list_at_wr_upd.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent arg0) {
						chk_btn();
					}
				});
				
				 //_qty_wr, _ap_num_wr, _data_wr, _equip_wr, _record_type_wr, _resp_wr, _status_wr, _shift_report_wr, _req_action_wr, _actual_time_wr, _actual_time1_wr
				id_wr = pic._id_wr;
				numap_wr_upd.setText(pic._ap_num_wr);
				shift_report_wr_upd.setText(pic._shift_report_wr);
				record_type_wr_upd.getSelectionModel().select(pic._record_type_wr);
				shop_wr_upd.getSelectionModel().select(sclass.parser_str_str(pic._equip_wr, 0));
				group_wr_upd.getSelectionModel().select(sclass.parser_str_str(pic._equip_wr, 1));
				lm_wr_upd.getSelectionModel().select(sclass.parser_str_str(pic._equip_wr, 2));
				os_wr_upd.getSelectionModel().select(sclass.parser_str_str(pic._equip_wr, 3));
				equip_wr_upd.getSelectionModel().select(sclass.parser_str_str(pic._equip_wr, 4));
				req_action_wr_upd.setText(pic._req_action_wr);
				resp_wr_upd.getSelectionModel().select(pic._resp_wr);
				status_wr_upd.getSelectionModel().select(pic._status_wr);
				actual_time_wr_upd.setText(pic._actual_time_wr);
				actual_time1_wr_upd.setText(pic._actual_time1_wr);
				qty_wr = pic._qty_wr;
				user_wr = pic._user_wr;
				user_number = pic._user_number;
				list_at_wr_upd.getSelectionModel().select(pic._activity_type_wr);
				
				actual_time1_wr_upd2.setText(pic._actual_time2_wr);
				actual_time1_wr_upd3.setText(pic._actual_time3_wr);
				actual_time1_wr_upd4.setText(pic._actual_time4_wr);
				resp_wr_upd2.getSelectionModel().select(pic._resp2_wr);
				resp_wr_upd3.getSelectionModel().select(pic._resp3_wr);
				resp_wr_upd4.getSelectionModel().select(pic._resp4_wr);
				
				if(pic._actual_date.equals("0"))
					w_data_b_upd.setValue(LocalDate.now());
				else
					w_data_b_upd.setValue(fx_dp.fromString(pic._actual_date));
				if(pic._actual_date1.equals("0"))
					w_data_e_upd.setValue(LocalDate.now());
				else
					w_data_e_upd.setValue(fx_dp.fromString(pic._actual_date1));
				
				w_data_b1_upd.setValue(fx_dp.fromString(pic._actual_date_2));
				w_data_b2_upd.setValue(fx_dp.fromString(pic._actual_date_3));
				w_data_b3_upd.setValue(fx_dp.fromString(pic._actual_date_4));
				
				b_picker_upd.setValue(fx_time.fromStringt(pic._hours1));
				b_picker1_upd.setValue(fx_time.fromStringt(pic._hours1_2));
				b_picker2_upd.setValue(fx_time.fromStringt(pic._hours1_3));
				b_picker3_upd.setValue(fx_time.fromStringt(pic._hours1_4));
				
				w_data_e1_upd.setValue(fx_dp.fromString(pic._actual_date2));
				w_data_e2_upd.setValue(fx_dp.fromString(pic._actual_date3));
				w_data_e3_upd.setValue(fx_dp.fromString(pic._actual_date4));
				
				e_picker_upd.setValue(fx_time.fromStringt(pic._hours2));
				e_picker1_upd.setValue(fx_time.fromStringt(pic._hours2_2));
				e_picker2_upd.setValue(fx_time.fromStringt(pic._hours2_3));
				e_picker3_upd.setValue(fx_time.fromStringt(pic._hours2_4));
				
				if(record_type_wr_upd.getValue().equals("CM"))
					chk_wt(pic._ap_num_wr);
				
				//Проверяем подтверждена запись менеджером или нет
				if(qty_wr.equals("1") || user_wr.equals("1"))
				{
					confirm_rec_wr.setVisible(true);
					shift_report_wr_upd.setDisable(true);
					req_action_wr_upd.setDisable(true);
					actual_time_wr_upd.setDisable(true); 
					actual_time1_wr_upd.setDisable(true); 
					numap_wr_upd.setDisable(true);
					shop_wr_upd.setDisable(true);
					group_wr_upd.setDisable(true);					
					lm_wr_upd.setDisable(true);
					os_wr_upd.setDisable(true);
					equip_wr_upd.setDisable(true);
					record_type_wr_upd.setDisable(true);
					resp_wr_upd.setDisable(true);
					status_wr_upd.setDisable(true);
					add_wr_upd.setDisable(true);
					w_data_b_upd.setDisable(true);
					w_data_e_upd.setDisable(true);
					b_picker_upd.setDisable(true);
					e_picker_upd.setDisable(true);
					shift_report_wr.setDisable(true);
					req_action_wr.setDisable(true);
					actual_time1_wr.setDisable(true);
					lbl_shop_ap.setDisable(true);
					lbl_group_ap.setDisable(true);
					lbl_lm_ap.setDisable(true);
					lbl_os_ap.setDisable(true);
					lbl_equip_ap.setDisable(true);
					record_type_wr.setDisable(true);
					status_wr.setDisable(true);
					resp_wr.setDisable(true);
					lbl_num_ap.setDisable(true);
					plus.setDisable(true);
				}
				
				shift_report_wr_upd.setOnKeyPressed(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				});
				req_action_wr_upd.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				});
				actual_time_wr_upd.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				});
				actual_time1_wr_upd.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				});
				status_wr_upd.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue,
							String newValue) {
						chk_btn();
					}
				});
				
				
				//Ввычисляем время сколько занял ремонт
			/*	int d_b = w_data_b_upd.getValue().getDayOfYear();
				int d_e = w_data_e_upd.getValue().getDayOfYear();
				
				int data_rezult = d_e - d_b;
				if(whb_upd.getText().equals(""))
					hours_b = 0;
				else
					hours_b = Integer.parseInt(whb_upd.getText());
				if(wmb_upd.getText().equals(""))
					min_b = 0;
				else
					min_b = Integer.parseInt(wmb_upd.getText());
				if(whe_upd.getText().equals(""))
					hours_e = 0;
				else
					hours_e = Integer.parseInt(whe_upd.getText());
				if(wme_upd.getText().equals(""))
					min_e = 0;
				else
					min_e = Integer.parseInt(wme_upd.getText());
				
				int time_rezult = Math.abs(hours_e - hours_b)*60 + Math.abs(min_e - min_b);
				
				wt_rezult = data_rezult*24 + time_rezult/60;*/
				sclass._style(add_wr_upd);
				
				add_wr_upd.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						if(record_type_wr_upd.getValue().equals("CM")) {
							if(!qr._select_confirm_wt(pic._idap_for_wr.substring(2)).equals("YES")) {
								
								b_gdw = fx_dp.toString(w_data_b_upd.getValue());
								e_gdw = fx_dp.toString(w_data_e_upd.getValue());
								b_gtw = fx_time.toStringt(b_picker_upd15.getValue());
								e_gtw = fx_time.toStringt(e_picker_upd15.getValue());
								
								//Ввычисляем время сколько занял ремонт
								int d_b = w_data_b_upd.getValue().getDayOfYear();
								int d_e = w_data_e_upd.getValue().getDayOfYear();
								
								int data_rezult = d_e - d_b;
								//Обрезаем секунды
								//int hours_b = Integer.parseInt(sclass.parser_double_dot(b_picker.getValue().toString(), 0));
								//int min_b = Integer.parseInt(sclass.parser_double_dot(b_picker.getValue().toString(), 1));
								//int hours_e = Integer.parseInt(sclass.parser_double_dot(e_picker.getValue().toString(), 0));
								//int min_e = Integer.parseInt(sclass.parser_double_dot(e_picker.getValue().toString(), 1));
								
								//int time_rezult = Math.abs(hours_e - hours_b)*60 + Math.abs(min_e - min_b);
								LocalTime test = b_picker_upd15.getValue();
								LocalTime test2 = e_picker_upd15.getValue();
								
								//long h_between = ChronoUnit.HOURS.between(test, test2);
								long m_between = ChronoUnit.MINUTES.between(test, test2);
								
								int time_rezult = Math.abs((int) m_between);
														
								//wt_rezult = data_rezult*24 + time_rezult/60.d;
								wt_rezult = data_rezult*24 + time_rezult/60.d;
							}
							else 
								wt_rezult = 0;
						}
						else
						{
							b_gdw = "0000-00-00";
							e_gdw = "0000-00-00";
							b_gtw = "00:00";
							e_gtw = "00:00";
						}
						
						//if(!record_type_wr_upd.getValue().equals("CM")) {
						//	list_at_wr_upd.setValue(qr._select_rec("hmmr_action_plan", "Icon_AT", "del_rec", "id", numap_wr_upd.getText()));
						//}
						//else
						//меняем вид работ и в WO, если поменяли вид работ в WR
						qr._update_rec_ap_iconat(sclass.parser_str(list_at_wr_upd.getValue(), 0), numap_wr_upd.getText());
						qr._update_rec_wr(id_wr.substring(2), numap_wr_upd.getText(), sclass.parser_str(shop_wr_upd.getValue(), 0), sclass.parser_str(group_wr_upd.getValue(), 0), sclass.parser_str(lm_wr_upd.getValue(), 0), sclass.parser_str(os_wr_upd.getValue(), 0), sclass.parser_str(equip_wr_upd.getValue(), 0), sclass.parser_str(shop_wr_upd.getValue(), 0)+"."+sclass.parser_str(group_wr_upd.getValue(), 0)+"."+sclass.parser_str(lm_wr_upd.getValue(), 0)+"."+sclass.parser_str(os_wr_upd.getValue(), 0)+"."+sclass.parser_str(equip_wr_upd.getValue(), 0), record_type_wr_upd.getValue(), sclass.parser_str(resp_wr_upd.getValue(), 0),sclass.parser_str(resp_wr_upd2.getValue(), 0),sclass.parser_str(resp_wr_upd3.getValue(), 0),sclass.parser_str(resp_wr_upd4.getValue(), 0), status_wr_upd.getValue(), shift_report_wr_upd.getText(), req_action_wr_upd.getText(), w_data_b_upd.getValue(),w_data_b1_upd.getValue(),w_data_b2_upd.getValue(),w_data_b3_upd.getValue(), actual_time_wr_upd.getText(), w_data_e_upd.getValue(),w_data_e1_upd.getValue(),w_data_e2_upd.getValue(),w_data_e3_upd.getValue(), actual_time1_wr_upd.getText(),actual_time1_wr_upd2.getText(),actual_time1_wr_upd3.getText(),actual_time1_wr_upd4.getText(), b_picker_upd.getValue(),b_picker1_upd.getValue(),b_picker2_upd.getValue(),b_picker3_upd.getValue(), e_picker_upd.getValue(),e_picker1_upd.getValue(),e_picker2_upd.getValue(),e_picker3_upd.getValue(), sclass.parser_str(list_at_wr_upd.getValue(), 0), sclass.parser_str(resp_wr_upd4.getValue(), 0), sclass.parser_str(resp_wr_upd5.getValue(), 0), sclass.parser_str(resp_wr_upd6.getValue(), 0), sclass.parser_str(resp_wr_upd7.getValue(), 0), sclass.parser_str(resp_wr_upd8.getValue(), 0), w_data_b4_upd.getValue(), w_data_b5_upd.getValue(), w_data_b6_upd.getValue(), w_data_b7_upd.getValue(), w_data_b8_upd.getValue(), w_data_e4_upd.getValue(), w_data_e5_upd.getValue(), w_data_e6_upd.getValue(), w_data_e7_upd.getValue(), w_data_e8_upd.getValue(), actual_time1_wr_upd4.getText(), actual_time1_wr_upd5.getText(), actual_time1_wr_upd6.getText(), actual_time1_wr_upd7.getText(), actual_time1_wr_upd8.getText(), b_picker4_upd.getValue(), b_picker5_upd.getValue(), b_picker6_upd.getValue(), b_picker7_upd.getValue(), b_picker8_upd.getValue(), e_picker4_upd.getValue(), e_picker5_upd.getValue(), e_picker6_upd.getValue(), e_picker7_upd.getValue(), e_picker8_upd.getValue());
						err_msg.setVisible(false);
						
						//Расчитываем врямя работы техников чтоб не было косяков
						actual_time1_wr_upd.setText(""+ fix_time(w_data_b_upd, w_data_e_upd, b_picker_upd, e_picker_upd));
						actual_time1_wr_upd2.setText(""+ fix_time(w_data_b1_upd, w_data_e1_upd, b_picker1_upd, e_picker1_upd));
						actual_time1_wr_upd3.setText(""+ fix_time(w_data_b2_upd, w_data_e2_upd, b_picker2_upd, e_picker2_upd));
						actual_time1_wr_upd4.setText(""+ fix_time(w_data_b3_upd, w_data_e3_upd, b_picker3_upd, e_picker3_upd));
						actual_time1_wr_upd5.setText(""+ fix_time(w_data_b4_upd, w_data_e4_upd, b_picker4_upd, e_picker4_upd));
						actual_time1_wr_upd6.setText(""+ fix_time(w_data_b5_upd, w_data_e5_upd, b_picker5_upd, e_picker5_upd));
						actual_time1_wr_upd7.setText(""+ fix_time(w_data_b6_upd, w_data_e6_upd, b_picker6_upd, e_picker6_upd));
						actual_time1_wr_upd8.setText(""+ fix_time(w_data_b7_upd, w_data_e7_upd, b_picker7_upd, e_picker7_upd));
						actual_time1_wr_upd9.setText(""+ fix_time(w_data_b8_upd, w_data_e8_upd, b_picker8_upd, e_picker8_upd));
						
						qr._insert_history(conn_connector.USER_ID, pic.USER_S + " - Обновил запись № = " + id_wr.substring(2) + " в таблице Work Recording");
								
								
						String id_wr = qr._select_last_id("hmmr_work_recording");
						//В зависимости от количества исполнителей указанных в записи WR создаем такое же количество записей в WR
						if(!qr._select_resp(id_wr, "_Resp2").equals("0"))
						{
							LocalDate b_data = fx_dp.fromString(qr._select_b_hours(id_wr, "_Actual_Date_2"));
							LocalDate e_data = fx_dp.fromString(qr._select_b_hours(id_wr, "_Actual_Date2"));
							LocalTime b_time = fx_time.fromStringt(qr._select_b_hours(id_wr, "_Hours1_2"));
							LocalTime e_time = fx_time.fromStringt(qr._select_b_hours(id_wr, "_Hours2_2"));
							String at = qr._select_b_hours(id_wr, "_Actual_Time2");
							//Если СМ то для всех остальных кроме первого техника время общего ремонта = 0
							wt_rezult = 0;
										
							qr._insert_wr(numap_wr_upd.getText(), conn_connector.USER_ID, sclass.parser_str(shop_wr_upd.getValue(), 0), group_wr_upd.getValue(), sclass.parser_str(lm_wr_upd.getValue(), 0), sclass.parser_str(os_wr_upd.getValue(), 0), sclass.parser_str(equip_wr_upd.getValue(), 0), sclass.parser_str(shop_wr_upd.getValue(), 0)+"."+group_wr_upd.getValue()+"."+sclass.parser_str(lm_wr_upd.getValue(), 0)+"."+sclass.parser_str(os_wr_upd.getValue(), 0)+"."+sclass.parser_str(equip_wr_upd.getValue(), 0), record_type_wr_upd.getValue(), wt_rezult, sclass.parser_str(qr._select_resp(id_wr, "_Resp2"), 0), "0", "0", "0", status_wr_upd.getValue(), shift_report_wr_upd.getText(), req_action_wr_upd.getText(), b_data, w_data_b_upd.getValue(), w_data_b2_upd.getValue(), w_data_b3_upd.getValue(), actual_time_wr_upd.getText(), e_data, w_data_e_upd.getValue(), w_data_e2_upd.getValue(), w_data_e3_upd.getValue(), at, "0", "0", "0", b_time, b_picker_upd.getValue(), b_picker2_upd.getValue(), b_picker3_upd.getValue(), e_time, e_picker_upd.getValue(), e_picker2_upd.getValue(), e_picker3_upd.getValue(), b_gdw, e_gdw, b_gtw, e_gtw, sclass.parser_str(list_at_wr_upd.getValue(), 0), sclass.parser_str(resp_wr_upd4.getValue(), 0), sclass.parser_str(resp_wr_upd5.getValue(), 0), sclass.parser_str(resp_wr_upd6.getValue(), 0), sclass.parser_str(resp_wr_upd7.getValue(), 0), sclass.parser_str(resp_wr_upd8.getValue(), 0), w_data_b4_upd.getValue(), w_data_b5_upd.getValue(), w_data_b6_upd.getValue(), w_data_b7_upd.getValue(), w_data_b8_upd.getValue(), w_data_e4_upd.getValue(), w_data_e5_upd.getValue(), w_data_e6_upd.getValue(), w_data_e7_upd.getValue(), w_data_e8_upd.getValue(), actual_time1_wr_upd4.getText(), actual_time1_wr_upd5.getText(), actual_time1_wr_upd6.getText(), actual_time1_wr_upd7.getText(), actual_time1_wr_upd8.getText(), b_picker4_upd.getValue(), b_picker5_upd.getValue(), b_picker6_upd.getValue(), b_picker7_upd.getValue(), b_picker8_upd.getValue(), e_picker4_upd.getValue(), e_picker5_upd.getValue(), e_picker6_upd.getValue(), e_picker7_upd.getValue(), e_picker8_upd.getValue());
							qr._update_r_wr(id_wr, "_Resp2", "0");
							qr._update_r_wr(id_wr, "_Actual_Time2", "0");
						}
						if(!qr._select_resp(id_wr, "_Resp3").equals("0"))
						{
							LocalDate b_data = fx_dp.fromString(qr._select_b_hours(id_wr, "_Actual_Date_3"));
							LocalDate e_data = fx_dp.fromString(qr._select_b_hours(id_wr, "_Actual_Date3"));
							LocalTime b_time = fx_time.fromStringt(qr._select_b_hours(id_wr, "_Hours1_3"));
							LocalTime e_time = fx_time.fromStringt(qr._select_b_hours(id_wr, "_Hours2_3"));
							String at = qr._select_b_hours(id_wr, "_Actual_Time3");
							wt_rezult = 0;
										
							qr._insert_wr(numap_wr_upd.getText(), conn_connector.USER_ID, sclass.parser_str(shop_wr_upd.getValue(), 0), group_wr_upd.getValue(), sclass.parser_str(lm_wr_upd.getValue(), 0), sclass.parser_str(os_wr_upd.getValue(), 0), sclass.parser_str(equip_wr_upd.getValue(), 0), sclass.parser_str(shop_wr_upd.getValue(), 0)+"."+group_wr_upd.getValue()+"."+sclass.parser_str(lm_wr_upd.getValue(), 0)+"."+sclass.parser_str(os_wr_upd.getValue(), 0)+"."+sclass.parser_str(equip_wr_upd.getValue(), 0), record_type_wr_upd.getValue(), wt_rezult, sclass.parser_str(qr._select_resp(id_wr, "_Resp3"), 0), "0", "0", "0", status_wr_upd.getValue(), shift_report_wr_upd.getText(), req_action_wr_upd.getText(), b_data, w_data_b_upd.getValue(), w_data_b2_upd.getValue(), w_data_b3_upd.getValue(), actual_time_wr_upd.getText(), e_data, w_data_e_upd.getValue(), w_data_e2_upd.getValue(), w_data_e3_upd.getValue(), at, "0", "0", "0", b_time, b_picker_upd.getValue(), b_picker2_upd.getValue(), b_picker3_upd.getValue(), e_time, e_picker_upd.getValue(), e_picker2_upd.getValue(), e_picker3_upd.getValue(), b_gdw, e_gdw, b_gtw, e_gtw, sclass.parser_str(list_at_wr_upd.getValue(), 0), sclass.parser_str(resp_wr_upd4.getValue(), 0), sclass.parser_str(resp_wr_upd5.getValue(), 0), sclass.parser_str(resp_wr_upd6.getValue(), 0), sclass.parser_str(resp_wr_upd7.getValue(), 0), sclass.parser_str(resp_wr_upd8.getValue(), 0), w_data_b4_upd.getValue(), w_data_b5_upd.getValue(), w_data_b6_upd.getValue(), w_data_b7_upd.getValue(), w_data_b8_upd.getValue(), w_data_e4_upd.getValue(), w_data_e5_upd.getValue(), w_data_e6_upd.getValue(), w_data_e7_upd.getValue(), w_data_e8_upd.getValue(), actual_time1_wr_upd4.getText(), actual_time1_wr_upd5.getText(), actual_time1_wr_upd6.getText(), actual_time1_wr_upd7.getText(), actual_time1_wr_upd8.getText(), b_picker4_upd.getValue(), b_picker5_upd.getValue(), b_picker6_upd.getValue(), b_picker7_upd.getValue(), b_picker8_upd.getValue(), e_picker4_upd.getValue(), e_picker5_upd.getValue(), e_picker6_upd.getValue(), e_picker7_upd.getValue(), e_picker8_upd.getValue());
							qr._update_r_wr(id_wr, "_Resp3", "0");
							qr._update_r_wr(id_wr, "_Actual_Time3", "0");
						}
						if(!qr._select_resp(id_wr, "_Resp4").equals("0"))
						{
							LocalDate b_data = fx_dp.fromString(qr._select_b_hours(id_wr, "_Actual_Date_4"));
							LocalDate e_data = fx_dp.fromString(qr._select_b_hours(id_wr, "_Actual_Date4"));
							LocalTime b_time = fx_time.fromStringt(qr._select_b_hours(id_wr, "_Hours1_4"));
							LocalTime e_time = fx_time.fromStringt(qr._select_b_hours(id_wr, "_Hours2_4"));
							String at = qr._select_b_hours(id_wr, "_Actual_Time4");
							wt_rezult = 0;
										
							qr._insert_wr(numap_wr_upd.getText(), conn_connector.USER_ID, sclass.parser_str(shop_wr_upd.getValue(), 0), group_wr_upd.getValue(), sclass.parser_str(lm_wr_upd.getValue(), 0), sclass.parser_str(os_wr_upd.getValue(), 0), sclass.parser_str(equip_wr_upd.getValue(), 0), sclass.parser_str(shop_wr_upd.getValue(), 0)+"."+group_wr_upd.getValue()+"."+sclass.parser_str(lm_wr_upd.getValue(), 0)+"."+sclass.parser_str(os_wr_upd.getValue(), 0)+"."+sclass.parser_str(equip_wr_upd.getValue(), 0), record_type_wr_upd.getValue(), wt_rezult, sclass.parser_str(qr._select_resp(id_wr, "_Resp4"), 0), "0", "0", "0", status_wr_upd.getValue(), shift_report_wr_upd.getText(), req_action_wr_upd.getText(), b_data, w_data_b_upd.getValue(), w_data_b2_upd.getValue(), w_data_b3_upd.getValue(), actual_time_wr_upd.getText(), e_data, w_data_e_upd.getValue(), w_data_e2_upd.getValue(), w_data_e3_upd.getValue(), at, "0", "0", "0", b_time, b_picker_upd.getValue(), b_picker2_upd.getValue(), b_picker3_upd.getValue(), e_time, e_picker_upd.getValue(), e_picker2_upd.getValue(), e_picker3_upd.getValue(), b_gdw, e_gdw, b_gtw, e_gtw, sclass.parser_str(list_at_wr_upd.getValue(), 0), sclass.parser_str(resp_wr_upd4.getValue(), 0), sclass.parser_str(resp_wr_upd5.getValue(), 0), sclass.parser_str(resp_wr_upd6.getValue(), 0), sclass.parser_str(resp_wr_upd7.getValue(), 0), sclass.parser_str(resp_wr_upd8.getValue(), 0), w_data_b4_upd.getValue(), w_data_b5_upd.getValue(), w_data_b6_upd.getValue(), w_data_b7_upd.getValue(), w_data_b8_upd.getValue(), w_data_e4_upd.getValue(), w_data_e5_upd.getValue(), w_data_e6_upd.getValue(), w_data_e7_upd.getValue(), w_data_e8_upd.getValue(), actual_time1_wr_upd4.getText(), actual_time1_wr_upd5.getText(), actual_time1_wr_upd6.getText(), actual_time1_wr_upd7.getText(), actual_time1_wr_upd8.getText(), b_picker4_upd.getValue(), b_picker5_upd.getValue(), b_picker6_upd.getValue(), b_picker7_upd.getValue(), b_picker8_upd.getValue(), e_picker4_upd.getValue(), e_picker5_upd.getValue(), e_picker6_upd.getValue(), e_picker7_upd.getValue(), e_picker8_upd.getValue());
							qr._update_r_wr(id_wr, "_Resp4", "0");
							qr._update_r_wr(id_wr, "_Actual_Time4", "0");
						}
						if(!qr._select_resp(id_wr, "_Resp5").equals("0"))
						{
							LocalDate b_data = fx_dp.fromString(qr._select_b_hours(id_wr, "_Actual_Date_5"));
							LocalDate e_data = fx_dp.fromString(qr._select_b_hours(id_wr, "_Actual_Date5"));
							LocalTime b_time = fx_time.fromStringt(qr._select_b_hours(id_wr, "_Hours1_5"));
							LocalTime e_time = fx_time.fromStringt(qr._select_b_hours(id_wr, "_Hours2_5"));
							String at = qr._select_b_hours(id_wr, "_Actual_Time5");
							wt_rezult = 0;
										
							qr._insert_wr(numap_wr_upd.getText(), conn_connector.USER_ID, sclass.parser_str(shop_wr_upd.getValue(), 0), group_wr_upd.getValue(), sclass.parser_str(lm_wr_upd.getValue(), 0), sclass.parser_str(os_wr_upd.getValue(), 0), sclass.parser_str(equip_wr_upd.getValue(), 0), sclass.parser_str(shop_wr_upd.getValue(), 0)+"."+group_wr_upd.getValue()+"."+sclass.parser_str(lm_wr_upd.getValue(), 0)+"."+sclass.parser_str(os_wr_upd.getValue(), 0)+"."+sclass.parser_str(equip_wr_upd.getValue(), 0), record_type_wr_upd.getValue(), wt_rezult, sclass.parser_str(qr._select_resp(id_wr, "_Resp5"), 0), "0", "0", "0", status_wr_upd.getValue(), shift_report_wr_upd.getText(), req_action_wr_upd.getText(), b_data, w_data_b_upd.getValue(), w_data_b2_upd.getValue(), w_data_b3_upd.getValue(), actual_time_wr_upd.getText(), e_data, w_data_e_upd.getValue(), w_data_e2_upd.getValue(), w_data_e3_upd.getValue(), at, "0", "0", "0", b_time, b_picker_upd.getValue(), b_picker2_upd.getValue(), b_picker3_upd.getValue(), e_time, e_picker_upd.getValue(), e_picker2_upd.getValue(), e_picker3_upd.getValue(), b_gdw, e_gdw, b_gtw, e_gtw, sclass.parser_str(list_at_wr_upd.getValue(), 0), sclass.parser_str(resp_wr_upd4.getValue(), 0), sclass.parser_str(resp_wr_upd5.getValue(), 0), sclass.parser_str(resp_wr_upd6.getValue(), 0), sclass.parser_str(resp_wr_upd7.getValue(), 0), sclass.parser_str(resp_wr_upd8.getValue(), 0), w_data_b4_upd.getValue(), w_data_b5_upd.getValue(), w_data_b6_upd.getValue(), w_data_b7_upd.getValue(), w_data_b8_upd.getValue(), w_data_e4_upd.getValue(), w_data_e5_upd.getValue(), w_data_e6_upd.getValue(), w_data_e7_upd.getValue(), w_data_e8_upd.getValue(), actual_time1_wr_upd4.getText(), actual_time1_wr_upd5.getText(), actual_time1_wr_upd6.getText(), actual_time1_wr_upd7.getText(), actual_time1_wr_upd8.getText(), b_picker4_upd.getValue(), b_picker5_upd.getValue(), b_picker6_upd.getValue(), b_picker7_upd.getValue(), b_picker8_upd.getValue(), e_picker4_upd.getValue(), e_picker5_upd.getValue(), e_picker6_upd.getValue(), e_picker7_upd.getValue(), e_picker8_upd.getValue());
							qr._update_r_wr(id_wr, "_Resp5", "0");
							qr._update_r_wr(id_wr, "_Actual_Time5", "0");
						}
						if(!qr._select_resp(id_wr, "_Resp6").equals("0"))
						{
							LocalDate b_data = fx_dp.fromString(qr._select_b_hours(id_wr, "_Actual_Date_6"));
							LocalDate e_data = fx_dp.fromString(qr._select_b_hours(id_wr, "_Actual_Date6"));
							LocalTime b_time = fx_time.fromStringt(qr._select_b_hours(id_wr, "_Hours1_6"));
							LocalTime e_time = fx_time.fromStringt(qr._select_b_hours(id_wr, "_Hours2_6"));
							String at = qr._select_b_hours(id_wr, "_Actual_Time6");
							wt_rezult = 0;
										
							qr._insert_wr(numap_wr_upd.getText(), conn_connector.USER_ID, sclass.parser_str(shop_wr_upd.getValue(), 0), group_wr_upd.getValue(), sclass.parser_str(lm_wr_upd.getValue(), 0), sclass.parser_str(os_wr_upd.getValue(), 0), sclass.parser_str(equip_wr_upd.getValue(), 0), sclass.parser_str(shop_wr_upd.getValue(), 0)+"."+group_wr_upd.getValue()+"."+sclass.parser_str(lm_wr_upd.getValue(), 0)+"."+sclass.parser_str(os_wr_upd.getValue(), 0)+"."+sclass.parser_str(equip_wr_upd.getValue(), 0), record_type_wr_upd.getValue(), wt_rezult, sclass.parser_str(qr._select_resp(id_wr, "_Resp6"), 0), "0", "0", "0", status_wr_upd.getValue(), shift_report_wr_upd.getText(), req_action_wr_upd.getText(), b_data, w_data_b_upd.getValue(), w_data_b2_upd.getValue(), w_data_b3_upd.getValue(), actual_time_wr_upd.getText(), e_data, w_data_e_upd.getValue(), w_data_e2_upd.getValue(), w_data_e3_upd.getValue(), at, "0", "0", "0", b_time, b_picker_upd.getValue(), b_picker2_upd.getValue(), b_picker3_upd.getValue(), e_time, e_picker_upd.getValue(), e_picker2_upd.getValue(), e_picker3_upd.getValue(), b_gdw, e_gdw, b_gtw, e_gtw, sclass.parser_str(list_at_wr_upd.getValue(), 0), sclass.parser_str(resp_wr_upd4.getValue(), 0), sclass.parser_str(resp_wr_upd5.getValue(), 0), sclass.parser_str(resp_wr_upd6.getValue(), 0), sclass.parser_str(resp_wr_upd7.getValue(), 0), sclass.parser_str(resp_wr_upd8.getValue(), 0), w_data_b4_upd.getValue(), w_data_b5_upd.getValue(), w_data_b6_upd.getValue(), w_data_b7_upd.getValue(), w_data_b8_upd.getValue(), w_data_e4_upd.getValue(), w_data_e5_upd.getValue(), w_data_e6_upd.getValue(), w_data_e7_upd.getValue(), w_data_e8_upd.getValue(), actual_time1_wr_upd4.getText(), actual_time1_wr_upd5.getText(), actual_time1_wr_upd6.getText(), actual_time1_wr_upd7.getText(), actual_time1_wr_upd8.getText(), b_picker4_upd.getValue(), b_picker5_upd.getValue(), b_picker6_upd.getValue(), b_picker7_upd.getValue(), b_picker8_upd.getValue(), e_picker4_upd.getValue(), e_picker5_upd.getValue(), e_picker6_upd.getValue(), e_picker7_upd.getValue(), e_picker8_upd.getValue());
							qr._update_r_wr(id_wr, "_Resp6", "0");
							qr._update_r_wr(id_wr, "_Actual_Time6", "0");
						}
						if(!qr._select_resp(id_wr, "_Resp7").equals("0"))
						{
							LocalDate b_data = fx_dp.fromString(qr._select_b_hours(id_wr, "_Actual_Date_7"));
							LocalDate e_data = fx_dp.fromString(qr._select_b_hours(id_wr, "_Actual_Date7"));
							LocalTime b_time = fx_time.fromStringt(qr._select_b_hours(id_wr, "_Hours1_7"));
							LocalTime e_time = fx_time.fromStringt(qr._select_b_hours(id_wr, "_Hours2_7"));
							String at = qr._select_b_hours(id_wr, "_Actual_Time7");
							wt_rezult = 0;
										
							qr._insert_wr(numap_wr_upd.getText(), conn_connector.USER_ID, sclass.parser_str(shop_wr_upd.getValue(), 0), group_wr_upd.getValue(), sclass.parser_str(lm_wr_upd.getValue(), 0), sclass.parser_str(os_wr_upd.getValue(), 0), sclass.parser_str(equip_wr_upd.getValue(), 0), sclass.parser_str(shop_wr_upd.getValue(), 0)+"."+group_wr_upd.getValue()+"."+sclass.parser_str(lm_wr_upd.getValue(), 0)+"."+sclass.parser_str(os_wr_upd.getValue(), 0)+"."+sclass.parser_str(equip_wr_upd.getValue(), 0), record_type_wr_upd.getValue(), wt_rezult, sclass.parser_str(qr._select_resp(id_wr, "_Resp7"), 0), "0", "0", "0", status_wr_upd.getValue(), shift_report_wr_upd.getText(), req_action_wr_upd.getText(), b_data, w_data_b_upd.getValue(), w_data_b2_upd.getValue(), w_data_b3_upd.getValue(), actual_time_wr_upd.getText(), e_data, w_data_e_upd.getValue(), w_data_e2_upd.getValue(), w_data_e3_upd.getValue(), at, "0", "0", "0", b_time, b_picker_upd.getValue(), b_picker2_upd.getValue(), b_picker3_upd.getValue(), e_time, e_picker_upd.getValue(), e_picker2_upd.getValue(), e_picker3_upd.getValue(), b_gdw, e_gdw, b_gtw, e_gtw, sclass.parser_str(list_at_wr_upd.getValue(), 0), sclass.parser_str(resp_wr_upd4.getValue(), 0), sclass.parser_str(resp_wr_upd5.getValue(), 0), sclass.parser_str(resp_wr_upd6.getValue(), 0), sclass.parser_str(resp_wr_upd7.getValue(), 0), sclass.parser_str(resp_wr_upd8.getValue(), 0), w_data_b4_upd.getValue(), w_data_b5_upd.getValue(), w_data_b6_upd.getValue(), w_data_b7_upd.getValue(), w_data_b8_upd.getValue(), w_data_e4_upd.getValue(), w_data_e5_upd.getValue(), w_data_e6_upd.getValue(), w_data_e7_upd.getValue(), w_data_e8_upd.getValue(), actual_time1_wr_upd4.getText(), actual_time1_wr_upd5.getText(), actual_time1_wr_upd6.getText(), actual_time1_wr_upd7.getText(), actual_time1_wr_upd8.getText(), b_picker4_upd.getValue(), b_picker5_upd.getValue(), b_picker6_upd.getValue(), b_picker7_upd.getValue(), b_picker8_upd.getValue(), e_picker4_upd.getValue(), e_picker5_upd.getValue(), e_picker6_upd.getValue(), e_picker7_upd.getValue(), e_picker8_upd.getValue());
							qr._update_r_wr(id_wr, "_Resp7", "0");
							qr._update_r_wr(id_wr, "_Actual_Time7", "0");
						}
						if(!qr._select_resp(id_wr, "_Resp8").equals("0"))
						{
							LocalDate b_data = fx_dp.fromString(qr._select_b_hours(id_wr, "_Actual_Date_8"));
							LocalDate e_data = fx_dp.fromString(qr._select_b_hours(id_wr, "_Actual_Date8"));
							LocalTime b_time = fx_time.fromStringt(qr._select_b_hours(id_wr, "_Hours1_8"));
							LocalTime e_time = fx_time.fromStringt(qr._select_b_hours(id_wr, "_Hours2_8"));
							String at = qr._select_b_hours(id_wr, "_Actual_Time8");
							wt_rezult = 0;
										
							qr._insert_wr(numap_wr_upd.getText(), conn_connector.USER_ID, sclass.parser_str(shop_wr_upd.getValue(), 0), group_wr_upd.getValue(), sclass.parser_str(lm_wr_upd.getValue(), 0), sclass.parser_str(os_wr_upd.getValue(), 0), sclass.parser_str(equip_wr_upd.getValue(), 0), sclass.parser_str(shop_wr_upd.getValue(), 0)+"."+group_wr_upd.getValue()+"."+sclass.parser_str(lm_wr_upd.getValue(), 0)+"."+sclass.parser_str(os_wr_upd.getValue(), 0)+"."+sclass.parser_str(equip_wr_upd.getValue(), 0), record_type_wr_upd.getValue(), wt_rezult, sclass.parser_str(qr._select_resp(id_wr, "_Resp8"), 0), "0", "0", "0", status_wr_upd.getValue(), shift_report_wr_upd.getText(), req_action_wr_upd.getText(), b_data, w_data_b_upd.getValue(), w_data_b2_upd.getValue(), w_data_b3_upd.getValue(), actual_time_wr_upd.getText(), e_data, w_data_e_upd.getValue(), w_data_e2_upd.getValue(), w_data_e3_upd.getValue(), at, "0", "0", "0", b_time, b_picker_upd.getValue(), b_picker2_upd.getValue(), b_picker3_upd.getValue(), e_time, e_picker_upd.getValue(), e_picker2_upd.getValue(), e_picker3_upd.getValue(), b_gdw, e_gdw, b_gtw, e_gtw, sclass.parser_str(list_at_wr_upd.getValue(), 0), sclass.parser_str(resp_wr_upd4.getValue(), 0), sclass.parser_str(resp_wr_upd5.getValue(), 0), sclass.parser_str(resp_wr_upd6.getValue(), 0), sclass.parser_str(resp_wr_upd7.getValue(), 0), sclass.parser_str(resp_wr_upd8.getValue(), 0), w_data_b4_upd.getValue(), w_data_b5_upd.getValue(), w_data_b6_upd.getValue(), w_data_b7_upd.getValue(), w_data_b8_upd.getValue(), w_data_e4_upd.getValue(), w_data_e5_upd.getValue(), w_data_e6_upd.getValue(), w_data_e7_upd.getValue(), w_data_e8_upd.getValue(), actual_time1_wr_upd4.getText(), actual_time1_wr_upd5.getText(), actual_time1_wr_upd6.getText(), actual_time1_wr_upd7.getText(), actual_time1_wr_upd8.getText(), b_picker4_upd.getValue(), b_picker5_upd.getValue(), b_picker6_upd.getValue(), b_picker7_upd.getValue(), b_picker8_upd.getValue(), e_picker4_upd.getValue(), e_picker5_upd.getValue(), e_picker6_upd.getValue(), e_picker7_upd.getValue(), e_picker8_upd.getValue());
							qr._update_r_wr(id_wr, "_Resp8", "0");
							qr._update_r_wr(id_wr, "_Actual_Time8", "0");
						}
						if(!qr._select_resp(id_wr, "_Resp9").equals("0"))
						{
							LocalDate b_data = fx_dp.fromString(qr._select_b_hours(id_wr, "_Actual_Date_9"));
							LocalDate e_data = fx_dp.fromString(qr._select_b_hours(id_wr, "_Actual_Date9"));
							LocalTime b_time = fx_time.fromStringt(qr._select_b_hours(id_wr, "_Hours1_9"));
							LocalTime e_time = fx_time.fromStringt(qr._select_b_hours(id_wr, "_Hours2_9"));
							String at = qr._select_b_hours(id_wr, "_Actual_Time9");
							wt_rezult = 0;
										
							qr._insert_wr(numap_wr_upd.getText(), conn_connector.USER_ID, sclass.parser_str(shop_wr_upd.getValue(), 0), group_wr_upd.getValue(), sclass.parser_str(lm_wr_upd.getValue(), 0), sclass.parser_str(os_wr_upd.getValue(), 0), sclass.parser_str(equip_wr_upd.getValue(), 0), sclass.parser_str(shop_wr_upd.getValue(), 0)+"."+group_wr_upd.getValue()+"."+sclass.parser_str(lm_wr_upd.getValue(), 0)+"."+sclass.parser_str(os_wr_upd.getValue(), 0)+"."+sclass.parser_str(equip_wr_upd.getValue(), 0), record_type_wr_upd.getValue(), wt_rezult, sclass.parser_str(qr._select_resp(id_wr, "_Resp9"), 0), "0", "0", "0", status_wr_upd.getValue(), shift_report_wr_upd.getText(), req_action_wr_upd.getText(), b_data, w_data_b_upd.getValue(), w_data_b2_upd.getValue(), w_data_b3_upd.getValue(), actual_time_wr_upd.getText(), e_data, w_data_e_upd.getValue(), w_data_e2_upd.getValue(), w_data_e3_upd.getValue(), at, "0", "0", "0", b_time, b_picker_upd.getValue(), b_picker2_upd.getValue(), b_picker3_upd.getValue(), e_time, e_picker_upd.getValue(), e_picker2_upd.getValue(), e_picker3_upd.getValue(), b_gdw, e_gdw, b_gtw, e_gtw, sclass.parser_str(list_at_wr_upd.getValue(), 0), sclass.parser_str(resp_wr_upd4.getValue(), 0), sclass.parser_str(resp_wr_upd5.getValue(), 0), sclass.parser_str(resp_wr_upd6.getValue(), 0), sclass.parser_str(resp_wr_upd7.getValue(), 0), sclass.parser_str(resp_wr_upd8.getValue(), 0), w_data_b4_upd.getValue(), w_data_b5_upd.getValue(), w_data_b6_upd.getValue(), w_data_b7_upd.getValue(), w_data_b8_upd.getValue(), w_data_e4_upd.getValue(), w_data_e5_upd.getValue(), w_data_e6_upd.getValue(), w_data_e7_upd.getValue(), w_data_e8_upd.getValue(), actual_time1_wr_upd4.getText(), actual_time1_wr_upd5.getText(), actual_time1_wr_upd6.getText(), actual_time1_wr_upd7.getText(), actual_time1_wr_upd8.getText(), b_picker4_upd.getValue(), b_picker5_upd.getValue(), b_picker6_upd.getValue(), b_picker7_upd.getValue(), b_picker8_upd.getValue(), e_picker4_upd.getValue(), e_picker5_upd.getValue(), e_picker6_upd.getValue(), e_picker7_upd.getValue(), e_picker8_upd.getValue());
							qr._update_r_wr(id_wr, "_Resp9", "0");
							qr._update_r_wr(id_wr, "_Actual_Time9", "0");
						}
								
						//Если мы апдейтим запись в WR по задаче из AP то меняем цвет для поля исполнитель на желтый, т.к. эта новая запись еще не проверенна
						//qr._update_otv_ap(numap_wr_upd.getText(), "flag_otv", "1");
						//qr._update_otv_ap(numap_wr_upd.getText(), "flag_oft", "0");
						//qr._update_otv_ap(numap_wr_upd.getText(), "flag_tm", "0");
								
						//апдейтим таблицу WR в зависимости от установленного файла сортировки, т. е. какая сортировка была включена на момент апдейта, 
						//такую и оставляем
								
						if(pic.flag == 1)
							pic._table_update_wr.addAll(qr._select_sort_apnum_wr(id_wr.substring(2)));
						if(pic.flag == 2)
							pic._table_update_wr.addAll(qr._select_data_wr(apwr_controller.before_date, apwr_controller.after_date));
						if(pic.flag == 0)
							pic._table_update_wr.addAll(qr._select_data_wr(apwr_controller.before_date, apwr_controller.after_date));
						if(pic.flag == 3)
							pic._table_update_wr.addAll(qr._select_sort_shop_wr(apwr_controller.before_date, apwr_controller.after_date, pic.SORT_SHOP));
						//System.out.println("UPDREC = " + pic.SORT_RESP);
						if(pic.flag == 4)
							pic._table_update_wr.addAll(qr._select_sort_resp_wr(apwr_controller.before_date, apwr_controller.after_date, sclass.parser_str(pic.SORT_RESP, 0)));
						confirm_rec_wr.setVisible(false);
								
						//Если мы апдейтим запись в WR по задаче из AP то меняем цвет для поля исполнитель на желтый, т.к. эта запись еще не проверенна
						//qr._update_otv_ap(qr._select_last_id("hmmr_action_plan"), "flag_otv", "0");
						//qr._update_otv_ap(qr._select_last_id("hmmr_action_plan"), "flag_oft", "0");
						//qr._update_otv_ap(qr._select_last_id("hmmr_action_plan"), "flag_tm", "0");
						//рефрешим таблицу AP
						pic._table_update.addAll(qr._select_data_ap(pic.USER_S));
						
						stage = (Stage) add_wr_upd.getScene().getWindow();
						stage.close();
					}
				});
				
				sclass._style(cancel_wr_upd);
				
				cancel_wr_upd.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						stage = (Stage) add_wr_upd.getScene().getWindow();
						stage.close();
					}
				});
				
				plus.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						lbl_trt_wr1.setDisable(false);
						w_data_b1_upd.setDisable(false);
						b_picker1_upd.setDisable(false);
						w_data_e1_upd.setDisable(false);
						e_picker1_upd.setDisable(false);
						resp_wr1.setDisable(false);
						resp_wr_upd2.setDisable(false);
						actual_time1_wr1.setDisable(false);
						actual_time1_wr_upd2.setDisable(true);
						plus1.setDisable(false);
						minus1.setDisable(false);
						plus.setDisable(true);
						
						if(resp_wr_upd2.getValue().equals("0")) {
							w_data_b1_upd.setValue(w_data_b_upd.getValue());
							w_data_e1_upd.setValue(w_data_e_upd.getValue());
							b_picker1_upd.setValue(b_picker_upd.getValue());
							e_picker1_upd.setValue(e_picker_upd.getValue());
						}
						
						actual_time1_wr_upd2.setText(""+fix_time(w_data_b1_upd, w_data_e1_upd, b_picker1_upd, e_picker1_upd));
					}
				});
				minus1.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						lbl_trt_wr1.setDisable(true);
						w_data_b1_upd.setDisable(true);
						b_picker1_upd.setDisable(true);
						w_data_e1_upd.setDisable(true);
						e_picker1_upd.setDisable(true);
						resp_wr1.setDisable(true);
						resp_wr_upd2.setDisable(true);
						actual_time1_wr1.setDisable(true);
						actual_time1_wr_upd2.setDisable(true);
						plus1.setDisable(true);
						minus1.setDisable(true);
						plus.setDisable(false);
					}
				});
				plus1.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						lbl_trt_wr2.setDisable(false);
						w_data_b2_upd.setDisable(false);
						b_picker2_upd.setDisable(false);
						w_data_e2_upd.setDisable(false);
						e_picker2_upd.setDisable(false);
						resp_wr2.setDisable(false);
						resp_wr_upd3.setDisable(false);
						actual_time1_wr2.setDisable(false);
						actual_time1_wr_upd2.setDisable(true);
						actual_time1_wr_upd3.setDisable(true);
						plus2.setDisable(false);
						minus2.setDisable(false);
						minus1.setDisable(true);
						plus.setDisable(true);
						plus1.setDisable(true);
						if(resp_wr_upd3.getValue().equals("0")) {
							w_data_b2_upd.setValue(w_data_b_upd.getValue());
							w_data_e2_upd.setValue(w_data_e_upd.getValue());
							b_picker2_upd.setValue(b_picker_upd.getValue());
							e_picker2_upd.setValue(e_picker_upd.getValue());
						}
						actual_time1_wr_upd3.setText(""+fix_time(w_data_b2_upd, w_data_e2_upd, b_picker2_upd, e_picker2_upd));
					}
				});
				minus2.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						lbl_trt_wr2.setDisable(true);
						w_data_b2_upd.setDisable(true);
						b_picker2_upd.setDisable(true);
						w_data_e2_upd.setDisable(true);
						e_picker2_upd.setDisable(true);
						resp_wr2.setDisable(true);
						resp_wr_upd3.setDisable(true);
						actual_time1_wr2.setDisable(true);
						//actual_time1_wr_upd2.setDisable(true);
						actual_time1_wr_upd3.setDisable(true);
						plus1.setDisable(false);
						minus1.setDisable(false);
						plus.setDisable(false);
						plus2.setDisable(true);
						minus2.setDisable(true);
					}
				});
				plus2.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						lbl_trt_wr3.setDisable(false);
						w_data_b3_upd.setDisable(false);
						b_picker3_upd.setDisable(false);
						w_data_e3_upd.setDisable(false);
						e_picker3_upd.setDisable(false);
						resp_wr3.setDisable(false);
						//resp_wr_upd3.setDisable(false);
						
						resp_wr_upd4.setDisable(false);
						actual_time1_wr3.setDisable(false);
						actual_time1_wr_upd3.setDisable(true);
						actual_time1_wr_upd4.setDisable(true);
						plus3.setDisable(false);
						minus2.setDisable(true);
						minus3.setDisable(false);
						plus.setDisable(true);
						plus1.setDisable(true);
						plus2.setDisable(true);
						if(resp_wr_upd4.getValue().equals("0")) {
							w_data_b3_upd.setValue(w_data_b_upd.getValue());
							w_data_e3_upd.setValue(w_data_e_upd.getValue());
							b_picker3_upd.setValue(b_picker_upd.getValue());
							e_picker3_upd.setValue(e_picker_upd.getValue());
						}
						actual_time1_wr_upd4.setText(""+fix_time(w_data_b3_upd, w_data_e3_upd, b_picker3_upd, e_picker3_upd));
					}
				});
				minus3.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						lbl_trt_wr3.setDisable(true);
						w_data_b3_upd.setDisable(true);
						b_picker3_upd.setDisable(true);
						w_data_e3_upd.setDisable(true);
						e_picker3_upd.setDisable(true);
						resp_wr3.setDisable(true);
						resp_wr_upd4.setDisable(true);
						actual_time1_wr3.setDisable(true);
						//actual_time1_wr_upd3.setDisable(true);
						actual_time1_wr_upd4.setDisable(true);
						//plus1.setDisable(false);
						//minus1.setDisable(false);
						//plus.setDisable(false);
						plus2.setDisable(false);
						plus3.setDisable(true);
						minus2.setDisable(false);
						minus3.setDisable(true);
					}
				});
				plus3.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						w_data_b4_upd.setDisable(false);
						b_picker4_upd.setDisable(false);
						w_data_e4_upd.setDisable(false);
						e_picker4_upd.setDisable(false);
						resp_wr4.setDisable(false);
						//resp_wr_upd3.setDisable(false);
						
						resp_wr_upd5.setDisable(false);
						actual_time1_wr4.setDisable(false);
						actual_time1_wr_upd4.setDisable(true);
						actual_time1_wr_upd5.setDisable(true);
						plus4.setDisable(false);
						minus3.setDisable(true);
						minus4.setDisable(false);
						plus3.setDisable(true);
//						plus1.setDisable(true);
//						plus2.setDisable(true);
						if(resp_wr_upd5.getValue().equals("0")) {
							w_data_b4_upd.setValue(w_data_b_upd.getValue());
							w_data_e4_upd.setValue(w_data_e_upd.getValue());
							b_picker4_upd.setValue(b_picker_upd.getValue());
							e_picker4_upd.setValue(e_picker_upd.getValue());
						}
						actual_time1_wr_upd5.setText(""+fix_time(w_data_b4_upd, w_data_e4_upd, b_picker4_upd, e_picker4_upd));
					}
				});
				minus4.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						w_data_b4_upd.setDisable(true);
						b_picker4_upd.setDisable(true);
						w_data_e4_upd.setDisable(true);
						e_picker4_upd.setDisable(true);
						resp_wr4.setDisable(true);
						resp_wr_upd5.setDisable(true);
						actual_time1_wr4.setDisable(true);
						//actual_time1_wr_upd3.setDisable(true);
						actual_time1_wr_upd5.setDisable(true);
						//plus1.setDisable(false);
						//minus1.setDisable(false);
						//plus.setDisable(false);
						plus3.setDisable(false);
						plus4.setDisable(true);
						minus3.setDisable(false);
						minus4.setDisable(true);
					}
				});
				plus4.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						w_data_b5_upd.setDisable(false);
						b_picker5_upd.setDisable(false);
						w_data_e5_upd.setDisable(false);
						e_picker5_upd.setDisable(false);
						resp_wr5.setDisable(false);
						//resp_wr_upd3.setDisable(false);
						
						resp_wr_upd6.setDisable(false);
						actual_time1_wr5.setDisable(false);
						actual_time1_wr_upd5.setDisable(true);
						actual_time1_wr_upd6.setDisable(true);
						plus5.setDisable(false);
						minus4.setDisable(true);
						minus5.setDisable(false);
						plus4.setDisable(true);
//						plus1.setDisable(true);
//						plus2.setDisable(true);
						if(resp_wr_upd6.getValue().equals("0")) {
							w_data_b5_upd.setValue(w_data_b_upd.getValue());
							w_data_e5_upd.setValue(w_data_e_upd.getValue());
							b_picker5_upd.setValue(b_picker_upd.getValue());
							e_picker5_upd.setValue(e_picker_upd.getValue());
						}
						actual_time1_wr_upd6.setText(""+fix_time(w_data_b5_upd, w_data_e5_upd, b_picker5_upd, e_picker5_upd));
					}
				});
				minus5.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						w_data_b5_upd.setDisable(true);
						b_picker5_upd.setDisable(true);
						w_data_e5_upd.setDisable(true);
						e_picker5_upd.setDisable(true);
						resp_wr5.setDisable(true);
						resp_wr_upd6.setDisable(true);
						actual_time1_wr5.setDisable(true);
						//actual_time1_wr_upd3.setDisable(true);
						actual_time1_wr_upd6.setDisable(true);
						//plus1.setDisable(false);
						//minus1.setDisable(false);
						//plus.setDisable(false);
						plus4.setDisable(false);
						plus5.setDisable(true);
						minus4.setDisable(false);
						minus5.setDisable(true);
					}
				});
				plus5.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						w_data_b6_upd.setDisable(false);
						b_picker6_upd.setDisable(false);
						w_data_e6_upd.setDisable(false);
						e_picker6_upd.setDisable(false);
						resp_wr6.setDisable(false);
						//resp_wr_upd3.setDisable(false);
						
						resp_wr_upd7.setDisable(false);
						actual_time1_wr6.setDisable(false);
						actual_time1_wr_upd6.setDisable(true);
						actual_time1_wr_upd7.setDisable(true);
						plus6.setDisable(false);
						minus5.setDisable(true);
						minus6.setDisable(false);
						plus5.setDisable(true);
//						plus1.setDisable(true);
//						plus2.setDisable(true);
						if(resp_wr_upd7.getValue().equals("0")) {
							w_data_b6_upd.setValue(w_data_b_upd.getValue());
							w_data_e6_upd.setValue(w_data_e_upd.getValue());
							b_picker6_upd.setValue(b_picker_upd.getValue());
							e_picker6_upd.setValue(e_picker_upd.getValue());
						}
						actual_time1_wr_upd7.setText(""+fix_time(w_data_b6_upd, w_data_e6_upd, b_picker6_upd, e_picker6_upd));
					}
				});
				minus6.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						w_data_b6_upd.setDisable(true);
						b_picker6_upd.setDisable(true);
						w_data_e6_upd.setDisable(true);
						e_picker6_upd.setDisable(true);
						resp_wr6.setDisable(true);
						resp_wr_upd7.setDisable(true);
						actual_time1_wr6.setDisable(true);
						//actual_time1_wr_upd3.setDisable(true);
						actual_time1_wr_upd7.setDisable(true);
						//plus1.setDisable(false);
						//minus1.setDisable(false);
						//plus.setDisable(false);
						plus5.setDisable(false);
						plus6.setDisable(true);
						minus5.setDisable(false);
						minus6.setDisable(true);
					}
				});
				plus6.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						w_data_b7_upd.setDisable(false);
						b_picker7_upd.setDisable(false);
						w_data_e7_upd.setDisable(false);
						e_picker7_upd.setDisable(false);
						resp_wr7.setDisable(false);
						//resp_wr_upd3.setDisable(false);
						
						resp_wr_upd8.setDisable(false);
						actual_time1_wr7.setDisable(false);
						actual_time1_wr_upd7.setDisable(true);
						actual_time1_wr_upd8.setDisable(true);
						plus7.setDisable(false);
						minus6.setDisable(true);
						minus7.setDisable(false);
						plus6.setDisable(true);
//						plus1.setDisable(true);
//						plus2.setDisable(true);
						if(resp_wr_upd8.getValue().equals("0")) {
							w_data_b7_upd.setValue(w_data_b_upd.getValue());
							w_data_e7_upd.setValue(w_data_e_upd.getValue());
							b_picker7_upd.setValue(b_picker_upd.getValue());
							e_picker7_upd.setValue(e_picker_upd.getValue());
						}
						actual_time1_wr_upd8.setText(""+fix_time(w_data_b7_upd, w_data_e7_upd, b_picker7_upd, e_picker7_upd));
					}
				});
				e_picker_upd.setOnMouseClicked(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						actual_time1_wr_upd.setText(""+ fix_time(w_data_b_upd, w_data_e_upd, b_picker_upd, e_picker_upd));
						chk_btn();
					}
				});
				minus7.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						w_data_b7_upd.setDisable(true);
						b_picker7_upd.setDisable(true);
						w_data_e7_upd.setDisable(true);
						e_picker7_upd.setDisable(true);
						resp_wr7.setDisable(true);
						resp_wr_upd8.setDisable(true);
						actual_time1_wr7.setDisable(true);
						//actual_time1_wr_upd3.setDisable(true);
						actual_time1_wr_upd8.setDisable(true);
						//plus1.setDisable(false);
						//minus1.setDisable(false);
						//plus.setDisable(false);
						plus6.setDisable(false);
						plus7.setDisable(true);
						minus6.setDisable(false);
						minus7.setDisable(true);
					}
				});
				plus7.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						w_data_b8_upd.setDisable(false);
						b_picker8_upd.setDisable(false);
						w_data_e8_upd.setDisable(false);
						e_picker8_upd.setDisable(false);
						resp_wr8.setDisable(false);
						//resp_wr_upd3.setDisable(false);
						
						resp_wr_upd9.setDisable(false);
						actual_time1_wr8.setDisable(false);
						actual_time1_wr_upd8.setDisable(true);
						actual_time1_wr_upd9.setDisable(true);
						minus7.setDisable(true);
						minus8.setDisable(false);
						plus7.setDisable(true);
//						plus1.setDisable(true);
//						plus2.setDisable(true);
						if(resp_wr_upd9.getValue().equals("0")) {
							w_data_b8_upd.setValue(w_data_b_upd.getValue());
							w_data_e8_upd.setValue(w_data_e_upd.getValue());
							b_picker8_upd.setValue(b_picker_upd.getValue());
							e_picker8_upd.setValue(e_picker_upd.getValue());
						}
						actual_time1_wr_upd9.setText(""+fix_time(w_data_b8_upd, w_data_e8_upd, b_picker8_upd, e_picker8_upd));
					}
				});
				minus8.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						w_data_b8_upd.setDisable(true);
						b_picker8_upd.setDisable(true);
						w_data_e8_upd.setDisable(true);
						e_picker8_upd.setDisable(true);
						resp_wr8.setDisable(true);
						resp_wr_upd9.setDisable(true);
						actual_time1_wr8.setDisable(true);
						//actual_time1_wr_upd3.setDisable(true);
						actual_time1_wr_upd9.setDisable(true);
						//plus1.setDisable(false);
						//minus1.setDisable(false);
						//plus.setDisable(false);
						plus7.setDisable(false);
						minus7.setDisable(false);
						minus8.setDisable(true);
					}
				});
				e_picker_upd.setOnMouseClicked(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						actual_time1_wr_upd.setText(""+ fix_time(w_data_b_upd, w_data_e_upd, b_picker_upd, e_picker_upd));
						chk_btn();
					}
				});
				e_picker1_upd.setOnMouseClicked(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						actual_time1_wr_upd2.setText(""+ fix_time(w_data_b1_upd, w_data_e1_upd, b_picker1_upd, e_picker1_upd));
						if(!resp_wr_upd2.getValue().equals("0"))
							chk_btn();
					}
				});
				e_picker2_upd.setOnMouseClicked(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						actual_time1_wr_upd3.setText(""+ fix_time(w_data_b2_upd, w_data_e2_upd, b_picker2_upd, e_picker2_upd));
						if(!resp_wr_upd3.getValue().equals("0"))
							chk_btn();
					}
				});
				e_picker3_upd.setOnMouseClicked(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						actual_time1_wr_upd4.setText(""+ fix_time(w_data_b3_upd, w_data_e3_upd, b_picker3_upd, e_picker3_upd));
						if(!resp_wr_upd4.getValue().equals("0"))
							chk_btn();
					}
				});
				
				//Пересчитываем время работы техника, если кликнули не только по часикам, но и в любом месте панели
				pane.setOnMouseClicked(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						actual_time1_wr_upd.setText(""+ fix_time(w_data_b_upd, w_data_e_upd, b_picker_upd, e_picker_upd));
						actual_time1_wr_upd2.setText(""+ fix_time(w_data_b1_upd, w_data_e1_upd, b_picker1_upd, e_picker1_upd));
						actual_time1_wr_upd3.setText(""+ fix_time(w_data_b2_upd, w_data_e2_upd, b_picker2_upd, e_picker2_upd));
						actual_time1_wr_upd4.setText(""+ fix_time(w_data_b3_upd, w_data_e3_upd, b_picker3_upd, e_picker3_upd));
					}
				});
				//Пересчитываем время работы техника, если кликнули не только по часикам, но и при клике на один из ComboBox выбора исполнителя
				resp_wr_upd.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						actual_time1_wr_upd.setText(""+ fix_time(w_data_b_upd, w_data_e_upd, b_picker_upd, e_picker_upd));
					}
				});
				resp_wr_upd2.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						actual_time1_wr_upd2.setText(""+ fix_time(w_data_b1_upd, w_data_e1_upd, b_picker1_upd, e_picker1_upd));
					}
				});
				resp_wr_upd3.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						actual_time1_wr_upd3.setText(""+ fix_time(w_data_b2_upd, w_data_e2_upd, b_picker2_upd, e_picker2_upd));
					}
				});
				resp_wr_upd4.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						actual_time1_wr_upd4.setText(""+ fix_time(w_data_b3_upd, w_data_e3_upd, b_picker3_upd, e_picker3_upd));
					}
				});
	}
	
	private void chk_wt(String id)
	{
		if(qr._select_confirm_wt(id).equals("YES"))
		{
			w_data_b_upd15.setDisable(true);
			w_data_e_upd15.setDisable(true);
			b_picker_upd15.setDisable(true);
			e_picker_upd15.setDisable(true);
			
			
			if(!qr._select_confirm_wt_data(id).equals("NO")) {
				w_data_b_upd15.setValue(fx_dp.fromString(sclass.parser_sql_str(qr._select_confirm_wt_data(id), 0)));
				w_data_e_upd15.setValue(fx_dp.fromString(sclass.parser_sql_str(qr._select_confirm_wt_data(id), 1)));
				b_picker_upd15.setValue(fx_time.fromStringt(sclass.parser_sql_str(qr._select_confirm_wt_data(id), 2)));
				e_picker_upd15.setValue(fx_time.fromStringt(sclass.parser_sql_str(qr._select_confirm_wt_data(id), 3)));
			}
			//chk_btn();
		}	
	}
	private void lang_fun(String loc1, String loc2)
	{
		ResourceBundle lngBndl = ResourceBundle
	            .getBundle("bundles.LangBundle", new Locale(loc1, loc2));
		
		lbl_add_rec_wr_upd.setText(lngBndl.getString("lbl_add_rec_wr_upd"));
		shift_report_wr.setText(lngBndl.getString("shift_report_wr")+":");
		req_action_wr.setText(lngBndl.getString("req_action_wr")+":");
		lbl_trt_wr.setText(lngBndl.getString("lbl_trt_wr"));
		lbl_shop_ap.setText(lngBndl.getString("lbl_shop_ap"));
		lbl_group_ap.setText(lngBndl.getString("lbl_group_ap"));
		lbl_lm_ap.setText(lngBndl.getString("lbl_lm_ap"));
		lbl_os_ap.setText(lngBndl.getString("lbl_os_ap"));
		lbl_equip_ap.setText(lngBndl.getString("lbl_equip_ap"));
		actual_time_wr.setText(lngBndl.getString("actual_time_wr")+":");
		actual_time1_wr.setText(lngBndl.getString("actual_time1_wr")+":");
		record_type_wr.setText(lngBndl.getString("record_type_wr")+":");
		resp_wr.setText(lngBndl.getString("resp_wr")+":");
		status_wr.setText(lngBndl.getString("status_wr")+":");
		add_wr_upd.setText(lngBndl.getString("lbl_apply"));
		cancel_wr_upd.setText(lngBndl.getString("cancel_tsk"));
		
		lbl_trt_wr1.setText(lngBndl.getString("lbl_trt_wr"));
		lbl_trt_wr2.setText(lngBndl.getString("lbl_trt_wr"));
		lbl_trt_wr3.setText(lngBndl.getString("lbl_trt_wr"));
		actual_time1_wr1.setText(lngBndl.getString("actual_time1_wr")+":");
		actual_time1_wr2.setText(lngBndl.getString("actual_time1_wr")+":");
		actual_time1_wr3.setText(lngBndl.getString("actual_time1_wr")+":");
		resp_wr1.setText(lngBndl.getString("resp_wr")+":");
		resp_wr2.setText(lngBndl.getString("resp_wr")+":");
		resp_wr3.setText(lngBndl.getString("resp_wr")+":");
		confirm_rec_wr.setText(lngBndl.getString("confirm_rec_wr"));
	}
	private void chk_btn()
	{
		try {
			if(shift_report_wr_upd.getText().length() != 0 && req_action_wr_upd.getText().length() != 0 && actual_time_wr_upd.getText().length() != 0 &&
					actual_time1_wr_upd.getText().length() != 0 && numap_wr_upd.getText().length() != 0 && 
					shop_wr_upd.getValue().length() != 0 && group_wr_upd.getValue().length() != 0 && lm_wr_upd.getValue().length() != 0 && os_wr_upd.getValue().length() != 0 &&
					equip_wr_upd.getValue().length() != 0 && record_type_wr_upd.getValue().length() != 0 && resp_wr_upd.getValue().length() != 0 &&
					status_wr_upd.getValue().length() != 0 && w_data_b_upd.getValue().toString().length() != 0 && b_picker_upd.getValue().toString().length() != 0 &&
					w_data_e_upd.getValue().toString().length() != 0 && e_picker_upd.getValue().toString().length() != 0)
			{
				//разрешаем редактировать запись только тому кто ее создал и Администраторам
				if(conn_connector.USER_ID.equals(user_number) || conn_connector.USER_ROLE.equals("Administrator"))
					add_wr_upd.setDisable(false);
			}
			else
				add_wr_upd.setDisable(true);
			}
			catch (Exception e) {
				
			}
	}
	
	private int fix_time(DatePicker d1, DatePicker d2, JFXTimePicker t1, JFXTimePicker t2)
	{
		int wt_rezult1 = 0;
		int d_b = d1.getValue().getDayOfYear();
		int d_e = d2.getValue().getDayOfYear();
		
		int data_rezult = d_e - d_b;
		//Обрезаем секунды
		//int hours_b = Integer.parseInt(sclass.parser_double_dot(t1.getValue().toString(), 0));
		//int min_b = Integer.parseInt(sclass.parser_double_dot(t1.getValue().toString(), 1));
		//int hours_e = Integer.parseInt(sclass.parser_double_dot(t2.getValue().toString(), 0));
		//int min_e = Integer.parseInt(sclass.parser_double_dot(t2.getValue().toString(), 1));
					
		//int time_rezult = Math.abs(hours_e - hours_b)*60 + Math.abs(min_e - min_b);
		
		LocalTime test = t1.getValue();
		LocalTime test2 = t2.getValue();
		
		//long h_between = ChronoUnit.HOURS.between(test, test2);
		long m_between = ChronoUnit.MINUTES.between(test, test2);
		
		int time_rezult = Math.abs((int) m_between);
		
		wt_rezult1 = data_rezult*24*60 + time_rezult;
		//System.out.println("M_BETWEEN = " + m_between +" HOURS_B = "+hours_b+" MIN_B = "+min_b+" HOURS_E = "+hours_e+" MIN_E = "+min_e+" TIME REZUL = "+time_rezult+" WT REZULT = "+wt_rezult1);
		return wt_rezult1;
	}
}
