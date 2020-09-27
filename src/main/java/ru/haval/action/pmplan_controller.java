package ru.haval.action;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;

import  ru.haval.application.conn_connector;
import  ru.haval.data.FxDatePickerConverter;
import  ru.haval.db._query;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ru.haval.share_class.s_class;

public class pmplan_controller {
	
	@FXML
	TableView<hmmr_pmplan_model> table_pmplan = new TableView<>();
	
	@FXML
	TableColumn<hmmr_pmplan_model, String> col_num_pmplan, col_numpm_pmplan, col_dd_pmplan, col_resp_pmplan, col_group_pm_pmplan;
	
	@FXML
	JFXDatePicker b_data_pmplan, e_data_pmplan;
	
	@FXML
	JFXButton upd_table_pmplan, upd_pmplan, cancel_pmplan, filter_clear;
	
	@FXML
	Label lbl_upd_pmplan, lbl_search_pmplan, lbl_with_pmplan, lbl_to_pmplan;
	
	private String _sql_rez;
	public static String _id_pmplan, _numpm_pmplan, _date_pmplan, _oft_pmplan, _pm_group, _pm_id;
	
	public static ObservableList<hmmr_pmplan_model> _table_update_pmplan = FXCollections.observableArrayList();
	
	_query qr = new _query();
	s_class scl = new s_class();
	public Stage stage = new Stage();
	FxDatePickerConverter fx_dp = new FxDatePickerConverter();
	int flag = 0;//Переменная нужна для обновления таблицы, например: если отсортировали таблицу по номеру, то при нажатии на кнопку обновить таблицу
	//таблица примет первоначальный вид заполнения данными, а надо чтобы при нажатии на кнопку сохранился бы тот вид который стал во время сортировки
	//т. е. в зависимости от значения этого флага при нажатии на кнопку обновления таблицы, будем подставлять тот запрос, который использовался для фильтра
	
