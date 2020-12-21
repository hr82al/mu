/*
@author: Grytsay Roman
*/
package ru.haval.application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;

import ru.haval.config.Config;
import ru.haval.db._connect;
import ru.haval.db._query;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ru.haval.share_class.s_class;

public class conn_connector
{
	@FXML
	TextField login_id;
	@FXML
	PasswordField passwd_id;
	@FXML
	Label l_info, host_err;
	@FXML
	JFXButton btn_conn, set_btn;
	
	@FXML
	JFXComboBox<String> s_lan;
	
	@FXML
	JFXCheckBox for_pc;

	
	_query qr = new _query();
	s_class scl = new s_class();
	private String query_rez;
	public static String USER_ID, USER_LOGIN, USER_ROLE;
	public static int LANG_ID;
	public static Stage pStage;
	ObservableList<String> lan_list = FXCollections.observableArrayList();
	
	//Начальные установки
	@FXML
	public void initialize()
	{
		host_err.setVisible(false);
		
		scl._style(btn_conn);
		
		lan_list.add("Русский");
		lan_list.add("English");
		lan_list.add("中文");
		s_lan.setStyle("-fx-font-family: \"System\"; -fx-font-size: 13;");//-fx-border-width: 1; -fx-border-color: gray;
		s_lan.setItems(lan_list);
		s_lan.setValue("Русский");
		LANG_ID = s_lan.getSelectionModel().getSelectedIndex();
		s_lan.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				LANG_ID = s_lan.getSelectionModel().getSelectedIndex();
				
				if(LANG_ID == 1)
				{
					ResourceBundle lngBndl = ResourceBundle
				            .getBundle("bundles.LangBundle", new Locale("en", "EN"));
			
				    l_info.setText(lngBndl.getString("l_info"));
				    login_id.setPromptText(lngBndl.getString("login_id"));
				    passwd_id.setPromptText(lngBndl.getString("passwd_id"));
				    btn_conn.setText(lngBndl.getString("btn_conn"));
				    for_pc.setText(lngBndl.getString("for_pc"));
				}
				
				if(LANG_ID == 0)
				{
					ResourceBundle lngBndl = ResourceBundle
				            .getBundle("bundles.LangBundle", new Locale("ru", "RU"));
			
				    l_info.setText(lngBndl.getString("l_info"));
				    login_id.setPromptText(lngBndl.getString("login_id"));
				    passwd_id.setPromptText(lngBndl.getString("passwd_id"));
				    btn_conn.setText(lngBndl.getString("btn_conn"));
				    for_pc.setText(lngBndl.getString("for_pc"));
				}
				if(LANG_ID == 2)
				{
					ResourceBundle lngBndl = ResourceBundle
				            .getBundle("bundles.LangBundle", new Locale("zh", "CN"));
			
				    l_info.setText(lngBndl.getString("l_info"));
				    login_id.setPromptText(lngBndl.getString("login_id"));
				    passwd_id.setPromptText(lngBndl.getString("passwd_id"));
				    btn_conn.setText(lngBndl.getString("btn_conn"));
				    for_pc.setText(lngBndl.getString("for_pc"));
				}
				if(LANG_ID == -1)
				{
					ResourceBundle lngBndl = ResourceBundle
				            .getBundle("bundles.LangBundle", new Locale("ru", "RU"));
			
				    l_info.setText(lngBndl.getString("l_info"));
				    login_id.setPromptText(lngBndl.getString("login_id"));
				    passwd_id.setPromptText(lngBndl.getString("passwd_id"));
				    btn_conn.setText(lngBndl.getString("btn_conn"));
				    for_pc.setText(lngBndl.getString("for_pc"));
				}
			}
		});
		//Читаем логин и пароль из файла, кроме техников т.к. у них один комп))

		login_id.setText(Config.getInstance().getUser());
		passwd_id.setText(Config.getInstance().getPassword());
		/*Scanner s;
		String line;
		int cnt = 0;
		try {
			s = new Scanner(new File("C:\\Report\\receipt.txt"));
			while (s.hasNext()) {
				line = s.nextLine();
				if (cnt == 0)
					login_id.setText(line);
				else
					passwd_id.setText(line);
				cnt = cnt + 1;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}*/
		 
				
		//Если расскоментировать, то установится фокус в login_id
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
		         s_lan.requestFocus();
		    }
		});
		Platform.runLater(() -> {
			Image imageOk = new Image(getClass().getResourceAsStream("/ru/haval/action/settings.png"));
			set_btn.setGraphic(new ImageView(imageOk)); });
	}
	
	
	//Обрабатываем нажатие на кнопку Войти
	public void _click_btn_conn()
	{
		fun_auth();
	}
	//Обрабатываем нажатие Enter в поле пароля
	public void _click_on_passwd()
	{
		fun_auth();
	}
		
	//функция авторизации и сохранения начальных данных, для использования далее в программе
	private void fun_auth()
	{

		String login, passwd;
		_connect.init();

		login = login_id.getText().toString();
		passwd = passwd_id.getText().toString();
		
		query_rez = qr._check_login_passwd(login);
		if(!passwd.equals(_parser_sql(query_rez, 1)) || login.isEmpty())
		{
			host_err.setVisible(true);
		}
		else
		{
			// В случае успешной авторизации сохранить параметры
			Config.getInstance().setIsValid(true);

			host_err.setVisible(false);
			
			USER_ID = _parser_sql(query_rez, 0);
			USER_ROLE = _parser_sql(query_rez, 2);
			USER_LOGIN = login;
			
			qr._insert_history(USER_ID, "#V018# Авторизация пользователя - " + scl.parser_str(qr._select_user(conn_connector.USER_ID), 1));
			Stage stage = new Stage();
	        try {
				FXMLDocumentController(stage);
			} catch (IOException e) {
				e.printStackTrace();
			}
	        //Пишим логин и пароль в файл, если не поставлена галка Чужой компьютер
	        if(!for_pc.isSelected()) 
	        {
		        try (BufferedWriter bf = new BufferedWriter(new FileWriter("C:\\Report\\receipt.txt"))) 
		        { 
			         bf.write(login); 
			         bf.newLine(); 
			         bf.write(passwd); 
		        }
	        
		        catch (IOException ex) 
		        { 
		        	Logger.getLogger(conn_connector.class.getName()).log(Level.FATAL, null, ex); 
		        }
		        //Сохранение логина и пароля в конфигурационном файле ( выполняется тольк в случае успешного подключения)
				Config.getInstance().setPassword(passwd);
		        Config.getInstance().setUser(login);
	        }
	        else {
	        	Config.getInstance().setPassword("");
			}
		}
	}
	
	/**
	 * Парсим строку результат выполнения sql-запроса
	 * @param sql_rez - собственно сам результат sql - запроса
	 * @param count - индекс указывающий что именно возвращаем из пришедшей строки (0 - id пользователя, 1 - пароль, 2 - роль)
	 * @return - возвращаем результат в вызывающуу функцию
	 */
	private String _parser_sql(String sql_rez, int count)
	{
		ArrayList<String> _parse_rez = new ArrayList<String>();
		String[] _pars_str = sql_rez.split(",");
		for(int i = 0;i < _pars_str.length;i ++)
		{
			_parse_rez.add(_pars_str[i]);
		}
		
		return _parse_rez.get(count).toString();
	}
	
	protected void FXMLDocumentController(Stage stage) throws IOException {
		//Закрываем окно входа в систему
	    stage = (Stage) btn_conn.getScene().getWindow();
	    stage.close();
	    
	    Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
	    
	    //Parent root = FXMLLoader.load(getClass().getResource("mu_main_window.fxml"));
	    Parent root = FXMLLoader.load(getClass().getResource("ap_wr.fxml"));
	    setPrimaryStage(stage);
        Scene scene = new Scene(root);
        //stage.setTitle("M&U - Main Window"+" "+scl.parser_str(qr._select_user(conn_connector.USER_ID), 1)+"/"+scl.parser_str(qr._select_user(conn_connector.USER_ID), 2)+" "+scl.parser_str(qr._select_user(conn_connector.USER_ID), 3) +"  MU."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 4)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 5)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 6)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 0));
        stage.setTitle("M&U - Work Order&Work Recording Window"+" "+scl.parser_str(qr._select_user(conn_connector.USER_ID), 1)+"/"+scl.parser_str(qr._select_user(conn_connector.USER_ID), 2)+" "+scl.parser_str(qr._select_user(conn_connector.USER_ID), 3) +"  MU."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 4)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 5)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 6)+"."+scl.parser_str(qr._select_user(conn_connector.USER_ID), 0));
        stage.setResizable(true);
        stage.setWidth(primaryScreenBounds.getWidth());//  - 575
        stage.setHeight(primaryScreenBounds.getHeight());// - 215
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
            }
        });
        stage.toBack();
        stage.show();
    }
	
	private void setPrimaryStage(Stage pStage) {
        conn_connector.pStage = pStage;
    }
	public static Stage getPrimaryStage() {
        return pStage;
    }

	public void changeSettings(ActionEvent actionEvent) {
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/ru/haval/action/change_settings.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Stage stage_set = new Stage();
		stage_set.initModality(Modality.WINDOW_MODAL);
		stage_set.initOwner(conn_connector.getPrimaryStage());
		Scene scene = new Scene(root);
		stage_set.setTitle("M&U - Настройка параметров");
		stage_set.setResizable(false);

		//stage.setWidth(primaryScreenBounds.getWidth() - 315);// - 77
		//stage.setHeight(primaryScreenBounds.getHeight() - 15);// - 77
		stage_set.setScene(scene);

		stage_set.show();
	}
}