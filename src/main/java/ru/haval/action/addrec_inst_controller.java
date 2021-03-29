package ru.haval.action;

import java.io.File;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jfoenix.controls.JFXButton;
import  ru.haval.application.conn_connector;
import ru.haval.config.Config;
import  ru.haval.db._query;
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

public class addrec_inst_controller
{
	@FXML
	ComboBox<String> typepm_inst, cyclepm1_inst, cyclepm2_inst, pos_inst, line_inst, power_inst, sinfo_inst, list_adm2, list_adm3, list_of1, list_of2;
	
	@FXML
	TextField ninst_inst, ver_inst, mt_inst, pmname_inst, sdoc_txt_inst, qtyspec_inst, ptw_inst, wt_inst, adm2_inst, adm3_inst, of1_inst, of2_inst,inst_pdf_pi;
	
	@FXML
	JFXButton sdoc_inst, add_confirm_inst, add_cancel_inst, exp_inst_pdf;
	
	@FXML
	Label error_msg, head_add_inst, col_ninst_inst, col_ver_inst, col_mt_inst, col_pmn_inst, col_pmt_inst, col_pmc1_inst, col_pmc2_inst, col_ool_inst,
	 col_oop_inst, col_pos_inst, col_sinfo_inst, col_sdoc_inst, col_qspec_inst, col_pty_inst, col_wt_inst, col_adm2_inst,
	 col_adm3_inst, col_of1_inst, col_of2_inst;
	
	@FXML
	DatePicker date_create_pi, date_change_pi;
	
	public static int ind1, ind2, ind3, ind4, ind5, ind6;
	
	private Stage stage;	
	_query qr = new _query();
	s_class sclass = new s_class();
	pm_inst_controller pic = new pm_inst_controller();
	File dataFile = null;
	Tooltip tip;
	
	private static String pathToPdf = "\\\\" +
			Config.getInstance().getAddress() +
			"\\MU\\Instruction_PDF", pathToPdf1 = "C://";//user.home
			
