package ru.haval.action;
//Pm instructions

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import com.jfoenix.controls.JFXButton;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import ru.haval.application.conn_connector;
import ru.haval.application.mu_main_controller;
import ru.haval.data.FxDatePickerConverter;
import ru.haval.db._query;
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
import ru.haval.filter.DynamicFilter;
import ru.haval.share_class.s_class;

public class pm_controller {

    DynamicFilter dynamicFilter;

    @FXML
    TextField searchPMDB;

    @FXML
    TableView<hmmr_pm_model> table_pm;

    @FXML
    TableColumn<hmmr_pm_model, String> col_id_pm, col_ninst_pm, col_group_pm, col_ool_pm, col_otv_pm, col_isp_pm, col_eq_id, col_period; //col_group_eq, col_lm_pm, col_os_pm, col_equip_pm, col_pmn_pm, col_pmc_pm, col_pmtype_pm,

    @FXML
    JFXButton add_ap_pm, add_pm, upd_pm, del_pm, close_pm, upd_table_pm, dup_rec_pm;

    public static String _id_pm, _ninst_pm_upd, _eq_id_upd, _group_pm_upd, _ool_pm_upd, _otv, _pm_exec, _shop_pm, _groupeq_pm, _lm_pm, _os_pm, _equip_pm;// _group_eq_upd, _lm_pm_upd, _os_pm_upd, _equip_pm_upd, _pmn_pm_upd, _pmc_pm_upd, _pmtype_pm_upd,

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
    //private static ObservableList<hmmr_pm_model> pmDbRows = FXCollections.observableArrayList();
    //TableColumn<hmmr_pm_model, String> col_eq_id = new TableColumn<hmmr_pm_model, String>(name_col);

    public static int _Id_Dup_Pm = 0;
    public static String _num_inst_last = "NULL"; //Номер последней выбранной инструкции

    public boolean responsible;

    boolean isPmsGet = false;

    ResourceBundle lngBndl;

