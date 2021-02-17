package ru.haval.share_class;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import org.apache.commons.lang3.StringUtils;
import ru.haval.action.hmmr_wp_model;
import ru.haval.data.FxDatePickerConverter;
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

import static com.mysql.cj.protocol.a.MysqlTextValueDecoder.isDate;

public class s_class {
	static FxDatePickerConverter fx_dp = new FxDatePickerConverter();
	static _query qr = new _query();
	Tooltip tip;
	private static s_class scl = new s_class();

	public static void alert(SQLException e) {
		Platform.runLater( () -> {
			_AlertDialog(e.getMessage() + ", " + " ошибка в строке № " + Thread.currentThread().getStackTrace()[4].getLineNumber() + "!");
		});
	}

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



	private static boolean isExcludedDate(final long shift, List<Long> excluded) {
		for (long i : excluded) {
			if (shift % i == 0) {
				return true;
			}
		}
		return false;
	}

	public static void updatePmYearDates(final String PMGroup, final LocalDate BEGIN_DATE, final String OFT, final String PERIOD_ID) {
		Thread thread = new Thread(() -> {
			synchronized (s_class.class) {
				List<Period> periods = findPassPeriods(Integer.parseInt(PMGroup));
				List<Long> excludedPeriods = new LinkedList<>();

				if (periods != null) {
					ArrayList<String> excludedPMGroups = new ArrayList<>();
					for (Period period : periods) {
						excludedPMGroups.add(period.getPmGroup());
					}
					for (Period period : periods) {
						//удаляем все записи из PM Plan группы для которой поменяли дату
						qr.deleteFromPmYearByPmGroup(period.getPmGroup());
					}
					for (Period period : periods) {
						updatePmYears(period.getPmGroup(), period.getBeginDate(), OFT, period.getPeriodId(), excludedPMGroups, true);
						excludedPeriods.add((long) period.getPeriod());
					}
				} else {
					qr.deleteFromPmYearByPmGroup(PMGroup);
					updatePmYears(PMGroup, BEGIN_DATE, OFT, PERIOD_ID, null, false);
				}
			}
		});
		thread.setPriority(Thread.MIN_PRIORITY);
		thread.start();
	}

	public static void updatePmYears(final String PMGroup, final LocalDate BEGIN_DATE, final String OFT, final String PERIOD_ID, ArrayList<String> excludedPMGroups, boolean isNewShift) {
		final PMCycle pmCycle = qr.getPmCycleByPMCycle(PERIOD_ID);
		//Prepare constant vars
		String pmId = qr._select_pmid(PMGroup);

		//PM_ID redundant the column and it has no any sense;
		if (pmId.equals("UNKNOWN")) {
			pmId = qr._select_last_id("hmmr_pm");
		}
		final String PM_ID = pmId;
		//***************************************************
		final LocalDate END_DATE = LocalDate.parse(pmCycle.endDate);
		final int PM_GROUP = Integer.parseInt(PMGroup);
		long shift = 0;
		if (isNewShift) {
			shift = Period.getPeriodByName(PERIOD_ID);
		}
		if (shift == 0) {
			shift = pmCycle.periodic;
		}

		final long SHIFT = shift;
		LocalDate date = BEGIN_DATE;
		long currentShift = 0;

		while (date.isBefore(END_DATE)) {
			//check if some of excluded groups already has the date
			if (date.isAfter(LocalDate.now()) && (excludedPMGroups == null || !qr.hasDateInPMGroupS(date, excludedPMGroups))) {
				System.out.println(PM_ID + " : " + PM_GROUP + " : " + date +  " : " + OFT);
				qr._insert_pm_year(PM_ID,PM_GROUP, date, OFT);
			}
			//update counter!!!
			date = date.plusDays(SHIFT);
		}
		qr._update_week_year(PM_GROUP);
	}

