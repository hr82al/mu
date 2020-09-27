package ru.haval.dir;

import com.jfoenix.controls.JFXButton;

import ru.haval.action.apwr_controller;
import  ru.haval.application.conn_connector;
import  ru.haval.db._query;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.haval.share_class.s_class;

public class Addrec_Parttype_Controller {
	
	@FXML
	TextField txt_part_type, txt_part_type_eng;
	
	@FXML
	JFXButton btn_add_part, btn_cancel_part;
	
	@FXML
	Label title_part_add, lbl_part_type, lbl_part_type_eng;
	
	_query qr = new _query();
	s_class scl = new s_class();
	apwr_controller pic = new apwr_controller();
	Part_Controller pc = new Part_Controller();
	Stage stage;
	
	@SuppressWarnings("static-access")
	@FXML
	public void initialize()
	{
		scl._style(btn_add_part);
		scl._style(btn_cancel_part);
		
		btn_add_part.setDisable(true);
		
		if(!pc._flag_window)
		{
			title_part_add.setText("Обновление записи Part Type");
			
			String _sql_rez = qr._select_for_update_part(pc._id_part);
			
			txt_part_type.setText(scl.parser_sql_str(_sql_rez, 0));
			txt_part_type_eng.setText(scl.parser_sql_str(_sql_rez, 1));
		}
		else
			title_part_add.setText("Добавление записи к Part Type");
		
		txt_part_type.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_part_type_eng.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		btn_add_part.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				if(pc._flag_window)
				{
					qr._insert_part(txt_part_type.getText(), txt_part_type_eng.getText());
					qr._insert_history(conn_connector.USER_ID, pic.USER_S + " - Добавил запись № = " + qr._select_last_id("hmmr_mu_part") + " в таблице Part Type");
				}
				else
				{
					qr._update_field_part(pc._id_part, txt_part_type.getText(), txt_part_type_eng.getText());
					qr._insert_history(conn_connector.USER_ID, pic.USER_S + " - О запись № = " + qr._select_last_id("hmmr_mu_part") + " в таблице Part Type");
				}
				pc._table_update_part.addAll(qr._select_data_part());
				
				stage = (Stage) btn_add_part.getScene().getWindow();
				stage.close();
			}
		});
		btn_cancel_part.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				stage = (Stage) btn_cancel_part.getScene().getWindow();
				stage.close();
			}
		});
	}
	
	private void chk_btn()
	{
		try {
			if(txt_part_type.getText().length() != 0 && txt_part_type_eng.getText().length() != 0)
			{
				btn_add_part.setDisable(false);
			}
			else
				btn_add_part.setDisable(true);
			}
			catch (Exception e) {
				
			}
	}

}
