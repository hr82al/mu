package ru.haval.action;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.Sheet;
import ru.haval.application.Main;
import ru.haval.db._query;
import ru.haval.filter.*;
import ru.haval.share_class.s_class;
import ru.haval.application.conn_connector;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FilterController {

    private IFilter filter;
    private SQLFilter sqlFilter;
    private String type = "WR";

    @FXML
    TextArea filter_query;

    @FXML
    TextField var_value;

    @FXML
    JFXButton filter_apply, save_filter, del_filter, cancel_ot, to_excel;

    @FXML
    ComboBox<String> filter_list, variable_list;

    private final _query qr = new _query();
    s_class sclass = new s_class();
    Main mn = new Main();

    @FXML
    public void initialize() {
        sclass._style(filter_apply);
        sclass._style(save_filter);
        sclass._style(del_filter);
        sclass._style(cancel_ot);

        filter_query.setText(filter.getSqlFilter());
        filter_query.setWrapText(true);
        if (!conn_connector.USER_ROLE.equals("Administrator"))
            del_filter.setDisable(true);

        if (filter.getSqlFilter() != "") {
            sqlFilter = SQLFilter.setSqlFilter(filter.getSqlFilter());
            initVariable_list();
            setVar_value();
        }
        if (filter instanceof APFilter) {
            type = "AP";
        }
        filter_list.setItems(qr.getFiltersNames(type));

        filter_apply.setOnAction((ActionEvent event) -> {
            filter.setSqlFilter(filter_query.getText());
            Stage stage = (Stage) filter_list.getScene().getWindow();
            stage.close();
        });

        save_filter.setOnAction((ActionEvent event) -> {
            if (filter_list.getSelectionModel().getSelectedItem() != null) {
                final String NAME = filter_list.getSelectionModel().getSelectedItem();
                final String FILTER = filter_query.getText();
                qr.saveSqlFilter(NAME, FILTER);
                filter_list.setItems(qr.getFiltersNames(type));
            }
        });

        del_filter.setOnAction((ActionEvent event) -> {
            if (filter_list.getSelectionModel().getSelectedItem() != null) {
                qr.deleteFilterByName(filter_list.getSelectionModel().getSelectedItem());
                filter_list.setItems(qr.getFiltersNames(type));
            }
        });

        cancel_ot.setOnAction((ActionEvent event) -> {
            Stage stage = (Stage) filter_list.getScene().getWindow();
            stage.close();
        });

        to_excel.setOnAction((event -> {
            filter.setSqlFilter(filter_query.getText());
            createExcel();
            Stage stage = (Stage) filter_list.getScene().getWindow();
            stage.close();
        }));

        filter_list.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            if (filter_list.getSelectionModel().getSelectedItem() != null) {
                final String name = filter_list.getSelectionModel().getSelectedItem();
                final String query = qr.getFilterByName(name);
                filter_query.setText(query);
                sqlFilter = SQLFilter.setSqlFilter(query);
                initVariable_list();
            }
        });

        variable_list.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setVar_value();

        });


        filter_query.textProperty().addListener((observable, oldValue, newValue) -> sqlFilter = SQLFilter.setSqlFilter(filter_query.getText()));

        var_value.textProperty().addListener((observable, oldValue, newValue) -> {
            if (variable_list.getSelectionModel().getSelectedItem() != null) {
                final String VARIABLE_NAME = variable_list.getSelectionModel().getSelectedItem();
                filter_query.setText(sqlFilter.changeVariable(VARIABLE_NAME, newValue));
            }
        });
    }

    private void filterApply() {
        filter.setSqlFilter(filter_query.getText());
        createExcel();
        Stage stage = (Stage) filter_list.getScene().getWindow();
        stage.close();
    }

    private void createExcel() {
        String file = System.getenv().get("TMP") + "\\report.xlsx";
        XSSFWorkbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("report");
        if (filter instanceof APFilter) {
            TableView<hmmr_ap_model> table = apwr_controller.getInstance().table_ap;
            Row row = sheet.createRow(0);
            for (int i = 0; i < table.getColumns().size(); i++){
                row.createCell(i).setCellValue(table.getColumns().get(i).getText());
            }
            String[] s = new String[]{"flag_otv", "flag_oft", "flag_tm"};
            for (int i = 12; i < 15; i++){
                row.createCell(i).setCellValue(s[i - 12]);
            }

            for (int i = 0; i < table.getItems().size(); i++) {
                row = sheet.createRow(i + 1);
                for (int j = 0; j < 15; j++) {
                    row.createCell(j).setCellValue(table.getItems().get(i).getByIndex(j));
                }
            }
        }
        else if (filter instanceof WRFilter) {
            TableView<hmmr_wr_model> table = apwr_controller.getInstance().table_wr;
            Row row = sheet.createRow(0);
            for (int i = 0; i < table.getColumns().size(); i++){
                row.createCell(i).setCellValue(table.getColumns().get(i).getText());
            }
            for (int i = 0; i < table.getItems().size(); i++) {
                row = sheet.createRow(i + 1);
                for (int j = 0; j < 13; j++) {
                    row.createCell(j).setCellValue(table.getItems().get(i).getByIndex(j));
                }
            }
        }
        try (OutputStream fileOut = new FileOutputStream(file)) {
            book.write(fileOut);
            book.close();
        } catch (Exception e) {
            s_class._AlertDialog(e.getMessage());
        }
        try {
            mn._run_excel(new File(file));
        } catch (IOException e) {
            s_class._AlertDialog(e.getMessage());
        }
    }

    private void setVar_value() {
        if (variable_list.getSelectionModel().getSelectedItem() != null) {
            var_value.setText(sqlFilter.getVariableValueByName(variable_list.getSelectionModel().getSelectedItem()));
        }
        else {
            var_value.setText("");
        }
    }

    private void initVariable_list() {
        if (sqlFilter != null) {
            variable_list.setItems(sqlFilter.getVars());
            variable_list.getSelectionModel().select(0);
        }
        else {
            variable_list.setItems(FXCollections.observableArrayList());
        }
    }

    public void setFilter(IFilter filter) {
        this.filter = filter;
    }
}
