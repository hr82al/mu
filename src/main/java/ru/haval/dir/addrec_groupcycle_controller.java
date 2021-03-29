package ru.haval.dir;

import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.scene.input.KeyEvent;
import ru.haval.action.apwr_controller;
import ru.haval.application.conn_connector;
import ru.haval.data.FxDatePickerConverter;
import ru.haval.db._query;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.haval.share_class.s_class;

public class addrec_groupcycle_controller {
	
	@FXML
	Label lbl_title, lbl_pm_group, lbl_pm_cycle, lbl_days_gc, lbl_start_date, lbl_duration, lbl_oft_ap;
	
	@FXML
	JFXButton add_rec, cancel_form;
	
	@FXML
	TextField txt_pm_group, txt_days_gc, txt_duration;
	
	@FXML
	ComboBox<String> list_pm_cycle, oft;
	
	@FXML
	DatePicker d_start_date;
	
	_query qr = new _query();
	s_class scl = new s_class();
	group_cycle_controller gcc = new group_cycle_controller();
	FxDatePickerConverter fx_dp = new FxDatePickerConverter();

	private Stage stage;
	LocalDate new_date;
	
	@FXML
	public void initialize()
	{
		scl._style(add_rec);
		scl._style(cancel_form);
		
		if(conn_connector.LANG_ID == 1)
			lang_fun("en", "EN");
		if(conn_connector.LANG_ID == 0)
			lang_fun("ru", "RU");
		if(conn_connector.LANG_ID == 2)
			lang_fun("zh", "CN");
		if(conn_connector.LANG_ID == -1)
			lang_fun("ru", "RU");
		
		add_rec.setDisable(true);
		txt_days_gc.setText("-7");
		d_start_date.setValue(LocalDate.now());
		
		txt_pm_group.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(!newValue.isEmpty()) {
		
				if (!newValue.matches("\\d*|#|\\*")) {
					txt_pm_group.setText(newValue.replaceAll("[^\\d|#|\\*]", ""));
		        }
				if(newValue.length() > 5) {
					
					txt_pm_group.setText(newValue.replaceAll("[0-9]", ""));
	            	
				}
				}
			}
		});
		txt_duration.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(!newValue.isEmpty()) {
		
				if (!newValue.matches("\\d*|#|\\*")) {
					txt_duration.setText(newValue.replaceAll("[^\\d|#|\\*]", ""));
		        }
				if(newValue.length() > 3) {
					
					txt_duration.setText(newValue.replaceAll("[0-9]", ""));
	            	
				}
				}
			}
		});
		txt_duration.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				scl._ToolTip("Количество дней на исполнение( Например: 7 )", txt_duration);
			}
		});
		txt_duration.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				scl._ToolTipHide();
			}
		});
		list_pm_cycle.setItems(qr._select_cycle_inst());
		oft.setItems(qr._select_fio_for_ap(4, ""));
		
		txt_pm_group.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		list_pm_cycle.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				chk_btn();
			}
		});
		oft.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				chk_btn();
			}
		});
		txt_duration.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		add_rec.setOnAction(new EventHandler<ActionEvent>() {
			
			@SuppressWarnings("static-access")
			@Override
			public void handle(ActionEvent arg0) {
				qr._insert_gc(txt_pm_group.getText(), list_pm_cycle.getValue(), d_start_date.getValue(), txt_duration.getText(), txt_days_gc.getText());
				qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + " - Создал запись № = " + qr._select_last_id("hmmr_group_cycle") + " в справочнике Группа-Период");
				//Check if the group in the pm year exists
				if (!qr.isPmGroupExists(txt_pm_group.getText())) {
					//Fill the PM Year DB for a new cycle
					s_class.updatePmYearDates(txt_pm_group.getText(), d_start_date.getValue(), oft.getValue().split(" - ")[0] ,list_pm_cycle.getValue());
				}
				gcc._table_update_gc.addAll(qr._select_for_gc());
				stage = (Stage) add_rec.getScene().getWindow();
				stage.close();
			}
		});
		cancel_form.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				stage = (Stage) cancel_form.getScene().getWindow();
				stage.close();
			}
		});
		Platform.runLater(() -> {
			String pmNum = qr.findFreePmNum();
			txt_pm_group.setText(pmNum);
		});
		String[] last = qr.getLastGroupCycle();
		list_pm_cycle.setValue(last[2]);
		d_start_date.setValue(LocalDate.parse(last[3]));
		txt_days_gc.setText(last[5]);
		txt_duration.setText(last[4]);
		oft.setValue(last[6]);
		add_rec.setDisable(false);
	}
	
	private void lang_fun(String loc1, String loc2)
	{
		ResourceBundle lngBndl = ResourceBundle
	            .getBundle("bundles.LangBundle", new Locale(loc1, loc2));
		 
		lbl_title.setText(lngBndl.getString("lbl_title"));
		lbl_pm_group.setText(lngBndl.getString("lbl_group")+":");
		lbl_pm_cycle.setText(lngBndl.getString("lbl_pm_cycle")+":");
		lbl_start_date.setText(lngBndl.getString("col_startdate_ps")+":");
		lbl_duration.setText(lngBndl.getString("lbl_duration")+":");

		lbl_oft_ap.setText(lngBndl.getString("lbl_oft_ap")+":");
		
		add_rec.setText(lngBndl.getString("add_tsk"));
		cancel_form.setText(lngBndl.getString("cancel_tsk"));
	}
	
	private void chk_btn()
	{

		try {
			if(txt_pm_group.getText().length() != 0 && list_pm_cycle.getValue().length() != 0 && txt_duration.getText().length() != 0 && !oft.getSelectionModel().isEmpty() )
				add_rec.setDisable(false);
			else
				add_rec.setDisable(true);
		}
		catch (Exception e) {
			
		}
	}

}