	@FXML
	public void initialize()
	{
		b_data_pmplan.setValue(LocalDate.now().minusYears(1));
		e_data_pmplan.setValue(LocalDate.now());
		
		if(conn_connector.LANG_ID == 1)
			lang_fun("en", "EN");
		if(conn_connector.LANG_ID == 0)
			lang_fun("ru", "RU");
		if(conn_connector.LANG_ID == 2)
			lang_fun("zh", "CN");
		if(conn_connector.LANG_ID == -1)
			lang_fun("ru", "RU");
		
		scl._style(upd_table_pmplan);
		scl._style(upd_pmplan);
		scl._style(cancel_pmplan);
		scl._style(filter_clear);
		
		filter_clear.setDisable(true);
		
		col_num_pmplan.setCellValueFactory(CellData -> CellData.getValue().IdProperty());
		//col_numpm_pmplan.setCellValueFactory(CellData -> CellData.getValue().num_pmProperty());
		col_group_pm_pmplan.setCellValueFactory(CellData -> CellData.getValue().pm_groupProperty());
		col_dd_pmplan.setCellValueFactory(CellData -> CellData.getValue().ddProperty());
		col_resp_pmplan.setCellValueFactory(CellData -> CellData.getValue().respProperty());
		
		
		table_pmplan.setEditable(true);
		upd_pmplan.setDisable(true);
		
		initData();
		
		table_pmplan.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				try {
					upd_pmplan.setDisable(false);
				}
				catch (Exception e) {
					
				}
			}
		});
		
		upd_pmplan.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				hmmr_pmplan_model _ccl1 = table_pmplan.getSelectionModel().getSelectedItem();
				try {
					//mu_main_controller.getPrimaryStage().setAlwaysOnTop(false);
					upd_pmplan.setDisable(true);
					func_upd(_ccl1.getId());
				} catch (Exception e) {
					
				}
			}
		});
		
		e_data_pmplan.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				table_pmplan.setItems(qr._select_data_pmplan(fx_dp.toString(b_data_pmplan.getValue()), fx_dp.toString(e_data_pmplan.getValue())));
				table_pmplan.getColumns().get(0).setVisible(false);
				table_pmplan.getColumns().get(0).setVisible(true);
				filter_clear.setDisable(false);
				flag = 1;
			}
		});
		
		filter_clear.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				table_pmplan.setItems(qr._select_data_pmplan());
				table_pmplan.getColumns().get(0).setVisible(false);
				table_pmplan.getColumns().get(0).setVisible(true);
				flag = 0;
				filter_clear.setDisable(true);
			}
		});
		
		upd_table_pmplan.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(flag == 1)
				{
					table_pmplan.setItems(qr._select_data_pmplan(fx_dp.toString(b_data_pmplan.getValue()), fx_dp.toString(e_data_pmplan.getValue())));
					table_pmplan.getColumns().get(0).setVisible(false);
					table_pmplan.getColumns().get(0).setVisible(true);
					filter_clear.setDisable(false);
				}
				else if(flag == 0)
				{
					table_pmplan.setItems(qr._select_data_pmplan());
					table_pmplan.getColumns().get(0).setVisible(false);
					table_pmplan.getColumns().get(0).setVisible(true);
				}
			}
		});
		cancel_pmplan.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				stage = (Stage) cancel_pmplan.getScene().getWindow();
				stage.close();
			}
		});
		_table_update_pmplan.addListener(new ListChangeListener<hmmr_pmplan_model>() {
		    @Override
			public void onChanged(Change<? extends hmmr_pmplan_model> c) {
				table_pmplan.setItems(qr._select_data_pmplan());
		    	table_pmplan.getColumns().get(0).setVisible(false);
		        table_pmplan.getColumns().get(0).setVisible(true);
			}
		});
		//Вызываем окно обновления по двойному клику на строке
		table_pmplan.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() == 2 ){
		               func_upd(table_pmplan.getSelectionModel().getSelectedItem().getId());
		           }
			}
		});
	}
	
	private void initData()
	{
		table_pmplan.setItems(qr._select_data_pmplan());
	}

	private void func_upd(String str)
	{
		_sql_rez = qr._select_for_update_pmplan(str);
		_id_pmplan = str; 
		//_pm_id = scl.parser_str_str_str(_sql_rez, 0);
		_pm_group = scl.parser_str_str_str(_sql_rez, 0);
		_date_pmplan = scl.parser_str_str_str(_sql_rez, 1);
		_oft_pmplan = scl.parser_str_str_str(_sql_rez, 2);
		
				
		try {
			//_flag = false;
			pmplan_upd(stage);
			//_flag = true;
			//t = new Thread(update_table());
    		//t.setDaemon(true);
    		//t.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Вызываем окно обновления записи
	protected void pmplan_upd(Stage stage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("upd_rec_pmplan.fxml"));
				   
	    Scene scene = new Scene(root);
	    stage.setTitle("M&U - Update Record Window");
	    stage.setResizable(false);
	    //stage.initModality(Modality.APPLICATION_MODAL);
	    stage.setScene(scene);
	    stage.show();
	}
	
	private void lang_fun(String loc1, String loc2)
	{
		ResourceBundle lngBndl = ResourceBundle
	            .getBundle("bundles.LangBundle", new Locale(loc1, loc2));
		
		lbl_upd_pmplan.setText(lngBndl.getString("lbl_upd_pmplan"));
		lbl_search_pmplan.setText(lngBndl.getString("lbl_search_pmplan"));
		lbl_with_pmplan.setText(lngBndl.getString("from_wr"));
		lbl_to_pmplan.setText(lngBndl.getString("to_wr"));
		col_dd_pmplan.setText(lngBndl.getString("dd_ap"));
		col_resp_pmplan.setText(lngBndl.getString("lbl_oft_ap"));
		col_group_pm_pmplan.setText(lngBndl.getString("lbl_group"));
		upd_pmplan.setText(lngBndl.getString("upd_ap"));
		cancel_pmplan.setText(lngBndl.getString("cancel_tsk"));
		upd_table_pmplan.setText(lngBndl.getString("upd_table_ap"));
		filter_clear.setText(lngBndl.getString("clear_filter"));
	}
}
