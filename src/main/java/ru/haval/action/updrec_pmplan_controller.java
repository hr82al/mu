package ru.haval.action;

import java.util.Locale;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import  ru.haval.application.conn_connector;
import  ru.haval.data.FxDatePickerConverter;
import  ru.haval.db._query;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import ru.haval.share_class.s_class;

public class updrec_pmplan_controller {
	
	@FXML
	JFXButton upd_pmplan, cancel_pmplan;
	
	@FXML
	JFXTextField id_pmplan, group_pm_pmplan;
	
	@FXML
	JFXDatePicker data_pmplan;
	
	@FXML
	ComboBox<String> oft_pmplan;
	
	@FXML
	Label lbl_upd_record_pmplan, lbl_de_pmplan, lbl_resp_pmplan, lbl_group_pm;
	
	_query qr = new _query();
	s_class scl = new s_class();
	pmplan_controller ppc = new pmplan_controller();
	FxDatePickerConverter fx_dp = new FxDatePickerConverter();
	private Stage stage;
	
	@SuppressWarnings("static-access")
	@FXML
	public void initialize()
	{
		scl._style(upd_pmplan);
		scl._style(cancel_pmplan);
		
		if(conn_connector.LANG_ID == 1)
			lang_fun("en", "EN");
		if(conn_connector.LANG_ID == 0)
			lang_fun("ru", "RU");
		if(conn_connector.LANG_ID == 2)
			lang_fun("zh", "CN");
		if(conn_connector.LANG_ID == -1)
			lang_fun("ru", "RU");
		
		upd_pmplan.setDisable(true);
		
		oft_pmplan.setItems(qr._select_fio_for_ap(1, apwr_controller.SHOP_NAME));
				
		id_pmplan.setText(ppc._id_pmplan);
		group_pm_pmplan.setText(ppc._pm_group);
		oft_pmplan.getSelectionModel().select(ppc._oft_pmplan.toString());
		data_pmplan.setValue(fx_dp.fromString(ppc._date_pmplan));
		
		
		upd_pmplan.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				qr._update_rec_pmplan(id_pmplan.getText(), data_pmplan.getValue(), scl.parser_str(oft_pmplan.getValue(), 0));
				qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + " - Обновил запись № = " + id_pmplan.getText() + " в таблице PM Plan");
				
				ppc._table_update_pmplan.addAll(qr._select_data_pmplan());
				
				stage = (Stage) upd_pmplan.getScene().getWindow();
				stage.close();
			}
		});
		
		cancel_pmplan.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				stage = (Stage) cancel_pmplan.getScene().getWindow();
				stage.close();
			}
		});
		data_pmplan.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				chk_btn();
			}
		});
		oft_pmplan.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				chk_btn();
			}
		});
	}
	private void chk_btn()
	{
		try {
			if(data_pmplan.getValue().toString().length() != 0 && oft_pmplan.getValue().length() != 0)
				upd_pmplan.setDisable(false);
			else
				upd_pmplan.setDisable(true);
		}
		catch (Exception e) {
			
		}
	}
	
	private void lang_fun(String loc1, String loc2)
	{
		ResourceBundle lngBndl = ResourceBundle
	            .getBundle("bundles.LangBundle", new Locale(loc1, loc2));
		
		lbl_upd_record_pmplan.setText(lngBndl.getString("lbl_upd_record_pmplan"));
		lbl_de_pmplan.setText(lngBndl.getString("lbl_de_pmplan")+":");
		lbl_resp_pmplan.setText(lngBndl.getString("lbl_otv_ap")+":");
		lbl_group_pm.setText(lngBndl.getString("lbl_group_ap"));
		upd_pmplan.setText(lngBndl.getString("lbl_apply"));
		cancel_pmplan.setText(lngBndl.getString("cancel_tsk"));
	}
}
