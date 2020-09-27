package ru.haval.dir;

import com.jfoenix.controls.JFXButton;

import ru.haval.action.apwr_controller;
import  ru.haval.application.conn_connector;
import  ru.haval.data.FxDatePickerConverter;
import  ru.haval.db._query;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import ru.haval.share_class.s_class;

public class updrec_cycle_controller
{
	@FXML
	JFXButton add_cycle_cancel, upd_cycle;
	
	@FXML
	TextField type_cycle, pereodic_cycle, moto_cycle;
	
	@FXML
	DatePicker bdate_cycle, edate_cycle;
	
	@FXML
	Label err_info_cycle;
	
	private Stage stage;
	private String _tc, _pc, _hc, _dc, _edc;
	s_class scl = new s_class();
	_query qr = new _query();
	FxDatePickerConverter fx_dp = new FxDatePickerConverter();
	CycleController cl = new CycleController();
	Tooltip tip;
	
	public updrec_cycle_controller()
	{
		
	}
	@FXML
	private void initialize()
	{
		scl._style(upd_cycle);
		scl._style(add_cycle_cancel);
		
		type_cycle.setText(CycleController._type);
		pereodic_cycle.setText(CycleController._pereodic);
		moto_cycle.setText(CycleController._hours);
		bdate_cycle.setValue(fx_dp.fromString(CycleController._bdate));
		edate_cycle.setValue(fx_dp.fromString(CycleController._edate));
		
		//разрешаем вводить только цифры и не больше трех
				pereodic_cycle.textProperty().addListener(new ChangeListener<String>() {

					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
						if(!newValue.isEmpty()) {
				
						if (!newValue.matches("\\d*|#|\\*")) {
				            pereodic_cycle.setText(newValue.replaceAll("[^\\d|#|\\*]", ""));
				        }
						if(newValue.length() > 5) {
							
			            	pereodic_cycle.setText(newValue.replaceAll("[0-9]", ""));
			            	
						}
					}
					}
				});
				moto_cycle.textProperty().addListener(new ChangeListener<String>() {

					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
						if(!newValue.isEmpty()) {
				
						if (!newValue.matches("\\d*|#|\\*")) {
				            moto_cycle.setText(newValue.replaceAll("[^\\d|#|\\*]", ""));
				        }
						if(newValue.length() > 5) {
							
			            	moto_cycle.setText(newValue.replaceAll("[0-9]", ""));
			            	
						}
					}
					}
				});
		
		pereodic_cycle.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip = new Tooltip("необходимо указать переодичность ППР в днях. Например: еженедельно - 7 дней,\n "
			      		+ " раз в две недели - 14 дней, раз в полгода - 182.5 дней, раз в год 365 дней и т.д.");
				Point2D p = pereodic_cycle.localToScreen(pereodic_cycle.getLayoutBounds().getMaxX(), pereodic_cycle.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(pereodic_cycle, p.getX(), p.getY());
			}
		});
		pereodic_cycle.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip.hide();
			}
		});
		
		edate_cycle.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip = new Tooltip("Это значение задает количество ППР, которые необходимо выполнить в будущем, \n" 
						+ " например: Дата начала - 01.08.2018, Дата окончания - 01.09.2018 и в колонке Переодичность значение - 7 дней \n" 
						+ " (т.е. еженедельно), тогда будет создано пять ППР - 01.08.2018; 08.08.2018; 15.08.2018; 23.08.2018 и 30.08.2018");
				Point2D p = edate_cycle.localToScreen(edate_cycle.getLayoutBounds().getMaxX(), edate_cycle.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(edate_cycle, p.getX(), p.getY());
			}
		});
		edate_cycle.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip.hide();
			}
		});
		
		add_cycle_cancel.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				stage = (Stage) add_cycle_cancel.getScene().getWindow();
				stage.close();
			}
		});
		upd_cycle.setOnAction(new EventHandler<ActionEvent>() {
			
			@SuppressWarnings("static-access")
			@Override
			public void handle(ActionEvent event) {
				if(type_cycle.getText().length() != 0 && pereodic_cycle.getText().length() != 0 && moto_cycle.getText().length() != 0 && bdate_cycle.getValue().toString().length() != 0 && edate_cycle.getValue().toString().length() != 0)
				{
					err_info_cycle.setVisible(false);
					
					_tc = type_cycle.getText();
					_pc = pereodic_cycle.getText();
					_hc = moto_cycle.getText();
					_dc = bdate_cycle.getValue().toString();
					_edc = edate_cycle.getValue().toString();
					
					qr._update_rec_cycle(_tc, _pc, _hc, _dc, _edc, CycleController._id);
					
					qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + " - Обновил запись № = " + CycleController._id + " в таблице PM Cycle");
					
					cl._table_update_cycle.addAll(qr._select_data_cycle());
					
					stage = (Stage) upd_cycle.getScene().getWindow();
					stage.close();
				}
				else
					err_info_cycle.setVisible(true);
			}
		});
	}
}