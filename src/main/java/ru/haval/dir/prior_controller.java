package ru.haval.dir;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import ru.haval.share_class.s_class;

public class prior_controller {
	
	@FXML
	TableView<hmmr_prior_model> table_prior = new TableView<>();
	
	@FXML
	TableColumn<hmmr_prior_model, String> col_id_tsk, col_name_prior, col_description;
	
	@FXML
	JFXButton add_prior, upd_prior, del_prior, cancel_prior;
	
	@FXML
	Label title_prior;
	
	_query qr = new _query();
	
	public Stage stage = new Stage();
	s_class scl = new s_class();
	private String icon = "Иконка";
		
	TableColumn<hmmr_prior_model, ImageView> icon_prior = new TableColumn<hmmr_prior_model, ImageView>(icon);
	
	public static ObservableList<hmmr_prior_model> _table_update_prior = FXCollections.observableArrayList();
	Tooltip tip;
	
	public static String _id_prior, _name_prior, _desc_prior, _icon_prior, _id_pr;
	@SuppressWarnings("unused")
	private String translate_icon;
	public static Stage pStage;
	
	@FXML
	public void initialize()
	{
		scl._style(add_prior);
		scl._style(upd_prior);
		scl._style(del_prior);
		scl._style(cancel_prior);
		
		upd_prior.setDisable(true);
		del_prior.setDisable(true);
		
		if(conn_connector.LANG_ID == 1)
			lang_fun("en", "EN");
		if(conn_connector.LANG_ID == 0)
			lang_fun("ru", "RU");
		if(conn_connector.LANG_ID == 2)
			lang_fun("zh", "CN");
		if(conn_connector.LANG_ID == -1)
			lang_fun("ru", "RU");
		
		if(conn_connector.LANG_ID == 0 || conn_connector.LANG_ID == -1)
			icon_prior.setText(icon);
		if(conn_connector.LANG_ID == 1)
			icon_prior.setText(icon);
		if(conn_connector.LANG_ID == 2)
			icon_prior.setText(icon);
		
		initData();
		
		col_id_tsk.setCellValueFactory(CellData -> CellData.getValue().getID_TSK());
		col_name_prior.setCellValueFactory(CellData -> CellData.getValue().getName_Prior());
		col_description.setCellValueFactory(CellData -> CellData.getValue().getDescription());
		
		final ObservableList<TableColumn<hmmr_prior_model, ?>> columns_icon = table_prior.getColumns();
		icon_prior.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<hmmr_prior_model, ImageView>, ObservableValue<ImageView>>() {

                    @SuppressWarnings("static-access")
					@Override
                    public SimpleObjectProperty<ImageView> call(TableColumn.CellDataFeatures<hmmr_prior_model, ImageView> arg0) {
                        hmmr_prior_model data = arg0.getValue();
                        ImageView iv = new ImageView();
                        iv.setFitWidth(30);
                        iv.setFitHeight(30);
                        
                      //запрещаем бегунку прокрутки возвращаться назад после нажатия кнопки
                        iv.setFocusTraversable(false);
                                                
                        BufferedImage bufferedImage;
						try {
							bufferedImage = ImageIO.read(new File(data.getIconStr()));
							Image image = SwingFXUtils.toFXImage(bufferedImage, null);
							iv.setImage(image);
						} catch (IOException e) {
							scl._AlertDialog(e.getMessage()+" prior_controller", "Ошибка загрузки изображения");
						}
                                               
                        icon_prior.setStyle( "-fx-alignment: CENTER;");
                                                
                       /* iv.setOnAction(new EventHandler<ActionEvent>() {
							
							@Override
							public void handle(ActionEvent event) {
							}
						});*/
                        
                        return new SimpleObjectProperty<ImageView>(iv);
                    }

                });
        columns_icon.add(icon_prior);
        
