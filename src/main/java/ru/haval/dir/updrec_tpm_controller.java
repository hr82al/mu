package ru.haval.dir;

import java.io.File;

import com.jfoenix.controls.JFXButton;

import ru.haval.action.apwr_controller;
import  ru.haval.application.conn_connector;
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

public class updrec_tpm_controller
{
	@FXML
	JFXButton add_tpm_cancel, upd_tpm, btn_exp_tpm;
	
	@FXML
	TextField name_tpm, desc_tpm, txt_icon_tpm;
	
	@FXML
	Label err_info_cycle;
	
	@FXML
	ComboBox<String> list_type_tpm;
	
	private Stage stage;
	s_class scl = new s_class();
	_query qr = new _query();
	type_pm_controller tpm = new type_pm_controller();
	
	static String pathToPdf = "\\\\10.168.150.74\\MU\\Img";
	
	public updrec_tpm_controller()
	{
		
	}
	@SuppressWarnings("static-access")
	@FXML
	private void initialize()
	{
		scl._style(upd_tpm);
		scl._style(add_tpm_cancel);
		scl._style(btn_exp_tpm);
		
		list_type_tpm.setItems(qr._select_list_str("hmmr_order_type", "Name"));
		
		list_type_tpm.setValue(qr._select_rec("hmmr_order_type", "Name", "del_rec", "id", tpm._type_tpm));
		name_tpm.setText(type_pm_controller._name_tpm);
		desc_tpm.setText(type_pm_controller._desc_tpm);
		txt_icon_tpm.setText(tpm._icon_tpm);
				
		add_tpm_cancel.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				stage = (Stage) add_tpm_cancel.getScene().getWindow();
				stage.close();
			}
		});
		upd_tpm.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(name_tpm.getText().length() != 0 && desc_tpm.getText().length() != 0)
				{
					err_info_cycle.setVisible(false);
					
					qr._update_rec_tpm(name_tpm.getText(),desc_tpm.getText(), qr._select_recStr("hmmr_order_type", "id", "del_rec", "Name", list_type_tpm.getValue()), txt_icon_tpm.getText().replace('\\', '/'),tpm._id_tpm);
					
					qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + " - Обновил запись № = " + type_pm_controller._id_tpm + " в таблице Activity Type");
					
					tpm._table_update_typepm.addAll(qr._select_data_typepm());
					
					stage = (Stage) upd_tpm.getScene().getWindow();
					stage.close();
				}
				else
					err_info_cycle.setVisible(true);
			}
		});
		FileChooser fc = new FileChooser();
	    fc.setTitle("Укажите путь к файлу:");
		btn_exp_tpm.setOnAction(new EventHandler<ActionEvent>() {
			
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