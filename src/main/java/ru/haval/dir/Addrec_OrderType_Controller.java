package ru.haval.dir;

import com.jfoenix.controls.JFXButton;

import ru.haval.action.apwr_controller;
import ru.haval.application.conn_connector;
import ru.haval.db._query;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.haval.share_class.s_class;

public class Addrec_OrderType_Controller {
	
	@FXML
	TextField txt_name_ot;
	
	@FXML
	TextArea txt_desc_ot;
	
	@FXML
	Label title_ot_add, lbl_name_ot, lbl_desc_ot;
	
	@FXML
	JFXButton add_ot, cancel_ot;
	
	s_class scl = new s_class();
	_query qr = new _query();
	Order_Type_Controller otc = new Order_Type_Controller();
	
	private Stage stage;
	
	@FXML
	public void initialize()
	{
		scl._style(add_ot);
		scl._style(cancel_ot);
		
		add_ot.setDisable(true);
		
		add_ot.setOnAction(new EventHandler<ActionEvent>() {
			
			@SuppressWarnings("static-access")
			@Override
			public void handle(ActionEvent arg0) {
				qr._insert_ot(txt_name_ot.getText(), txt_desc_ot.getText());
				
				qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + " - Создал запись № = " + qr._select_last_id("hmmr_order_type") + " в Справочнике Order Type");
				
				otc._table_update_ot.addAll(qr._select_data_ot());
				stage = (Stage) add_ot.getScene().getWindow();
				stage.close();
			}
		});
		cancel_ot.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				stage = (Stage) cancel_ot.getScene().getWindow();
				stage.close();
			}
		});
		txt_name_ot.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_desc_ot.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
	}
	
	private void chk_btn()
	{
		try {
			if(txt_name_ot.getText().length() != 0 && txt_desc_ot.getText().length() != 0)
				add_ot.setDisable(false);
			else
				add_ot.setDisable(true);
			}
			catch (Exception e) {
				
			}
	}

}
