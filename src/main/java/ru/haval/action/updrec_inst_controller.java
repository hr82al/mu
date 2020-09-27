package ru.haval.action;

import java.io.File;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

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
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import ru.haval.share_class.s_class;

public class updrec_inst_controller
{
	@FXML
	ComboBox<String> typepm_inst_upd, cyclepm1_inst_upd, cyclepm2_inst_upd, pos_inst_upd, line_inst_upd, power_inst_upd, sinfo_inst_upd, list_adm2, list_adm3, list_of1, list_of2;
	
	@FXML
	TextField ninst_inst_upd, ver_inst_upd, mt_inst_upd, pmname_inst_upd, sdoc_txt_inst_upd, qtyspec_inst_upd, ptw_inst_upd, wt_inst_upd, adm2_inst_upd, adm3_inst_upd, of1_inst_upd, of2_inst_upd, inst_pdf_pi;
	
	@FXML
	JFXButton sdoc_inst_upd, add_confirm_inst_upd, add_cancel_inst_upd, exp_inst_pdf;
	
	@FXML
	Label error_msg_upd, head_upd_inst, col_ninst_inst, col_ver_inst, col_mt_inst, col_pmn_inst, col_pmt_inst, col_pmc1_inst, col_pmc2_inst, col_ool_inst,
	 col_oop_inst, col_pos_inst, col_sinfo_inst, col_sdoc_inst, col_qspec_inst, col_pty_inst, col_wt_inst, col_adm2_inst,
	 col_adm3_inst, col_of1_inst, col_of2_inst;
	
	@FXML
	DatePicker date_create_pi, date_change_pi;
	
	private Stage stage;	
	_query qr = new _query();
	pm_inst_controller pic = new pm_inst_controller();
	typepm_model_inst tmi = new typepm_model_inst();
	s_class sclass = new s_class();
	FxDatePickerConverter fx_dp = new FxDatePickerConverter();
	Tooltip tip;	
	private static String pathToPdf = "\\\\10.168.150.74\\MU\\Instruction_PDF", pathToPdf1 = "C://";//user.home
	
