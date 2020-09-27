package ru.haval.dir;

import java.io.IOException;
import java.util.Optional;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import ru.haval.action.apwr_controller;
import  ru.haval.application.conn_connector;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ru.haval.share_class.s_class;

public class CycleController
{
	@FXML
	TableView<Cycle> table_cycle;
	
	@FXML
	TableColumn<Cycle, String> col_id;
	
	@FXML
	TableColumn<Cycle, String> col_type;
	
	@FXML
	TableColumn<Cycle, String> col_pereodic;
	
	@FXML
	TableColumn<Cycle, String> col_motohours, col_begin_date, col_end_date;
	
	@FXML
	JFXButton add_c, upd_c, del_c, cancel_c, upd_table_cycle;
	
	@FXML
	JFXRadioButton K10, K20, K30, K40, K50, K60, K70;
	
	_query qr = new _query();
	private Stage stage = new Stage();
	s_class scl = new s_class();
	
	public static ObservableList<TableColumn<Cycle, ?>> columns_cycle;
	public static ObservableList<JFXRadioButton> rbuttons = FXCollections.observableArrayList();
	public static ObservableList<Cycle> _table_update_cycle = FXCollections.observableArrayList();
	
	public static String _id, _type, _pereodic, _hours, _bdate, _edate;
		
	
	@FXML
	public void initialize() {
		scl._style(add_c);
		scl._style(upd_c);
		scl._style(del_c);
		scl._style(cancel_c);
		scl._style(upd_table_cycle);
		
       	initData();
       	
       	columns_cycle = table_cycle.getColumns();
       	
       	table_cycle.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);
        
       	col_id.setCellValueFactory(CellData -> CellData.getValue().IdProperty());
       	col_type.setCellValueFactory(CellData -> CellData.getValue().TypeProperty());
       	col_pereodic.setCellValueFactory(CellData -> CellData.getValue().PereodicProperty());
       	col_motohours.setCellValueFactory(CellData -> CellData.getValue().HoursProperty());
       	col_begin_date.setCellValueFactory(CellData -> CellData.getValue().BeginDateProperty());
       	col_end_date.setCellValueFactory(CellData -> CellData.getValue().EndDateProperty());
       	
       	col_id.setSortable(false);
       	col_type.setSortable(false);
       	col_pereodic.setSortable(false);
       	col_motohours.setSortable(false);
       	col_begin_date.setSortable(false);
       	col_end_date.setSortable(false);
       	
       	table_cycle.setEditable(true);
       	
