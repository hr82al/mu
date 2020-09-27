package ru.haval.action;

import java.io.IOException;
import java.util.Optional;

import org.controlsfx.control.textfield.TextFields;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;

import  ru.haval.application.conn_connector;
import ru.haval.application.mu_main_controller;
import  ru.haval.db._query;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import ru.haval.share_class.s_class;

public class CS_Controller {
	
	@FXML
	TableView<Hmmr_CS_Model> table_cs = new TableView<>();
	
	@FXML
	TableColumn<Hmmr_CS_Model, String> col_id_cs, col_comp_id, col_sub_id;
	
	@FXML
	JFXButton btn_add_rec, btn_upd_rec, btn_del_rec, btn_upd_tbl, btn_cancel, clear_filter;
	
	@FXML JFXRadioButton rbtn_num, rbtn_name;
	
	@FXML
	TextField txt_search;
	
	@FXML
	Label lbl_title_cs;
	
	_query qr = new _query();
	s_class scl = new s_class();
	
	String name_complex = "Название материала (complex)";
	private String name_subpart = "Название материала (sub-part)";
	TableColumn<Hmmr_CS_Model, String> col_complex_id = new TableColumn<Hmmr_CS_Model, String>(name_complex);
	TableColumn<Hmmr_CS_Model, String> col_subpart_id = new TableColumn<Hmmr_CS_Model, String>(name_subpart);
	
	public static int _flag_sort_cs = 0;
	
	ObservableList<String> list_num = FXCollections.observableArrayList();
	ObservableList<String> list_name = FXCollections.observableArrayList();
	public static ObservableList<Hmmr_CS_Model> _table_update_cs = FXCollections.observableArrayList();
	public static int _flag_tbl_upd_cs = 0, _id_cs = 0;
	public static Boolean _flag_window_cs = true;
	Stage stage = new Stage();
	public static String _search = "null";
	public static String _txt_data = "null";//в эту переменную пишем номер комплексного материала, чтоб при открытии формы добавления записи этот номер уже подставлялся в форму
	
