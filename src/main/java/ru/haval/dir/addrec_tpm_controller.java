package ru.haval.dir;

import java.io.File;

import com.jfoenix.controls.JFXButton;

import ru.haval.action.apwr_controller;
import  ru.haval.application.conn_connector;
import ru.haval.config.Config;
import  ru.haval.db._query;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import ru.haval.share_class.s_class;

public class addrec_tpm_controller
{
	@FXML
	JFXButton add_tpm_confirm, add_tpm_cancel, btn_exp_tpm;
	
	@FXML
	TextField name_tpm, desc_tpm, txt_icon_tpm;
	
	@FXML
	Label err_info_cycle, lbl_icon_tpm;
	
	@FXML
	ComboBox<String> list_type_tpm;
	
	private Stage stage;
	
	private String _t_tpm, _n_tpm, _d_tpm, _i_tpm;
	s_class scl = new s_class();
	_query qr = new _query();
	type_pm_controller tpm = new type_pm_controller();
	
	private static String pathToPdf = "\\\\" +
			Config.getInstance().getAddress() +
			"\\MU\\Img";
	
	public addrec_tpm_controller()
	{
		
	}
	
	@FXML
	public void initialize()
	{
		scl._style(add_tpm_confirm);
		scl._style(add_tpm_cancel);
		scl._style(btn_exp_tpm);
		
		list_type_tpm.setItems(qr._select_list_str("hmmr_order_type", "Name"));
		
		add_tpm_cancel.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				stage = (Stage) add_tpm_cancel.getScene().getWindow();
				stage.close();
			}
		});
		
		add_tpm_confirm.setOnAction(new EventHandler<ActionEvent>() {
			
			@SuppressWarnings("static-access")
			@Override
			public void handle(ActionEvent event) {
				
				if(name_tpm.getText().length() != 0 && desc_tpm.getText().length() != 0)
				{
					err_info_cycle.setVisible(false);
					
					_t_tpm = list_type_tpm.getValue();
					_n_tpm = name_tpm.getText();
					_d_tpm = desc_tpm.getText();
					_i_tpm = txt_icon_tpm.getText().replace('\\', '/');
					//qr._select_rec("hmmr_order_type", "id", "del_rec", "Name", _t_tpm) - Получаем ID из ORDER TYPES
					qr._insert_type_pm(qr._select_recStr("hmmr_order_type", "id", "del_rec", "Name", _t_tpm), _n_tpm, _d_tpm, _i_tpm);
					
					qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + "Создал запись № = " + qr._select_last_id("hmmr_activity_type") + " в Activity Type");
					
					tpm._table_update_typepm.addAll(qr._select_data_typepm());
					
					stage = (Stage) add_tpm_confirm.getScene().getWindow();
					stage.close();
				}
				else
					err_info_cycle.setVisible(true);
			}
		});
		FileChooser fc = new FileChooser();
	    fc.setTitle("Укажите путь к файлу:");
		btn_exp_tpm.setOnAction(new EventHandler<ActionEvent>() {
			
			@SuppressWarnings("static-access")
			@Override
			public void handle(ActionEvent arg0) {
				fc.setInitialDirectory(new File(pathToPdf));
				fc.getExtensionFilters().addAll(
				      new ExtensionFilter(
				      "JPG Files", "*.jpg"),
				      new ExtensionFilter(
				            "PNG Files", 
				            "*.png"));
				    //showing the file chooser
				    File phil = 
				        fc.showOpenDialog(
				            tpm.getPrimaryStage());
				    
				    // checking that a file was
				    // chosen by the user
				    if (phil != null) {
				    	pathToPdf =  phil.getParent();
				    	txt_icon_tpm.setText(phil.getPath());
				    }
			}
		});
	}
}