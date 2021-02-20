package ru.haval.action;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.haval.db._query;
import ru.haval.filter.IFilter;
import ru.haval.filter.SQLFilter;
import ru.haval.share_class.s_class;

public class FilterController {

    private IFilter filter;
    private SQLFilter sqlFilter;

    @FXML
    TextArea filter_query;

    @FXML
    TextField var_value;

    @FXML
    JFXButton filter_apply, save_filter, del_filter, cancel_ot;

    @FXML
    ComboBox<String> filter_list, variable_list;

    private final _query qr = new _query();
    s_class sclass = new s_class();

    @FXML
    public void initialize() {
        sclass._style(filter_apply);
        sclass._style(save_filter);
        sclass._style(del_filter);
        sclass._style(cancel_ot);

//        filter_query.setText(filter.getSqlFilter());
        filter_query.setWrapText(true);

        filter_list.setItems(qr.getFiltersNames());

        filter_apply.setOnAction((ActionEvent event) -> {
//            filter.setSqlFilter(filter_query.getText());
            if (sqlFilter != null) {
                filter.setSqlFilter(sqlFilter.getQuery());
            }
            else {
                filter.setSqlFilter(filter_query.getText());
            }
//            System.out.println(sqlFilter.getQuery());
            Stage stage = (Stage) filter_list.getScene().getWindow();
            stage.close();
        });

        save_filter.setOnAction((ActionEvent event) -> {
            if (filter_list.getSelectionModel().getSelectedItem() != null) {
                final String NAME = filter_list.getSelectionModel().getSelectedItem();
                final String FILTER = filter_query.getText();
                qr.saveSqlFilter(NAME, FILTER);
                filter_list.setItems(qr.getFiltersNames());
            }
        });

        del_filter.setOnAction((ActionEvent event) -> {
            if (filter_list.getSelectionModel().getSelectedItem() != null) {
                qr.deleteFilterByName(filter_list.getSelectionModel().getSelectedItem());
                filter_list.setItems(qr.getFiltersNames());
            }
        });

        cancel_ot.setOnAction((ActionEvent event) -> {
            Stage stage = (Stage) filter_list.getScene().getWindow();
            stage.close();
        });

        filter_list.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            if (filter_list.getSelectionModel().getSelectedItem() != null) {
                final String name = filter_list.getSelectionModel().getSelectedItem();
                final String query = qr.getFilterByName(name);
                filter_query.setText(query);
                sqlFilter = SQLFilter.setSqlFilter(query);
                if (sqlFilter != null) {
                    variable_list.setItems(sqlFilter.getVars());
                    variable_list.getSelectionModel().select(0);
                }
                else {
                    variable_list.setItems(FXCollections.observableArrayList());
                }
            }
        });

        variable_list.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (variable_list.getSelectionModel().getSelectedItem() != null) {
                var_value.setText(sqlFilter.getVariableValueByName(variable_list.getSelectionModel().getSelectedItem()));
            }
            else {
                var_value.setText("");
            }
        });

        filter_query.textProperty().addListener((observable, oldValue, newValue) -> sqlFilter = SQLFilter.setSqlFilter(filter_query.getText()));

        var_value.textProperty().addListener((observable, oldValue, newValue) -> {
            if (variable_list.getSelectionModel().getSelectedItem() != null) {
                final String VARIABLE_NAME = variable_list.getSelectionModel().getSelectedItem();
                filter_query.setText(sqlFilter.changeVariable(VARIABLE_NAME, newValue));
            }
        });
    }

    public void setFilter(IFilter filter) {
        this.filter = filter;
    }
}