	@FXML
	public void initialize()
	{
		
		//разрешаем вводить только цифры и не больше трех
		ver_inst.textProperty().addListener(new ChangeListener<String>() {

					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
						if(!newValue.isEmpty()) {
				
						if (!newValue.matches("\\d*|#|\\*")) {
							ver_inst.setText(newValue.replaceAll("[^\\d|#|\\*]", ""));
				        }
						if(newValue.length() > 3) {
							
							ver_inst.setText(newValue.replaceAll("[0-9]", ""));
			            	
						}
					}
					}
				});
		qtyspec_inst.textProperty().addListener(new ChangeListener<String>() {

					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
						if(!newValue.isEmpty()) {
				
						if (!newValue.matches("\\d*|#|\\*")) {
							qtyspec_inst.setText(newValue.replaceAll("[^\\d|#|\\*]", ""));
				        }
						if(newValue.length() > 2) {
							
							qtyspec_inst.setText(newValue.replaceAll("[0-9]", ""));
			            	
						}
					}
					}
				});
		ptw_inst.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(!newValue.isEmpty()) {
		
				if (!newValue.matches("\\d*|#|\\*")) {
					ptw_inst.setText(newValue.replaceAll("[^\\d|#|\\*]", ""));
		        }
				if(newValue.length() > 3) {
					
					ptw_inst.setText(newValue.replaceAll("[0-9]", ""));
	            	
				}
			}
			}
		});
		wt_inst.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(!newValue.isEmpty()) {
		
				if (!newValue.matches("\\d*|#|\\*")) {
					wt_inst.setText(newValue.replaceAll("[^\\d|#|\\*]", ""));
		        }
				if(newValue.length() > 3) {
					
					wt_inst.setText(newValue.replaceAll("[0-9]", ""));
	            	
				}
			}
			}
		});
		
		if(conn_connector.LANG_ID == 1)
			lang_fun("en", "EN");
		if(conn_connector.LANG_ID == 0)
			lang_fun("ru", "RU");
		if(conn_connector.LANG_ID == 2)
			lang_fun("zh", "CN");
		if(conn_connector.LANG_ID == -1)
			lang_fun("ru", "RU");
		
		add_confirm_inst.setDisable(true);
		
		sclass._style(add_confirm_inst);
		sclass._style(add_cancel_inst);
		sclass._style(sdoc_inst);
		sclass._style(exp_inst_pdf);
		
		date_create_pi.setValue(LocalDate.now());
		date_change_pi.setValue(LocalDate.now());
		
		//инициализируем данными combobox
		typepm_inst.setItems(qr._select_typepm_inst());
		cyclepm1_inst.setItems(qr._select_cycle_inst());
		cyclepm2_inst.setItems(qr._select_cycle_inst());
		pos_inst.setItems(qr._select_pos_inst());
		
		String line1 = new String("ON");
		String line2 = new String("OFF");
		line_inst.getItems().addAll(line1, line2);
		
		String power1 = new String("ON");
		String power2 = new String("OFF");
		power_inst.getItems().addAll(power1, power2);
		
		String sinfo1 = new String("M");
		String sinfo2 = new String("P");
		sinfo_inst.getItems().addAll(sinfo1, sinfo2);
		
		//инициализируем данными три TextField
		/*cyclepm_inst.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				period_inst.setText(sclass.parser_sql_str(qr._select_for_addform_inst(cyclepm_inst.getValue().toString()), 0));
				if(!sclass.parser_sql_str(qr._select_for_addform_inst(cyclepm_inst.getValue().toString()), 1).equals("1"))
					qty_hours_inst.setText(sclass.parser_sql_str(qr._select_for_addform_inst(cyclepm_inst.getValue().toString()), 1));
				else
					qty_hours_inst.setText("0");
				if(!sclass.parser_sql_str(qr._select_for_addform_inst(cyclepm_inst.getValue().toString()), 2).equals("1"))
					qty_weeks_inst.setText(sclass.parser_sql_str(qr._select_for_addform_inst(cyclepm_inst.getValue().toString()), 2));
				else
					qty_weeks_inst.setText("0");
			}
		});*/
		cyclepm1_inst.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip = new Tooltip("Берем ППР из документации. Если в документации 1 вариант выполнения ППР, то в PM Cycle1 и PM Cycle2 \n"
						+ " ставим одинаковые значения, например: ТО1 и ТО1, если в документации 2 варианта выполнения ППР, то в\n"
						+ " PM Cycle1 и PM Cycle2 ставим разные значения, например: ТО1 и ТО1р");
				Point2D p = cyclepm1_inst.localToScreen(cyclepm1_inst.getLayoutBounds().getMaxX(), cyclepm1_inst.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(cyclepm1_inst, p.getX(), p.getY());
			}
		});
		cyclepm1_inst.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip.hide();
			}
		});
		cyclepm2_inst.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip = new Tooltip("Берем ППР из документации. Если в документации 1 вариант выполнения ППР, то в PM Cycle1 и PM Cycle2 \n"
						+ " ставим одинаковые значения, например: ТО1 и ТО1, если в документации 2 варианта выполнения ППР, то в\n"
						+ " PM Cycle1 и PM Cycle2 ставим разные значения, например: ТО1 и ТО1р");
				Point2D p = cyclepm2_inst.localToScreen(cyclepm2_inst.getLayoutBounds().getMaxX(), cyclepm2_inst.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(cyclepm2_inst, p.getX(), p.getY());
			}
		});
		cyclepm2_inst.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip.hide();
			}
		});
		FileChooser fc1 = new FileChooser();
	    fc1.setTitle("Choose a path to file:");
		sdoc_inst.setOnAction(new EventHandler<ActionEvent>() {
			
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
				    	 sdoc_txt_inst.setText(phil.getPath());
			    	}
			    catch (Exception e) {
					
				}
			    chk_btn();
			}
	   });
		
		 
		FileChooser fc = new FileChooser();
	    fc.setTitle("Choose a path to file:");
		exp_inst_pdf.setOnAction(new EventHandler<ActionEvent>() {
			
			@SuppressWarnings("static-access")
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
		list_adm2.setItems(qr._select_pm_str("Admission_2"));	
		list_adm2.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				adm2_inst.setText(list_adm2.getValue());
			}
		});
		list_adm3.setItems(qr._select_pm_str("Admission_3"));	
		list_adm3.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				adm3_inst.setText(list_adm3.getValue());
			}
		});
		list_of1.setItems(qr._select_pm_str("Outfit_1"));	
		list_of1.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				of1_inst.setText(list_of1.getValue());
			}
		});
		list_of2.setItems(qr._select_pm_str("Outfit_2"));	
		list_of2.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				of2_inst.setText(list_of2.getValue());
			}
		});
		
	//Проверяем заполнение полей	
		ninst_inst.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				chk_btn();
			}
		});
		ver_inst.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				chk_btn();
			}
		});
		mt_inst.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				chk_btn();
			}
		});
		pmname_inst.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				chk_btn();
			}
		});
		sdoc_txt_inst.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				chk_btn();
			}
		});
		qtyspec_inst.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				chk_btn();
			}
		});
		ptw_inst.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				chk_btn();
			}
		});
		wt_inst.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				chk_btn();
			}
		});
		adm2_inst.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				chk_btn();
			}
		});
		adm3_inst.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				chk_btn();
			}
		});
		of1_inst.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				chk_btn();
			}
		});
		of2_inst.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				chk_btn();
			}
		});
		typepm_inst.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				chk_btn();
			}
		});
		cyclepm1_inst.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				chk_btn();
			}
		});
		cyclepm2_inst.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				chk_btn();
			}
		});
		pos_inst.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				chk_btn();
			}
		}); 
		line_inst.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				chk_btn();
			}
		});
		power_inst.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				chk_btn();
			}
		});
		sinfo_inst.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				chk_btn();
			}
		});
		
		add_confirm_inst.setOnAction(new EventHandler<ActionEvent>() {
			
			@SuppressWarnings("static-access")
			@Override
			public void handle(ActionEvent event) {
				
			/*	if(ninst_inst.getText().length() != 0 && ver_inst.getText().length() != 0 && mt_inst.getText().length() != 0 && pmname_inst.getText().length() != 0 &&
				   sdoc_txt_inst.getText().length() != 0 && qtyspec_inst.getText().length() != 0 && ptw_inst.getText().length() != 0 && wt_inst.getText().length() != 0 &&
				   adm2_inst.getText().length() != 0 && adm3_inst.getText().length() != 0 && of1_inst.getText().length() != 0 && of2_inst.getText().length() != 0 &&
				   typepm_inst.getValue().toString().length() != 0 && cyclepm1_inst.getValue().toString().length() != 0 && cyclepm2_inst.getValue().toString().length() != 0 && pos_inst.getValue().toString().length() != 0 && 
				   line_inst.getValue().toString().length() != 0 && power_inst.getValue().toString().length() != 0 && sinfo_inst.getValue().toString().length() != 0)
				{
					error_msg.setVisible(false);*/
					
					qr._insert_pm_inst(ninst_inst.getText(), date_create_pi.getValue(),date_change_pi.getValue(),inst_pdf_pi.getText().replace('\\', '/'),ver_inst.getText(), mt_inst.getText(), pmname_inst.getText(), sdoc_txt_inst.getText().replace('\\', '/'), 
							           qtyspec_inst.getText(), ptw_inst.getText(), wt_inst.getText(), adm2_inst.getText(), adm3_inst.getText(), of1_inst.getText(),
							           of2_inst.getText(), sclass.parser_str(typepm_inst.getValue().toString(), 0), cyclepm1_inst.getValue().toString(), cyclepm2_inst.getValue().toString(), 
							           pos_inst.getValue().toString(), line_inst.getValue().toString(), power_inst.getValue().toString(), sinfo_inst.getValue().toString());
					
					qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + " - Создал запись № = " + qr._select_last_id("pm_inst") + " в PM Instruction");
					
					pic._table_update_inst.addAll(qr._select_data_pminst());
					
					stage = (Stage) add_confirm_inst.getScene().getWindow();
					stage.close();
				//}
				//else
			    //	error_msg.setVisible(true);
			}
		});
		
		add_cancel_inst.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				stage = (Stage) add_cancel_inst.getScene().getWindow();
				stage.close();
			}
		});
		//FIXME
		hmmr_inst_model last = qr.getLastPmInstruction();
		Pattern pattern = Pattern.compile("\\d{4,}");
		String inst = last.getnum_inst();

			Matcher matcher = pattern.matcher(inst);
			if (matcher.find()) {
				final String numString = matcher.group(0);
				long num = Long.parseLong(numString);
				do {
				++num;
				inst = inst.replace(numString, String.format("%0" + numString.length() + "d", num));
				} while (qr.isInstNumExists(inst));
			}

		ninst_inst.setText(inst);
		ver_inst.setText(last.getVer());
		date_create_pi.setValue(LocalDate.parse(last.getdate_create()));
		date_change_pi.setValue(LocalDate.parse(last.getdate_change()));
		inst_pdf_pi.setText(last.getinst_pdf());
		mt_inst.setText(last.getMTT());
		pmname_inst.setText(last.getPM_name());
		typepm_inst.setValue(last.gettype_PM());
		cyclepm1_inst.setValue(last.getPM_cycle1());
		cyclepm2_inst.setValue(last.getPM_cycle2());
		line_inst.setValue(last.getOlOl());
		power_inst.setValue(last.getPoPo());
		pos_inst.setValue(last.getPos());
		sinfo_inst.setValue(last.getS_info());
		sdoc_txt_inst.setText(last.getS_doc());
		qtyspec_inst.setText(last.getQty_s());
		ptw_inst.setText(last.getPWT());
		wt_inst.setText(last.getWT());
		adm2_inst.setText(last.getAdm_2());
		list_adm2.setValue(last.getAdm_2());
		adm3_inst.setText(last.getAdm_3());
		list_adm3.setValue(last.getAdm_3());
		of1_inst.setText(last.getOF_1());
		list_of1.setValue(last.getOF_1());
		of2_inst.setText(last.getOF_2());
		list_of2.setValue(last.getOF_2());
	}
	
	private void lang_fun(String loc1, String loc2)
	{
		ResourceBundle lngBndl = ResourceBundle
	            .getBundle("bundles.LangBundle", new Locale(loc1, loc2));
		
		head_add_inst.setText(lngBndl.getString("head_add_inst"));
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
		add_confirm_inst.setText(lngBndl.getString("lbl_apply"));
		add_cancel_inst.setText(lngBndl.getString("cancel_tsk"));
		sdoc_inst.setText(lngBndl.getString("sdoc_inst"));
		ptw_inst.setPromptText(lngBndl.getString("ptw_inst"));
		wt_inst.setPromptText(lngBndl.getString("wt_inst"));
	}
	private void chk_btn()
	{
		try {
			if(ninst_inst.getText().length() != 0 && ver_inst.getText().length() != 0 && mt_inst.getText().length() != 0 && pmname_inst.getText().length() != 0 &&
					   sdoc_txt_inst.getText().length() != 0 && qtyspec_inst.getText().length() != 0 && ptw_inst.getText().length() != 0 && wt_inst.getText().length() != 0 &&
					   adm2_inst.getText().length() != 0 && adm3_inst.getText().length() != 0 && of1_inst.getText().length() != 0 && of2_inst.getText().length() != 0 &&
					   typepm_inst.getValue().toString().length() != 0 && cyclepm1_inst.getValue().toString().length() != 0 && cyclepm2_inst.getValue().toString().length() != 0 && pos_inst.getValue().toString().length() != 0 && 
					   line_inst.getValue().toString().length() != 0 && power_inst.getValue().toString().length() != 0 && sinfo_inst.getValue().toString().length() != 0 && inst_pdf_pi.getText().length() != 0)
				add_confirm_inst.setDisable(false);
			else
				add_confirm_inst.setDisable(true);
			}
		catch (Exception e) {
			
		}
	}
}