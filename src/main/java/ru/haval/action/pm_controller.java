package ru.haval.action;
//Pm instructions
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import  ru.haval.application.conn_connector;
import ru.haval.application.mu_main_controller;
import  ru.haval.data.FxDatePickerConverter;
import  ru.haval.db._query;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ru.haval.share_class.TooltippedTableCell;
import ru.haval.share_class.s_class;

public class pm_controller {

	@FXML
	TextField searchPMDB;
	
	@FXML
	TableView<hmmr_pm_model> table_pm;
	
	@FXML
	TableColumn<hmmr_pm_model, String> col_id_pm, col_ninst_pm, col_group_pm, col_ool_pm, col_otv_pm, col_isp_pm, col_eq_id, col_period; //col_group_eq, col_lm_pm, col_os_pm, col_equip_pm, col_pmn_pm, col_pmc_pm, col_pmtype_pm,
	
	@FXML
	JFXButton add_ap_pm, add_pm, upd_pm, del_pm, close_pm, upd_table_pm, dup_rec_pm;
	
	public static String _id_pm, _ninst_pm_upd, _eq_id_upd,_group_pm_upd, _ool_pm_upd, _otv, _pm_exec, _shop_pm, _groupeq_pm, _lm_pm, _os_pm, _equip_pm;// _group_eq_upd, _lm_pm_upd, _os_pm_upd, _equip_pm_upd, _pmn_pm_upd, _pmc_pm_upd, _pmtype_pm_upd, 
	
	@FXML
	ScrollPane sp_pm;
	
	@FXML
	Pane pane_pm;
	
	@FXML
	VBox vbox_pm;
	
	@FXML
	HBox hb1, hb2, hb3;
	
	_query qr = new _query();
	private Stage stage = new Stage();
	s_class scl = new s_class();
	FxDatePickerConverter fx_dp = new FxDatePickerConverter();
	public static ObservableList<TableColumn<hmmr_pm_model, ?>> columns_pm;
	private String name_col = "Оборудование";
	public static ObservableList<hmmr_pm_model> _table_update_pm = FXCollections.observableArrayList();
	//TableColumn<hmmr_pm_model, String> col_eq_id = new TableColumn<hmmr_pm_model, String>(name_col);
	
	public static int _Id_Dup_Pm = 0;
	public static String _num_inst_last = "NULL"; //Номер последней выбранной инструкции

	private ObservableList<hmmr_pm_model> pmDbRows;
	private String pmRows = "";
	private HashSet<String> pmOTVs = new HashSet<>();
	private HashSet<String> pmPMCs = new HashSet<>();
	private HashSet<String> pmPMGroups = new HashSet<>();

	boolean isPmsGet = false;

