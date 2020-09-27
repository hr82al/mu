package ru.haval.action;

import org.controlsfx.control.textfield.TextFields;

import com.jfoenix.controls.JFXButton;

import  ru.haval.application.conn_connector;
import  ru.haval.db._query;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.haval.share_class.s_class;

public class AddRec_CS_Controller {
	
	@FXML
	TextField txt_num_compex, txt_num_sub;
	
	@FXML
	JFXButton btn_accept, btn_cancel;
	
	@FXML
	Label lbl_title_cs;
	
	_query qr = new _query();
	s_class scl = new s_class();
	CS_Controller csc = new CS_Controller();
	Stage stage;
	
	ObservableList<String> list_num_complex = FXCollections.observableArrayList();
	ObservableList<String> list_sub = FXCollections.observableArrayList();
	
	@SuppressWarnings("static-access")
	public void initialize()
	{
		scl._style(btn_accept);
		scl._style(btn_cancel);
		
		btn_accept.setDisable(true);
		
		list_num_complex.addAll(qr._select_num_cs());//_select_list_str("hmmr_sp_db", "HMMR_Material_Num"));
		list_sub.addAll(qr._select_num_cs1());//_select_recArr("hmmr_sp_db", "HMMR_Material_Num", "SP_MU_Description_RUS", "del_rec"));
		
		TextFields.bindAutoCompletion(txt_num_compex, list_num_complex);
		TextFields.bindAutoCompletion(txt_num_sub, list_sub);
		
		if(!csc._txt_data.equals("null"))
			txt_num_compex.setText(csc._txt_data);
		
		if(!csc._flag_window_cs)
		{
			lbl_title_cs.setText("Обновление записи в Component Specification");
			
			String _sql_rez = qr._select_for_update_cs(String.valueOf(csc._id_cs));
			
			txt_num_compex.setText(scl.parser_str_str_str(_sql_rez, 0));
			txt_num_sub.setText(scl.parser_str_str_str(_sql_rez, 1));
		}
		else
			lbl_title_cs.setText("Добавление записи к Component Specification");
		
		txt_num_compex.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_num_sub.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		
		btn_accept.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				csc._txt_data = txt_num_compex.getText();
				if(csc._flag_window_cs) {
					qr._insert_cs(qr._select_id("hmmr_sp_db", "HMMR_Material_Num", scl.parser_str(txt_num_compex.getText(), 0)), qr._select_id("hmmr_sp_db", "HMMR_Material_Num", scl.parser_str(txt_num_sub.getText(), 0)));
					qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + " - Создал запись № = " + qr._select_last_id("hmmr_comp_spec") + " в таблице Components Specification");
				}
				else
				{
					qr._update_cs(String.valueOf(csc._id_cs), qr._select_id("hmmr_sp_db", "HMMR_Material_Num", scl.parser_str(txt_num_compex.getText(), 0)), qr._select_id("hmmr_sp_db", "HMMR_Material_Num", scl.parser_str(txt_num_sub.getText(), 0)));
					qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + " - Обновил запись № = " + csc._id_cs + " в таблице Components Specification");
				}
				
				if(csc._flag_tbl_upd_cs == 0)
					csc._table_update_cs.addAll(qr._select_data_cs());
				if(csc._flag_tbl_upd_cs == 1)
					csc._table_update_cs.addAll(qr._select_data_cs_sort("HMMR_Material_Num_Complex", csc._search));
				if(csc._flag_tbl_upd_cs == 2)
					csc._table_update_cs.addAll(qr._select_data_cs_sort(csc._search));
				
				stage = (Stage) btn_accept.getScene().getWindow();
				stage.close();
			}
		});
		btn_cancel.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				stage = (Stage) btn_cancel.getScene().getWindow();
				stage.close();
			}
		});
	}
	
	void chk_btn()
	{
		try {
			if(txt_num_compex.getText().length() != 0 && txt_num_sub.getText().length() != 0)
				btn_accept.setDisable(false);
			else
				btn_accept.setDisable(true);
		}
		catch (Exception e) {
			
		}
	}

}