       	add_c.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				try {
					//_flag = false;
					pmcycle_add(stage);
					//_flag = true;
					//t = new Thread(update_table());
	        		//t.setDaemon(true);
	        		//t.start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
      cancel_c.setOnAction(new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent event) {
			stage = (Stage) cancel_c.getScene().getWindow();
			stage.close();
		}
	}); 
      
      upd_c.setOnAction(new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent event) {
			upd_c.setDisable(true);
			del_c.setDisable(true);
			Cycle _ccl1 = table_cycle.getSelectionModel().getSelectedItem();
			try {
			func_upd(_ccl1.getId());
			} catch (Exception e) {
				
			}
		}
	});
            
      del_c.setOnAction(new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent event) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
		    alert.setTitle("M&U - Delete Record Window");
		    Cycle _ccl = table_cycle.getSelectionModel().getSelectedItem();
		    
		    alert.setHeaderText("Вы действительно хотите удалить запись № = " + _ccl.getId() + "?");
		    //alert.setContentText("C:/MyFile.txt");
		 
		    // option != null.
		    Optional<ButtonType> option = alert.showAndWait();
		    if (option.get() == null) {
		       //label.setText("No selection!");
		    } else if (option.get() == ButtonType.OK) {
		  	   _ccl = table_cycle.getSelectionModel().getSelectedItem();
		  	   try {
		  	   func_del(_ccl.getId());
		  	   } catch (Exception e) {
				
			}
		    } else if (option.get() == ButtonType.CANCEL) {
		       //label.setText("Cancelled!");
		    } else {
		       //label.setText("-");
		    }
		}
	});
      
      Label per = new Label("Периодичность, дни");
      per.setTooltip(new Tooltip("необходимо указать переодичность ППР в днях. Например: еженедельно - 7 дней,\n "
      		+ " раз в две недели - 14 дней, раз в полгода - 182.5 дней, раз в год 365 дней и т.д."));
      col_pereodic.setGraphic(per);
      
      Label per1 = new Label("Дата окончания");
      per1.setTooltip(new Tooltip("Это значение задает количество ППР, которые необходимо выполнить в будущем,\n "
      		+ " например: Дата начала - 01.08.2018, Дата окончания - 01.09.2018 и в колонке Переодичность значение - 7 дней \n "
      		+ " (т.е. еженедельно), тогда будет создано пять ППР - 01.08.2018; 08.08.2018; 15.08.2018; 23.08.2018 и 30.08.2018"));
      col_end_date.setGraphic(per1);
      
            
    //Вызываем окно обновления по двойному клику на строке
    table_cycle.setOnMousePressed(new EventHandler<MouseEvent>() {
    	@Override
    	public void handle(MouseEvent event) {
    		if (event.getClickCount() == 2 ){
                   func_upd(table_cycle.getSelectionModel().getSelectedItem().getId());
               }
    	}
    });  
    
    //Клик мышью внутри таблицы останавливает поток и активирует кнопки Обновить и Удалить
    table_cycle.setOnMouseClicked(new EventHandler<Event>() {
		@Override
		public void handle(Event event) {
			upd_c.setDisable(false);
			if(!conn_connector.USER_ROLE.equals("Technics") || !conn_connector.USER_ROLE.equals("Engeneer"))
				del_c.setDisable(false);
			}
	});
    upd_table_cycle.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			ref_table();
		}
	});
        
    rbuttons.add(K10);
	rbuttons.add(K20);
	rbuttons.add(K30);
	rbuttons.add(K40);
	rbuttons.add(K50);
	rbuttons.add(K60);
	rbuttons.add(K70);
        
    scl._moto_to_days(K10);
    scl._moto_to_days(K20);
    scl._moto_to_days(K30);
    scl._moto_to_days(K40);
    scl._moto_to_days(K50);
    scl._moto_to_days(K60);
    scl._moto_to_days(K70);
        
    if(qr._select_rbtn().equals("K10"))
      	K10.setSelected(true);
      if(qr._select_rbtn().equals("K20"))
       	K20.setSelected(true);
      if(qr._select_rbtn().equals("K30"))
       	K30.setSelected(true);
      if(qr._select_rbtn().equals("K40"))
       	K40.setSelected(true);
      if(qr._select_rbtn().equals("K50"))
       	K50.setSelected(true);
      if(qr._select_rbtn().equals("K60"))
       	K60.setSelected(true);
      if(qr._select_rbtn().equals("K70"))
       	K70.setSelected(true);
        
      upd_c.setDisable(true);
      del_c.setDisable(true);
        
      _table_update_cycle.addListener(new ListChangeListener<Cycle>() {
		@Override
		public void onChanged(Change<? extends Cycle> c) {
		   	table_cycle.setItems(qr._select_data_cycle());
		   	table_cycle.getColumns().get(0).setVisible(false);
		    table_cycle.getColumns().get(0).setVisible(true);
		}
		});
    }
	
	//Вызываем окно добавления записи к справочнику PM_Cycle
	protected void pmcycle_add(Stage stage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("add_rec_cycle.fxml"));
		   
	    Scene scene = new Scene(root);
	    stage.setTitle("M&U - Add Record Window");
	    stage.setResizable(false);
	    //stage.initModality(Modality.APPLICATION_MODAL);
	    stage.setScene(scene);
	    stage.show();
	}
	//Вызываем окно обновления записи
	protected void pmcycle_upd(Stage stage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("upd_rec_cycle.fxml"));
		   
	    Scene scene = new Scene(root);
	    stage.setTitle("M&U - Update Record Window");
	    stage.setResizable(false);
	    //stage.initModality(Modality.APPLICATION_MODAL);
	    stage.setScene(scene);
	    stage.show();
	}

	private void initData()
	{
		table_cycle.setItems(qr._select_data_cycle());
	}
	
	private String parser_sql_str(String str, int count)
	{
		String[] p_str = null;
		for(int i = 0; i < str.length(); i++)
		{
			p_str = str.split(","); 
		}
		return p_str[count];
	}
	
	private void func_upd(String str)
	{
		String _sql_rez = qr._select_for_update_cycle(str);
		_id = str;
		_type = parser_sql_str(_sql_rez, 0);
		_pereodic = parser_sql_str(_sql_rez, 1);
		_hours = parser_sql_str(_sql_rez, 2);
		_bdate = parser_sql_str(_sql_rez, 3);
		_edate = parser_sql_str(_sql_rez, 4);
		try {
			pmcycle_upd(stage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void func_del(String str)
	{
		qr._update_rec_pmcycle_del(str);
		qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + " - Удалил запись № = " + str + " в таблице PM Cycle");
		_id = str;
	}
	//Функция обновления таблицы
	private void ref_table()
	{
		table_cycle.setItems(qr._select_data_cycle());
		columns_cycle.get(0).setVisible(false);
	    columns_cycle.get(0).setVisible(true);
	}
}