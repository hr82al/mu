package ru.haval.share_class;


import java.util.Optional;
import java.util.Random;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;

import  ru.haval.db._query;
import ru.haval.dir.CycleController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;

public class s_class {

	_query qr = new _query();
	Tooltip tip;
	
	public String parser_sql_str(String str, int count)
	{
		String[] p_str = null;
		for(int i = 0; i < str.length(); i++)
		{
			p_str = str.split(","); 
		}
		return p_str[count];
	}
	
	public String parser_str(String str, int count)
	{
		String[] p_str = null;
		for(int i = 0; i < str.length(); i++)
			p_str = str.split(" - ");
		return p_str[count];
	}
	public static String parser_str_str(String str, int count)
	{
		String[] p_str = null;
		for(int i = 0; i < str.length(); i++)
			p_str = str.split("\\.");
		return p_str[count];
	}
	public String parser_str_str_str(String str, int count)
	{
		String[] p_str = null;
		for(int i = 0; i < str.length(); i++)
			p_str = str.split(";");
		return p_str[count];
	}
	
	public String parser_double_dot(String str, int count)
	{
		String[] p_str = null;
		for(int i = 0; i < str.length(); i++)
			p_str = str.split(":");
		return p_str[count];
	}
	//Делаем стиль для кнопки
	public void _style(JFXButton btn)
	{
		btn.setStyle("-fx-background-color: #e7e7e7; -fx-border-width: 2; -fx-border-color: #1680c2;");
		
		btn.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				btn.setStyle("-fx-background-color: #e3faff; -fx-border-width: 2; -fx-border-color: #1680c2;");
			}
		});
		
		btn.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				btn.setStyle("-fx-background-color: #e7e7e7; -fx-border-width: 2; -fx-border-color: #1680c2;");
			}
		});
	}
	//Делаем стиль для кнопки
		public void _style1(JFXButton btn)
		{
			btn.setStyle("-fx-background-color: #e7e7e7; -fx-border-width: 1; -fx-border-color: #1680c2;");
			
			btn.setOnMouseEntered(new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					btn.setStyle("-fx-background-color: #e3faff; -fx-border-width: 1; -fx-border-color: #1680c2;");
				}
			});
			
			btn.setOnMouseExited(new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					btn.setStyle("-fx-background-color: #e7e7e7; -fx-border-width: 1; -fx-border-color: #1680c2;");
				}
			});
		}
	
	//Перевод моточасов прогрессивных ППР в дни
	public JFXRadioButton _moto_to_days(JFXRadioButton rbtn)
	{
		rbtn.setOnAction(new EventHandler<ActionEvent>() {
				
			@Override
			public void handle(ActionEvent event) {
				int qm = Integer.parseInt(rbtn.getText());
				int _const = 180;
				ObservableList<Integer> list = qr._select_data_moto();
				double rez_period;
				
				for (int i = 0; i < list.size(); i++)
				{
					double act1 = (_const / 3600.d) * qm;
					double act2 = act1 / list.get(i).doubleValue();
					rez_period = 52 / act2;
					qr._update_rec_cycle(String.valueOf(Math.round(rez_period * 7)), list.get(i).toString());
				}
				qr._update_rbtn(rbtn.getId());
				
				rbtn.setSelected(true);
				
				for (int j = 0; j < CycleController.rbuttons.size(); j++)
				{
					if(CycleController.rbuttons.get(j) != rbtn)
					{
						CycleController.rbuttons.get(j).setSelected(false);
					}
				}
				
			}
		});
		return rbtn;
	}
/**
 * Вызов диалогового окна, в титле которого надпись - Внимание! Ошибка в SQL запросе.	
 * @param str - Сообщение которое выведет диалоговое окно
 */
	public static void _AlertDialog(String str)
	{
		Alert alert = new Alert(AlertType.CONFIRMATION);
	    alert.setTitle("Внимание! Ошибка в SQL запросе.");
	    	    
	    alert.setHeaderText(str);
	    //alert.setContentText("C:/MyFile.txt");
	 
	    // option != null.
	    Optional<ButtonType> option = alert.showAndWait();
	    if (option.get() == null) {
	       //label.setText("No selection!");
	    } else if (option.get() == ButtonType.OK) {
	  	   
	    }// else if (option.get() == ButtonType.CANCEL) {
	       //label.setText("Cancelled!");
	    //} else {
	       //label.setText("-");
	    //}
	}
	/**
	 * Вызов диалогового окна без надписи в титле
	 * @param str - Сообщение которое выведет диалоговое окно
	 * @param str1 - Сообщение которое будет в титле
	 */
	public static void _AlertDialog(String str, String str1)
	{
		Alert alert = new Alert(AlertType.CONFIRMATION);
	    alert.setTitle(str1);
	    	    
	    alert.setHeaderText(str);
	    //alert.setContentText("C:/MyFile.txt");
	 
	    // option != null.
	    Optional<ButtonType> option = alert.showAndWait();
	    if (option.get() == null) {
	       //label.setText("No selection!");
	    } else if (option.get() == ButtonType.OK) {
	  	   
	    } else if (option.get() == ButtonType.CANCEL) {
	       //label.setText("Cancelled!");
	    //} else {
	       //label.setText("-");
	    }
	}
	/**
	 * Вызываем подсказку на определенном элементе
	 * @param str - Строка подсказки
	 * @param node - id элемента на котором вызываем подсказку
	 */
	public void _ToolTip(String str, Node node)
	{
		tip = new Tooltip(str);
		Point2D p = node.localToScreen(node.getLayoutBounds().getMaxX(), node.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
        tip.show(node, p.getX(), p.getY());
	}
	public void _ToolTipHide()
	{
		tip.hide();
	}
	
	//генерируем случайный пароль, только из цифр и только в размере 4, если надо буквенный снимаем комментарии
	public static String generate() { //int from, int to
	   String pass  = "";
	   Random r     = new Random();
	   int cntchars = 4;//from + r.nextInt(to - from + 1);
       for (int i = 0; i < cntchars; ++i) {
          char next = 0;
          int range = 10;
          switch(r.nextInt(1)) { //3
            case 0: {next = '0'; range = 10;} break;
            //case 1: {next = 'a'; range = 26;} break;
            //case 2: {next = 'A'; range = 26;} break;
          }
          pass += (char)((r.nextInt(range)) + next);
       }
	   return pass;
	}
	//Функция необходимая при обновлении таблицы TableView
	public <S> void _update_table(TableView<S> tv)
	{
		tv.getColumns().get(0).setVisible(false);
        tv.getColumns().get(0).setVisible(true);
	}

}
