package ru.haval.share_class;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;

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

public class s_class {
	static FxDatePickerConverter fx_dp = new FxDatePickerConverter();
	static _query qr = new _query();
	Tooltip tip;
	private static s_class scl = new s_class();

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

	public static void updatePmPlanDates(String before_pars, String b_date, String  pm_id, int pm_group){

		String Otv_for_task = null;

		String pereodic = scl.parser_sql_str(before_pars, 0);
		String e_date = scl.parser_sql_str(before_pars, 2);
		@SuppressWarnings("unused")
		String shop = scl.parser_sql_str(before_pars, 3);
		Otv_for_task = scl.parser_sql_str(before_pars, 4);

		int _count = Integer.parseInt(pereodic);
		int _cnt = _count;

		int day_bdate = fx_dp.fromString(b_date).getDayOfMonth();
		int month_bdate = fx_dp.fromString(b_date).getMonthValue();
		int year_bdate = fx_dp.fromString(b_date).getYear();

		int day_edate = fx_dp.fromString(e_date).getDayOfMonth();
		int month_edate = fx_dp.fromString(e_date).getMonthValue();
		int year_edate = fx_dp.fromString(e_date).getYear();

		//Находим количество дней в течении которых должно выполняться ППР, а затем находим сколько надо создать записей в таблице hmmr_pm_year
		int gen_day = Math.abs(day_edate - day_bdate);
		int gen_month = Math.abs(month_edate - month_bdate) * 30;
		int gen_year = Math.abs(year_edate - year_bdate) * 365;

		int _general = Math.round((gen_day + gen_month + gen_year) / _count);
		// If group has only pms format "HMMR-MU??-PM-????-{period}"
		List<Period> periods = findPassPeriods(pm_group);
		List<Integer> excludedPeriods = new LinkedList<>();
		if (periods != null) {
			for (int j = Period.getPeriods() - 1; j >=0;j--) {
				for (Period period : periods) {
					if (period.isPeriodNumber(j)) {
						_count = period.getPeriod();
						_general = Math.round((gen_day + gen_month + gen_year) / _count);
						pm_id = qr._select_pmid(period.getPmGroup());
						pm_group = Integer.parseInt(period.getPmGroup());
						for (int i = 0; i < _general; i++) {
							if (isExcluded(_count, excludedPeriods)) {
								continue;
							}
							LocalDate days = LocalDate.of(year_bdate, month_bdate, day_bdate).plusDays(_count);//Расчитываем даты когда заявка должна быть выполнена

							qr._insert_pm_year(pm_id, pm_group, days, Otv_for_task);
							_count = period.getPeriod() + _count;
						}
						qr._update_week_year(pm_group);
						excludedPeriods.add(0, period.getPeriod());
					}
				}
			}

		} else {
			for (int i = 0; i < _general; i++) {
				LocalDate days = LocalDate.of(year_bdate, month_bdate, day_bdate).plusDays(_count);//Расчитываем даты когда заявка должна быть выполнена
				qr._insert_pm_year(pm_id, pm_group, days, Otv_for_task);
				_count = _cnt + _count;
			}
			qr._update_week_year(pm_group);
		}

	}

	private static boolean isExcludedDate(final long shift, final long[] excluded) {
		for (long i : excluded) {
			if (shift % i == 0) {
				return true;
			}
		}
		return false;
	}

	public static void updatePmYearDates(final String PMGroup, final LocalDate BEGIN_DATE, final String OFT, final String PERIOD_ID) {
		Thread thread = new Thread(() -> {
			updatePmYears(PMGroup, BEGIN_DATE, OFT, PERIOD_ID, new long[] {});
		});
		thread.setPriority(Thread.MIN_PRIORITY);
		thread.start();
	}

	public static void updatePmYears(final String PMGroup, final LocalDate BEGIN_DATE, final String OFT, final String PERIOD_ID, final long[] EXCLUDED_DAYS) {
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
		final long SHIFT = pmCycle.periodic;

		LocalDate date = BEGIN_DATE;
		long currentShift = 0;

		while (date.plusDays(currentShift).isBefore(END_DATE)) {
			//pass excluded days
			if (isExcludedDate(currentShift, EXCLUDED_DAYS)) {
				continue;
			}
//			System.out.println(PM_ID + " : " + PM_GROUP + " : " + date.plusDays(currentShift) +  " : " + OFT);
			qr._insert_pm_year(PM_ID,PM_GROUP, date.plusDays(currentShift), OFT);
			//update counter!!!
			currentShift += SHIFT;
		}
		qr._update_week_year(PM_GROUP);
	}

