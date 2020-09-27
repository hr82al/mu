package ru.haval.action;

import java.time.LocalDate;
import java.time.Month;

import com.jfoenix.controls.JFXButton;
import  ru.haval.application.conn_connector;
import  ru.haval.data.FxDatePickerConverter;
import  ru.haval.db._query;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.haval.share_class.s_class;

public class Addrec_Staff_Controller {
	
	@FXML
	TextField txt_fam_staff, txt_imya_staff, txt_otch_staff, txt_imya_eng_staff, txt_fam_eng_staff, txt_email_staff, txt_skype_staff, txt_cell1_staff, txt_cell2_staff, txt_adr_staff,
	txt_avto_staff, txt_staffid_staff, txt_ID_staff, txt_pos_rus_staff, txt_pos_staff, txt_work_staff, txt_gwm_staff, txt_shoes_staff, txt_clothe_staff, txt_login_staff, txt_passwd_staff;
	
	@FXML
	Label lbl_fam_staff, lbl_imya_staff, lbl_otch_staff, lbl_imya_eng_staff, lbl_fam_eng_staff, lbl_dob_staff, lbl_email_staff, lbl_skype_staff, lbl_cell1_staff, lbl_cell2_staff,
	lbl_adr_staff, lbl_avto_staff, lbl_staffid_staff, lbl_ID_staff, lbl_sec_staff, lbl_groups_staff, lbl_team_staff, lbl_pos_rus_staff, lbl_pos_staff, lbl_work_staff, lbl_gwm_staff,
	lbl_shoes_staff, lbl_clothe_staff, lbl_dbegin_staff, lbl_dend_staff, lbl_login_staff, lbl_passwd_staff, lbl_rule_staff;
	
	@FXML
	ComboBox<String> list_sec_staff, list_groups_staff, list_team_staff, list_pos_rus_staff, list_pos_staff, list_rule_staff;
	
	@FXML
	DatePicker d_dob_staff, d_dbegin_staff, d_dend_staff;
	
	@FXML
	JFXButton btn_add_staff, btn_cancel_staff, btn_gen_staff;
	
	@FXML
	Tab autorith;
	
	String Role;
	
	_query qr = new _query();
	s_class scl = new s_class();
	FxDatePickerConverter fx_dp = new FxDatePickerConverter();
	Stuff_Controller sc = new Stuff_Controller();
	Stage stage;
	
	ObservableList<String> list_sec = FXCollections.observableArrayList();
	ObservableList<String> list_groups = FXCollections.observableArrayList();
	ObservableList<String> list_team = FXCollections.observableArrayList();
	ObservableList<String> list_rule = FXCollections.observableArrayList();
	
