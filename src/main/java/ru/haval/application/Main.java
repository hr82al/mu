package ru.haval.application;
	

import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import ru.haval.share_class.s_class;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {
	private Stage primaryStage;
	private AnchorPane rootLayout;
	public static Stage pStage;
	
	s_class scl = new s_class();
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("M&U - Authorize Window");
		try {
			InitConn();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void InitConn() throws IOException
	{
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("authorize.fxml"));
			rootLayout = (AnchorPane) loader.load();
						
			Scene scene = new Scene(rootLayout);
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
			primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("Maintenance.png")));
			primaryStage.show();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	@SuppressWarnings("unused")
	private void setPrimaryStage(Stage pStage) {
        Main.pStage = pStage;
    }
	public static Stage getPrimaryStage() {
        return pStage;
    }
	//Запускаем на выполнение любой файл, не только Excel!!!!!
	@SuppressWarnings("static-access")
	public void _run_excel(File path) throws IOException
	{
		try {
		getHostServices().showDocument(path.toURI().toURL().toExternalForm());
		}
		catch (Exception e) {
			scl._AlertDialog("Указан неверный путь к файлу!", "Внимание!");
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