	public static void updatePmPlanDates2(String before_pars, String b_date, String  pm_id, int pm_group){

		String Otv_for_task = null;

		String pereodic = scl.parser_sql_str(before_pars, 0);
		String e_date = scl.parser_sql_str(before_pars, 2);
		@SuppressWarnings("unused")
		String shop = scl.parser_sql_str(before_pars, 3);
		Otv_for_task = scl.parser_sql_str(before_pars, 4);

		int _count = Integer.parseInt(pereodic);
		int _cnt = _count;

		int day_bdate = fx_dp.fromString(b_date).getDayOfMonth();
		int month_bdate = fx_dp.fromString(b_date).getMonthValue();
		int year_bdate = fx_dp.fromString(b_date).getYear();

		int day_edate = fx_dp.fromString(e_date).getDayOfMonth();
		int month_edate = fx_dp.fromString(e_date).getMonthValue();
		int year_edate = fx_dp.fromString(e_date).getYear();

		//Находим количество дней в течении которых должно выполняться ППР, а затем находим сколько надо создать записей в таблице hmmr_pm_year
		int gen_day = Math.abs(day_edate - day_bdate);
		int gen_month = Math.abs(month_edate - month_bdate) * 30;
		int gen_year = Math.abs(year_edate - year_bdate) * 365;

		int _general = Math.round((gen_day + gen_month + gen_year) / _count);
		// If group has only pms format "HMMR-MU??-PM-????-{period}"
		List<Period> periods = findPassPeriods(pm_group);
		List<Integer> excludedPeriods = new LinkedList<>();
		if (periods != null) {
			for (int j = Period.getPeriods() - 1; j >=0;j--) {
				for (Period period : periods) {
					if (period.isPeriodNumber(j)) {
						_count = period.getPeriod();
						_general = Math.round((gen_day + gen_month + gen_year) / _count);
						pm_id = qr._select_pmid(period.getPmGroup());
						pm_group = Integer.parseInt(period.getPmGroup());
						for (int i = 0; i < _general; i++) {
							if (isExcluded(_count, excludedPeriods)) {
								continue;
							}
							LocalDate days = LocalDate.of(year_bdate, month_bdate, day_bdate).plusDays(_count);//Расчитываем даты когда заявка должна быть выполнена

							qr._insert_pm_year(pm_id, pm_group, days, Otv_for_task);
							_count = period.getPeriod() + _count;
						}
						qr._update_week_year(pm_group);
						excludedPeriods.add(0, period.getPeriod());
					}
				}
			}

		} else {
			for (int i = 0; i < _general; i++) {
				LocalDate days = LocalDate.of(year_bdate, month_bdate, day_bdate).plusDays(_count);//Расчитываем даты когда заявка должна быть выполнена
				//qr._insert_pm_year(pm_id, pm_group, days, Otv_for_task);
				System.out.println(pm_id + " : " + pm_group + " : " + days +  " : " + Otv_for_task);
				_count = _cnt + _count;
			}
			qr._update_week_year(pm_group);
		}

	}

	private static boolean isExcluded(int count, List<Integer> excludedPeriods) {
		for (int exclude : excludedPeriods) {
			if (count % exclude == 0) {
				return true;
			}
		}
		return false;
	}

	private static List<Period> findPassPeriods(int pm_group) {
		// If group has only pms format "HMMR-MU??-PM-????-{period}"
		int instrSubNum = hasOnlyGroupInstructionNumFormat(pm_group);
		if (instrSubNum == 0) {
			return null;
		} else {
			String instructionNumber = qr.getInstructionsNumByPmGroup(pm_group).get(1);
			//find other groups the same format "HMMR-MU??-PM-????-{period}"
			ArrayList<String[]> groups = qr.findGroupsLikeInstructionNumber("HMMR-MU__-PM-" + instrSubNum + "-%");
			List<Period> periods = new ArrayList<>();
			Set<String> startDates = new HashSet<>();
			for (String[] group : groups) {
				startDates.add(group[9]);
			}
			if (startDates.size() > 1) {
				return null;
			}
			for (String[] group : groups) {
				periods.add(new Period(group));
			}
			return periods;
		}
	}



	//hasOnlyOne instruction format "HMMR-MU??-PM-????-{period}" in given group
	private static int hasOnlyGroupInstructionNumFormat(int pm_group) {
		List<String> instructionsInGroup = qr.getInstructionsNumByPmGroup(pm_group);
		if (instructionsInGroup.size() != 1) {
			return 0;
		} else {
			Pattern pattern = Pattern.compile("^HMMR-MU\\d{2}-PM-\\d{4}-.{3,4}");
			Matcher matcher = pattern.matcher(instructionsInGroup.get(1));
			if (matcher.find()) {
				return Integer.parseInt(instructionsInGroup.get(1).substring(13,17));
			}
		}
		return 0;
	}

}