	@FXML
	public void initialize()
	{
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		Double screen_width = primaryScreenBounds.getWidth();
		Double screen_hight = primaryScreenBounds.getHeight(); 
		
		//sp_pm.setPrefWidth(screen_width - 230);
		//sp_pm.setPrefHeight(screen_hight - 50);
		
		//pane_pm.setPrefHeight(screen_hight - 50);
		//vbox_pm.setPrefHeight(screen_hight - 50);
		
	//	hbox_pm.setPrefHeight(screen_hight - 199);
		sp_pm.setPrefWidth(screen_width - 900);
		sp_pm.setPrefHeight(screen_hight - 50);
		pane_pm.setPrefWidth(screen_width - 900);
		pane_pm.setPrefHeight(screen_hight - 50);
		
		vbox_pm.setPrefWidth(screen_width - 900);
		vbox_pm.setPrefHeight(screen_hight - 50);
		hb1.setPrefWidth(screen_width - 900);
		hb1.setPrefHeight(70.0);
		hb2.setPrefWidth(screen_width - 900);
		hb2.setPrefHeight(screen_hight - 220);
		hb3.setPrefWidth(screen_width - 900);
		hb3.setPrefHeight(70.0);
		table_pm.setPrefWidth(screen_width - 1000);
		table_pm.setPrefHeight(screen_hight - 200);
		
		if(screen_width == 1920.0) {
			sp_pm.setPrefWidth(screen_width - 900);
			pane_pm.setPrefWidth(screen_width - 900);
			vbox_pm.setPrefWidth(screen_width - 900);
			hb1.setPrefWidth(screen_width - 900);
			hb2.setPrefWidth(screen_width - 900);
			hb3.setPrefWidth(screen_width - 900);
			table_pm.setPrefWidth(screen_width-1000);
		}
		if(screen_width == 1768.0) {
			sp_pm.setPrefWidth(screen_width - 800);
			pane_pm.setPrefWidth(screen_width - 800);
			vbox_pm.setPrefWidth(screen_width - 800);
			hb1.setPrefWidth(screen_width - 800);
			hb2.setPrefWidth(screen_width - 800);
			hb3.setPrefWidth(screen_width - 800);
			table_pm.setPrefWidth(screen_width - 900);
		}
		if(screen_width == 1600.0) {
			sp_pm.setPrefWidth(screen_width - 600);
			pane_pm.setPrefWidth(screen_width - 600);
			vbox_pm.setPrefWidth(screen_width - 600);
			hb1.setPrefWidth(screen_width - 600);
			hb2.setPrefWidth(screen_width - 600);
			hb3.setPrefWidth(screen_width - 600);
			table_pm.setPrefWidth(screen_width - 500);
		}
		if(screen_width == 1440.0) {
			sp_pm.setPrefWidth(screen_width - 600);
			pane_pm.setPrefWidth(screen_width - 600);
			vbox_pm.setPrefWidth(screen_width - 600);
			hb1.setPrefWidth(screen_width - 600);
			hb2.setPrefWidth(screen_width - 600);
			hb3.setPrefWidth(screen_width - 600);
			table_pm.setPrefWidth(screen_width - 500);
		}
		if(screen_width == 1366.0) {
			sp_pm.setPrefWidth(screen_width - 500);
			pane_pm.setPrefWidth(screen_width - 500);
			vbox_pm.setPrefWidth(screen_width - 500);
			hb1.setPrefWidth(screen_width - 600);
			hb2.setPrefWidth(screen_width - 600);
			hb3.setPrefWidth(screen_width - 600);
			table_pm.setPrefWidth(screen_width - 600);
		}
		if(screen_width == 1360.0) {
			sp_pm.setPrefWidth(screen_width - 500);
			pane_pm.setPrefWidth(screen_width - 500);
			vbox_pm.setPrefWidth(screen_width - 500);
			hb1.setPrefWidth(screen_width - 600);
			hb2.setPrefWidth(screen_width - 600);
			hb3.setPrefWidth(screen_width - 600);
			table_pm.setPrefWidth(screen_width - 600);
		}
		if(screen_width == 1280.0) {
			sp_pm.setPrefWidth(screen_width - 500);
			pane_pm.setPrefWidth(screen_width - 500);
			vbox_pm.setPrefWidth(screen_width - 500);
			hb1.setPrefWidth(screen_width - 550);
			hb2.setPrefWidth(screen_width - 550);
			hb3.setPrefWidth(screen_width - 550);
			table_pm.setPrefWidth(screen_width - 550);
		}
		upd_pm.setDisable(true);
		del_pm.setDisable(true);
		add_ap_pm.setDisable(true);
		dup_rec_pm.setDisable(true);
		
		if(conn_connector.LANG_ID == 1)
			lang_fun("en", "EN");
		if(conn_connector.LANG_ID == 0)
			lang_fun("ru", "RU");
		if(conn_connector.LANG_ID == 2)
			lang_fun("zh", "CN");
		if(conn_connector.LANG_ID == -1)
			lang_fun("ru", "RU");
		
		scl._style(add_ap_pm);
		scl._style(add_pm);
		scl._style(upd_pm);
		scl._style(del_pm);
		scl._style(close_pm);
		scl._style(upd_table_pm);
		scl._style(dup_rec_pm);
		//Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		//Double screen_width = primaryScreenBounds.getWidth();
		//Double screen_hight = primaryScreenBounds.getHeight(); 
		
		//sp_pm.setPrefWidth(screen_width - 230);
		//sp_pm.setPrefHeight(screen_hight - 30);
				
		initData();
		
		columns_pm = table_pm.getColumns();	
		
		//устанавливаем права для кнопки удалить
		if(conn_connector.USER_ROLE.equals("Technics")) // || conn_connector.USER_ROLE.equals("Engeneer")
			del_pm.setDisable(true);
			
		col_id_pm.setCellValueFactory(CellData -> CellData.getValue().IdProperty());
		col_ninst_pm.setCellValueFactory(CellData -> CellData.getValue().num_instProperty());
		col_eq_id.setCellValueFactory(CellData -> CellData.getValue().EquipProperty());
		col_period.setCellValueFactory(CellData -> CellData.getValue().PMCProperty());
//		col_eq_id.setCellValueFactory(CellData -> CellData.getValue().eq_idProperty());
		col_group_pm.setCellValueFactory(CellData -> CellData.getValue().Group_PMProperty());
		col_group_pm.setCellValueFactory(TooltippedTableCell.showNextDate());
//		col_lm_pm.setCellValueFactory(CellData -> CellData.getValue().L_MProperty());
//		col_os_pm.setCellValueFactory(CellData -> CellData.getValue().O_SProperty());
//		col_equip_pm.setCellValueFactory(CellData -> CellData.getValue().EquipProperty());
//		col_group_eq.setCellValueFactory(CellData -> CellData.getValue().Group_EQProperty());
		//col_supplier_pm.setCellValueFactory(CellData -> CellData.getValue().S_SProperty());
		//col_mtt_pm.setCellValueFactory(CellData -> CellData.getValue().MTTProperty());
//		col_pmn_pm.setCellValueFactory(CellData -> CellData.getValue().PMNProperty());
//		col_pmc_pm.setCellValueFactory(CellData -> CellData.getValue().PMCProperty());
//		col_pmtype_pm.setCellValueFactory(CellData -> CellData.getValue().TPOTProperty());
		col_ool_pm.setCellValueFactory(CellData -> CellData.getValue().OOLProperty());
		col_otv_pm.setCellValueFactory(CellData -> CellData.getValue().OtvProperty());
		col_isp_pm.setCellValueFactory(CellData -> CellData.getValue().Otv_IspProperty());
		
//		col_id_pm.setSortable(false);
//		col_ninst_pm.setSortable(false);
//		col_eq_id.setSortable(false);
//		col_lm_pm.setSortable(false);
//		col_os_pm.setSortable(false);
//		col_equip_pm.setSortable(false);
//		col_group_pm.setSortable(false);
//		col_group_eq.setSortable(false);
//		col_pmn_pm.setSortable(false);
//		col_pmc_pm.setSortable(false);
//		col_pmtype_pm.setSortable(false);
//		col_ool_pm.setSortable(false);
//		col_otv_pm.setSortable(false);
//		col_days_exp.setSortable(false);
		
		table_pm.setEditable(true);
		final ObservableList<TableColumn<hmmr_pm_model, ?>> columns = table_pm.getColumns();
//		col_eq_id.setCellValueFactory(
//                new Callback<TableColumn.CellDataFeatures<hmmr_pm_model, String>, ObservableValue<String>>() {
//
//                    @Override
//                    public ObservableValue<String> call(TableColumn.CellDataFeatures<hmmr_pm_model, String> arg0) {
//                        hmmr_pm_model data = arg0.getValue();
//                        return new SimpleObjectProperty<String>(qr._select_fillpm_equip(data.geteq_id(), "hmmr_pm"));
//                    }
//
//                });
        
        //columns.add(col_eq_id);
		
		//Вызываем окно обновления по двойному клику на строке
		table_pm.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() == 2 ){
		               func_upd(table_pm.getSelectionModel().getSelectedItem().getId());
		           }
			}
		});  
		
	      //Клик мышью внутри таблицы активирует кнопки Обновить и Удалить
	        table_pm.setOnMouseClicked(new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					upd_pm.setDisable(false);
				//	if(!conn_connector.USER_ROLE.equals("Engeneer"))
					del_pm.setDisable(false);
					add_ap_pm.setDisable(false);
					dup_rec_pm.setDisable(false);
					_Id_Dup_Pm = Integer.parseInt(table_pm.getSelectionModel().getSelectedItem().getId());
				}
			});
	        add_pm.setOnAction(new EventHandler<ActionEvent>() {
	    		
	    		@Override
	    		public void handle(ActionEvent event) {
	    			try {
	    				upd_pm.setDisable(true);
	    				del_pm.setDisable(true);
	    				add_ap_pm.setDisable(true);
	    				dup_rec_pm.setDisable(true);
	    				
	    				pm_add(stage);
	    			} catch (IOException e) {
	    				e.printStackTrace();
	    			}
	    		}
	    	});
	        upd_pm.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					upd_pm.setDisable(true);
					del_pm.setDisable(true);
					add_ap_pm.setDisable(true);
					dup_rec_pm.setDisable(true);
					hmmr_pm_model _ccl1 = table_pm.getSelectionModel().getSelectedItem();
					try {
					func_upd(_ccl1.getId());
					} catch (Exception e) {
						
					}
				}
			});
	        del_pm.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					Alert alert = new Alert(AlertType.CONFIRMATION);
				    alert.setTitle("M&U - Delete Record Window");
				    hmmr_pm_model _ccl = table_pm.getSelectionModel().getSelectedItem();
				    
				    alert.setHeaderText("Вы действительно хотите удалить запись № = " + _ccl.getId() + "?");
				    //alert.setContentText("C:/MyFile.txt");
				 
				    // option != null.
				    Optional<ButtonType> option = alert.showAndWait();
				    if (option.get() == null) {
				       //label.setText("No selection!");
				    } else if (option.get() == ButtonType.OK) {
				  	   _ccl = table_pm.getSelectionModel().getSelectedItem();
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
	        close_pm.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					stage = (Stage) close_pm.getScene().getWindow();
					stage.close();
				}
			});
	        add_ap_pm.setOnAction(new EventHandler<ActionEvent>() {
				
				@SuppressWarnings("static-access")
				@Override
				public void handle(ActionEvent event) {
					String Otv_for_task = null;
					
					hmmr_pm_model _ccl = table_pm.getSelectionModel().getSelectedItem();
					//String sql_rez = qr._select_for_pmplan(_ccl.getGroup_PM());
					//for(int j = 0; j < qr._select_data_pmplan().size(); j++) {
					if(!_ccl.getGroup_PM().equals("0")) {
						if(!scl.parser_sql_str(qr._select_for_pmgroup(_ccl.getGroup_PM()), 0).equals(_ccl.getGroup_PM())) {
							try {
								String before_pars = qr._select_for_pmplan(_ccl.getGroup_PM()).get(0);
								String pereodic = scl.parser_sql_str(before_pars, 0);
								String b_date = scl.parser_sql_str(before_pars, 1);
								if(!b_date.equals("2018-10-10")) {
									String e_date = scl.parser_sql_str(before_pars, 2);
									@SuppressWarnings("unused")
									String shop = scl.parser_sql_str(before_pars, 3);
									Otv_for_task = scl.parser_sql_str(before_pars, 4);
									
									int pm_group = Integer.parseInt(_ccl.getGroup_PM());
									
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
									int gen_month = Math.abs(month_edate - month_bdate)*30;
									int gen_year = Math.abs(year_edate - year_bdate)*365;
									
									int _general = Math.round((gen_day + gen_month + gen_year)/_count);
									
									for (int i = 0; i < _general; i++) {
										LocalDate days = LocalDate.of(year_bdate, month_bdate, day_bdate).plusDays(_count);//Расчитываем даты когда заявка должна быть выполнена
										qr._insert_pm_year(_ccl.getId(), pm_group, days, Otv_for_task);
										_count = _cnt + _count;
									}
									qr._update_week_year(pm_group);
								}
								else
									scl._AlertDialog("Пожалуйста, измените дату старта ППР в справочнике Группа-Период!", "Внимание!");
							}
							catch (Exception e) {
								scl._AlertDialog("Не найден номер инструкции или имя цикла переодичности задано некорректно!", "Ошибка!");
							}
						}
						else
						{
							scl._AlertDialog("Группа "+ _ccl.getGroup_PM() +" уже добавлена в PM PLAN!", "Группа уже существует");
						}
					}
					else
						scl._AlertDialog("Группа 0 не может быть добавлена в PM PLAN! Введите корректный номер группы!", "Ошибка!");
					//LocalDate day256_2017 = LocalDate.ofYearDay(2018, 256);
					//System.out.println("DAY = "+ day_bdate + " MONTH = " + month_bdate + " YEAR = " + year_bdate);
					add_ap_pm.setDisable(true);
				}
			});
	        upd_table_pm.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					setPmItems(qr._select_data_pm2());
					columns_pm.get(0).setVisible(false);
				    columns_pm.get(0).setVisible(true);
				}
			});
	        _table_update_pm.addListener(new ListChangeListener<hmmr_pm_model>() {
			    @Override
				public void onChanged(Change<? extends hmmr_pm_model> c) {
					setPmItems(qr._select_data_pm2());
			    	table_pm.getColumns().get(0).setVisible(false);
			        table_pm.getColumns().get(0).setVisible(true);
			    }
			});
	   //Ставим фокус и опускаемся на последнюю строку таблицы     
	        table_pm.requestFocus();
	        table_pm.getFocusModel().focus(0);
	        table_pm.getSelectionModel().selectLast();
	        table_pm.scrollTo(table_pm.getItems().size());
	        
	        dup_rec_pm.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					try {
						pm_dup();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
	        searchPMDB.textProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					pmRows = newValue;
					showSearchedPMDB();
				}
			});
	}

	private void showSearchedPMDB() {
		if (pmRows.length() != 0) {
			ObservableList<hmmr_pm_model> searchedRows =  FXCollections.observableArrayList();
			ObservableList<hmmr_pm_model> tmpSearch =  FXCollections.observableArrayList();
			tmpSearch.addAll(pmDbRows);
			String[] searches = pmRows.split(",");

			for (String search : searches) {
				try {
					//If the search is OTV
					if (pmOTVs.contains(search)) {
						for (hmmr_pm_model i : tmpSearch) {
							if (i.getOtv().equals(search)) {
								searchedRows.add(i);
							}
						}
					} else if (pmPMCs.contains(search)) {
						for (hmmr_pm_model i : tmpSearch) {
							if (i.getPMC().equals(search)) {
								searchedRows.add(i);
							}
						}
					} else if (pmPMGroups.contains(search)) {
						for (hmmr_pm_model i : tmpSearch) {
							if (i.getGroup_PM().equals(search)) {
								searchedRows.add(i);
							}
						}
					} else {
						for (hmmr_pm_model i : tmpSearch) {
							if ( (i.getEquip().contains(search) || i.getnum_inst().contains(search))) {
								searchedRows.add(i);
							}
						}
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}


				 /*else if ()
				//If wpSearch one upper letter search by shop
				if (search.length() == 1 && Character.isUpperCase(search.charAt(0))) {
					for (hmmr_pm_model i : tmpSearch) {
						if (i.getEquip().charAt(0) == search.charAt(0)) {
							searchedRows.add(i);
						}
					}
					//If the search is OTV
				} else if (search.equals("need select") || (search.length() <= 3 && wpOTVs.contains(search))) {
					for (hmmr_pm_model i : tmpSearch) {
						if (i.getOTV().equals(search)) {
							searchedRows.add(i);
						}
					}
				}
				//If the search is a number than search in PM
				else if (StringUtils.isNumeric(search)) {
					for (hmmr_pm_model i : tmpSearch) {
						if (i.getPM_Num().contains(search)) {
							searchedRows.add(i);
						}
					}
				}
				//If the search is a date
				else if (isDate(search)) {
					for (hmmr_pm_model i : tmpSearch) {
						if (i.getD_D().equals(search)) {
							searchedRows.add(i);
						}
					}
				}
				// else search in equipment and description
				else {
					for (hmmr_pm_model i : tmpSearch) {
						if (i.getEquip().contains(search) || i.getDesc().contains(search)) {
							searchedRows.add(i);
						}
					}
				}*/
				tmpSearch.clear();
				tmpSearch.addAll(searchedRows);
				searchedRows.clear();
			}
			table_pm.setItems(tmpSearch);
		} else {
			table_pm.setItems(pmDbRows);
		}
		table_pm.getColumns().get(0).setVisible(false);
		table_pm.getColumns().get(0).setVisible(true);
	}

	private void initData()
	{
		setPmItems(qr._select_data_pm2());
	}

	private void setPmItems(ObservableList<hmmr_pm_model> select_data_pm2) {
		pmDbRows = select_data_pm2;
		getPms();
		showSearchedPMDB();
	}

	private void getPms() {
		if (isPmsGet) return;
		for (hmmr_pm_model i : pmDbRows) {
			pmOTVs.add(i.getOtv());
			pmPMCs.add(i.getPMC());
			pmPMGroups.add(i.getGroup_PM());
		}
		isPmsGet = true;
	}


	private void func_upd(String str)
	{
		 
		String _sql_rez = qr._select_for_update_pm(str);
		_id_pm = str;
		_ninst_pm_upd = scl.parser_sql_str(_sql_rez, 0);
		_eq_id_upd = scl.parser_sql_str(_sql_rez, 1);
		_group_pm_upd = scl.parser_sql_str(_sql_rez, 2);
		_otv = scl.parser_sql_str(_sql_rez, 3);
		_ool_pm_upd = scl.parser_sql_str(_sql_rez, 4);
		_pm_exec = scl.parser_sql_str(_sql_rez, 5);
		_shop_pm = scl.parser_sql_str(_sql_rez, 6);
		_groupeq_pm = scl.parser_sql_str(_sql_rez, 7);
		_lm_pm = scl.parser_sql_str(_sql_rez, 8);
		_os_pm = scl.parser_sql_str(_sql_rez, 9);
		_equip_pm = scl.parser_sql_str(_sql_rez, 10);
				
		try {
			pm_upd(stage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void func_del(String str)
	{
		qr._update_rec_pm_del(str);
		qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + " - Удалил запись № = " + str + " в таблице PM");
		_id_pm = str;
		_table_update_pm.addAll(qr._select_data_pm2());
	}
	
	//Вызываем окно добавления записи к PM
	protected void pm_add(Stage stage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("add_rec_pm.fxml"));
		   
	    Scene scene = new Scene(root);
	    stage.setTitle("M&U - Add Record Window");
	    stage.setResizable(false);
	    //stage.initModality(Modality.APPLICATION_MODAL);
	    stage.setScene(scene);
	    stage.show();
	}
	//Вызываем окно обновления записи
	protected void pm_upd(Stage stage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("upd_rec_pm.fxml"));
				   
	    Scene scene = new Scene(root);
	    stage.setTitle("M&U - Update Record Window");
	    stage.setResizable(false);
	    //stage.initModality(Modality.APPLICATION_MODAL);
	    stage.setScene(scene);
	    stage.show();
	}
	//Добавляем эту функцию, чтобы окна добавить и обновить были модальными. Без этой функции выпадает эксэпшн!
	@FXML
	public void logoutWindow() throws IOException {
	    stage.showAndWait();
	}
	
	private void lang_fun(String loc1, String loc2)
	{
		ResourceBundle lngBndl = ResourceBundle
	            .getBundle("bundles.LangBundle", new Locale(loc1, loc2));
		 
		col_ninst_pm.setText(lngBndl.getString("col_ninst_pm"));
		//col_eq_id.setText(lngBndl.getString("col_shop_pm"));
		col_group_pm.setText(lngBndl.getString("col_group_eq"));
		col_ool_pm.setText(lngBndl.getString("col_ool_pm"));
		col_otv_pm.setText(lngBndl.getString("lbl_otv_ap"));
//		col_days_exp.setText(lngBndl.getString("col_days_exp"));
		add_ap_pm.setText(lngBndl.getString("add_ap_pm"));
		add_pm.setText(lngBndl.getString("add_tsk"));
		upd_pm.setText(lngBndl.getString("upd_wr"));
		del_pm.setText(lngBndl.getString("del_inst"));
		close_pm.setText(lngBndl.getString("cancel_tsk"));
		upd_table_pm.setText(lngBndl.getString("upd_table_wr"));
	}
	
	//Вызываем окно дублирования записи
		protected void pm_dup() throws IOException {
			Parent root = FXMLLoader.load(getClass().getResource("duplicate_rec_pm.fxml"));
			Scene scene = new Scene(root);
		    Stage stage_set = new Stage();
			stage_set.initModality(Modality.WINDOW_MODAL);	
			stage_set.initOwner(mu_main_controller.getPrimaryStage());
		    stage_set.setTitle("M&U - Duplicate Record Window");
		    stage_set.setResizable(false);
		    stage_set.setScene(scene);
		    stage_set.show();
		}
}