	public void initialize()
	{
		scl._style(btn_add_rec);
		scl._style(btn_upd_rec);
		scl._style(btn_del_rec);
		scl._style(btn_upd_tbl);
		scl._style(btn_cancel);
		scl._style(clear_filter);
		
		btn_upd_rec.setDisable(true);
		btn_del_rec.setDisable(true);
		
		table_cs.setEditable(true);
		
		list_num.addAll(qr._select_name("hmmr_sp_db", "HMMR_Material_Num", "SP_MU_Description_RUS"));
		list_name.addAll(qr._select_name("hmmr_sp_db", "SP_MU_Description_RUS", "Model"));
		
		initData();
		
		col_id_cs.setCellValueFactory(CellData -> CellData.getValue().getId());
		col_comp_id.setCellValueFactory(CellData -> CellData.getValue().getID_HMMR_COMPLEX());
		col_sub_id.setCellValueFactory(CellData -> CellData.getValue().getID_HMMR_SUB());
		
		//col_complex_id.setPrefWidth(300.0);
		col_subpart_id.setPrefWidth(300.0);
		
		//final ObservableList<TableColumn<Hmmr_CS_Model, ?>> columns_complex = table_cs.getColumns();
		/*col_complex_id.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Hmmr_CS_Model, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Hmmr_CS_Model, String> arg0) {
                        Hmmr_CS_Model data = arg0.getValue();
                        return new SimpleObjectProperty<String>(qr._select_fillcs(data.getID_HMMR_COMPLEXStr()));
                    }

                });
        
        columns_complex.add(col_complex_id); */
        
        final ObservableList<TableColumn<Hmmr_CS_Model, ?>> columns_subpart = table_cs.getColumns();
		col_subpart_id.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Hmmr_CS_Model, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Hmmr_CS_Model, String> arg0) {
                        Hmmr_CS_Model data = arg0.getValue();
                        return new SimpleObjectProperty<String>(qr._select_fillcs(data.getID_HMMR_SUBStr()));
                    }

                });
        
        columns_subpart.add(col_subpart_id);
        
        rbtn_num.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				   	rbtn_name.setSelected(false);
		        	rbtn_num.setSelected(true);
		        	TextFields.bindAutoCompletion(txt_search, list_num);
		    }
		});
        rbtn_name.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				   	rbtn_name.setSelected(true);
		        	rbtn_num.setSelected(false);
		        	TextFields.bindAutoCompletion(txt_search, list_name);
		    }
		});
      if(rbtn_num.isSelected())
    	  TextFields.bindAutoCompletion(txt_search, list_num);
      else
    	  TextFields.bindAutoCompletion(txt_search, list_name);
	
      txt_search.setOnKeyPressed(new EventHandler<KeyEvent>() {

		@Override
		public void handle(KeyEvent event) {
			if(event.getCode() == KeyCode.ENTER)
				if(rbtn_num.isSelected()) {
					table_cs.setItems(qr._select_data_cs_sort("ID_HMMR_COMPLEX", qr._select_id("hmmr_sp_db", "HMMR_Material_Num", scl.parser_str(txt_search.getText(), 0))));
					_flag_tbl_upd_cs = 1;
				}
				else {
					table_cs.setItems(qr._select_data_cs_sort(scl.parser_str(txt_search.getText(), 0)));
					_flag_tbl_upd_cs = 2;
				}
		}
	});
      Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
		         txt_search.requestFocus();
		    }
		});
      _table_update_cs.addListener(new ListChangeListener<Hmmr_CS_Model>() {

		@Override
		public void onChanged(Change<? extends Hmmr_CS_Model> arg0) {
			_refresh_table();
		}
    	  
      });
      table_cs.setOnMouseClicked(new EventHandler<Event>() {

		@Override
		public void handle(Event arg0) {
			btn_upd_rec.setDisable(false);
			btn_del_rec.setDisable(false);
			try {
				_id_cs = Integer.parseInt(table_cs.getSelectionModel().getSelectedItem().getIdStr());
			}
			catch (Exception e) {
				
			}
		}
	});
      table_cs.setOnMousePressed(new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			if(event.getClickCount() == 2)
			{
				try {
					_flag_window_cs = false;
					btn_upd_rec.setDisable(true);
					btn_del_rec.setDisable(true);
					_id_cs = Integer.parseInt(table_cs.getSelectionModel().getSelectedItem().getIdStr());				
					cs_add(stage);
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		}
	});
      btn_add_rec.setOnAction(new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent arg0) {
			try {
				_flag_window_cs = true;
				btn_upd_rec.setDisable(true);
				btn_del_rec.setDisable(true);
								
				cs_add(stage);
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	});
      btn_upd_rec.setOnAction(new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent arg0) {
			try {
				_flag_window_cs = false;
				btn_upd_rec.setDisable(true);
				btn_del_rec.setDisable(true);
								
				cs_add(stage);
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	});
      btn_del_rec.setOnAction(new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent arg0) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
		    alert.setTitle("M&U - Delete Record Window");
		    Hmmr_CS_Model _ccl = table_cs.getSelectionModel().getSelectedItem();
		    
		    alert.setHeaderText("Вы действительно хотите удалить запись № = " + _ccl.getIdStr() + "?");
		    //alert.setContentText("C:/MyFile.txt");
		 
		    // option != null.
		    Optional<ButtonType> option = alert.showAndWait();
		    if (option.get() == null) {
		       //label.setText("No selection!");
		    } else if (option.get() == ButtonType.OK) {
		  	   _ccl = table_cs.getSelectionModel().getSelectedItem();
		  	   try {
		  	   qr._update_deleterec_cs(_ccl.getIdStr());
		  	   qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + " - Удалил запись № = " + _ccl.getIdStr() + " в таблице Components Specification");
		  	   _refresh_table();
		  	   } catch (Exception e) {
				
			}
		    } else if (option.get() == ButtonType.CANCEL) {
		       //label.setText("Cancelled!");
		    } else {
		       //label.setText("-");
		    }
		}
	});
      btn_upd_tbl.setOnAction(new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent arg0) {
			_refresh_table();
			btn_upd_rec.setDisable(true);
			btn_del_rec.setDisable(true);
		}
	});
      clear_filter.setOnAction(new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent arg0) {
			table_cs.setItems(qr._select_data_cs());
			scl._update_table(table_cs);
			btn_upd_rec.setDisable(true);
			btn_del_rec.setDisable(true);
			txt_search.setText("");
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
		
	void initData()
	{
		table_cs.setItems(qr._select_data_cs());
	}
	
	//Вызываем окно добавления записи к CS
	protected void cs_add(Stage stage) throws IOException {
	   Parent root = FXMLLoader.load(getClass().getResource("Addrec_CS_Controller.fxml"));
			   
	   Scene scene = new Scene(root);
	   Stage stage_set = new Stage();
	   stage_set.initModality(Modality.WINDOW_MODAL);	
	   stage_set.initOwner(mu_main_controller.getPrimaryStage());
	   stage_set.setTitle("M&U - Add Record Window");
	   stage_set.setResizable(false);
	   stage_set.setScene(scene);
	   stage_set.show();
	}
	void _refresh_table()
	{
		if(_flag_tbl_upd_cs == 0)
			table_cs.setItems(qr._select_data_cs());
	
		if(_flag_tbl_upd_cs == 1)
			table_cs.setItems(qr._select_data_cs_sort("ID_HMMR_COMPLEX", qr._select_id("hmmr_sp_db", "HMMR_Material_Num", scl.parser_str(txt_search.getText(), 0))));
					
		if(_flag_tbl_upd_cs == 2)
			table_cs.setItems(qr._select_data_cs_sort(scl.parser_str(txt_search.getText(), 0)));
		
		_search = txt_search.getText();
		scl._update_table(table_cs);
	}

}
