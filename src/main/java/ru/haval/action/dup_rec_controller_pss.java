package ru.haval.action;

import com.jfoenix.controls.JFXButton;

import  ru.haval.db._query;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.haval.share_class.s_class;

public class dup_rec_controller_pss {
	
	@FXML
	TextField num_rec_ps;
	
	@FXML
	JFXButton dup_rec_add, dup_cancel;
	
	s_class scl = new s_class();
	_query qr = new _query();
	PartSpec_Controller psc = new PartSpec_Controller();
	Stage stage;
	
	public static int _qty_rec = 0;
	@FXML
	public void initialize()
	{
		scl._style(dup_rec_add);
		scl._style(dup_cancel);
		
		dup_rec_add.setDisable(true);
		
		num_rec_ps.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(!newValue.isEmpty()) {
		
				if (!newValue.matches("\\d*|#|\\*")) {
					num_rec_ps.setText(newValue.replaceAll("[^\\d|#|\\*]", ""));
		        }
				if(newValue.length() > 5) {
					
					num_rec_ps.setText(newValue.replaceAll("[0-9]", ""));
	            	
				}
			}
			}
		});
		dup_rec_add.setOnAction(new EventHandler<ActionEvent>() {
			
			@SuppressWarnings("static-access")
			@Override
			public void handle(ActionEvent arg0) {
				try {
					if(num_rec_ps.getText().length() != 0)
						for(int i = 0; i < Integer.parseInt(num_rec_ps.getText()); i++)
						{
							qr._insert_psc_dup(psc._id_parts);
						}
				}
				catch (Exception e) {
					
				}
				psc._table_update_parts.addAll(qr._select_data_parts());
				stage = (Stage) dup_rec_add.getScene().getWindow();
				stage.close();
			}
		});
		dup_cancel.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				stage = (Stage) dup_cancel.getScene().getWindow();
				stage.close();
			}
		});
		num_rec_ps.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				if(num_rec_ps.getText().length() != 0)
					dup_rec_add.setDisable(false);
			}
		});
	}

}
