package ru.haval.dir;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

import javax.imageio.ImageIO;

import com.jfoenix.controls.JFXButton;
import ru.haval.action.apwr_controller;
import ru.haval.application.Main;
import  ru.haval.application.conn_connector;
import  ru.haval.db._query;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import ru.haval.share_class.s_class;

public class type_pm_controller
{
	@FXML
	TableView<type_pm> table_tpm;
	
	@FXML
	TableColumn<type_pm, String> col_id_tpm, col_type_tpm, col_name_tpm, col_desc_tpm; //, col_icon_tpm
	
	@FXML
	JFXButton add_tpm, upd_tpm, del_tpm, cancel_tpm, upd_table_type;
	
	_query qr = new _query();
	private Stage stage = new Stage();
	public static Stage pStage;
	s_class scl = new s_class();
	
	public static ObservableList<TableColumn<type_pm, ?>> columns_type;
	public static ObservableList<type_pm> _table_update_typepm = FXCollections.observableArrayList();
	
	private String icon = "Иконка";
	TableColumn<type_pm, ImageView> icon_tpm = new TableColumn<type_pm, ImageView>(icon);
	
	Tooltip tip;	
	public static String _id_tpm, _type_tpm, _name_tpm, _desc_tpm, _icon_tpm;
	
	@FXML
	public void initialize() {
		scl._style(add_tpm);
		scl._style(upd_tpm);
		scl._style(del_tpm);
		scl._style(cancel_tpm);
		scl._style(upd_table_type);
				
       	initData();
       	
       	columns_type = table_tpm.getColumns();
       	
       	table_tpm.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);
        