	@FXML
	public void initialize()
	{
		scl._style(btn_add_staff);
		scl._style(btn_cancel_staff);
		scl._style(btn_gen_staff);
		
		btn_add_staff.setDisable(true);
		
		list_sec.add("MU");
		list_sec.add("MTC");
		list_sec.add("PLE");
		list_sec.add("UTL");
		
		list_sec_staff.setItems(list_sec);
		
		list_groups.add("A");
		list_groups.add("E");
		list_groups.add("P");
		list_groups.add("L");
		list_groups.add("S,W");
		list_groups.add("MU");
		list_groups.add("UTL");
		
		list_groups_staff.setItems(list_groups);
		
		list_team.add("HOD");
		list_team.add("HOS");
		list_team.add("GL");
		list_team.add("TL");
		list_team.add("SL");
		list_team.add("E");
		list_team.add("T");
		
		list_team_staff.setItems(list_team);
		
		if(conn_connector.USER_ROLE.equals("Administrator")) {
			list_rule.add("Administrator");
			list_rule.add("Group Leader");
			list_rule.add("Team Leader");
			list_rule.add("Engineer");
			list_rule.add("Technician");
		}
		if(conn_connector.USER_ROLE.equals("Group Lead")) {
			list_rule.add("Group Leader");
			list_rule.add("Team Leader");
			list_rule.add("Engineer");
			list_rule.add("Technician");
		}
		if(conn_connector.USER_ROLE.equals("Team Lead")) {
			list_rule.add("Team Leader");
			list_rule.add("Engineer");
			list_rule.add("Technician");
		}
		if(conn_connector.USER_ROLE.equals("Engeneer")) {
			list_rule.add("Engineer");
			list_rule.add("Technician");
		}
		list_rule_staff.setItems(list_rule);
		
		list_pos_rus_staff.setItems(qr._select_list_str("hmmr_mu_staff", "Position_RUS"));
		list_pos_staff.setItems(qr._select_list_str("hmmr_mu_staff", "Position"));
		
		LocalDate de = LocalDate.of(2018, Month.OCTOBER, 10);
		d_dend_staff.setValue(de);
		
		//разрешаем вводить только цифры и не больше трех
		/*txt_cell1_staff.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(!newValue.isEmpty()) {
				
					if (!newValue.matches("\\d*|#|\\*")) {
						txt_cell1_staff.setText(newValue.replaceAll("[^\\d|#|\\*]", ""));
				    }
					if(newValue.length() > 11) {
						txt_cell1_staff.setText(newValue.replaceAll("[0-9]", ""));
			        }
				}
			}
		});
		txt_cell2_staff.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(!newValue.isEmpty()) {
				
					if (!newValue.matches("\\d*|#|\\*")) {
						txt_cell2_staff.setText(newValue.replaceAll("[^\\d|#|\\*]", ""));
				    }
					if(newValue.length() > 11) {
						txt_cell2_staff.setText(newValue.replaceAll("[0-9]", ""));
			        }
				}
			}
		});*/
		txt_shoes_staff.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(!newValue.isEmpty()) {
				
					if (!newValue.matches("\\d*|#|\\*")) {
						txt_shoes_staff.setText(newValue.replaceAll("[^\\d|#|\\*]", ""));
				    }
					if(newValue.length() > 2) {
						txt_shoes_staff.setText(newValue.replaceAll("[0-9]", ""));
			        }
				}
			}
		});
		txt_clothe_staff.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(!newValue.isEmpty()) {
				
					if (!newValue.matches("\\d*|#|\\*")) {
						txt_clothe_staff.setText(newValue.replaceAll("[^\\d|#|\\*]", ""));
				    }
					if(newValue.length() > 2) {
						txt_clothe_staff.setText(newValue.replaceAll("[0-9]", ""));
			        }
				}
			}
		});
		txt_work_staff.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(!newValue.isEmpty()) {
				
					if (!newValue.matches("\\d*|#|\\*")) {
						txt_work_staff.setText(newValue.replaceAll("[^\\d|#|\\*]", ""));
				    }
					if(newValue.length() > 2) {
						txt_work_staff.setText(newValue.replaceAll("[0-9]", ""));
			        }
				}
			}
		});
		
		list_pos_rus_staff.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				txt_pos_rus_staff.setText(list_pos_rus_staff.getValue());
			}
		});
		list_pos_staff.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				txt_pos_staff.setText(list_pos_staff.getValue());
			}
		});
		
		autorith.setOnSelectionChanged(new EventHandler<Event>() {
			
			@Override
			public void handle(Event arg0) {
				try {
					txt_login_staff.setText(txt_gwm_staff.getText().toLowerCase());
				}
				catch (Exception e) {
					
				}
			}
		});
		
		d_dbegin_staff.setValue(LocalDate.now());
		
		//Проверяем заполнение полей
		txt_fam_staff.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_imya_staff.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_otch_staff.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_imya_eng_staff.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_fam_eng_staff.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_email_staff.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_skype_staff.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_cell1_staff.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_cell2_staff.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_adr_staff.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_avto_staff.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_staffid_staff.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_ID_staff.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_pos_rus_staff.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_pos_staff.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_work_staff.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_gwm_staff.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_shoes_staff.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_clothe_staff.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_login_staff.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_passwd_staff.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		btn_gen_staff.setOnAction(new EventHandler<ActionEvent>() {
			
			@SuppressWarnings("static-access")
			@Override
			public void handle(ActionEvent arg0) {
				txt_passwd_staff.setText(scl.generate());
			}
		});
		btn_add_staff.setOnAction(new EventHandler<ActionEvent>() {
			
			@SuppressWarnings("static-access")
			@Override
			public void handle(ActionEvent arg0) {
				
				int usr_id = Integer.parseInt(qr._select_last_id("users")) + 1;
				if(qr._flag_error) {
					qr._insert_staff(txt_staffid_staff.getText(), txt_ID_staff.getText(), String.valueOf(usr_id), txt_fam_staff.getText(), txt_imya_staff.getText(), txt_otch_staff.getText(), txt_imya_eng_staff.getText(), txt_fam_eng_staff.getText(), d_dob_staff.getValue(), list_sec_staff.getValue(), list_groups_staff.getValue(), list_team_staff.getValue(), txt_work_staff.getText(), txt_pos_staff.getText(), txt_pos_rus_staff.getText(), txt_gwm_staff.getText(), d_dbegin_staff.getValue(), txt_email_staff.getText(), txt_skype_staff.getText(), txt_cell1_staff.getText(), txt_cell2_staff.getText(), txt_adr_staff.getText(), txt_avto_staff.getText(), txt_shoes_staff.getText(), txt_clothe_staff.getText(), d_dend_staff.getValue());
					qr._insert_users(usr_id, txt_imya_staff.getText(), txt_fam_staff.getText(), txt_login_staff.getText(), txt_passwd_staff.getText(), d_dbegin_staff.getValue(), Role);
					qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + " - Создал запись № = " + qr._select_last_id("hmmr_mu_staff") + " в таблице STAFF");
					qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + " - Создал запись № = " + qr._select_last_id("users") + " в таблице Users");
					sc._table_update_staff.addAll(qr._select_data_staff());
				
					stage = (Stage) btn_add_staff.getScene().getWindow();
					stage.close();
				}
			}
		});
		btn_cancel_staff.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				stage = (Stage) btn_cancel_staff.getScene().getWindow();
				stage.close();
			}
		});
		list_sec_staff.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				chk_btn();
			}
		});
		list_groups_staff.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				chk_btn();
			}
		});
		list_team_staff.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				chk_btn();
			}
		});
		list_pos_rus_staff.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				chk_btn();
			}
		});
		list_pos_staff.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				chk_btn();
			}
		});
		
		list_rule_staff.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				if(conn_connector.USER_ROLE.equals("Administrator")) {
					if(list_rule_staff.getSelectionModel().getSelectedIndex() == 0)
						Role = "Administrator";
					if(list_rule_staff.getSelectionModel().getSelectedIndex() == 1)
						Role = "Group Lead";
					if(list_rule_staff.getSelectionModel().getSelectedIndex() == 2)
						Role = "Team Lead";
					if(list_rule_staff.getSelectionModel().getSelectedIndex() == 3)
						Role = "Engeneer";
					if(list_rule_staff.getSelectionModel().getSelectedIndex() == 4)
						Role = "Technics";
				}
				if(conn_connector.USER_ROLE.equals("Group Lead")) {
					if(list_rule_staff.getSelectionModel().getSelectedIndex() == 0)
						Role = "Group Lead";
					if(list_rule_staff.getSelectionModel().getSelectedIndex() == 1)
						Role = "Team Lead";
					if(list_rule_staff.getSelectionModel().getSelectedIndex() == 2)
						Role = "Engeneer";
					if(list_rule_staff.getSelectionModel().getSelectedIndex() == 3)
						Role = "Technics";
				}
				if(conn_connector.USER_ROLE.equals("Team Lead")) {
					if(list_rule_staff.getSelectionModel().getSelectedIndex() == 0)
						Role = "Team Lead";
					if(list_rule_staff.getSelectionModel().getSelectedIndex() == 1)
						Role = "Engeneer";
					if(list_rule_staff.getSelectionModel().getSelectedIndex() == 2)
						Role = "Technics";
				}
				if(conn_connector.USER_ROLE.equals("Engeneer")) {
					if(list_rule_staff.getSelectionModel().getSelectedIndex() == 0)
						Role = "Engeneer";
					if(list_rule_staff.getSelectionModel().getSelectedIndex() == 1)
						Role = "Technics";
				}
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
		d_dob_staff.setOnAction(event);
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() { 
		    public void handle(ActionEvent e1) 
		    { 
		       	chk_btn();
			} 
		}; 
		d_dbegin_staff.setOnAction(event1);
	}
		
	private void chk_btn()
	{
		try {
			if(txt_fam_staff.getText().length() != 0 && txt_imya_staff.getText().length() != 0 &&
					txt_otch_staff.getText().length() != 0 && txt_imya_eng_staff.getText().length() != 0 && txt_fam_eng_staff.getText().length() != 0 && txt_email_staff.getText().length() != 0 &&
					txt_skype_staff.getText().length() != 0 && txt_cell1_staff.getText().length() != 0 && txt_cell2_staff.getText().length() != 0 && txt_adr_staff.getText().length() != 0 &&
					txt_avto_staff.getText().length() != 0 && txt_staffid_staff.getText().length() != 0 &&
					txt_ID_staff.getText().length() != 0 && txt_pos_rus_staff.getText().length() != 0 && txt_pos_staff.getText().length() != 0 &&
					txt_work_staff.getText().length() != 0 && txt_gwm_staff.getText().length() != 0 && txt_shoes_staff.getText().length() != 0 && txt_clothe_staff.getText().length() != 0 &&
					txt_login_staff.getText().length() != 0 && txt_passwd_staff.getText().length() != 0 && list_sec_staff.getValue().length() != 0 && list_groups_staff.getValue().length() != 0 &&
					list_team_staff.getValue().length() != 0 && list_rule_staff.getValue().length() != 0 &&
					d_dob_staff.getValue().toString().length() != 0 && d_dbegin_staff.getValue().toString().length() != 0)
				btn_add_staff.setDisable(false);
			else
				btn_add_staff.setDisable(true);
			}
			catch (Exception e) {
				
			}
	}

}