	@SuppressWarnings("static-access")
	@FXML
	public void initialize()
	{
		
		//разрешаем вводить только цифры и не больше трех
		try {
				ver_inst_upd.textProperty().addListener(new ChangeListener<String>() {

							@Override
							public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
								if(!newValue.isEmpty() && !newValue.equals("null") && newValue != null) {
						
								if (!newValue.matches("\\d*|#|\\*")) {
									ver_inst_upd.setText(newValue.replaceAll("[^\\d|#|\\*]", ""));
						        }
								if(newValue.length() > 3) {
									
									ver_inst_upd.setText(newValue.replaceAll("[0-9]", ""));
					            	
								}
							}
							}
						});
				qtyspec_inst_upd.textProperty().addListener(new ChangeListener<String>() {

							@Override
							public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
								if(!newValue.isEmpty() && !newValue.equals("null") && newValue != null) {
						
								if (!newValue.matches("\\d*|#|\\*")) {
									qtyspec_inst_upd.setText(newValue.replaceAll("[^\\d|#|\\*]", ""));
						        }
								if(newValue.length() > 2) {
									
									qtyspec_inst_upd.setText(newValue.replaceAll("[0-9]", ""));
					            	
								}
							}
							}
						});
				ptw_inst_upd.textProperty().addListener(new ChangeListener<String>() {

					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
						if(!newValue.isEmpty() && !newValue.equals("null") && newValue != null) {
				
						if (!newValue.matches("\\d*|#|\\*")) {
							ptw_inst_upd.setText(newValue.replaceAll("[^\\d|#|\\*]", ""));
				        }
						if(newValue.length() > 3) {
							
							ptw_inst_upd.setText(newValue.replaceAll("[0-9]", ""));
			            	
						}
					}
					}
				});
				wt_inst_upd.textProperty().addListener(new ChangeListener<String>() {

					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
						if(!newValue.isEmpty() && !newValue.equals("null") && newValue != null) {
				
						if (!newValue.matches("\\d*|#|\\*")) {
							wt_inst_upd.setText(newValue.replaceAll("[^\\d|#|\\*]", ""));
				        }
						if(newValue.length() > 3) {
							
							wt_inst_upd.setText(newValue.replaceAll("[0-9]", ""));
			            	
						}
					}
					}
				});
		}
		catch (Exception e) {
			
		}
				if(conn_connector.LANG_ID == 1)
					lang_fun("en", "EN");
				if(conn_connector.LANG_ID == 0)
					lang_fun("ru", "RU");
				if(conn_connector.LANG_ID == 2)
					lang_fun("zh", "CN");
				if(conn_connector.LANG_ID == -1)
					lang_fun("ru", "RU");
				
				add_confirm_inst_upd.setDisable(true);
				
				sclass._style(add_confirm_inst_upd);
				sclass._style(add_cancel_inst_upd);
				sclass._style(sdoc_inst_upd);
				sclass._style(exp_inst_pdf);
				
				date_create_pi.setValue(LocalDate.now());
				date_change_pi.setValue(LocalDate.now());
				
		//инициализируем данными combobox
				typepm_inst_upd.setItems(qr._select_typepm_inst());
				cyclepm1_inst_upd.setItems(qr._select_cycle_inst());
				cyclepm2_inst_upd.setItems(qr._select_cycle_inst());
				pos_inst_upd.setItems(qr._select_pos_inst());
				
				String line1 = new String("ON");
				String line2 = new String("OFF");
				line_inst_upd.getItems().addAll(line1, line2);
				
				String power1 = new String("ON");
				String power2 = new String("OFF");
				power_inst_upd.getItems().addAll(power1, power2);
				
				String sinfo1 = new String("M");
				String sinfo2 = new String("P");
				sinfo_inst_upd.getItems().addAll(sinfo1, sinfo2);
				
				cyclepm1_inst_upd.setOnMouseEntered(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						tip = new Tooltip("Берем ППР из документации. Если в документации 1 вариант выполнения ППР, то в PM Cycle1 и PM Cycle2 \n"
								+ " ставим одинаковые значения, например: ТО1 и ТО1, если в документации 2 варианта выполнения ППР, то в\n"
								+ " PM Cycle1 и PM Cycle2 ставим разные значения, например: ТО1 и ТО1р");
						Point2D p = cyclepm1_inst_upd.localToScreen(cyclepm1_inst_upd.getLayoutBounds().getMaxX(), cyclepm1_inst_upd.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
				        tip.show(cyclepm1_inst_upd, p.getX(), p.getY());
						//cyclepm1_inst_upd.setTooltip(tip);
					}
				});
				cyclepm1_inst_upd.setOnMouseExited(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						tip.hide();
					}
				});
				cyclepm2_inst_upd.setOnMouseEntered(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						tip = new Tooltip("Берем ППР из документации. Если в документации 1 вариант выполнения ППР, то в PM Cycle1 и PM Cycle2 \n"
								+ " ставим одинаковые значения, например: ТО1 и ТО1, если в документации 2 варианта выполнения ППР, то в\n"
								+ " PM Cycle1 и PM Cycle2 ставим разные значения, например: ТО1 и ТО1р");
						Point2D p = cyclepm2_inst_upd.localToScreen(cyclepm2_inst_upd.getLayoutBounds().getMaxX(), cyclepm2_inst_upd.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
				        tip.show(cyclepm2_inst_upd, p.getX(), p.getY());
					}
				});
				cyclepm2_inst_upd.setOnMouseExited(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						tip.hide();
					}
				});
				FileChooser fc1 = new FileChooser();
			    fc1.setTitle("Choose a path to file:");
				sdoc_inst_upd.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						fc1.setInitialDirectory(new File(pathToPdf1));
					    fc1.getExtensionFilters().addAll(
					        new ExtensionFilter(
					            "Excel Files", 
					            "*.xlsx", "*.xls"),
					        new ExtensionFilter(
					            "All Files", 
					            "*.*"));
					    
					    //showing the file chooser
					    File phil = 
					        fc1.showOpenDialog(
					            pic.stage);
					    
					    // checking that a file was
					    // chosen by the user
					    if (phil != null) 
					    	try {
						    	 pathToPdf1 =  phil.getParent();
						    	 sdoc_txt_inst_upd.setText(phil.getPath());
					    	}
					    catch (Exception e) {
							
						}
					    chk_btn();
					}
			   });
				
				 
				FileChooser fc = new FileChooser();
			    fc.setTitle("Choose a path to file:");
				exp_inst_pdf.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
					
					    fc.setInitialDirectory(new File(pathToPdf)); //System.getProperty(pathToPdf)
					    fc.getExtensionFilters().addAll(
					        new ExtensionFilter(
					            "PDF Files", 
					            "*.pdf"),
					        new ExtensionFilter(
					            "All Files", 
					            "*.*"));
					    
					    //showing the file chooser
					    File phil = 
					        fc.showOpenDialog(
					            pic.getPrimaryStage());
					    
					    // checking that a file was
					    // chosen by the user
					    if (phil != null) { 
					    	try {
								pathToPdf =  phil.getParent();//.replace("\\", "//")
								inst_pdf_pi.setText(phil.getPath());
					    	}
					    	catch (Exception e) {
								
							}
					    }
					    chk_btn();
					}
			   });
				//инициализируем данными три TextField
				/*cyclepm_inst_upd.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
						period_inst_upd.setText(sclass.parser_sql_str(qr._select_for_addform_inst(cyclepm_inst_upd.getValue().toString()), 0));
						if(!sclass.parser_sql_str(qr._select_for_addform_inst(cyclepm_inst_upd.getValue().toString()), 1).equals("1"))
							qty_hours_inst_upd.setText(sclass.parser_sql_str(qr._select_for_addform_inst(cyclepm_inst_upd.getValue().toString()), 1));
						else
							qty_hours_inst_upd.setText("0");
						if(!sclass.parser_sql_str(qr._select_for_addform_inst(cyclepm_inst_upd.getValue().toString()), 2).equals("1"))
							qty_weeks_inst_upd.setText(sclass.parser_sql_str(qr._select_for_addform_inst(cyclepm_inst_upd.getValue().toString()), 2));
						else
							qty_weeks_inst_upd.setText("0");
					}
				});*/
				
		list_adm2.setItems(qr._select_pm_str("Admission_2"));	
		list_adm2.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				adm2_inst_upd.setText(list_adm2.getValue());
				chk_btn();
			}
		});
		
		list_adm3.setItems(qr._select_pm_str("Admission_3"));	
		list_adm3.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				adm3_inst_upd.setText(list_adm3.getValue());
				chk_btn();
			}
		});
		
		list_of1.setItems(qr._select_pm_str("Outfit_1"));	
		list_of1.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				of1_inst_upd.setText(list_of1.getValue());
				chk_btn();
			}
		});
		
		list_of2.setItems(qr._select_pm_str("Outfit_2"));	
		list_of2.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				of2_inst_upd.setText(list_of2.getValue());
				chk_btn();
			}
		});
		
		ninst_inst_upd.setText(pic._ninst_inst);
		date_create_pi.setValue(fx_dp.fromString(pic._date_create));
		date_change_pi.setValue(fx_dp.fromString(pic._date_change));
		inst_pdf_pi.setText(pic._inst_pdf);
		ver_inst_upd.setText(pic._ver_inst); 
		mt_inst_upd.setText(pic._mt_inst); 
		pmname_inst_upd.setText(pic._pmname_inst); 
		sdoc_txt_inst_upd.setText(pic._sdoc_txt_inst); 
		qtyspec_inst_upd.setText(pic._qtyspec_inst); 
		ptw_inst_upd.setText(pic._ptw_inst); 
		wt_inst_upd.setText(pic._wt_inst); 
		adm2_inst_upd.setText(pic._adm2_inst); 
		adm3_inst_upd.setText(pic._adm3_inst); 
		of1_inst_upd.setText(pic._of1_inst); 
		of2_inst_upd.setText(pic._of2_inst); 
		typepm_inst_upd.getSelectionModel().select(pic._typepm_inst);
		cyclepm1_inst_upd.getSelectionModel().select(pic._cyclepm1_inst);
		cyclepm2_inst_upd.getSelectionModel().select(pic._cyclepm2_inst);
		pos_inst_upd.getSelectionModel().select(pic._pos_inst); 
		line_inst_upd.getSelectionModel().select(pic._line_inst); 
		power_inst_upd.getSelectionModel().select(pic._power_inst); 
		sinfo_inst_upd.getSelectionModel().select(pic._sinfo_inst);
		list_adm2.getSelectionModel().select(pic._adm2_inst);
		list_adm3.getSelectionModel().select(pic._adm3_inst);
		list_of1.getSelectionModel().select(pic._of1_inst);
		list_of2.getSelectionModel().select(pic._of2_inst);
		
		add_cancel_inst_upd.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				stage = (Stage) add_cancel_inst_upd.getScene().getWindow();
				stage.close();
			}
		});
		
		//Проверяем заполнение полей	
				ninst_inst_upd.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				});
				ver_inst_upd.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				});
				mt_inst_upd.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				});
				pmname_inst_upd.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				});
				sdoc_txt_inst_upd.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				});
				qtyspec_inst_upd.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				});
				ptw_inst_upd.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				});
				wt_inst_upd.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				});
				adm2_inst_upd.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				});
				adm3_inst_upd.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				});
				of1_inst_upd.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				});
				of2_inst_upd.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						chk_btn();
					}
				});
				typepm_inst_upd.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						chk_btn();
					}
				});
				cyclepm1_inst_upd.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						chk_btn();
					}
				});
				cyclepm2_inst_upd.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						chk_btn();
					}
				});
				pos_inst_upd.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						chk_btn();
					}
				}); 
				line_inst_upd.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						chk_btn();
					}
				});
				power_inst_upd.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						chk_btn();
					}
				});
				sinfo_inst_upd.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						chk_btn();
					}
				});
				//!!!!!!!!!!!Отслеживаем изменение даты!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() { 
				    public void handle(ActionEvent e) 
				    { 
				       	chk_btn();
					} 
				}; 
				date_change_pi.setOnAction(event);
				//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				
		add_confirm_inst_upd.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				//if(ninst_inst_upd.getText().length() != 0 && ver_inst_upd.getText().length() != 0 && mt_inst_upd.getText().length() != 0 && pmname_inst_upd.getText().length() != 0 &&
				//		   sdoc_txt_inst_upd.getText().length() != 0 && qtyspec_inst_upd.getText().length() != 0 && ptw_inst_upd.getText().length() != 0 && wt_inst_upd.getText().length() != 0 &&
				//		   adm2_inst_upd.getText().length() != 0 && adm3_inst_upd.getText().length() != 0 && of1_inst_upd.getText().length() != 0 && of2_inst_upd.getText().length() != 0 &&
				//		   typepm_inst_upd.getValue().toString().length() != 0 && cyclepm1_inst_upd.getValue().toString().length() != 0 && cyclepm2_inst_upd.getValue().toString().length() != 0 && pos_inst_upd.getValue().toString().length() != 0 && 
				//		   line_inst_upd.getValue().toString().length() != 0 && power_inst_upd.getValue().toString().length() != 0 && sinfo_inst_upd.getValue().toString().length() != 0)
				//{
				//	error_msg_upd.setVisible(false);
					
					qr._update_rec_inst(pic._id_inst, ninst_inst_upd.getText(), date_create_pi.getValue(), date_change_pi.getValue(), inst_pdf_pi.getText().replace('\\', '/'), ver_inst_upd.getText(), mt_inst_upd.getText(), pmname_inst_upd.getText(), sdoc_txt_inst_upd.getText().replace('\\', '/'), qtyspec_inst_upd.getText(), ptw_inst_upd.getText(), wt_inst_upd.getText(), adm2_inst_upd.getText(), adm3_inst_upd.getText(), of1_inst_upd.getText(), of2_inst_upd.getText(), sclass.parser_str(typepm_inst_upd.getValue(), 0), cyclepm1_inst_upd.getValue(), cyclepm2_inst_upd.getValue(), pos_inst_upd.getValue(), line_inst_upd.getValue(), power_inst_upd.getValue(), sinfo_inst_upd.getValue());
					
					qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + " - Обновил запись № = " + pic._id_inst + " в таблице PM Instruction");
					
					pic._table_update_inst.addAll(qr._select_data_pminst());
					stage = (Stage) add_confirm_inst_upd.getScene().getWindow();
					stage.close();
				//}
				//else
				//	error_msg_upd.setVisible(true);
			}
		});
	}
	
	private void lang_fun(String loc1, String loc2)
	{
		ResourceBundle lngBndl = ResourceBundle
	            .getBundle("bundles.LangBundle", new Locale(loc1, loc2));
		
		head_upd_inst.setText(lngBndl.getString("head_add_inst"));
		col_ninst_inst.setText(lngBndl.getString("col_ninst_inst")+":");
		col_ver_inst.setText(lngBndl.getString("col_ver_inst")+":");
		col_mt_inst.setText(lngBndl.getString("col_mt_inst")+":");
		col_pmn_inst.setText(lngBndl.getString("col_pmn_inst")+":");
		col_pmt_inst.setText(lngBndl.getString("col_pmt_inst")+":");
		col_pmc1_inst.setText(lngBndl.getString("col_pmc1_inst")+":");
		col_pmc2_inst.setText(lngBndl.getString("col_pmc2_inst")+":");
		col_ool_inst.setText(lngBndl.getString("col_ool_inst")+":");
		col_oop_inst.setText(lngBndl.getString("col_oop_inst")+":");
		col_pos_inst.setText(lngBndl.getString("col_pos_inst")+":");
		col_sinfo_inst.setText(lngBndl.getString("col_sinfo_inst")+":");
		col_sdoc_inst.setText(lngBndl.getString("col_sdoc_inst")+":");
		
		col_qspec_inst.setText(lngBndl.getString("col_qspec_inst")+":");
		col_pty_inst.setText(lngBndl.getString("col_pty_inst")+":");
		col_wt_inst.setText(lngBndl.getString("col_wt_inst")+":");
		col_adm2_inst.setText(lngBndl.getString("col_adm2_inst")+":");
		col_adm3_inst.setText(lngBndl.getString("col_adm3_inst")+":");
		col_of1_inst.setText(lngBndl.getString("col_of1_inst")+":");
		col_of2_inst.setText(lngBndl.getString("col_of2_inst")+":");
		add_confirm_inst_upd.setText(lngBndl.getString("lbl_apply"));
		add_cancel_inst_upd.setText(lngBndl.getString("cancel_tsk"));
		sdoc_inst_upd.setText(lngBndl.getString("sdoc_inst"));
		ptw_inst_upd.setPromptText(lngBndl.getString("ptw_inst"));
		wt_inst_upd.setPromptText(lngBndl.getString("wt_inst"));
	}
	private void chk_btn()
	{
		try {
			if(ninst_inst_upd.getText().length() != 0 && ver_inst_upd.getText().length() != 0 && mt_inst_upd.getText().length() != 0 && pmname_inst_upd.getText().length() != 0 &&
					   sdoc_txt_inst_upd.getText().length() != 0 && qtyspec_inst_upd.getText().length() != 0 && ptw_inst_upd.getText().length() != 0 && wt_inst_upd.getText().length() != 0 &&
					   adm2_inst_upd.getText().length() != 0 && adm3_inst_upd.getText().length() != 0 && of1_inst_upd.getText().length() != 0 && of2_inst_upd.getText().length() != 0 &&
					   typepm_inst_upd.getValue().toString().length() != 0 && cyclepm1_inst_upd.getValue().toString().length() != 0 && cyclepm2_inst_upd.getValue().toString().length() != 0 && pos_inst_upd.getValue().toString().length() != 0 && 
					   line_inst_upd.getValue().toString().length() != 0 && power_inst_upd.getValue().toString().length() != 0 && sinfo_inst_upd.getValue().toString().length() != 0)
				add_confirm_inst_upd.setDisable(false);
			else
				add_confirm_inst_upd.setDisable(true);
			}
		catch (Exception e) {
			
		}
	}
}