       	col_id_tpm.setCellValueFactory(CellData -> CellData.getValue().IdProperty());
       	col_type_tpm.setCellValueFactory(CellData -> CellData.getValue().TypeProperty());
       	col_name_tpm.setCellValueFactory(CellData -> CellData.getValue().NameProperty());
       	col_desc_tpm.setCellValueFactory(CellData -> CellData.getValue().DescProperty());
       	final ObservableList<TableColumn<type_pm, ?>> columns_icon = table_tpm.getColumns();
		icon_tpm.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<type_pm, ImageView>, ObservableValue<ImageView>>() {

                    @SuppressWarnings("static-access")
					@Override
                    public SimpleObjectProperty<ImageView> call(TableColumn.CellDataFeatures<type_pm, ImageView> arg0) {
                        type_pm data = arg0.getValue();
                        ImageView iv = new ImageView();
                        iv.setFitWidth(30);
                        iv.setFitHeight(30);
                        
                      //запрещаем бегунку прокрутки возвращаться назад после нажатия кнопки
                        iv.setFocusTraversable(false);
                                                
                        BufferedImage bufferedImage;
						try {
							bufferedImage = ImageIO.read(new File(data.getIcon()));
							Image image = SwingFXUtils.toFXImage(bufferedImage, null);
							iv.setImage(image);
						} catch (IOException e) {
							scl._AlertDialog(e.getMessage()+" activity_type_controller", "Ошибка загрузки изображения");
						}
                                               
                        icon_tpm.setStyle( "-fx-alignment: CENTER;");
                                                
                       /* iv.setOnAction(new EventHandler<ActionEvent>() {
							
							@Override
							public void handle(ActionEvent event) {
							}
						});*/
                        
                        return new SimpleObjectProperty<ImageView>(iv);
                    }

                });
        columns_icon.add(icon_tpm);
       	
       	col_id_tpm.setSortable(false);
       	col_type_tpm.setSortable(false);
       	col_desc_tpm.setSortable(false);
       	
       	table_tpm.setEditable(true);
       	
       	add_tpm.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				try {
					//_flag = false;
					typepm_add(stage);
					//_flag = true;
					//t = new Thread(update_table());
	        		//t.setDaemon(true);
	        		//t.start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
      cancel_tpm.setOnAction(new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent event) {
			stage = (Stage) cancel_tpm.getScene().getWindow();
			stage.close();
		}
	}); 
      upd_tpm.setOnAction(new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent event) {
			upd_tpm.setDisable(true);
			del_tpm.setDisable(true);
			type_pm _ccl1 = table_tpm.getSelectionModel().getSelectedItem();
			try {
			func_upd(_ccl1.getId());
			} catch (Exception e) {
				
			}
		}
	});
      
      del_tpm.setOnAction(new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent event) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
		    alert.setTitle("M&U - Delete Record Window");
		    type_pm _ccl = table_tpm.getSelectionModel().getSelectedItem();
		    
		    alert.setHeaderText("Вы действительно хотите удалить запись № = " + _ccl.getId() + "?");
		    //alert.setContentText("C:/MyFile.txt");
		 
		    // option != null.
		    Optional<ButtonType> option = alert.showAndWait();
		    if (option.get() == null) {
		       //label.setText("No selection!");
		    } else if (option.get() == ButtonType.OK) {
		  	   _ccl = table_tpm.getSelectionModel().getSelectedItem();
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
    
    //Вызываем окно обновления по двойному клику на строке
    table_tpm.setOnMousePressed(new EventHandler<MouseEvent>() {
    	@Override
    	public void handle(MouseEvent event) {
    		if (event.getClickCount() == 2 ){
                   func_upd(table_tpm.getSelectionModel().getSelectedItem().getId());
               }
    	}
    });  
    
      //Клик мышью внутри таблицы останавливает поток и активирует кнопки Обновить и Удалить
        table_tpm.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				upd_tpm.setDisable(false);
				if(!conn_connector.USER_ROLE.equals("Technics") || !conn_connector.USER_ROLE.equals("Engeneer"))
					del_tpm.setDisable(false);
			}
		});
        
        upd_table_type.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				table_tpm.setItems(qr._select_data_typepm());
				columns_type.get(0).setVisible(false);
			    columns_type.get(0).setVisible(true);
			}
		});
        
        _table_update_typepm.addListener(new ListChangeListener<type_pm>() {
		    @Override
			public void onChanged(Change<? extends type_pm> c) {
				table_tpm.setItems(qr._select_data_typepm());
		    	table_tpm.getColumns().get(0).setVisible(false);
		        table_tpm.getColumns().get(0).setVisible(true);
			}
		});
    }
	
	//Вызываем окно добавления записи к справочнику PM_Cycle
	protected void typepm_add(Stage stage) throws IOException {
		setPrimaryStage(stage);
		Parent root = FXMLLoader.load(getClass().getResource("add_rec_typepm.fxml"));
		   
	    Scene scene = new Scene(root);
	    stage.setTitle("M&U - Add Record Window");
	    stage.setResizable(false);
	    //stage.initModality(Modality.APPLICATION_MODAL);
	    stage.setScene(scene);
	    stage.show();
	}
	//Вызываем окно обновления записи
	protected void typepm_upd(Stage stage) throws IOException {
		setPrimaryStage(stage);
		Parent root = FXMLLoader.load(getClass().getResource("upd_rec_typepm.fxml"));
		   
	    Scene scene = new Scene(root);
	    stage.setTitle("M&U - Update Record Window");
	    stage.setResizable(false);
	    //stage.initModality(Modality.APPLICATION_MODAL);
	    stage.setScene(scene);
	    stage.show();
	}

	private void initData()
	{
		table_tpm.setItems(qr._select_data_typepm());
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
		String _sql_rez = qr._select_for_update_typepm(str);
		_id_tpm = str;
		_type_tpm = parser_sql_str(_sql_rez, 0);
		_name_tpm = parser_sql_str(_sql_rez, 1);
		_desc_tpm = parser_sql_str(_sql_rez, 2);
		_icon_tpm = parser_sql_str(_sql_rez, 3);
		try {
			typepm_upd(stage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void func_del(String str)
	{
		qr._update_rec_typepm_del(str);
		qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + " - Удалил запись № = " + str + " в таблице Activity Types");
		_id_tpm = str;
	}
	
	private void setPrimaryStage(Stage pStage) {
        Main.pStage = pStage;
    }
	public static Stage getPrimaryStage() {
        return pStage;
    }
}