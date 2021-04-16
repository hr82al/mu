package ru.haval.action;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTimePicker;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import ru.haval.db._query;
import ru.haval.share_class.s_class;

import java.time.*;

public class AddNewWrsController {
    @FXML
    public javafx.scene.control.TableView<hmmr_ap_model> table_ap = new TableView<>();

    @FXML
    TableColumn<hmmr_ap_model, String> desc_ap;
    @FXML
    TableColumn<hmmr_ap_model, String> dd_ap;
    @FXML
    TableColumn<hmmr_ap_model, String> equip_ap;
    @FXML
    TableColumn<hmmr_ap_model, DatePicker> begin_date, end_date;
    @FXML
    TableColumn<hmmr_ap_model, JFXTimePicker> begin_time, end_time;
    @FXML
    JFXButton add_wrs;
    @FXML
    TextArea report;
    @FXML
    JFXTimePicker beginTime;
    @FXML
    DatePicker beginDate;

    private boolean is_dates_correct = false;

    s_class scl = new s_class();
    _query qr = new _query();

    @FXML
    public void initialize(){

        beginTime.setIs24HourView(true);
        report.setWrapText(true);

        beginTime.setValue(LocalTime.now());
        beginDate.setValue(LocalDate.now().minusDays(1));
        cutSeconds(beginTime);

        apwr_controller apwr = apwr_controller.getInstance();

        scl._style(add_wrs);
        add_wrs.setDisable(true);

        add_wrs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) add_wrs.getScene().getWindow();
                stage.close();
                synchronized (addrec_wr_controller.class) {
                    Task<Void> task = new Task<Void>() {
                        @Override
                        protected Void call() throws Exception {
                            final String old_button_caption = apwr_controller.getInstance().add_wr.getText();
                            final String old_counter_caption = hmmr_ap_model.getTotal();
                            ObservableList<hmmr_ap_model> haps = table_ap.getItems();
                            int counter = haps.size();
                            Platform.runLater(() -> {
                                apwr_controller.getInstance().add_wr.setDisable(true);
                            });
                            for (hmmr_ap_model i : haps) {
                                s_class.addWorkRecord(i.getId().substring(2), report.getText(), LocalTime.parse(i.getBeginTime()), LocalDate.parse(i.getBeginDate()), LocalTime.parse(i.getEndTime()), LocalDate.parse(i.getEndDate()));
                                final String button_caption = counter + " осталось";
                                Platform.runLater(() -> {
                                    apwr_controller.getInstance().add_wr.setText(button_caption);
                                    hmmr_ap_model.setTotal(button_caption);
                                });
                                counter--;
                            }
                            Platform.runLater(() -> {
                                apwr_controller.getInstance().add_wr.setDisable(false);
                                apwr_controller.getInstance().add_wr.setText(old_button_caption);
                                hmmr_ap_model.setTotal(old_counter_caption);
                            });
                            return null;
                        }
                    };
                    Thread thread = new Thread(task);
                    thread.setPriority(Thread.MIN_PRIORITY);
                    thread.start();
                }
            }
        });

        report.textProperty().addListener((event) -> {
            check_buttons();
        });

        //Fill table with only works hwo belong to the executor.
        ObservableList<hmmr_ap_model> items = FXCollections.observableArrayList();
        for (hmmr_ap_model i : apwr.table_ap.getSelectionModel().getSelectedItems()) {
            if (i.getOTV().equals(apwr_controller.USER_S)) {
                items.add(i);
            }
        }
        table_ap.setItems(items);
        desc_ap.setCellValueFactory(CellData -> CellData.getValue().DescProperty());
        dd_ap.setCellValueFactory(CellData -> CellData.getValue().D_DProperty());
        equip_ap.setCellValueFactory(CellData -> CellData.getValue().EquipProperty());

        begin_date.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<hmmr_ap_model, DatePicker>, ObservableValue<DatePicker>>() {
            @Override
            public ObservableValue<DatePicker> call(TableColumn.CellDataFeatures<hmmr_ap_model, DatePicker> param) {
                hmmr_ap_model ap_model = param.getValue();
                DatePicker datePicker = new DatePicker();
                if (ap_model.getBeginDate() != null) {
                    datePicker.setValue(LocalDate.parse(ap_model.getBeginDate()));
                    datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
                        ap_model.setBeginDate(newValue.toString());
                    });
                }
                return new SimpleObjectProperty<DatePicker>(datePicker);
            }
        });
        end_date.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<hmmr_ap_model, DatePicker>, ObservableValue<DatePicker>>() {
            @Override
            public ObservableValue<DatePicker> call(TableColumn.CellDataFeatures<hmmr_ap_model, DatePicker> param) {
                hmmr_ap_model ap_model = param.getValue();
                DatePicker datePicker = new DatePicker();
                if (ap_model.getBeginDate() != null) {
                    datePicker.setValue(LocalDate.parse(ap_model.getEndDate()));
                    datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
                        ap_model.setEndDate(newValue.toString());
                    });
                }
                return new SimpleObjectProperty<DatePicker>(datePicker);
            }
        });

        begin_time.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<hmmr_ap_model, JFXTimePicker>, ObservableValue<JFXTimePicker>>() {
            @Override
            public ObservableValue<JFXTimePicker> call(TableColumn.CellDataFeatures<hmmr_ap_model, JFXTimePicker> param) {
                hmmr_ap_model ap_model = param.getValue();
                JFXTimePicker timePicker = new JFXTimePicker();
                timePicker.setIs24HourView(true);
                if (ap_model.getBeginTime() != null) {
                    timePicker.setValue(LocalTime.parse(ap_model.getBeginTime()));
                    timePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
                        ap_model.setBeginTime(newValue.toString());
                    });
                }
                return new SimpleObjectProperty<JFXTimePicker>(timePicker);
            }
        });
        end_time.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<hmmr_ap_model, JFXTimePicker>, ObservableValue<JFXTimePicker>>() {
            @Override
            public ObservableValue<JFXTimePicker> call(TableColumn.CellDataFeatures<hmmr_ap_model, JFXTimePicker> param) {
                hmmr_ap_model ap_model = param.getValue();
                JFXTimePicker timePicker = new JFXTimePicker();
                timePicker.setIs24HourView(true);
                if (ap_model.getEndTime() != null) {
                    timePicker.setValue(LocalTime.parse(ap_model.getEndTime()));
                    timePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
                        ap_model.setEndTime(newValue.toString());
                    });
                }
                return new SimpleObjectProperty<JFXTimePicker>(timePicker);
            }
        });
        updateTable();


        beginTime.setOnAction((event) -> updateTable());
        beginDate.setOnAction((event -> updateTable()));
    }

    private void cutSeconds(JFXTimePicker beginTime) {
        beginTime.setValue(LocalTime.of(beginTime.getValue().getHour(), beginTime.getValue().getMinute()));
    }

    private void recalculate(){
        ZoneOffset zoneOffset = ZoneId.systemDefault().getRules().getOffset(LocalDateTime.now());
        long beginEpochSecond = beginDate.getValue().toEpochSecond(beginTime.getValue(), zoneOffset);
        LocalDateTime beginDateTime = LocalDateTime.ofEpochSecond(beginEpochSecond,0, zoneOffset);
        LocalDateTime lastDateTime = beginDateTime;
        long full_time = 0;
        boolean has_fore_dates = false;
        LocalDate current_date = LocalDate.now();
        for (hmmr_ap_model i : table_ap.getItems()) {
            long totalTime = qr.getTotalWorkTime(i.getPM_Num());
            i.setBeginTime(lastDateTime.toLocalTime().toString());
            i.setBeginDate(lastDateTime.toLocalDate().toString());
            lastDateTime = lastDateTime.plusMinutes(totalTime);
            i.setEndTime(lastDateTime.toLocalTime().toString());
            i.setEndDate(lastDateTime.toLocalDate().toString());
            full_time += totalTime;
        }
        if (full_time > 720 || lastDateTime.isAfter(LocalDateTime.of(LocalDate.now(), LocalTime.of(0,0)))) {
            is_dates_correct = false;
        }
        else {
            is_dates_correct = true;
        }
    }

    private void updateTable() {
        recalculate();
        check_buttons();
        table_ap.getColumns().get(0).setVisible(false);
        table_ap.getColumns().get(0).setVisible(true);
    }

    private void check_buttons() {
        if (report.getText().length() > 0 && is_dates_correct) {
            add_wrs.setText("Выполнить");
            add_wrs.setDisable(false);
        } else {
            add_wrs.setDisable(true);
            if (report.getText().length() == 0) {
                add_wrs.setText("Введите описание");
            }
            if (!is_dates_correct) {
                add_wrs.setText("Время > 720 min или неверная дата");
            }
        }
    }
}