	private static List<Period> findPassPeriods(int pmGroup) {
		// If in the group more then one equipment to break
		if (qr.countEquipmentInPmGroup(pmGroup) > 1) {
			return  null;
		}
		// If group has only pms format "HMMR-MU??-PM-????-{period}"
		String instrSubNum = hasOnlyGroupInstructionNumFormat(pmGroup);
		if (instrSubNum == null) {
			return null;
		} else {
			String instructionNumber = qr.getInstructionsNumByPmGroup(pmGroup).get(0);
			//find other groups the same format "HMMR-MU??-PM-????-{period}"
			ArrayList<Period> periods = qr.findGroupsLikeInstructionNumber("HMMR-MU__-PM-" + instrSubNum + "-%");
			//find target equipment ID
			int targetEqID = 0;
			for (Period period : periods) {
				if (Integer.parseInt(period.getPmGroup()) == pmGroup) {
					targetEqID = period.getEqID();
				}
			}
			//remove equipment that not conform target equipment
			for (Period period : periods) {
				//Check all periods has only one in pm group or not target equipment ID
				if (qr.countEquipmentInPmGroup(Integer.parseInt(period.getPmGroup())) > 1 || period.getEqID() != targetEqID) {
					periods.remove(period);
				}
			}
			if (periods.size() == 0) {
				return null;
			}

//			Set<String> startDates = new HashSet<>();
//			//Check the instructions in the group all starts at the same date
//			for (String[] group : groups) {
//				startDates.add(group[9]);
//			}
//			if (startDates.size() > 1) {
//				return null;
//			}
//			for (String[] group : groups) {
//				periods.add(new Period(group));
//			}
			Collections.sort(periods);
			return periods;
		}
	}



	//hasOnlyOne instruction format "HMMR-MU??-PM-????-{period}" in given group
	private static String hasOnlyGroupInstructionNumFormat(int pm_group) {
		List<String> instructionsInGroup = qr.getInstructionsNumByPmGroup(pm_group);
		if (instructionsInGroup.size() != 1) {
			return null;
		} else {
			Pattern pattern = Pattern.compile("^HMMR-MU\\d{2}-PM-\\d{3,6}-.{3,5}$");
			Matcher matcher = pattern.matcher(instructionsInGroup.get(0));
			if (matcher.find()) {
				return instructionsInGroup.get(0).split("-")[3];
			}
		}
		return null;
	}

	public static String transliterate(String russian) {
		final HashMap<String, String> ruEn = new HashMap<>();
		ruEn.put("А", "A");
		ruEn.put("а", "a");
		ruEn.put("Б", "B");
		ruEn.put("б", "b");
		ruEn.put("В", "V");
		ruEn.put("в", "v");
		ruEn.put("Г", "G");
		ruEn.put("г", "g");
		ruEn.put("Д", "D");
		ruEn.put("д", "d");
		ruEn.put("Е", "E");
		ruEn.put("е", "e");
		ruEn.put("Ё", "E");
		ruEn.put("ё", "e");
		ruEn.put("Ж", "Zh");
		ruEn.put("ж", "zh");
		ruEn.put("З", "Z");
		ruEn.put("з", "z");
		ruEn.put("И", "I");
		ruEn.put("и", "i");
		ruEn.put("Й", "Y");
		ruEn.put("й", "y");
		ruEn.put("К", "K");
		ruEn.put("к", "k");
		ruEn.put("Л", "L");
		ruEn.put("л", "l");
		ruEn.put("М", "M");
		ruEn.put("м", "m");
		ruEn.put("Н", "N");
		ruEn.put("н", "n");
		ruEn.put("О", "O");
		ruEn.put("о", "o");
		ruEn.put("П", "P");
		ruEn.put("п", "p");
		ruEn.put("Р", "R");
		ruEn.put("р", "r");
		ruEn.put("С", "S");
		ruEn.put("с", "s");
		ruEn.put("Т", "T");
		ruEn.put("т", "t");
		ruEn.put("У", "U");
		ruEn.put("у", "u");
		ruEn.put("Ф", "F");
		ruEn.put("ф", "f");
		ruEn.put("Х", "Kh");
		ruEn.put("х", "kh");
		ruEn.put("Ц", "Ts");
		ruEn.put("ц", "ts");
		ruEn.put("Ч", "Ch");
		ruEn.put("ч", "ch");
		ruEn.put("Ш", "Sh");
		ruEn.put("ш", "sh");
		ruEn.put("Щ", "Shch");
		ruEn.put("щ", "shch");
		ruEn.put("Ъ", "");
		ruEn.put("ъ", "");
		ruEn.put("Ь", "");
		ruEn.put("ь", "");
		ruEn.put("Э", "E");
		ruEn.put("э", "e");
		ruEn.put("Ю", "Yu");
		ruEn.put("ю", "yu");
		ruEn.put("Я", "Ya");
		ruEn.put("я", "ya");
		StringBuilder in = new StringBuilder(russian);
		StringBuilder out = new StringBuilder();
		for (int i = 0; i < in.length(); i++) {
			String ch = Character.toString(in.charAt(i));
			if (ruEn.containsKey(ch)) {
				out.append(ruEn.get(ch));
			}
			else {
				out.append(ch);
			}
		}
		return out.toString();
	}
}