        _table_update_prior.addListener(new ListChangeListener<hmmr_prior_model>() {
		    @Override
			public void onChanged(Change<? extends hmmr_prior_model> c) {
				table_prior.setItems(qr._select_data_prior());
		    	table_prior.getColumns().get(0).setVisible(false);
		        table_prior.getColumns().get(0).setVisible(true);
			}
		});
        add_prior.setOnAction(new EventHandler<ActionEvent>() {
			
			@SuppressWarnings("static-access")
			@Override
			public void handle(ActionEvent event) {
				try {
					upd_prior.setDisable(true);
					del_prior.setDisable(true);
					
					prior_add(stage);
				} catch (IOException e) {
					scl._AlertDialog(e.getMessage(), "Ошибка при открытии окна добавлении записи в prior_controller");
				}
				
			}
		});
        table_prior.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				upd_prior.setDisable(false);
				//устанавливаем права для кнопки удалить
				if(!conn_connector.USER_ROLE.equals("Engeneer"))
					del_prior.setDisable(false);
			}
		});
        upd_prior.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				upd_prior.setDisable(true);
				del_prior.setDisable(true);
				hmmr_prior_model _ccl1 = table_prior.getSelectionModel().getSelectedItem();
				try {
				func_upd(_ccl1.getId());
				} catch (Exception e) {
					
				}
				
			}
		});
      //Вызываем окно обновления по двойному клику на строке
      table_prior.setOnMousePressed(new EventHandler<MouseEvent>() {
      	@Override
      	public void handle(MouseEvent event) {
      		if (event.getClickCount() == 2 ){
                     func_upd(table_prior.getSelectionModel().getSelectedItem().getId());
                 }
      	}
      });
      cancel_prior.setOnAction(new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent event) {
			stage = (Stage) cancel_prior.getScene().getWindow();
			stage.close();
		}
	});
      del_prior.setOnAction(new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent event) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
		    alert.setTitle("M&U - Delete Record Window");
		    hmmr_prior_model _ccl = table_prior.getSelectionModel().getSelectedItem();
		    
		    alert.setHeaderText("Вы действительно хотите удалить запись № = " + _ccl.getId() + "?");
		    Optional<ButtonType> option = alert.showAndWait();
		    if (option.get() == null) {
		       
		    } else if (option.get() == ButtonType.OK) {
		  	   _ccl = table_prior.getSelectionModel().getSelectedItem();
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
	}

	private void initData()
	{
		table_prior.setItems(qr._select_data_prior());
	}
	
	private void func_upd(String str)
	{
		String _sql_rez = qr._select_for_update_prior(str);
		_id_pr = str;
		_id_prior = scl.parser_sql_str(_sql_rez, 0);
		_name_prior = scl.parser_sql_str(_sql_rez, 1);
		_desc_prior = scl.parser_sql_str(_sql_rez, 2);
		_icon_prior = scl.parser_sql_str(_sql_rez, 3); 
		
		try {
			prior_upd(stage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void func_del(String str)
	{
		qr._delete_rec_prior(str);
		qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + " - Удалил запись № = " + str + " в таблице Справочнике приоритетов");
		_table_update_prior.addAll(qr._select_data_prior());
	}
	
	//Вызываем окно добавления записи к Спраавочнику приоритетов
	protected void prior_add(Stage stage) throws IOException {
		setPrimaryStage(stage);
		Parent root = FXMLLoader.load(getClass().getResource("add_rec_prior.fxml"));
		   
	    Scene scene = new Scene(root);
	    stage.setTitle("M&U - Add Record Window");
	    stage.setResizable(false);
	    //stage.initModality(Modality.APPLICATION_MODAL);
	    stage.setScene(scene);
	    stage.show();
	}
	//Вызываем окно обновления записи
	protected void prior_upd(Stage stage) throws IOException {
		setPrimaryStage(stage);
		Parent root = FXMLLoader.load(getClass().getResource("upd_rec_prior.fxml"));
		   
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
		
		title_prior.setText(lngBndl.getString("title_prior"));
		col_id_tsk.setText(lngBndl.getString("col_id_tsk"));
		col_name_prior.setText(lngBndl.getString("col_name_prior"));
		col_description.setText(lngBndl.getString("col_description"));
		icon = lngBndl.getString("translate_icon");
				
		add_prior.setText(lngBndl.getString("add_tsk"));
		
		upd_prior.setText(lngBndl.getString("upd_ap"));
		
		del_prior.setText(lngBndl.getString("del_inst"));
		cancel_prior.setText(lngBndl.getString("cancel_tsk"));
		
	}
	
	private void setPrimaryStage(Stage pStage) {
        Main.pStage = pStage;
    }
	public static Stage getPrimaryStage() {
        return pStage;
    }
}