    @FXML
    public void initialize() {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        Double screen_width = primaryScreenBounds.getWidth();
        Double screen_hight = primaryScreenBounds.getHeight();

        table_pm.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

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

        if (screen_width == 1920.0) {
            sp_pm.setPrefWidth(screen_width - 900);
            pane_pm.setPrefWidth(screen_width - 900);
            vbox_pm.setPrefWidth(screen_width - 900);
            hb1.setPrefWidth(screen_width - 900);
            hb2.setPrefWidth(screen_width - 900);
            hb3.setPrefWidth(screen_width - 900);
            table_pm.setPrefWidth(screen_width - 1000);
        }
        if (screen_width == 1768.0) {
            sp_pm.setPrefWidth(screen_width - 800);
            pane_pm.setPrefWidth(screen_width - 800);
            vbox_pm.setPrefWidth(screen_width - 800);
            hb1.setPrefWidth(screen_width - 800);
            hb2.setPrefWidth(screen_width - 800);
            hb3.setPrefWidth(screen_width - 800);
            table_pm.setPrefWidth(screen_width - 900);
        }
        if (screen_width == 1600.0) {
            sp_pm.setPrefWidth(screen_width - 600);
            pane_pm.setPrefWidth(screen_width - 600);
            vbox_pm.setPrefWidth(screen_width - 600);
            hb1.setPrefWidth(screen_width - 600);
            hb2.setPrefWidth(screen_width - 600);
            hb3.setPrefWidth(screen_width - 600);
            table_pm.setPrefWidth(screen_width - 500);
        }
        if (screen_width == 1440.0) {
            sp_pm.setPrefWidth(screen_width - 600);
            pane_pm.setPrefWidth(screen_width - 600);
            vbox_pm.setPrefWidth(screen_width - 600);
            hb1.setPrefWidth(screen_width - 600);
            hb2.setPrefWidth(screen_width - 600);
            hb3.setPrefWidth(screen_width - 600);
            table_pm.setPrefWidth(screen_width - 500);
        }
        if (screen_width == 1366.0) {
            sp_pm.setPrefWidth(screen_width - 500);
            pane_pm.setPrefWidth(screen_width - 500);
            vbox_pm.setPrefWidth(screen_width - 500);
            hb1.setPrefWidth(screen_width - 600);
            hb2.setPrefWidth(screen_width - 600);
            hb3.setPrefWidth(screen_width - 600);
            table_pm.setPrefWidth(screen_width - 600);
        }
        if (screen_width == 1360.0) {
            sp_pm.setPrefWidth(screen_width - 500);
            pane_pm.setPrefWidth(screen_width - 500);
            vbox_pm.setPrefWidth(screen_width - 500);
            hb1.setPrefWidth(screen_width - 600);
            hb2.setPrefWidth(screen_width - 600);
            hb3.setPrefWidth(screen_width - 600);
            table_pm.setPrefWidth(screen_width - 600);
        }
        if (screen_width == 1280.0) {
            sp_pm.setPrefWidth(screen_width - 500);
            pane_pm.setPrefWidth(screen_width - 500);
            vbox_pm.setPrefWidth(screen_width - 500);
            hb1.setPrefWidth(screen_width - 550);
            hb2.setPrefWidth(screen_width - 550);
            hb3.setPrefWidth(screen_width - 550);
            table_pm.setPrefWidth(screen_width - 550);
        }
        if (screen_width < 1280) {
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

        if (conn_connector.LANG_ID == 1)
            lang_fun("en", "EN");
        if (conn_connector.LANG_ID == 0)
            lang_fun("ru", "RU");
        if (conn_connector.LANG_ID == 2)
            lang_fun("zh", "CN");
        if (conn_connector.LANG_ID == -1)
            lang_fun("ru", "RU");

        scl._style(add_ap_pm);
        scl._style(add_pm);
        scl._style(upd_pm);
        scl._style(del_pm);
        scl._style(close_pm);
        scl._style(upd_table_pm);
        scl._style(dup_rec_pm);

        initData();

        columns_pm = table_pm.getColumns();

        //устанавливаем права для кнопки удалить
        if (conn_connector.USER_ROLE.equals("Technics")) // || conn_connector.USER_ROLE.equals("Engeneer")
            del_pm.setDisable(true);

        col_id_pm.setCellValueFactory(CellData -> CellData.getValue().IdProperty());
        col_ninst_pm.setCellValueFactory(CellData -> CellData.getValue().num_instProperty());
        col_eq_id.setCellValueFactory(CellData -> CellData.getValue().EquipProperty());
        col_period.setCellValueFactory(CellData -> CellData.getValue().PMCProperty());
//		col_eq_id.setCellValueFactory(CellData -> CellData.getValue().eq_idProperty());
        col_group_pm.setCellValueFactory(CellData -> CellData.getValue().Group_PMProperty());

        //Shows the due date of howerd group in the pm editor
        col_group_pm.setCellFactory(new Callback<TableColumn<hmmr_pm_model, String>, TableCell<hmmr_pm_model, String>>() {
            @Override
            public TableCell<hmmr_pm_model, String> call(TableColumn<hmmr_pm_model, String> p) {
                return new TableCell<hmmr_pm_model, String>() {
                    TableCell<hmmr_pm_model, String> hmmr_pm_modelStringTableCell = this;

                    @Override
                    public void updateItem(String t, boolean empty) {

                        Tooltip tooltip = new Tooltip();
                        //super.updateItem(t, empty);
                        tooltip.setOnShowing(new EventHandler<WindowEvent>() {
                            @Override
                            public void handle(WindowEvent event) {
                                hmmr_pm_model myModel = getTableView().getItems().get(getTableRow().getIndex());
                                //tooltip.setText(myModel.getTip());
                                tooltip.setText("Due date: " + qr.getNextDateByPMGroup(myModel.getGroup_PM()));
                                setTooltip(tooltip);
                                setText(t.toString());
                            }
                        });

                        if (t == null) {
                            setTooltip(null);
                            setText(null);
                        } else {
                            tooltip.setText("");
                            setTooltip(tooltip);
                            setText(t.toString());
                        }
                    }
                };
            }
        });
        col_ool_pm.setCellValueFactory(CellData -> CellData.getValue().OOLProperty());
        col_otv_pm.setCellValueFactory(CellData -> CellData.getValue().OtvProperty());
        col_isp_pm.setCellValueFactory(CellData -> CellData.getValue().Otv_IspProperty());

        table_pm.setEditable(true);
        final ObservableList<TableColumn<hmmr_pm_model, ?>> columns = table_pm.getColumns();

        //Вызываем окно обновления по двойному клику на строке
        table_pm.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
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
                if (table_pm.getSelectionModel().getSelectedItem() != null) {
                    _Id_Dup_Pm = Integer.parseInt(table_pm.getSelectionModel().getSelectedItem().getId());
                }
            }
        });

        table_pm.setOnKeyPressed( e -> {
            changeButtonLabels();
        });

        table_pm.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            changeButtonLabels();
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
                if (table_pm.getSelectionModel().getSelectedItems().size() > 1) {
                    updateExecutor();
                } else {
                    hmmr_pm_model _ccl1 = table_pm.getSelectionModel().getSelectedItem();
                    try {
                        func_upd(_ccl1.getId());
                    } catch (Exception e) {

                    }
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
                if (table_pm.getSelectionModel().getSelectedItems().size() > 1) {
                    updateResp();
                }
                else {

                    String Otv_for_task = null;

                    hmmr_pm_model selectedItem = table_pm.getSelectionModel().getSelectedItem();

                    if (!selectedItem.getGroup_PM().equals("0")) {
                        if (!scl.parser_sql_str(qr._select_for_pmgroup(selectedItem.getGroup_PM()), 0).equals(selectedItem.getGroup_PM())) {
                            try {
                                String before_pars = qr._select_for_pmplan(selectedItem.getGroup_PM()).get(0);
                                String b_date = scl.parser_sql_str(before_pars, 1);
                                if (!b_date.equals("2018-10-10")) {
                                    String pmGroup = selectedItem.getGroup_PM();

                                    LocalDate beginDate = LocalDate.parse(b_date);
                                    String periodID = qr.getGroupCycleByGroup(selectedItem.getGroup_PM()).PM_Cycle;
                                    s_class.updatePmYearDates(selectedItem.getGroup_PM(), beginDate, selectedItem.getOtv(), periodID);

                                } else
                                    scl._AlertDialog("Пожалуйста, измените дату старта ППР в справочнике Группа-Период!", "Внимание!");
                            } catch (Exception e) {
                                scl._AlertDialog("Не найден номер инструкции или имя цикла переодичности задано некорректно!", "Ошибка!");
                            }
                        } else {
                            scl._AlertDialog("Группа " + selectedItem.getGroup_PM() + " уже добавлена в PM PLAN!", "Группа уже существует");
                        }
                    } else
                        scl._AlertDialog("Группа 0 не может быть добавлена в PM PLAN! Введите корректный номер группы!", "Ошибка!");
                    //LocalDate day256_2017 = LocalDate.ofYearDay(2018, 256);
                    //System.out.println("DAY = "+ day_bdate + " MONTH = " + month_bdate + " YEAR = " + year_bdate);
                    add_ap_pm.setDisable(true);
                }
            }
        });
        upd_table_pm.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                setPmItems(qr._select_data_pm2());
            }
        });
        _table_update_pm.addListener(new ListChangeListener<hmmr_pm_model>() {
            @Override
            public void onChanged(Change<? extends hmmr_pm_model> c) {
                setPmItems(qr._select_data_pm2());
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
//                initDynamicFilter();
                dynamicFilter.change(newValue);
            }
        });
    }

    private void initDynamicFilter() {
        if (dynamicFilter == null) {
            dynamicFilter = new DynamicFilter(table_pm);
        }
    }

    private void changeButtonLabels() {
        if (table_pm.getSelectionModel().getSelectedItems().size() > 1) {
            upd_pm.setText(lngBndl.getString("chg_exec"));
            add_ap_pm.setText(lngBndl.getString("chg_resp"));
        }
        else {
            upd_pm.setText(lngBndl.getString("upd_wr"));
            add_ap_pm.setText(lngBndl.getString("add_ap_pm"));
        }
    }

    private void updateResp() {
        responsible = true;
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("upd_oft.fxml"));
            Scene scene = new Scene(root);
            Stage stage_set = new Stage();
            stage_set.setUserData(this);
            stage_set.initModality(Modality.WINDOW_MODAL);
            stage_set.initOwner(this.stage);
            stage_set.setTitle("M&U - Изменение ответетвенного.");
            stage_set.setResizable(false);
            stage_set.setScene(scene);
            stage_set.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateExecutor() {
        Parent root = null;
        responsible = false;
        try {
            root = FXMLLoader.load(getClass().getResource("upd_oft.fxml"));
            Scene scene = new Scene(root);
            Stage stage_set = new Stage();
            stage_set.setUserData(this);
            stage_set.initModality(Modality.WINDOW_MODAL);
            stage_set.initOwner(this.stage);
            stage_set.setTitle("M&U - Изменение исполнителя.");
            stage_set.setResizable(false);
            stage_set.setScene(scene);
            stage_set.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void initData() {
        initDynamicFilter();
        setPmItems(qr._select_data_pm2());
    }

    public void setPmItems(ObservableList<hmmr_pm_model> select_data_pm2) {
        dynamicFilter.update(select_data_pm2, searchPMDB.getText());
    }

    private void func_upd(String str) {

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

    private void func_del(String str) {
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

    private void lang_fun(String loc1, String loc2) {
        lngBndl = ResourceBundle
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
