package ru.haval.application;
	

import java.awt.*;
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
	public static String jarLocation;

	static {
		String jarLocationUrl = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		jarLocation = new File(jarLocationUrl.toString()).getParent();
	}
	
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
//		System.out.println(path.toString());
//		if (!path.exists() && path.toString().endsWith(".pdf")) {
//			path = fix_pdf_path(path);
//		}
		try {
			if (path.toString().endsWith(".pdf"))
				Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + path);
			else {
				getHostServices().showDocument(path.toURI().toASCIIString());
			}

//		getHostServices().showDocument(path.toURI().toURL().toExternalForm());
		}
		catch (Exception e) {
			scl._AlertDialog("Указан неверный путь к файлу!", "Внимание!");
		}
	}

	private File fix_pdf_path(File path) {
		return path;
	}


	public static void main(String[] args) {
		launch(args);
	}
}
