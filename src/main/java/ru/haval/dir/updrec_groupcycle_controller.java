package ru.haval.dir;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import ru.haval.share_class.s_class;

public class updrec_groupcycle_controller {
	@FXML
	Label lbl_title_upd, lbl_pm_group, lbl_pm_cycle, lbl_days_gc, lbl_start_date, lbl_duration;
	
	@FXML
	JFXButton add_rec, cancel_form;
	
	@FXML
	TextField txt_pm_group, txt_days_gc, txt_duration;
	
	@FXML
	ComboBox<String> list_pm_cycle;
	
	@FXML
	DatePicker d_start_date;
	
	_query qr = new _query();
	s_class scl = new s_class();
	group_cycle_controller gcc = new group_cycle_controller();
	FxDatePickerConverter fx_dp = new FxDatePickerConverter();
	
	LocalDate new_date;
	
	private Stage stage;
	
	@SuppressWarnings("static-access")
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
		
		txt_pm_group.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		/*list_pm_cycle.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				chk_btn();
			}
		});*/
		list_pm_cycle.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				chk_btn();
			}
		});
		txt_duration.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		
		txt_pm_group.setText(scl.parser_str(qr._select_for_gc_str(gcc._id_gc), 0));
		list_pm_cycle.getSelectionModel().select(scl.parser_str(qr._select_for_gc_str(gcc._id_gc), 1));
		txt_days_gc.setText(scl.parser_str(qr._select_for_gc_str(gcc._id_gc), 2));
		d_start_date.setValue(fx_dp.fromString(scl.parser_str(qr._select_for_gc_str(gcc._id_gc), 3)));
		txt_duration.setText(scl.parser_str(qr._select_for_gc_str(gcc._id_gc), 4));
		//!!!!!!!!!!!Отслеживаем изменение даты!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	Alert alert = new Alert(AlertType.CONFIRMATION);
			    alert.setTitle("M&U - Внимание!");
			    			    
			    alert.setHeaderText("Вы изменили дату! Вы уверены что хотите пересчитать в PM Plan группу: "+txt_pm_group.getText()+" на новую дату?");
			    
			    Optional<ButtonType> option = alert.showAndWait();
			    if (option.get() == null) {
			      
			    } else if (option.get() == ButtonType.OK) {
			  	   try {
			  		   new_date = d_start_date.getValue();
			  		   new_pm_date(gcc._id_gc);
			  		   chk_btn();
			  	   } catch (Exception e1) {
					
				}
			    } else if (option.get() == ButtonType.CANCEL) {
			       return;
			    } else {
			       //label.setText("-");
			    }
            } 
        }; 
        d_start_date.setOnAction(event);
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		add_rec.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				qr._update_for_gc(gcc._id_gc, txt_pm_group.getText(), list_pm_cycle.getValue(), txt_days_gc.getText(), d_start_date.getValue(), txt_duration.getText());
				qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + " - Обновил запись № = " + qr._select_last_id("hmmr_group_cycle") + " в справочнике Группа-Период");
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
	}
	
	private void lang_fun(String loc1, String loc2)
	{
		ResourceBundle lngBndl = ResourceBundle
	            .getBundle("bundles.LangBundle", new Locale(loc1, loc2));
		 
		lbl_title_upd.setText(lngBndl.getString("lbl_title_upd"));
		lbl_pm_group.setText(lngBndl.getString("lbl_group")+":");
		lbl_pm_cycle.setText(lngBndl.getString("lbl_pm_cycle")+":");
		lbl_start_date.setText(lngBndl.getString("col_startdate_ps")+":");
		lbl_duration.setText(lngBndl.getString("lbl_duration")+":");
		
		add_rec.setText(lngBndl.getString("upd_ap"));
		cancel_form.setText(lngBndl.getString("cancel_tsk"));
	}
	
	@SuppressWarnings("static-access")
	private void new_pm_date(int _id_gc)
	{
		String Otv_for_task = null;
		
		//находим Pm_id для группы для которой поменяли дату
		String pm_id = qr._select_pmid(txt_pm_group.getText());
		if(!pm_id.equals("UNKNOWN")) {
			//удаляем все записи из PM Plan группы для которой поменяли дату
			qr._update_new_date(txt_pm_group.getText());
			
			if(!txt_pm_group.getText().equals("0")) {
				if(!scl.parser_sql_str(qr._select_for_pmgroup(txt_pm_group.getText()), 0).equals(txt_pm_group.getText())) {
					try {
						String before_pars = qr._select_for_pmplan(txt_pm_group.getText()).get(0);
						String pereodic = scl.parser_sql_str(before_pars, 0);
						String b_date = fx_dp.toString(new_date);
						
							String e_date = scl.parser_sql_str(before_pars, 2);
							@SuppressWarnings("unused")
							String shop = scl.parser_sql_str(before_pars, 3);
							Otv_for_task = scl.parser_sql_str(before_pars, 4);
							
							int pm_group = Integer.parseInt(txt_pm_group.getText());
							
							int _count = Integer.parseInt(pereodic);
							int _cnt = _count;
							
							int day_bdate = fx_dp.fromString(b_date).getDayOfMonth();
							int month_bdate = fx_dp.fromString(b_date).getMonthValue();
							int year_bdate = fx_dp.fromString(b_date).getYear();
							
							int day_edate = fx_dp.fromString(e_date).getDayOfMonth();
							int month_edate = fx_dp.fromString(e_date).getMonthValue();
							int year_edate = fx_dp.fromString(e_date).getYear();
							
							//Находим количество дней в течении которых должно выполняться ППР, а затем находим сколько надо создать записей в таблице hmmr_pm_year
							int gen_day = Math.abs(day_edate - day_bdate);
							int gen_month = Math.abs(month_edate - month_bdate)*30;
							int gen_year = Math.abs(year_edate - year_bdate)*365;
							
							int _general = Math.round((gen_day + gen_month + gen_year)/_count);
							
							for (int i = 0; i < _general; i++) {
								LocalDate days = LocalDate.of(year_bdate, month_bdate, day_bdate).plusDays(_count);//Расчитываем даты когда заявка должна быть выполнена
								qr._insert_pm_year(pm_id, pm_group, days, Otv_for_task);
								_count = _cnt + _count;
							}
							qr._update_week_year(pm_group);
						
					}
					catch (Exception e) {
						scl._AlertDialog("Не найдено не одного PM соответствующего группе: "+ txt_pm_group.getText() +" !", "Ошибка!");
					}
				}
				else
				{
					scl._AlertDialog("Группа "+ txt_pm_group.getText() +" уже добавлена в PM PLAN!", "Группа уже существует");
				}
			}
			else
				scl._AlertDialog("Группа 0 не может быть добавлена в PM PLAN! Введите корректный номер группы!", "Ошибка!");
		}
		else
		{
			Alert alert = new Alert(AlertType.CONFIRMATION);
		    alert.setTitle("M&U - Внимание!");
		    			    
		    alert.setHeaderText("ВНИМАНИЕ!!! Группы: "+txt_pm_group.getText()+" нет в PM PLAN!\nДобавить эту группу в PM PLAN? Дата старта будет: "+d_start_date.getValue()+"!\nЕсли нет, то эту группу Вам необходимо будет добавить позже вручную!");
		    
		    Optional<ButtonType> option = alert.showAndWait();
		    if (option.get() == null) {
		      
		    } else if (option.get() == ButtonType.OK) {
		  	   try {
		  		   new_date = d_start_date.getValue();
		  		   new_pm_date(fx_dp.toString(new_date));
		  		   chk_btn();
		  	   } catch (Exception e1) {
				
			}
		    } else if (option.get() == ButtonType.CANCEL) {
		    	return;
		    }
		}
	}
	
	@SuppressWarnings("static-access")
	private void new_pm_date(String chk_date)
	{
		String Otv_for_task = null;
				
		if(!chk_date.equals("2018-10-10")) {
			String before_pars = qr._select_for_pmplan(txt_pm_group.getText()).get(0);
			String pereodic = scl.parser_sql_str(before_pars, 0);
			String b_date = fx_dp.toString(new_date);
					
			String e_date = scl.parser_sql_str(before_pars, 2);
			@SuppressWarnings("unused")
			String shop = scl.parser_sql_str(before_pars, 3);
			Otv_for_task = scl.parser_sql_str(before_pars, 4);
						
			int pm_group = Integer.parseInt(txt_pm_group.getText());
						
			int _count = Integer.parseInt(pereodic);
			int _cnt = _count;
						
			int day_bdate = fx_dp.fromString(b_date).getDayOfMonth();
			int month_bdate = fx_dp.fromString(b_date).getMonthValue();
			int year_bdate = fx_dp.fromString(b_date).getYear();
						
			int day_edate = fx_dp.fromString(e_date).getDayOfMonth();
			int month_edate = fx_dp.fromString(e_date).getMonthValue();
			int year_edate = fx_dp.fromString(e_date).getYear();
						
			//Находим количество дней в течении которых должно выполняться ППР, а затем находим сколько надо создать записей в таблице hmmr_pm_year
			int gen_day = Math.abs(day_edate - day_bdate);
			int gen_month = Math.abs(month_edate - month_bdate)*30;
			int gen_year = Math.abs(year_edate - year_bdate)*365;
						
			int _general = Math.round((gen_day + gen_month + gen_year)/_count);
			
			if(!qr._select_recStrSort("hmmr_pm", "id", "del_rec", "PM_Group", txt_pm_group.getText()).equals("NULL")) {
//				System.out.println("GROUP_NUM = " + txt_pm_group.getText() + "PM_ID = " + qr._select_recStrSort("hmmr_pm", "id", "del_rec", "PM_Group", txt_pm_group.getText()));
				for (int i = 0; i < _general; i++) {
					LocalDate days = LocalDate.of(year_bdate, month_bdate, day_bdate).plusDays(_count);//Расчитываем даты когда заявка должна быть выполнена
					qr._insert_pm_year(String.valueOf(qr._select_recStrSort("hmmr_pm", "id", "del_rec", "PM_Group", txt_pm_group.getText())), pm_group, days, Otv_for_task);
					_count = _cnt + _count;
				}
			}
			else
				scl._AlertDialog("Группа "+ txt_pm_group.getText() +" не найдена в таблице PM - Редактор!\nПожалуйста добавте эту группу в PM - Редактор! ","Группа не существует");
		}		
}
	
	private void chk_btn()
	{
		try {
			if(txt_pm_group.getText().length() != 0 && list_pm_cycle.getValue().length() != 0 && txt_duration.getText().length() != 0)
				add_rec.setDisable(false);
			else
				add_rec.setDisable(true);
		}
		catch (Exception e) {
			
		}
	}
}
