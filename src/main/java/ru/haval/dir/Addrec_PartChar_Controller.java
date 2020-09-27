package ru.haval.dir;

import com.jfoenix.controls.JFXButton;

import ru.haval.db._query;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.haval.share_class.s_class;

public class Addrec_PartChar_Controller {
	
	@FXML
	TextField txt_pst, txt_pste, txt_pcn1, txt_pcn2, txt_pcn3, txt_pcn4;
	
	@FXML
	ComboBox<String> list_pt, list_spk, list_pcn1, list_pcn2, list_pcn3, list_pcn4;
	
	@FXML
	Label lbl_title_pc, lbl_pcn, lbl_pcne, lbl_pt, lbl_spk, lbl_pst, lbl_pste, lbl_pcn1, lbl_pcn2, lbl_pcn3, lbl_pcn4;
	
	@FXML
	JFXButton btn_add_pc, btn_cancel_pc;
	
	_query qr = new _query();
	s_class scl = new s_class();
	PartChar_Controller pcc = new PartChar_Controller();
	Stage stage;
	
	@SuppressWarnings("static-access")
	@FXML
	public void initialize()
	{
		scl._style(btn_add_pc);
		scl._style(btn_cancel_pc);
		
		btn_add_pc.setDisable(true);
		
		list_spk.setItems(qr._select_recArr("hmmr_activity_type", "Name", "Description", "del_rec", "ID_OT", 4));
		list_pt.setItems(qr._select_list_str("hmmr_mu_part", "Part_Type"));
		list_pcn1.setItems(qr._select_list_str("hmmr_partchar_dir", "Part_Char"));
		list_pcn2.setItems(qr._select_list_str("hmmr_partchar_dir", "Part_Char"));
		list_pcn3.setItems(qr._select_list_str("hmmr_partchar_dir", "Part_Char"));
		list_pcn4.setItems(qr._select_list_str("hmmr_partchar_dir", "Part_Char"));
		
		if(!pcc._flag_window_pc)
		{
			lbl_title_pc.setText("Обновление записи Part Char");
			
			String _sql_rez = qr._select_for_update_partchar(pcc._id_part_char);
			
			list_pt.getSelectionModel().select(scl.parser_str_str_str(_sql_rez, 0));
			list_spk.getSelectionModel().select(scl.parser_str_str_str(_sql_rez, 1));
			txt_pst.setText(scl.parser_str_str_str(_sql_rez, 2));
			txt_pste.setText(scl.parser_str_str_str(_sql_rez, 3));
			txt_pcn1.setText(scl.parser_str_str_str(_sql_rez, 4));
			txt_pcn2.setText(scl.parser_str_str_str(_sql_rez, 5));
			txt_pcn3.setText(scl.parser_str_str_str(_sql_rez, 6));
			txt_pcn4.setText(scl.parser_str_str_str(_sql_rez, 7));
		}
		else
			lbl_title_pc.setText("Добавление записи к Part Char");
		
		txt_pst.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_pste.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_pcn1.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_pcn2.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_pcn3.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_pcn4.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		list_pt.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				chk_btn();
			}
		});
		list_spk.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				chk_btn();
			}
		});
		
		list_pcn1.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				txt_pcn1.setText(list_pcn1.getValue());
				chk_btn();
			}
		});
		list_pcn2.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				txt_pcn2.setText(list_pcn2.getValue());
				chk_btn();
			}
		});
		list_pcn3.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				txt_pcn3.setText(list_pcn3.getValue());
				chk_btn();
			}
		});
		list_pcn4.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				txt_pcn4.setText(list_pcn4.getValue());
				chk_btn();
			}
		});
		
		btn_add_pc.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				if(pcc._flag_window_pc)
					qr._insert_partchar(list_pt.getValue(), scl.parser_str(list_spk.getValue(), 0), txt_pst.getText(), txt_pste.getText(), txt_pcn1.getText(), txt_pcn2.getText(), txt_pcn3.getText(), txt_pcn4.getText());
				else
					qr._update_partchar(pcc._id_part_char, list_pt.getValue(), scl.parser_str(list_spk.getValue(), 0), txt_pst.getText(), txt_pste.getText(), txt_pcn1.getText(), txt_pcn2.getText(), txt_pcn3.getText(), txt_pcn4.getText());
				
				pcc._table_update_partchar.addAll(qr._select_data_partchar());
				
				stage = (Stage) btn_add_pc.getScene().getWindow();
				stage.close();
			}
		});
		
		btn_cancel_pc.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				stage = (Stage) btn_cancel_pc.getScene().getWindow();
				stage.close();
			}
		});
	}
	
	private void chk_btn()
	{
		try {
			if(txt_pst.getText().length() != 90 && txt_pste.getText().length() != 0 &&
					txt_pcn1.getText().length() != 0 && txt_pcn2.getText().length() != 0 && txt_pcn3.getText().length() != 0 && txt_pcn4.getText().length() != 0)
			{
				btn_add_pc.setDisable(false);
			}
			else
				btn_add_pc.setDisable(true);
			}
			catch (Exception e) {
				
			}
	}

}
